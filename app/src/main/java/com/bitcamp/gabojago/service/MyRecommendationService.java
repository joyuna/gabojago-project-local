package com.bitcamp.gabojago.service;


import com.bitcamp.gabojago.vo.MyRecommendation;

import java.util.List;

public interface MyRecommendationService {

    List<MyRecommendation> myRecommendationList(String id) throws Exception;
}
