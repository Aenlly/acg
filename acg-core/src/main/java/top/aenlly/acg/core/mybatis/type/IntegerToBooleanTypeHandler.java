package top.aenlly.acg.core.mybatis.type;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Boolean的类型转换器实现类，对应数据库的 TINYINT 类型
 *
 * @author 永不言败
 * @since 2022 3/23 12:50:15
 */
@MappedJdbcTypes(JdbcType.TINYINT)
@MappedTypes(Boolean.class)
public class IntegerToBooleanTypeHandler implements TypeHandler<Boolean> {


    @Override
    public void setParameter(PreparedStatement ps, int i, Boolean b, JdbcType jdbcType) throws SQLException {
        // 设置占位符
        ps.setString(i, String.valueOf(b ? 1 : 0));
    }

    @Override
    public Boolean getResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value.equals("0") ? false : true;
    }

    @Override
    public Boolean getResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value.equals("0") ? false : true;
    }

    @Override
    public Boolean getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value.equals("0") ? false : true;
    }
}
