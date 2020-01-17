package grape.common.rest.tools;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * getContextPath = /context // 部署到ROOT为空""
 * getPathInfo = null
 * getQueryString = null
 * getRequestURI = /context/news/main/111.html
 * getServletPath = /news/main/111.html
 * getRemoteAddr = 0:0:0:0:0:0:0:1
 * getRemoteAddr1 = 0:0:0:0:0:0:0:1
 * getLocalAddr = 0:0:0:0:0:0:0:1
 * getRequestURL = http://localhost:8080/context/news/main/111.html
 * protocol = HTTP/1.1
 * getServerPort = 8080
 * getScheme = http
 * getServerName = localhost
 * getServletContext = org.apache.catalina.core.ApplicationContextFacade@4f43c2d3
 * <p>
 * <p>
 * Created by yw on 2016/2/21.
 */
@Slf4j
public class RequestTool {

    /**
     * 获得用户远程地址,ip地址
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Real-IP");
        if (!StringUtils.isEmpty(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        } else if (!StringUtils.isEmpty(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        } else if (!StringUtils.isEmpty(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }

    /**
     * 获取取cookie
     *
     * @param request
     *
     * @return
     */
    public static Cookie[] getCookies(HttpServletRequest request) {
        return request.getCookies();
    }

    /**
     * 根据cookie名获取
     *
     * @param name
     * @param request
     *
     * @return
     */
    public static Cookie getCookieByName(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * 是否是Ajax异步请求
     *
     * @param request
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {

        String accept = request.getHeader("accept");
        String xRequestedWith = request.getHeader("X-Requested-With");

        // 如果是异步请求或是手机端，则直接返回信息
        return ((accept != null && accept.indexOf("application/json") != -1
                || (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1)
        ));
    }

    /**
     * spring环境中获取当前请求对象
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 获得站点url
     *
     * @return
     */
    public static String getWebUrl() {
        final HttpServletRequest request = getRequest();
        String url = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() != 80) {
            url += ":" + request.getServerPort();
        }
        url += request.getContextPath();
        return url;
    }

    /**
     * 获取headers
     *
     * @param request
     *
     * @return
     */
    public static Map<String, String> getHeaderText(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;

    }

    /**
     * 获取当前访问的域名
     *
     * @param request
     * @param includeScheme 是否包含scheme
     * @param includePort   是否包含接口
     *
     * @return http://localhost:8080
     */
    public static String getDomain(HttpServletRequest request, boolean includeScheme, boolean includePort) {
        String _scheme = request.getScheme();
        String _serverName = request.getServerName();
        int _port = request.getServerPort();
        String r = _serverName;
        if (includeScheme) {
            r = _scheme + "://" + r;
        }
        if (includePort) {
            r = r + ":" + _port;
        }
        return r;
    }

    /**
     * @param request
     * @param includeContextPath
     *
     * @return
     */
    public static String[] resolveRequestURI(HttpServletRequest request, boolean includeContextPath) {
        String[] r = null;
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();

        String siteRequestUri = requestURI;
        if (!includeContextPath && !StringUtils.isEmpty(contextPath)) {
            siteRequestUri = siteRequestUri.substring(contextPath.length());
        }
        r = siteRequestUri.split("/");
        return r;
    }

    /**
     * 获取当前项目的实际地址存放路径
     *
     * @param request
     *
     * @return
     */
    public static String getWebappRealPath(HttpServletRequest request) {

        return request.getServletContext().getRealPath("");
    }
}
