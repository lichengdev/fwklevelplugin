package pers.bc.utils.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import pers.bc.utils.file.FilePathUtil;
import pers.bc.utils.file.FileUtilbc;
import pers.bc.utils.file.StreamUtilbc;
import pers.bc.utils.net.HttpClientUtilbc;

public class TestHttp
{
    public static void main(String[] args)
    {
        
        try
        {
            List<File> listFile = FileUtilbc.listFile("C:\\Users\\licheng\\Desktop\\pers", true);
            FileUtilbc.codeConvertFileList(new File("D:\\pers"), "UTF-8", "GB2312");
            for (int i = 0; i < listFile.size(); i++)
            {
                File file = listFile.get(i);
            }
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    @Test
    public void test1()
    {
        String html =
            HttpClientUtilbc
                .loadText("https://www.baidupcs.com/rest/2.0/pcs/file?method=batchdownload&app_id=250528&zipcontent=%7B%22fs_id%22%3A%5B%22275782793601276%22%2C%22291236997485079%22%2C%22544445880800024%22%5D%7D&sign=DCb740ccc5511e5e8fedcff06b081203:i7x9MpuuGyljRbv1KGt4MJa%2FpJQ%3D&uid=2584000538&time=1585136173&dp-logid=1943948625031676740&dp-callid=0&shareid=1965975108&vuk=2450748260&from_uk=2584000538&zipname=%E3%80%90%E6%89%B9%E9%87%8F%E4%B8%8B%E8%BD%BD%E3%80%91NCC%E6%96%B0%E5%8D%95%E6%8D%AE%E9%9C%80%E8%A6%81%E5%AE%9E%E7%8E%B0%E7%9A%84%E6%8E%A5%E5%8F%A3%E7%AD%89.zip");
        System.out.println(html);
        
    }
    
    @Test
    public void test2()
    {
        byte[] by =
            HttpClientUtilbc
                .loadBytes("https://www.baidupcs.com/rest/2.0/pcs/file?method=batchdownload&app_id=250528&zipcontent=%7B%22fs_id%22%3A%5B%22275782793601276%22%2C%22291236997485079%22%2C%22544445880800024%22%5D%7D&sign=DCb740ccc5511e5e8fedcff06b081203:i7x9MpuuGyljRbv1KGt4MJa%2FpJQ%3D&uid=2584000538&time=1585136173&dp-logid=1943948625031676740&dp-callid=0&shareid=1965975108&vuk=2450748260&from_uk=2584000538&zipname=%E3%80%90%E6%89%B9%E9%87%8F%E4%B8%8B%E8%BD%BD%E3%80%91NCC%E6%96%B0%E5%8D%95%E6%8D%AE%E9%9C%80%E8%A6%81%E5%AE%9E%E7%8E%B0%E7%9A%84%E6%8E%A5%E5%8F%A3%E7%AD%89.zip");
        try
        {
            InputStream in = StreamUtilbc.byteTOInputStream(by);
            
            String filePatch = FilePathUtil.readFilePatch(TestHttp.class, "1222");
            File f = new File(filePatch);
            FileOutputStream o = new FileOutputStream(f);
            int n;
            while ((n = in.read(by)) != -1)
            {
                o.write(by, 0, n);
            }
            o.close();
            in.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
}
