package com.bitcamp.gabojago.dao;


import com.bitcamp.gabojago.vo.MyRecommendation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyRecommendationDao {

    List<MyRecommendation> myRecommendationList(String id);


}
