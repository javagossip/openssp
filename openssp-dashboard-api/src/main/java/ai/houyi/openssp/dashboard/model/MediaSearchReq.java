/*
 * Copyright 2017 The OpenAds Project
 *
 * The OpenAds Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package ai.houyi.openssp.dashboard.model;

import org.apache.commons.lang3.StringUtils;

import ai.houyi.openssp.model.example.MediaExample;

/**
 * @author weiping wang
 *
 */
public class MediaSearchReq extends SearchReq {
	private String name;
	private Integer type;
	private Integer status;
	private Integer publisherId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public MediaExample toExample() {
		MediaExample example = new MediaExample();
		MediaExample.Criteria criteria = example.createCriteria();

		if (StringUtils.isNotBlank(name)) {
			criteria.andNameLike("%" + name + "%");
		}

		if (type != null) {
			criteria.andTypeEqualTo(type);
		}

		if (status != null) {
			criteria.andStatusEqualTo(status);
		}

		if (publisherId != null) {
			criteria.andPublisherIdEqualTo(publisherId);
		}
		return example;
	}
}
