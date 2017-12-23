/*
 * Copyright 2017, OpenSkywalking Organization All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Project repository: https://github.com/OpenSkywalking/skywalking
 */

package org.skywalking.apm.agent.core.context.util;

import org.skywalking.apm.network.proto.KeyWithStringValue;

/**
 * The <code>KeyValuePair</code> represents a object which contains a string key and a string value.
 *
 * @author wusheng
 */
public class KeyValuePair {
    private String key;
    private String value;

    public KeyValuePair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public KeyWithStringValue transform() {
        KeyWithStringValue.Builder keyValueBuilder = KeyWithStringValue.newBuilder();
        keyValueBuilder.setKey(key);
        if (value != null) {
            keyValueBuilder.setValue(value);
        }
        return keyValueBuilder.build();
    }
}