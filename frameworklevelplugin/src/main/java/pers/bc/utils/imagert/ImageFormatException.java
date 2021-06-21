package pers.bc.utils.imagert;

/**
 * Signals that an Image Format exception of some sort has occurred.
 * <p>
 * Note that the classes in the com.sun.image.codec.jpeg package are not part of the core Java APIs. They are
 * a part of Sun's JDK and JRE distributions. Although other licensees may choose to distribute these classes,
 * developers cannot depend on their availability in non-Sun implementations. We expect that equivalent
 * functionality will eventually be available in a core API or standard extension.
 * <p>
 * 
 * @author Tom Sausner
 * @see JPEGImageEncoder
 * @see JPEGImageDecoder
 * @since 1.2
 */
public class ImageFormatException extends RuntimeException
{
    
    private static final long serialVersionUID = 6746071126462144994L;
    
    /**
     * Constructs an <code>ImageFormatException</code> with no detail message.
     */
    public ImageFormatException()
    {
        super();
    }
    
    /**
     * Constructs an <code>ImageFormatException</code> with the specified detailed message.
     * 
     * @param s the message.
     */
    public ImageFormatException(String s)
    {
        super(s);
    }
}
