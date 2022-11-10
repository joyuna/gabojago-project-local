package com.bitcamp.gabojago.dao;

import com.bitcamp.gabojago.vo.event.Event;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventDao {
    List<Event> findAll();

    int insert(Event event);

    Event findByNo(int no);

    int deleteByNo(int no);

    int update(Event event);
}
