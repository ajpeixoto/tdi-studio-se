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
package org.talend.designer.core.ui.editor.process;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.talend.core.model.components.ComponentCategory;
import org.talend.core.model.components.IComponent;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IElement;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.IElementParameterDefaultValue;
import org.talend.core.model.process.INode;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.FakePropertyImpl;
import org.talend.core.ui.component.ComponentsFactoryProvider;
import org.talend.designer.core.model.FakeElement;
import org.talend.designer.core.model.components.ElementParameter;
import org.talend.designer.core.model.components.ElementParameterDefaultValue;
import org.talend.designer.core.ui.editor.connections.Connection;
import org.talend.designer.core.ui.editor.nodecontainer.NodeContainer;
import org.talend.designer.core.ui.editor.nodes.Node;


/**
 * created by nrousseau on Aug 29, 2016
 * Detailled comment
 *
 */
public class ProcessTest {

    /**
     * Test method for {@link org.talend.designer.core.ui.editor.process.Process#checkProcess()}.
     */
    @Test
    public void testCheckProcess() {

        Process p = new Process(new FakePropertyImpl()) {

            /* (non-Javadoc)
             * @see org.talend.designer.core.ui.editor.process.Process#checkProblems()
             */
            @Override
            protected void checkProblems() {
                assertThat(isDuplicate(), is(false));
                assertThat(isActivate(), is(true));
            }

        };
        p.setActivate(false);
        p.setDuplicate(false);
        p.checkProcess();
        p.setActivate(false);
        p.setDuplicate(true);
        p.checkProcess();
        p.setActivate(true);
        p.setDuplicate(false);
        p.checkProcess();
        p.setActivate(true);
        p.setDuplicate(true);
        p.checkProcess();
    }

    /**
     * Test method for {@link org.talend.designer.core.ui.editor.process.Process#checkProcess()}.
     */
    @Test
    public void testNoNeedSetValue() {
        Process p = new Process(new FakePropertyImpl());

        List<IElementParameterDefaultValue> defaultValues = null;

        IElement elem = new FakeElement("test");
        IElementParameter param = new ElementParameter(elem);

        IElementParameterDefaultValue defaultValue = new ElementParameterDefaultValue();
        defaultValues = new ArrayList<IElementParameterDefaultValue>();
        defaultValues.add(defaultValue);
        param.setDefaultValues(defaultValues);
        assertFalse(p.noNeedSetValue(param, "aa"));

        assertTrue(p.noNeedSetValue(param, null));

        defaultValue = new ElementParameterDefaultValue();
        defaultValue.setDefaultValue("aa");
        defaultValues = new ArrayList<IElementParameterDefaultValue>();
        defaultValues.add(defaultValue);
        param.setDefaultValues(defaultValues);
        assertTrue(p.noNeedSetValue(param, "aa"));

        defaultValue = new ElementParameterDefaultValue();
        defaultValues = new ArrayList<IElementParameterDefaultValue>();
        defaultValue.setDefaultValue("bb");
        defaultValues.add(defaultValue);
        param.setDefaultValues(defaultValues);
        assertFalse(p.noNeedSetValue(param, "aa"));
    }
    
    @Test
    public void testGetNodesOfType() {
        //version supported by tAmazonMysqlConnection: mysql5
        IComponent mysqlConnComp = ComponentsFactoryProvider.getInstance().get("tMysqlConnection",
                ComponentCategory.CATEGORY_4_DI.getName());
        IComponent amazonConnComp = ComponentsFactoryProvider.getInstance().get("tAmazonMysqlConnection",
                ComponentCategory.CATEGORY_4_DI.getName());
        IComponent mysqlInputComp = ComponentsFactoryProvider.getInstance().get("tMysqlInput",
                ComponentCategory.CATEGORY_4_DI.getName());
        
        //vesion supported by tAmazonOracleConnection: 18, 12, 11
        IComponent oracleConnComp = ComponentsFactoryProvider.getInstance().get("tOracleConnection",
                ComponentCategory.CATEGORY_4_DI.getName());
        IComponent oracle9ConnComp = ComponentsFactoryProvider.getInstance().get("tOracleConnection",
                ComponentCategory.CATEGORY_4_DI.getName());
        IComponent oracle12ConnComp = ComponentsFactoryProvider.getInstance().get("tOracleConnection",
                ComponentCategory.CATEGORY_4_DI.getName());
        IComponent amazonOracleConnComp = ComponentsFactoryProvider.getInstance().get("tAmazonOracleConnection",
                ComponentCategory.CATEGORY_4_DI.getName());
        IComponent oracleInputComp = ComponentsFactoryProvider.getInstance().get("tOracleInput",
                ComponentCategory.CATEGORY_4_DI.getName());
        
        Property property1 = PropertiesFactory.eINSTANCE.createProperty();
        property1.setId("property1"); 
        property1.setVersion("0.1"); 
        property1.setLabel("test1");
        Process process = new Process(property1);
        
        Node mysqlConnNode = new Node(mysqlConnComp,process);
        Node amazonMysqlConnNode = new Node(amazonConnComp,process);
        Node mysqlInputNode = new Node(mysqlInputComp,process);
        process.addNodeContainer(new NodeContainer(amazonMysqlConnNode));
        process.addNodeContainer(new NodeContainer(mysqlConnNode));
        process.addNodeContainer(new NodeContainer(mysqlInputNode));
        
        Node oracleConnNode = new Node(oracleConnComp,process);
        Node oracle9ConnNode = new Node(oracle9ConnComp,process);
        Node oracle12ConnNode = new Node(oracle12ConnComp,process);
        Node amazonOracleConnNode = new Node(amazonOracleConnComp,process);
        Node oracleInputNode = new Node(oracleInputComp,process);
        process.addNodeContainer(new NodeContainer(oracleConnNode));
        process.addNodeContainer(new NodeContainer(oracle9ConnNode));
        process.addNodeContainer(new NodeContainer(oracle12ConnNode));
        process.addNodeContainer(new NodeContainer(amazonOracleConnNode));
        process.addNodeContainer(new NodeContainer(oracleInputNode));
        
        oracle9ConnNode.getElementParameter("DB_VERSION").setValue("ORACLE_9");
        oracle12ConnNode.getElementParameter("DB_VERSION").setValue("ORACLE_12");
        
        List<INode> s = process.getNodesOfType("tMysqlConnection");
        assertEquals(s.size(),1); 
        s = process.getNodesOfType("tAmazonMysqlConnection");
        assertEquals(s.size(),1); //amazon one
        
        s = process.getNodesOfType("tOracleConnection");
        assertEquals(s.size(),3); 
        s = process.getNodesOfType("tAmazonOracleConnection");
        assertEquals(s.size(),1); //amazon one
        
    }

