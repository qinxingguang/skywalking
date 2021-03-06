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

package org.skywalking.apm.collector.ui.jetty.handler;

import com.google.gson.JsonElement;
import org.skywalking.apm.collector.core.module.ModuleManager;
import org.skywalking.apm.collector.server.jetty.ArgumentsParseException;
import org.skywalking.apm.collector.server.jetty.JettyHandler;
import org.skywalking.apm.collector.ui.service.TraceStackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取一次分布式链路追踪记录详情的逻辑处理器
 *
 * @author peng-yongsheng
 */
public class TraceStackGetHandler extends JettyHandler {

    private final Logger logger = LoggerFactory.getLogger(TraceStackGetHandler.class);

    @Override public String pathSpec() {
        return "/traceStack/globalTraceId";
    }

    private final TraceStackService service;

    public TraceStackGetHandler(ModuleManager moduleManager) {
        this.service = new TraceStackService(moduleManager);
    }

    @Override protected JsonElement doGet(HttpServletRequest req) throws ArgumentsParseException {
        // 解析 globalTraceId 参数
        String globalTraceId = req.getParameter("globalTraceId");
        logger.debug("globalTraceId: {}", globalTraceId);

        // 获取一次分布式链路追踪记录详情的逻辑处理器
        return service.load(globalTraceId);
    }

    @Override protected JsonElement doPost(HttpServletRequest req) throws ArgumentsParseException {
        throw new UnsupportedOperationException();
    }
}
