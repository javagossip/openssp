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
package ai.houyi.openssp.router;

import java.util.List;

import ai.houyi.openssp.router.model.Bidder;
import ai.houyi.openssp.rtb.proto.OpenRtb.BidRequest;
import ai.houyi.openssp.rtb.proto.OpenRtb.BidResponse;

/**
 * 
 * @author wangwp
 */
public abstract class BidderRouter {

	public final BidResponse bid(BidRequest bidRequest) {
		List<Bidder> bidders = getBidders(bidRequest);
		
		return null;
	}

	protected abstract List<Bidder> getBidders(BidRequest bidRequest);
}
