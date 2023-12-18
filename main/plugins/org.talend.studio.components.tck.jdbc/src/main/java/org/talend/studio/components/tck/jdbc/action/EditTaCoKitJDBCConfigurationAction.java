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

import org.talend.core.model.properties.TacokitDatabaseConnectionItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.model.IRepositoryNode;
import org.talend.sdk.component.studio.metadata.action.EditTaCoKitConfigurationAction;

/**
 * Metadata contextual action which creates WizardDialog used to edit Component configuration.
 * Repository node may have only 1 edit action. This action is registered as extension point.
 * Thus, it supports double click out of the box
 */
public class EditTaCoKitJDBCConfigurationAction extends EditTaCoKitConfigurationAction {
    
    @Override
    protected boolean isSupportNodeType (final IRepositoryNode node) {
        if (node.getObjectType().equals(ERepositoryObjectType.METADATA_TACOKIT_JDBC)) {
            return true;
        }
        return false;
    }

    @Override
    public Class getClassForDoubleClick() {
        return TacokitDatabaseConnectionItem.class;
    }
}
