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
package org.talend.sdk.component.studio.model.parameter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * Unit-tests for {@link ValueConverter}
 */
public class ValueConverterTest {

    @Test
    public void testToTable() {
        Map<String, Object> expected0 = new HashMap<>();
        expected0.put("key1", "value11");
        expected0.put("key2", "value12");
        Map<String, Object> expected1 = new HashMap<>();
        expected1.put("key1", "value21");
        expected1.put("key2", "value22");

        String table = "[{key1=value11, key2=value12}, {key1=value21, key2=value22}]";
        List<Map<String, Object>> converted = ValueConverter.toTable(table);
        assertEquals(2, converted.size());
        assertEquals(expected0, converted.get(0));
        assertEquals(expected1, converted.get(1));
    }

    @Test
    public void testToTableNull() {
        ArrayList<Map<String, Object>> empty = new ArrayList<>();
        List<Map<String, Object>> actual = ValueConverter.toTable(null);
        assertEquals(empty, actual);
    }

    @Test
    public void testToTableEmpty() {
        ArrayList<Map<String, Object>> empty = new ArrayList<>();
        List<Map<String, Object>> actual = ValueConverter.toTable("");
        assertEquals(empty, actual);
    }

    @Test
    public void testGetMainTableParameterName() {
        String paramName = "configuration.headers[].key";
        assertEquals("configuration.headers", ValueConverter.getMainTableParameterName(paramName));

        paramName = "configuration.headers[0].key";
        assertEquals("configuration.headers", ValueConverter.getMainTableParameterName(paramName));

        paramName = "configuration.headers[0]";
        assertEquals("configuration.headers", ValueConverter.getMainTableParameterName(paramName));
    }

    @Test
    public void testGetTableParameterIndex() {
        String paramName = "configuration.headers[0].key";
        assertEquals(0, ValueConverter.getTableParameterIndex(paramName));

        paramName = "configuration.headers[1].key";
        assertEquals(1, ValueConverter.getTableParameterIndex(paramName));

        paramName = "configuration.headers";
        assertEquals(-1, ValueConverter.getTableParameterIndex(paramName));
    }

    @Test
    public void testGetTableParameterNameNoIndex() {
        String paramName = "configuration.headers[0].key";
        assertEquals("configuration.headers[].key", ValueConverter.getTableParameterNameNoIndex(paramName));

        paramName = "configuration.headers[1].key";
        assertEquals("configuration.headers[].key", ValueConverter.getTableParameterNameNoIndex(paramName));

        paramName = "configuration.headers[0].value";
        assertEquals("configuration.headers[].value", ValueConverter.getTableParameterNameNoIndex(paramName));
    }
    
    @Test
    public void testGetTableParameterNameWithIndex() {
        String paramName = "configuration.headers[].key";
        assertEquals("configuration.headers[0].key", ValueConverter.getTableParameterNameWithIndex(0, paramName));

        assertEquals("configuration.headers[1].key", ValueConverter.getTableParameterNameWithIndex(1, paramName));

        paramName = "configuration.headers";
        assertNull(ValueConverter.getTableParameterNameWithIndex(1, paramName));
    }

    @Test
    public void testGetSameNameTableParameter() {
        Map<String, String> testData = new HashMap<String, String>();
        testData.put("configuration.headers[0].value", "value-0");
        testData.put("configuration.headers[2].query", "MAIN");
        testData.put("configuration.headers[1].query", "MAIN");
        testData.put("configuration.headers[0].query", "MAIN");
        testData.put("configuration.headers[2].key", "h3");
        testData.put("configuration.headers[1].key", "h2");
        testData.put("configuration.headers[0].key", "h1");
        testData.put("configuration.headers[1].value", "value-1");
        testData.put("configuration.headers[2].value", "value-2");
        testData.put("configuration.datastore.authentication.oauth20.params[2]", "scope2");
        testData.put("configuration.datastore.authentication.oauth20.params[1]", "scope1");
        testData.put("configuration.datastore.authentication.oauth20.params[0]", "scope0");

        Map<String, String> sameNameParms = ValueConverter.getSameNameTableParameter("configuration.headers", testData);
        assertEquals(9, sameNameParms.size());
        boolean hasError = false;
        int lastIndex = 0;
        for (String key : sameNameParms.keySet()) {
            int index = ValueConverter.getTableParameterIndex(key);
            if (index >= lastIndex) {
                lastIndex = index;
            } else {
                hasError = true;
            }
        }
        assertFalse(hasError);

        sameNameParms = ValueConverter.getSameNameTableParameter("configuration.datastore.authentication.oauth20.params",
                testData);
        assertEquals(3, sameNameParms.size());
        lastIndex = 0;
        for (String key : sameNameParms.keySet()) {
            int index = ValueConverter.getTableParameterIndex(key);
            if (index >= lastIndex) {
                lastIndex = index;
            } else {
                hasError = true;
            }
        }
        assertFalse(hasError);
    }

    @Test
    public void testIsListParameterValue() {
        String data = "[]";
        assertTrue(ValueConverter.isListParameterValue(data));

        data = " [ ] ";
        assertTrue(ValueConverter.isListParameterValue(data));

        data = "[{configuration.headers[].key=\"h1\", configuration.headers[].value=\"11\"}, {configuration.headers[].key=\"h2\", configuration.headers[].value=\"22\"}, {configuration.headers[].key=\"h3\", configuration.headers[].value=\"33\"}]";
        assertTrue(ValueConverter.isListParameterValue(data));

        data = "[{configuration.headers[].key=\"h1\", configuration.headers[].value=\"11\"}]";
        assertTrue(ValueConverter.isListParameterValue(data));

        data = "[{configuration.headers[].key=\"h1\", configuration.headers[].value=\"11\"}";
        assertFalse(ValueConverter.isListParameterValue(data));
    }

}
