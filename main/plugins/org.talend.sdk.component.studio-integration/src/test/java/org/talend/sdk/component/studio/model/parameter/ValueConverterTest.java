/**
 * Copyright (C) 2006-2021 Talend Inc. - www.talend.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.talend.sdk.component.studio.model.parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit-tests for {@link ValueConverter}
 */
class ValueConverterTest {

    @Test
    void testToTable() {
        Map<String, Object> expected0 = new HashMap<>();
        expected0.put("key1", "value11");
        expected0.put("key2", "value12");
        Map<String, Object> expected1 = new HashMap<>();
        expected1.put("key1", "value21");
        expected1.put("key2", "value22");

        String table = "[{key1=value11, key2=value12}, {key1=value21, key2=value22}]";
        List<Map<String, Object>> converted = ValueConverter.toTable(table);
        Assertions.assertEquals(2, converted.size());
        Assertions.assertEquals(expected0, converted.get(0));
        Assertions.assertEquals(expected1, converted.get(1));
    }

    @Test
    void testToTableNull() {
        ArrayList<Map<String, Object>> empty = new ArrayList<>();
        List<Map<String, Object>> actual = ValueConverter.toTable(null);
        Assertions.assertEquals(empty, actual);
    }

    @Test
    void testToTableEmpty() {
        ArrayList<Map<String, Object>> empty = new ArrayList<>();
        List<Map<String, Object>> actual = ValueConverter.toTable("");
        Assertions.assertEquals(empty, actual);
    }

}
