package com.framework.admin.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.framework.admin.annotation.SysLog;
import com.framework.admin.entity.FwImageEntity;
import com.framework.admin.service.FwImageService;
import com.framework.admin.util.DictUtil;
import com.framework.admin.util.SysConfigRedis;
import com.framework.common.util.result.Result;
/**
 * 图库表
 *
 * @author hanyl
 * @date 2019-06-22 21:57:09
 */
@Controller
@RequestMapping("admin/system/fwImage")
public class FwImageController {

	@Autowired
	private FwImageService fwImageService;

	/**
	 * 本地测试上传地址
	 */
	private static final String UPLOAD_PATH="F:\\springBootdemo\\upload\\";
	
	@PostMapping(value = "list")
	@ResponseBody
	public Result list( HttpServletRequest request ,@RequestParam Integer page,@RequestParam Integer limit) {

		return fwImageService.getImagePage(page, limit, request);
	}
	@SysLog("添加配置")
	@PostMapping(value = "add")
	@ResponseBody
	public Result save(FwImageEntity  fwImage) {
        fwImageService.save(fwImage);
        return Result.success();

	}
	@SysLog("修改配置")
	@PostMapping(value = "edit")
	@ResponseBody
	public Result edit(FwImageEntity fwImage,@RequestParam Long uid) {
        FwImageEntity updateEntity=fwImageService.get(uid);
	    updateEntity.setLocalUrl(fwImage.getLocalUrl())	;
	    updateEntity.setCloudUrl(fwImage.getCloudUrl())	;
	    updateEntity.setRemark(fwImage.getRemark())	;
        fwImageService.update(updateEntity);
        return Result.success();

	}
	@SysLog("删除配置")
	@GetMapping(value = "del")
	@ResponseBody
	public Result del( @RequestParam(value = "id") Long id) {
		FwImageEntity  delEntity =fwImageService.findById(id);
		if(null!=delEntity) {
			fwImageService.delete(delEntity);
		}
		return Result.success();

	}
	
//=============================================================
	
	@GetMapping(value = "list")
	public String list(ModelMap modelMap) {
		return "admin/fwImage/fwImageList";
	}
	@GetMapping(value = "add")
	public String add(ModelMap modelMap) {
		return "admin/fwImage/fwImageAdd";
	}
	@GetMapping(value = "edit")
	public String edit(ModelMap modelMap,@RequestParam Long id) {
		modelMap.put("fwImage", fwImageService.get(id));
		return "admin/fwImage/fwImageEdit";
	}

//	@SysLog("上传图片")
	@PostMapping(value = "upload")
	@ResponseBody
	public Result upload(@RequestParam("file") MultipartFile file,String imageType,String remark,Integer type) {
		Result r = Result.success();
	try {
		String msg = null;
	     if (file.isEmpty()) {
	    	 msg= "上传失败，请选择文件";
	        }
        String fileName = getFileNewName(file.getOriginalFilename());
        String filePath = UPLOAD_PATH;
        File dest = new File(filePath + fileName);
      
            file.transferTo(dest);
            FwImageEntity  fwImage=new FwImageEntity();
            fwImage.setLocalUrl(fileName);
            fwImage.setType(imageType);
            fwImageService.save(fwImage);
//            if("22222.png".equals(file.getOriginalFilename())) {
//            	 responseEntity.setAny("code", "1");
//            }else {
//            	responseEntity.setAny("code", "0");
//            }
        	r.setAny("code", "0");
        	r.setAny("fileName", fileName);
        	r.setAny("img_prefix", SysConfigRedis.getConfigByKey("img_prefix") );
        } catch (IOException e) {
        	e.printStackTrace();
        }
		return r;

	}
	
	@PostMapping(value = "editorUpload")
	@ResponseBody
	public Result editorUpload(@RequestParam("file") MultipartFile file,String imageType,String remark) {
		Result r = Result.success();
	try {
	     if (file.isEmpty()) {
	    	return Result.failure("请选择文件");
	        }
        String fileName = getFileNewName(file.getOriginalFilename());
        String filePath = UPLOAD_PATH;
        File dest = new File(filePath + fileName);
      
            file.transferTo(dest);
            FwImageEntity  fwImage=new FwImageEntity();
            fwImage.setLocalUrl(fileName);
            fwImage.setType(imageType);
            fwImageService.save(fwImage);
        	r.setAny("code", "0");
        	r.setAny("fileName", fileName);
        	r.setAny("img_prefix", SysConfigRedis.getConfigByKey("img_prefix") );
        } catch (IOException e) {
        	e.printStackTrace();
        }
		return r;

	}
	@GetMapping(value = "detail")
	public String detail(ModelMap modelMap,@RequestParam Long id) {
		FwImageEntity model=fwImageService.get(id);
		modelMap.put("fwImage",model );
		modelMap.put("img_prefix",SysConfigRedis.getConfigByKey("img_prefix") );
		modelMap.put("typeName",DictUtil.keyValue("IMAGE_STATUS",model.getType()) );
		return "admin/fwImage/fwImageDetail";
	}

	public static String getFileNewName(String fileName) {
		 String postfix = fileName.substring(fileName.lastIndexOf('.'));
		return System.currentTimeMillis()+postfix;
	}
	
	@GetMapping(value = "dialog")
	public String dialog(ModelMap modelMap) {
		return "admin/fwImage/dialog";
	}
}
 