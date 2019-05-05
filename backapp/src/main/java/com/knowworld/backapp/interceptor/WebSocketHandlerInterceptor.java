package com.knowworld.backapp.interceptor;

import com.knowworld.backapp.common.Constants;
import com.knowworld.backapp.common.ImUser;
import com.knowworld.backapp.model.domain.Member;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 用于WebSocket记录用户信息
 */
public class WebSocketHandlerInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession();
            if (session != null) {
                Member member = (Member) session.getAttribute(Constants.SESSION_MEMBER_KEY);
                attributes.put(Constants.WEB_SOCKET_USERNAME, new ImUser(member.getId(), member.getRealName()));
            }
        }
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }
}
