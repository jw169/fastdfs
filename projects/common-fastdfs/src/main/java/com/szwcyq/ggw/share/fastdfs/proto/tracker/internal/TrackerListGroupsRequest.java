package com.szwcyq.ggw.share.fastdfs.proto.tracker.internal;

import com.szwcyq.ggw.share.fastdfs.proto.CmdConstants;
import com.szwcyq.ggw.share.fastdfs.proto.FdfsRequest;
import com.szwcyq.ggw.share.fastdfs.proto.ProtoHead;

/**
 * 列出分组命令
 * 
 * @author tobato
 *
 */
public class TrackerListGroupsRequest extends FdfsRequest {

    public TrackerListGroupsRequest() {
        head = new ProtoHead(CmdConstants.TRACKER_PROTO_CMD_SERVER_LIST_GROUP);
    }
}
