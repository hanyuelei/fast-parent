package com.framework.admin.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.admin.annotation.SysLog;
import com.framework.admin.entity.FwNoticeEntity;
import com.framework.admin.service.FwNoticeService;
import com.framework.common.util.result.Result;
/**
 * 
* @ClassName: FwNoticeController  
* @Description: TODO(公告控制类)  
* @author hanyl
* @date 2019年6月18日 下午5:32:01  
*
 */
@Controller
@RequestMapping("admin/system/notice")
public class FwNoticeController {

	public static final Integer OPNE_STATUS=1;
	@Autowired
	private FwNoticeService fwNoticeService;

	
	@PostMapping(value = "list")
	@ResponseBody
	public Result list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit, HttpServletRequest request) {
		
		return fwNoticeService.getNoticePage(page, limit, request);
	}
	@SysLog("添加公告")
	@PostMapping(value = "add")
	@ResponseBody
	public Result save(FwNoticeEntity  notice) {
        fwNoticeService.save(notice);
		return Result.success();

	}
	@SysLog("修改公告")
	@PostMapping(value = "edit")
	@ResponseBody
	public Result edit(FwNoticeEntity  notice,@RequestParam Long nid) {
        FwNoticeEntity fwNoticeEntity=fwNoticeService.get(nid);
        fwNoticeEntity.setTitle(notice.getTitle());
        fwNoticeEntity.setContent(notice.getContent());
        fwNoticeEntity.setTime(notice.getTime());
        fwNoticeEntity.setStatus(notice.getStatus());
        fwNoticeService.update(fwNoticeEntity);
		return Result.success();

	}
	@SysLog("删除公告")
	@GetMapping(value = "del")
	@ResponseBody
	public Result del( @RequestParam(value = "id") Long id) {
		FwNoticeEntity entity= fwNoticeService.get(id);
		if(null!=entity){
			fwNoticeService.delete(entity);
		}
		return Result.success();

	}
	/**
	 * 
	* @Title: getNotice  
	* @Description: TODO( 获取最后一条开启的公告 用来前台提示)  
	* @param @return    设定文件  
	* @return ResponseEntity    返回类型  
	* @throws
	 */
	@PostMapping(value = "getNotice")
	@ResponseBody
	public Result getNotice() {
		Result r = Result.success();
        FwNoticeEntity fwNoticeEntity=fwNoticeService.getNoticeByStatus(OPNE_STATUS);
        r.setAny("data", fwNoticeEntity);
        return r;

	}
	/**
	 * 公告列表跳转
	 */
	@GetMapping(value = "list")
	public String list() {
		return "admin/notice/noticeList";
	}
	/**
	 * 公告添加跳转
	 */
	@GetMapping(value = "add")
	public String add() {
		return "admin/notice/noticeAdd";
	}
	/**
	 * 公告修改跳转
	 */
	@GetMapping(value = "edit")
	public String edit(ModelMap modelMap,@RequestParam Long id) {
		modelMap.put("notice", fwNoticeService.get(id));
		return "admin/notice/noticeEdit";
	}
	/**
	 * 公告详情
	 */
	@GetMapping(value = "detail")
	public String detail(ModelMap modelMap,@RequestParam Long id) {
		modelMap.put("notice", fwNoticeService.get(id));
		return "admin/notice/noticeDetail";
	}
}
