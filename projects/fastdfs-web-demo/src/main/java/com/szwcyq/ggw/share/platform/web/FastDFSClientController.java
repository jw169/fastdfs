package com.szwcyq.ggw.share.platform.web;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.szwcyq.ggw.share.fastdfs.conn.FdfsWebServer;
import com.szwcyq.ggw.share.fastdfs.domain.StorePath;
import com.szwcyq.ggw.share.fastdfs.fastdfs.ProtoCommon;
import com.szwcyq.ggw.share.fastdfs.service.FastDFSClient;
import com.szwcyq.ggw.share.fastdfs.utils.FastDFSUtil;
/**
 * 
 * @author huangjianwei
 *
 */
@RestController
@RequestMapping("/fastDFSClient")
public class FastDFSClientController {
	
	@Autowired
	private FastDFSClient fastDFSClient;
	
	@Autowired
    private FdfsWebServer fdfsWebServer;
	
	@Autowired
	private FastDFSUtil fastDFSUtil;
	
	/** 日志 */
	protected static Logger log = LoggerFactory.getLogger(FastDFSClientController.class);
	
	 /**
     * 上传文件
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */
	@RequestMapping(value = "/uploadFile")
	public String uploadFile(){
		String uploadFile="";
		try {
			File file=new File("F:/hjw/gaofutongworkspace/share/projects/web/web-fastdfs-base/src/main/resources/images/gs.jpg");
			
			//defaultGenerateStorageClient.setTrackerClientService(trackerClientService);
			uploadFile = fastDFSClient.uploadFile(file);
			
			log.info("---webServerUrl:"+fdfsWebServer.getWebServerUrl());
			log.info("---secret_key:"+fdfsWebServer.getSecret_key());
			
			//图片查看路径
			
			//String configFileName = "src/main/resources/fdfs_client.conf";  
			//ClientGlobal.init(configFileName);  
			
			StorePath storePath = StorePath.praseFromUrl(uploadFile);
			//以秒为单位  
			int ts = (int)(System.currentTimeMillis()/1000); 
			ts=ts+100;
			String token = ProtoCommon.getToken(storePath.getPath(),ts, "FastDFS1234567890");
			//log.info("--信息：FullPath：{}",storePath.getFullPath());
			//log.info("--信息：path：{}",storePath.getPath());
			//log.info("--信息：ts：{}",ts);
			//log.info("--信息：token：{}",token);
			
			//uploadFile=uploadFile+"?ts="+ts+"&token="+token;
			//获得图片能够访问的url
			uploadFile=fastDFSUtil.getUrlPermissions(uploadFile);
			//log.info("--获得key：{}",new FastDFSUtil().getSecret_key());
			log.info("---访问图片的URL：{}",uploadFile);
			//SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
			//log.info("---时间:{}", dateformat.format(System.currentTimeMillis()));
		} catch (Exception e) {
			//log.error("--异常：{}",JacksonUtil.toJson(e));
			return "上传图片出现异常";
		}
		return "上传图片成功：图片URL："+uploadFile;
	}
	
	/**
	 * 删除文件
	 * @return
	 */
	@RequestMapping(value = "/deleteFile")
	public String deleteFile(@RequestParam(value="url",defaultValue="") String url){
		try {

			fastDFSClient.deleteFile(url);
			log.info("--删除图片成功：URL：{}",url);
		} catch (Exception e) {
			//log.error("--异常：{}",JacksonUtil.toJson(e));
			return "删除图片出现异常";
		}
		return "删除图片成功：图片URL："+url;
	}
	
	
	/**
     * 上传图片并且生成缩略图
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */
	@RequestMapping(value = "/uploadImageAndCrtThumbImage")
	public String uploadImageAndCrtThumbImage(){
		String uploadFile="";
		try {
			File file=new File("F:/hjw/gaofutongworkspace/share/projects/web/web-fastdfs-base/src/main/resources/images/gs.jpg");
			uploadFile = fastDFSClient.uploadImageAndCrtThumbImage(file);
			log.info("--上传图片成功：URL：{}",uploadFile);
			log.info("--缩略图：URL：{}",FastDFSUtil.getThumbImagePath(uploadFile));
		} catch (Exception e) {
			//log.error("--异常：{}",JacksonUtil.toJson(e));
			return "上传图片出现异常";
		}
		return "上传图片成功：图片URL："+uploadFile+"   :缩略图url："+FastDFSUtil.getThumbImagePath(uploadFile);
	}
	
	
	/**
	 * 下载
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/downloadFile")
	public String downloadFile(@RequestParam(value="url",defaultValue="") String url){
		byte[] downloadFile=null;
		try {
			
			downloadFile = fastDFSClient.downloadFile(url);
			log.info("--下载图片成功：URL：{}",downloadFile);
		} catch (Exception e) {
			//log.error("--异常：{}",JacksonUtil.toJson(e));
			return "下载图片出现异常";
		}
		return "下载图片成功：内容："+downloadFile;
	}
}
