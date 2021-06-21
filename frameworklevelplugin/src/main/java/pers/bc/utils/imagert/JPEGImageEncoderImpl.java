package pers.bc.utils.imagert;

import java.awt.Point;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.ComponentSampleModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.RescaleOp;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.OutputStream;
import java.security.AccessController;

public class JPEGImageEncoderImpl implements JPEGImageEncoder
{
    private OutputStream outStream = null;
    private JPEGParam param = null;
    private boolean pack = false;
    
    private static final Class<OutputStream> OutputStreamClass = OutputStream.class;
    
    static
    {
        AccessController.doPrivileged(new LoadLibraryAction("jpeg"));
    }
    
    public JPEGImageEncoderImpl(OutputStream paramOutputStream)
    {
        if (paramOutputStream == null)
        {
            throw new IllegalArgumentException("OutputStream is null.");
        }
        this.outStream = paramOutputStream;
        initEncoder(OutputStreamClass);
    }
    
    public JPEGImageEncoderImpl(OutputStream paramOutputStream, JPEGEncodeParam paramJPEGEncodeParam)
    {
        this(paramOutputStream);
        setJPEGEncodeParam(paramJPEGEncodeParam);
    }
    
    public int getDefaultColorId(ColorModel paramColorModel)
    {
        boolean bool = paramColorModel.hasAlpha();
        ColorSpace localColorSpace1 = paramColorModel.getColorSpace();
        ColorSpace localColorSpace2 = null;
        switch (localColorSpace1.getType())
        {
            case 6 :
                return 1;
                
            case 5 :
                if (bool)
                {
                    return 7;
                }
                return 3;
                
            case 3 :
                if (localColorSpace2 == null)
                {
                    try
                    {
                        localColorSpace2 = ColorSpace.getInstance(1002);
                    }
                    catch (IllegalArgumentException localIllegalArgumentException)
                    {
                    }
                }
                
                if (localColorSpace1 == localColorSpace2)
                {
                    return bool ? 10 : 5;
                }
                
                return bool ? 7 : 3;
                
            case 9 :
                return 4;
        }
        return 0;
    }
    
    public synchronized OutputStream getOutputStream()
    {
        return this.outStream;
    }
    
    public synchronized void setJPEGEncodeParam(JPEGEncodeParam paramJPEGEncodeParam)
    {
        this.param = new JPEGParam(paramJPEGEncodeParam);
    }
    
    public synchronized JPEGEncodeParam getJPEGEncodeParam()
    {
        return (JPEGEncodeParam) this.param.clone();
    }
    
    public JPEGEncodeParam getDefaultJPEGEncodeParam(Raster paramRaster, int paramInt)
    {
        JPEGParam localJPEGParam = new JPEGParam(paramInt, paramRaster.getNumBands());
        localJPEGParam.setWidth(paramRaster.getWidth());
        localJPEGParam.setHeight(paramRaster.getHeight());
        
        return localJPEGParam;
    }
    
    public JPEGEncodeParam getDefaultJPEGEncodeParam(BufferedImage paramBufferedImage)
    {
        ColorModel localColorModel = paramBufferedImage.getColorModel();
        int i = getDefaultColorId(localColorModel);
        
        if (!(localColorModel instanceof IndexColorModel))
        {
            return getDefaultJPEGEncodeParam(paramBufferedImage.getRaster(), i);
        }
        
        JPEGParam localJPEGParam;
        
        if (localColorModel.hasAlpha()) localJPEGParam = new JPEGParam(i, 4);
        else
        {
            localJPEGParam = new JPEGParam(i, 3);
        }
        localJPEGParam.setWidth(paramBufferedImage.getWidth());
        localJPEGParam.setHeight(paramBufferedImage.getHeight());
        return localJPEGParam;
    }
    
    public JPEGEncodeParam getDefaultJPEGEncodeParam(int paramInt1, int paramInt2)
    {
        return new JPEGParam(paramInt2, paramInt1);
    }
    
    public JPEGEncodeParam getDefaultJPEGEncodeParam(JPEGDecodeParam paramJPEGDecodeParam) throws ImageFormatException
    {
        return new JPEGParam(paramJPEGDecodeParam);
    }
    
