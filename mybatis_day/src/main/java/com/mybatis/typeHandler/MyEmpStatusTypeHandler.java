package com.mybatis.typeHandler;

import com.mybatis.entity.EmpStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by admin on 2019/7/25 17:16
 *
 * @Author: created by admin
 * @Date: created in 17:16 2019/7/25
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class MyEmpStatusTypeHandler implements TypeHandler<EmpStatus> {
    @Override
    public void setParameter(PreparedStatement ps, int i, EmpStatus parameter, JdbcType jdbcType) throws SQLException {
        System.out.println("要保存的状态码："+parameter.getCode());
        ps.setString(i, parameter.getCode());
    }

    @Override
    public EmpStatus getResult(ResultSet rs, String columnName) throws SQLException {
        String code = rs.getString(columnName);
        //根据数据库中的code值返回枚举对象
        System.out.println("从数据库中获取的状态码："+code);
        EmpStatus status = EmpStatus.getEmpStatusByCode(code);

        return status;
    }

    @Override
    public EmpStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        String code = rs.getString(columnIndex);
        System.out.println("从数据库中获取的状态码："+code);
        EmpStatus status = EmpStatus.getEmpStatusByCode(code);
        return status;
    }

    @Override
    public EmpStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String code = cs.getString(columnIndex);
        System.out.println("从数据库中获取的状态码："+code);
        EmpStatus status = EmpStatus.getEmpStatusByCode(code);
        return status;
    }
}
