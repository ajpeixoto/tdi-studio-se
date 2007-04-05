// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.designer.core.ui.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IWorkbenchPart;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataTable;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.INodeConnector;
import org.talend.designer.core.i18n.Messages;
import org.talend.designer.core.model.components.EParameterName;
import org.talend.designer.core.ui.editor.connections.Connection;
import org.talend.designer.core.ui.editor.connections.EDesignerConnection;
import org.talend.designer.core.ui.editor.nodes.Node;
import org.talend.designer.core.ui.editor.nodes.NodePart;
import org.talend.designer.core.ui.editor.process.Process;

/**
 * Action that manage to create a connection from the context menu. A connection type is used to know which kind of
 * connection will be created. <br/>
 * 
 * $Id$
 * 
 */
public class ConnectionCreateAction extends SelectionAction {

    public static final String ID = "org.talend.designer.core.ui.editor.action.ConnectionCreateAction"; //$NON-NLS-1$

    private EConnectionType connecType;

    private NodePart nodePart;

    private List<String> menuList;

    private List<Object> listArgs;

    private static final String NEW_OUTPUT = "*New Output*"; //$NON-NLS-1$

    /**
     * Define the type of the connection and the workbench part who will manage the connection.
     * 
     * @param part
     * @param connecType
     */
    public ConnectionCreateAction(IWorkbenchPart part, EConnectionType connecType) {
        super(part);
        this.connecType = connecType;
        // setId(ID+connecType.getName());
    }

    @Override
    protected boolean calculateEnabled() {
        return canPerformAction();
    }

    /**
     * Test if the selected item is a node.
     * 
     * @return true/false
     */
    @SuppressWarnings("unchecked")//$NON-NLS-1$
    private boolean canPerformAction() {
        if (getSelectedObjects().isEmpty()) {
            return false;
        }
        List parts = getSelectedObjects();
        if (parts.size() == 1) {
            Object o = parts.get(0);
            if (!(o instanceof NodePart)) {
                return false;
            }
            nodePart = (NodePart) o;
            if (!(nodePart.getModel() instanceof Node)) {
                return false;
            }
            Node node = (Node) nodePart.getModel();
            if (!node.isActivate()) {
                return false;
            }

            if (connecType.equals(EConnectionType.RUN_AFTER) || connecType.equals(EConnectionType.RUN_BEFORE)) {
                if (!(Boolean) node.getPropertyValue(EParameterName.STARTABLE.getName()) || (!node.isSubProcessStart())) {
                    return false;
                }
            }

            INodeConnector nodeConnector = node.getConnectorFromType(connecType);
            if (nodeConnector.getMaxLinkOutput() != -1) {
                if (nodeConnector.getCurLinkNbOutput() >= nodeConnector.getMaxLinkOutput()) {
                    return false;
                }
            }
            if (nodeConnector.getMaxLinkOutput() == 0) {
                return false;
            }
            if (!node.getConnectorFromType(connecType).isBuiltIn()) {
                setText(EDesignerConnection.getConnection(connecType).getMenuName());
            }
            menuList = new ArrayList<String>();
            if (node.getConnectorFromType(connecType).isBuiltIn()) {
                for (int i = 0; i < node.getMetadataList().size(); i++) {
                    IMetadataTable table = ((IMetadataTable) node.getMetadataList().get(i));
                    String name = table.getTableName();
                    if (connecType.equals(EConnectionType.TABLE)) {
                        name = table.getLabel() + " (" + name + ")";
                    }
                    boolean nameUsed = false;
                    for (Connection connec : (List<Connection>) node.getOutgoingConnections()) {
                        if (connec.getMetadataTable().getTableName().equals(table.getTableName())) {
                            nameUsed = true;
                        }
                    }
                    // if the name is not already in the process adds to the list
                    if (!nameUsed) {
                        menuList.add(name);
                    }
                }
                menuList.add(getNewOutputMenuName());
            } else {
                String menuName;
                if (node.getConnectorFromType(connecType).isCustomName()) {
                    menuName = getNewOutputMenuName();
                } else {
                    menuName = EDesignerConnection.getConnection(connecType).getMenuName();
                }
                setText(menuName);
                menuList.add(menuName);
            }

            return true;
        }
        return false;
    }

    private String getNewOutputMenuName() {
        return NEW_OUTPUT + " (" + EDesignerConnection.getConnection(connecType).getMenuName() + ")";
    }

    public List<String> getMenuList() {
        return menuList;
    }

    public void setMenuList(final List<String> menuList) {
        this.menuList = menuList;
    }

