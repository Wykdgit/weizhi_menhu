package com.wizz.demo.service.Impl;

import com.wizz.demo.dao.SwiperDao;
import com.wizz.demo.model.Swiper;
import com.wizz.demo.service.SwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;
@PropertySource({"application.properties"})
@Service


public class SwiperServiceImpl implements SwiperService {
    private Map<String,Object> params = new HashMap<>();


    @Value("${web.upload-path}")
    public    String filePath ;
    @Value("${web.swiper-maxnum}")
    public    int MAXNUM ;
    @Value("${web.swiper-maxsize}")
    public    int MAXSIZE ;
    @Value("${img.domain.name}")
    public String imgDomain;

    @Autowired
    private SwiperDao swiperDao;

    //    public static final  String filePath = "C:\\Users\\mop\\IdeaProjects\\weizhiweb\\src\\main\\resources\\upload\\";
    @Override
    public Map<String,Object> insert(MultipartFile file){
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        long fileSize=file.getSize();
        int maxSize=MAXSIZE;
        System.out.println("size is "+fileSize);
        System.out.println("上传的文件名为：" + fileName+" 后缀名: "+suffixName);
        if (fileSize>maxSize){
            params.put("error_code",-3);
            params.put("msg","Size Exceeded, max is "+maxSize/1024+"KB the File is "+fileSize/1024+" KB");
            return params;

        }
        fileName = UUID.randomUUID() + suffixName;
        if (!(suffixName.equals(".jpg") || suffixName.equals(".bmp") || suffixName.equals(".png") || suffixName.equals(".jpeg"))){
            params.put("error_code",-4);
            params.put("msg","Only .jpg .png .jpeg .bmp allowed");
            return params;
        }
        File dest = new File(filePath+fileName);
        System.out.println(dest.getPath());
        List<Swiper> resultSet = swiperDao.getAll();
        int size = resultSet.size();
        int maxnum=MAXNUM;
        if (size>=maxnum) {
            params.put("error_code",-1);
            params.put("msg","Photo Num Exceeded, Max is "+maxnum);
            return params;
        }
        try{
            file.transferTo(dest);
            params.put("error_code",0);
            params.put("fileName",fileName);
            params.put("msg","ok");
            Swiper swiper = new Swiper();
            swiper.setName(fileName);
            swiper.setDate(new Date());
            int newrank=-1;
            for (Swiper tmp:resultSet){
                if (tmp.getId()>newrank)
                    newrank=tmp.getRank();
                System.out.println(newrank);
            }
            swiper.setRank(newrank+1);
            swiperDao.insert(swiper);
        }
        catch (Exception e){
            params.put("error_code",-1000);
            params.put("msg","内部错误"+e);
        }
        return params;
    }

    public  Comparator<Swiper> comparatorr = new Comparator<Swiper>()
    {
        @Override
        public int compare(Swiper o1, Swiper o2){
            return o1.getRank()<o2.getRank()?-1:1;
        }
    };

    @Override
    public Map<String,Object> list(){
        params.clear();
        List<Swiper> tmp=new ArrayList<Swiper>();
        tmp=swiperDao.getAll();
        Collections.sort(tmp,comparatorr);
        List<HashMap<String,Object>> photolist = new ArrayList();

        for (Swiper i:tmp){
            HashMap<String ,Object> item= new HashMap<>();
            item.put("id",i.getId());
            item.put("rank",i.getRank());
            item.put("name",i.getName());
            item.put("date",i.getDate());
            item.put("imgurl",imgDomain+i.getName());
            photolist.add(item);
        }
        params.put("photolist",photolist);
        System.out.println(swiperDao.getAll().getClass().toString());
        params.put("num",swiperDao.getAll().size());
        params.put("error_code",0);
        return params;
    }
    @Override
    public Map<String,Object>del(int id){
        params.clear();
        List<Swiper> tmp=swiperDao.findById(id);
        if (tmp.size()==0){
            params.put("error_code",-1);
            params.put("msg","This id isn't in database");
            return params;
        }
        Swiper item=tmp.get(0);
        try{
            File photofile=new File(filePath+item.getName());
            photofile.delete();
            swiperDao.delete(id);
            params.put("error_code",0);
            params.put("msg","ok");
            List<Swiper> allPhotoItem = swiperDao.getAll();
            for (Swiper i:allPhotoItem){
                if (i.getRank()>item.getRank())
                    //把他后面的每个rank-1就可以
                    updateRankById(i.getId(),i.getRank()-1);
            }
            return params;

        }
        catch (Exception E){
            params.put("error_code",-1000);
            params.put("msg","内部错误"+E);
            System.out.println(E);
            return params;
        }
    }

    @Override
    public Map<String,Object> move(int id,int code){
        params.clear();
        List<Swiper> tmp=swiperDao.findById(id);
        if (tmp.size()==0){
            params.put("error_code",-1);
            params.put("msg","This id isn't in database");
            return params;
        }
        Swiper item=tmp.get(0);
        List<Swiper> allPhotoItem = swiperDao.getAll();
        if (code==-1){
            //左移（提升）
            if (item.getRank()==0){
                params.put("error_code",-1);
                params.put("msg","Already the first pic");
                return params;
            }
            for (Swiper i:allPhotoItem){
                if (i.getRank()==item.getRank()-1){
                    //原来占据这个位子的搬到item的rank
                    updateRankById(i.getId(),item.getRank());
                    //item的rank提升
                    updateRankById(item.getId(),item.getRank()-1);
                    params.put("error_code",0);
                    params.put("msg","ok");
                    return params;
                }
            }
        }
        if (code==1){
            if ( item.getRank()==allPhotoItem.size()-1){
                params.put("error_code",-1);
                params.put("msg","Already the last pic");
                return params;
            }
            for (Swiper i:allPhotoItem){
                if (i.getRank()==item.getRank()+1){
                    //原来占据这个位子的搬到item
                    updateRankById(i.getId(),item.getRank());
                    //item的rank降低
                    updateRankById(item.getId(),item.getRank()+1);
                    params.put("error_code",0);
                    params.put("msg","ok");
                    return params;
                }
            }
        }
        params.put("error_code",-1000);
        params.put("msg","sys Error");
        return params;
    }

    public Object updateRankById(int id,int newrank){
        Swiper swiper = new Swiper();
        swiper.setRank(newrank);
        swiper.setId(id);
        swiperDao.update(swiper);
        return 1;
    }



}
