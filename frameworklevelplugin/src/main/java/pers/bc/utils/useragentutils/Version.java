package pers.bc.utils.useragentutils;

/**
 * Container for general version information. All version information is stored as String as sometimes version
 * information includes alphabetical characters.
 * 
 * @qualiFild pers.bc.utils.useragentutils.Version.java<br>
 * @author：LiBencheng<br>
 * @date Created on 2020-10-22<br>
 * @version 1.0<br>
 */
public class Version implements Comparable<Version>
{
    
    String version;
    String majorVersion;
    String minorVersion;
    
    public Version(String version, String majorVersion, String minorVersion)
    {
        super();
        this.version = version;
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
    }
    
    public String getVersion()
    {
        return version;
    }
    
    public String getMajorVersion()
    {
        return majorVersion;
    }
    
    public String getMinorVersion()
    {
        return minorVersion;
    }
    
    @Override
    public String toString()
    {
        return version;
    }
    
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((majorVersion == null) ? 0 : majorVersion.hashCode());
        result = prime * result + ((minorVersion == null) ? 0 : minorVersion.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Version other = (Version) obj;
        if (majorVersion == null)
        {
            if (other.majorVersion != null) return false;
        }
        else if (!majorVersion.equals(other.majorVersion)) return false;
        if (minorVersion == null)
        {
            if (other.minorVersion != null) return false;
        }
        else if (!minorVersion.equals(other.minorVersion)) return false;
        if (version == null)
        {
            if (other.version != null) return false;
        }
        else if (!version.equals(other.version)) return false;
        return true;
    }
    
    public int compareTo(Version other)
    {
        if (other == null)
        {
            return 1;
        }
        
        String[] versionParts = version.split("\\.");
        String[] otherVersionParts = other.version.split("\\.");
        
        for (int i = 0; i < Math.min(versionParts.length, otherVersionParts.length); i++)
        {
            if (versionParts[i].length() == otherVersionParts[i].length())
            {
                int comparisonResult = versionParts[i].compareTo(otherVersionParts[i]);
                if (comparisonResult == 0)
                {
                    continue;
                }
                else
                {
                    return comparisonResult;
                }
            }
            else
            {
                return versionParts[i].length() > otherVersionParts[i].length() ? 1 : -1;
            }
        }
        
        if (versionParts.length > otherVersionParts.length)
        {
            return 1;
        }
        else if (versionParts.length < otherVersionParts.length)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
}
