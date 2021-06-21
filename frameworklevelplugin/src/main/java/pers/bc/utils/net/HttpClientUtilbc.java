package pers.bc.utils.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import pers.bc.utils.cons.PubConsUtilbc;
import pers.bc.utils.file.FileUtilbc;
import pers.bc.utils.pub.CharsetUtil;
import pers.bc.utils.pub.LoggerUtilbc;
import pers.bc.utils.pub.PubEnvUtilbc;

import com.google.gson.Gson;

/**
 * Http请求工具 ,Http通讯
 * @qualiFild pers.bc.utils.net.HttpClientUtil.java<br>
 * @author：licheng<br>
 * @date Created on 2020年3月24日<br>
 * @version 1.0<br>
 */
public class HttpClientUtilbc
{
    
    /** 是建立连接的超时时间 */
    static Integer ConnectTimeout = 30000;
    
    /** 是传递数据的超时时间 */
    static Integer ReadTimeout = 500000;
    
    // /////////////////////////////// HttpServletRequest内容处理工具类//////////////////////////////////////
    /**
     * *********************************************************** <br>
     * 说明：把request转换成json数据 <br>
     * @see <br>
     * @param request
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.net.HttpServletRequestUtil#readReqStr <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午2:58:04 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String readReqStr(HttpServletRequest request)
    {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try
        {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (null != reader)
                {
                    reader.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    
    /**
     ** 把request转换成xml数据
     **/
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param request
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.net.HttpClientUtilbc#readReqXml <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-17 <br>
     * @time 下午6:39:23 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String readReqXml(HttpServletRequest request)
    {
        String inputLine;
        String notityXml = "";
        try
        {
            while ((inputLine = request.getReader().readLine()) != null)
            {
                notityXml += inputLine;
            }
            request.getReader().close();
        }
        catch (Exception e)
        {
            
            return null;
        }
        return notityXml;
        
    }
    
    /**
     ** 把request转换成map数据
     **/
    public static Map<String, String> getRequestParams(HttpServletRequest request)
    {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();)
        {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++)
            {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }
    
