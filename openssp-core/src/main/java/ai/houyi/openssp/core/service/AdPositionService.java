/*
 * Copyright 2017 The OpenAds Project
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
package ai.houyi.openssp.core.service;

import ai.houyi.openads.commons.PageResult;
import ai.houyi.openssp.model.AdPosition;
import ai.houyi.openssp.model.example.AdPositionExample;

/**
 * @author weiping wang
 *
 */
public interface AdPositionService {
	void saveOrUpdateAdPosition(AdPosition adPosition);

	void deleteAdPosition(int adPositionId);

	AdPosition loadAdPosition(int adPositionId);

	PageResult<AdPosition> listAdPositions(int pageNo, int pageSize, AdPositionExample _example);

	PageResult<AdPosition> listAdPositions(int pageNo, int pageSize);

	PageResult<AdPosition> listAdPositions(int pageNo);
}
