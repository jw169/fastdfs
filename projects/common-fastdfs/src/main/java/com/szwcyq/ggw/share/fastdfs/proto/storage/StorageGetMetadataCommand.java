package com.szwcyq.ggw.share.fastdfs.proto.storage;

import java.util.Set;

import com.szwcyq.ggw.share.fastdfs.domain.MateData;
import com.szwcyq.ggw.share.fastdfs.proto.AbstractFdfsCommand;
import com.szwcyq.ggw.share.fastdfs.proto.storage.internal.StorageGetMetadataRequest;
import com.szwcyq.ggw.share.fastdfs.proto.storage.internal.StorageGetMetadataResponse;

/**
 * 设置文件标签
 * 
 * @author tobato
 *
 */
public class StorageGetMetadataCommand extends AbstractFdfsCommand<Set<MateData>> {

    /**
     * 设置文件标签(元数据)
     * 
     * @param groupName
     * @param path
     * @param metaDataSet
     * @param type
     */
    public StorageGetMetadataCommand(String groupName, String path) {
        this.request = new StorageGetMetadataRequest(groupName, path);
        // 输出响应
        this.response = new StorageGetMetadataResponse();
    }

}
