package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.mapper.CostMapper;
import com.lanou.service.CostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CostServiceImpl implements CostService {

    @Resource
    private CostMapper mapper;
    public PageInfo<Cost> findAllPageFee(Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 3 : pageSize;
        PageHelper.startPage(pageNum,pageSize);
        List<Cost> costList = mapper.findAll();
        PageInfo<Cost> pageInfo = new PageInfo<Cost>(costList);
        return pageInfo;
    }
    public void insertCost(Cost cost) {
        mapper.insert(cost);
    }

    public Cost findById(int id) {
        return mapper.findById(id);
    }

    public void updateCost(Cost cost) {
        mapper.update(cost);
    }

    public Integer delete(Integer id) {

        mapper.delete(id);

        return id;
    }

    public void updateStatus(Integer id) {
         mapper.updateStatus(id);
    }
}
