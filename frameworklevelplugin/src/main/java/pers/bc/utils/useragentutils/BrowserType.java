package pers.bc.utils.useragentutils;

/**
 * Enum constants classifying the different types of browsers which are common in user-agent strings
 * 
 * @qualiFild pers.bc.utils.useragentutils.BrowserType.java<br>
 * @author：LiBencheng<br>
 * @date Created on 2020-10-22<br>
 * @version 1.0<br>
 */
public enum BrowserType
{
    
    /**
     * Standard web-browser
     */
    WEB_BROWSER("Browser"),
    /**
     * Special web-browser for mobile devices
     */
    MOBILE_BROWSER("Browser (mobile)"),
    /**
     * Text only browser like the good old Lynx
     */
    TEXT_BROWSER("Browser (text only)"),
    /**
     * Email client like Thunderbird
     */
    EMAIL_CLIENT("Email Client"),
    /**
     * Search robot, spider, crawler,...
     */
    ROBOT("Robot"),
    /**
     * Downloading tools
     */
    TOOL("Downloading tool"),
    /**
     * Application
     */
    APP("Application"), UNKNOWN("unknown");
    
    private String name;
    
    private BrowserType(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
}
