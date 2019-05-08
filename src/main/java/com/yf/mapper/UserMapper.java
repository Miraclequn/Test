package com.yf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yf.entity.Permission;
import com.yf.entity.Role;
import com.yf.entity.User;
@Mapper
public interface UserMapper {
	@Select(value = {" select * from user where name = #{name}" })
    public User getUserByName(@Param("name")String name);
}
