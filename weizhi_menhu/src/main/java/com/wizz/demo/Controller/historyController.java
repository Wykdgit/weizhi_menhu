package com.wizz.demo.Controller;

import com.wizz.demo.model.WizzHistory;
import com.wizz.demo.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/history2")
public class historyController {
    @Autowired
    private HistoryService historyService;

    @GetMapping("/aaa")//查
    public String getTitle(int id){
        return historyService.getMainTitle(id);
    }
    @GetMapping("bbb")
    public String getBriefIntro(int id){
        return historyService.getBriefIntro(id);
    }
    @PostMapping("/manage/update")
    public String update(WizzHistory wizzHistory){
        historyService.updateInfo(wizzHistory);
        return "success";
    }
    @PostMapping("/manage/add")
    public String add(WizzHistory wizzHistory){
        historyService.addInfo(wizzHistory);
        return "success";
    }
    @RequestMapping("/manage/delete")
    public  String delete(int id){
        historyService.deleteInfo(id);
        return "success";
    }
    @GetMapping("list")
    public Map<String,Object> findAll(){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        List<WizzHistory> list=historyService.findAll();
        modelMap.put("list",list);
        return modelMap;
    }
}
