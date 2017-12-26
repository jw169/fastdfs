package com.szwcyq.ggw.share.platform.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szwcyq.ggw.share.fastdfs.domain.ThumbImageConfig;
import com.szwcyq.ggw.share.fastdfs.proto.storage.DownloadByteArray;
import com.szwcyq.ggw.share.fastdfs.service.FastFileStorageClient;
/**
 * 
 * @author huangjianwei
 *
 */
@RestController
@RequestMapping("/FastDfs")
public class FastDfsController {

	@Autowired
	protected FastFileStorageClient storageClient;

	@Autowired
	private ThumbImageConfig thumbImageConfig;

	/** 日志 */
	protected static Logger log = LoggerFactory
			.getLogger(FastDfsController.class);

	/**
	 * 删除
	 * 
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(String url) {

		try {
			log.info("---url:{}" + url);
			storageClient.deleteFile(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "DELETE!";
	}

	/**
	 * 下载图片
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/downloaFile")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		InputStream in = null;
		OutputStream out = null;
		try {

			// 获得请求文件名
			String filename = UUID.randomUUID().toString() + ".jpg";

			// 设置文件MIME类型
			response.setContentType(request.getServletContext().getMimeType(
					filename));
			// 设置Content-Disposition
			response.setHeader("Content-Disposition", "attachment;filename="
					+ filename);

			// 读取文件
			DownloadByteArray callback = new DownloadByteArray();
			byte[] content = storageClient.downloadFile("group1",
					"M00/00/00/wKhdhFo8zFGAdEhsAAHYvQQn-YE569.jpg", callback);
			in = new ByteArrayInputStream(content);
			out = response.getOutputStream();
			// 写文件
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
		} catch (Exception e) {
			log.info("---异常：{}", e);
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}

	}

}
