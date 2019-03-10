package com.wanwj1.xw.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.wanwj1.xw.entity.TImg;
import com.wanwj1.xw.jsoup.Pic;
import com.wanwj1.xw.service.ImgService;

@RestController
@EnableAutoConfiguration
@RequestMapping("/Img")
public class PicBootController {
	private Logger logger = Logger.getLogger(this.getClass());	//log4j日志

	@Resource
    private ImgService imgService;

    @RequestMapping("getImg")
    public TImg getImg(HttpServletRequest request, Model model) {
    	int id = Integer.parseInt(request.getParameter("id"));
    	logger.info("getImg:"+id);
    	TImg timg = this.imgService.selectByPrimaryKey(id);
        return timg;
    }
    
	
	@RequestMapping(value = "/getImgByName", method = RequestMethod.GET)
    public List<TImg> getImgByName(HttpServletRequest request, Model model) {
    	int curlPage = Integer.parseInt(request.getParameter("curlPage"));
    	String picName = request.getParameter("picName")+"";
    	logger.info("picName:"+picName+"curlPage:"+curlPage);
    	List<TImg> timg = this.imgService.selectByName(picName, curlPage,42);
        return timg;
    }
	
    @RequestMapping("addImg")
    public String addImg(HttpServletRequest request, Model model) {
    	Pic pic=new Pic();
    	for(int num=1;num<=2;num++) {
    		System.out.println(111);
        	List<TImg> list=pic.getImg(num);
        	System.out.println(list);
        	if(list.size()>0) {        	
        	for(int i=0;i<list.size();i++) {
            	this.imgService.addImg(list.get(i));
        	}}
        	logger.info("第"+num+"页完成********************************");
    	}

        return "ok";
    }
}