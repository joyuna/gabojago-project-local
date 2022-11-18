package com.bitcamp.gabojago.service;


import com.bitcamp.gabojago.vo.Member;

public interface ModifyMyPageService {


    boolean profileUpdate(Member member) throws Exception;

    boolean myAccountUpdate(Member member) throws Exception;

    Member get(String id) throws Exception;

    int nickCheck(String nickName) throws Exception;

    int resignMember(String memberId) throws Exception;


}
