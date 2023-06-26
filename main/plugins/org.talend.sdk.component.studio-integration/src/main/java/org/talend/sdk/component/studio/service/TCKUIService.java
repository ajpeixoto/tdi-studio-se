// ============================================================================
//
// Copyright (C) 2006-2023 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.sdk.component.studio.service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.repository.model.ProjectRepositoryNode;
import org.talend.core.service.ITCKUIService;
import org.talend.repository.model.IRepositoryNode;
import org.talend.repository.model.RepositoryNode;
import org.talend.sdk.component.server.front.model.ConfigTypeNode;
import org.talend.sdk.component.studio.Lookups;
import org.talend.sdk.component.studio.metadata.TaCoKitCache;
import org.talend.sdk.component.studio.metadata.action.CreateTaCoKitConfigurationAction;
import org.talend.sdk.component.studio.metadata.node.ITaCoKitRepositoryNode;
import org.talend.sdk.component.studio.metadata.node.TaCoKitFamilyRepositoryNode;
import org.talend.sdk.component.studio.metadata.provider.TaCoKitMetadataContentProvider;
import org.talend.sdk.component.studio.ui.wizard.TaCoKitCreateWizard;
import org.talend.sdk.component.studio.util.TCKImageCache;
import org.talend.sdk.component.studio.util.TaCoKitConst;

public class TCKUIService implements ITCKUIService {

    @Override
    // TODO move this to extension along with snowflake
    public List<IRepositoryNode> mergeTCKDBRepositoryNode(Object[] base) {
        List<IRepositoryNode> baseList = Stream.of(base).map(IRepositoryNode.class::cast).collect(Collectors.toList());
        Object[] toMerge = null;
        Map<String, ConfigTypeNode> nodes = Lookups.taCoKitCache().getConfigTypeNodeMap();
        ConfigTypeNode configTypeNode = nodes.values().stream().filter(node -> "JDBCNew".equals(node.getName())).findFirst()
                .orElse(null);
        if (configTypeNode != null) {
            try {
                RepositoryNode parent = ProjectRepositoryNode.getInstance()
                        .getRootRepositoryNode(ERepositoryObjectType.METADATA_CONNECTIONS, false);
                TaCoKitFamilyRepositoryNode fakeNode = new TaCoKitFamilyRepositoryNode(parent, configTypeNode.getDisplayName(),
                        configTypeNode);
                toMerge = new TaCoKitMetadataContentProvider().getChildren(fakeNode);
            } catch (Exception e) {
                ExceptionHandler.process(e);
            }
        }
        if (toMerge == null || toMerge.length == 0) {
            return baseList;
        }

        List<IRepositoryNode> toMergeList = Stream.of(toMerge).map(IRepositoryNode.class::cast).collect(Collectors.toList());
        // TODO merge children trees for duplicate folder
        // still use/create traditional folder
        baseList.addAll(toMergeList);
        return baseList;
    }

    @Override
    public Image getTCKImage(Object element, BiFunction<Image, IRepositoryViewObject, Image> decorator) {
        return TCKImageCache.getImage(element, decorator);
    }

    @Override
    public boolean isTCKRepoistoryNode(RepositoryNode node) {
        return ITaCoKitRepositoryNode.class.isInstance(node);
    }

    @Override
    public ERepositoryObjectType getTCKRepositoryType(String componentName) {
        TaCoKitCache cache = Lookups.taCoKitCache();
        Map<String, ConfigTypeNode> configTypeNodeMap = cache.getConfigTypeNodeMap();
        ConfigTypeNode configTypeNode = configTypeNodeMap.values().stream().filter(n -> componentName.equals(n.getName()))
                .findFirst().orElse(null);
        if (configTypeNode != null) {
            return cache.getRepositoryObjectType2ConfigTypeNodeMap().entrySet().stream()
                    .filter(en -> en.getValue().equals(configTypeNode)).map(Map.Entry::getKey).findFirst().orElse(null);
        }
        return null;
    }

    @Override
    public int openTCKWizard(String type, boolean creation, Object node, String[] existingNames) {
        // TODO find a way to sync creation, node, existingNames if needed
        TaCoKitCache cache = Lookups.taCoKitCache();
        Map<String, ConfigTypeNode> configTypeNodeMap = cache.getConfigTypeNodeMap();
        ConfigTypeNode configTypeNode = configTypeNodeMap.values().stream().filter(n -> type.equals(n.getName())).findFirst()
                .orElse(null);
        CreateTaCoKitConfigurationAction createAction = null;
        if (configTypeNode != null) {
            Set<String> edges = configTypeNode.getEdges();
            if (edges != null && !edges.isEmpty()) {
                List<String> edgeArray = new LinkedList<String>(edges);
                Collections.sort(edgeArray);
                for (String edge : edgeArray) {
                    ConfigTypeNode subTypeNode = configTypeNodeMap.get(edge);
                    if (TaCoKitConst.CONFIG_NODE_ID_DATASTORE.equals(subTypeNode.getConfigurationType())) {
                        createAction = new CreateTaCoKitConfigurationAction(subTypeNode);
                        break;
                    }
                }
            }
        }
        if (createAction != null) {
            try {
                RepositoryNode parent = ProjectRepositoryNode.getInstance()
                        .getRootRepositoryNode(ERepositoryObjectType.METADATA_CONNECTIONS, false);
                TaCoKitFamilyRepositoryNode fakeNode = new TaCoKitFamilyRepositoryNode(parent, configTypeNode.getDisplayName(),
                        configTypeNode);
                createAction.init(fakeNode);
                TaCoKitCreateWizard wizard = createAction.createWizard(PlatformUI.getWorkbench());
                WizardDialog wizardDialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
                wizardDialog.setPageSize(780, 540);
                wizardDialog.create();
                return wizardDialog.open();
            } catch (Exception e) {
                ExceptionHandler.process(e);
            }
        }
        return -1;
    }

}
