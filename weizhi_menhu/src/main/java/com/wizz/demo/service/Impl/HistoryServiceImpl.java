package com.wizz.demo.service.Impl;

import com.wizz.demo.dao.HistoryDao;
import com.wizz.demo.model.WizzHistory;
import com.wizz.demo.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryDao historyDao;

    @Override
    public String getMainTitle(int id) {
        return historyDao.getMainTitleById(id);
    }

    @Override
    public String getBriefIntro(int id) {
        return historyDao.getBriefIntroById(id);
    }

    @Override
    public int updateInfo(WizzHistory wizzHistory) {
        historyDao.update(wizzHistory);
        return 0;
    }

    @Override
    public int addInfo(WizzHistory wizzHistory) {
        historyDao.addHistory(wizzHistory);
        return 0;
    }

    @Override
    public int deleteInfo(int id) {
        historyDao.deleteHistory(id);
        return 0;
    }

    @Override
    public List<WizzHistory> findAll() {

        return historyDao.findAll();
    }
}
