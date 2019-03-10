package com.wanwj1.xw.controller;



import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;


@RestController
public class FileController {
    //访问路径为：http://127.0.0.1:8080/file
    @RequestMapping("/file")
    public String file(){
        return "/file";
    }
    
    @RequestMapping("/mutifile")
    public String mutifile(){
        return "/mutifile";
    }

	/*
	 * { "code": 0 ,"msg": "" ,"data": { "src": "http://cdn.layui.com/123.jpg" } }
	 */
    @RequestMapping("/upload")
    @ResponseBody
    public JSONObject handleFileUpload(@RequestParam("file")MultipartFile file){
    	//本地地址
    	String localUrl="D:\\TEST\\";
    	
        JSONObject json=new JSONObject();


        if(!file.isEmpty()){
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(localUrl+file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            }catch(FileNotFoundException e) {
                e.printStackTrace();
                json.put("code", 0);
                json.put("msg", "上传失败"+e.getMessage());
                json.put("data", "");
                return json;
            }catch (IOException e) {
                e.printStackTrace();
                json.put("code", 0);
                json.put("msg", "上传失败"+e.getMessage());
                json.put("data", "");
                return json;
            }
            json.put("code", 0);
            json.put("msg", "上传成功");
            json.put("data", "");


            return json;

        }else{
            json.put("code", 0);
            json.put("msg", "上传失败，因为文件是空的");
            json.put("data", "");
            return json;

        }
    }

    /**

     * 多文件具体上传时间，主要是使用了MultipartHttpServletRequest和MultipartFile

     * @param request

     * @return

     */

    @RequestMapping(value="/batch/upload", method=RequestMethod.POST) 
    public @ResponseBody 
    String handleFileUpload(HttpServletRequest request){ 
        List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("file"); 
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i =0; i< files.size(); ++i) { 
            file = files.get(i); 
            if (!file.isEmpty()) { 
                try { 
                    byte[] bytes = file.getBytes(); 
                    stream = 
                            new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename()))); 
                    stream.write(bytes); 
                    stream.close(); 
                } catch (Exception e) { 
                    stream =  null;
                    return "You failed to upload " + i + " =>" + e.getMessage(); 
                } 
            } else { 
                return "You failed to upload " + i + " becausethe file was empty."; 
            } 
        } 
        return "upload successful"; 

    } 
}