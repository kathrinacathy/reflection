package com.mybatis.entity;

import java.io.Serializable;

/**
 * Created by admin on 2019/7/16 14:18
 *
 * @Author: created by admin
 * @Date: created in 14:18 2019/7/16
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public interface Entity<ID extends Serializable> extends Serializable {

    ID getId();
    boolean isDeletable();
}
