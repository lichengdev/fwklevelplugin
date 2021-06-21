package pers.bc.utils.file;

// import java.io.File;
// import java.io.FileOutputStream;
// import java.io.InputStream;
// import java.util.Map;
//
// import pers.bc.utils.pub.LoggerUtilbc;
// import pers.bc.utils.pub.PubEnvUtilbc;
// import pers.bc.utils.pub.StringUtilbc;
//
// import java.awt.geom.Rectangle2D;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;
// import java.io.OutputStream;
//
// import java.awt.Font;
// import java.awt.geom.Dimension2D;
// import java.awt.geom.Rectangle2D;
//
// import org.apache.poi.xwpf.usermodel.XWPFDocument;
// import org.apache.poi.xwpf.usermodel.XWPFParagraph;
// import org.apache.poi.xwpf.usermodel.XWPFRun;

// import com.jacob.activeX.ActiveXComponent;
// import com.jacob.com.Dispatch;
// import com.jacob.com.Variant;
// import com.spire.doc.FileFormat;
// import com.spire.doc.documents.Paragraph;
// import com.spire.doc.fields.DocPicture;
// import com.spire.pdf.PdfDocument;
// import com.spire.pdf.PdfPageBase;
// import com.spire.pdf.annotations.PdfRubberStampAnnotation;
// import com.spire.pdf.annotations.appearance.PdfAppearance;
// import com.spire.pdf.graphics.PdfImage;
// import com.spire.pdf.graphics.PdfTemplate;
// import com.spire.pdf.PdfDocument;
// import com.spire.pdf.automaticfields.PdfCompositeField;
// import com.spire.pdf.automaticfields.PdfPageCountField;
// import com.spire.pdf.automaticfields.PdfPageNumberField;
// import com.spire.pdf.graphics.PdfBrushes;
// import com.spire.pdf.graphics.PdfStringFormat;
// import com.spire.pdf.graphics.PdfTextAlignment;
// import com.spire.pdf.graphics.PdfTrueTypeFont;
// import com.spire.pdf.graphics.PdfVerticalAlignment;
//
// import com.aspose.words.Document;
// import com.aspose.words.License;
// import com.aspose.words.SaveFormat;

/**
 * 
 ** RtfCommonUtil帮手
 * @qualiFild nc.impl.hi.psndoc.NCC_Impl_HRWaPubService.java<br>
 * @author：LiBencheng<br>
 * @date Created on 2021-3-16<br>
 * @version 1.0<br>
 */
