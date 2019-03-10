package com.wanwj1.xw.service;

import java.util.List;

import com.wanwj1.xw.entity.TImg;

public interface ImgService {
    public TImg selectByPrimaryKey(int id);
    

    public List<TImg> selectByName(String picName,int currPage, int pageSize);

    boolean addImg(TImg record);

}