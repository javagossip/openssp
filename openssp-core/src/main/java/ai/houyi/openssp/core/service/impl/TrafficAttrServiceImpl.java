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
import ai.houyi.openssp.core.service.TrafficAttrService;
import ai.houyi.openssp.mapper.TrafficAttrMapper;
import ai.houyi.openssp.model.TrafficAttr;
import ai.houyi.openssp.model.example.TrafficAttrExample;

/**
 * 
 * @author wangwp
 */
@Service
public class TrafficAttrServiceImpl implements TrafficAttrService {
	@Autowired
	private TrafficAttrMapper trafficAttrMapper;

	@Override
	public void saveOrUpdateTrafficAttr(TrafficAttr trafficAttr) {
		if (trafficAttr.getId() == null) {
			trafficAttrMapper.insertSelective(trafficAttr);
		} else {
			trafficAttrMapper.updateByPrimaryKeySelective(trafficAttr);
		}
	}

	@Override
	public void deleteTrafficAttr(int trafficAttrId) {
		trafficAttrMapper.deleteByPrimaryKey(trafficAttrId);
	}

	@Override
	public TrafficAttr loadTrafficAttr(int trafficAttrId) {
		return trafficAttrMapper.selectByPrimaryKey(trafficAttrId);
	}

	@Override
	public PageResult<TrafficAttr> listTrafficAttrs(int pageNo, int pageSize, TrafficAttrExample _example) {
		TrafficAttrExample example = _example == null ? new TrafficAttrExample() : _example;
		int total = (int) trafficAttrMapper.countByExample(example);

		example.limit((pageNo - 1) * pageSize, pageSize);
		List<TrafficAttr> dataList = trafficAttrMapper.selectByExample(example);

		PageResult.Builder<TrafficAttr> builder = PageResult.builder();
		builder.withDataList(dataList).withPageNo(pageNo).withPageSize(pageSize).withTotal(total);

		return builder.build();
	}

	@Override
	public PageResult<TrafficAttr> listTrafficAttrs(int pageNo, int pageSize) {
		return listTrafficAttrs(pageNo, pageSize, null);
	}

	@Override
	public PageResult<TrafficAttr> listTrafficAttrs(int pageNo) {
		return listTrafficAttrs(pageNo, Constants.DEFAULT_PAGE_SIZE, null);
	}

	@Override
	public void batchDeleteTrafficAttrs(List<Integer> trafficAttrIds) {
		TrafficAttrExample example = TrafficAttrExample.newAndCreateCriteria().andIdIn(trafficAttrIds).example();
		trafficAttrMapper.deleteByExample(example);
	}
}
