package com.futao.springboot.learn.mybatisplus.typehandlers;

import com.futao.springboot.learn.mybatisplus.entity.enums.IEnum;
import com.futao.springboot.learn.mybatisplus.entity.enums.UserGenderEnum;
import com.futao.springboot.learn.mybatisplus.tool.EnumTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ft <futao@mysteel.com>
 * @date 2021/2/5
 */
@Slf4j
@MappedJdbcTypes({JdbcType.INTEGER, JdbcType.BIGINT, JdbcType.VARCHAR})
@MappedTypes({UserGenderEnum.class})
public class EnumTypeHandler<T extends IEnum<?>> extends BaseTypeHandler<IEnum<?>> {

    private Class<T> enumType;

    public EnumTypeHandler(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, IEnum<?> parameter, JdbcType jdbcType) throws SQLException {
        Object value = parameter.value();
        ps.setObject(i, value);
    }

    @Override
    public IEnum<?> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object object = rs.getObject(columnName);
        if (object != null) {
            return EnumTools.valueOf(object, enumType);
        }
        return null;
    }

    @Override
    public IEnum<?> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object object = rs.getObject(columnIndex);
        if (object != null) {
            return EnumTools.valueOf(object, enumType);
        }
        return null;
    }

    @Override
    public IEnum<?> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object object = cs.getObject(columnIndex);
        if (object != null) {
            return EnumTools.valueOf(object, enumType);
        }
        return null;
    }
}
