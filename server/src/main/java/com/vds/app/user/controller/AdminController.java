package com.vds.app.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v2/open/admin")
public class AdminController {

	@RequestMapping("index")
	public String index() {

		return "views/admin/index/index";
	}

	@RequestMapping("login")
	public String login() {

		return "views/admin/login";
	}
}
