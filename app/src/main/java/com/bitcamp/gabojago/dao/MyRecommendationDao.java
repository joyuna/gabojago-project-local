package com.bitcamp.gabojago.dao;


import com.bitcamp.gabojago.vo.Recommendation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyRecommendationDao {

    List<Recommendation> myRecommendationList(String id);


}
