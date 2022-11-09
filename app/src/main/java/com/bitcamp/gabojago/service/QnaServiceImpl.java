package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.dao.QnaDao;
import com.bitcamp.gabojago.vo.qna.QnaBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QnaServiceImpl implements QnaService {

    @Autowired
    QnaDao qnaDao;

    @Transactional
    @Override
    public void add(QnaBoard qnaBoard) throws  Exception {
        System.out.println("QnaServiceImpl : " + qnaBoard.toString());

        if (qnaDao.insert(qnaBoard) == 0) {
            throw new Exception("게시글 등록 실패!!");
        }
    }

    @Override
    public List<QnaBoard> list() throws Exception {
        return qnaDao.findAll();
    }

    @Override
    public QnaBoard get(int no) throws Exception {
        return qnaDao.findByNo(no);
    }

    @Override
    public Boolean delete(int no) throws Exception {
        return qnaDao.deleteByNo(no) > 0;
    }

    @Override
    public boolean update(QnaBoard qnaBoard) throws Exception {
        System.out.println("QnaServiceImplUpdate : " + qnaBoard.toString());
        if (qnaDao.update(qnaBoard) == 0) {
            return false;
        }
        return true;
    }
}
