package com.efeiyi.data.demo;

import com.efeiyi.data.service.DemoService;
import com.efeiyi.data.util.IpUtil;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public class DemoServiceImpl implements DemoService {

    public String sayIp() {
        return IpUtil.getMyIp();
    }
}
