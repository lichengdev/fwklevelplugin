//package pers.bc.utils.yonyou;
//
//import java.io.Serializable;
//
//import pers.bc.utils.Bean.BeanHelper;
//import pers.bc.utils.Bean.BeanStruct;
//import pers.bc.utils.Bean.BeanUtil;
//import pers.bc.utils.Bean.Factory;
//import pers.bc.utils.Bean.MethodInfo;
//import pers.bc.utils.Bean.PropDescriptor;
//import pers.bc.utils.Bean.ReflectionUtilbc;
//import pers.bc.utils.cons.FTPUtilbc;
//import pers.bc.utils.cons.IFileStorageConst;
//import pers.bc.utils.cons.ObjectFilter;
//import pers.bc.utils.cons.ObjectHandler;
//import pers.bc.utils.cons.ObjectProcess;
//import pers.bc.utils.cons.PropertyFilter;
//import pers.bc.utils.cons.PubConsUtilbc;
//import pers.bc.utils.encrypt.ASEUtil;
//import pers.bc.utils.encrypt.Base32;
//import pers.bc.utils.encrypt.Base64;
//import pers.bc.utils.encrypt.Base64Ext;
//import pers.bc.utils.encrypt.BlowfishUtil;
//import pers.bc.utils.encrypt.DESUtil;
//import pers.bc.utils.encrypt.RsaUtil;
//import pers.bc.utils.encrypt.SecureHA;
//import pers.bc.utils.encrypt.SecurityHelper;
//import pers.bc.utils.enums.DataBaseType;
//import pers.bc.utils.file.FileEncodingUtil;
//import pers.bc.utils.file.FileHelper;
//import pers.bc.utils.file.FilePathUtil;
//import pers.bc.utils.file.FileReadImpl;
//import pers.bc.utils.file.FileType;
//import pers.bc.utils.file.FileTypeImpl;
//import pers.bc.utils.file.FileUtilbc;
//import pers.bc.utils.file.InOutUtil;
//import pers.bc.utils.file.StreamUtilbc;
//import pers.bc.utils.file.Zip;
//import pers.bc.utils.file.ZipUtils;
//import pers.bc.utils.ftp.FTPConstant;
//import pers.bc.utils.ftp.FTPLog;
//import pers.bc.utils.ftp.FTPUtilImpl;
//import pers.bc.utils.ftp.FTPVo;
//import pers.bc.utils.ftp.FileAttr;
//import pers.bc.utils.ftp.UfsHostURI;
//import pers.bc.utils.image.CaptchaUtil;
//import pers.bc.utils.image.GifEncoder;
//import pers.bc.utils.image.PictureUtilbc;
//import pers.bc.utils.image.Quant;
//import pers.bc.utils.image.RMPhotoUtils;
//import pers.bc.utils.map.CoordinateUtil;
//import pers.bc.utils.math.MathCircleUtilbc;
//import pers.bc.utils.math.MathCuboidUtilbc;
//import pers.bc.utils.math.MathCylinderUtilbc;
//import pers.bc.utils.math.MathFunctionUtilbc;
//import pers.bc.utils.math.MathNumberUtilbc;
//import pers.bc.utils.math.MathQuadrangleUtilbc;
//import pers.bc.utils.math.MathRandomUtilbc;
//import pers.bc.utils.math.MathTriangleUtilbc;
//import pers.bc.utils.math.XMathUtilbc;
//import pers.bc.utils.net.HttpClientUtilbc;
//import pers.bc.utils.net.WebBrowser;
//import pers.bc.utils.pub.AppDebug;
//import pers.bc.utils.pub.ArrayUtilbc;
//import pers.bc.utils.pub.AssertUtil;
//import pers.bc.utils.pub.CharUtil;
//import pers.bc.utils.pub.CharsetUtil;
//import pers.bc.utils.pub.CollectionUtil;
//import pers.bc.utils.pub.DateUtil;
//import pers.bc.utils.pub.IntegerUtils;
//import pers.bc.utils.pub.LoggerUtilbc;
//import pers.bc.utils.pub.PropertiesUtilbc;
//import pers.bc.utils.pub.RandomUtil;
//import pers.bc.utils.pub.RegUtil;
//import pers.bc.utils.pub.StringUtil;
//import pers.bc.utils.pub.StringUtilbc;
//import pers.bc.utils.pub.SysUtil;
//import pers.bc.utils.pub.XMLUtilbc;
//import pers.bc.utils.sql.BeanResultSetProcessor;
//import pers.bc.utils.sql.CountVO;
//import pers.bc.utils.sql.DBToJavaVO;
//import pers.bc.utils.sql.DBUtilbc;
//import pers.bc.utils.sql.ProcessorUtilslbc;
//import pers.bc.utils.sql.ResultSetProcessor;
//import pers.bc.utils.sql.SQLHelper;
//import pers.bc.utils.test.TestTask;
//import pers.bc.utils.thread.HandleCallable;
//import pers.bc.utils.thread.IThreadTask;
//import pers.bc.utils.thread.IThreadTaskImp;
//import pers.bc.utils.thread.MultiThreadUtils;
//import pers.bc.utils.thread.ThreadResultBean;
//import pers.bc.utils.throwable.ExceptionUtilbc;
//import pers.bc.utils.throwable.MylbcException;
//import pers.bc.utils.webservice.My_Impl_PubWebService;
//import pers.bc.utils.webservice.My_Itf_PubWebService;
//import pers.bc.utils.webservice.WebServiceTest;
//import pers.bc.utils.webservice.WebServiceUtilbc;
//import pers.bc.utils.webservice.WebServicelbc;
//
///**
// * 常用工具类集合
// * 
// * @qualiFild pers.bc.utils.yonyou.PubUtilbc.java<br>
// * @author：Li Bencheng<br>
// * @date Created on 2020年4月25日<br>
// * @version 1.0<br>
// */
//@SuppressWarnings("rawtypes")
//public final class PubUtilbc implements Serializable
//{
//    
//    private static final long serialVersionUID = -5549483586504563636L;
//    
//    public static MathCircleUtilbc mathCircleUtilbc = new MathCircleUtilbc();
//    public static MathCuboidUtilbc mathCuboidUtilbc = new MathCuboidUtilbc();
//    public static MathCylinderUtilbc mathCylinderUtilbc = new MathCylinderUtilbc();
//    public static MathFunctionUtilbc mathFunctionUtilbc = new MathFunctionUtilbc();
//    public static MathNumberUtilbc mathNumberUtilbc = new MathNumberUtilbc();
//    public static MathQuadrangleUtilbc mathQuadrangleUtilbc = new MathQuadrangleUtilbc();
//    public static MathRandomUtilbc mathRandomUtilbc = new MathRandomUtilbc();
//    public static MathTriangleUtilbc mathTriangleUtilbc = new MathTriangleUtilbc();
//    public static XMathUtilbc xMathUtilbc = new XMathUtilbc();
//    
//    public static ArrayUtilbc arrayUtilbc;
//    public static PropDescriptor propDescriptor;
//    public static FTPUtilImpl fTPUtilImpl;
//    public static FTPVo fTPVo;
//    public static FTPUtilbc fTPUtil;
//    
//    public static IThreadTask iThreadTask;
//    public static My_Itf_PubWebService my_Itf_PubWebService;
//    public static BeanStruct beanStruct;
//    public static IFileStorageConst iFileStorageConst;
//    public static ObjectFilter objectFilter;
//    public static ObjectHandler objectHandler;
//    public static ObjectProcess objectProcess;
//    public static PropertyFilter propertyFilter;
//    public static PubConsUtilbc pubConsUtilbc;
//    public static FTPConstant fTPConstant;
//    
//    public static BeanHelper beanHelper = new BeanHelper();
//    public static BeanUtil beanUtil = new BeanUtil();
//    public static Factory factory = new Factory();
//    public static MethodInfo methodInfo;
//    public static ReflectionUtilbc reflectionUtilbc = new ReflectionUtilbc();
//    
//    public static ASEUtil aSEUtil = new ASEUtil();
//    public static Base32 base32 = new Base32();
//    public static Base64 base64 = new Base64();
//    public static Base64Ext base64Ext = new Base64Ext();
//    public static BlowfishUtil blowfishUtil = new BlowfishUtil();
//    public static DESUtil dESUtil = new DESUtil();
//    public static RsaUtil rsaUtil = new RsaUtil();
//    public static SecureHA secureHA = new SecureHA();
//    public static SecurityHelper securityHelper = new SecurityHelper();
//    public static FileEncodingUtil fileEncodingUtil = new FileEncodingUtil();
//    public static FileHelper fileHelper = new FileHelper();
//    public static FilePathUtil filePathUtil = new FilePathUtil();
//    public static FileReadImpl fileReadImpl = new FileReadImpl();
//    
//    public static FileType fileType;
//    public static FileTypeImpl fileTypeImpl = new FileTypeImpl();
//    public static FileUtilbc fileUtilbc = new FileUtilbc();
//    public static InOutUtil inOutUtil = new InOutUtil();
//    public static StreamUtilbc streamUtil = new StreamUtilbc();
//    public static Zip zip = new Zip();
//    public static ZipUtils zipUtils = new ZipUtils();
//    public static FileAttr fileAttr = new FileAttr();
//    
//    public static FTPLog fTPLog = new FTPLog();
//    public static UfsHostURI ufsHostURI = new UfsHostURI();
//    public static CaptchaUtil captchaUtil = new CaptchaUtil();
//    public static GifEncoder gifEncoder = new GifEncoder();
//    public static PictureUtilbc pictureUtilbc = new PictureUtilbc();
//    public static Quant quant = new Quant();
//    public static RMPhotoUtils rMPhotoUtils = new RMPhotoUtils();
//    
//    public static CoordinateUtil coordinateUtil = new CoordinateUtil();
//    public static HttpClientUtilbc httpClientUtilbc = new HttpClientUtilbc();
//    public static NCSSOUtil nCSSOUtil = new NCSSOUtil();
//    public static WebBrowser webBrowser = new WebBrowser();
//    public static AppDebug appDebug = new AppDebug();
//    
//    public static AssertUtil assertUtil = new AssertUtil();
//    public static CharsetUtil charsetUtil = new CharsetUtil();
//    public static CharUtil charUtil = new CharUtil();
//    public static CollectionUtil collectionUtil = new CollectionUtil();
//    public static DateUtil dateUtil = new DateUtil();
//    public static ExceptionUtilbc exceptionUtilbc = new ExceptionUtilbc();
//    public static IntegerUtils integerUtils = new IntegerUtils();
//    public static LoggerUtilbc LoggerUtilbc = new LoggerUtilbc();
//    public static MylbcException mylbcException = new MylbcException();
//    public static PropertiesUtilbc propertiesUtilbc = new PropertiesUtilbc(null);
//    public static RandomUtil randomUtil = new RandomUtil();
//    public static RegUtil regUtil = new RegUtil();
//    public static StringUtil stringUtil = new StringUtil();
//    public static StringUtilbc stringUtilbc = new StringUtilbc();
//    public static SysUtil sysUtil = new SysUtil();
//    public static XMLUtilbc xMLUtils = new XMLUtilbc();
//    public static BeanResultSetProcessor beanResultSetProcessor = new BeanResultSetProcessor();
//    public static CountVO countVO = new CountVO();
//    public static DBToJavaVO dBToJavaVO = new DBToJavaVO();
//    public static DBUtilbc dBUtils = new DBUtilbc(DataBaseType.ORACLE);
//    public static ProcessorUtilslbc processorUtilslbc = new ProcessorUtilslbc();
//    public static ResultSetProcessor resultSetProcessor = new ResultSetProcessor();
//    public static SQLHelper sQLHelper = new SQLHelper();
//    public static HandleCallable handleCallable = new HandleCallable();
//    public static IThreadTaskImp iThreadTaskImp = new IThreadTaskImp();
//    public static MultiThreadUtils multiThreadUtils = new MultiThreadUtils();
//    public static TestTask testTask = new TestTask();
//    public static ThreadResultBean threadResultBean = new ThreadResultBean();
//    public static My_Impl_PubWebService my_Impl_PubWebService = new My_Impl_PubWebService();
//    public static WebServicelbc webServicelbc = new WebServicelbc();
//    public static WebServiceTest webServiceTest = new WebServiceTest();
//    public static WebServiceUtilbc webServiceUtilbc = new WebServiceUtilbc();
//    
//}
