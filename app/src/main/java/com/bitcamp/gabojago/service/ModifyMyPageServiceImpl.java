package com.bitcamp.gabojago.service;


import com.bitcamp.gabojago.dao.ModifyMyPageDao;
import com.bitcamp.gabojago.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModifyMyPageServiceImpl implements ModifyMyPageService {
    @Autowired
    ModifyMyPageDao modifyMyPageDao;


    @Override
    public boolean profileUpdate(Member member) throws Exception {
        if (modifyMyPageDao.profileUpdate(member) == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean myAccountUpdate(Member member) throws Exception {
        if (modifyMyPageDao.myAccountUpdate(member) == 0) {
            return false;
        }
        return true;
    }
    @Override
    public Member get(String id) throws Exception {
        return modifyMyPageDao.findById(id);
    }

    // 닉네임 중복 확인
    @Override
    public int nickCheck(String nickName) throws Exception {
        int result = modifyMyPageDao.findByNickName(nickName);
        return result;
    }

//    // 회원 탈퇴를 위한 비밀번호 확인
//    @Override
//    public int passwordCheck(Member member) throws Exception {
//        int result = modifyMyPageDao.findByPassword(member.getId(), member.getPassword());
//        return result;
//    }
//
//    @Override
//    public boolean memberDelete(Member member) throws Exception {
//        return modifyMyPageDao.memberDelete(member.getId()) > 0;
//    }

    @Override
    public int pwCheck(String memberId, String memberPw) throws Exception {
        int result = modifyMyPageDao.checkPassword(memberId, memberPw);
        System.out.println("pwCheckServiceImpl = " + result);
        return result;
    }
    @Override
    public int resignMember(String memberId, String memberPw) throws Exception {
        System.out.println("resignMemberServiceImpl = "+ memberId+", "+memberPw);
        int result = modifyMyPageDao.resignMemberStatus(memberId, memberPw);
        System.out.println("resignMemberResult = " + result);
        return result;
    }
}

