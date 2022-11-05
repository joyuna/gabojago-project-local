package com.bitcamp.gabojago.web;

import com.bitcamp.gabojago.service.MemberService;
import com.bitcamp.gabojago.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth/")
public class AuthController {

  @Autowired
  MemberService memberService;

  @GetMapping("register")
  public String form(@CookieValue(name = "id", defaultValue="") String id, Model model) throws Exception {
    model.addAttribute("id", id);
    return "auth/register";
  }

  @PostMapping("login")
  public ModelAndView login(
          String id,
          String password,
          String saveEmail,
          HttpServletResponse response,
          HttpSession session) throws Exception {

    Member member = memberService.get(id, password);

    if (member != null) {
      session.setAttribute("loginMember", member); // 로그인한 멤버 정보를 세션 보관소에 저장
    }

    // 클라이언트에게 쿠키 보내기
    // - 쿠키 데이터는 문자열만 가능
    Cookie cookie = new Cookie("id", id); // 클라이언트 쪽에 저장할 쿠키 생성

    cookie.setPath("/app");

    if (saveEmail == null) {
      cookie.setMaxAge(0); // 클라이언트에게 해당 이름의 쿠키를 지우라고 명령한다.
    } else {
      // 쿠키의 지속시간을 설정하지 않으면 웹브라우저가 실행되는 동안만 유효하다.
      // 만약 웹브라우저를 종료하더라도 쿠키를 유지하고 싶다면,
      // 지속 시간을 설정해야 한다.
      cookie.setMaxAge(60 * 60 * 24 * 7);
    }
    response.addCookie(cookie);

    ModelAndView mv = new ModelAndView("/auth/loginResult");
    mv.addObject("member", member);
    return mv;
  }

  @GetMapping("logout")
  public String logout(HttpSession httpSession) throws Exception {
    httpSession.invalidate();
    return "redirect:../";
  }

}
