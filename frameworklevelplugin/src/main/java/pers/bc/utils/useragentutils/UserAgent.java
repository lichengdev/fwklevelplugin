package pers.bc.utils.useragentutils;

import java.io.Serializable;

/**
 * Container class for user-agent information with operating system and browser details. Can decode user-agent
 * strings. <br>
 * <br>
 * Resources:<br>
 * <a href="http://www.useragentstring.com">User Agent String.Com</a><br>
 * <a href="http://www.myuseragentstring.com/">My User Agent String</a><br>
 * <a href="http://www.user-agents.org">List of User-Agents</a><br>
 * <a href="http://user-agent-string.info">user-agent-string.info</a><br>
 * <a href="http://www.zytrax.com/tech/web/browser_ids.htm">Browser ID (User-Agent) Strings</a><br>
 * <a href="http://www.zytrax.com/tech/web/mobile_ids.html">Mobile Browser ID (User-Agent) Strings</a><br>
 * <a href="http://www.joergkrusesweb.de/internet/browser/user-agent.html">Browser-Kennungen</a><br>
 * <a href="http://deviceatlas.com/devices">Device Atlas - Mobile Device Intelligence</a><br>
 * <a href="http://mobileopera.com/reference/ua">Mobile Opera user-agent strings</a><br>
 * <a href="http://en.wikipedia.org/wiki/S60_platform">S60 platform</a><br>
 * <a href="http://msdn.microsoft.com/en-us/library/ms537503.aspx">Understanding User-Agent Strings</a><br>
 * <a href="http://developer.sonyericsson.com/site/global/docstools/browsing/p_browsing.jsp">Sony Ericsson Web
 * Docs & Tools</a><br>
 * <a href="http://developer.apple.com/internet/safari/faq.html#anchor2">What is the Safari user-agent
 * string</a><br>
 * <a href="http://www.pgts.com.au/pgtsj/pgtsj0208c.html">List of User Agent Strings</a><br>
 * <a href="http://blogs.msdn.com/iemobile/archive/2006/08/03/Detecting_IE_Mobile.aspx">Detecting Internet
 * Explorer Mobile's User-Agent on the server</a>
 */

/**
 * 继承 UserAgentUtils-1.14.jar
 * @qualiFild pers.bc.utils.useragentutils.UserAgent.java<br>
 * @author：LiBencheng<br>
 * @date Created on 2020-10-22<br>
 * @version 1.0<br>
 */
public class UserAgent implements Serializable
{
    
    private static final long serialVersionUID = 7025462762784240212L;
    private OperatingSystem operatingSystem = OperatingSystem.UNKNOWN;
    private Browser browser = Browser.UNKNOWN;
    private int id;
    private String userAgentString;
    
    public UserAgent(OperatingSystem operatingSystem, Browser browser)
    {
        this.operatingSystem = operatingSystem;
        this.browser = browser;
        this.id = ((operatingSystem.getId() << 16) + browser.getId());
    }
    
    public UserAgent(String userAgentString)
    {
        Browser browser = Browser.parseUserAgentString(userAgentString);
        
        OperatingSystem operatingSystem = OperatingSystem.UNKNOWN;
        
        // BOTs don't have an interesting OS for us
        if (browser != Browser.BOT) operatingSystem = OperatingSystem.parseUserAgentString(userAgentString);
        
        this.operatingSystem = operatingSystem;
        this.browser = browser;
        this.id = ((operatingSystem.getId() << 16) + browser.getId());
        this.userAgentString = userAgentString;
    }
    
    /**
     * @param userAgentString
     * @return UserAgent
     */
    public static UserAgent parseUserAgentString(String userAgentString)
    {
        return new UserAgent(userAgentString);
    }
    
    /**
     * Detects the detailed version information of the browser. Depends on the userAgent to be available. Use
     * it only after using UserAgent(String) or UserAgent.parseUserAgent(String). Returns null if it can not
     * detect the version information.
     * @return Version
     */
    public Version getBrowserVersion()
    {
        return this.browser.getVersion(this.userAgentString);
    }
    
    /**
     * @return the system
     */
    public OperatingSystem getOperatingSystem()
    {
        return operatingSystem;
    }
    
    /**
     * @return the browser
     */
    public Browser getBrowser()
    {
        return browser;
    }
    
    /**
     * Returns an unique integer value of the operating system & browser combination
     * @return the id
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * Combined string representation of both enums
     */
    public String toString()
    {
        return this.operatingSystem.toString() + "-" + this.browser.toString();
    }
    
    /**
     * Returns UserAgent based on specified unique id
     * @param id
     * @return
     */
    public static UserAgent valueOf(int id)
    {
        OperatingSystem operatingSystem = OperatingSystem.valueOf((short) (id >> 16));
        Browser browser = Browser.valueOf((short) (id & 0x0FFFF));
        return new UserAgent(operatingSystem, browser);
    }
    
    /**
     * Returns UserAgent based on combined string representation
     * @param name
     * @return
     */
    public static UserAgent valueOf(String name)
    {
        if (name == null) throw new NullPointerException("Name is null");
        
        String[] elements = name.split("-");
        
        if (elements.length == 2)
        {
            OperatingSystem operatingSystem = OperatingSystem.valueOf(elements[0]);
            Browser browser = Browser.valueOf(elements[1]);
            return new UserAgent(operatingSystem, browser);
        }
        
        throw new IllegalArgumentException("Invalid string for userAgent " + name);
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((browser == null) ? 0 : browser.hashCode());
        result = prime * result + id;
        result = prime * result + ((operatingSystem == null) ? 0 : operatingSystem.hashCode());
        return result;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final UserAgent other = (UserAgent) obj;
        if (browser == null)
        {
            if (other.browser != null) return false;
        }
        else if (!browser.equals(other.browser)) return false;
        if (id != other.id) return false;
        if (operatingSystem == null)
        {
            if (other.operatingSystem != null) return false;
        }
        else if (!operatingSystem.equals(other.operatingSystem)) return false;
        return true;
    }
    
}
