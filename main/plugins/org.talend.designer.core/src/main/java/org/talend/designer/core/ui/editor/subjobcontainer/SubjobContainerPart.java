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
package org.talend.designer.core.ui.editor.subjobcontainer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.ui.gmf.util.DisplayUtils;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.CorePlugin;
import org.talend.core.model.general.ILibrariesService;
import org.talend.core.model.process.IProcess2;
import org.talend.core.model.process.Problem;
import org.talend.core.model.process.Problem.ProblemStatus;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.designer.core.ui.dialog.mergeorder.ErrorMessageDialog;
import org.talend.designer.core.ui.editor.AbstractSwtGraphicalEditPart;
import org.talend.designer.core.ui.editor.nodecontainer.NodeContainer;
import org.talend.designer.core.ui.editor.nodes.Node;
import org.talend.designer.core.ui.editor.process.NodeSnapToGeometry;
import org.talend.designer.core.ui.editor.process.Process;
import org.talend.designer.core.ui.editor.subjobcontainer.sparkstreaming.SparkStreamingSubjobContainerFigure;
import org.talend.designer.core.ui.views.properties.ComponentSettingsView;
import org.talend.librariesmanager.ui.views.ModulesView;

/**
 * DOC nrousseau class global comment. Detailled comment
 */
public class SubjobContainerPart extends AbstractSwtGraphicalEditPart
        implements ICrossPlatformSubjobContainerPart, PropertyChangeListener, IAdaptable, NodeEditPart {

    public SubjobContainerPart() {
        super();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.gef.editparts.AbstractEditPart#setSelected(int)
     */
    @Override
    public void setSelected(int value) {
        super.setSelected(value);
        if (value == SELECTED_PRIMARY) {
            ComponentSettingsView viewer = (ComponentSettingsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                    .getActivePage().findView(ComponentSettingsView.ID);

            if (viewer != null) {
                viewer.setElement((SubjobContainer) getModel());
            }

        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#activate()
     */
    @Override
    public void activate() {
        super.activate();
        ((SubjobContainer) getModel()).addPropertyChangeListener(this);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#deactivate()
     */
    @Override
    public void deactivate() {
        super.deactivate();
        ((SubjobContainer) getModel()).removePropertyChangeListener(this);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    public void refreshVisuals() {
        Boolean isDisplaySubjobs = ((SubjobContainer) this.getModel()).isDisplayed();
        if (getParent() == null || !isDisplaySubjobs) {
            return;
        }
        Rectangle rectangle = ((SubjobContainer) this.getModel()).getSubjobContainerRectangle();
        if (rectangle == null) {
            return;
        }
        ((SubjobContainerFigure) getFigure()).initializeSubjobContainer(rectangle);
        // added for bug 4005
        if (getFigure().getParent() != null) {
            ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        SubjobContainer container = (SubjobContainer) this.getModel();
        Boolean isDisplaySubjobs = (container).isDisplayed();

        if (!isDisplaySubjobs) {
            Figure figure = new FreeformLayer();
            figure.setLayoutManager(new FreeformLayout());
            return figure;
        }

        IProcess2 process = container.getProcess();
        Object obj = process.getAdditionalProperties().get("FRAMEWORK");//$NON-NLS-1$
        SubjobContainerFigure subjobContainer = null;
        if ("Spark Streaming".equals(obj) && !(process.getProperty().getItem() instanceof JobletProcessItem)) { //$NON-NLS-1$
            subjobContainer = new SparkStreamingSubjobContainerFigure(container);
        } else {
            subjobContainer = new SubjobContainerFigure(container);
        }
        return subjobContainer;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new SubjobContainerLayoutEditPolicy());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String prop = evt.getPropertyName();
        if (SubjobContainer.UPDATE_SUBJOB_CONTENT.equals(prop)) {
            refresh();
            List<AbstractGraphicalEditPart> childrens = (List<AbstractGraphicalEditPart>)getChildren();
            for (AbstractGraphicalEditPart part : childrens) {
                part.refresh();
            }
        } else if (SubjobContainer.UPDATE_SUBJOB_CONNECTIONS.equals(prop)) {
            refreshSourceConnections();
        } else if (SubjobContainer.UPDATE_SUBJOB_TITLE_COLOR.equals(prop)) {
            if (getFigure() instanceof SubjobContainerFigure) {
                ((SubjobContainerFigure) getFigure()).updateSubJobTitleColor();
                refreshVisuals();
            }
        } else if (SubjobContainer.UPDATE_SUBJOB_DISPLAY.equals(prop)) {
            List<NodeContainer> tmpList = new ArrayList<NodeContainer>(((SubjobContainer) getModel()).getNodeContainers());
            ((SubjobContainer) getModel()).getNodeContainers().clear();
            refreshChildren();
            List elems = ((Process) getParent().getModel()).getElements();
            elems.remove(getModel());
            EditPart parent = getParent();
            parent.refresh();
            ((SubjobContainer) getModel()).getNodeContainers().addAll(tmpList);
            elems.add(getModel());
            parent.refresh();
        } else if ("UPDATE_SPARKSTREAMING_STATUS".equals(prop)) {
            if (getFigure() instanceof SparkStreamingSubjobContainerFigure) {
                ((SparkStreamingSubjobContainerFigure) getFigure()).updateData();
                refreshVisuals();
            }
        } else { // can only be UPDATE_SUBJOB_DATA, need to modify if some others are added
            if (getFigure() instanceof SubjobContainerFigure) {
                ((SubjobContainerFigure) getFigure()).updateData();
                refreshVisuals();
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
     */
    @Override
    protected List getModelChildren() {
        return getCrossPlatformModelChildren();
    }

    @Override
    public List getCrossPlatformModelChildren() {
        return ICrossPlatformSubjobContainerPart.super.getCrossPlatformModelChildren();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getAdapter(java.lang.Class)
     */
    @Override
    public Object getAdapter(Class key) {
        if (key == SnapToHelper.class) {
            List<Object> snapStrategies = new ArrayList<Object>();
            Boolean val = (Boolean) getViewer().getProperty(RulerProvider.PROPERTY_RULER_VISIBILITY);

            val = (Boolean) getViewer().getProperty(NodeSnapToGeometry.PROPERTY_SNAP_ENABLED);
            if (val != null && val.booleanValue()) {
                snapStrategies.add(new NodeSnapToGeometry(this));
            }

            val = (Boolean) getViewer().getProperty(SnapToGrid.PROPERTY_GRID_ENABLED);
            if (val != null && val.booleanValue()) {
                snapStrategies.add(new SnapToGrid(this));
            }

            if (snapStrategies.size() == 0) {
                return null;
            }
            if (snapStrategies.size() == 1) {
                return snapStrategies.get(0);
            }

            SnapToHelper[] ss = new SnapToHelper[snapStrategies.size()];
            for (int i = 0; i < snapStrategies.size(); i++) {
                ss[i] = (SnapToHelper) snapStrategies.get(i);
            }
            return new CompoundSnapToHelper(ss);
        }
        return super.getAdapter(key);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#addChildVisual(org.eclipse.gef.EditPart, int)
     */
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        int nbChildrensInFigure = 2;
        Boolean isDisplaySubjobs = ((SubjobContainer) this.getModel()).isDisplayed();
        if (!isDisplaySubjobs) {
            nbChildrensInFigure = 0;
        }
        super.addChildVisual(childEditPart, index + nbChildrensInFigure);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        return new ChopboxAnchor(getFigure());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.Request)
     */
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        return new ChopboxAnchor(getFigure());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
        return new ChopboxAnchor(getFigure());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
     */
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return new ChopboxAnchor(getFigure());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelSourceConnections()
     */
    @Override
    protected List getModelSourceConnections() {
        return getCrossPlatformModelSourceConnections();
    }

    @Override
    public List getCrossPlatformModelSourceConnections() {
        return ICrossPlatformSubjobContainerPart.super.getCrossPlatformModelSourceConnections();
    }

    @Override
    public void performRequest(Request req) {
        if (req instanceof SelectionRequest) {
            Point location = ((SelectionRequest) req).getLocation();
            List<NodeContainer> list = this.getModelChildren();
            for (NodeContainer nodeCon : list) {
                if (nodeCon.getErrorMarkRectangle() != null && nodeCon.getErrorMarkRectangle().contains(location)) {
                    Node node = nodeCon.getNode();
                    if (node.isErrorFlag()) {
                        ErrorMessageDialog dialog = new ErrorMessageDialog(DisplayUtils.getDefaultShell(false), node);
                        dialog.open();
                        break;
                    }
                } else if (nodeCon.getErrorRectangle() != null && nodeCon.getErrorRectangle().contains(location)) {
                    Node node = nodeCon.getNode();
                    showModules(node);
                }
            }
        }
        super.performRequest(req);
    }

    private void showModules(Node node) {
        ILibrariesService moduleService = CorePlugin.getDefault().getLibrariesService();
        if (moduleService == null) {
            return;
        }
        List<Problem> problems = moduleService.getProblems(node, node);
        List<Problem> mproblems = new ArrayList<Problem>();
        List<String> modulesName = new ArrayList<String>();
        String componentName = null;
        String moduleStr = "Module_";
        for (Problem pro : problems) {
            if (pro.getStatus() == ProblemStatus.ERROR && pro.getKey() != null && pro.getKey().startsWith(moduleStr)) {
                mproblems.add(pro);
            }
        }
        if (mproblems.isEmpty()) {
            return;
        }
        for (Problem pro : mproblems) {
            if (componentName == null) {
                componentName = pro.getComponentName();
            }
            String key = pro.getKey();
            if (key.contains(moduleStr)) {
                int first = key.indexOf(moduleStr);
                String keydes = key.substring(first + moduleStr.length());
                modulesName.add(keydes);
            }
        }

        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        final IWorkbenchPage page = window.getActivePage();
        if (page == null) {
            return;
        }
        try {
            IViewPart view = page.showView("org.talend.designer.codegen.perlmodule.ModulesView");
            if (view instanceof ModulesView) {
                ((ModulesView) view).selectUninstalledItem(componentName, modulesName);
            }
        } catch (PartInitException e) {
            ExceptionHandler.process(e);
        }
    }

    @Override
    public void crossPlatformActivate() {
        activate();
    }

    @Override
    public void crossPlatformDeactivate() {
        deactivate();
    }

    @Override
    public void refreshCrossPlatformVisuals() {
        refreshVisuals();
    }

    @Override
    public void crossPlatformRefresh() {
        refresh();
    }

}
