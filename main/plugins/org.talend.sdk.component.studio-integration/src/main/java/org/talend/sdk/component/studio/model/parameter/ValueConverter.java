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
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

/**
 * Utility class for ElementParameter value conversion. It is used to convert string values from repository to
 * appropriate types
 * used in ElementParameter
 */
public final class ValueConverter {

    private ValueConverter() {
        new AssertionError();
    }

    /**
     * Pattern used to find first and last square bracket in the string
     */
    private static final Pattern BRACKETS_PATTERN = Pattern.compile("^\\[|\\]$");

    /**
     * Pattern used to find first and last curly bracket in the string
     */
    private static final Pattern CURLY_BRACKETS_PATTERN = Pattern.compile("^\\{|\\}$");

    /**
     * Converts String to List of Maps (table element parameter representation)
     * It assumes {@code str} has correct format (it doesn't check it)
     * TODO: quick implementation. May have bugs
     *
     * @param str String value to be converted to list
     * @return list value
     */
    public static List<Map<String, Object>> toTable(final String str) {
        if (isListEmpty(str)) {
            return new ArrayList<>();
        }
        ArrayList<Map<String, Object>> table = new ArrayList<>();
        String trimmed = trimBrackets(str);
        String[] records = trimmed.split("\\},\\s?\\{");
        for (String record : records) {
            record = trimCurlyBrackets(record);
            String[] entries = record.split(",\\s?");
            Map<String, Object> element = new LinkedHashMap<>();
            for (String entry : entries) {
                String[] keyValue = new String[] {};
                if (entry.contains("=")) {
                    keyValue = entry.split("=");
                } else {
                    keyValue = entry.split(":");
                }
                if (keyValue.length < 2) {
                    continue;
                }
                String key = keyValue[0];
                String value = "null".equals(keyValue[1]) ? null : keyValue[1];
                element.put(key, value);
            }
            table.add(element);
        }
        return table;
    }
    
    
    /**
     * The logic same with TableElementParameter.getStringValue
     * @param list
     * @return
     */
    public static String toStringValue(List<Map<String, String>> list) {
        return list.toString();
    }

    /**
     * Checks whether String representation of the list is empty or not
     *
     * @param list String representation of the list
     * @return true, if it is empty
     */
    private static boolean isListEmpty(final String list) {
        return list == null || list.isEmpty() || "[]".equals(list) || "[{}]".equals(list);
    }

    /**
     * Trims first symbol if it is '[' and last one if it is ']'
     *
     * @param str String to trim
     * @return trimmed string
     */
    private static String trimBrackets(final String str) {
        return BRACKETS_PATTERN.matcher(str).replaceAll("");
    }

    /**
     * Trims first symbol if it is '{' and last one if it is '}'
     *
     * @param str String to trim
     * @return trimmed string
     */
    private static String trimCurlyBrackets(final String str) {
        return CURLY_BRACKETS_PATTERN.matcher(str).replaceAll("");
    }


    public static String getMainTableParameterName(String name) {
        int begin = name.indexOf("[");
        int end = name.indexOf("]");
        if (begin > 0 && end > 0 && end > begin) {
            return name.substring(0, begin);
        }
        return name;
    }


    public static int getTableParameterIndex(String name) {
        int begin = name.indexOf("[");
        int end = name.indexOf("]");
        if (begin > 0 && end > 0 && end > begin) {
            return Integer.parseInt(name.substring(begin + 1, end));
        }
        return -1;
    }
    
    public static String getTableParameterNameInProperties(String name) {
        int begin = name.indexOf("[");
        int end = name.indexOf("]");
        if (begin > 0 && end > 0 && end > begin) {
            return name.substring(0, begin + 1) + name.substring(end);
        }
        return name;
    }
    
    /**
     *  Get same main name parameters that key sorted by index
     * @param paramName
     * @param migratedProperties
     * @return same main name parameters that key sorted by index
     */
    public static Map<String, String> getSameNameTableParameter(String paramName, Map<String, String> migratedProperties) {
        Map<String, String> properties = new HashMap<String, String>();
        for (String key : migratedProperties.keySet()) {
            String name = ValueConverter.getMainTableParameterName(key);
            if (paramName.equals(name)) {
                properties.put(key, migratedProperties.get(key));
            }
        }
        Map<String, String> sortedMap = new TreeMap<String, String>(new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                int index1 = ValueConverter.getTableParameterIndex(o1);
                int index2 = ValueConverter.getTableParameterIndex(o2);
                if (index1 != index2) {
                    return index1 - index2;
                }
                return o1.compareTo(o2);
            }
        });
        sortedMap.putAll(properties);
        return sortedMap;
    }

    public static boolean isListParameterValue(String value) {
        if (value != null && value.trim().startsWith("[") && value.trim().endsWith("]")) {
            return true;
        }
        return false;
    }
}
