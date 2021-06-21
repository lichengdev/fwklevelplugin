package pers.bc.utils.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;

/**
 * 
 * @qualiFild com.pub.utils.file.InOutUtil.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public final class InOutUtil
{
    public static final int DEFAULT_COPY_BUFFER_SIZE = 8192;
    public static final long DEFAULT_COPY_AMOUNT = Long.MAX_VALUE;
    
    public InOutUtil()
    {
    }
    
    public static int readLine(InputStream paramInputStream, OutputStream paramOutputStream) throws IOException
    {
        int i = 0;
        for (;;)
        {
            int j = paramInputStream.read();
            if (j == -1)
            {
                break;
            }
            i++;
            paramOutputStream.write(j);
            if (j == 10)
            {
                break;
            }
        }
        return i;
    }
    
    public static byte[] serialize(Serializable s) throws IOException
    {
        if (s == null) return null;
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bo);
        os.writeObject(s);
        return bo.toByteArray();
    }
    
    public static Serializable deserialize(byte[] ba) throws IOException, ClassNotFoundException
    {
        Serializable value = null;
        
        if (ba == null) return null;
        ByteArrayInputStream bi = new ByteArrayInputStream(ba);
        ObjectInputStream is = new ObjectInputStream(bi);
        value = (Serializable) is.readObject();
        
        return value;
    }
    
    public static long copy(InputStream paramInputStream, OutputStream paramOutputStream) throws IOException
    {
        return copy(paramInputStream, paramOutputStream, Long.MAX_VALUE, 8192);
    }
    
    public static long copy(InputStream paramInputStream, OutputStream paramOutputStream, long paramLong) throws IOException
    {
        return copy(paramInputStream, paramOutputStream, paramLong, 8192);
    }
    
    public static long copy(InputStream paramInputStream, OutputStream paramOutputStream, long paramLong, int paramInt) throws IOException
    {
        int i = (int) Math.min(paramInt, paramLong);
        byte[] arrayOfByte = new byte[i];
        long l = 0L;
        int j;
        while ((l < paramLong) && (-1 != (j = paramInputStream.read(arrayOfByte, 0, i))))
        {
            paramOutputStream.write(arrayOfByte, 0, j);
            if (j > Long.MAX_VALUE - l)
            {
                l = Long.MAX_VALUE;
            }
            else
            {
                l += j;
            }
            if (l >= paramLong)
            {
                return l;
            }
            i = (int) Math.min(paramInt, paramLong - l);
        }
        return l;
    }
    
    public static long copy(Reader paramReader, Writer paramWriter) throws IOException
    {
        return copy(paramReader, paramWriter, Long.MAX_VALUE, 8192);
    }
    
    public static long copy(Reader paramReader, Writer paramWriter, long paramLong) throws IOException
    {
        return copy(paramReader, paramWriter, paramLong, 8192);
    }
    
    public static long copy(Reader paramReader, Writer paramWriter, long paramLong, int paramInt) throws IOException
    {
        int i = (int) Math.min(paramInt, paramLong);
        char[] arrayOfChar = new char[i];
        long l = 0L;
        int j;
        while ((l < paramLong) && (-1 != (j = paramReader.read(arrayOfChar, 0, i))))
        {
            paramWriter.write(arrayOfChar, 0, j);
            if (j > Long.MAX_VALUE - l)
            {
                l = Long.MAX_VALUE;
            }
            else
            {
                l += j;
            }
            if (l >= paramLong)
            {
                return l;
            }
            i = (int) Math.min(paramInt, paramLong - l);
        }
        return l;
    }
}
