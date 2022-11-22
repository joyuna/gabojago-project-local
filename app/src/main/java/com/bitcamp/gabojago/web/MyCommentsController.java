package com.bitcamp.gabojago.web;

import com.bitcamp.gabojago.service.MemberService;
import com.bitcamp.gabojago.service.MyCommentsService;
import com.bitcamp.gabojago.vo.ExhibitionReview;
import com.bitcamp.gabojago.vo.JangComment;
import com.bitcamp.gabojago.vo.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/myPage/myComments/")
public class MyCommentsController {

    private MyCommentsService myCommentsService;

    private MemberService memberService;

    public MyCommentsController(MyCommentsService myCommentsService, MemberService memberService) {
        this.myCommentsService = myCommentsService;
        this.memberService = memberService;
    }

    // 코스추천 댓글
    @GetMapping("myRecommendationCommentsList")
    public void myRecommendationCommentsList(JangComment jangComment,
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

        jangComment.setWriter((Member) session.getAttribute("loginMember"));
        model.addAttribute("jangComments", myCommentsService.myRecommendationCommentsList(jangComment.getWriter().getId()));
    }

    // 전시회 댓글
    @GetMapping("myExhibitionCommentsList")
    public void myExhibitionCommentsList(ExhibitionReview exhibitionReview,
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

        exhibitionReview.setWriter((Member) session.getAttribute("loginMember"));
        model.addAttribute("exhibitionReviews", myCommentsService.myExhibitionCommentsList(exhibitionReview.getWriter().getId()));
    }
}
