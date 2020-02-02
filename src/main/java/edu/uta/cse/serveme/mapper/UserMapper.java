package edu.uta.cse.serveme.mapper;

import com.github.pagehelper.Page;
import edu.uta.cse.serveme.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    User selcectByUsername(String username);

    Boolean existUsername(String username);

    Boolean existEmail(String email);

    Boolean existPhone(String phone);

    Page<User> listByParam(Map<String, Object> params);
}