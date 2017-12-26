package com.szwcyq.ggw.share.platform;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

import com.szwcyq.ggw.share.fastdfs.FdfsClientConfig;

/**
 * 
 * @author huangjianwei
 *
 */

@SpringBootApplication
@Import(FdfsClientConfig.class)
public class FastDFSWebapp {
	public static void main(String[] args){
		new SpringApplicationBuilder()
				.sources(FastDFSWebapp.class)
				.web(true)
				.run(args);
	}
}
