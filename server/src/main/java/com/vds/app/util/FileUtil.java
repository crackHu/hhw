package com.vds.app.util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import sun.misc.BASE64Decoder;



@SuppressWarnings("restriction")
public class FileUtil {
	
	
	
	/**
	 * 获取值
	 *@author wsy
	 *@time 2016年6月29日下午5:33:46
	 *@param key
	 *@return
	 */
	public static String getValue(String key){
		URL url = FileUtil.class.getResource("/config.properties");
		String value = ReadProperties.getValue(url.getPath(), key);
		return value;
		
	}
	
	
	
	/**
	 * 获取随机文件名
	 * @return
	 */
	public static String getFileName(){
		String name = UUID.randomUUID().toString();
		String fileName = name.replace("-", "");
		return fileName;
	}
	
	/**
	 *  获取tomcat相对路径
	 * @param request
	 * @return
	 */
	public static String getRelativePath(HttpServletRequest request) {
		String realPath = request.getSession().getServletContext()
				.getRealPath("//");
		return realPath;
	}
	
	/**
	 * 创建文件夹目录
	 * @param destDirName 目录
	 * @return
	 */
	public static boolean createDir(String destDirName) {  
	        File dir = new File(destDirName);  
	        if (dir.exists()) {  
	            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");  
	            return false;  
	        }  
	        if (!destDirName.endsWith(File.separator)) {  
	            destDirName = destDirName + File.separator;  
	        }  
	        //创建目录  
	        if (dir.mkdirs()) {  
	            System.out.println("创建目录" + destDirName + "成功！");  
	            return true;  
	        } else {  
	            System.out.println("创建目录" + destDirName + "失败！");  
	            return false;  
	        }  
	    }  
	
	/**
	 * 删除文件
	 * @param request
	 * @param path
	 * @return
	 */
	public static boolean deleteFile(HttpServletRequest request,String path){
        File file = new File(getRelativePath(request)+path);
        boolean flag = false;
         
        if(file.exists()&&file.isFile()){//判断文件是否存在
            flag = file.delete();//删除文件
        }else{
        	System.out.println("文件不存在");
        }
         
        return flag;
    }
	
	/**
	 * 删除文件(nginx)
	 * @param request
	 * @param path
	 * @return
	 */
	public static boolean deleteFileNginx(String path){
        File file = new File(Config.fileSavePath+path);
        boolean flag = false;
         
        if(file.exists()&&file.isFile()){//判断文件是否存在
            flag = file.delete();//删除文件
        }else{
        	System.out.println("文件不存在");
        }
         
        return flag;
    }
	
	/**
	 * 获取文件后缀名
	 * @param file
	 * @return
	 */
	public String getFileSuffix(File file){
		String fileName=file.getName();
	    String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
	    return prefix;
	}
	
	/**
	 * 获取文件后缀名
	 * @param file
	 * @return
	 */
	public static String getFileSuffix(MultipartFile file){
		String fileName=file.getOriginalFilename();
	    String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
	    return prefix;
	}
	/**
	 * 上传文件
	 * @param request
	 * @param file
	 * @param catalog
	 */
	public static String uploadFile(HttpServletRequest request,MultipartFile file,String catalog){
		//判断文件是否为空
		if(!file.isEmpty()){
			String relativePath = getRelativePath(request);//获取到tomcat的相对路径
			String ml = relativePath+"//"+catalog;//获取文件的上传路径
			createDir(ml);
			String prefix = getFileSuffix(file);
			String originFileName = getFileName()+"."+prefix;
			File uploadFile = new File(ml, originFileName);  
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), uploadFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return catalog+"//"+originFileName;
		}else{
			return null;
		}
	}
	
	/**
	 * 上传文件（base64）
	 * @param request
	 * @param baseCode
	 * @param type
	 * @param role
	 * @return
	 */
	 public static String toFile(HttpServletRequest request,String baseCode,String type, String role){
			   
		String fileName = getFileName();
		String relativePath = getRelativePath(request);//获取到tomcat的相对路径
		String folder = role+"//"+fileName+"."+type;
		System.out.println("relativePath:"+relativePath);
		String ml = getRelativePath(request)+"//"+role;
			//先创建目录
			createDir(ml);
			try {
				byte[] buffer = new BASE64Decoder().decodeBuffer(baseCode.replace(" ", "+"));
				FileOutputStream	out = new FileOutputStream(ml+"//"+fileName+"."+type);
				out.write(buffer);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			
			return folder;
			
	}
	 
	 /**
	  * 上传图片（nginx）
	  *@author wsy
	  *@time 2016年6月29日下午5:46:54
	  *@param file
	  *@param catalog
	  *@return
	  */
	 public static String uploadImage(MultipartFile file,String catalog){
		//判断文件是否为空
			if(!file.isEmpty()){
				String relativePath = Config.fileSavePath;
				String ml = relativePath+"//"+catalog;//获取文件的上传路径
				createDir(ml);
				String prefix = getFileSuffix(file);
				String originFileName = getFileName()+"."+prefix;
				File uploadFile = new File(ml, originFileName);  
				try {
					FileUtils.copyInputStreamToFile(file.getInputStream(), uploadFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return catalog+"//"+originFileName;
			}else{
				return null;
			}
	 }
	 
	 /**
	  * 获取request域中的文件
	  *@author wsy
	  *@time 2016年7月11日下午5:21:30
	  *@param request
	  *@param fileName
	  *@return
	  */
	 public static MultipartFile getMultipartFile(HttpServletRequest request,String fileName){
		 CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		 if (multipartResolver.isMultipart(request)) {
			 MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			 MultipartFile multipartFile = multiRequest.getFile(fileName);
			 return multipartFile;
		}else{
			return null;
		}
	 }
	 
	 /*public static uploadFile(HttpServletRequest request){
		//创建一个通用的多部分解析器.  
		   CommonsMultipartResolver commonsMultipartResolver = new   
		CommonsMultipartResolver(request.getSession().getServletContext());  
		//设置编码  
		   commonsMultipartResolver.setDefaultEncoding("utf-8");  
		 //判断 request 是否有文件上传,即多部分请求...  
		 if (commonsMultipartResolver.isMultipart(request))  
		   {  
		      //转换成多部分request  
		      MultipartHttpServletRequest multipartRequest =         
		    commonsMultipartResolver.resolveMultipart(request);  
		  
		   // file 是指 文件上传标签的 name=值  
		    // 根据 name 获取上传的文件...  
		    MultipartFile file = multipartRequest.getFile("file"); 
	 }*/
	 
}
