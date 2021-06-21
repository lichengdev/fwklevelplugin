package pers.bc.utils.webservice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import pers.bc.utils.cons.PubConsUtilbc;
import pers.bc.utils.pub.LoggerUtilbc;
import pers.bc.utils.pub.PubEnvUtilbc;

/**
 * WebService简单的使用工具类
 * @qualiFild pers.bc.utils.webservice.WebServiceUtilbc.java<br>
 * @author：LiBencheng<br>
 * @date Created on 2020-4-28<br>
 * @version 1.0<br>
 */
public class WebServiceUtilbc implements PubConsUtilbc
{
    private static String fileUrl = "webservicelogs";
    
    /**
     * *********************************************************** <br>
     * 说明： 此方法使用axis技术访问接口<br>
     * wsdl接口远程调用方法，需要axis.jar
     * @see <br>
     * @param url 接口地址
     * @param OperationName 参数为 <br>
     *            new QName("http://zckj.erpInterface.b2b.casic.com/(namespaceURI名称空间URIwsdl文件里有描述)",
     *            "getProductInfoList(localPart方法)")
     * @param
     * @return xml 报文 <br>
     * @throws Exception <br>
     * @String <br>
     * @methods pers.bc.utils.webservice.WebServiceUtilbc#getWSDLCall <br>
     * @author LiBencheng <br>
     * @date Created on 2020-4-28 <br>
     * @time 上午9:12:24 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getResultAxis(String url, QName OperationName, Object[] wsdlParms) throws Exception
    {
        String result = "";
        try
        {
            org.apache.axis.client.Service service = new org.apache.axis.client.Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(url);
            call.setOperationName(OperationName);// WSDL里面描述的接口名称
            // 接口的参数
            for (int i = 0, j = PubEnvUtilbc.getSize(wsdlParms); i < j; i++)
            {
                call.addParameter("arg" + i, XMLType.XSD_STRING, ParameterMode.IN);
            }
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
            result = (String) call.invoke(wsdlParms);
        }
        catch (Exception e)
        {
            LoggerUtilbc.getInstance(fileUrl).exception("getResultAxisThw", e);
            throw new Exception(e);
        }
        return result;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 此方法默认String 其他参数 请重写 <br>
     * wsdl接口远程调用方法，需要axis.jar
     * @see <br>
     * @param webserviceURL 地址不带?WSDL
     * @param xmlns xmlns:zckj=\"http://zckj.erpInterface.b2b.casic.com/ 约束(namespaceURI名称空间URI)<br>
     *            wsdl文件里有描述
     * @param method 方法
     * @param wsdlParms 参数
     * @return Object
     * @throws Exception <br>
     * @Object <br>
     * @methods pers.bc.utils.webservice.WebServiceUtilbc#getResultAxis <br>
     * @author Li Bencheng <br>
     * @date Created on 2020-4-28 <br>
     * @time 上午9:12:00 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Object getResultAxis(String webserviceURL, String xmlns, String method, Object[] wsdlParms) throws Exception
    {
        Object obj = null;
        try
        {
            org.apache.axis.client.Service service = new org.apache.axis.client.Service();
            org.apache.axis.client.Call call = (org.apache.axis.client.Call) service.createCall();
            call.setTargetEndpointAddress(webserviceURL);
            call.setEncodingStyle(UTF_8);
            call.setOperationName(new QName(xmlns, method));// 设置操作名
            // call.addParameter("arg0", XMLType.XSD_STRING, ParameterMode.IN);// 设置操作名
            for (int i = 0, j = PubEnvUtilbc.getSize(wsdlParms); i < j; i++)
            {
                call.addParameter("arg" + i, XMLType.XSD_STRING, ParameterMode.IN);
            }
            call.setReturnType(XMLType.XSD_STRING);
            obj = call.invoke(wsdlParms);
        }
        catch (Exception e)
        {
            LoggerUtilbc.getInstance(fileUrl).exception("getResultAxisThw", e);
            throw new Exception(e);
        }
        return obj;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 此方法使用http方式访问接口<br>
     * reqxml 使用SoapUI 获取 该参数片段，然后自己封装xml请求片段
     * @see <br>
     * @param reqXML 请求报文，包含请求参数,参数格式： <br>
     *            <soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"
     *            xmlns:zckj=\"http://zckj.erpInterface.b2b.casic.com/\"><br>
     *            <soapenv:Header/> <br>
     *            <soapenv:Body> <zckj:getProductInfoList> <br>
     *            <arg0> strParm</arg0> <br>
     *            </zckj:getProductInfoList> <br>
     *            </soapenv:Body> <br>
     *            </soapenv:Envelope> <br>
     *            特别说明：<arg0>strParm</arg0> strParm 为xml时候 <!CDATA[XMLParm ]>
     * @param webserviceURL 地址WSDL
     * @param method 方法
     * @return String
     * @throws Exception <br>
     * @String <br>
     * @methods pers.bc.utils.webservice.WebServiceUtilbc#getResultHttp <br>
     * @author LiBencheng <br>
     * @date Created on 2020-4-28 <br>
     * @time 上午9:11:09 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getResultHttp(String reqXML, String webserviceURL, String method) throws Exception
    {
        StringBuffer data = new StringBuffer();
        try
        {
            if (PubEnvUtilbc.isEmpty(webserviceURL)) throw new Exception("参数webserviceURL：不能没有！");
            URL url = new URL(webserviceURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
            con.setRequestProperty("SOAPAction", method);
            OutputStream reqStream = con.getOutputStream();
            reqStream.write(reqXML.getBytes(UTF_8));
            reqStream.flush();
            
            // 接收报文
            InputStream resStream = con.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(resStream, UTF_8));
            String line = null;
            while ((line = in.readLine()) != null)
            {
                data.append(line);
            }
            con.disconnect();
        }
        catch (Exception e)
        {
            LoggerUtilbc.getInstance(fileUrl).exception("getResultHttpThw", e);
            throw new Exception(e);
        }
        
        return data.toString();
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 此方法使用xfire技术访问接口 <br>
     * 需要xfire-all-1.2.6.jar
     * @see <br>
     * @param webserviceURL wsdl地址
     * @param method 方法
     * @param wsdlParms
     * @return Object
     * @throws Exception <br>
     * @Object <br>
     * @methods pers.bc.utils.webservice.WebServiceUtilbc#getResultXfire <br>
     * @author LiBencheng <br>
     * @date Created on 2020-4-28 <br>
     * @time 上午9:10:38 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Object getResultXfire(String webserviceURL, String method, Object[] wsdlParms) throws Exception
    {
        try
        {
            org.codehaus.xfire.client.Client client = new org.codehaus.xfire.client.Client(new URL(webserviceURL));
            return client.invoke(method, wsdlParms);
        }
        catch (Exception e)
        {
            LoggerUtilbc.getInstance(fileUrl).exception("getResultXfireThw", e);
            throw new Exception("连接webservice失败，获取数据为空call", e);
        }
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 此方法使用axis技术,生成客户端代码进行访问webservice接口的方式，需要axis的jar包 <br>
     * wsdl2java和工程中需使用的jar包：<br>
     * http://download.csdn.net/detail/zhangaocommit/9093091
     * @see -p package; -S? 为生成的源码指定存储路径; -o <path>? 指定生成代码的输出路径 <br>
     * @param packageName 包名,示例：pers.bc.utils.webservice
     * @param wsdlUrl 支持本地路径和网络路径，网络路径wadl地址(需要带?wsdl)<br>
     * @void <br>
     * @methods pers.bc.utils.webservice.WebServiceUtilbc#WSDL2Java <br>
     * @author LiBencheng <br>
     * @date Created on 2019-3-9 <br>
     * @time 上午10:02:02 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void WSDL2Java(String packageName, String wsdlUrl)
    {
        org.apache.axis.wsdl.WSDL2Java.main(new String[]{"-o", "src", "-p", packageName, "-u", wsdlUrl});
    }
    
}
