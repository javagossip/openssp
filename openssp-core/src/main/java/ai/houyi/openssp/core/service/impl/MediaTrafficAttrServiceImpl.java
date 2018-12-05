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

import com.alibaba.fastjson.JSON;

import ai.houyi.openssp.core.service.MediaTrafficAttrService;
import ai.houyi.openssp.mapper.MediaTrafficAttrMapper;
import ai.houyi.openssp.model.MediaTrafficAttr;
import ai.houyi.openssp.model.example.MediaTrafficAttrExample;

/**
 * 
 * @author wangwp
 */
@Service
public class MediaTrafficAttrServiceImpl implements MediaTrafficAttrService {
	@Autowired
	private MediaTrafficAttrMapper mediaTrafficAttrMapper;

	@Override
	public void addTrafficAttrsToMedia(int mediaId, List<Integer> trafficAttrIds) {
		List<MediaTrafficAttr> mediaTrafficAttrs = new ArrayList<>();

		trafficAttrIds.forEach(trafficAttrId -> {
			MediaTrafficAttr mediaTrafficAttr = new MediaTrafficAttr();
			mediaTrafficAttr.setMediaId(mediaId);
			mediaTrafficAttr.setTrafficAttrId(trafficAttrId);

			mediaTrafficAttrs.add(mediaTrafficAttr);
		});

		mediaTrafficAttrMapper.batchInsert(mediaTrafficAttrs);
	}

	@Override
	public List<MediaTrafficAttr> listTrafficAttrsByMediaId(int mediaId) {
		return mediaTrafficAttrMapper
				.selectByExample(new MediaTrafficAttrExample().createCriteria().andMediaIdEqualTo(mediaId).example());
	}

	@Override
	public void setMediaTrafficAttrValues(int mediaTrafficAttrId, List<String> enumValues) {
		MediaTrafficAttr mediaTrafficAttr = new MediaTrafficAttr();
		mediaTrafficAttr.setId(mediaTrafficAttrId);
		mediaTrafficAttr.setEnumValues(JSON.toJSONString(enumValues));
	}
}
