package com.bitcamp.gabojago.web;

import com.bitcamp.gabojago.service.MyRecommendationService;
import com.bitcamp.gabojago.vo.Member;
import com.bitcamp.gabojago.vo.MyRecommendation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/myPage/myRecommendation/")
public class MyRecommendationController {

    private MyRecommendationService myRecommendationService;

    public MyRecommendationController(MyRecommendationService myRecommendationService) {
        this.myRecommendationService = myRecommendationService;
    }

    @GetMapping("myRecommendationList")
    public void myRecommendationList(MyRecommendation myRecommendation,
                                     HttpSession session,
                                     Model model) throws Exception {


        myRecommendation.setWriter((Member) session.getAttribute("loginMember"));
        model.addAttribute("myRecommendations", myRecommendationService.myRecommendationList(myRecommendation.getWriter().getId()));
    }
}
