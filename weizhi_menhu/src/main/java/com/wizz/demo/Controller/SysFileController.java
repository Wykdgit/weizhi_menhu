package com.wizz.demo.Controller;

import com.wizz.demo.model.SysFile;
import com.wizz.demo.service.SysFileService;
import com.wizz.demo.util.ResultData;
import com.wizz.demo.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system")
public class SysFileController{

    @Autowired
    private SysFileService fileService;


    /**
     * 获取所有数据
     * @return
     */
    @RequestMapping("/getList")
    @ResponseBody
    public List<SysFile> getList(SysFile sysFile){
        return fileService.getList(sysFile);
    }

    /**
     * 根据id获取单个数据
     * @return
     */
    @RequestMapping("/get/{id}")
    @ResponseBody
    public SysFile get(@PathVariable("id") int id){
        return fileService.getById(id);
    }

    /**
     * 根据id删除
     * @return
     */
    @RequestMapping("/manage/delete/{id}")
    @ResponseBody
    public ResultData delete(@PathVariable("id") int id){
        fileService.delete(id);
        ResultData res = new ResultData();
        res.setMsg("删除成功!");
        return res;
    }

    /**
     * 上传资料
     *
     * @param file
     * @param param
     */
    @RequestMapping(value = "/manage/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResultData upload(@RequestParam("file") MultipartFile[] file, @RequestParam Map<String, Object> param) {
        Object t = param.get("theme");
        if(t == null || t.toString().equals("")){
            return ResultUtils.error("模块主题不能为空！");
        }
        String theme = t.toString();
        if(theme.toCharArray().length > 10){
            return ResultUtils.error("主题不能超过10个汉字!");
        }
        Object content = param.get("content");
        if(content== null || content.toString().equals("")){
            return ResultUtils.error("模块简介不能为空！");
        }
        String c = content.toString();
        if(c.toCharArray().length > 150){
            return ResultUtils.error("简介不能超过150个汉字!");
        }

        try {
            fileService.saveFile(file,param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResultData res = new ResultData();
        res.setMsg("成功添加！");
        return res;
    }

    /**
     * 修改
     * @param file
     * @param param
     */
    @RequestMapping(value = "/manage/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultData update(@RequestParam("file") MultipartFile[] file, @RequestParam Map<String, Object> param) {
        Object t = param.get("theme");
        if(t == null || t.toString().equals("")){
            return ResultUtils.error("模块主题不能为空！");
        }
        String theme = t.toString();
        if(theme.toCharArray().length > 10){
            return ResultUtils.error("主题不能超过10个汉字!");
        }
        Object content = param.get("content");
        if(content == null || content.toString().equals("")){
            return ResultUtils.error("模块简介不能为空！");
        }
        String c = content.toString();
        if(c.toCharArray().length > 150){
            return ResultUtils.error("简介不能超过150个汉字!");
        }
        try {
            fileService.update(file,param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResultData res = new ResultData();
        res.setMsg("修改成功！");
        return res;
    }

}
