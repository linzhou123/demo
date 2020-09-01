package com.example.demo.typehandler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * List<>包装类装换为json格式-mybatis
 * */
public class ListTypeHandler extends BaseTypeHandler<List> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSONArray.toJSONString(parameter));
    }

    @Override
    public List getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return JSONObject.parseArray(rs.getString(columnName));
    }

    @Override
    public List getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return JSONObject.parseArray(rs.getString(columnIndex));
    }

    @Override
    public List getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return JSONObject.parseArray(cs.getString(columnIndex));
    }
}
