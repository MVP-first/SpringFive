package com.aaa.sb.service.power;

import com.aaa.sb.dao.power.StationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:StationServiceImpl
 * discription:
 * author:cmq
 * createTime:2018-12-27 20:57
 */
@Service
public class StationServiceImpl implements StationService{

    @Autowired
    private StationDao stationDao;

    @Override
    public List<Map> getList(Map map) {
        return stationDao.getList(map);
    }

    @Override
    public int addStation(Map map) {
        return stationDao.addStation(map);
    }

    @Override
    public int updateStation(Map map) {
        return stationDao.updateStation(map);
    }

    @Override
    public int deleteStation(Map map) {
        return stationDao.deleteStation(map);
    }
}
