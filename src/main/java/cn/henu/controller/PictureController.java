package cn.henu.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.henu.common.utils.FastDFSClient;
import cn.henu.common.utils.JsonUtils;

/**
 * 图片上传处理的controller
 * @author syw
 *
 */
@Controller
public class PictureController {
	//取出配置文件的图片服务器中的地址
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
	@RequestMapping(value="/pic/upload", produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String uploadFile(MultipartFile uploadFile) {
		
		try {
			//把图片上传到图片服务器
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
			//获取文件的扩展名
			String filename = uploadFile.getOriginalFilename();
			String extName=filename.substring(filename.lastIndexOf(".")+1);
			//得到文件的地址和文件名
			String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
			//补充为完整的url
			url=IMAGE_SERVER_URL+url;
			System.out.println(url);
			//封装到Map中返回
			Map map = new HashMap<>();
			map.put("error", 0);
			map.put("url", url);
			return JsonUtils.objectToJson(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map map = new HashMap<>();
			map.put("error", 1);
			map.put("message", "图片上传失败");
			return JsonUtils.objectToJson(map);
		}
		
		
	}
}
