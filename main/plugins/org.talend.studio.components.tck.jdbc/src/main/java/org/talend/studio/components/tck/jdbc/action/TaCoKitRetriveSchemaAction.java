/**
 * Copyright (C) 2006-2021 Talend Inc. - www.talend.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.talend.studio.components.tck.jdbc.action;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.ui.gmf.util.DisplayUtils;
import org.talend.commons.ui.runtime.exception.ExceptionMessageDialog;
import org.talend.commons.ui.runtime.image.EImage;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.core.model.metadata.IMetadataConnection;
import org.talend.core.model.metadata.builder.ConvertionHelper;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.repository.model.ProjectRepositoryNode;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.metadata.managment.repository.ManagerConnection;
import org.talend.metadata.managment.utils.MetadataConnectionUtils;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.IRepositoryNode.EProperties;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.ui.views.IRepositoryView;
import org.talend.repository.ui.wizards.metadata.table.database.DatabaseTableWizard;
import org.talend.sdk.component.studio.Lookups;
import org.talend.sdk.component.studio.i18n.Messages;
import org.talend.sdk.component.studio.metadata.action.TaCoKitMetadataContextualAction;
import org.talend.sdk.component.studio.metadata.migration.TaCoKitMigrationManager;
import org.talend.sdk.component.studio.metadata.model.TaCoKitConfigurationItemModel;
import org.talend.sdk.component.studio.metadata.model.TaCoKitConfigurationModel;
import org.talend.sdk.component.studio.metadata.node.ITaCoKitRepositoryNode;
import org.talend.sdk.component.studio.ui.wizard.TaCoKitConfigurationRuntimeData;

/**
 * Metadata contextual action which creates WizardDialog used to edit Component configuration.
 * Repository node may have only 1 edit action. This action is registered as extension point.
 * Thus, it supports double click out of the box
 */
public class TaCoKitRetriveSchemaAction extends TaCoKitMetadataContextualAction {

    protected static final int WIZARD_WIDTH = 900;

    protected static final int WIZARD_HEIGHT = 495;
    
    public TaCoKitRetriveSchemaAction() {
        super();
        setImageDescriptor(ImageProvider.getImageDesc(EImage.EDIT_ICON));
    }

    @Override
    public void init(final RepositoryNode node) {
        boolean isLeafNode = false;
        if (node instanceof ITaCoKitRepositoryNode) {
            isLeafNode = ((ITaCoKitRepositoryNode) node).isLeafNode();
        }
        if (!isLeafNode) {
            setEnabled(false);
            return;
        }
        setRepositoryNode((ITaCoKitRepositoryNode) node);
        setConfigTypeNode(repositoryNode.getConfigTypeNode());
        setToolTipText(getEditLabel());
        Image nodeImage = getNodeImage();
        if (nodeImage != null) {
            this.setImageDescriptor(ImageDescriptor.createFromImage(nodeImage));
        }
        IProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();
        switch (node.getType()) {
        case SIMPLE_FOLDER:
        case SYSTEM_FOLDER:
            if (isUserReadOnly() || belongsToCurrentProject(node) || isDeleted(node)) {
                setEnabled(false);
                return;
            } else {
                this.setText(getCreateLabel());
                collectChildNames(node);
                setEnabled(true);
            }
            break;
        case REPOSITORY_ELEMENT:
            if (factory.isPotentiallyEditable(node.getObject()) && isLastVersion(node)) {
                this.setText(getEditLabel());
                collectSiblingNames(node);
                setReadonly(false);
            } else {
                this.setText(getOpenLabel());
                setReadonly(true);
            }
            setEnabled(true);
            break;
        default:
            return;
        }
    }

    @Override
    protected WizardDialog createWizardDialog() {
        IWizard wizard = null;
        try {
            wizard = createWizard(PlatformUI.getWorkbench());
        } catch (Exception e) {
            ExceptionHandler.process(e);
        } 
        return new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
    }

