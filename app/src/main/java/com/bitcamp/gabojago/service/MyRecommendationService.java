package com.bitcamp.gabojago.service;


import com.bitcamp.gabojago.vo.Recommendation;

import java.util.List;

public interface MyRecommendationService {

    List<Recommendation> myRecommendationList(String id) throws Exception;
}
