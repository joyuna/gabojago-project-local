package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.dao.MyCommentsDao;
import com.bitcamp.gabojago.vo.ExhibitionReview;
import com.bitcamp.gabojago.vo.JangComment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyCommentsServiceImpl implements MyCommentsService {

    MyCommentsDao myCommentsDao;

    public MyCommentsServiceImpl(MyCommentsDao myCommentsDao) {
        this.myCommentsDao = myCommentsDao;
    }

    // 코스추천 댓글
    @Override
    public List<JangComment> myRecommendationCommentsList(String id) throws Exception {
        return myCommentsDao.myRecommendationCommentsList(id);
    }

    // 전시회 댓글
    @Override
    public List<ExhibitionReview> myExhibitionCommentsList(String id) throws Exception {
        return myCommentsDao.myExhibitionCommentsList(id);
    }

}
