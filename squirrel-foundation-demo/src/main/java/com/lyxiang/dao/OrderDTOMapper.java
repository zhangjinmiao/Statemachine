package com.lyxiang.dao;

import com.lyxiang.pojo.OrderDTO;

public interface OrderDTOMapper {

    int insert(OrderDTO orderDTO);

    int updateByPrimaryKeySelective(OrderDTO orderDTO);

}
