package com.wanwj1.xw.service;


import org.springframework.stereotype.Service;
import com.wanwj1.xw.dao.TImgMapper;
import com.wanwj1.xw.entity.TImg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


@Service("ImgService")
public class ImgServiceImpl implements ImgService {

    @Resource
    private TImgMapper imgMpr;


    public TImg selectByPrimaryKey(int id) {
        return imgMpr.selectByPrimaryKey(id);
    }

    
    public boolean addImg(TImg record){
        boolean result = false;
        try {
        	imgMpr.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

	@Override
	public List<TImg> selectByName(String picName, int currPage, int pageSize) {
    	Map<String, Object> data = new HashMap<String, Object>();
        data.put("picName", "%"+picName+"%");
        data.put("currIndex", (currPage-1)*pageSize);
        data.put("pageSize", pageSize);
        return imgMpr.selectByName(data);
    }

}