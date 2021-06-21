package pers.bc.utils.image;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.ImageIcon;

import org.apache.commons.io.IOUtils;

import pers.bc.utils.imagert.JPEGCodec;
import pers.bc.utils.imagert.JPEGImageEncoder;
import pers.bc.utils.pub.LoggerUtilbc;

/**
 * @qualiFild nc.pub.itf.tools.image.Encoder.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class PictureUtilbc
{
    private static final int EOF = -1;
    
    public PictureUtilbc()
    {
    }
    
    private int imgW, imgH;
    private byte[] pixAry;
    private int initCodeSize;
    private int remaining;
    private int curPixel;
    
    // GIFCOMPR.C - GIF Image compression routines
    //
    // Lempel-Ziv compression based on 'compress'. GIF modifications by
    // David Rowley (mgardi@watdcsu.waterloo.edu)
    
    // General DEFINEs
    
    static final int BITS = 12;
    
    static final int HSIZE = 5003; // 80% occupancy
    
    // GIF Image compression - modified 'compress'
    //
    // Based on: compress.c - File compression ala IEEE Computer, June 1984.
    //
    // By Authors: Spencer W. Thomas (decvax!harpo!utah-cs!utah-gr!thomas)
    // Jim McKie (decvax!mcvax!jim)
    // Steve Davies (decvax!vax135!petsd!peora!srd)
    // Ken Turkowski (decvax!decwrl!turtlevax!ken)
    // James A. Woods (decvax!ihnp4!ames!jaw)
    // Joe Orost (decvax!vax135!petsd!joe)
    
    int n_bits; // number of bits/code
    int maxbits = BITS; // user settable max # bits/code
    int maxcode; // maximum code, given n_bits
    int maxmaxcode = 1 << BITS; // should NEVER generate this code
    
    int[] htab = new int[HSIZE];
    int[] codetab = new int[HSIZE];
    
    int hsize = HSIZE; // for dynamic table sizing
    
    int free_ent = 0; // first unused entry
    
    // block compression parameters -- after all codes are used up,
    // and compression rate changes, start over.
    boolean clear_flg = false;
    
    // Algorithm: use open addressing double hashing (no chaining) on the
    // prefix code / next character combination. We do a variant of Knuth's
    // algorithm D (vol. 3, sec. 6.4) along with G. Knott's relatively-prime
    // secondary probe. Here, the modular division first probe is gives way
    // to a faster exclusive-or manipulation. Also do block compression with
    // an adaptive reset, whereby the code table is cleared when the compression
    // ratio decreases, but after the table fills. The variable-length output
    // codes are re-sized at this point, and a special CLEAR code is generated
    // for the decompressor. Late addition: construct the table according to
    // file size for noticeable speed improvement on small files. Please direct
    // questions about this implementation to ames!jaw.
    
    int g_init_bits;
    
    int ClearCode;
    int EOFCode;
    
    // output
    //
    // Output the given code.
    // Inputs:
    // code: A n_bits-bit integer. If == -1, then EOF. This assumes
    // that n_bits =< wordsize - 1.
    // Outputs:
    // Outputs code to the file.
    // Assumptions:
    // Chars are 8 bits long.
    // Algorithm:
    // Maintain a BITS character long buffer (so that 8 codes will
    // fit in it exactly). Use the VAX insv instruction to insert each
    // code in turn. When the buffer fills up empty it and start over.
    
    int cur_accum = 0;
    int cur_bits = 0;
    
    int masks[] = {
        0x0000,
        0x0001,
        0x0003,
        0x0007,
        0x000F,
        0x001F,
        0x003F,
        0x007F,
        0x00FF,
        0x01FF,
        0x03FF,
        0x07FF,
        0x0FFF,
        0x1FFF,
        0x3FFF,
        0x7FFF,
        0xFFFF};
    
    // Number of characters so far in this 'packet'
    int a_count;
    
    // Define the storage for the packet accumulator
    byte[] accum = new byte[256];
    
    // ----------------------------------------------------------------------------
    PictureUtilbc(int width, int height, byte[] pixels, int color_depth)
    {
        imgW = width;
        imgH = height;
        pixAry = pixels;
        initCodeSize = Math.max(2, color_depth);
    }
    
    // Add a character to the end of the current packet, and if it is 254
    // characters, flush the packet to disk.
    void char_out(byte c, OutputStream outs) throws IOException
    {
        accum[a_count++] = c;
        if (a_count >= 254) flush_char(outs);
    }
    
    // Clear out the hash table
    
    // table clear for block compress
    void cl_block(OutputStream outs) throws IOException
    {
        cl_hash(hsize);
        free_ent = ClearCode + 2;
        clear_flg = true;
        
        output(ClearCode, outs);
    }
    
    // reset code table
    void cl_hash(int hsize)
    {
        for (int i = 0; i < hsize; ++i)
            htab[i] = -1;
    }
    
    void compress(int init_bits, OutputStream outs) throws IOException
    {
        int fcode;
        int i /* = 0 */;
        int c;
        int ent;
        int disp;
        int hsize_reg;
        int hshift;
        
        // Set up the globals: g_init_bits - initial number of bits
        g_init_bits = init_bits;
        
        // Set up the necessary values
        clear_flg = false;
        n_bits = g_init_bits;
        maxcode = MAXCODE(n_bits);
        
        ClearCode = 1 << (init_bits - 1);
        EOFCode = ClearCode + 1;
        free_ent = ClearCode + 2;
        
        a_count = 0; // clear packet
        
        ent = nextPixel();
        
        hshift = 0;
        for (fcode = hsize; fcode < 65536; fcode *= 2)
            ++hshift;
        hshift = 8 - hshift; // set hash code range bound
        
        hsize_reg = hsize;
        cl_hash(hsize_reg); // clear hash table
        
        output(ClearCode, outs);
        
        outer_loop: while ((c = nextPixel()) != EOF)
        {
            fcode = (c << maxbits) + ent;
            i = (c << hshift) ^ ent; // xor hashing
            
            if (htab[i] == fcode)
            {
                ent = codetab[i];
                continue;
            }
            else if (htab[i] >= 0) // non-empty slot
            {
                disp = hsize_reg - i; // secondary hash (after G. Knott)
                if (i == 0) disp = 1;
                do
                {
                    if ((i -= disp) < 0) i += hsize_reg;
                    
                    if (htab[i] == fcode)
                    {
                        ent = codetab[i];
                        continue outer_loop;
                    }
                }
                while (htab[i] >= 0);
            }
            output(ent, outs);
            ent = c;
            if (free_ent < maxmaxcode)
            {
                codetab[i] = free_ent++; // code -> hashtable
                htab[i] = fcode;
            }
            else
                cl_block(outs);
        }
        // Put out the final code.
        output(ent, outs);
        output(EOFCode, outs);
    }
    
    // ----------------------------------------------------------------------------
    void encode(OutputStream os) throws IOException
    {
        os.write(initCodeSize); // write "initial code size" byte
        
        remaining = imgW * imgH; // reset navigation variables
        curPixel = 0;
        
        compress(initCodeSize + 1, os); // compress and write the pixel data
        
        os.write(0); // write block terminator
    }
    
    /**
     * *********************************************************** <br>
     * 说明： Flush the packet to disk, and reset the accumulator
     * @see
     * @param outs
     * @throws IOException
     * @void
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午6:35:24
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    void flush_char(OutputStream outs) throws IOException
    {
        if (a_count > 0)
        {
            outs.write(a_count);
            outs.write(accum, 0, a_count);
            a_count = 0;
        }
    }
    
    final int MAXCODE(int n_bits)
    {
        return (1 << n_bits) - 1;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： Return the next pixel from the image
     * @see
     * @return
     * @int
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午6:34:52
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    private int nextPixel()
    {
        if (remaining == 0) return EOF;
        
        --remaining;
        
        byte pix = pixAry[curPixel++];
        
        return pix & 0xff;
    }
    
    void output(int code, OutputStream outs) throws IOException
    {
        cur_accum &= masks[cur_bits];
        
        if (cur_bits > 0) cur_accum |= (code << cur_bits);
        else
            cur_accum = code;
        
        cur_bits += n_bits;
        
        while (cur_bits >= 8)
        {
            char_out((byte) (cur_accum & 0xff), outs);
            cur_accum >>= 8;
            cur_bits -= 8;
        }
        
        // If the next entry is going to be too big for the code size,
        // then increase it, if possible.
        if (free_ent > maxcode || clear_flg)
        {
            if (clear_flg)
            {
                maxcode = MAXCODE(n_bits = g_init_bits);
                clear_flg = false;
            }
            else
            {
                ++n_bits;
                if (n_bits == maxbits) maxcode = maxmaxcode;
                else
                    maxcode = MAXCODE(n_bits);
            }
        }
        
        if (code == EOFCode)
        {
            // At EOF, write the rest of the buffer.
            while (cur_bits > 0)
            {
                char_out((byte) (cur_accum & 0xff), outs);
                cur_accum >>= 8;
                cur_bits -= 8;
            }
            
            flush_char(outs);
        }
    }
    
    /**
     * *********************************************************** <br>
     * 说明：生成缩略图 <br>
     * @see <br>
     * @param srcPhote
     * @return <br>
     * @byte[] <br>
     * @methods nc.pub.itf.tools.image.PictureUtilbc#transPreviewPhoto <br>
     * @author licheng <br>
     * @date Created on 2019-8-26 <br>
     * @time 下午8:54:40 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static byte[] transPreviewPhoto(byte[] srcPhote)
    {
        // 源图片
        Image src = new ImageIcon(srcPhote).getImage();
        
        int w = src.getWidth(null);
        int h = src.getHeight(null);
        
        if ((w > h && w <= 120) || (w < h && h <= 120))
        {
            return srcPhote;
        }
        
        int nw = 120;
        int nh = (nw * h) / w;
        if (nh > 120)
        {
            nh = 120;
            nw = (nh * w) / h;
        }
        
        BufferedImage targetImage = new BufferedImage(nw, nh, BufferedImage.TYPE_INT_RGB);
        targetImage.getGraphics().drawImage(src, 0, 0, nw, nh, null);
        
        String fileName = System.currentTimeMillis() + ".jpg";
        File outImage = new File(fileName);
        
        // 生成缩略图
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(outImage);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
            encoder.encode(targetImage);
        }
        catch (Exception e)
        {
            
            LoggerUtilbc.getInstance("imagelogs").exception("transPreviewPhotoThw", e);
            // 删除生成的文件
            if (outImage.exists())
            {
                outImage.delete();
            }
            return null;
        }
        finally
        {
            IOUtils.closeQuietly(fos);
        }
        
        // 将缩略图转化为byte[]
        FileInputStream fis = null;
        byte[] imgData = null;
        try
        {
            imgData = new byte[(int) outImage.length()];
            fis = new FileInputStream(outImage);
            fis.read(imgData);
        }
        catch (Exception e)
        {
            LoggerUtilbc.getInstance("imagelogs").exception("transPreviewPhotoThw", e);
            // 删除生成的文件
            if (outImage.exists())
            {
                outImage.delete();
            }
            return null;
        }
        finally
        {
            IOUtils.closeQuietly(fis);
        }
        
        // 删除生成的文件
        if (outImage.exists())
        {
            outImage.delete();
        }
        
        return imgData;
    }
    
}
