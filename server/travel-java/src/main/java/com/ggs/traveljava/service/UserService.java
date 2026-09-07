package com.ggs.traveljava.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ggs.traveljava.entity.User;
import com.ggs.traveljava.mapper.UserMapper;
import com.ggs.traveljava.utils.JwtUtils;
import com.ggs.traveljava.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public LoginVO login(String username, String password) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(wrapper);

        if (user == null) {
            throw new RuntimeException("用户名或密码错误");
        }

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("用户名或密码错误");
        }

        String token = JwtUtils.generateToken(username);

        LoginVO loginVO = new LoginVO();
        loginVO.setToken(token);
        loginVO.setUsername(username);
        return loginVO;
    }

    public LoginVO register(String username, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new RuntimeException("两次密码输入不一致");
        }

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        User existUser = userMapper.selectOne(wrapper);

        if (existUser != null) {
            throw new RuntimeException("用户名已存在");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userMapper.insert(user);

        String token = JwtUtils.generateToken(username);

        LoginVO loginVO = new LoginVO();
        loginVO.setToken(token);
        loginVO.setUsername(username);
        return loginVO;
    }
}
