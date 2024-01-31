// ============================================================================
//
// Copyright (C) 2006-2024 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.sdk.component.studio.metadata.migration;

import java.util.Date;
import java.util.GregorianCalendar;

import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.runtime.service.ITaCoKitService;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.migration.AbstractItemMigrationTask;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.Item;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.sdk.component.studio.Lookups;
import org.talend.sdk.component.studio.metadata.model.TaCoKitConfigurationModel;

public class UpgradeTacokitMetadataMigrationTask extends AbstractItemMigrationTask {

    private ProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();

    @Override
    public Date getOrder() {
        GregorianCalendar gc = new GregorianCalendar(2024, 01, 23, 12, 0, 0);
        return gc.getTime();
    }

    @Override
    public ExecutionResult execute(Item item) {
        boolean modified = false;
        if (item instanceof ConnectionItem) {
            try {
                ConnectionItem conItem = (ConnectionItem) item;
                Connection connection = conItem.getConnection();
                if (!TaCoKitConfigurationModel.isTacokit(connection)) {
                    return ExecutionResult.NOTHING_TO_DO;
                }
                try {
                    ITaCoKitService.getInstance().waitForStart();
                } catch (Throwable t) {
                    // don't block if fail
                    ExceptionHandler.process(t);
                }
                TaCoKitMigrationManager taCoKitMigrationManager = Lookups.taCoKitCache().getMigrationManager();
                TaCoKitConfigurationModel configModel = new TaCoKitConfigurationModel(conItem.getConnection());
                if (configModel.needsMigration()) {
                    taCoKitMigrationManager.migrate(configModel, null);
                    factory.save(item, true);
                    modified = true;
                }
            } catch (IllegalArgumentException e) {
                ExceptionHandler.process(e);
            } catch (Exception e) {
                ExceptionHandler.process(e);
                return ExecutionResult.FAILURE;
            }
        }
        if (modified) {
            return ExecutionResult.SUCCESS_NO_ALERT;
        } else {
            return ExecutionResult.NOTHING_TO_DO;
        }
    }
}
