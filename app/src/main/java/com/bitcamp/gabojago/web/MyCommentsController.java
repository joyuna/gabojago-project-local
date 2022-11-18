package com.bitcamp.gabojago.web;

import com.bitcamp.gabojago.service.MyCommentsService;
import com.bitcamp.gabojago.vo.ExhibitionReview;
import com.bitcamp.gabojago.vo.JangComment;
import com.bitcamp.gabojago.vo.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/myPage/myComments/")
public class MyCommentsController {

    private MyCommentsService myCommentsService;

    public MyCommentsController(MyCommentsService myCommentsService) {
        this.myCommentsService = myCommentsService;
    }

    @GetMapping("myRecommendationCommentsList")
    public void myRecommendationCommentsList(JangComment jangComment,
                                             HttpSession session,
                                             Model model) throws Exception {

        jangComment.setWriter((Member) session.getAttribute("loginMember"));
        model.addAttribute("jangComments", myCommentsService.myRecommendationCommentsList(jangComment.getWriter().getId()));
    }

    @GetMapping("myExhibitionCommentsList")
    public void myExhibitionCommentsList(ExhibitionReview exhibitionReview,
                                         HttpSession session,
                                         Model model) throws Exception {

        exhibitionReview.setWriter((Member) session.getAttribute("loginMember"));
        model.addAttribute("exhibitionReviews", myCommentsService.myExhibitionCommentsList(exhibitionReview.getWriter().getId()));
    }
}
