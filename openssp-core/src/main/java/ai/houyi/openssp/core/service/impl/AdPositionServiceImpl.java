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
import ai.houyi.openssp.core.service.AdPositionService;
import ai.houyi.openssp.mapper.AdPositionMapper;
import ai.houyi.openssp.model.AdPosition;
import ai.houyi.openssp.model.example.AdPositionExample;

/**
 * 
 * @author wangwp
 */
@Service
public class AdPositionServiceImpl implements AdPositionService {
	@Autowired
	private AdPositionMapper adPositionMapper;

	@Override
	public void saveOrUpdateAdPosition(AdPosition adPosition) {
		if (adPosition.getId() == null) {
			adPositionMapper.insertSelective(adPosition);
		} else {
			adPositionMapper.updateByPrimaryKeySelective(adPosition);
		}
	}

	@Override
	public void deleteAdPosition(int adPositionId) {
		adPositionMapper.deleteByPrimaryKey(adPositionId);
	}

	@Override
	public AdPosition loadAdPosition(int adPositionId) {
		return adPositionMapper.selectByPrimaryKey(adPositionId);
	}

	@Override
	public PageResult<AdPosition> listAdPositions(int pageNo, int pageSize, AdPositionExample _example) {
		AdPositionExample example = _example == null ? new AdPositionExample() : _example;
		int total = (int) adPositionMapper.countByExample(example);

		example.limit((pageNo - 1) * pageSize, pageSize);
		List<AdPosition> dataList = adPositionMapper.selectByExample(example);

		PageResult.Builder<AdPosition> builder = PageResult.builder();
		builder.withDataList(dataList).withPageNo(pageNo).withPageSize(pageSize).withTotal(total);

		return builder.build();
	}

	@Override
	public PageResult<AdPosition> listAdPositions(int pageNo, int pageSize) {
		return listAdPositions(pageNo, pageSize, null);
	}

	@Override
	public PageResult<AdPosition> listAdPositions(int pageNo) {
		return listAdPositions(pageNo, Constants.DEFAULT_PAGE_SIZE, null);
	}

}
