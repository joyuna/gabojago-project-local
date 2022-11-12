package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.vo.ExhibitionReview;
import com.bitcamp.gabojago.vo.Member;

import java.util.List;


public interface MemberService {

  Member idCheck(String id) throws Exception;

  Member nickNameCheck(String nickName) throws Exception;



  Member get(String id, String password) throws Exception;

  boolean join(String email, String phoneNo, Member member) throws Exception;

  Member phoneNoCheck(String phoneNo) throws Exception;

  Member emailCheck(String email) throws Exception;


  /* 장현경 작업 중*/
  boolean delete(String id) throws Exception;
  List<Member> list() throws Exception;

  Member get(String id) throws Exception;

  boolean update(Member member) throws Exception;

}
