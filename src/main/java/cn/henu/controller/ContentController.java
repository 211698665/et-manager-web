package cn.henu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 内容管理的controller
 * @author syw
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.henu.common.pojo.EasyUIDataGridResult;
import cn.henu.common.utils.EtResult;
import cn.henu.content.service.ContentService;
import cn.henu.pojo.TbContent;

@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping(value="/content/save",method=RequestMethod.POST)//不写的话get和post方式都能够访问到,写的话只能通过指定的方式访问
	@ResponseBody
	public EtResult addContent(TbContent content) {
		//把内容数据保存到数据库
		EtResult result = contentService.addContent(content);
		return result;
	}
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUIDataGridResult getContentList(Integer page,Integer rows,long categoryId){
	    EasyUIDataGridResult contentList = contentService.getContentList(page, rows,categoryId);
		return contentList;
	}
}
