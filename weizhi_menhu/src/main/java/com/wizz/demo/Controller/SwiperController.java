package com.wizz.demo.Controller;

import com.wizz.demo.dao.SwiperDao;
import com.wizz.demo.model.Swiper;
import com.wizz.demo.service.SwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/swiper")
public class SwiperController{
    private Map<String,Object> params = new HashMap<>();


    @Autowired
    private SwiperService swiperService;

    @Autowired
    private SwiperDao swiperDao;

    /**
     * 用户查询照片列表接口
     * @return json
     */
    @RequestMapping(value = "/list")
    public Object list(){
        params = swiperService.list();
        return params;

    }

    /**
     * 用户上传图片接口，一次接受一张图片后，保存文件，写入相关信息到数据库
     * @param file
     * @return json
     */
    @RequestMapping(value = "/manage/upload")
    public Object upload(@RequestParam(value = "head_img",required = false) MultipartFile file){
        params.clear();
        if (file==null){
            return params;
        }
        params = swiperService.insert(file);
        return params;

    }

    /**
     * 用户根据id删除照片接口
     * @param id
     * @return json
     */
    @RequestMapping(value = "/manage/del")
    public Object del(@RequestParam(value = "id", required = false) Integer id){
        params.clear();
        if (id == null)return params;
        params = swiperService.del(id);
        return params;
    }
    @RequestMapping(value = "/manage/move")
    public Object movePhoto(@RequestParam(value = "id",required = false) Integer id,@RequestParam(value = "code",required = false) Integer code){
        params.clear();
        if (id==null || code == null) return params;
        params = swiperService.move(id,code);
        return params;
    }





}
@Controller
@RequestMapping("/swiper")
class SwiperHtml{
    @RequestMapping("/uploadpage")
    public  Object uploadPage(){
        return "upload";
        }
}