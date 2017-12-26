package com.szwcyq.ggw.share.fastdfs.proto.tracker;

import java.util.List;

import com.szwcyq.ggw.share.fastdfs.domain.StorageState;
import com.szwcyq.ggw.share.fastdfs.proto.AbstractFdfsCommand;
import com.szwcyq.ggw.share.fastdfs.proto.tracker.internal.TrackerListStoragesRequest;
import com.szwcyq.ggw.share.fastdfs.proto.tracker.internal.TrackerListStoragesResponse;

/**
 * 列出组命令
 * 
 * @author tobato
 *
 */
public class TrackerListStoragesCommand extends AbstractFdfsCommand<List<StorageState>> {

    public TrackerListStoragesCommand(String groupName, String storageIpAddr) {
        super.request = new TrackerListStoragesRequest(groupName, storageIpAddr);
        super.response = new TrackerListStoragesResponse();
    }

    public TrackerListStoragesCommand(String groupName) {
        super.request = new TrackerListStoragesRequest(groupName);
        super.response = new TrackerListStoragesResponse();
    }

}
