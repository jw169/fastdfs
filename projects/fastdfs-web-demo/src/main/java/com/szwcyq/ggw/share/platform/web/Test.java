package com.szwcyq.ggw.share.platform.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.szwcyq.ggw.share.fastdfs.conn.FdfsWebServer;
import com.szwcyq.ggw.share.fastdfs.service.FastDFSClient;
import com.szwcyq.ggw.share.fastdfs.utils.FastDFSUtil;
/**
 * 
 * @author huangjianwei
 *
 */
@RestController
@RequestMapping("/test")
public class Test {
	
	@Autowired
	private FastDFSClient fastDFSClient;
	
	@Autowired
    private FdfsWebServer fdfsWebServer;
	
	@Autowired
	private FastDFSUtil fastDFSUtil;
	
	/** 日志 */
	protected static Logger log = LoggerFactory.getLogger(FastDFSClientController.class);
	/**
	 * 上传图片
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/uploadFile")
	public String uploadFile(MultipartFile m){
		String uploadFile="";
		try {		
			uploadFile = fastDFSClient.uploadFile(m);
			log.info("---返回图片的路径"+uploadFile);
			//获得图片能够访问的url
			uploadFile=fastDFSUtil.getUrlPermissions(uploadFile);
			log.info("---访问图片的URL：{}",uploadFile);
			
		} catch (Exception e) {
			log.error("--异常：{}",e);
			return "上传图片出现异常";
		}
		return "上传图片成功：图片URL："+uploadFile;
	}
	

}