    public DatabaseTableWizard createWizard(final IWorkbench wb) throws Exception {
        TaCoKitConfigurationRuntimeData runtimeData = createRuntimeData();
        if (!runtimeData.isReadonly()) {
            try {
                TaCoKitConfigurationItemModel itemModel = new TaCoKitConfigurationItemModel(runtimeData.getConnectionItem());
                TaCoKitConfigurationModel configurationModel = new TaCoKitConfigurationModel(runtimeData.getConnectionItem().getConnection());
                TaCoKitMigrationManager migrationManager = Lookups.taCoKitCache().getMigrationManager();
                if (configurationModel.needsMigration()) {
                    String label = ""; //$NON-NLS-1$
                    try {
                        label = itemModel.getDisplayLabel();
                    } catch (Exception e) {
                        // ignore
                    }
                    MessageDialog dialog = new MessageDialog(DisplayUtils.getDefaultShell(),
                            Messages.getString("migration.check.dialog.title"), null, //$NON-NLS-1$
                            Messages.getString("migration.check.dialog.ask", label), MessageDialog.WARNING, //$NON-NLS-1$
                            new String[] { IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL }, 0);
                    int result = dialog.open();
                    if (result == 0) {
                        final Exception[] ex = new Exception[1];
                        ProgressMonitorDialog monitorDialog = new ProgressMonitorDialog(DisplayUtils.getDefaultShell());
                        monitorDialog.run(true, true, new IRunnableWithProgress() {

                            @Override
                            public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                                try {
                                    migrationManager.migrate(configurationModel, monitor);
                                } catch (Exception e) {
                                    ex[0] = e;
                                }
                            }
                        });
                        if (ex[0] != null) {
                            ExceptionMessageDialog.openWarning(DisplayUtils.getDefaultShell(),
                                    Messages.getString("migration.check.dialog.title"), //$NON-NLS-1$
                                    Messages.getString("migration.check.dialog.failed"), ex[0]); //$NON-NLS-1$
                            throw ex[0];
                        }
                    }
                }
            } catch (Exception e) {
                ExceptionHandler.process(e);
            }
        }
        
        final ManagerConnection managerConnection = new ManagerConnection();

        DatabaseConnection connection = ConvertionHelper.fillJDBCParams4TacokitDatabaseConnection(runtimeData.getConnectionItem().getConnection());
        //boolean useKrb = Boolean.valueOf(connection.getParameters().get(ConnParameterKeys.CONN_PARA_KEY_USE_KRB));
        // TUP-596 : Update the context name in connection when the user does a context switch in DI
        String oldContextName = connection.getContextName();
        Connection copyConnection = MetadataConnectionUtils.prepareConection(connection);
        if (copyConnection == null) {
            return null;
        }
        IMetadataConnection metadataConnection = ConvertionHelper.convert(copyConnection, false, copyConnection.getContextName());
        
        DatabaseTableWizard databaseTableWizard =
                new DatabaseTableWizard(PlatformUI.getWorkbench(), runtimeData.isCreation(), repositoryNode.getObject(), null, getExistingNames(), false, managerConnection, metadataConnection);
        return databaseTableWizard;
    } 

    private TaCoKitConfigurationRuntimeData createRuntimeData() {
        TaCoKitConfigurationRuntimeData runtimeData = new TaCoKitConfigurationRuntimeData();
        runtimeData.setTaCoKitRepositoryNode(repositoryNode);
        runtimeData.setConfigTypeNode(repositoryNode.getConfigTypeNode());
        runtimeData.setConnectionItem((ConnectionItem) repositoryNode.getObject().getProperty().getItem());
        runtimeData.setCreation(true);
        runtimeData.setReadonly(isReadonly());
        return runtimeData;
    }
    
    protected void handleWizard(ITaCoKitRepositoryNode node, WizardDialog wizardDialog) {
        wizardDialog.setPageSize(WIZARD_WIDTH, WIZARD_HEIGHT);
        wizardDialog.create();
        int result = wizardDialog.open();
        IRepositoryView viewPart = getViewPart();
        if (viewPart != null) {
            if (WizardDialog.CANCEL == result) {
                RepositoryNode rootNode = ProjectRepositoryNode.getInstance().getRootRepositoryNode(node, false);
                if (rootNode != null) {
                    rootNode.getChildren().clear();
                    rootNode.setInitialized(false);
                    viewPart.refresh(rootNode);
                }
            }
            viewPart.expand(node, true);
        }
        ERepositoryObjectType nodeType = (ERepositoryObjectType) node.getProperties(EProperties.CONTENT_TYPE);
        if (nodeType.isSubItem()) { // edit table
            RepositoryNode parent = node.getParent();
            if (parent.getObject() == null) { // db
                parent = parent.getParent();
            }
        }
    }

    protected String getEditLabel() {
        return "Retrieve schema";
    }
}
