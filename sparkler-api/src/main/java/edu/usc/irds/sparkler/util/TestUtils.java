/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.usc.irds.sparkler.util;

import edu.usc.irds.sparkler.C;
import edu.usc.irds.sparkler.ExtensionPoint;
import edu.usc.irds.sparkler.JobContext;
import edu.usc.irds.sparkler.SparklerException;
import org.apache.hadoop.conf.Configuration;

/**
 * This class contains some utils to help plugin developers write unit tests easily
 * @since Sparkler 0.1
 */
public class TestUtils {

    /**
     * Configuration to be used by  tests
     */
    public static final Configuration CONFIG = C.defaults.newDefaultConfig();

    /**
     * Job context to be used by the tests
     */
    public static final JobContext JOB_CONTEXT = () -> CONFIG;

    /**
     * Instantiates and tests the
     * @param clazz the class
     * @param <T> type
     * @return instance of the extension
     * @throws SparklerException when an error occurs
     */
    public static <T extends ExtensionPoint> T newInstance(Class<T> clazz) throws SparklerException {
        try {
            T instance = clazz.newInstance();
            instance.init(JOB_CONTEXT);
            return instance;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new SparklerException("Could not create instance of " + clazz.getName(), e);
        }
    }
}
