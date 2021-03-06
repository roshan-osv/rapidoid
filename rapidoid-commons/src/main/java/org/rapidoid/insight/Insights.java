/*-
 * #%L
 * rapidoid-commons
 * %%
 * Copyright (C) 2014 - 2020 Nikolche Mihajlovski and contributors
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

package org.rapidoid.insight;

import org.rapidoid.RapidoidThing;
import org.rapidoid.collection.Coll;

import java.util.List;
import java.util.Map;

/**
 * @author Nikolche Mihajlovski
 * @since 4.1.0
 */
public class Insights extends RapidoidThing {

    private static final Map<String, List<Insightful>> RESOURCES = Coll.mapOfLists();

    private static final Map<String, List<Object>> INFOS = Coll.mapOfLists();

    private static final Map<String, List<Object>> RESETABLE_INFOS = Coll.mapOfLists();

    public static synchronized void register(Insightful resource) {
        RESOURCES.get(resource.getKind()).add(resource);
    }

    public static synchronized String getInfo() {
        String info = RESETABLE_INFOS.toString() + " :: " + INFOS.toString() + " :: " + RESOURCES.toString();

        for (List<Object> list : RESETABLE_INFOS.values()) {
            for (Object item : list) {
                if (item instanceof Measure) {
                    Measure m = (Measure) item;
                    m.reset();
                }
            }
        }

        return info;
    }

    public static String getCpuMemStats() {
        Runtime rt = Runtime.getRuntime();

        long totalMem = rt.totalMemory();
        long maxMem = rt.maxMemory();
        long freeMem = rt.freeMemory();
        long usedMem = totalMem - freeMem;
        int megs = 1024 * 1024;

        String msg = "MEM [total=%s MB, used=%s MB, max=%s MB]";
        return String.format(msg, totalMem / megs, usedMem / megs, maxMem / megs);
    }

    public static synchronized void show() {
        new InsightsThread().start();
    }

    public static synchronized void register(String name, Object info) {
        INFOS.get(name).add(info);
    }

    public static synchronized StatsMeasure stats(String name) {
        StatsMeasure measure = new StatsMeasure();
        RESETABLE_INFOS.get(name).add(measure);
        return measure;
    }

    public static synchronized void reset() {
        RESOURCES.clear();
        INFOS.clear();
        RESETABLE_INFOS.clear();
    }

}
