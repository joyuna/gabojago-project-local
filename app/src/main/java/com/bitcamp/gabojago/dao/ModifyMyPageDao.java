package com.bitcamp.gabojago.dao;


import com.bitcamp.gabojago.vo.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ModifyMyPageDao {

    int profileUpdate(Member member);

    int myAccountUpdate(Member member);

    Member findById(String id);

    // 닉네임 중복 확인
    int findByNickName(String nickName);

//    // 회원 탈퇴를 위한 비밀번호 확인
//    int findByPassword(@Param("id") String id,@Param("password") String password);
//
//    int memberDelete(@Param("id") String id);

    int checkPassword(@Param("id")String memberId, @Param("password") String memberPw);
    int resignMemberStatus(@Param("id")String memberId, @Param("password") String memberPw);
}
