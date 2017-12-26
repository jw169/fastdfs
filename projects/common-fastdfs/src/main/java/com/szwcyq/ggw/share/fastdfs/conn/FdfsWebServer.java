package com.szwcyq.ggw.share.fastdfs.conn;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.szwcyq.ggw.share.fastdfs.FdfsClientConstants;

/**
 * 表示文件Web服务器对象
 * 
 * <pre>
 * 由Nginx服务器承担此角色，通常配置以后就不会再改变
 * </pre>
 * 
 * @author tobato
 *
 */
@Component
@ConfigurationProperties(prefix = FdfsClientConstants.ROOT_CONFIG_PREFIX)
public class FdfsWebServer {

    private String webServerUrl;
    
    private String secret_key;

    public String getWebServerUrl() {
        return webServerUrl;
    }

    public void setWebServerUrl(String webServerUrl) {
        this.webServerUrl = webServerUrl;
    }

	public String getSecret_key() {
		return secret_key;
	}

	public void setSecret_key(String secret_key) {
		this.secret_key = secret_key;
	}
    
    

}
