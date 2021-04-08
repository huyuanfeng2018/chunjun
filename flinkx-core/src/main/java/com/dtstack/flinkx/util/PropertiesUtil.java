/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dtstack.flinkx.util;

import com.dtstack.flinkx.conf.FlinkxCommonConf;
import com.dtstack.flinkx.conf.SyncConf;
import org.apache.commons.io.Charsets;
import org.apache.commons.lang3.StringUtils;

import java.net.URLDecoder;
import java.util.Properties;

/**
 * Date: 2021/01/19
 * Company: www.dtstack.com
 *
 * @author tudou
 */
public class PropertiesUtil {

    /**
     * 解析properties json字符串
     * @param confStr json字符串
     * @return Properties
     * @throws Exception
     */
    public static Properties parseConf(String confStr) throws Exception{
        if(StringUtils.isEmpty(confStr)){
            return new Properties();
        }

        confStr = URLDecoder.decode(confStr, Charsets.UTF_8.toString());
        return GsonUtil.GSON.fromJson(confStr, Properties.class);
    }

    /**
     * Properties key value去空格
     * @param confProperties
     * @return
     */
    public static Properties propertiesTrim(Properties confProperties) {
        Properties properties = new Properties();
        confProperties.forEach(
                (k, v) -> {
                    properties.put(k.toString().trim(), v.toString().trim());
                }
        );
        return properties;
    }

    /**
     * 初始化FlinkxCommonConf
     * @param flinkxCommonConf
     * @param syncConf
     */
    public static void initFlinkxCommonConf(FlinkxCommonConf flinkxCommonConf, SyncConf syncConf){
        flinkxCommonConf.setBytes(syncConf.getSpeed().getBytes());
        flinkxCommonConf.setRecord(syncConf.getErrorLimit().getRecord());
        flinkxCommonConf.setPercentage(syncConf.getErrorLimit().getPercentage());
        flinkxCommonConf.setLogger(syncConf.getLog().isLogger());
        flinkxCommonConf.setLogLevel(syncConf.getLog().getLevel());
        flinkxCommonConf.setLogPath(syncConf.getLog().getPath());
        flinkxCommonConf.setLogPattern(syncConf.getLog().getPattern());
        flinkxCommonConf.setDirtyDataPath(syncConf.getDirty().getPath());
        flinkxCommonConf.setDirtyDataHadoopConf(syncConf.getDirty().getHadoopConfig());
        flinkxCommonConf.setFieldNameList(syncConf.getDirty().getReaderColumnNameList());

    }
}
