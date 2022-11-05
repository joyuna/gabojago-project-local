package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.dao.MemberDao;
import com.bitcamp.gabojago.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultMemberService implements MemberService {

  @Autowired
  MemberDao memberDao;

  @Override
  public Member get(String id, String password) throws Exception {
    return memberDao.findByIdPassword(id, password);
  }
}