    public synchronized void encode(BufferedImage paramBufferedImage) throws IOException, ImageFormatException
    {
        if (this.param == null)
        {
            setJPEGEncodeParam(getDefaultJPEGEncodeParam(paramBufferedImage));
        }
        if ((paramBufferedImage.getWidth() != this.param.getWidth()) || (paramBufferedImage.getHeight() != this.param.getHeight()))
        {
            throw new ImageFormatException("Param block's width/height doesn't match the BufferedImage");
        }
        
        if (this.param.getEncodedColorID() != getDefaultColorId(paramBufferedImage.getColorModel()))
        {
            throw new ImageFormatException("The encoded COLOR_ID doesn't match the BufferedImage");
        }
        
        WritableRaster localWritableRaster = paramBufferedImage.getRaster();
        ColorModel localColorModel = paramBufferedImage.getColorModel();
        
        if ((localColorModel instanceof IndexColorModel))
        {
            IndexColorModel localIndexColorModel = (IndexColorModel) localColorModel;
            paramBufferedImage = localIndexColorModel.convertToIntDiscrete(localWritableRaster, false);
            localWritableRaster = paramBufferedImage.getRaster();
            localColorModel = paramBufferedImage.getColorModel();
        }
        
        encode(localWritableRaster, localColorModel);
    }
    
    public synchronized void encode(BufferedImage paramBufferedImage, JPEGEncodeParam paramJPEGEncodeParam) throws IOException,
            ImageFormatException
    {
        setJPEGEncodeParam(paramJPEGEncodeParam);
        encode(paramBufferedImage);
    }
    
    public void encode(Raster paramRaster) throws IOException, ImageFormatException
    {
        if (this.param == null)
        {
            setJPEGEncodeParam(getDefaultJPEGEncodeParam(paramRaster, 0));
        }
        
        if (paramRaster.getNumBands() != paramRaster.getSampleModel().getNumBands())
        {
            throw new ImageFormatException("Raster's number of bands doesn't match the SampleModel");
        }
        
        if ((paramRaster.getWidth() != this.param.getWidth()) || (paramRaster.getHeight() != this.param.getHeight()))
        {
            throw new ImageFormatException("Param block's width/height doesn't match the Raster");
        }
        
        if ((this.param.getEncodedColorID() != 0) && (this.param.getNumComponents() != paramRaster.getNumBands()))
        {
            throw new ImageFormatException("Param block's COLOR_ID doesn't match the Raster.");
        }
        
        encode(paramRaster, (ColorModel) null);
    }
    
    public void encode(Raster paramRaster, JPEGEncodeParam paramJPEGEncodeParam) throws IOException, ImageFormatException
    {
        setJPEGEncodeParam(paramJPEGEncodeParam);
        encode(paramRaster);
    }
    
    private boolean useGiven(Raster paramRaster)
    {
        SampleModel localSampleModel = paramRaster.getSampleModel();
        if (localSampleModel.getDataType() != 0)
        {
            return false;
        }
        if (!(localSampleModel instanceof ComponentSampleModel))
        {
            return false;
        }
        ComponentSampleModel localComponentSampleModel = (ComponentSampleModel) localSampleModel;
        if (localComponentSampleModel.getPixelStride() != localSampleModel.getNumBands()) return false;
        int[] arrayOfInt = localComponentSampleModel.getBandOffsets();
        for (int i = 0; i < arrayOfInt.length; i++)
        {
            if (arrayOfInt[i] != i) return false;
        }
        return true;
    }
    
    private boolean canPack(Raster paramRaster)
    {
        SampleModel localSampleModel = paramRaster.getSampleModel();
        if (localSampleModel.getDataType() != 3)
        {
            return false;
        }
        if (!(localSampleModel instanceof SinglePixelPackedSampleModel))
        {
            return false;
        }
        SinglePixelPackedSampleModel localSinglePixelPackedSampleModel = (SinglePixelPackedSampleModel) localSampleModel;
        
        int[] arrayOfInt1 = {16711680, 65280, 255, -16777216};
        int[] arrayOfInt2 = localSinglePixelPackedSampleModel.getBitMasks();
        if ((arrayOfInt2.length != 3) && (arrayOfInt2.length != 4))
        {
            return false;
        }
        for (int i = 0; i < arrayOfInt2.length; i++)
        {
            if (arrayOfInt2[i] != arrayOfInt1[i]) return false;
        }
        return true;
    }
    
