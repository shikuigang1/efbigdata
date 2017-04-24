package com.efeiyi.search.scheduler;

import com.efeiyi.search.dao.TestMapper;
import com.efeiyi.search.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/4/10 0010.
 */
@Component
public class TaskJob {

    @Autowired
    private TestMapper testMapper;

    @Scheduled(cron = "0 */1 * * * ?")
    public void demoJob() {
        //读数据建立索引
/*
        HashMap<String,Long> map = new HashMap<String, Long>();
        map.put("id",0l);
        List<Test> ls = testMapper.findAll(map);

        System.out.print(ls.size());*/

    }

}
