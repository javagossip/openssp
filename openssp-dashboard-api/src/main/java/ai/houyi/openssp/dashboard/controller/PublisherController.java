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
import ai.houyi.openssp.core.service.PublisherService;
import ai.houyi.openssp.dashboard.model.PublisherSearchReq;
import ai.houyi.openssp.model.Publisher;

/**
 * @author weiping wang
 *
 */
@Controller
@Path("/publisher")
public class PublisherController {
	@Autowired
	private PublisherService publisherService;

	@POST
	@Path
	public void saveOrUpdatePublisher(@RequestBody Publisher publisher) {
		publisherService.saveOrUpdatePublisher(publisher);
	}

	@GET
	@Path("/{publisherId}")
	public Publisher getPublisher(@PathVariable int publisherId) {
		return publisherService.loadPublisher(publisherId);
	}

	@POST
	@Path("/list")
	public PageResult<Publisher> listPublishers(@RequestBody PublisherSearchReq req) {
		return publisherService.listPublishers(req.getPageNo(), req.getPageSize(),req.toExample());
	}
	
	@POST
	@Path("/{publisherId}")
	public void deletePublisher(@PathVariable int publisherId) {
		publisherService.deletePublisher(publisherId);
	}
}
