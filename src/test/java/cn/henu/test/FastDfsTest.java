package cn.henu.test;

import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import cn.henu.common.utils.FastDFSClient;

public class FastDfsTest {

	//测试图片服务器
	@Test
	public void testUpload() throws Exception, IOException, MyException {
		//创建一个配置文件,内容就是tracker服务器的地址
		//使用全局对象，加载配置文件
		ClientGlobal.init("C:/javaspace/et-manager-web/src/main/resources/conf/client.conf");
		//创建trackerClient	对象
		TrackerClient trackerClient = new TrackerClient();
		//获得trackerServer
		TrackerServer trackerServer = trackerClient.getConnection();
		//创建一个storageServer的引用
		StorageServer storageServer=null;
		//创建一个storageClient参数需要使用trackerServer和storageServer
		StorageClient storageClient=new StorageClient(trackerServer,storageServer);
		//使用storageClient上传文件.注意复制的时候路径可能会出现隐藏字符，所以要手打
		String[] strings = storageClient.upload_file("C:/IMG/322286.jpg", "jpg", null);
		for (String str : strings) {
			System.out.println(str);
		}
	}
	
	//利用工具类的方式测试图片上传的功能
	@Test
	public void testFastDfsClient() throws Exception{
		FastDFSClient fastDFSClient = new FastDFSClient("C:/javaspace/et-manager-web/src/main/resources/conf/client.conf");
		String file = fastDFSClient.uploadFile("C:/IMG/306761.jpg");
		System.out.println(file);
	}
}
