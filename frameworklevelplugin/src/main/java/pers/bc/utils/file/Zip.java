package pers.bc.utils.file;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * 
 * @qualiFild com.pub.utils.file.Zip.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Zip implements Serializable
{
    
    private static final long serialVersionUID = -4186888993400692177L;
    
    public Zip()
    {
        
    }
    
    @XmlAttribute(name = "file")
    private String file;
    @XmlAttribute(name = "href")
    private String href;
    @XmlAttribute(name = "digest")
    private String digest;
    @XmlAttribute(name = "location")
    private String location;
    @XmlAttribute(name = "optional")
    private boolean optional = true;
    
    public boolean isOptional()
    {
        return this.optional;
    }
    
    public void setOptional(boolean optional)
    {
        this.optional = optional;
    }
    
    public String getDigest()
    {
        return this.digest;
    }
    
    public void setDigest(String digest)
    {
        this.digest = digest;
    }
    
    public String getLocation()
    {
        return this.location;
    }
    
    public void setLocation(String location)
    {
        this.location = location;
    }
    
    public String getFile()
    {
        return this.file;
    }
    
    public void setFile(String file)
    {
        this.file = file;
    }
    
    public String getHref()
    {
        return this.href;
    }
    
    public void setHref(String href)
    {
        this.href = href;
    }
}
