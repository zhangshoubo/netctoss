package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;

import java.util.List;

/**
 * Created by dllo on 17/8/30.
 */
public interface CostService {


   PageInfo<Cost> findAllPageFee(Integer pageNum,Integer pageSize);

   void insertCost(Cost cost);

   Cost findById(int id);

    void updateCost(Cost cost);

    Integer delete(Integer id);

    void updateStatus(Integer id);
}
