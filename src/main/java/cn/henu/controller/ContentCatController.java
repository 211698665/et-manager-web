package cn.henu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.henu.common.pojo.EasyUITreeNode;
import cn.henu.common.utils.EtResult;
import cn.henu.content.service.ContentCategoryService;

/**
 * 内容分类管理controller
 * @author syw
 *
 */
@Controller
public class ContentCatController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(name="id",defaultValue="0")Long parentId){
		List<EasyUITreeNode> list = contentCategoryService.getContentCatList(parentId);
		return list;
	}
	
	//添加分类的节点
	@RequestMapping(value="/content/category/create", method=RequestMethod.POST)
	@ResponseBody
	public EtResult createContentCategory(Long parentId,String name) {
		
		EtResult etResult = contentCategoryService.addContentCategory(parentId, name);
		return etResult;
	}
	
	//修改内容分类
	@RequestMapping("/content/category/update")
	@ResponseBody
	public EtResult updateNode(Long id,String name ){
		return contentCategoryService.updateNode(id, name);
	}
	//删除分类
	@RequestMapping("/content/category/delete/")
	@ResponseBody
	public EtResult  deleteNode(Long id){
		int res = contentCategoryService.deleteNode(id);
		return EtResult.ok(res);
	}
	
}
