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
package org.talend.designer.core.ui.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.DeleteAction;
import org.eclipse.ui.IWorkbenchPart;
import org.talend.designer.core.ui.action.business.CrossPlatformProcessNodeDeleteAction;
import org.talend.designer.core.ui.editor.AbstractTalendEditor;
import org.talend.designer.core.ui.editor.nodes.NodePart;
import org.talend.designer.core.ui.editor.subjobcontainer.SubjobContainer;
import org.talend.designer.core.ui.editor.subjobcontainer.SubjobContainerPart;

/**
 * Modification of the delete action to enhance the speed of the action from GEF. <br/>
 *
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 *
 */
public class GEFDeleteAction extends DeleteAction implements ICrossPlatformDeleteActionHook {

    private CrossPlatformProcessNodeDeleteAction delegate;

    public GEFDeleteAction(IWorkbenchPart part) {
        super(part);
    }

    @Override
    public List getCrossPlatformSelectedObjects() {
        return getSelectedObjects();
    }

    @Override
    protected boolean calculateEnabled() {
        delegate = null;

        AbstractTalendEditor editor = (AbstractTalendEditor) this.getWorkbenchPart();
        delegate = new CrossPlatformProcessNodeDeleteAction(editor.getProcess(), this);
        return delegate.isEnabled(getSelectedObjects());
    }

    public static List filterSameObject(List list) {
        List newList = new ArrayList();
        for (Object object : list) {
            if (!newList.contains(object)) {
                newList.add(object);
            }
        }

        return newList;

    }

    @Override
    public Command createDeleteCommand(List objects) {
        if (delegate == null) {
            return null;
        }
        return delegate.createCommand(objects);
    }

    @Override
    protected List getSelectedObjects() {
        List<Object> subJobContainerParts = new ArrayList<>();

        // if selected nodes is from collapsed subjob, replaced it with subjob as selected
        List selectedObjects = new ArrayList(super.getSelectedObjects());
        Iterator it = selectedObjects.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            if (obj instanceof NodePart) {
                NodePart nodePart = (NodePart) obj;
                EditPart nodeParent = nodePart.getParent();
                if (nodeParent != null && nodeParent.getParent() instanceof SubjobContainerPart) {
                    SubjobContainerPart subJobContainerPart = (SubjobContainerPart) nodeParent.getParent();
                    SubjobContainer subjobContainer = (SubjobContainer) subJobContainerPart.getModel();
                    if (subjobContainer.isCollapsed()) {
                        it.remove();
                        if (!subJobContainerParts.contains(subJobContainerPart)) {
                            subJobContainerParts.add(subJobContainerPart);
                        }
                    }
                }
            }
        }
        selectedObjects.addAll(subJobContainerParts);

        return selectedObjects;
    }
}
