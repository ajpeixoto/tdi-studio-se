// ============================================================================
//
// Copyright (C) 2006-2019 Talend Inc. - www.talend.com
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

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.talend.core.CorePlugin;
import org.talend.core.model.migration.AbstractItemMigrationTask;
import org.talend.core.model.process.ProcessUtils;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.runprocess.ItemCacheManager;

/**
 * DOC zwxue class global comment. Detailled comment
 */
public class GenerateJobPomMigrationTask extends AbstractItemMigrationTask {

    @Override
    public Date getOrder() {
        GregorianCalendar gc = new GregorianCalendar(2018, 1, 23, 23, 00, 00);
        return gc.getTime();
    }

    @Override
    public ExecutionResult execute(Item item) {
        // only execute the migration task during logon, disable it for the import item (check of log finished)
        if (!ProxyRepositoryFactory.getInstance().isFullLogonFinished()) {
        	
        	// skip poms generation for routelets
            if (item != null && item.getProperty() != null && ProcessUtils.isRoutelet(item.getProperty())) {
            	return ExecutionResult.SKIPPED;
            }
            
            CorePlugin.getDefault().getRunProcessService().generatePom(item);
            
            if (item != null && ProcessUtils.isRoute(item.getProperty()) && ProcessUtils.isRouteWithRoutelets(item)) {
            	generatePomsForChildRoutelets(item);
            }
          
            return ExecutionResult.SUCCESS_NO_ALERT;
        }
        return ExecutionResult.NOTHING_TO_DO;
    }
    
    @Override
    public List<ERepositoryObjectType> getTypes() {
        return ERepositoryObjectType.getAllTypesOfProcess2();
    }
    
    private void generatePomsForChildRoutelets(Item routeItem) {
        // generate poms for child routelets with references to parent routes
        if (routeItem!= null && routeItem.getProperty() != null && ProcessUtils.isRoute(routeItem.getProperty())) {
            if (routeItem!= null && routeItem instanceof ProcessItem) {
                for (Object obj : ((ProcessItem) routeItem).getProcess().getNode()) {
                    if (obj instanceof NodeType) {
                    	NodeType node = (NodeType) obj;
                        if (ProcessUtils.isRouteletNode(node)) {
                        	
                            String jobIds = ProcessUtils.getParameterValue(node.getElementParameter(), "PROCESS_TYPE:PROCESS_TYPE_PROCESS");
                            String jobVersion = ProcessUtils.getParameterValue(node.getElementParameter(), "PROCESS_TYPE:PROCESS_TYPE_VERSION"); //$NON-NLS-1$
                            ProcessItem routeletItem = ItemCacheManager.getProcessItem(jobIds, jobVersion);
                            
                            routeletItem.setParent(routeItem);
                            CorePlugin.getDefault().getRunProcessService().generatePom(routeletItem);
                        }
                    }
                }
            }
        }
    }

}
