//package pers.bc.utils.yonyou;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import nc.bs.framework.common.RuntimeEnv;
//import nc.uap.lfw.core.LfwRuntimeEnvironment;
//import nc.uap.portal.task.itf.ITaskQryTmp;
//import nc.uap.portal.task.ui.TaskHelper;
//import nc.uap.wfm.engine.TaskProcessUI;
//import pers.bc.utils.file.FileUtilbc;
//import pers.bc.utils.pub.LoggerUtilbc;
//import pers.bc.utils.pub.PropertiesUtilbc;
//
///**
// * 单点登录
// * @qualiFild nc.pub.itf.tools.net.NCSSOUtil.java<br>
// * @author：licheng<br>
// * @date Created on 2019-10-15<br>
// * @version 1.0<br>
// */
//public class NCSSOUtil
//{
//    private Map<String, String> configMap = null;
//    private static NCSSOUtil SSOKeyUtil = null;
//    private static LoggerUtilbc loggerUtilbc = new LoggerUtilbc("ncssologs", "ncssoutil");
//    
//    private String cfgFilePath = RuntimeEnv.getInstance().getNCHome() + File.separator + "modules" + File.separator + "ncsso"
//        + File.separator + "META-INF" + File.separator + "ncssocfg.properties";
//    
//    public static NCSSOUtil getInstance()
//    {
//        if (null == SSOKeyUtil) SSOKeyUtil = new NCSSOUtil();
//        return SSOKeyUtil;
//    }
//    
//    public NCSSOUtil()
//    {
//        try
//        {
//            configMap = PropertiesUtilbc.getAllProperties(cfgFilePath);
//        }
//        catch (IOException e)
//        {
//        }
//    }
//    
//    public String getBaseAddr(HttpServletRequest request)
//    {
//        String scheme = request.getScheme();
//        String servername = request.getServerName();
//        int serverport = request.getLocalPort();
//        if (serverport == 80)
//        {
//            return scheme + "://" + servername;
//        }
//        else
//        {
//            return scheme + "://" + servername + ":" + serverport;
//        }
//    }
//    
//    public boolean ssoRegisterNC(String user_code, String key, String registerUrl)
//    {
//        OutputStreamWriter osw = null;
//        BufferedReader br = null;
//        try
//        {
//            if (user_code != null && user_code.trim().length() > 0)
//            {
//                URL httpurl = new URL(registerUrl);
//                HttpURLConnection con = (HttpURLConnection) httpurl.openConnection();
//                
//                con.setDoInput(true);
//                con.setDoOutput(true);
//                con.setRequestMethod("POST");
//                con.setUseCaches(false);
//                
//                osw = new OutputStreamWriter(con.getOutputStream(), "GBK");
//                
//                StringBuffer requestParam = new StringBuffer();
//                requestParam.append("ssoKey=").append(key);
//                requestParam.append("&userCode=").append(user_code);
//                if (configMap.get("groupcode") != null)
//                {
//                    requestParam.append("&groupCode=").append(configMap.get("groupcode"));
//                }
//                if (configMap.get("busicenter") != null)
//                {
//                    requestParam.append("&busiCenter=").append(configMap.get("busicenter"));
//                }
//                
//                osw.write(requestParam.toString());
//                osw.flush();
//                
//                StringBuffer sb = new StringBuffer();
//                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                String text = "";
//                while ((text = br.readLine()) != null)
//                {
//                    sb.append(text);
//                }
//                
//                return sb.toString().indexOf(key) != -1;
//            }
//        }
//        catch (Exception e)
//        {
//            loggerUtilbc.exception(e);
//        }
//        finally
//        {
//            FileUtilbc.close(br, osw);
//        }
//        
//        return false;
//    }
//    
//    /**
//     * 单点自助
//     */
//    public String ssoRegisterHRSS(String user_code, String key, String registerUrl)
//    {
//        OutputStreamWriter osw = null;
//        BufferedReader br = null;
//        String ssokey = null;
//        try
//        {
//            if (user_code != null && user_code.trim().length() > 0)
//            {
//                URL httpurl = new URL(registerUrl);
//                HttpURLConnection con = (HttpURLConnection) httpurl.openConnection();
//                
//                con.setDoInput(true);
//                con.setDoOutput(true);
//                con.setRequestMethod("POST");
//                con.setUseCaches(false);
//                
//                osw = new OutputStreamWriter(con.getOutputStream(), "GBK");
//                StringBuffer requestParam = new StringBuffer();
//                requestParam.append("&userid=").append(user_code);
//                // requestParam.append("&password=").append(userPasswd);
//                // if (getDataSource() != null) {
//                // requestParam.append("&dsname=").append(getDataSource());
//                // }
//                requestParam.append("&needverifypasswd=").append("N");
//                requestParam.append("&type=").append("2");
//                osw.write(requestParam.toString());
//                osw.flush();
//                
//                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                String str = br.readLine();
//                
//                String[] strs = str.split(":");
//                ssokey = strs[1];
//                
//            }
//        }
//        catch (Exception e)
//        {
//            loggerUtilbc.exception(e);
//        }
//        finally
//        {
//            FileUtilbc.close(br, osw);
//        }
//        
//        return ssokey;
//    }
//    
//    /**
//     * 单点NC获取ssoKey
//     */
//    public String ssoRegister4NC(String user_code, String key, String registerUrl)
//    {
//        OutputStreamWriter osw = null;
//        BufferedReader br = null;
//        String ssokey = null;
//        try
//        {
//            if (user_code != null && user_code.trim().length() > 0)
//            {
//                URL httpurl = new URL(registerUrl);
//                HttpURLConnection con = (HttpURLConnection) httpurl.openConnection();
//                
//                con.setDoInput(true);
//                con.setDoOutput(true);
//                con.setRequestMethod("POST");
//                con.setUseCaches(false);
//                
//                osw = new OutputStreamWriter(con.getOutputStream(), "GBK");
//                StringBuffer requestParam = new StringBuffer();
//                // 获取NC注册登陆的ssoKey,注册时不需要添加ssoKey
//                // requestParam.append("ssoKey=").append(key);
//                requestParam.append("&userCode=").append(user_code);
//                if (configMap.get("groupcode") != null && !configMap.get("groupcode").isEmpty())
//                {
//                    requestParam.append("&groupCode=").append(configMap.get("groupcode"));
//                }
//                if (configMap.get("busicenter") != null && !configMap.get("busicenter").isEmpty())
//                {
//                    requestParam.append("&busiCenter=").append(configMap.get("busicenter"));
//                }
//                osw.write(requestParam.toString());
//                osw.flush();
//                
//                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                String str = br.readLine();
//                
//                // String[] strs = str.split(":");
//                ssokey = str;
//            }
//        }
//        catch (Exception e)
//        {
//            loggerUtilbc.exception(e);
//        }
//        finally
//        {
//            FileUtilbc.close(br, osw);
//        }
//        
//        return ssokey;
//    }
//    
//    /**
//     * 单点自助(员工刷卡单点)
//     */
//    public String ssoRegister4CSSC(String user_code, String key, String registerUrl)
//    {
//        OutputStreamWriter osw = null;
//        BufferedReader br = null;
//        String ssokey = null;
//        
//        try
//        {
//            if (user_code != null && user_code.trim().length() > 0)
//            {
//                
//                URL httpurl = new URL(registerUrl);
//                HttpURLConnection con = (HttpURLConnection) httpurl.openConnection();
//                
//                con.setDoInput(true);
//                con.setDoOutput(true);
//                con.setRequestMethod("POST");
//                con.setUseCaches(false);
//                
//                osw = new OutputStreamWriter(con.getOutputStream(), "GBK");
//                StringBuffer requestParam = new StringBuffer();
//                // String ncLoginApproveUrl = baseAddr +
//                // "/portal/pt/home/view?pageModule=weberm&pageName=MYEXPPORTAL";
//                requestParam.append("&pageModule=").append("weberm");
//                requestParam.append("&pageName=").append("MYEXPPORTAL");
//                requestParam.append("&userid=").append(user_code);
//                // requestParam.append("&password=").append(userPasswd);
//                if (configMap.get("datasource") != null)
//                {
//                    requestParam.append("&dsname=").append(configMap.get("datasource"));
//                }
//                requestParam.append("&needverifypasswd=").append("N");
//                requestParam.append("&type=").append("2");
//                osw.write(requestParam.toString());
//                osw.flush();
//                
//                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                String str = br.readLine();
//                
//                String[] strs = str.split(":");
//                ssokey = strs[1];
//            }
//        }
//        catch (Exception e)
//        {
//            loggerUtilbc.exception(e);
//        }
//        finally
//        {
//            FileUtilbc.close(br, osw);
//        }
//        return ssokey;
//    }
//    
//    /**
//     * 单点自助 并且打开对应待办
//     */
//    public String ssoWorkHRSS(String user_code, String key, String registerUrl)
//    {
//        // byte[] token = NCLocator.getInstance().lookup(IFwLogin.class).login("wangr", "qqq111", null);
//        String ssokey = ssoRegisterHRSS(user_code, key, registerUrl);
//        // String ncLoginUrl = "http://127.0.0.1:7777/portal/pt/home/index?ssoKey=" + ssokey;
//        // try
//        // {
//        // URL url = new URL(ncLoginUrl);
//        // URLConnection con = url.openConnection();
//        // BufferedReader r = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
//        //
//        // }
//        // catch (Exception e)
//        // {
//        // return "";
//        // }finally {
//        //
//        // }
//        return ssokey;
//        
//    }
//    
//    public String getPortalTaskUrlByTaskID(String taskid)
//    
//    {
//        
//        ITaskQryTmp taskQry = TaskHelper.getTaskQry("wfmtaskqry");
//        
//        // ITaskQryTmp taskQry = new HrssWfmTaskQryTmpImpl();
//        
//        TaskProcessUI tpi = taskQry.getTaskProcessUrl(taskid);
//        
//        String url = "";
//        
//        if (tpi != null)
//        
//        {
//            String portal = LfwRuntimeEnvironment.getWebContext().getRequest().getRequestURL().toString();
//            
//            portal = portal.substring(0, portal.indexOf("/portal"));
//            
//            url = portal + tpi.getUrl();
//            
//        }
//        
//        return url;
//        
//    }
//    
//}
