package org.prototype.check.base.service.imp;

import org.prototype.check.base.service.DictService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 2017/4/1.
 */

@Service
public class DictServiceImp implements DictService {
    @Override
    public Map<String, String> options(String key) {

        System.out.println("================== load:" + key);

        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "北京");
        map.put("2", "上海");
        return map;
    }
}
