package com.szwcyq.ggw.share.fastdfs.service;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.szwcyq.ggw.share.fastdfs.conn.FdfsWebServer;
import com.szwcyq.ggw.share.fastdfs.domain.StorePath;
import com.szwcyq.ggw.share.fastdfs.exception.FdfsUnsupportStorePathException;
import com.szwcyq.ggw.share.fastdfs.proto.storage.DownloadByteArray;
/**
 * 简易版fastDFS使用
 * @author huangjianwei
 *
 */
@Component
public class FastDFSClient {

	 private final Logger logger = LoggerFactory.getLogger(FastDFSClient.class);

	    @Autowired
	    private FastFileStorageClient storageClient;

	    @Autowired
	    private FdfsWebServer fdfsWebServer;

	    /**
	     * 上传文件
	     * @param file 文件对象
	     * @return 文件访问地址
	     * @throws IOException
	     */
	    public String uploadFile(MultipartFile file) throws IOException {
	        StorePath storePath = storageClient.uploadFile(file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
	        return getResAccessUrl(storePath);
	    }

	    /**
	     * 上传文件
	     * @param file 文件对象
	     * @return 文件访问地址
	     * @throws IOException
	     */
	    public String uploadFile(File file) throws IOException {
	        FileInputStream inputStream = new FileInputStream (file);
	        StorePath storePath = storageClient.uploadFile(inputStream,file.length(), FilenameUtils.getExtension(file.getName()),null);
	        return getResAccessUrl(storePath);
	    }

	    /**
	     * 将一段字符串生成一个文件上传
	     * @param content 文件内容
	     * @param fileExtension
	     * @return
	     */
	    public String uploadFile(String content, String fileExtension) {
	        byte[] buff = content.getBytes(Charset.forName("UTF-8"));
	        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
	        StorePath storePath = storageClient.uploadFile(stream,buff.length, fileExtension,null);
	        return getResAccessUrl(storePath);
	    }

	    // 封装图片完整URL地址
	    private String getResAccessUrl(StorePath storePath) {
	        String fileUrl = fdfsWebServer.getWebServerUrl() + storePath.getFullPath();
	        return fileUrl;
	    }

	    /**
	     * 删除文件
	     * @param fileUrl 文件访问地址
	     * @return
	     */
	    public void deleteFile(String fileUrl) {
	        if (StringUtils.isEmpty(fileUrl)) {
	            return;
	        }
	        try {
	            StorePath storePath = StorePath.praseFromUrl(fileUrl);
	            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
	        } catch (FdfsUnsupportStorePathException e) {
	            logger.warn(e.getMessage());
	        }
	    }
	    
	    /**
	     * 上传图片并且生成缩略图
	     * @param file 文件对象
	     * @return 文件访问地址
	     * @throws IOException
	     */
	    public String uploadImageAndCrtThumbImage(File file) throws IOException {
	        FileInputStream inputStream = new FileInputStream (file);
	        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(inputStream,file.length(), FilenameUtils.getExtension(file.getName()),null);
	        return getResAccessUrl(storePath);
	    }
	    
	    /**
	     * 上传图片并且生成缩略图（自定义大小）
	     */
	    public String uploadImageAndCrtThumbImage(File file,int width,int height) throws IOException {
	        FileInputStream inputStream = new FileInputStream (file);
	        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(inputStream,file.length(), FilenameUtils.getExtension(file.getName()),null,width,height);
	        return getResAccessUrl(storePath);
	    }
	    
	    
	    /**
	     * 下载整个文件
	     * 
	     * @param groupName
	     * @param path
	     * @param callback
	     * @return
	     */
	    public byte[] downloadFile(String fileUrl) throws IOException {
	    	
	    	if (StringUtils.isEmpty(fileUrl)) {
	            return null ;
	        }
	        try {
	        	DownloadByteArray callback = new DownloadByteArray();
	            StorePath storePath = StorePath.praseFromUrl(fileUrl);
	            byte[] downloadFile = storageClient.downloadFile(storePath.getGroup(), storePath.getPath(),callback);
	            return downloadFile;
	        } catch (FdfsUnsupportStorePathException e) {
	            logger.warn(e.getMessage());
	        }
	        return null ;
	    	
	      
	    }
}
