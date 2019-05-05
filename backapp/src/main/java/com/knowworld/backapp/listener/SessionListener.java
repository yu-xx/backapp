package com.knowworld.backapp.listener;

import com.knowworld.backapp.common.Constants;
import com.knowworld.backapp.handler.WebSocketHandler;
import com.knowworld.backapp.model.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Autowired
    WebSocketHandler webSocketHandler;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println(httpSessionEvent.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        Member member = (Member) session.getAttribute(Constants.SESSION_MEMBER_KEY);
        if (member != null) {
            webSocketHandler.offLine(member.getId());
        }
    }
}
