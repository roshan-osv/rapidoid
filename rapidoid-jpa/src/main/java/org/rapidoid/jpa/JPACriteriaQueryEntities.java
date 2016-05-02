package org.rapidoid.jpa;

/*
 * #%L
 * rapidoid-jpa
 * %%
 * Copyright (C) 2014 - 2016 Nikolche Mihajlovski and contributors
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

import org.rapidoid.RapidoidThing;
import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Authors("Nikolche Mihajlovski")
@Since("5.1.0")
public class JPACriteriaQueryEntities<T> extends RapidoidThing implements Entities<T> {

	private final CriteriaQuery<T> criteria;

	public JPACriteriaQueryEntities(CriteriaQuery<T> criteria) {
		this.criteria = criteria;
	}

	private Query query() {
		return JPA.em().createQuery(this.criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> all() {
		return query().getResultList();
	}

	@Override
	public List<T> page(int start, int length) {
		return JPAUtil.getPage(query(), start, length);
	}

}