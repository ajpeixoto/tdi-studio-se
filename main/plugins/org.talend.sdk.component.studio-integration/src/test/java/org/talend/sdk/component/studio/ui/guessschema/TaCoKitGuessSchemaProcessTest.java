/*
 * Copyright (C) 2006-2023 Talend Inc. - www.talend.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 */

package org.talend.sdk.component.studio.ui.guessschema;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.talend.sdk.component.api.exception.DiscoverSchemaException;


class TaCoKitGuessSchemaProcessTest {


    @Test
    void serializeDiscoverSchemaException() {
        final String flattened ="{\"localizedMessage\":\"Not allowed to execute the HTTP call to retrieve the schema.\",\"message\":\"Not allowed to execute the HTTP call to retrieve the schema.\",\"stackTrace\":[{\"className\":\"org.talend.sdk.component.runtime.di.schema.TaCoKitGuessSchema\",\"fileName\":\"TaCoKitGuessSchema.java\",\"lineNumber\":209,\"methodName\":\"guessComponentSchema\"},{\"className\":\"guess.mock_job_for_guess_schema_0_1.Mock_job_for_Guess_schema\",\"fileName\":\"Mock_job_for_Guess_schema.java\",\"lineNumber\":478,\"methodName\":\"tHTTPClient_1Process\"},{\"className\":\"guess.mock_job_for_guess_schema_0_1.Mock_job_for_Guess_schema\",\"fileName\":\"Mock_job_for_Guess_schema.java\",\"lineNumber\":1015,\"methodName\":\"runJobInTOS\"},{\"className\":\"guess.mock_job_for_guess_schema_0_1.Mock_job_for_Guess_schema\",\"fileName\":\"Mock_job_for_Guess_schema.java\",\"lineNumber\":804,\"methodName\":\"main\"}],\"suppressed\":[],\"possibleHandleErrorWith\":\"exception\"}";
        final String f =
"{\"localizedMessage\":\"Not allowed to execute the HTTP call to retrieve the schema.\",\"message\":\"Not allowed to execute the HTTP call to retrieve the schema.\",\"suppressed\":[],\"possibleHandleErrorWith\":\"exception\"}";
        final Jsonb jsonb = JsonbBuilder.create();
        DiscoverSchemaException e = jsonb.fromJson(flattened, DiscoverSchemaException.class);
        assertFalse("execute".equals(e.getPossibleHandleErrorWith()));
        assertEquals("exception", e.getPossibleHandleErrorWith());
        assertEquals("Not allowed to execute the HTTP call to retrieve the schema.", e.getMessage());
        assertEquals("Not allowed to execute the HTTP call to retrieve the schema.", e.getLocalizedMessage());
    }
}