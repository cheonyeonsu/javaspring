package com.shopmax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {
	@GetMapping(value="/item/shop")
	public String itemShopList() {
		return "item/itemShopList";
	}
	
	//상품 등록 페이지
	@GetMapping(value="/admin/item/new")
	public String itemForm() {
		return "item/itemForm";
	}
	
	
	//상품 관리 페이지
	@GetMapping(value="/admin/items")
	public String itemManage() {
		return "item/itemMng";
	}
}
