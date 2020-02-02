package edu.uta.cse.serveme.typehandler;

import edu.uta.cse.serveme.utils.JsonUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author housirvip
 */
@MappedJdbcTypes(JdbcType.LONGVARCHAR)
public class ListTypeHandler implements TypeHandler<Object> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {

        ps.setString(i, JsonUtils.convertToString(parameter));
    }

    @Override
    public List getResult(ResultSet rs, String columnName) throws SQLException {

        return jsonToList(rs.getString(columnName));
    }

    @Override
    public List getResult(ResultSet rs, int columnIndex) throws SQLException {

        return jsonToList(rs.getString(columnIndex));
    }

    @Override
    public List getResult(CallableStatement cs, int columnIndex) throws SQLException {

        return jsonToList(cs.getString(columnIndex));
    }

    private List jsonToList(String json) throws RuntimeException {

        return JsonUtils.convertToList(json, Object.class);
    }
}


