package com.bitcamp.gabojago.web;

import com.bitcamp.gabojago.service.MemberService;
import com.bitcamp.gabojago.service.MyRecommendationService;
import com.bitcamp.gabojago.vo.Member;
import com.bitcamp.gabojago.vo.Recommendation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/myPage/myRecommendation/")
public class MyRecommendationController {

    private MyRecommendationService myRecommendationService;
    private MemberService memberService;

    public MyRecommendationController(MyRecommendationService myRecommendationService, MemberService memberService) {
        this.myRecommendationService = myRecommendationService;
        this.memberService = memberService;
    }

    @GetMapping("myRecommendationList")
    public void myRecommendationList(Recommendation recommendation,
                                     HttpSession session,
                                     Model model) throws Exception {

        Member loginMember = (Member) session.getAttribute("loginMember");

        if (loginMember == null) {

            model.addAttribute("myPageFailMessage", "로그인이 필요한 항목입니다. 회원가입 또는 로그인해 주세요.");

//            return "myPage/myPageFail";

        } else {
            Member member = memberService.get(loginMember.getId());

            model.addAttribute("id", member.getId());
            model.addAttribute("name", member.getName());
            model.addAttribute("profileFig", member.getProfileFig());
            model.addAttribute("nickName", member.getNickName());
            model.addAttribute("mbti", member.getMbti());
            model.addAttribute("snsAddress", member.getSnsAddress());

        }

        recommendation.setWriter((Member) session.getAttribute("loginMember"));
        model.addAttribute("recommendations", myRecommendationService.myRecommendationList(recommendation.getWriter().getId()));

    }
}
