package com.bitcamp.gabojago.dao;

import com.bitcamp.gabojago.vo.Report;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportDao {

  // recommendationReportAdd - 신고처리
  void recommendationReportAdd(Report report);

  // 신고당한 user의 과거행적 조회
  int countReportById(String reportedId);
}
