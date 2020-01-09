package com.framework.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.admin.service.FwSysLogService;
import com.framework.common.util.result.Result;

@Controller
@RequestMapping("admin/system/syslog")
public class FwSysLogController {

	@Autowired
	private FwSysLogService fwSysLogService;
	
	@GetMapping(value = "list")
	public String list() {
		return "admin/syslog/syslogList";
	}
	
	@PostMapping(value = "list")
	@ResponseBody
	public Result list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit, HttpServletRequest request) {
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			return fwSysLogService.getSysLogPage(page, limit, request);
	}
}
