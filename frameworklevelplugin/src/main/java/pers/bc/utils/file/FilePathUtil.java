package pers.bc.utils.file;

import java.io.File;

import pers.bc.utils.pub.RegUtil;

/**
 * 文件名及文件路径相关的操作
 * @qualiFild com.pub.utils.file.FilePathUtil.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public final class FilePathUtil
{
    
    /**
     * 判断是否符是合法的文件路径
     * 
     * @param path 需要处理的文件路径
     */
    public final static boolean legalFile(String path)
    {
        // 下面的正则表达式有问题
        String regex = "[a-zA-Z]:(?:[/][^/:*?\"<>|.][^/:*?\"<>|]{0,254})+";
        // String regex ="^([a-zA-z]:)|(^\\.{0,2}/)|(^\\w*)\\w([^:?*\"><|]){0,250}";
        return RegUtil.isMatche(commandPath(path), regex);
    }
    
    /**
     * 返回一个通用的文件路径
     * 
     * @param file 需要处理的文件路径
     * @return Summary windows中路径分隔符是\在linux中是/但windows也支持/方式 故全部使用/
     */
    public final static String commandPath(String file)
    {
        return file.replaceAll("\\\\{1,}", "/").replaceAll("\\/{2,}", "/");
    }
    
    public static String readFilePatch(Class relativePosition, String fileName)
    {
        try
        {
            String path = findPath(relativePosition, fileName);
            File f = new File(path);
            String content;
            content = org.apache.commons.io.FileUtils.readFileToString(f);
            return content;
        }
        catch (Exception e)
        {
            throw new RuntimeException(relativePosition.getName() + "所在目录下不存在文件:" + fileName);
        }
    }
    
    public static String findPath(Class relativePosition, String fileName)
    {
        String path = relativePosition.getResource(fileName).getPath();
        return path;
    }
}
