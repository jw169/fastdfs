FastDFS-Client

##介绍
作者：huangjianwei
在原作者YuQing与yuqih发布的java客户端基础上进行了大量重构工作，便于Java工作者开发。

文件结构说明：
doc：fastdfs安装教程和API使用教程
projects：fastdfs项目开发的连接器和基本使用的API
common-fastdfs：fastdfs基本使用公共工程
，fastdfs-web-demo：简单的使用演示

*************************项目使用说明书****************************************
环境要求：jdk1.8，maven-3.3.9

1.项目由maven构建，采用spring Boot框架
2.导入common-fastdfs和fastdfs-web-demo两个项目
温馨提示：common-fastdfs是个jar工程，里面封装了fastdfs基本连接器和工具类，fastdfs-web-demo是个web功能，简单的使用演示(包括上传和下载)
3.启动fastdfs-web-demo项目的fastdfswebapp.java类
4.访问地址：http://client.com:8100/

*************************结束****************************************

主要特性

1. 对关键部分代码加入了单元测试，便于理解与服务端的接口交易，提高接口质量
2. 将以前对byte硬解析风格重构为使用 对象+注解 的形式，尽量增强了代码的可读性
3. 支持对服务端的连接池管理(commons-pool2)
4. 支持上传图片时候检查图片格式，并且自动生成缩略图
5. 支持防盗链机智（token）

##运行环境要求

由于笔者主要工作环境是SpringBoot，因此目前客户端主要依赖于SpringBoot

    	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.2.3.RELEASE</version>
	</parent>
    
JDK环境要求  1.8

##单元测试

由于工作时间关系与解析原代码的复杂性，单元测试无法完全做到脱离FastDFS服务端，请见谅。

执行单元测试需要配置TestConstants文件当中参数

在Tracker与Storage都在一个机器的环境下

      private static String ip_home = "192.168.1.105";
      public static InetSocketAddress address = new InetSocketAddress(ip_home, FdfsMockSocketServer.PORT);
      public static InetSocketAddress store_address = new InetSocketAddress(ip_home, FdfsMockSocketServer.STORE_PORT);
      
      public static final String DEFAULT_STORAGE_IP = ip_home;
  
      
在Tracker与Storage不在一个机器的环境下      
     
    private static String ip_work = "192.168.174.47";
    private static String ip_work_store = "192.168.174.49";
    public static InetSocketAddress address = new InetSocketAddress(ip_work, FdfsMockSocketServer.PORT);
    public static InetSocketAddress store_address = new InetSocketAddress(ip_work_store, FdfsMockSocketServer.STORE_PORT);
    
    public static final String DEFAULT_STORAGE_IP = ip_work_store;
   

##FastDFS-Client使用方式

###1.在项目Pom当中加入依赖

Maven依赖为

    <groupId>com.szwcyq.ggw.share</groupId>
    <artifactId>ggw-share-fastdfs-common</artifactId>
    <version>12.0.0-SNAPSHOT</version>

###2.将Fdfs配置引入项目

将FastDFS-Client客户端引入本地化项目的方式非常简单，在SpringBoot项目当中

    /**
     * 导入FastDFS-Client组件
     * 
     * @author tobato
     *
     */
    @Configuration
    @Import(FdfsClientConfig.class)
    public class ComponetImport {
        // 导入依赖组件
    }
    
是的，只需要一行注解 @Import(FdfsClientConfig.class)

###3.在application.yml当中配置Fdfs相关参数
    # ===================================================================
    # 分布式文件系统FDFS配置
    # ===================================================================
    fdfs:
      soTimeout: 1501
      connectTimeout: 601 
      thumbImage:             #缩略图生成参数
        width: 150
        height: 150
      trackerList:            #TrackerList参数,支持多个
        - 192.168.1.105:22122
        - 192.168.1.106:22122 

###4.使用接口服务对Fdfs服务端进行操作

主要接口包括

1. TrackerClient - TrackerServer接口 
2. GenerateStorageClient - 一般文件存储接口 (StorageServer接口)
3. FastFileStorageClient - 为方便项目开发集成的简单接口(StorageServer接口)
4. AppendFileStorageClient - 支持文件续传操作的接口 (StorageServer接口)
5. FastDFSClient - 简易版上传（FastDFSClient接口）



