package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.service.CostService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CostController {

    @Resource
    private CostService costService;


    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/fee/fee_list")
    public String jump(){
        return "fee/fee_list";
    }


    @RequestMapping(value = "fee/fee_list.html")
    public String fee() {
        return "fee/fee_list";
    }


    @RequestMapping(value = "/findAllPageFee")
    @ResponseBody
    public PageInfo<Cost> findAllPageFee(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageInfo<Cost> pageFee = costService.findAllPageFee(pageNum, pageSize);
        return pageFee;
    }


    @RequestMapping(value = "fee_add.html")
    public String fee_add() {
        return "fee/fee_add";
    }

    @RequestMapping(value = "/backfrontpage")
    public String back(){
        return "fee/fee_list";
    }


    @RequestMapping(value = "fee/feeAddCost")
    public String feeAddCost(Cost cost) {
        cost.setStatus("1");
        cost.setCreatime(new Timestamp(System.currentTimeMillis()));
        costService.insertCost(cost);
        return "redirect:fee_list.html";
    }

    @RequestMapping(value = "/fee_modi")
    public String fee_modi(@RequestParam(value = "cost_id") Integer id, HttpSession session) {
        Cost cost = costService.findById(id);
        session.setAttribute("cost", cost);
        return "fee/fee_modi";
    }

    @RequestMapping(value = "/showcost")
    @ResponseBody
    public Map<String,Object> showId(HttpSession session ){
        Cost cost = (Cost) session.getAttribute("cost");
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("showcost",cost);
        return map;
    }




    @RequestMapping(value = "/modi")
    public String modi(Cost cost) {
        System.out.println(cost);
        costService.updateCost(cost);
        return "redirect:/fee/fee_list";

    }
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public String updateStatus(@RequestParam("id") Integer id){
         costService.updateStatus(id);

        return null;

    }






    @RequestMapping(value = "fee/fee_detail.html")
    public String   detail( @RequestParam(value = "detail_id") Integer id, HttpSession session) {
        Cost cost = costService.findById(id);
        session.setAttribute("detail_id", cost);
        return "fee/fee_detail";
    }

//    个人自费名称页面fee_detail.html，走这个方法显示页面上的数据
    @RequestMapping(value = "/fee_detail")
    @ResponseBody
    public Map<String,Object> showdetail(HttpSession session) {
        Cost cost = (Cost) session.getAttribute("detail_id");
        Map<String, Object> map  = new HashMap<String, Object>();
        map.put("detail",cost);
        return map;
    }

    @RequestMapping(value = "/deleteFee")
    @ResponseBody
    public Map<String,Integer> delete(@RequestParam(value = "detail_id") Integer id) {
        Integer did = costService.delete(id);
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("did",did);
        return  map ;
    }









}
