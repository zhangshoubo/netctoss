package com.lanou.mapper;


import com.lanou.bean.Cost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CostMapper {

   List<Cost> findAll();
   void insert(Cost cost);
   Cost findById(@Param("fid") int id);
   void update(Cost cost);
   void delete(@Param("did") int id);

    void updateStatus(Integer id);
}