    // //////////////////////////////////////////////////////////////////
    /**
     * *********************************************************** <br>
     * 说明：读取远程URL的字节信息 <br>
     * @see <br>
     * @param url
     * @return <br>
     * @byte[] <br>
     * @methods pers.bc.utils.net.HttpClientUtil#loadBytes <br>
     * @author licheng <br>
     * @date Created on 2020年3月24日 <br>
     * @time 下午8:28:47 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static byte[] loadBytes(String url)
    {
        byte[] responseBody = null;
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        try
        {
            // 1.执行getMethod,调用http接口
            httpClient.executeMethod(getMethod);
            // 2.读取内容
            responseBody = getMethod.getResponseBody();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            // 4.释放连接
            getMethod.releaseConnection();
        }
        return responseBody;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：读取远程VO <br>
     * @see <br>
     * @param <T>
     * @param url
     * @param voType
     * @return <br>
     * @T <br>
     * @methods pers.bc.utils.net.HttpClientUtil#loadJsonVO <br>
     * @author licheng <br>
     * @date Created on 2020年3月24日 <br>
     * @time 下午8:30:36 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <T> T loadJsonVO(String url, Class<T> voType)
    {
        return new Gson().fromJson(loadText(url), voType);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：读取远程VO <br>
     * @see <br>
     * @param <T>
     * @param url
     * @param voType
     * @param charset
     * @return <br>
     * @T <br>
     * @methods pers.bc.utils.net.HttpClientUtil#loadJsonVO <br>
     * @author licheng <br>
     * @date Created on 2020年3月24日 <br>
     * @time 下午8:30:46 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <T> T loadJsonVO(String url, Class<T> voType, String charset)
    {
        return new Gson().fromJson(loadText(url, charset), voType);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 读取远程JSON数组<br>
     * @see <br>
     * @param url
     * @return <br>
     * @List <br>
     * @methods pers.bc.utils.net.HttpClientUtil#loadJsonArray <br>
     * @author licheng <br>
     * @date Created on 2020年3月24日 <br>
     * @time 下午8:31:11 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static List loadJsonArray(String url)
    {
        return new Gson().fromJson(loadText(url), ArrayList.class);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 读取远程JSON数组<br>
     * @see <br>
     * @param url
     * @param charset
     * @return <br>
     * @List <br>
     * @methods pers.bc.utils.net.HttpClientUtil#loadJsonArray <br>
     * @author licheng <br>
     * @date Created on 2020年3月24日 <br>
     * @time 下午8:30:57 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static List<?> loadJsonArray(String url, String charset)
    {
        return new Gson().fromJson(loadText(url, charset), ArrayList.class);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 读取远程JSON键值对表<br>
     * @see <br>
     * @param url
     * @return <br>
     * @Map <br>
     * @methods pers.bc.utils.net.HttpClientUtil#loadJsonObject <br>
     * @author licheng <br>
     * @date Created on 2020年3月24日 <br>
     * @time 下午8:31:28 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Map<?, ?> loadJsonObject(String url)
    {
        return new Gson().fromJson(loadText(url), LinkedHashMap.class);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 取远程JSON键值对表<br>
     * @see <br>
     * @param url
     * @param charset
     * @return <br>
     * @Map <br>
     * @methods pers.bc.utils.net.HttpClientUtil#loadJsonObject <br>
     * @author licheng <br>
     * @date Created on 2020年3月24日 <br>
     * @time 下午8:31:41 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Map<?, ?> loadJsonObject(String url, String charset)
    {
        return new Gson().fromJson(loadText(url, charset), LinkedHashMap.class);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：读取远程字符串（JSON/HTML） <br>
     * @see <br>
     * @param url
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.net.HttpClientUtil#loadText <br>
     * @author licheng <br>
     * @date Created on 2020年3月24日 <br>
     * @time 下午8:31:54 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String loadText(String url)
    {
        return loadText(url, PubConsUtilbc.UTF_8);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：读取远程字符串（JSON/HTML） <br>
     * @see <br>
     * @param url
     * @param charset
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.net.HttpClientUtil#loadText <br>
     * @author licheng <br>
     * @date Created on 2020年3月24日 <br>
     * @time 下午8:32:46 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String loadText(String url, String charset)
    {
        try
        {
            return new String(loadBytes(url), charset);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 读取16进制码流字符串 注意：传递的信息大小会膨胀1倍<br>
     * @see <br>
     * @param url
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.net.HttpClientUtil#loadHex <br>
     * @author licheng <br>
     * @date Created on 2020年3月24日 <br>
     * @time 下午8:32:56 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String loadHex(String url)
    {
        return CharsetUtil.bytesToHexString(loadBytes(url));
    }
    
    /**
     * *********************************************************** <br>
     * 说明：通过一个jsonString参数来传递数据，POST请求 请求地址的接口的参数必须为String data <br>
     * @see <br>
     * @param urls
     * @param json
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.net.HttpClientUtilbc#jsonPost <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-17 <br>
     * @time 下午6:40:05 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String jsonPost(String urls, String json)
    {
        OutputStream out = null;
        BufferedReader inputStreamReader = null;
        StringBuffer buffer = null;
        try
        {
            URL url = new URL(urls);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(ConnectTimeout);
            connection.setReadTimeout(ReadTimeout);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept-Charset", PubConsUtilbc.UTF_8); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // 设置发送数据的格式
            connection.connect();
            if (!PubEnvUtilbc.isEmpty(json))
            {
                out = connection.getOutputStream();
                String pushData = "data=" + json;
                out.write(pushData.getBytes(PubConsUtilbc.UTF_8));
                out.flush();
                out.close();
            }
            // 获取响应
            inputStreamReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), PubConsUtilbc.UTF_8));
            // 读取响应
            buffer = new StringBuffer();
            String data = new String("");
            while ((data = inputStreamReader.readLine()) != null)
            {
                buffer.append(data);
            }
            while ((data = inputStreamReader.readLine()) != null)
            {
                buffer.append(data);
            }
        }
        catch (Exception e)
        {
            System.out.println("发送 POST 请求出现异常！" + e);
            LoggerUtilbc.getInstance("httplogs").exception("jsonPost发送POST请求出现异常！", e);
        }
        finally
        {
            FileUtilbc.close(inputStreamReader);
        }
        
        return buffer.toString();
    }
    
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url 发送请求的URL
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url)
    {
        String result = "";
        BufferedReader in = null;
        try
        {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1;SV1)");
            
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            // Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            // for (String key : map.keySet()) {
            // System.out.println(key + "--->" + map.get(key));
            // }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), PubConsUtilbc.UTF_8));
            String line;
            while ((line = in.readLine()) != null)
            {
                result += line;
            }
        }
        catch (Exception e)
        {
            System.out.println("发送GET请求出现异常！" + e);
            LoggerUtilbc.getInstance("httplogs").exception("发送GET请求出现异常！", e);
        }
        // 使用finally块来关闭输入流
        finally
        {
            FileUtilbc.close(in);
        }
        return result;
    }
    
    /**
     * 向指定URL发送GET方法的请求，参数都放在HttpRequestServlet request的内容中
     * @param url 发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param)
    {
        String result = "";
        BufferedReader in = null;
        try
        {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), PubConsUtilbc.UTF_8));
            String line;
            while ((line = in.readLine()) != null)
            {
                result += line;
            }
        }
        catch (Exception e)
        {
            System.out.println("【HTTP发送GET请求出现异常】:访问URL：" + url + ",错误：" + e.getMessage());
        }
        // 使用finally块来关闭输入流
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (Exception e2)
            {
                System.out.println("【HTTP请求关闭输入流异常】:访问URL：" + url + ",错误：" + e2.getMessage());
                LoggerUtilbc.getInstance("httplogs").exception("【HTTP请求关闭输入流异常】:访问URL：" + url + ",错误：" + e2.getMessage(), e2);
                
            }
        }
        return result;
    }
    
    // 发送GET请求
    public static String sendGet(String url, Map<String, String> parameters)
    {
        String result = "";
        BufferedReader in = null;// 读取响应输入流
        StringBuffer sb = new StringBuffer();// 存储参数
        String params = "";// 编码之后的参数
        try
        {
            // 编码请求参数
            if (parameters.size() == 1)
            {
                for (String name : parameters.keySet())
                {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), PubConsUtilbc.UTF_8));
                }
                params = sb.toString();
            }
            else
            {
                for (String name : parameters.keySet())
                {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), PubConsUtilbc.UTF_8)).append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
            String full_url = url + "?" + params;
            System.out.println(full_url);
            // 创建URL对象
            URL connURL = new URL(full_url);
            // 打开URL连接
            HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1;SV1)");
            // 建立实际的连接
            httpConn.connect();
            // 响应头部获取
            Map<String, List<String>> headers = httpConn.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : headers.keySet())
            {
                System.out.println(key + "\t：\t" + headers.get(key));
            }
            // 定义BufferedReader输入流来读取URL的响应,并设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), PubConsUtilbc.UTF_8));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null)
            {
                result += line;
            }
        }
        catch (Exception e)
        {
            System.out.println("发送GET请求出现异常！" + e);
            LoggerUtilbc.getInstance("httplogs").exception("sendGet发送GET请求出现异常！", e);
            
        }
        finally
        {
            FileUtilbc.close(in);
        }
        return result;
    }
    
    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param)
    {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try
        {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1;SV1)");
            
            // 是建立连接的超时时间；
            conn.setConnectTimeout(ConnectTimeout);
            // 是传递数据的超时时间
            conn.setReadTimeout(ReadTimeout);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), PubConsUtilbc.UTF_8));
            String line;
            while ((line = in.readLine()) != null)
            {
                result += line;
            }
        }
        catch (Exception e)
        {
            System.out.println("发送 POST 请求出现异常！" + e);
            LoggerUtilbc.getInstance("httplogs").exception("sendPost发送POST请求出现异常！", e);
        }
        // 使用finally块来关闭输出流、输入流
        finally
        {
            FileUtilbc.close(out, in);
        }
        return result;
    }
    
    // 发送POST请求
    public static String sendPost(String url, Map<String, String> parameters)
    {
        String result = "";// 返回的结果
        BufferedReader in = null;// 读取响应输入流
        PrintWriter out = null;
        StringBuffer sb = new StringBuffer();// 处理请求参数s
        String params = "";// 编码之后的参数
        HttpURLConnection httpConn = null;
        
        try
        {
            // 编码请求参数
            if (parameters.size() == 1)
            {
                for (String name : parameters.keySet())
                {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), PubConsUtilbc.UTF_8));
                }
                params = sb.toString();
            }
            else
            {
                for (String name : parameters.keySet())
                {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), PubConsUtilbc.UTF_8)).append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
            // 创建URL对象
            URL connURL = new URL(url);
            // 打开URL连接
            httpConn = (HttpURLConnection) connURL.openConnection();
            
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1;SV1)");
            // 设置POST方式
            // 设置是否从httpUrlConnection读入，默认情况下是true;
            httpConn.setDoInput(true);
            // http正文内，因此需要设为true, 默认情况下是false;
            httpConn.setDoOutput(true);
            // 获取HttpURLConnection对象对应的输出流
            out = new PrintWriter(httpConn.getOutputStream());
            // 发送请求参数
            out.write(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应，设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null)
            {
                result += line;
            }
        }
        catch (Exception e)
        {
            System.out.println("发送 POST 请求出现异常！" + e);
            LoggerUtilbc.getInstance("httplogs").exception("sendPost发送POST请求出现异常！", e);
        }
        finally
        {
            FileUtilbc.close(out, in);
        }
        return result;
    }
    
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url 发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String doGet(String url, String param)
    {
        String result = "";
        BufferedReader in = null;
        try
        {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1;SV1)");
            // 建立实际的连接
            connection.connect();
            
            // 获取所有响应头字段
            // Map<String, List<String>> map = connection.getHeaderFields();
            // // 遍历所有的响应头字段
            // for (String key : map.keySet())
            // {
            // System.out.println(key + "--->" + map.get(key));
            // }
            
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), PubConsUtilbc.UTF_8));
            String line;
            while ((line = in.readLine()) != null)
            {
                result += line;
            }
        }
        catch (Exception e)
        {
            System.out.println("发送GET请求出现异常！" + e);
            LoggerUtilbc.getInstance("httplogs").exception("doget发送GET请求出现异常！", e);
            
        }
        // 使用finally块来关闭输入流
        finally
        {
            FileUtilbc.close(in);
        }
        return result;
    }
    
    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String doPost(String url, String param)
    {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try
        {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), PubConsUtilbc.UTF_8));
            String line;
            while ((line = in.readLine()) != null)
            {
                result += line;
            }
        }
        catch (Exception e)
        {
            System.out.println("发送 POST 请求出现异常！" + e);
            LoggerUtilbc.getInstance("httplogs").exception("dopost发送 POST 请求出现异常！", e);
        }
        // 使用finally块来关闭输出流、输入流
        finally
        {
            FileUtilbc.close(out, in);
        }
        return result;
    }
    
}
