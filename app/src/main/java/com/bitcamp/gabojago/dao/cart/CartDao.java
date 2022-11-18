package com.bitcamp.gabojago.dao.cart;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartDao {

  List<Map<String, String>> getCartList(String id);
	
}
