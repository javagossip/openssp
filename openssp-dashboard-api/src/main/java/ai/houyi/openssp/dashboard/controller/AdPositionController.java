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
package ai.houyi.openssp.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;

import ai.houyi.dorado.rest.annotation.Controller;
import ai.houyi.dorado.rest.annotation.GET;
import ai.houyi.dorado.rest.annotation.POST;
import ai.houyi.dorado.rest.annotation.Path;
import ai.houyi.dorado.rest.annotation.PathVariable;
import ai.houyi.dorado.rest.annotation.RequestBody;
import ai.houyi.openads.commons.PageResult;
import ai.houyi.openssp.core.service.AdPositionService;
import ai.houyi.openssp.dashboard.model.AdPositionSearchReq;
import ai.houyi.openssp.model.AdPosition;

/**
 * @author weiping wang
 *
 */
@Controller
@Path("/adposition")
public class AdPositionController {
	@Autowired
	private AdPositionService adPositionService;

	@POST
	@Path
	public void saveOrUpdateAdPosition(@RequestBody AdPosition adPosition) {
		adPositionService.saveOrUpdateAdPosition(adPosition);
	}

	@GET
	@Path("/{adPositionId}")
	public AdPosition getAdPosition(@PathVariable int adPositionId) {
		return adPositionService.loadAdPosition(adPositionId);
	}

	@POST
	@Path("/list")
	public PageResult<AdPosition> listAdPositions(@RequestBody AdPositionSearchReq searchReq) {
		return adPositionService.listAdPositions(searchReq.getPageNo(), searchReq.getPageSize(), searchReq.toExample());
	}
	
	@POST
	@Path("/{adPositionId}")
	public void deleteAdPosition(@PathVariable int adPositionId) {
		adPositionService.deleteAdPosition(adPositionId);
	}
}
