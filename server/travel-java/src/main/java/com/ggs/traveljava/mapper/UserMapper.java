package com.ggs.traveljava.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ggs.traveljava.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
