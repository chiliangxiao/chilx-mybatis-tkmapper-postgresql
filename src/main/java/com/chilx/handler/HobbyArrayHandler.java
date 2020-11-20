package com.chilx.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chilx.entity.Hobby;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.postgresql.util.PGobject;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * mybatis 数组类型处理器
 *
 * @author chilx
 * @date 2020/11/20
 **/
@MappedJdbcTypes(JdbcType.OTHER)
@MappedTypes(Hobby[].class)
public class HobbyArrayHandler extends BaseTypeHandler<Hobby[]> {
    private static final PGobject PG_OBJECT = new PGobject();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Hobby[] parameter, JdbcType jdbcType)
            throws SQLException {
        if (ps != null) {
            PG_OBJECT.setType("jsonb");
            PG_OBJECT.setValue(JSON.toJSONString(parameter));
            ps.setObject(i, PG_OBJECT);
        }
    }

    @Override
    public Hobby[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getArray(rs.getObject(columnName));
    }

    @Override
    public Hobby[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {

        return getArray(rs.getObject(columnIndex));
    }

    @Override
    public Hobby[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {

        return getArray(cs.getObject(columnIndex));
    }


    private Hobby[] getArray(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            Object value = JSONObject.parseObject(JSON.toJSONString(obj)).get("value");
            return JSON.parseObject(value.toString(), Hobby[].class);
        } catch (Exception ignored) {
        }
        return null;
    }
}
