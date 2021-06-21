package pers.bc.utils.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.lang3.ObjectUtils;  

import pers.bc.utils.file.FileUtilbc;
import pers.bc.utils.file.StreamUtilbc;
import pers.bc.utils.pub.PubEnvUtilbc;

public class BarCodeUtils
{
    private static Double height = 5.0, width = 0.5;
    /** 是否两边留白 */
    private static Boolean withQuietZone = Boolean.FALSE;
    /** 隐藏可读文本 */
    private static Boolean hideText = Boolean.FALSE;
    
    public static void main(String[] args) throws IOException
    {
        generateBarCodeFile("0010210300062-01", "E:\\yonyou\\home\\gjgwhome\\temp\\wordtemplet\\barcode.png");
        
        // byte[] bytes = generateBarCode128("0010210300062-01", 5.0, 0.5, Boolean.FALSE, Boolean.FALSE);
        // File file = new File("E:\\yonyou\\home\\gjgwhome\\temp\\wordtemplet\\barcode.png");
        // file.getParentFile().getAbsolutePath();
        // StreamUtilbc.writeByteArrayToFile(file, bytes);
        
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param content
     * @param destPath 文件按上级目录
     * @param fileName 文件名
     * @throws IOException <br>
     * @void <br>
     * @methods pers.bc.utils.image.BarCodeUtils#generateBarCodeFile <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-20 <br>
     * @time 下午9:34:25 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void generateBarCodeFile(String content, String destPath, String fileName) throws IOException
    {
        if (PubEnvUtilbc.isEmpty(fileName)) fileName = new Random().nextInt(99999999) + ".PNG";
        generateBarCodeFile(content, destPath + File.separator + fileName);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param content
     * @param fileAbsolutePath 文件绝对路径
     * @throws IOException <br>
     * @void <br>
     * @methods pers.bc.utils.image.BarCodeUtils#generateBarCodeFile <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-20 <br>
     * @time 下午9:34:31 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void generateBarCodeFile(String content, String fileAbsolutePath) throws IOException
    {
        generateBarCodeFile(content, new File(fileAbsolutePath), height, width, withQuietZone, hideText);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param content 要生成的文本
     * @param height 条形码的高度
     * @param width 条形码的宽度
     * @param withQuietZone 是否两边留白
     * @param hideText 隐藏可读文本
     * @throws IOException <br>
     * @void <br>
     * @methods pers.bc.utils.image.BarCodeUtils#generateBarCodeFile <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-20 <br>
     * @time 下午9:33:36 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void generateBarCodeFile(String content, File file, Double height, Double width, Boolean withQuietZone, Boolean hideText)
            throws IOException
    {
        FileUtilbc.createFiles(file.getParent());
        byte[] barCodeBytes = generateBarCodeFile(content, height, width, withQuietZone, hideText);
        StreamUtilbc.writeByteArrayToFile(file, barCodeBytes);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param content 要生成的文本
     * @param height 条形码的高度
     * @param width 条形码的宽度
     * @param withQuietZone 是否两边留白
     * @param hideText 隐藏可读文本
     * @return 图片对应的字节码
     * @throws IOException <br>
     * @byte[] <br>
     * @methods pers.bc.utils.image.BarCodeUtils#generateBarCodeFile <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-20 <br>
     * @time 下午9:28:51 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static byte[] generateBarCodeFile(String content, Double height, Double width, Boolean withQuietZone, Boolean hideText)
            throws IOException
    {
        return generateBarCode128(content, height, width, withQuietZone, hideText);
    }
    
    /**
     * *********************************************************** <br>
     * *说明：生成code128条形码 <br>
     * @see <br>
     * @param content 要生成的文本
     * @param height 条形码的高度
     * @param width 条形码的宽度
     * @param withQuietZone 是否两边留白
     * @param hideText 隐藏可读文本
     * @return 图片对应的字节码<br>
     * @byte[] <br>
     * @throws IOException
     * @methods nc.impl.hi.psndoc.BarCodeUtils#generateBarCode128 <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-20 <br>
     * @time 19:39:38 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    private static byte[] generateBarCode128(String content, Double height, Double width, Boolean withQuietZone, Boolean hideText)
            throws IOException
    {
        org.krysalis.barcode4j.impl.code128.Code128Bean bean = new org.krysalis.barcode4j.impl.code128.Code128Bean();
        // 分辨率
        int dpi = 512;
        // 设置两侧是否留白
        bean.doQuietZone(withQuietZone);
        // 设置条形码高度和宽度
        bean.setBarHeight((Double) ObjectUtils.defaultIfNull(height, 9.0D));
        if (width != null) bean.setModuleWidth(width);
        // 设置文本位置（包括是否显示）
        if (hideText) bean.setMsgPosition(org.krysalis.barcode4j.HumanReadablePlacement.HRP_NONE);
        // 设置图片类型
        String format = "image/png";
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider canvas = new org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider(ous, format, dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
        // 生产条形码
        bean.generateBarcode(canvas, content);
        canvas.finish();
        byte[] byteArray = ous.toByteArray();
        
        FileUtilbc.close(ous);
        
        return byteArray;
    }
    
}
