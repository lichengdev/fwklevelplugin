package pers.bc.utils.net;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie 工具类 
 * @qualiFild pers.bc.utils.net.CookieUtilbc.java<br>
 * @author：李本城<br>
 * @date Created on 2020年4月3日<br>
 * @version 1.0<br>
 */
public final class CookieUtilbc
{
    
    /**
     * *********************************************************** <br>
     * 说明：得到Cookie的值, 不编码 <br>
     * @see <br>
     * @param request
     * @param cookieName
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.net.CookieUtilbc#getCookieValue <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午6:06:26 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName)
    {
        return getCookieValue(request, cookieName, false);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：得到Cookie的值 <br>
     * @see <br>
     * @param request
     * @param cookieName
     * @param isDecoder
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.net.CookieUtilbc#getCookieValue <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午6:06:44 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder)
    {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null)
        {
            return null;
        }
        String retValue = null;
        try
        {
            for (int i = 0; i < cookieList.length; i++)
            {
                if (cookieList[i].getName().equals(cookieName))
                {
                    if (isDecoder)
                    {
                        retValue = URLDecoder.decode(cookieList[i].getValue(), "UTF-8");
                    }
                    else
                    {
                        retValue = cookieList[i].getValue();
                    }
                    break;
                }
            }
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return retValue;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 得到Cookie的值<br>
     * @see <br>
     * @param request
     * @param cookieName
     * @param encodeString
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.net.CookieUtilbc#getCookieValue <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午6:07:06 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, String encodeString)
    {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null)
        {
            return null;
        }
        String retValue = null;
        try
        {
            for (int i = 0; i < cookieList.length; i++)
            {
                if (cookieList[i].getName().equals(cookieName))
                {
                    retValue = URLDecoder.decode(cookieList[i].getValue(), encodeString);
                    break;
                }
            }
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return retValue;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：设置Cookie的值 不设置生效时间默认浏览器关闭即失效,也不编码 <br>
     * @see <br>
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue <br>
     * @void <br>
     * @methods pers.bc.utils.net.CookieUtilbc#setCookie <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午6:10:37 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue)
    {
        setCookie(request, response, cookieName, cookieValue, -1);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 设置Cookie的值 在指定时间内生效,但不编码<br>
     * @see <br>
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxage <br>
     * @void <br>
     * @methods pers.bc.utils.net.CookieUtilbc#setCookie <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午6:10:28 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue,
            int cookieMaxage)
    {
        setCookie(request, response, cookieName, cookieValue, cookieMaxage, false);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：设置Cookie的值 不设置生效时间,但编码 <br>
     * @see <br>
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param isEncode <br>
     * @void <br>
     * @methods pers.bc.utils.net.CookieUtilbc#setCookie <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午6:10:46 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue,
            boolean isEncode)
    {
        setCookie(request, response, cookieName, cookieValue, -1, isEncode);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：设置Cookie的值 在指定时间内生效, 编码参数 <br>
     * @see <br>
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxage
     * @param isEncode <br>
     * @void <br>
     * @methods pers.bc.utils.net.CookieUtilbc#setCookie <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午6:10:09 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue,
            int cookieMaxage, boolean isEncode)
    {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, isEncode);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 设置Cookie的值 在指定时间内生效, 编码参数(指定编码)<br>
     * @see <br>
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxage
     * @param encodeString <br>
     * @void <br>
     * @methods pers.bc.utils.net.CookieUtilbc#setCookie <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午6:09:59 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue,
            int cookieMaxage, String encodeString)
    {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, encodeString);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：删除Cookie带cookie域名 <br>
     * @see <br>
     * @param request
     * @param response
     * @param cookieName <br>
     * @void <br>
     * @methods pers.bc.utils.net.CookieUtilbc#deleteCookie <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午6:09:49 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName)
    {
        doSetCookie(request, response, cookieName, "", -1, false);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：设置Cookie的值，并使其在指定时间内生效
     * @see <br>
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxage cookieMaxage cookie生效的最大秒数
     * @param isEncode <br>
     * @void <br>
     * @methods pers.bc.utils.net.CookieUtilbc#doSetCookie <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午6:09:07 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue,
            int cookieMaxage, boolean isEncode)
    {
        try
        {
            if (cookieValue == null)
            {
                cookieValue = "";
            }
            else if (isEncode)
            {
                cookieValue = URLEncoder.encode(cookieValue, "utf-8");
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0) cookie.setMaxAge(cookieMaxage);
            if (null != request)
            {// 设置域名的cookie
                String domainName = getDomainName(request);
                System.out.println(domainName);
                if (!"localhost".equals(domainName))
                {
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 设置Cookie的值，并使其在指定时间内生效
     * @see <br>
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxage cookie生效的最大秒数<br>
     * @param encodeString <br>
     * @void <br>
     * @methods pers.bc.utils.net.CookieUtilbc#doSetCookie <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午6:08:33 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue,
            int cookieMaxage, String encodeString)
    {
        try
        {
            if (cookieValue == null)
            {
                cookieValue = "";
            }
            else
            {
                cookieValue = URLEncoder.encode(cookieValue, encodeString);
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0) cookie.setMaxAge(cookieMaxage);
            if (null != request)
            {// 设置域名的cookie
                String domainName = getDomainName(request);
                System.out.println(domainName);
                if (!"localhost".equals(domainName))
                {
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * *********************************************************** <br>
     * 说明：得到cookie的域名 <br>
     * @see <br>
     * @param request
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.net.CookieUtilbc#getDomainName <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午6:08:21 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    private static final String getDomainName(HttpServletRequest request)
    {
        String domainName = null;
        
        String serverName = request.getRequestURL().toString();
        if (serverName == null || serverName.equals(""))
        {
            domainName = "";
        }
        else
        {
            serverName = serverName.toLowerCase();
            serverName = serverName.substring(7);
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if (len > 3)
            {
                // www.xxx.com.cn
                domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            }
            else if (len <= 3 && len > 1)
            {
                // xxx.com or xxx.cn
                domainName = "." + domains[len - 2] + "." + domains[len - 1];
            }
            else
            {
                domainName = serverName;
            }
        }
        
        if (domainName != null && domainName.indexOf(":") > 0)
        {
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
        return domainName;
    }
    
}
