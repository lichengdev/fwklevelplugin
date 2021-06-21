package pers.bc.utils.useragentutils;

/**
 * Enum constants classifying the different types of applications which are common in referrer strings
 * @qualiFild pers.bc.utils.useragentutils.ApplicationType.java<br>
 * @author：LiBencheng<br>
 * @date Created on 2020-10-22<br>
 * @version 1.0<br>
 */
public enum ApplicationType
{
    
    /**
     * Webmail service like Windows Live Hotmail and Gmail.
     */
    WEBMAIL("Webmail client"), UNKNOWN("unknown");
    
    private String name;
    
    private ApplicationType(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
}
