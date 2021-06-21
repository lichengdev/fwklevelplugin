package pers.bc.utils.pub;

import java.io.File;
import java.io.InputStream;

import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamSource;

/**
 * 
 * @qualiFild nc.pub.itf.tools.pub.XMLUtils.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public final class XMLUtilbc
{
    private static final SAXTransformerFactory saxFactory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
    
    private static TransformerFactory transformerFactory;
    
    public XMLUtilbc()
    {
    }
    
    public static TransformerHandler createTransformerHandler() throws TransformerConfigurationException
    {
        return saxFactory.newTransformerHandler();
    }
    
    public static TransformerHandler createTransformerHandler(Templates template) throws TransformerConfigurationException
    {
        return saxFactory.newTransformerHandler(template);
    }
    
    public static Templates createTemplate(InputStream is) throws TransformerConfigurationException
    {
        return createTemplate(new StreamSource(is));
    }
    
    public static Templates createTemplate(File file) throws TransformerConfigurationException
    {
        return createTemplate(new StreamSource(file));
    }
    
    private static Templates createTemplate(StreamSource streamSource) throws TransformerConfigurationException
    {
        if (transformerFactory == null)
        {
            synchronized (XMLUtilbc.class)
            {
                if (transformerFactory == null)
                {
                    transformerFactory = TransformerFactory.newInstance();
                }
            }
        }
        return transformerFactory.newTemplates(streamSource);
    }
}
