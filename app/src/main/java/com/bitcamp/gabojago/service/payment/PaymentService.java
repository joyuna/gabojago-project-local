package com.bitcamp.gabojago.service.payment;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.gabojago.dao.cart.CartDao;
import com.bitcamp.gabojago.vo.Member;

@Service
public class PaymentService {
  
  @Autowired
  CartDao cartDao;
  
  public List<Map<String, String>> getCartList(Member member) {
    return cartDao.getCartList(member.getId());
  }
}
