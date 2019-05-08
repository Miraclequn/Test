package com.yf.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yf.entity.User;
import com.yf.util.JwtUtil;
/**
 * 身份验证
 * @author Miracal
 *
 */
@Controller
@RequestMapping("/Login")
public class ShiroLoginController {
	@RequestMapping(value="/user")
	public String getallmeeting(User user,Model model) {
		System.out.println(user.getName()+":"+user.getPassword());
		User user2;
        //授权认证
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(user.getName(),user.getPassword());
        try {
	        Subject subject = SecurityUtils.getSubject();
	            //完成登录
	        subject.login(usernamePasswordToken);
	        //获得用户对象
	        user2=(User) subject.getPrincipal();
	        String uname = user2.getName();
	        Session session = subject.getSession();
	        session.setAttribute("username",uname);

	     // 正确登陆后，jwt创建token;
			String token = JwtUtil.createtokeen(uname, user2.getPassword());

		} catch (Exception e) {
			model.addAttribute("error", "密码错误或用户名不存在");
			return "fail";
		}
        return "success";
	}
}
