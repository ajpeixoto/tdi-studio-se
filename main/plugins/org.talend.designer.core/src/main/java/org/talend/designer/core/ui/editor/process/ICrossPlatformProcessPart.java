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
package org.talend.designer.core.ui.editor.process;

import java.util.List;

import org.talend.designer.core.ui.editor.subjobcontainer.ICrossPlatformEditPart;

public interface ICrossPlatformProcessPart extends ICrossPlatformEditPart {

    @Override
    default List getCrossPlatformModelChildren() {
        return ((Process) this.getCrossPlatformModel()).getElements();
    }

}
