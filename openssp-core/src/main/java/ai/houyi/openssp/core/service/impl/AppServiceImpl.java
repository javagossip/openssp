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
import ai.houyi.openssp.core.service.AppService;
import ai.houyi.openssp.mapper.AppMapper;
import ai.houyi.openssp.model.App;
import ai.houyi.openssp.model.example.AppExample;

/**
 * 
 * @author wangwp
 */
@Service
public class AppServiceImpl implements AppService {
	@Autowired
	private AppMapper appMapper;

	@Override
	public void saveOrUpdateApp(App app) {
		if (app.getId() == null) {
			appMapper.insertSelective(app);
		} else {
			appMapper.updateByPrimaryKeySelective(app);
		}
	}

	@Override
	public void deleteApp(int appId) {
		appMapper.deleteByPrimaryKey(appId);
	}

	@Override
	public App loadApp(int appId) {
		return appMapper.selectByPrimaryKey(appId);
	}

	@Override
	public PageResult<App> listApps(int pageNo, int pageSize, AppExample _example) {
		AppExample example = _example == null ? new AppExample() : _example;
		long total = appMapper.countByExample(example);
		example.limit((pageNo - 1) * pageSize, pageSize);

		List<App> dataList = appMapper.selectByExample(example);

		PageResult.Builder<App> builder = PageResult.builder();
		builder.withDataList(dataList).withPageNo(pageNo).withPageSize(pageSize).withTotal((int) total);

		return builder.build();
	}

	@Override
	public PageResult<App> listApps(int pageNo, int pageSize) {
		return listApps(pageNo, pageSize, null);
	}

	@Override
	public PageResult<App> listApps(int pageNo) {
		return listApps(pageNo, Constants.DEFAULT_PAGE_SIZE, null);
	}

}
