package cn.henu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.henu.common.utils.EtResult;
import cn.henu.search.service.SearchItemService;

/**
 * 导入商品数据道索引库
 * @author syw
 *
 */
@Controller
public class SearchItemController {
	@Autowired
	private SearchItemService searchItemService;

	@RequestMapping("/index/item/import")
	@ResponseBody
	public EtResult importItemList() {
		EtResult result = searchItemService.importAllItem();
		return result;
	}
}
