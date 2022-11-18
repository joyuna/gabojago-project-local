package com.bitcamp.gabojago.web;

import com.bitcamp.gabojago.service.MemberService;
import com.bitcamp.gabojago.service.ModifyMyPageService;
import com.bitcamp.gabojago.vo.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/myPage/modify/")
public class ModifyMyPageController {

    private ServletContext sc;

    private ModifyMyPageService modifyMyPageService;

    private MemberService memberService;


    public ModifyMyPageController(ServletContext sc,
                                  ModifyMyPageService modifyMyPageService,
                                  MemberService memberService) {
        this.sc = sc;
        this.modifyMyPageService = modifyMyPageService;
        this.memberService = memberService;
    }

    @GetMapping("profileDetail")
    public Map profileDetail(HttpSession session) throws Exception {
        Member loginMember = (Member) session.getAttribute("loginMember");
        Member member = modifyMyPageService.get(loginMember.getId());

        Map map = new HashMap();
        map.put("member", member);
        return map;
    }

    @GetMapping("myAccountDetail")
    public Map myAccountDetail(HttpSession session) throws Exception {
        Member loginMember = (Member) session.getAttribute("loginMember");
        Member member = modifyMyPageService.get(loginMember.getId());

        Map map = new HashMap();
        map.put("member", member);
        return map;
    }

    @PostMapping("profileUpdate")
    public String profileUpdate(
            Member member,
            @RequestParam MultipartFile file,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpSession session) throws Exception {

            Member loginMember = (Member) session.getAttribute("loginMember");

            Member saveMember = memberService.get(loginMember.getId());
            saveMember.setNickName(member.getNickName().trim());
            saveMember.setMbti(member.getMbti());
            saveMember.setSnsAddress(member.getSnsAddress().trim());

           if(!file.isEmpty()){
               String dirPath = sc.getRealPath("/board/files");
               String filename = UUID.randomUUID().toString();
               String profileFig = filename;
               file.transferTo(new File(dirPath + "/" + filename));
               saveMember.setProfileFig(profileFig);
           }

           modifyMyPageService.profileUpdate(saveMember);

           model.addAttribute("profileFig", saveMember.getProfileFig());
           model.addAttribute("nickname", saveMember.getNickName());
           model.addAttribute("mbti", saveMember.getMbti());
           model.addAttribute("snsAddress", saveMember.getSnsAddress());

           return "redirect:/myPage/";
    }

    @PostMapping ("myAccountUpdate")
    public String myAccountUpdate(Member member, HttpSession session) throws Exception {
        modifyMyPageService.myAccountUpdate(member);
        return "redirect:/myPage/";
    }

    // 닉네임 중복 확인
    @ResponseBody
    @PostMapping("nickCheck")
    public int nickCheck(String nickName) throws Exception {
        int result = modifyMyPageService.nickCheck(nickName);
        return result;
    }


    @GetMapping("memberDeleteForm")
    public Map memberDeleteForm(HttpSession session) throws Exception {
        Member loginMember = (Member) session.getAttribute("loginMember");
        Member member = modifyMyPageService.get(loginMember.getId());

        Map map = new HashMap();
        map.put("member", member);
        return map;
    }

    // 회원 탈퇴를 위한 비밀번호 확인
    @ResponseBody
    @PostMapping("passwordCheck")
    public int passwordCheck(Member member) throws Exception {
        int result = modifyMyPageService.passwordCheck(member);
        return result;
    }

    @PostMapping("memberDelete")
    public String memberDelete(Member member) throws Exception {
        modifyMyPageService.memberDelete(member);
        return "redirect:../";
    }
}

