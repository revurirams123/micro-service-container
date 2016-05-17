/*
 * Copyright 2016 Adobe Systems Incorporated. All rights reserved.
 *
 * This file is licensed to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR REPRESENTATIONS OF ANY KIND,
 * either express or implied.  See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.adobe.api.platform.msc.client.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import javax.ws.rs.core.Link;
import java.io.IOException;

/**
 * Custom Jackson deserializer for JAX-RS {@link javax.ws.rs.core.Link} objects.
 * <p/>
 * User: ccristia
 * Date: 01/17/14
 */
public class LinkDeserializer extends JsonDeserializer<Link> {

    @Override
    public Link deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);
        return Link.fromUri(node.get("href").asText())
                .rel(node.get("rel").asText())
                .title(node.get("title").asText())
                .build();
    }
}