    @Test
    public void testIsParamDistribution() {
        Process p = new Process(new FakePropertyImpl());
        IElement elem = new FakeElement("test");
        IElementParameter param = new ElementParameter(elem);
        param.setName("DISTRIBUTION");
        param.setValue("Cloudera");
        assertTrue(p.isParamDistribution(param));
        param = new ElementParameter(elem);
        param.setName("DB_VERSION");
        param.setValue("Mysql8");
        assertFalse(p.isParamDistribution(param));
    }

    @Test
    public void testSortNodes() throws Exception {
        IComponent tRowGeneratorComp = ComponentsFactoryProvider.getInstance().get("tRowGenerator",
                ComponentCategory.CATEGORY_4_DI.getName());
        IComponent tHashInputComp = ComponentsFactoryProvider.getInstance().get("tHashInput",
                ComponentCategory.CATEGORY_4_DI.getName());
        IComponent tHashOutputComp = ComponentsFactoryProvider.getInstance().get("tHashOutput",
                ComponentCategory.CATEGORY_4_DI.getName());
        IComponent tMapComp = ComponentsFactoryProvider.getInstance().get("tMap", ComponentCategory.CATEGORY_4_DI.getName());
        IComponent tLogRowComp = ComponentsFactoryProvider.getInstance().get("tLogRow",
                ComponentCategory.CATEGORY_4_DI.getName());

        Process process = new Process(new FakePropertyImpl());
        Node tRowGenerator1 = new Node(tRowGeneratorComp, process);
        Node tHashOutput1 = new Node(tHashOutputComp, process);
        Connection row1 = new Connection(tRowGenerator1, tHashOutput1, EConnectionType.FLOW_MAIN,
                EConnectionType.FLOW_MAIN.getName(), "tRowGenerator1", "row1", false);
        ((List<IConnection>) tRowGenerator1.getOutgoingConnections()).add(row1);
        ((List<IConnection>) tHashOutput1.getIncomingConnections()).add(row1);
        addNodeToProcess(process, tRowGenerator1);
        addNodeToProcess(process, tHashOutput1);

        Node tRowGenerator2 = new Node(tRowGeneratorComp, process);
        Node tMap1 = new Node(tMapComp, process);
        Connection row4 = new Connection(tRowGenerator2, tMap1, EConnectionType.FLOW_MAIN, EConnectionType.FLOW_MAIN.getName(),
                "tRowGenerator2", "row4", false);
        ((List<IConnection>) tRowGenerator2.getOutgoingConnections()).add(row4);
        ((List<IConnection>) tMap1.getIncomingConnections()).add(row4);
        Node tMap2 = new Node(tMapComp, process);
        Connection out1 = new Connection(tMap1, tMap2, EConnectionType.FLOW_MAIN, EConnectionType.FLOW_MAIN.getName(), "out1",
                "out1", false);
        ((List<IConnection>) tMap1.getOutgoingConnections()).add(out1);
        ((List<IConnection>) tMap2.getIncomingConnections()).add(out1);
        Node tLogRow = new Node(tLogRowComp, process);
        Connection out2 = new Connection(tMap2, tLogRow, EConnectionType.FLOW_MAIN, EConnectionType.FLOW_MAIN.getName(), "out2",
                "out2", false);
        addNodeToProcess(process, tRowGenerator2);
        addNodeToProcess(process, tMap1);
        addNodeToProcess(process, tMap2);
        addNodeToProcess(process, tLogRow);

        Node tRowGenerator3 = new Node(tRowGeneratorComp, process);
        Node tHashOutput2 = new Node(tHashOutputComp, process);
        Connection row2 = new Connection(tRowGenerator3, tHashOutput2, EConnectionType.FLOW_MAIN,
                EConnectionType.FLOW_MAIN.getName(), "tRowGenerator3", "row2", false);
        ((List<IConnection>) tRowGenerator3.getOutgoingConnections()).add(row2);
        ((List<IConnection>) tHashOutput2.getIncomingConnections()).add(row2);
        addNodeToProcess(process, tRowGenerator3);
        addNodeToProcess(process, tHashOutput2);

        Node tRowGenerator4 = new Node(tRowGeneratorComp, process);
        Node tHashOutput3 = new Node(tHashOutputComp, process);
        Connection row3 = new Connection(tRowGenerator4, tHashOutput3, EConnectionType.FLOW_MAIN,
                EConnectionType.FLOW_MAIN.getName(), "tRowGenerator4", "row3", false);
        ((List<IConnection>) tRowGenerator4.getOutgoingConnections()).add(row3);
        ((List<IConnection>) tHashOutput3.getIncomingConnections()).add(row3);
        addNodeToProcess(process, tRowGenerator4);
        addNodeToProcess(process, tHashOutput3);

        Node tHashInput1 = new Node(tHashInputComp, process);
        Node tHashInput2 = new Node(tHashInputComp, process);
        Node tHashInput3 = new Node(tHashInputComp, process);
        Node tHashInput4 = new Node(tHashInputComp, process);
        Node tMap3 = new Node(tMapComp, process);
        Node tMap4 = new Node(tMapComp, process);
        Connection row5 = new Connection(tHashInput4, tMap4, EConnectionType.FLOW_MAIN, EConnectionType.FLOW_MAIN.getName(),
                "tHashInput4", "row5", false);
        ((List<IConnection>) tHashInput4.getOutgoingConnections()).add(row5);
        ((List<IConnection>) tMap4.getIncomingConnections()).add(row5);
        Connection row6 = new Connection(tHashInput2, tMap4, EConnectionType.FLOW_REF, EConnectionType.FLOW_REF.getName(),
                "tHashInput2", "row6", false);
        ((List<IConnection>) tHashInput2.getOutgoingConnections()).add(row6);
        ((List<IConnection>) tMap4.getIncomingConnections()).add(row6);
        Connection out3 = new Connection(tMap4, tMap2, EConnectionType.FLOW_REF, EConnectionType.FLOW_REF.getName(), "out3",
                "out3", false);
        ((List<IConnection>) tMap4.getOutgoingConnections()).add(out3);
        ((List<IConnection>) tMap2.getIncomingConnections()).add(out3);
        addNodeToProcess(process, tHashInput4);
        addNodeToProcess(process, tMap4);
        addNodeToProcess(process, tHashInput2);


        Connection row7 = new Connection(tHashInput3, tMap3, EConnectionType.FLOW_MAIN, EConnectionType.FLOW_MAIN.getName(),
                "tHashInput3", "row7", false);
        ((List<IConnection>) tHashInput3.getOutgoingConnections()).add(row7);
        ((List<IConnection>) tMap3.getIncomingConnections()).add(row7);
        Connection row8 = new Connection(tHashInput1, tMap3, EConnectionType.FLOW_REF, EConnectionType.FLOW_REF.getName(),
                "tHashInput1", "row8", false);
        ((List<IConnection>) tHashInput1.getOutgoingConnections()).add(row8);
        ((List<IConnection>) tMap3.getIncomingConnections()).add(row8);
        Connection out4 = new Connection(tMap3, tMap1, EConnectionType.FLOW_REF, EConnectionType.FLOW_REF.getName(), "out4",
                "out4", false);
        ((List<IConnection>) tMap3.getOutgoingConnections()).add(out4);
        ((List<IConnection>) tMap1.getIncomingConnections()).add(out4);
        addNodeToProcess(process, tHashInput3);
        addNodeToProcess(process, tMap3);
        addNodeToProcess(process, tHashInput1);

        process.checkStartNodes();
        Class<? extends Process> pClass = process.getClass();
        Method method = pClass.getDeclaredMethod("sortNodes", List.class);
        method.setAccessible(true);
        List<INode> returnList = (List<INode>) method.invoke(process, process.getGraphicalNodes());
        assertTrue(returnList.indexOf(tHashInput3) < returnList.indexOf(tHashInput4));

    }

    private void addNodeToProcess(Process process, Node node) {
        NodeContainer nodeContainer = new NodeContainer(node);
        process.addNodeContainer(nodeContainer);
    }
}
