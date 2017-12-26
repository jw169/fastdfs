package com.szwcyq.ggw.share.fastdfs.utils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.szwcyq.ggw.share.fastdfs.common.MyException;
import com.szwcyq.ggw.share.fastdfs.conn.FdfsWebServer;
import com.szwcyq.ggw.share.fastdfs.domain.StorePath;
import com.szwcyq.ggw.share.fastdfs.fastdfs.ProtoCommon;

/**
 * 工具类
 * @author huangjianwei
 *
 */
@Component
public class FastDFSUtil {

    public static final String DEFAULT_GROUP1 = "group1";
    public static final String DEFAULT_GROUP2 = "group2";
    public static final String DEFAULT_GROUP3 = "group3";
    public static final String DEFAULT_GROUP4 = "group4";
    public static final String DEFAULT_GROUP5 = "group5";
    public static final String DEFAULT_GROUP6 = "group6";
    
    @Autowired
    private FdfsWebServer fdfsWebServer;
    
   // private String secret_key;
   
    /*public String getPrefixName() {
        if (cachedPrefixName == null) {
            StringBuilder buffer = new StringBuilder();
            buffer.append("_").append(width).append("x").append(height);
            cachedPrefixName = new String(buffer);
        }
        return cachedPrefixName;
    }*/
    

    /**
     * 根据path获取图片访问权限路径
     * @param path
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws MyException
     */
    public  String getUrlPermissions(String path) throws UnsupportedEncodingException, NoSuchAlgorithmException, MyException{
    	StorePath storePath = StorePath.praseFromUrl(path);
    	int ts = (int)(System.currentTimeMillis()/1000); 
		ts=ts+100;
    	String token = ProtoCommon.getToken(storePath.getPath(),ts,fdfsWebServer.getSecret_key());
    	return path+"?ts="+ts+"&token="+token;
    }
    
    

    /**
     * 根据文件名获取缩略图路径
     */
    public static String getThumbImagePath(String masterFilename) {
        Validate.notBlank(masterFilename, "主文件不能为空");
        StringBuilder buff = new StringBuilder(masterFilename);
        int index = buff.lastIndexOf(".");
        buff.insert(index, "_150x150");
        return buff.toString();
    }

    

}
