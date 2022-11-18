package com.bitcamp.gabojago.web.payment;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bitcamp.gabojago.service.payment.PaymentService;
import com.bitcamp.gabojago.vo.Member;

@Controller
@RequestMapping("/payment/")
public class PaymentController {
  
  @Autowired
  PaymentService paymentService;
  
  @GetMapping("showCart")
  public String showCart(Model model, HttpSession session) throws Exception {
    Member member = (Member) session.getAttribute("loginMember");
    model.addAttribute("cartList", paymentService.getCartList(member));
    
    return "payment/showCart";
  }
  
  @GetMapping("paymentPage")
  public String searchResult(Model model, HttpSession session, Integer price) throws Exception {
    Member member = (Member) session.getAttribute("loginMember");
    
    model.addAttribute("member", member);
    model.addAttribute("price", price);
    
    return "payment/paymentPage";
  }
}
