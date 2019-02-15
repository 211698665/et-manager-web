package cn.henu.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.henu.common.pojo.EasyUIDataGridResult;
import cn.henu.common.utils.EtResult;
import cn.henu.pojo.TbItem;
import cn.henu.pojo.TbItemDesc;
import cn.henu.service.ItemService;

/**
 * 商品管理的controller
 * @author syw
 *
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	//测试根据商品id进行商品查询
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		//通过网络传输必须进行序列化
		TbItem item = itemService.getItemById(itemId);
		return item;
	}
	
	@RequestMapping("item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page,Integer rows) {
		//注意这里的参数名称要和请求的url中的参数的名称一致
		EasyUIDataGridResult list = itemService.getItemList(page, rows);
		return list;
	}
	/**
	 * 商品添加功能
	 */
	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	public EtResult addItem(TbItem item,String desc) {
		EtResult result = itemService.addItem(item, desc);
		return result;
	}
	/**
	 * 异步重新加载回显描述
	 * @param id
	 * @return
	 *//*
	@RequestMapping("/rest/item/query/item/desc/{id}")
	@ResponseBody
	public TbItemDesc selectTbItemDesc(@PathVariable long id){
	    TbItemDesc itemDesc= itemService.selectTbItemDesc(id);
	    return itemDesc;
	}

	*//**
	 * 异步重新加载商品信息
	 * @param id
	 * @return
	 *//*
	@RequestMapping("/rest/item/param/item/query/{id}")
	@ResponseBody
	public TbItem queryById(@PathVariable long id){
	    TbItem item = itemService.getItemById(id);
	    return item;
	}*/

	
	
	
	/**
	 * 获取修改商品，商品描述信息获取，回显
	 */
	/*@RequestMapping("/item/desc/{itemId}") // /item/123
	@ResponseBody// 把对象转成json
	public TbItemDesc getItemDescById(@PathVariable Long itemId) {
		// 根据商品的id查询商品的描述信息
		TbItemDesc tbItemDesc = itemService.getTbItemDescById(itemId);
		return tbItemDesc;
	}*/
	
	/**
	 * 更新商品到数据库
	 * @return
	 */
	@RequestMapping("/rest/item/update")
	@ResponseBody
	public EtResult getItemUpdate(TbItem tbItem,String desc){
		EtResult result = new EtResult();
		Date date = new Date();
		tbItem.setStatus((byte)1);
		tbItem.setUpdated(date);
		tbItem.setCreated(date);
		// 根据id去更新TbItem
		itemService.updateItem(tbItem);
		// 根据id去更新TbItemDesc
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(tbItem.getId());
		itemDesc.setUpdated(date);
		itemDesc.setItemDesc(desc);
		itemService.updateItemDesc(itemDesc);
		
		result.setStatus(200);
		return result;
	}
	
	/**
	 * 根据id删除商品
	 */
	@RequestMapping("/rest/item/delete")
	@ResponseBody
	public EtResult getItemDelete(long [] ids){
		EtResult result = new EtResult();
		// 根据ids删除商品
		itemService.batchDeleteItem(ids);
		// 根据ids删除商品描述
		itemService.batchDeleteItemDesc(ids);
		result.setStatus(200);
 		return EtResult.ok();
	}
}
