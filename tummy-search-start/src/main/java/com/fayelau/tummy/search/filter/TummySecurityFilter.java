package com.fayelau.tummy.search.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import com.fayelau.tummy.search.core.constants.CommonConstants;
import com.fayelau.tummy.search.core.utils.BaseSecurity;
import com.fayelau.tummy.search.entity.Session;
import com.fayelau.tummy.search.inter.service.IAuthenticateService;

import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.UserAgent;
import nl.bitwalker.useragentutils.Version;

@WebFilter(filterName = "TummySecurityFilter", urlPatterns = { "/*" })
@Order
public class TummySecurityFilter implements Filter {

    private static final String USER_AGENT = "User-Agent";
    private static final String TOKEN_KEY = "token";
    
    @Autowired
    private IAuthenticateService authenticateService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest hsRequest = (HttpServletRequest) request;
        HttpServletResponse hsResponse = (HttpServletResponse) response;
        hsResponse.setCharacterEncoding("UTF-8");

        // 获取并设置客户端信息
        UserAgent userAgent = UserAgent.parseUserAgentString(hsRequest.getHeader(USER_AGENT));
        Map<String, String> clientInfo = new HashMap<>();
        Browser browser = userAgent.getBrowser();
        Version browserVersion = userAgent.getBrowserVersion();
        String clientBrowser = "UNKNOWN";
        if (browser != null) {
            clientBrowser = browser.getName();
        }
        if (browserVersion != null) {
            clientBrowser += browserVersion.getVersion();
        }
        String clientIp = this.getIp(hsRequest);
        String clientOs = userAgent.getOperatingSystem().getName();
        clientInfo.put(CommonConstants.CLIENT_INFO_BROWSER, clientBrowser);
        clientInfo.put(CommonConstants.CLIENT_INFO_IP, clientIp);
        clientInfo.put(CommonConstants.CLIENT_INFO_OS, clientOs);
        BaseSecurity.clientInfoLocal.set(clientInfo);

        // 验证是否属于匿名访问
        try {
            String requestUri = hsRequest.getRequestURI();
            String requestMethod = hsRequest.getMethod();
            Boolean isAnonymousAccess = authenticateService.isBelongAnonymousAccess(requestUri, requestMethod);
            if (isAnonymousAccess) {
                chain.doFilter(request, response);
                return;
            }
            
            // 验证令牌并获取会话信息
            String token = hsRequest.getHeader(TOKEN_KEY);
            if (StringUtils.isEmpty(token)) {
                token = hsRequest.getParameter(TOKEN_KEY);
            }
            if (StringUtils.isEmpty(token)) {
                hsResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "禁止访问！未携带身份验证令牌，身份验证失败");
                return;
            }
            Session session = authenticateService.verifyToken(token);
            BaseSecurity.currentSessionLocal.set(session);
            chain.doFilter(request, response);
        } catch (Exception e) {
            hsResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }
    }

    private String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

}
