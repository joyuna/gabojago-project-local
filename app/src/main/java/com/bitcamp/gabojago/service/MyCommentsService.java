package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.vo.ExhibitionReview;
import com.bitcamp.gabojago.vo.JangComment;

import java.util.List;

public interface MyCommentsService {

    // 코스추천글 댓글
    List<JangComment> myRecommendationCommentsList(String id) throws Exception;

    // 전시회 댓글
    List<ExhibitionReview> myExhibitionCommentsList(String id) throws Exception;

}
