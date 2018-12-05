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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.houyi.openssp.core.service.AppAdPositionService;
import ai.houyi.openssp.mapper.AdPositionMapper;
import ai.houyi.openssp.mapper.AppAdPositionMapper;
import ai.houyi.openssp.model.AdPosition;
import ai.houyi.openssp.model.AppAdPosition;
import ai.houyi.openssp.model.example.AdPositionExample;
import ai.houyi.openssp.model.example.AppAdPositionExample;

/**
 * 
 * @author wangwp
 */
@Service
public class AppAdPositionServiceImpl implements AppAdPositionService {
	@Autowired
	private AppAdPositionMapper appAdPositionMapper;
	@Autowired
	private AdPositionMapper adPositionMapper;

	@Override
	public void setAppAdPositions(int appId, List<Integer> adPositionIds) {
		List<AppAdPosition> appAdPositions = new ArrayList<>();

		adPositionIds.forEach(adPositionId -> {
			AppAdPosition appAdPosition = new AppAdPosition();
			appAdPosition.setAdPositionId(adPositionId);
			appAdPosition.setAppId(appId);
			appAdPositions.add(appAdPosition);
		});

		appAdPositionMapper.batchInsert(appAdPositions);
	}

	@Override
	public List<AdPosition> listAppAdPositions(int appId) {
		List<AppAdPosition> appAdPositions = appAdPositionMapper
				.selectByExample(new AppAdPositionExample().createCriteria().andAppIdEqualTo(appId).example());
		if (appAdPositions == null || appAdPositions.isEmpty()) {
			return null;
		}

		List<Integer> adPositionIds = new ArrayList<>();
		appAdPositions.forEach(e -> adPositionIds.add(e.getAdPositionId()));

		return adPositionMapper
				.selectByExample(new AdPositionExample().createCriteria().andIdIn(adPositionIds).example());
	}
}