public class RtfCommonUtilbc
{
    // static String strDocTempletDirPath;
    //
    // static String TEMPRTFFILENAME = "tempRtfFileName";
    // static String HYPERLINKS = "hyperlinks";
    //
    // // 获取印章图片的宽度和高度
    // public static int signDefaultWidth = 150;
    // public static int signDefaultHeight = 150;
    // public static int signDefXWidth = 50;
    // public static int signDefYHeight = 230;
    //
    // static
    // {
    // strDocTempletDirPath = LoggerUtilbc.getWorkPath() + File.separator + "temp" + File.separator +
    // "wordtemplet" + File.separator;
    // }
    //
    // /**
    // * *********************************************************** <br>
    // * *说明： aspose-words-15.8.0-jdk16.jar和文件license.xml<br>
    // * (用于去水印，放在项目的resources下)<br>
    // * @see <br>
    // * @return
    // * @throws Exception <br>
    // * @boolean <br>
    // * @methods nc.impl.hi.psndoc.LetterHelper#getLicense <br>
    // * @author LiBencheng <br>
    // * @date Created on 2021-3-19 <br>
    // * @time 16:27:30 <br>
    // * @version 1.0 <br>
    // ************************************************************* <br>
    // */
    // public static boolean getLicense(String licenseXmlFile) throws Exception
    // {
    // boolean result = false;
    // if (PubEnvUtilbc.isEmpty(licenseXmlFile)) licenseXmlFile =
    // RtfCommonUtilbc.class.getResource("").getPath() + "license.xml";
    // InputStream is = StreamUtilbc.getFileInputStream(licenseXmlFile);
    // // Test.class.getClassLoader().getResourceAsStream("license.xml"); // Test要替换成当前类名
    // // license.xml应放在..\WebRoot\WEB-INF\classes路径下
    // License aposeLic = new License();
    // aposeLic.setLicense(is);
    // result = true;
    // FileUtilbc.close(is);
    // return result;
    // }
    //
    // /**
    // * *********************************************************** <br>
    // * *说明：word TO pdf <br>
    // * *全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换<br>
    // * @see <br>
    // * @param rtfTempFileName
    // * @return
    // * @String <br>
    // * @methods nc.impl.hi.psndoc.LetterHelper#rtf2pdf <br>
    // * @throws Exception
    // * @author LiBencheng <br>
    // * @date Created on 2021-3-19 <br>
    // * @time 14:45:55 <br>
    // * @version 1.0 <br>
    // ************************************************************* <br>
    // */
    // public static String rtf2pdf(String rtfTempFileName, String licenseXmlFile) throws Exception
    // {
    // if (!getLicense(licenseXmlFile))
    // {// 验证License 若不验证则转化出的pdf文档会有水印产生
    // return rtfTempFileName;
    // }
    // // 新建一个空白pdf文档
    // File file = new File(rtfTempFileName.replace(".doc", ".pdf"));
    // FileOutputStream os = new FileOutputStream(file);
    // // Address是将要被转化的word文档
    // // 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
    // Document doc = new Document(rtfTempFileName);
    // doc.save(os, SaveFormat.PDF);
    // FileUtilbc.close(os);
    //
    // // 加载word示例文档
    // // Document document = new Document();
    // // document.loadFromFile(pdfPath, FileFormat.Doc);
    // // // 保存结果文件
    // // document.saveToFile(savePath, FileFormat.PDF);
    // // document.close();
    //
    // // moving the rtf data into input stream to be used in RTF parser
    // // ByteArrayInputStream rtfInputStream = new ByteArrayInputStream(rtfStream);
    // // ByteArrayInputStream rtfInputStream = new
    // // ByteArrayInputStream(StreamUtilbc.readFileToByteArray(rtfTempFileName));
    //
    // // set headers
    // // resp.setHeader("Cache-Control", "no-store");
    // // resp.addHeader("Content-Type", "application/pdf");
    // // resp.addHeader("Content-Disposition", "inline; filename=Karta.pdf");
    // // resp.setStatus(HttpServletResponse.SC_OK);
    // // resp.getOutputStream().write(pdfStream.toByteArray());
    // // pdf output stream
    // // ByteArrayOutputStream pdfStream = StreamUtilbc.getByteArrayOutputStream();
    // // Document pdfDoc = new Document();
    // // PdfWriter pdfWriter = PdfWriter.getInstance(pdfDoc, pdfStream);
    // // pdfDoc.open();
    // // RtfParser rtfParser = new RtfParser(null);
    // // rtfParser.convertRtfDocument(rtfInputStream, pdfDoc);
    // // pdfDoc.close();
    // // pdfWriter.close();
    // // FileUtilbc.close(pdfStream, rtfInputStream);
    //
    // return rtfTempFileName.replace(".doc", ".pdf");
    // }
    //
    // /**
    // * *********************************************************** <br>
    // * *说明：字符串转换为rtf编码 <br>
    // * @see <br>
    // * @param bs 需要替換的内容
    // * @return
    // * @throws Exception <br>
    // * @String <br>
    // * @methods nc.impl.hi.psndoc.RTFToWordUtil#contentToRtf <br>
    // * @author LiBencheng <br>
    // * @date Created on 2021-3-11 <br>
    // * @time 16:49:47 <br>
    // * @version 1.0 <br>
    // ************************************************************* <br>
    // */
    // private static String contentToRtf(byte[] bs) throws Exception
    // {
    //
    // StringBuffer sb = new StringBuffer("");
    // char[] digital = "0123456789ABCDEF".toCharArray();
    // int bit;
    // for (int i = 0; i < bs.length; i++)
    // {
    // bit = (bs[i] & 0x0f0) >> 4;
    // sb.append("\\'");
    // sb.append(digital[bit]);
    // bit = bs[i] & 0x0f;
    // sb.append(digital[bit]);
    // }
    //
    // return sb.toString();
    // }
    //
    // /**
    // * *********************************************************** <br>
    // * *说明：替换文档的可变部分 <br>
    // * @see <br>
    // * @param content 文档内容
    // * @param key 标识
    // * @param replacecontent 替换内容
    // * @return
    // * @throws Exception <br>
    // * @String <br>
    // * @methods nc.impl.hi.psndoc.RTFToWordUtil#replaceRTF <br>
    // * @author LiBencheng <br>
    // * @date Created on 2021-3-11 <br>
    // * @time 16:47:52 <br>
    // * @version 1.0 <br>
    // ************************************************************* <br>
    // */
    // private static String replaceRTF(String content, String key, Object replacecontent) throws Exception
    // {
    // byte[] bs = null;
    // // GB18030
    // // bs = StringUtilbc.valueOfEmpty(replacecontent).getBytes(PubConsUtilbc.GB2312);
    // bs = StringUtilbc.valueOfEmpty(replacecontent).getBytes("GB18030");
    // // bs = StringUtilbc.valueOfEmpty(replacecontent).getBytes("UTF-8");
    // if (PubEnvUtilbc.equals(key, "$sign$"))
    // {
    // bs = (byte[]) replacecontent;
    // }
    //
    // String target = content.replace(key, contentToRtf(bs));
    //
    // return target;
    // }
    //
    // /**
    // * *********************************************************** <br>
    // * *说明：修改变化部分 <br>
    // * @see <br>
    // * @param dataMap 需要替换的数据
    // * @param sourcecontent
    // * @return
    // * @throws Exception <br>
    // * @String <br>
    // * @methods nc.impl.hi.psndoc.RTFToWordUtil#replaceRTF <br>
    // * @author LiBencheng <br>
    // * @date Created on 2021-3-11 <br>
    // * @time 16:44:10 <br>
    // * @version 1.0 <br>
    // ************************************************************* <br>
    // */
    // private static String replaceRTFContent(Map<String, Object> dataMap, String sourcecontent) throws
    // Exception
    // {
    // /* 修改变化部分 */
    // String targetcontent = "";
    // int i = 0;
    // for (String key : dataMap.keySet())
    // {
    // Object value = dataMap.get(key);
    // if (i == 0) targetcontent = replaceRTF(sourcecontent, key, value);
    // else
    // targetcontent = replaceRTF(targetcontent, key, value);
    //
    // i++;
    // }
    //
    // return targetcontent;
    // }
    //
    // /**
    // * *********************************************************** <br>
    // * *说明： <br>
    // * @see <br>
    // * @param rtfFileName 模板文件
    // * @param transferletterMap 模板上 key-value
    // * @throws Exception <br>
    // * @void <br>
    // * @methods nc.impl.hi.psndoc.LetterHelper#replaceRTFContent <br>
    // * @author LiBencheng <br>
    // * @date Created on 2021-3-17 <br>
    // * @time 11:26:20 <br>
    // * @version 1.0 <br>
    // ************************************************************* <br>
    // */
    // public static String replaceRTFContent(String rtfFileName, Map<String, Object> transferletterMap)
    // throws Exception
    // {
    // String targetFileName = getFullRtfFileName(null);
    //
    // byte[] readFileToByteArray = StreamUtilbc.readFileToByteArray(rtfFileName);
    // /* 字节形式读取模板文件内容,将结果转为字符串 */
    // String sourcecontent = StreamUtilbc.byteTOString(readFileToByteArray);
    // // String sourcecontent = StreamUtilbc.InputStreamTOString(inputStream, "GB18030");
    // String targetcontent = replaceRTFContent(transferletterMap, sourcecontent);
    // /* 结果输出保存到文件 */
    // FileUtilbc.createFiles(targetFileName);
    // FileUtilbc.write(new File(targetFileName), targetcontent);
    //
    // return targetFileName;
    // }
    //
    // public static String getFullRtfFileName(String strFileName)
    // {
    // if (pers.bc.utils.pub.StringUtil.isBlank(strFileName))
    // {
    // strFileName = String.valueOf(System.currentTimeMillis());
    // }
    //
    // return strDocTempletDirPath + "wordtemplet_" + strFileName + ".rtf";
    // }
    //
    // public static void pdfGenPageYesFirst(String signFilePath, String sourcePdfPath)
    // {
    // pdfGenPage(signFilePath, sourcePdfPath, sourcePdfPath, Boolean.TRUE);
    // }
    //
    // public static void pdfGenPageYesFirst(String signFilePath, String sourcePdfPath, String targetPdfPath)
    // {
    // pdfGenPage(signFilePath, sourcePdfPath, targetPdfPath, Boolean.TRUE);
    // }
    //
    // public static void pdfGenPageNoFirst(String signFilePath, String sourcePdfPath, String targetPdfPath)
    // {
    // pdfGenPage(signFilePath, sourcePdfPath, targetPdfPath, Boolean.FALSE);
    // }
    //
    // /**
    // * *********************************************************** <br>
    // * *说明： <br>
    // * @see <br>
    // * @param signFilePath 签章文件绝对路径
    // * @param sourcePdfPath 源pdf绝对路径
    // * @param targetPdfPath 目标pdf绝对路径
    // * @param isAddFirstPage 首页需要添加<br>
    // * @void <br>
    // * @methods pers.bc.utils.file.RtfCommonUtilbc#pdfGenPage <br>
    // * @author LiBencheng <br>
    // * @date Created on 2021-3-22 <br>
    // * @time 14:37:27 <br>
    // * @version 1.0 <br>
    // ************************************************************* <br>
    // */
    // public static void pdfGenPage(String signFilePath, String sourcePdfPath, String targetPdfPath, Boolean
    // isAddFirstPage)
    // {
    // // 加载PDF文档
    // PdfDocument pdf = new PdfDocument();
    // pdf.loadFromFile(sourcePdfPath);
    // // 添加一个空白页，目的为了删除jar包添加的水印，后面再移除这一页
    // pdf.getPages().add();
    // Font font2 = new Font("宋体", Font.PLAIN, 10);
    // // 创建字体
    // PdfTrueTypeFont font = new PdfTrueTypeFont(font2);
    //
    // // 遍历文档中的页
    // for (int i = 0; i < pdf.getPages().getCount(); i++)
    // {
    // if (!isAddFirstPage && i == 1) continue;
    // operatioPage(signFilePath, pdf, font, i);
    // }
    // // 移除第一个页
    // pdf.getPages().remove(pdf.getPages().get(pdf.getPages().getCount() - 1));
    // // 保存为另外一个文档
    // pdf.saveToFile(targetPdfPath);
    //
    // }
    //
    // /**
    // * *********************************************************** <br>
    // * *说明： <br>
    // * @see <br>
    // * @param signFilePath 签章文件绝对路径
    // * @param pdf 源pdf绝对路径
    // * @param font
    // * @param operatioPage 需要添加页码的页数 <br>
    // * @void <br>
    // * @methods pers.bc.utils.file.RtfCommonUtilbc#operatioPage <br>
    // * @author LiBencheng <br>
    // * @date Created on 2021-3-22 <br>
    // * @time 14:37:41 <br>
    // * @version 1.0 <br>
    // ************************************************************* <br>
    // */
    // private static void operatioPage(String signFilePath, PdfDocument pdf, PdfTrueTypeFont font, int
    // operatioPage)
    // {
    //
    // Dimension2D pageSize = pdf.getPages().get(operatioPage).getSize();
    // float y = (float) pageSize.getHeight() - 40;
    //
    // // 初始化页码域
    // PdfPageNumberField number = new PdfPageNumberField();
    //
    // // 初始化总页数域
    // PdfPageCountField count = new PdfPageCountField();
    //
    // // 创建复合域
    // PdfCompositeField compositeField = new PdfCompositeField(font, PdfBrushes.getBlack(), "第{0}页 共{1}页",
    // number, count);
    //
    // // 设置复合域内文字对齐方式
    // compositeField.setStringFormat(new PdfStringFormat(PdfTextAlignment.Right, PdfVerticalAlignment.Top));
    //
    // // 测量文字大小
    // Dimension2D textSize = font.measureString(compositeField.getText());
    //
    // // 设置复合域的在PDF页面上的位置及大小
    // compositeField.setBounds(new Rectangle2D.Float(((float) pageSize.getWidth() - (float)
    // textSize.getWidth()) / 2, y,
    // (float) textSize.getWidth(), (float) textSize.getHeight()));
    //
    // // 将复合域添加到PDF页面
    // compositeField.draw(pdf.getPages().get(operatioPage).getCanvas());
    //
    // }
    //
    // public static void pdfGenSignYesFirst(String signFilePath, String sourcePdfPath, int operatioPpage)
    // {
    // pdfGenSignYesFirst(signFilePath, sourcePdfPath, sourcePdfPath, signDefaultWidth, signDefaultHeight,
    // signDefXWidth, signDefYHeight,
    // operatioPpage);
    // }
    //
    // public static void pdfGenSignYesFirst(String signFilePath, String sourcePdfPath, String targetPdfPath,
    // int operatioPpage)
    // {
    // pdfGenSignYesFirst(signFilePath, sourcePdfPath, targetPdfPath, signDefaultWidth, signDefaultHeight,
    // signDefXWidth, signDefYHeight,
    // operatioPpage);
    // }
    //
    // public static void pdfGenSignYesFirst(String signFilePath, String sourcePdfPath, String targetPdfPath,
    // int signWidth, int signHeight,
    // int signXWidth, int signYHeight)
    // {
    // pdfGenSign(signFilePath, sourcePdfPath, targetPdfPath, signWidth, signHeight, signXWidth, signYHeight,
    // -1, Boolean.TRUE);
    // }
    //
    // public static void pdfGenSignYesFirst(String signFilePath, String sourcePdfPath, String targetPdfPath,
    // int signWidth, int signHeight,
    // int signXWidth, int signYHeight, int operatioPage)
    // {
    // pdfGenSign(signFilePath, sourcePdfPath, targetPdfPath, signWidth, signHeight, signXWidth, signYHeight,
    // operatioPage, Boolean.TRUE);
    // }
    //
    // public static void pdfGenSignNoFirst(String signFilePath, String sourcePdfPath, String targetPdfPath,
    // int signWidth, int signHeight,
    // int signXWidth, int signYHeight, int operatioPage)
    // {
    // pdfGenSign(signFilePath, sourcePdfPath, targetPdfPath, signWidth, signHeight, signXWidth, signYHeight,
    // operatioPage, Boolean.FALSE);
    // }
    //
    // /**
    // * *********************************************************** <br>
    // * 说明： pdf生成签章<br>
    // * @see <br>
    // * @param signFilePath 签章文件绝对路径
    // * @param sourcePdfPath 源pdf绝对路径
    // * @param targetPdfPath 目标pdf绝对路径
    // * @param signWidth 签章宽度
    // * @param signHeight 签章高度
    // * @param signXWidth 离X轴距离
    // * @param signYHeight 离Y轴距离
    // * @param operatioPage 需要添加签章的页数
    // * @param isAddtPage 第一页是否添加页数<br>
    // * @void <br>
    // * @methods pers.bc.utils.file.RtfCommonUtilbc#pdfGenSign <br>
    // * @author LiBencheng <br>
    // * @date Created on 2021-3-22 <br>
    // * @time 下午2:04:30 <br>
    // * @version 1.0 <br>
    // ************************************************************* <br>
    // */
    // public static void pdfGenSign(String signFilePath, String sourcePdfPath, String targetPdfPath, int
    // signWidth, int signHeight,
    // int signXWidth, int signYHeight, int operatioPage, Boolean isAddtPage)
    // {
    //
    // // 创建PdfDocument对象，加载PDF测试文档
    // PdfDocument pdf = new PdfDocument();
    // pdf.loadFromFile(sourcePdfPath);
    // // 添加一个空白页，目的为了删除jar包添加的水印，后面再移除这一页
    // pdf.getPages().add();
    // // operatioPage = operatioPage + 1;
    // operatioPage = operatioPage - 1;
    // // 获取PDF文件的页数
    // int pageCount = pdf.getPages().getCount();
    // // 指定需要添加的叶
    // if (isAddtPage && operatioPage >= 0 && operatioPage < pageCount)
    // operatioPage(signFilePath, pdf, signWidth, signHeight, signXWidth, signYHeight, operatioPage);
    // else
    // // 循环给PDF添加印章
    // for (int i = 0; i < pageCount; i++)
    // {
    // if (!isAddtPage && PubEnvUtilbc.equals(i, operatioPage)) continue;
    // operatioPage(signFilePath, pdf, signWidth, signHeight, signXWidth, signYHeight, i);
    // }
    //
    // // 移除第一个页
    // pdf.getPages().remove(pdf.getPages().get(pdf.getPages().getCount() - 1));
    // pdf.saveToFile(targetPdfPath);
    // // 保存文档
    // // doc.saveToFile("D:\\test.pdf", FileFormat.PDF);
    //
    // }
    //
    // /**
    // * *********************************************************** <br>
    // * 说明： pdf生成签章<br>
    // * @see <br>
    // * @param signFilePath 签章文件绝对路径
    // * @param pdf 源pdf绝对路径
    // * @param pdf 目标pdf绝对路径
    // * @param signWidth 签章宽度
    // * @param signHeight 签章高度
    // * @param signXWidth 离X轴距离
    // * @param signYHeight 离Y轴距离
    // * @param operatioPage 需要添加签章的页数<<br>
    // * @void <br>
    // * @methods pers.bc.utils.file.RtfCommonUtilbc#operatioPage <br>
    // * @author LiBencheng <br>
    // * @date Created on 2021-3-22 <br>
    // * @time 下午2:06:48 <br>
    // * @version 1.0 <br>
    // ************************************************************* <br>
    // */
    // private static void operatioPage(String signFilePath, PdfDocument pdf, int signWidth, int signHeight,
    // int signXWidth, int signYHeight,
    // int operatioPage)
    // {
    // // 获取文档第几页
    // PdfPageBase page = pdf.getPages().get(operatioPage);
    // // 加载印章图片
    // PdfImage image = PdfImage.fromFile(signFilePath);
    // // 获取印章图片的宽度和高度
    // // int width = 150;
    // // int height = 150;
    // // 创建PdfTemplate对象
    // PdfTemplate template = new PdfTemplate(signWidth, signHeight);
    // // 将图片绘制到模板
    // template.getGraphics().drawImage(image, 0, 0, signWidth, signHeight);
    // // 创建PdfRubebrStampAnnotation对象，指定大小和位置
    // Rectangle2D rect = new Rectangle2D.Float((float) (page.getActualSize().getWidth() - signWidth -
    // signXWidth),
    // (float) (page.getActualSize().getHeight() - signHeight - signYHeight), signWidth, signHeight);
    // PdfRubberStampAnnotation stamp = new PdfRubberStampAnnotation(rect);
    // // 创建PdfAppearance对象
    // PdfAppearance pdfAppearance = new PdfAppearance(stamp);
    // // 将模板应用为PdfAppearance的一般状态
    // pdfAppearance.setNormal(template);
    // // 将PdfAppearance 应用为图章的样式
    // stamp.setAppearance(pdfAppearance);
    // // 添加图章到PDF
    // page.getAnnotationsWidget().add(stamp);
    // }
    
}
