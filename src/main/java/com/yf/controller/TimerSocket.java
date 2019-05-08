package com.yf.controller;

import java.io.IOException;
import java.util.Random;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.tomcat.websocket.WsContainerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.yf.util.WebSocketServer;

@Component
public class TimerSocket {

    //代表每一秒执行一次任务
    @Scheduled(fixedRate=1000)
    public void coinInfo(){
    	
    	try {
    		Random random=new Random();
			WebSocketServer.sendInfo("你好吗？"+random.nextInt(), "33");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
    }
}
