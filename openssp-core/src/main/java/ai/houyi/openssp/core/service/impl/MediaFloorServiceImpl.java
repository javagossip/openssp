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
import ai.houyi.openssp.core.service.MediaFloorService;
import ai.houyi.openssp.mapper.MediaFloorMapper;
import ai.houyi.openssp.model.MediaFloor;
import ai.houyi.openssp.model.example.MediaFloorExample;

/**
 * 
 * @author wangwp
 */
@Service
public class MediaFloorServiceImpl implements MediaFloorService {
	@Autowired
	private MediaFloorMapper mediaFloorMapper;

	@Override
	public void saveOrUpdateMediaFloor(MediaFloor mediaFloor) {
		if (mediaFloor.getId() == null) {
			mediaFloorMapper.insertSelective(mediaFloor);
		} else {
			mediaFloorMapper.updateByPrimaryKeySelective(mediaFloor);
		}
	}

	@Override
	public void deleteMediaFloor(int mediaFloorId) {
		mediaFloorMapper.deleteByPrimaryKey(mediaFloorId);
	}

	@Override
	public MediaFloor loadMediaFloor(int mediaFloorId) {
		return mediaFloorMapper.selectByPrimaryKey(mediaFloorId);
	}

	@Override
	public PageResult<MediaFloor> listMediaFloors(int pageNo, int pageSize, MediaFloorExample _example) {
		MediaFloorExample example = _example == null ? new MediaFloorExample() : _example;
		int total = (int) mediaFloorMapper.countByExample(example);

		example.limit((pageNo - 1) * pageSize, pageSize);
		List<MediaFloor> dataList = mediaFloorMapper.selectByExample(example);

		PageResult.Builder<MediaFloor> builder = PageResult.builder();
		builder.withDataList(dataList).withPageNo(pageNo).withPageSize(pageSize).withTotal(total);

		return builder.build();
	}

	@Override
	public PageResult<MediaFloor> listMediaFloors(int pageNo, int pageSize) {
		return listMediaFloors(pageNo, pageSize, null);
	}

	@Override
	public PageResult<MediaFloor> listMediaFloors(int pageNo) {
		return listMediaFloors(pageNo, Constants.DEFAULT_PAGE_SIZE, null);
	}
}
