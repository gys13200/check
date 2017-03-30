package org.prototype.check.service.imp;

import org.prototype.check.dao.TestDao;
import org.prototype.check.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gys on 2017/3/30.
 */

@Service
public class TestServiceImp implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public void user() {
        testDao.count();
    }
}
