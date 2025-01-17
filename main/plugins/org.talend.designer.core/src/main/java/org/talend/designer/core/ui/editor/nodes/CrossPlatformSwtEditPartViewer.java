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
package org.talend.designer.core.ui.editor.nodes;

import org.eclipse.gef.EditPartViewer;

public class CrossPlatformSwtEditPartViewer implements ICrossPlatformEditPartViewer {

    private EditPartViewer viewer;

    public CrossPlatformSwtEditPartViewer(EditPartViewer viewer) {
        this.viewer = viewer;
    }

    @Override
    public void deselectAll() {
        this.viewer.deselectAll();
    }

}
