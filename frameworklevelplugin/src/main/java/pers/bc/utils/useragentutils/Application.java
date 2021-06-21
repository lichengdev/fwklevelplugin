package pers.bc.utils.useragentutils;

/**
 * Enum constants for internet applications like web-application and rich internet application.
 * @qualiFild pers.bc.utils.useragentutils.Application.java<br>
 * @author：LiBencheng<br>
 * @date Created on 2020-10-22<br>
 * @version 1.0<br>
 */
public enum Application
{
    
    HOTMAIL(Manufacturer.MICROSOFT, 1, "Windows Live Hotmail", new String[]{"mail.live.com", "hotmail.msn"}, ApplicationType.WEBMAIL), GMAIL(
            Manufacturer.GOOGLE, 5, "Gmail", new String[]{"mail.google.com"}, ApplicationType.WEBMAIL), YAHOO_MAIL(Manufacturer.YAHOO, 10,
            "Yahoo Mail", new String[]{"mail.yahoo.com"}, ApplicationType.WEBMAIL), COMPUSERVE(Manufacturer.COMPUSERVE, 20, "Compuserve",
            new String[]{"csmail.compuserve.com"}, ApplicationType.WEBMAIL), AOL_WEBMAIL(Manufacturer.AOL, 30, "AOL webmail",
            new String[]{"webmail.aol.com"}, ApplicationType.WEBMAIL),
    /**
     * MobileMe webmail client by Apple. Previously known as .mac.
     */
    MOBILEME(Manufacturer.APPLE, 40, "MobileMe", new String[]{"www.me.com"}, ApplicationType.WEBMAIL),
    /**
     * Mail.com Mail.com provides consumers with web-based e-mail services
     */
    MAIL_COM(Manufacturer.MMC, 50, "Mail.com", new String[]{".mail.com"}, ApplicationType.WEBMAIL),
    /**
     * Popular open source webmail client. Often installed by providers or privately.
     */
    HORDE(Manufacturer.OTHER, 50, "horde", new String[]{"horde"}, ApplicationType.WEBMAIL), OTHER_WEBMAIL(Manufacturer.OTHER, 60,
            "Other webmail client", new String[]{"webmail", "webemail"}, ApplicationType.WEBMAIL), UNKNOWN(Manufacturer.OTHER, 0,
            "Unknown", new String[0], ApplicationType.UNKNOWN);
    
    private final short id;
    private final String name;
    private final String[] aliases;
    private final ApplicationType applicationType;
    private final Manufacturer manufacturer;
    
    private Application(Manufacturer manufacturer, int versionId, String name, String[] aliases, ApplicationType applicationType)
    {
        this.id = (short) ((manufacturer.getId() << 8) + (byte) versionId);
        this.name = name;
        this.aliases = aliases;
        this.applicationType = applicationType;
        this.manufacturer = manufacturer;
    }
    
    public short getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    /**
     * @return the applicationType
     */
    public ApplicationType getApplicationType()
    {
        return applicationType;
    }
    
    /**
     * @return the manufacturer
     */
    public Manufacturer getManufacturer()
    {
        return manufacturer;
    }
    
    /*
     * Checks if the given referrer string matches to the application. Only checks for one specific
     * application.
     */
    public boolean isInReferrerString(String referrerString)
    {
        for (String alias : aliases)
        {
            if (referrerString.toLowerCase().indexOf(alias.toLowerCase()) != -1) return true;
        }
        return false;
    }
    
    /*
     * Iterates over all Application to compare the signature with the referrer string. If no match can be
     * found Application.UNKNOWN will be returned.
     */
    public static Application parseReferrerString(String referrerString)
    {
        // skip the empty and "-" referrer
        if (referrerString != null && referrerString.length() > 1)
        {
            for (Application applicationInList : Application.values())
            {
                if (applicationInList.isInReferrerString(referrerString)) return applicationInList;
            }
        }
        return Application.UNKNOWN;
    }
    
    /**
     * Returns the enum constant of this type with the specified id. Throws IllegalArgumentException if the
     * value does not exist.
     * 
     * @param id
     * @return
     */
    public static Application valueOf(short id)
    {
        for (Application application : Application.values())
        {
            if (application.getId() == id) return application;
        }
        
        // same behavior as standard valueOf(string) method
        throw new IllegalArgumentException("No enum const for id " + id);
    }
    
}
