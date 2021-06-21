package pers.bc.utils.ftp;

import java.util.Date;

/**
 * FTP文件熟悉
 * @qualiFild nc.pub.itf.tools.ftp.FileAttr.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class FileAttr
{
    private String fileName;
    private Date ModifyTime;
    private Long size;
    
    public String getFileName()
    {
        return fileName;
    }
    
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    
    public Date getModifyTime()
    {
        return new Date(ModifyTime.getTime());
    }
    
    public void setModifyTime(Date modifyTime)
    {
        ModifyTime = modifyTime;
    }
    
    public Long getSize()
    {
        return size;
    }
    
    public void setSize(Long size)
    {
        this.size = size;
    }
}
