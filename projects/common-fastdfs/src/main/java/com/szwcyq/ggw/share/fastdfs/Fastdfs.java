package com.szwcyq.ggw.share.fastdfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import(FdfsClientConfig.class)
public class Fastdfs {
	
	public static void main(String[] args) {
        SpringApplication.run(Fastdfs.class, args);
    }
	
}
