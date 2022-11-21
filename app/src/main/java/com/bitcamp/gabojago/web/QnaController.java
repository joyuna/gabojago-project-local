package com.bitcamp.gabojago.web;

import com.bitcamp.gabojago.service.QnaService;
import com.bitcamp.gabojago.vo.Member;
import com.bitcamp.gabojago.vo.qna.QnaBoard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/support/qna/")
public class QnaController {
    @Autowired
    QnaService qnaService;

    public QnaController(QnaService qnaService) {
        System.out.println("QnaController() 호출됨!");
        this.qnaService = qnaService;
    }

    @GetMapping("list")
    public void list(Model model) throws Exception {
        model.addAttribute("qnaBoards", qnaService.list());
    }

    @GetMapping("addForm")
    public void addForm() throws Exception {

    }

    @PostMapping("add")
    public String add(
            QnaBoard qnaBoard,
            HttpSession session) throws Exception {
        Member member = (Member) session.getAttribute("loginMember");
        System.out.println("qnaControllerAdd : " + qnaBoard.toString());
        qnaBoard.setId(member.getId());
        qnaService.add(qnaBoard);
        return "redirect:list";
    }

//    @GetMapping("detail")
//    public ModelAndView detail(int no, HttpSession session) throws Exception {
//        Member member = (Member) session.getAttribute("loginMember");
//        ModelAndView mv = new ModelAndView();
//        QnaBoard qnaBoard = qnaService.get(no);
//        if (member == null) {
//            mv.setViewName("/support/qna/list");
//            return mv;
//        } else if (!member.getId().equals(qnaBoard.getId()) && !member.getId().equals("admin")) {
//            mv.setViewName("/support/qna/list");
//            return mv;
//        }
//        mv.setViewName("/support/qna/detail?no=" + no);
//        mv.addObject("qnaBoard", qnaBoard);
//        return mv;
//    }

//    @GetMapping("detail")
//    public String detail(int no, Model model, HttpSession session) throws Exception {
//        Member member = (Member) session.getAttribute("loginMember");
//        QnaBoard qnaBoard = qnaService.get(no);
//        if (member == null) {
//            return "redirect:list";
//        } else if (!member.getId().equals(qnaBoard.getId()) && !member.getId().equals("admin")) {
//            return "redirect:list";
//        }
//        model.addAttribute("qnaBoard", qnaBoard);
//        return "redirect:detail?no="+no;
//    }

    @GetMapping("detail")
    public void detail(int no, Model model, HttpSession session) throws Exception {
        Member member = (Member) session.getAttribute("loginMember");
        QnaBoard qnaBoard = qnaService.get(no);
        model.addAttribute("qnaBoard", qnaBoard);
        boolean isPass = true;

        if(qnaBoard.getDisclosure() && member == null) {
            isPass = false;
        } else if(qnaBoard.getDisclosure() && member != null) {
            if(!member.getId().equals(qnaBoard.getId()) && !member.getId().equals("admin")) {
                isPass = false;
            }
        }

        model.addAttribute("isPass", isPass);
    }

    @GetMapping("delete")
    public String delete(int no, HttpSession session) throws Exception {
        Member member = (Member) session.getAttribute("loginMember");

        qnaService.delete(no);
        return "redirect:list";
    }

    @GetMapping("editForm")
    public void editForm(int no, Model model, HttpSession session) throws Exception {
        Member member = (Member) session.getAttribute("loginMember");
        model.addAttribute("qnaBoard", qnaService.get(no));
        model.addAttribute("member", member);
    }

    @PostMapping("update")
    public String update(
            QnaBoard qnaBoard,
            HttpSession session) throws Exception {
        System.out.println("qnaControllerUpdate : " + qnaBoard.toString());
        Member member = (Member) session.getAttribute("loginMember");
        if (member == null) {
            return "redirect:list";
        } else if (!member.getId().equals(qnaBoard.getId())) {
            return "redirect:list";
        }
        qnaService.update(qnaBoard);
        return "redirect:list";
    }
}
