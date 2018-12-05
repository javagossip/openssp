/*
 * Copyright 2017 The OpenDSP Project
 *
 * The OpenDSP Project licenses this file to you under the Apache License,
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
package ai.houyi.openssp.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.houyi.openads.commons.Constants;
import ai.houyi.openads.commons.PageResult;
import ai.houyi.openssp.core.service.PublisherService;
import ai.houyi.openssp.mapper.PublisherMapper;
import ai.houyi.openssp.model.Publisher;
import ai.houyi.openssp.model.example.PublisherExample;

/**
 * 
 * @author wangwp
 */
@Service
public class PublisherServiceImpl implements PublisherService {
	@Autowired
	private PublisherMapper publisherMapper;

	@Override
	public void saveOrUpdatePublisher(Publisher publisher) {
		if (publisher.getId() == null) {
			publisherMapper.insertSelective(publisher);
		} else {
			publisherMapper.updateByPrimaryKeySelective(publisher);
		}
	}

	@Override
	public void deletePublisher(int publisherId) {
		publisherMapper.deleteByPrimaryKey(publisherId);
	}

	@Override
	public Publisher loadPublisher(int publisherId) {
		return publisherMapper.selectByPrimaryKey(publisherId);
	}

	@Override
	public PageResult<Publisher> listPublishers(int pageNo, int pageSize) {
		return listPublishers(pageNo, pageSize, null);
	}

	@Override
	public PageResult<Publisher> listPublishers(int pageNo) {
		return listPublishers(pageNo, Constants.DEFAULT_PAGE_SIZE);
	}

	@Override
	public PageResult<Publisher> listPublishers(int pageNo, int pageSize, PublisherExample example) {
		PublisherExample _example = example == null ? new PublisherExample() : example;
		long total = publisherMapper.countByExample(_example);
		_example.limit((pageNo - 1) * pageSize, pageSize);

		List<Publisher> dataList = publisherMapper.selectByExample(_example);

		PageResult.Builder<Publisher> builder = PageResult.builder();
		builder.withDataList(dataList).withPageNo(pageNo).withPageSize(pageSize).withTotal((int) total);

		return builder.build();
	}
}