    private String askForConnectionName(String nodeLabel) {
        InputDialog id = new InputDialog(getWorkbenchPart().getSite().getShell(), nodeLabel
                + Messages.getString("ConnectionCreateAction.dialogTitle"), //$NON-NLS-1$
                Messages.getString("ConnectionCreateAction.dialogMessage"), "", null); //$NON-NLS-1$ //$NON-NLS-2$
        id.open();
        if (id.getReturnCode() == InputDialog.CANCEL) {
            return "";
        }
        return id.getValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {
        IMetadataTable meta = null;
        IMetadataTable newMetadata = null;
        String connectionName = null;

        if (getSelectedObjects().isEmpty()) {
            return;
        }
        List parts = getSelectedObjects();
        if (parts.size() == 1) {
            Object o = parts.get(0);
            if (!(o instanceof NodePart)) {
                return;
            }
            nodePart = (NodePart) o;
        } else {
            return;
        }

        Node node = (Node) nodePart.getModel();
        if (node.getConnectorFromType(connecType).isBuiltIn()) {
            if (getText().equals(getNewOutputMenuName())) {
                boolean nameOk = false;
                while (!nameOk) {

                    connectionName = askForConnectionName(node.getLabel());
                    if (connectionName.equals("")) {
                        return;
                    }
                    if (node.getConnectorFromType(connecType).isCustomName()
                            || node.getProcess().checkValidConnectionName(connectionName)) {
                        nameOk = true;
                    } else {
                        String message = Messages.getString(
                                "ConnectionCreateAction.errorCreateConnectionName", connectionName); //$NON-NLS-1$
                        MessageDialog.openError(getWorkbenchPart().getSite().getShell(), Messages
                                .getString("ConnectionCreateAction.error"), message); //$NON-NLS-1$
                    }
                }

                if (node.getConnectorFromType(connecType).isCustomName()) {
                    meta = new MetadataTable();
                    meta.setTableName(connectionName);
                    meta.setLabel(connectionName);
                    // meta.setTableId(node.getMetadataList().size());
                    newMetadata = meta;
                } else {
                    boolean metaExist = false;
                    for (int i = 0; i < node.getMetadataList().size(); i++) {
                        if (((IMetadataTable) node.getMetadataList().get(i)).getTableName().equals(connectionName)) {
                            metaExist = true;
                        }
                    }
                    if (!metaExist) {
                        meta = new MetadataTable();
                        meta.setTableName(connectionName);
                        newMetadata = meta;
                    }
                }
            } else {
                String tableName;
//                int tableId = -1;
                if (node.getConnectorFromType(connecType).isCustomName()) {
                    int end = getText().length() - 1;
                    int start = getText().lastIndexOf("(") + 1;
                    tableName = getText().substring(start, end);
//                    table = Integer.parseInt(stringId);
//                    tableName = getText().substring(0, start - 2);
                    meta = node.getMetadataTable(tableName);
//                    meta = (IMetadataTable) node.getMetadataList().get(tableId);
                    connectionName = meta.getLabel();
                } else {
                    tableName = getText();
//                    tableId = -1;
                    
                    meta = node.getMetadataTable(tableName);
//                    for (int i = 0; i < node.getMetadataList().size(); i++) {
//                        IMetadataTable table = (IMetadataTable) node.getMetadataList().get(i);
//                        if (table.getTableName().equals(tableName)) {
//                            meta = (IMetadataTable) node.getMetadataList().get(i);
//                        }
//                    }
                    connectionName = meta.getTableName();
                }
            }
        } else {
            if (node.getConnectorFromType(connecType).isCustomName()) {
                connectionName = askForConnectionName(node.getLabel());
            } else {
                if (connecType.equals(EConnectionType.FLOW_MAIN)) {
                    connectionName = node.getProcess().generateUniqueConnectionName(Process.DEFAULT_CONNECTION_NAME);
                } else {
                    connectionName = EDesignerConnection.getConnection(connecType).getLinkName();
                }
            }
            if (node.getMetadataList().size() == 0) {
                meta = null;
            } else {
                meta = (IMetadataTable) node.getMetadataList().get(0);
            }
        }

        /**
         * Create a mouse down event that thinks it is over the blob and dispatch it. This is a bit of a fudge to mimic
         * what the user ought to do.
         */
        Point point = null;
        point = nodePart.getFigure().getClientArea().getCenter();

        Point p = point;
        nodePart.getFigure().translateToAbsolute(p);

        Canvas canvas = (Canvas) nodePart.getViewer().getControl();
        Event event = new Event();
        event.button = 1;
        event.count = 0;
        event.detail = 0;
        event.end = 0;
        event.height = 0;
        event.keyCode = 0;
        event.start = 0;
        event.stateMask = 0;
        event.time = 9516624; // any old time... doesn't matter
        event.type = 3;
        event.widget = canvas;
        event.width = 0;
        event.x = p.x + 3;
        event.y = p.y + 3;
        /**
         * Set the connection tool to be the current tool
         */

        listArgs = new ArrayList<Object>();
        if (connecType.equals(EConnectionType.FLOW_MAIN) || connecType.equals(EConnectionType.FLOW_REF)
                || connecType.equals(EConnectionType.TABLE)) {
            listArgs.add(meta.getTableName());
        } else {
            listArgs.add(node.getUniqueName());
        }
        listArgs.add(connectionName);
        listArgs.add(newMetadata);
        MenuConnectionCreationTool myConnectTool = new MenuConnectionCreationTool(new CreationFactory() {

            public Object getNewObject() {
                return listArgs;
            }

            public Object getObjectType() {
                return connecType;
            }
        });
        myConnectTool.performConnectionStartWith(nodePart);
        nodePart.getViewer().getEditDomain().setActiveTool(myConnectTool);

        canvas.notifyListeners(3, event);
    }
}
