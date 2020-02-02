package edu.uta.cse.serveme.typehandler;

import edu.uta.cse.serveme.utils.JsonUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author housirvip
 */
@MappedJdbcTypes(JdbcType.LONGVARCHAR)
public class MapTypeHandler implements TypeHandler<Object> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {

        ps.setString(i, JsonUtils.convertToString(parameter));
    }

    @Override
    public Map getResult(ResultSet rs, String columnName) throws SQLException {

        return jsonToMap(rs.getString(columnName));
    }

    @Override
    public Map getResult(ResultSet rs, int columnIndex) throws SQLException {

        return jsonToMap(rs.getString(columnIndex));
    }

    @Override
    public Map getResult(CallableStatement cs, int columnIndex) throws SQLException {

        return jsonToMap(cs.getString(columnIndex));
    }

    private Map jsonToMap(String json) throws RuntimeException {

        return JsonUtils.convertToMap(json, Object.class, Object.class);
    }
}


