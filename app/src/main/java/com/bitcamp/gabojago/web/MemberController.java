package com.bitcamp.gabojago.web;

import com.bitcamp.gabojago.service.MemberService;
import com.bitcamp.gabojago.vo.KakaoDTO;
import com.bitcamp.gabojago.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/member/")
public class MemberController {

  @Autowired
  MemberService memberService;
  // HttpSession 클래스 주입.
  @Autowired
  private HttpSession session;

  @GetMapping("list")
  public void list(Model model) throws Exception {
    // 프론트 컨트롤러가 건네준 Model 객체에 작업 결과를 담아 두면
    // 핸들러 호출이 끝났을 때 JSP 를 실행하기 전에
    // 먼저 Model 객체에 담아둔 값을 ServletRequest 보관소로 옮긴다.
    model.addAttribute("members", memberService.list());
  }
  @GetMapping("detail")
  public void detail(String id, Map map) throws Exception {
    Member member = memberService.get(id);

    if (member == null) {
      throw new Exception("해당 아이디의 회원이 없습니다.");
    }
    map.put("member", member);
  }


@PostMapping("update")
  public String update(Member member) throws Exception {
    if (!memberService.update(member)) {
      throw new Exception("회원 변경 오류입니다!");
    }
    return "redirect:list";
  }

  @GetMapping("delete")
  public String delete(String id) throws Exception {
    if (!memberService.delete(id)) {
      throw new Exception("회원 삭제 오류입니다!");
    }

    return "redirect:list";
  }

  @GetMapping("findid/{name}/{email}")
  @ResponseBody
  public String findId(@PathVariable("name") String name, @PathVariable("email") String email) throws Exception {
    Map<String, String> map = new HashMap();
    map.put("name", name);
    map.put("email", email);

    Member member = memberService.findId(map);

    if(member ==null){
      return "입력한 정보에 일치하는 회원이 존재하지 않습니다";
    }
    return member.getId() ;
  }

  @GetMapping("findid")
  public void findId()  throws Exception {

  }



  //  비밀번호 찾기
  @GetMapping("findpwd/{id}/{name}/{email}")
@ResponseBody
  public String findpwd(@PathVariable("id") String id,@PathVariable("name") String name,@PathVariable("email") String email) throws Exception {
    Map<String, String> map = new HashMap();
    map.put("id", id);
    map.put("name", name);
    map.put("email", email);

    Member member = memberService.findpwd(map);

    if (member == null) {
      return "입력한 정보에 일치하는 회원이 존재하지 않습니다";
    }
    return member.getPassword();
  }

  @GetMapping("findpwd")
  public void findpwd()  throws Exception {

  }



/* public String sendEmailPwd(@PathVariable("email") String email){
   MailDto dto = memberService.createMailAndChangePassword(email);
   memberService.emailSend(dto);
   return"/member/login"
 }*/


  @RequestMapping(value="/main/kakao_login.ajax")
  public String kakaoLogin() {
    StringBuffer loginUrl = new StringBuffer();
    loginUrl.append("https://kauth.kakao.com/oauth/authorize?client_id=");
    loginUrl.append("88b44f74865aa118de9f54888c85a112");
    loginUrl.append("&redirect_uri=");
    loginUrl.append("http://localhost:9999/app/kakao_callback");
    loginUrl.append("&response_type=code");

    return "redirect:"+loginUrl.toString();
  }




}