    private void encode(Raster paramRaster, ColorModel paramColorModel) throws IOException, ImageFormatException
    {
        SampleModel localSampleModel = paramRaster.getSampleModel();
        
        int j = localSampleModel.getNumBands();
        int i;
        if (paramColorModel == null)
        {
            
            for (i = 0; i < j; i++)
            {
                if (localSampleModel.getSampleSize(i) > 8)
                {
                    throw new ImageFormatException("JPEG encoder can only accept 8 bit data.");
                }
            }
        }
        
        int k = this.param.getEncodedColorID();
        switch (this.param.getNumComponents())
        {
            case 1 :
                if ((k != 1) && (k != 0) && (this.param.findAPP0() != null))
                {
                    
                    throw new ImageFormatException(
                        "1 band JFIF files imply Y or unknown encoding.\nParam block indicates alternate encoding.");
                }
                
                break;
            case 3 :
                if ((k != 3) && (this.param.findAPP0() != null))
                {
                    throw new ImageFormatException("3 band JFIF files imply YCbCr encoding.\nParam block indicates alternate encoding.");
                }
                
                break;
            case 4 :
                if ((k != 4) && (this.param.findAPP0() != null))
                {
                    throw new ImageFormatException("4 band JFIF files imply CMYK encoding.\nParam block indicates alternate encoding.");
                }
                
                break;
        }
        
        if (!this.param.isImageInfoValid())
        {
            writeJPEGStream(this.param, paramColorModel, this.outStream, null, 0, 0);
            return;
        }
        
        DataBuffer localDataBuffer = paramRaster.getDataBuffer();
        
        int i1 = 0;
        int i2 = 1;
        int[] arrayOfInt1 = null;
        
        if (paramColorModel != null)
        {
            if ((paramColorModel.hasAlpha()) && (paramColorModel.isAlphaPremultiplied()))
            {
                i1 = 1;
                i2 = 0;
            }
            arrayOfInt1 = paramColorModel.getComponentSize();
            for (i = 0; i < j; i++)
            {
                if (arrayOfInt1[i] != 8)
                {
                    i2 = 0;
                }
            }
        }
        
        this.pack = false;
        Object localObject2;
        int n;
        int m;
        Object localObject1;
        if ((i2 != 0) && (useGiven(paramRaster)))
        {
            localObject2 = (ComponentSampleModel) localSampleModel;
            
            n =
                localDataBuffer.getOffset()
                    + ((ComponentSampleModel) localObject2).getOffset(paramRaster.getMinX() - paramRaster.getSampleModelTranslateX(),
                        paramRaster.getMinY() - paramRaster.getSampleModelTranslateY());
            
            m = ((ComponentSampleModel) localObject2).getScanlineStride();
            localObject1 = ((DataBufferByte) localDataBuffer).getData();
        }
        else if ((i2 != 0) && (canPack(paramRaster)))
        {
            localObject2 = (SinglePixelPackedSampleModel) localSampleModel;
            
            n =
                localDataBuffer.getOffset()
                    + ((SinglePixelPackedSampleModel) localObject2).getOffset(
                        paramRaster.getMinX() - paramRaster.getSampleModelTranslateX(),
                        paramRaster.getMinY() - paramRaster.getSampleModelTranslateY());
            
            m = ((SinglePixelPackedSampleModel) localObject2).getScanlineStride();
            localObject1 = ((DataBufferInt) localDataBuffer).getData();
            this.pack = true;
        }
        else
        {
            int[] arrayOfInt2 = new int[j];
            float[] arrayOfFloat1 = new float[j];
            for (i = 0; i < j; i++)
            {
                arrayOfInt2[i] = i;
                if (i2 == 0)
                {
                    
                    if (arrayOfInt1[i] != 8)
                    {
                        arrayOfFloat1[i] = (255.0F / ((1 << arrayOfInt1[i]) - 1));
                    }
                    else
                        arrayOfFloat1[i] = 1.0F;
                }
            }
            localObject2 =
                new ComponentSampleModel(0, paramRaster.getWidth(), paramRaster.getHeight(), j, j * paramRaster.getWidth(), arrayOfInt2);
            
            WritableRaster localWritableRaster =
                Raster.createWritableRaster((SampleModel) localObject2, new Point(paramRaster.getMinX(), paramRaster.getMinY()));
            
            if (i2 != 0)
            {
                localWritableRaster.setRect(paramRaster);
                
            }
            else
            {
                
                float[] arrayOfFloat2 = new float[j];
                
                RescaleOp localRescaleOp = new RescaleOp(arrayOfFloat1, arrayOfFloat2, null);
                
                localRescaleOp.filter(paramRaster, localWritableRaster);
                if (i1 != 0)
                {
                    int[] arrayOfInt3 = new int[j];
                    for (i = 0; i < j; i++)
                    {
                        arrayOfInt3[i] = 8;
                    }
                    ComponentColorModel localComponentColorModel =
                        new ComponentColorModel(paramColorModel.getColorSpace(), arrayOfInt3, true, true, 3, 0);
                    
                    localComponentColorModel.coerceData(localWritableRaster, false);
                }
            }
            
            localDataBuffer = localWritableRaster.getDataBuffer();
            localObject1 = ((DataBufferByte) localDataBuffer).getData();
            
            n = localDataBuffer.getOffset() + ((ComponentSampleModel) localObject2).getOffset(0, 0);
            m = ((ComponentSampleModel) localObject2).getScanlineStride();
        }
        
        verify(n, m, localDataBuffer.getSize());
        
        writeJPEGStream(this.param, paramColorModel, this.outStream, localObject1, n, m);
    }
    
