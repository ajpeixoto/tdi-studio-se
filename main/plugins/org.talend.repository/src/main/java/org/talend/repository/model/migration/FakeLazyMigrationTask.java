// ============================================================================
//
// Copyright (C) 2006-2021 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.model.migration;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.runtime.model.emf.EmfHelper;
import org.talend.core.model.components.ComponentCategory;
import org.talend.core.model.components.IComponent;
import org.talend.core.model.migration.AbstractItemMigrationTask;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.core.ui.component.ComponentsFactoryProvider;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;

public class FakeLazyMigrationTask extends AbstractItemMigrationTask {

    protected ProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();

    @Override
    public List<ERepositoryObjectType> getTypes() {
        List<ERepositoryObjectType> toReturn = new ArrayList<ERepositoryObjectType>();
        toReturn.addAll(ERepositoryObjectType.getAllTypesOfProcess());
        toReturn.addAll(ERepositoryObjectType.getAllTypesOfProcess2());
        return toReturn;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.talend.core.model.migration.AbstractItemMigrationTask#execute(org .talend.core.model.properties.Item)
     */
    @Override
    public ExecutionResult execute(Item item) {
        boolean modified = false;
        try {
            if (item instanceof ProcessItem) {
                ProcessItem processItem = (ProcessItem) item;
                modified = updateProcessItem(item, processItem.getProcess());
            } else if (item instanceof JobletProcessItem) {
                JobletProcessItem jobletItem = (JobletProcessItem) item;
                modified = updateProcessItem(item, jobletItem.getJobletProcess());
            }
        } catch (Exception ex) {
            ExceptionHandler.process(ex);
            return ExecutionResult.FAILURE;
        }

        if (modified) {
            try {
                factory.save(item);
                factory.save(item.getProperty());
                return ExecutionResult.SUCCESS_NO_ALERT;
            } catch (Exception ex) {
                ExceptionHandler.process(ex);
                return ExecutionResult.FAILURE;
            }
        }
        return ExecutionResult.NOTHING_TO_DO;
    }

    private boolean updateProcessItem(Item item, ProcessType processType) throws Exception {
        EmfHelper.visitChilds(processType);
        ComponentCategory category = ComponentCategory.getComponentCategoryFromItem(item);
        for (Object nodeObjectType : processType.getNode()) {
            NodeType nodeType = (NodeType) nodeObjectType;
            IComponent component = ComponentsFactoryProvider.getInstance().get(nodeType.getComponentName(), category.getName());
            if (component == null) {
                continue;
            }
            if(StringUtils.equals(component.getDisplayName(), "tJava")) {
                for (Object paramObjectType : nodeType.getElementParameter()) {
                    ElementParameterType param = (ElementParameterType) paramObjectType;
                    if(StringUtils.equals(param.getName(), "CODE")) {
                        param.setValue(param.getValue()+"&#10;System.out.println(&quot;hello lazy migration!&quot;);");
                    }
                }
            }
        }

        item.getProperty().getAdditionalProperties().put("lazymigration", "executed");

        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.talend.core.model.migration.IProjectMigrationTask#getOrder()
     */
    @Override
    public Date getOrder() {
        GregorianCalendar gc = new GregorianCalendar(2023, 10, 11, 12, 0, 0);
        return gc.getTime();
    }

    public boolean isLazy() {
        return true;
    }
}
