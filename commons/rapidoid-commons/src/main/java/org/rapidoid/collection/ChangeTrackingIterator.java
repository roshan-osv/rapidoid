/*-
 * #%L
 * rapidoid-commons
 * %%
 * Copyright (C) 2014 - 2018 Nikolche Mihajlovski and contributors
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

package org.rapidoid.collection;

import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;


@Authors("Nikolche Mihajlovski")
@Since("5.2.0")
public class ChangeTrackingIterator<E> extends AbstractIteratorDecorator<E> {

	private final AtomicBoolean dirtyFlag;

	public ChangeTrackingIterator(Iterator<E> decorated, AtomicBoolean dirtyFlag) {
		super(decorated);
		this.dirtyFlag = dirtyFlag;
	}

	@Override
	public void remove() {
		super.remove();
		dirtyFlag.set(true);
	}

}