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
import ai.houyi.openssp.core.service.MediaService;
import ai.houyi.openssp.mapper.MediaMapper;
import ai.houyi.openssp.model.Media;
import ai.houyi.openssp.model.example.MediaExample;

/**
 * 
 * @author wangwp
 */
@Service
public class MediaServiceImpl implements MediaService {
	@Autowired
	private MediaMapper mediaMapper;

	@Override
	public void saveOrUpdateMedia(Media media) {
		mediaMapper.insertSelective(media);
	}

	@Override
	public void deleteMedia(int mediaId) {
		mediaMapper.deleteByPrimaryKey(mediaId);
	}

	@Override
	public Media loadMedia(int mediaId) {
		return mediaMapper.selectByPrimaryKey(mediaId);
	}

	@Override
	public PageResult<Media> listMedias(int pageNo, int pageSize, MediaExample _example) {
		MediaExample example = _example == null ? new MediaExample() : _example;
		long total = mediaMapper.countByExample(example);
		example.limit((pageNo - 1) * pageSize, pageSize);

		List<Media> dataList = mediaMapper.selectByExample(example);

		PageResult.Builder<Media> builder = PageResult.builder();
		builder.withDataList(dataList).withPageNo(pageNo).withPageSize(pageSize).withTotal((int) total);

		return builder.build();
	}

	@Override
	public PageResult<Media> listMedias(int pageNo) {
		return listMedias(pageNo, Constants.DEFAULT_PAGE_SIZE, null);
	}

	@Override
	public PageResult<Media> listMedias(int pageNo, int pageSize) {
		return listMedias(pageNo, pageSize, null);
	}

}
