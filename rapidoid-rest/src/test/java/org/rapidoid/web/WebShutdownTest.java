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

package org.rapidoid.web;

import org.junit.jupiter.api.Test;
import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;
import org.rapidoid.http.IsolatedIntegrationTest;
import org.rapidoid.setup.App;
import org.rapidoid.setup.Setup;
import org.rapidoid.setup.Setups;

@Authors("Nikolche Mihajlovski")
@Since("5.4.2")
public class WebShutdownTest extends IsolatedIntegrationTest {

    @Test
    public void step1() {
        App app = new App().start();
        app.port(9999);
        app.shutdown();
    }

    @Test
    public void step2() {
        App app = new App().start();
        app.get("/x").plain("X");

        onlyGet("/x");
    }

}
