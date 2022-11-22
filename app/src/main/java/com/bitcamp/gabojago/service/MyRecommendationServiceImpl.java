package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.dao.MyRecommendationDao;
import com.bitcamp.gabojago.vo.Recommendation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyRecommendationServiceImpl implements MyRecommendationService {

    private MyRecommendationDao myRecommendationDao;

    public MyRecommendationServiceImpl(MyRecommendationDao myRecommendationDao) {
        this.myRecommendationDao = myRecommendationDao;
    }

    @Override
    public List<Recommendation> myRecommendationList(String id) throws Exception {
        return myRecommendationDao.myRecommendationList(id);
    }
}
