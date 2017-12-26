package com.szwcyq.ggw.share.fastdfs.proto.tracker;

import java.util.List;

import com.szwcyq.ggw.share.fastdfs.domain.GroupState;
import com.szwcyq.ggw.share.fastdfs.proto.AbstractFdfsCommand;
import com.szwcyq.ggw.share.fastdfs.proto.tracker.internal.TrackerListGroupsRequest;
import com.szwcyq.ggw.share.fastdfs.proto.tracker.internal.TrackerListGroupsResponse;

/**
 * 列出组命令
 * 
 * @author tobato
 *
 */
public class TrackerListGroupsCommand extends AbstractFdfsCommand<List<GroupState>> {

    public TrackerListGroupsCommand() {
        super.request = new TrackerListGroupsRequest();
        super.response = new TrackerListGroupsResponse();
    }

}
