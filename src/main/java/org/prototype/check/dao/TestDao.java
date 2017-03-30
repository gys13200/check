package org.prototype.check.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by gys on 2017/3/30.
 */

@Mapper
public interface TestDao {

    int count();
}
