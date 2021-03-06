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

package org.skywalking.apm.collector.storage.es.dao;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.skywalking.apm.collector.client.elasticsearch.ElasticSearchClient;
import org.skywalking.apm.collector.storage.dao.IApplicationRegisterDAO;
import org.skywalking.apm.collector.storage.es.base.dao.EsDAO;
import org.skywalking.apm.collector.storage.table.register.Application;
import org.skywalking.apm.collector.storage.table.register.ApplicationTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author peng-yongsheng
 */
public class ApplicationEsRegisterDAO extends EsDAO implements IApplicationRegisterDAO {

    private final Logger logger = LoggerFactory.getLogger(ApplicationEsRegisterDAO.class);

    public ApplicationEsRegisterDAO(ElasticSearchClient client) {
        super(client);
    }

    @Override public int getMaxApplicationId() {
        return getMaxId(ApplicationTable.TABLE, ApplicationTable.COLUMN_APPLICATION_ID);
    }

    @Override public int getMinApplicationId() {
        return getMinId(ApplicationTable.TABLE, ApplicationTable.COLUMN_APPLICATION_ID);
    }

    @Override public void save(Application application) {
        logger.debug("save application register info, application getId: {}, application code: {}", application.getId(), application.getApplicationCode());
        ElasticSearchClient client = getClient();
        Map<String, Object> source = new HashMap<>();
        source.put(ApplicationTable.COLUMN_APPLICATION_CODE, application.getApplicationCode());
        source.put(ApplicationTable.COLUMN_APPLICATION_ID, application.getApplicationId());

        IndexResponse response = client.prepareIndex(ApplicationTable.TABLE, application.getId()).setSource(source).setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE).get();
        logger.debug("save application register info, application getId: {}, application code: {}, status: {}", application.getApplicationId(), application.getApplicationCode(), response.status().name());
    }

}
