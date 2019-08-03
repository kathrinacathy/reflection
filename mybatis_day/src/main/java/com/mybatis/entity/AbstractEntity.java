package com.mybatis.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by admin on 2019/7/16 14:16
 *
 * @Author: created by admin
 * @Date: created in 14:16 2019/7/16
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public abstract class AbstractEntity<ID extends Serializable>  implements Entity  {

    protected ID id;

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public boolean isDeletable() {
        return true;
    }

    @Override
    public String toString() {
         ReflectionToStringBuilder.toString(this,ToStringStyle.DEFAULT_STYLE);
        return "";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity<?> that = (AbstractEntity<?>) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }
}
