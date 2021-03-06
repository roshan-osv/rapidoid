/*-
 * #%L
 * rapidoid-rest
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

package org.rapidoid.http;

import org.junit.jupiter.api.Test;
import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;
import org.rapidoid.setup.App;

import java.util.TreeMap;

@Authors("Nikolche Mihajlovski")
@Since("5.2.0")
public class HttpZoneTest extends IsolatedIntegrationTest {

    @Test
    public void testMainZone() {
        App app = new App().start();

        ReqHandler zoneHandler = req -> new TreeMap<>(HttpUtils.zone(req).toMap());

        app.get("/a").json(zoneHandler);
        app.get("/b").zone("admin").json(zoneHandler);
        app.get("/c").zone("other").json(zoneHandler);
        app.get("/d").zone("center").json(zoneHandler);

        onlyGet("/a");
        onlyGet("/b");
        onlyGet("/c");
        onlyGet("/d");
    }

}
