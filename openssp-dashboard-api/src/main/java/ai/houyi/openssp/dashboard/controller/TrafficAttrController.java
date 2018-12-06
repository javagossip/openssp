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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ai.houyi.dorado.rest.annotation.Controller;
import ai.houyi.dorado.rest.annotation.GET;
import ai.houyi.dorado.rest.annotation.POST;
import ai.houyi.dorado.rest.annotation.Path;
import ai.houyi.dorado.rest.annotation.PathVariable;
import ai.houyi.dorado.rest.annotation.RequestBody;
import ai.houyi.openads.commons.PageResult;
import ai.houyi.openssp.core.service.TrafficAttrService;
import ai.houyi.openssp.dashboard.model.TrafficAttrSearchReq;
import ai.houyi.openssp.model.TrafficAttr;

/**
 * @author weiping wang
 *
 */
@Controller
@Path("/trafficattr")
public class TrafficAttrController {
	@Autowired
	private TrafficAttrService trafficAttrService;

	@POST
	@Path
	public void saveOrUpdateTrafficAttr(@RequestBody TrafficAttr trafficAttr) {
		trafficAttrService.saveOrUpdateTrafficAttr(trafficAttr);
	}

	@GET
	@Path("/{trafficAttrId}")
	public TrafficAttr getTrafficAttr(@PathVariable int trafficAttrId) {
		return trafficAttrService.loadTrafficAttr(trafficAttrId);
	}

	@POST
	@Path("/list")
	public PageResult<TrafficAttr> listTrafficAttrs(@RequestBody TrafficAttrSearchReq searchReq) {
		return trafficAttrService.listTrafficAttrs(searchReq.getPageNo(), searchReq.getPageSize(),
				searchReq.toExample());
	}

	@POST
	@Path("/{trafficAttrId}")
	public void deleteTrafficAttr(int trafficAttrId) {
		trafficAttrService.deleteTrafficAttr(trafficAttrId);
	}

	@POST
	@Path("/delete")
	public void deleteTrafficAttrs(List<Integer> trafficAttrIds) {
		trafficAttrService.batchDeleteTrafficAttrs(trafficAttrIds);
	}
}
