package com.simple.admin.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.simple.common.config.EnvPropertiesConfiger;
import com.simple.common.image.Thumbnailator;
import com.simple.common.util.AjaxWebUtil;
import com.simple.common.util.DateUtil;
import com.simple.common.util.FileUploadUtil;
import com.simple.common.util.ImageHandleUtil;
import com.simple.common.util.PrimaryKeyUtil;
import com.simple.constant.Constant;
import com.simple.fileencrypt.FileEncryptUtil;
@Controller
@RequestMapping(value = "/image")
public class ImageUploadController {
	
	private static final Logger log = LoggerFactory.getLogger(ImageUploadController.class);

	@RequestMapping(value="/uploadFile",method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile[] files,int widht,int height, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			List<String> images = new ArrayList<String>();
			for (MultipartFile file:files ) {
				long timestart = System.currentTimeMillis();
				String imagewidth = request.getParameter("width");
				String foler = FileUploadUtil.UPLOAD_IMAGE_DIR +System.currentTimeMillis();
				File folerfile = new File(EnvPropertiesConfiger.getValue("uploadDir")+foler);
				if (!folerfile.exists()) {
					folerfile.mkdirs();
				}
				String filepath = foler+ File.separator + PrimaryKeyUtil.getUUID() ;
				String path = filepath+".jpg";
				Thumbnailator.scale(file.getInputStream(), widht, height, 1.0f, 0, EnvPropertiesConfiger.getValue("uploadDir")+filepath+".jpg");
				/**不需要裁剪正方形
				long time1 = System.currentTimeMillis();
				System.out.println(">>>>img time1 : "+(time1-timestart));
				if (null != imagewidth) {
					int width = 0;
					try {
						width = Integer.parseInt(imagewidth);
					}catch(Exception e) {
					}
					if (width>0) {
						Thumbnailator.cutSquareImage(new File(path), width, width, 0.8f, 0, filepath+"_"+width+".jpg");
						path = filepath+"_"+width+".jpg";
					}
				}
				System.out.println(">>>>img time2 : "+(System.currentTimeMillis()-time1));
				*/
				images.add(path);
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"上传成功", images);
		}catch(Exception e) {
			log.error("上传失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"上传失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value="/uploadEncrypt",method = RequestMethod.POST)
	@ResponseBody
	public String uploadEncrypt(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			List<String> images = new ArrayList<String>();
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
			String foler = FileUploadUtil.UPLOAD_ENCRYPT_DIR +System.currentTimeMillis();
			File folerfile = new File(EnvPropertiesConfiger.getValue("uploadDir")+foler);
			if (!folerfile.exists()) {
				folerfile.mkdirs();
			}
			String filepath = foler+ File.separator + PrimaryKeyUtil.getUUID() ;
			String path = filepath+"."+suffix;
			String tempfilepath = EnvPropertiesConfiger.getValue("uploadDir")+filepath+"_temp."+suffix;
	        File f = new File(tempfilepath);
	        file.transferTo(f);
			new FileEncryptUtil(com.simple.fileencrypt.Constant.FILE_ENCRYPT_KEY).encrypt(tempfilepath, EnvPropertiesConfiger.getValue("uploadDir")+path);
			images.add(path);
			//f.delete();
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"上传成功", images);
		}catch(Exception e) {
			log.error("上传失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"上传失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	public static void main(String[] args) {
		String tempfilepath = "C:\\Users\\zhengfy1\\Downloads\\ea19ea94-352b-4925-b62a-c216725cdc71_temp.pdf";
		String filepaht = "C:\\Users\\zhengfy1\\Downloads\\ea19ea94-352b-4925-b62a-c216725cdc71.pdf";
		try {
			new FileEncryptUtil(com.simple.fileencrypt.Constant.FILE_ENCRYPT_KEY).encrypt(tempfilepath, filepaht);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
