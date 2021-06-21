package pers.bc.utils.test;

import com.sun.org.apache.bcel.internal.generic.NEW;

import pers.bc.utils.net.HttpClientUtilbc;
import pers.bc.utils.pub.LoggerUtilbc;
import pers.bc.utils.pub.StringUtilbc;

public class TestBean
{
    
    public static void main(String[] args)
    {
        String urlStr = "http://127.0.0.1:3006/ncchr/pm/flow/manage/queryPhotoByPsnPk";
        HttpClientUtilbc.sendPost(urlStr, "[\"\",\"\"]");
        LoggerUtilbc log = LoggerUtilbc.getInstance("dbug");
        log.error(urlStr);
        log.info(urlStr);
        log.debug(urlStr);
        log.savaLogErr("savaLogErr", urlStr);
        log.savaLogInfo("savaLogInfo", urlStr);
        log.exception(urlStr, null);
        
        System.err.println(StringUtilbc.toString(new TestBean()));
        
    }
}
