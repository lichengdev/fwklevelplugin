//package pers.bc.utils.yonyou;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import nc.vo.pub.SuperVO;
//import nc.vo.pub.lang.UFDateTime;
//
//import org.apache.commons.lang.ObjectUtils;
//
///**
// * 总则 通用的NCVO类
// * @qualiFild pers.bc.utils.yonyou.GeneralVO.java<br>
// * @author：LiBencheng<br>
// * @date Created on 2020-5-19<br>
// * @version 1.0<br>
// */
//public class GeneralVO extends SuperVO
//
//{
//    /**
//     * @date 2020-5-19
//     */
//    private static final long serialVersionUID = 356036342597536493L;
//    
//    private final static String PK_PREFIX = "$";
//    
//    private Integer dr;
//    
//    private HashMap<String, Integer> hashType = null;// 记录字段的类型
//    
//    private HashMap<String, Object> hashValue = new HashMap<String, Object>();// 所有的值
//    
//    // 所有的字段名
//    private List<String> listFieldName = new ArrayList<String>();
//    
//    public List<String> getListFieldName()
//    {
//        return listFieldName;
//    }
//    
//    private String strParentPKFieldName;// 主表主键字段名
//    
//    private String strPKFieldName;// 主键字段名
//    
//    private String strTableName;// 表名
//    
//    private UFDateTime ts;
//    
//    /*********************************************************************************************************
//     * Created on 2020-7-11 12:39:33<br>
//     * @author libchc
//     ********************************************************************************************************/
//    public GeneralVO()
//    {
//        super();
//    }
//    
//    /**************************************************************
//     * Created on 2012-10-9 9:24:12<br>
//     * @param strTableName
//     * @param strPkFieldCode
//     * @author libchc
//     **************************************************************/
//    public GeneralVO(String strTableName, String strPkFieldCode)
//    {
//        super();
//        
//        setTableName(strTableName);
//        setPKFieldName(strPkFieldCode);
//    }
//    
//    /*********************************************************************************************************
//     * Created on 2020-7-11 12:39:41<br>
//     * @author libchc
//     * @see nc.vo.pub.SuperVO#clone()
//     ********************************************************************************************************/
//    @SuppressWarnings("unchecked")
//    @Override
//    public Object clone()
//    {
//        GeneralVO generalVO = null;
//        
//        try
//        {
//            generalVO = (GeneralVO) super.clone();
//        }
//        catch (Exception e)
//        {
//            generalVO = new GeneralVO();
//        }
//        
//        generalVO.hashValue = (HashMap<String, Object>) hashValue.clone();
//        
//        generalVO.listFieldName.addAll(listFieldName);
//        
//        generalVO.dr = dr;
//        generalVO.ts = ts;
//        generalVO.strPKFieldName = strPKFieldName;
//        generalVO.strParentPKFieldName = strParentPKFieldName;
//        
//        return generalVO;
//    }
//    
//    /*********************************************************************************************************
//     * 获取所有字段的名称 <br>
//     * Created on 2020-7-11 12:42:31<br>
//     * @author libchc
//     * @see nc.vo.pub.SuperVO#getAttributeNames()
//     ********************************************************************************************************/
//    @Override
//    public String[] getAttributeNames()
//    {
//        return listFieldName.toArray(new String[0]);
//    }
//    
//    /***************************************************************************
//     * <br>
//     * Created on 2011-3-21 14:46:53<br>
//     * @param strAttrName
//     * @return int 取得列的类型
//     * @author libchc
//     ***************************************************************************/
//    public int getAttributeType(String strAttrName)
//    {
//        if (hashType == null)
//        {
//            return -1;
//        }
//        
//        return hashType.get(strAttrName) != null ? hashType.get(strAttrName).intValue() : -1;
//    }
//    
//    /*********************************************************************************************************
//     * 获取属性值<br>
//     * Created on 2020-7-11 12:45:21<br>
//     * @author libchc
//     * @see nc.vo.pub.SuperVO#getAttributeValue(java.lang.String)
//     ********************************************************************************************************/
//    @Override
//    public Object getAttributeValue(String strAttrName)
//    {
//        Object objValue = hashValue.get(PK_PREFIX + strAttrName);
//        
//        if (objValue != null)
//        {
//            return objValue;
//        }
//        
//        return hashValue.get(strAttrName);
//    }
//    
//    /*********************************************************************************************************
//     * Created on 2020-7-12 14:21:11<br>
//     * @author libchc
//     * @return the dr
//     ********************************************************************************************************/
//    public Integer getDr()
//    {
//        return dr;
//    }
//    
//    /*********************************************************************************************************
//     * Created on 2020-7-11 12:22:02<br>
//     * @author libchc
//     ********************************************************************************************************/
//    @Override
//    public String getParentPKFieldName()
//    {
//        return strParentPKFieldName;
//    }
//    
//    /*********************************************************************************************************
//     * Created on 2020-7-11 12:22:02<br>
//     * @author libchc
//     ********************************************************************************************************/
//    @Override
//    public String getPKFieldName()
//    {
//        return strPKFieldName;
//    }
//    
//    /**************************************************************
//     * {@inheritDoc}<br>
//     * Created on 2012-10-16 16:37:15<br>
//     * @see nc.vo.pub.SuperVO#getPrimaryKey()
//     * @author libchc
//     **************************************************************/
//    @Override
//    public String getPrimaryKey()
//    {
//        return ObjectUtils.toString(getAttributeValue(getPKFieldName()));
//    }
//    
//    /*********************************************************************************************************
//     * Created on 2020-7-11 12:22:02<br>
//     * @author libchc
//     ********************************************************************************************************/
//    @Override
//    public String getTableName()
//    {
//        return strTableName;
//    }
//    
//    /*********************************************************************************************************
//     * Created on 2020-7-12 14:21:11<br>
//     * @author libchc
//     * @return the ts
//     ********************************************************************************************************/
//    public UFDateTime getTs()
//    {
//        return ts;
//    }
//    
//    /*********************************************************************************************************
//     * <br>
//     * Created on 2020-7-11 12:48:14<br>
//     * @author libchc
//     * @return Hashtable
//     ********************************************************************************************************/
//    public HashMap<String, Object> getValues()
//    {
//        return hashValue;
//    }
//    
//    /*********************************************************************************************************
//     * <br>
//     * Created on 2020-7-11 12:48:19<br>
//     * @author libchc
//     * @param strAttrName
//     ********************************************************************************************************/
//    public void removeAttributeName(String strAttrName)
//    {
//        hashValue.remove(strAttrName);
//        
//        int index = listFieldName.indexOf(strAttrName);
//        
//        if (index >= 0 && index < listFieldName.size())
//        {
//            listFieldName.remove(index);
//        }
//    }
//    
//    /*********************************************************************************************************
//     * <br>
//     * Created on 2020-7-14 12:12:11<br>
//     * @author libchc
//     * @param strAttrNames
//     ********************************************************************************************************/
//    public void setAttributeNames(String... strAttrNames)
//    {
//        if (strAttrNames == null || strAttrNames.length == 0)
//        {
//            return;
//        }
//        
//        for (String strAttrName : strAttrNames)
//        {
//            if (!listFieldName.contains(strAttrName))
//            {
//                listFieldName.add(strAttrName);
//            }
//        }
//    }
//    
//    /***************************************************************************
//     * 设置列的类型<br>
//     * Created on 2011-3-21 14:46:51<br>
//     * @param strAttrName
//     * @param iType
//     * @author libchc
//     ***************************************************************************/
//    public void setAttributeType(String strAttrName, int iType)
//    {
//        if (strAttrName == null)
//        {
//            return;
//        }
//        
//        if (hashType == null)
//        {
//            hashType = new HashMap<String, Integer>();
//        }
//        
//        hashType.put(strAttrName, iType);
//    }
//    
//    /*********************************************************************************************************
//     * 设置属性值。<br>
//     * Created on 2020-7-11 12:48:26<br>
//     * @author libchc
//     * @see nc.vo.pub.SuperVO#setAttributeValue(java.lang.String, java.lang.Object)
//     ********************************************************************************************************/
//    @Override
//    public void setAttributeValue(String strAttrName, Object objValue)
//    {
//        if (strAttrName == null)
//        {
//            return;
//        }
//        
//        if (!listFieldName.contains(strAttrName))
//        {
//            listFieldName.add(strAttrName);
//        }
//        
//        hashValue.put(strAttrName, objValue);
//    }
//    
//    /*********************************************************************************************************
//     * Created on 2020-7-12 14:21:11<br>
//     * @author libchc
//     * @param dr the dr to set
//     ********************************************************************************************************/
//    public void setDr(Integer dr)
//    {
//        this.dr = dr;
//    }
//    
//    /***************************************************************************
//     * Created on 2008-8-21 10:18:19<br>
//     * @author libchc
//     * @param strParentPKFieldName the parentPKFieldName to set
//     ***************************************************************************/
//    public void setParentPKFieldName(String strParentPKFieldName)
//    {
//        this.strParentPKFieldName = strParentPKFieldName;
//    }
//    
//    /***************************************************************************
//     * Created on 2008-8-21 10:18:19<br>
//     * @author libchc
//     * @param strPkFieldName the pKFieldName to set
//     ***************************************************************************/
//    public void setPKFieldName(String strPkFieldName)
//    {
//        strPKFieldName = strPkFieldName;
//    }
//    
//    /*********************************************************************************************************
//     * Created on 2020-7-11 12:26:23<br>
//     * @author libchc
//     * @param strTableName the tableName to set
//     ********************************************************************************************************/
//    public void setTableName(String strTableName)
//    {
//        this.strTableName = strTableName;
//    }
//    
//    /*********************************************************************************************************
//     * Created on 2020-7-12 14:21:11<br>
//     * @author libchc
//     * @param ts the ts to set
//     ********************************************************************************************************/
//    public void setTs(UFDateTime ts)
//    {
//        this.ts = ts;
//    }
//}
