package org.talend.sdk.component.studio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Copyright (C) 2006-2021 Talend Inc. - www.talend.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

public class VirtualComponentTest {

    @Test
    public void testIsVirtualComponentAzureAdlsGen2() {
        assertTrue(Lookups.taCoKitCache().isVirtualComponentId("YXp1cmUtZGxzLWdlbjIjQXp1cmUjQWRsc0dlbjJJbnB1dAConnection"));
        assertTrue(Lookups.taCoKitCache().isVirtualComponentName("tAzureAdlsGen2Connection"));
    }

    @Test
    public void testIsVirtualComponentRabbitMQ() {
        // tRabbitMQConnection
        assertTrue(Lookups.taCoKitCache().isVirtualComponentId("cmFiYml0bXEjUmFiYml0TVEjSW5wdXQConnection"));
        // tRabbitMQClose
        assertTrue(Lookups.taCoKitCache().isVirtualComponentId("cmFiYml0bXEjUmFiYml0TVEjSW5wdXQClose"));

        // tRabbitMQConnection
        assertTrue(Lookups.taCoKitCache().isVirtualComponentName("tRabbitMQConnection"));
        // tRabbitMQClose
        assertTrue(Lookups.taCoKitCache().isVirtualComponentName("tRabbitMQClose"));
    }

    @Test
    public void testIsVirtualComponentNetSuiteV2019() {
        // tNetSuiteV2019Connection
        assertTrue(Lookups.taCoKitCache().isVirtualComponentId("bmV0c3VpdGUjTmV0U3VpdGUjVjIwMTlJbnB1dAConnection"));
        // tNetSuiteV2019Connection
        assertTrue(Lookups.taCoKitCache().isVirtualComponentName("tNetSuiteV2019Connection"));
    }

    @Test
    public void testIsVirtualComponentNeo4jv4() {
        // tNeo4jv4Connection
        assertTrue(Lookups.taCoKitCache().isVirtualComponentId("bmVvNGojTmVvNGp2NCNJbnB1dAConnection"));
        // tNeo4jv4Close
        assertTrue(Lookups.taCoKitCache().isVirtualComponentId("bmVvNGojTmVvNGp2NCNJbnB1dAClose"));

        // tNeo4jv4Connection
        assertTrue(Lookups.taCoKitCache().isVirtualComponentName("tNeo4jv4Connection"));
        // tNeo4jv4Close
        assertTrue(Lookups.taCoKitCache().isVirtualComponentName("tNeo4jv4Close"));
    }

    @Test
    public void testIsVirtualComponentJMS() {
        // tMessagingConnection
        assertTrue(Lookups.taCoKitCache().isVirtualComponentId("am1zLWNvbm5lY3RvciNNZXNzYWdpbmcjSW5wdXQConnection"));
        // tMessagingClose
        assertTrue(Lookups.taCoKitCache().isVirtualComponentId("am1zLWNvbm5lY3RvciNNZXNzYWdpbmcjSW5wdXQClose"));

        // tMessagingConnection
        assertTrue(Lookups.taCoKitCache().isVirtualComponentName("tMessagingConnection"));
        // tMessagingClose
        assertTrue(Lookups.taCoKitCache().isVirtualComponentName("tMessagingClose"));
    }

    @Test
    public void testIsVirtualComponentAmazonDocumentDB() {
        // tAmazonDocumentDBConnection
        assertTrue(Lookups.taCoKitCache().isVirtualComponentId("YXdzLWRvY3VtZW50ZGIjQW1hem9uRG9jdW1lbnREQiNJbnB1dAConnection"));
        // tAmazonDocumentDBClose
        assertTrue(Lookups.taCoKitCache().isVirtualComponentId("YXdzLWRvY3VtZW50ZGIjQW1hem9uRG9jdW1lbnREQiNJbnB1dAClose"));

        // tAmazonDocumentDBConnection
        assertTrue(Lookups.taCoKitCache().isVirtualComponentName("tAmazonDocumentDBConnection"));
        // tAmazonDocumentDBClose
        assertTrue(Lookups.taCoKitCache().isVirtualComponentName("tAmazonDocumentDBClose"));
    }

    @Test
    public void testIsVirtualComponentBigtable() {
        // tBigtableConnection
        assertTrue(Lookups.taCoKitCache().isVirtualComponentId("YmlndGFibGUjQmlndGFibGUjSW5wdXQConnection"));
        // tBigtableClose
        assertTrue(Lookups.taCoKitCache().isVirtualComponentId("YmlndGFibGUjQmlndGFibGUjSW5wdXQClose"));

        // tBigtableConnection
        assertTrue(Lookups.taCoKitCache().isVirtualComponentName("tBigtableConnection"));
        // tBigtableClose
        assertTrue(Lookups.taCoKitCache().isVirtualComponentName("tBigtableClose"));
    }

    @Test
    public void testIsVirtualComponentSamba() {
        // tSambaConnection
        assertTrue(Lookups.taCoKitCache().isVirtualComponentId("c2FtYmEjU2FtYmEjSW5wdXQConnection"));
        // tSambaClose
        assertTrue(Lookups.taCoKitCache().isVirtualComponentId("c2FtYmEjU2FtYmEjSW5wdXQClose"));

        // tSambaConnection
        assertTrue(Lookups.taCoKitCache().isVirtualComponentName("tSambaConnection"));
        // tSambaClose
        assertTrue(Lookups.taCoKitCache().isVirtualComponentName("tSambaClose"));
    }

    @Test
    public void testIsVirtualComponentBoxv2() {
        // tBoxv2Connection
        assertTrue(Lookups.taCoKitCache().isVirtualComponentId("Ym94LXN0dWRpbyNCb3h2MiNJbnB1dAConnection"));

        // tBoxv2Connection
        assertTrue(Lookups.taCoKitCache().isVirtualComponentName("tBoxv2Connection"));
    }

    @Test
    public void testIsVirtualComponentJDBC() {
        // JDBCConnection
        assertTrue(Lookups.taCoKitCache().isVirtualComponentId("bmV3amRiYyNKREJDI0lucHV0Connection"));
        // JDBCClose
        assertTrue(Lookups.taCoKitCache().isVirtualComponentId("bmV3amRiYyNKREJDI0lucHV0Close"));

        // JDBCConnection
        assertTrue(Lookups.taCoKitCache().isVirtualComponentName("JDBCConnection"));
        // JDBCClose
        assertTrue(Lookups.taCoKitCache().isVirtualComponentName("JDBCClose"));
    }

    @Test
    public void testIsVirtualComponentError() {
        assertFalse(Lookups.taCoKitCache().isVirtualComponentId("bmV3amRiYyNKREJDI0lucHV0"));
        assertFalse(Lookups.taCoKitCache().isVirtualComponentId("JDBCInput"));
    }
}
