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

package org.skywalking.apm.agent.core.conf;

import org.skywalking.apm.agent.core.dictionary.DictionaryUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Agent 远程配置类
 * 需要从 Collector 进行读取
 *
 * The <code>RemoteDownstreamConfig</code> includes configurations from collector side.
 * All of them initialized null, Null-Value or empty collection.
 *
 * @author wusheng
 */
public class RemoteDownstreamConfig {
    public static class Agent {
        public volatile static int APPLICATION_ID = DictionaryUtil.nullValue();

        public volatile static int APPLICATION_INSTANCE_ID = DictionaryUtil.nullValue();
    }

    public static class Collector {
        /**
         * Collector GRPC-Service address.
         */
        public volatile static List<String> GRPC_SERVERS = new LinkedList<String>();
    }
}