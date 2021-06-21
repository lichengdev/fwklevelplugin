package pers.bc.utils.imagert;

import java.awt.Point;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.InputStream;
import java.security.AccessController;

public class JPEGImageDecoderImpl implements JPEGImageDecoder
{
    private static final Class<InputStream> InputStreamClass = InputStream.class;
    
    private JPEGDecodeParam param = null;
    private InputStream input = null;
    private WritableRaster aRas = null;
    private BufferedImage aBufImg = null;
    private ColorModel cm = null;
    private boolean unpack = false;
    private boolean flip = false;
    
    static
    {
        AccessController.doPrivileged(new LoadLibraryAction("jpeg"));
    }
    
    public JPEGImageDecoderImpl(InputStream paramInputStream)
    {
        if (paramInputStream == null)
        {
            throw new IllegalArgumentException("InputStream is null.");
        }
        this.input = paramInputStream;
        initDecoder(InputStreamClass);
    }
    
    public JPEGImageDecoderImpl(InputStream paramInputStream, JPEGDecodeParam paramJPEGDecodeParam)
    {
        this(paramInputStream);
        setJPEGDecodeParam(paramJPEGDecodeParam);
    }
    
    public JPEGDecodeParam getJPEGDecodeParam()
    {
        if (this.param != null)
        {
            return (JPEGDecodeParam) this.param.clone();
        }
        return null;
    }
    
    public void setJPEGDecodeParam(JPEGDecodeParam paramJPEGDecodeParam)
    {
        this.param = ((JPEGDecodeParam) paramJPEGDecodeParam.clone());
    }
    
    public synchronized InputStream getInputStream()
    {
        return this.input;
    }
    
    public synchronized Raster decodeAsRaster() throws ImageFormatException
    {
        try
        {
            this.param = readJPEGStream(this.input, this.param, false);
        }
        catch (IOException localIOException)
        {
            System.out.println("Can't open input Stream" + localIOException);
            localIOException.printStackTrace();
        }
        
        return this.aRas;
    }
    
    public synchronized BufferedImage decodeAsBufferedImage() throws ImageFormatException
    {
        try
        {
            this.param = readJPEGStream(this.input, this.param, true);
        }
        catch (IOException localIOException)
        {
            System.out.println("Can't open input Stream" + localIOException);
            localIOException.printStackTrace();
        }
        return this.aBufImg;
    }
    
    private void readTables() throws IOException
    {
        try
        {
            this.param = readJPEGStream(this.input, null, false);
        }
        catch (ImageFormatException localImageFormatException)
        {
            localImageFormatException.printStackTrace();
        }
    }
    
    private int getDecodedColorModel(int paramInt, boolean paramBoolean) throws ImageFormatException
    {
        int[] arrayOfInt1 = {8};
        int[] arrayOfInt2 = {8, 8, 8};
        int[] arrayOfInt3 = {8, 8, 8, 8};
        
        this.cm = null;
        this.unpack = false;
        this.flip = false;
        
        if (!paramBoolean)
        {
            return paramInt;
        }
        switch (paramInt)
        {
            case 1 :
                this.cm = new ComponentColorModel(ColorSpace.getInstance(1003), arrayOfInt1, false, false, 1, 0);
                
                return paramInt;
            case 5 :
                this.cm = new ComponentColorModel(ColorSpace.getInstance(1002), arrayOfInt2, false, false, 1, 0);
                
                return paramInt;
            case 10 :
                this.cm = new ComponentColorModel(ColorSpace.getInstance(1002), arrayOfInt3, true, false, 3, 0);
                
                return paramInt;
                
            case 2 :
            case 3 :
                this.unpack = true;
                this.cm = new DirectColorModel(24, 16711680, 65280, 255);
                return 2;
                
            case 8 :
            case 9 :
                this.flip = true;
            case 6 :
            case 7 :
                this.unpack = true;
                this.cm = new DirectColorModel(ColorSpace.getInstance(1000), 32, 16711680, 65280, 255, -16777216, false, 3);
                
                return 6;
        }
        
        throw new ImageFormatException("Can't construct a BufferedImage for given COLOR_ID");
    }
    
    private Object allocateDataBuffer(int paramInt1, int paramInt2, int paramInt3)
    {
        Object localObject;
        
        if (this.unpack)
        {
            int[] arrayOfInt;
            if (paramInt3 == 3)
            {
                arrayOfInt = new int[]{16711680, 65280, 255};
                this.aRas = Raster.createPackedRaster(3, paramInt1, paramInt2, arrayOfInt, new Point(0, 0));
            }
            else if (paramInt3 == 4)
            {
                arrayOfInt = new int[]{16711680, 65280, 255, -16777216};
                this.aRas = Raster.createPackedRaster(3, paramInt1, paramInt2, arrayOfInt, new Point(0, 0));
            }
            else
            {
                throw new ImageFormatException("Can't unpack with anything other than 3 or 4 components");
            }
            
            localObject = ((DataBufferInt) this.aRas.getDataBuffer()).getData();
        }
        else
        {
            this.aRas = Raster.createInterleavedRaster(0, paramInt1, paramInt2, paramInt3, new Point(0, 0));
            
            localObject = ((DataBufferByte) this.aRas.getDataBuffer()).getData();
        }
        
        if (this.cm != null)
        {
            this.aBufImg = new BufferedImage(this.cm, this.aRas, true, null);
        }
        return localObject;
    }
    
    private native void initDecoder(Class<InputStream> paramClass);
    
    private synchronized native JPEGDecodeParam readJPEGStream(InputStream paramInputStream, JPEGDecodeParam paramJPEGDecodeParam,
            boolean paramBoolean) throws IOException, ImageFormatException;
}
