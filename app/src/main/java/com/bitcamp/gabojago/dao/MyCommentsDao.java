package com.bitcamp.gabojago.dao;

import com.bitcamp.gabojago.vo.ExhibitionReview;
import com.bitcamp.gabojago.vo.JangComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyCommentsDao {

    // 코스추천 댓글
    List<JangComment> myRecommendationCommentsList(String id);

    // 전시회 댓글
    List<ExhibitionReview> myExhibitionCommentsList(String id);

}
