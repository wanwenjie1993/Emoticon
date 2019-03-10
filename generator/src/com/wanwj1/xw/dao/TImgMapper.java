package com.wanwj1.xw.dao;

import com.wanwj1.xw.entity.TImg;

public interface TImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TImg record);

    int insertSelective(TImg record);

    TImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TImg record);

    int updateByPrimaryKey(TImg record);
}