    private void verify(int paramInt1, int paramInt2, int paramInt3) throws ImageFormatException
    {
        int i = this.param.getWidth();
        int j = this.param.getHeight();
        
        int k = this.pack ? 1 : this.param.getNumComponents();
        
        if ((i <= 0) || (j <= 0) || (j > Integer.MAX_VALUE / i))
        {
            throw new ImageFormatException("Invalid image dimensions");
        }
        
        if ((paramInt2 < 0) || (paramInt2 > Integer.MAX_VALUE / j) || (paramInt2 > paramInt3))
        {
            
            throw new ImageFormatException("Invalid scanline stride: " + paramInt2);
        }
        
        int m = (j - 1) * paramInt2;
        
        if ((k < 0) || (k > Integer.MAX_VALUE / i) || (k > paramInt3) || (k * i > paramInt2))
        {
            
            throw new ImageFormatException("Invalid pixel stride: " + k);
        }
        
        int n = i * k;
        if (n > Integer.MAX_VALUE - m)
        {
            throw new ImageFormatException("Invalid raster attributes");
        }
        
        int i1 = m + n;
        if ((paramInt1 < 0) || (paramInt1 > Integer.MAX_VALUE - i1))
        {
            throw new ImageFormatException("Invalid data offset");
        }
        
        int i2 = paramInt1 + i1;
        if (i2 > paramInt3)
        {
            throw new ImageFormatException("Computed buffer size doesn't match DataBuffer");
        }
    }
    
    private int getNearestColorId(ColorModel paramColorModel)
    {
        ColorSpace localColorSpace = paramColorModel.getColorSpace();
        switch (localColorSpace.getType())
        {
            case 5 :
                if (paramColorModel.hasAlpha()) return 6;
                return 2;
        }
        
        return getDefaultColorId(paramColorModel);
    }
    
    private native void initEncoder(Class<OutputStream> paramClass);
    
    private synchronized native void writeJPEGStream(JPEGEncodeParam paramJPEGEncodeParam, ColorModel paramColorModel,
            OutputStream paramOutputStream, Object paramObject, int paramInt1, int paramInt2) throws IOException, ImageFormatException;
}
