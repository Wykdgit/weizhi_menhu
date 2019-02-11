package com.wizz.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface SwiperService
{
    public Map<String,Object> insert(MultipartFile file);
    public Map<String,Object> list();
    public Map<String,Object> del(int id);
    public Map<String,Object> move(int id,int code);
}
