package com.szwcyq.ggw.share.fastdfs.proto.storage;

import java.util.Set;

import com.szwcyq.ggw.share.fastdfs.domain.MateData;
import com.szwcyq.ggw.share.fastdfs.proto.AbstractFdfsCommand;
import com.szwcyq.ggw.share.fastdfs.proto.FdfsResponse;
import com.szwcyq.ggw.share.fastdfs.proto.storage.enums.StorageMetdataSetType;
import com.szwcyq.ggw.share.fastdfs.proto.storage.internal.StorageSetMetadataRequest;

/**
 * 设置文件标签
 * 
 * @author tobato
 *
 */
public class StorageSetMetadataCommand extends AbstractFdfsCommand<Void> {

    /**
     * 设置文件标签(元数据)
     * 
     * @param groupName
     * @param path
     * @param metaDataSet
     * @param type
     */
    public StorageSetMetadataCommand(String groupName, String path, Set<MateData> metaDataSet,
            StorageMetdataSetType type) {
        this.request = new StorageSetMetadataRequest(groupName, path, metaDataSet, type);
        // 输出响应
        this.response = new FdfsResponse<Void>() {
            // default response
        };
    }

}
