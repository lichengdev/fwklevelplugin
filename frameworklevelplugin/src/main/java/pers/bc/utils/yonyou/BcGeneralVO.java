//package pers.bc.utils.yonyou;
//
//import java.util.Arrays;
//
//import nc.vo.bd.meta.IBDObject;
//import nc.vo.bd.meta.bdobject.Code;
//import nc.vo.bd.meta.bdobject.Group;
//import nc.vo.bd.meta.bdobject.Id;
//import nc.vo.bd.meta.bdobject.Name;
//import nc.vo.bd.meta.bdobject.Org;
//import nc.vo.bd.meta.bdobject.PId;
//import pers.bc.utils.Bean.BeanHelper;
//
///**
// * 实现IBDObject接口的，总则 通用的NCVO类
// * @qualiFild pers.bc.utils.yonyou.BcGeneralVO.java<br>
// * @author：LiBencheng<br>
// * @date Created on 2020-5-19<br>
// * @version 1.0<br>
// */
//public class BcGeneralVO extends GeneralVO implements IBDObject
//{
//    
//    /**
//     * @date 2020-5-19
//     */
//    private static final long serialVersionUID = 8337395417233787989L;
//    
//    public BcGeneralVO()
//    {
//        super();
//        initIBDobj();
//    }
//    
//    public BcGeneralVO(String strTableName, String strPkFieldCode)
//    {
//        super();
//        initIBDobj();
//        setTableName(strTableName);
//        setPKFieldName(strPkFieldCode);
//    }
//    
//    /**
//     * 设置属性值
//     */
//    @Override
//    public void setAttributeValue(String strAttrName, Object objValue)
//    {
//        if (Arrays.asList(FILED_CODE).contains(strAttrName))
//        {
//            BeanHelper.setProperty(this, strAttrName, objValue);
//        }
//        else
//        {
//            super.setAttributeValue(strAttrName, objValue);
//        }
//        
//    }
//    
//    public static final String ID = "id";
//    public static final String PID = "pId";
//    public static final String pid = "pid";
//    public static final String CODE = "code";
//    public static final String NAME = "name";
//    public static final String PK_ORG = "pk_org";
//    public static final String PK_GROUP = "pk_group";
//    
//    /** IBDObject 接口常量 */
//    public static final String[] FILED_CODE = {ID, PID, CODE, NAME, PK_ORG, PK_GROUP};
//    public static final String[] FILED_NAME = {"id", "上级id", "编码", "名称", "所属组织", "所属集团"};
//    
//    private void initIBDobj()
//    {
//        for (String field : FILED_CODE)
//        {
//            if (!getListFieldName().contains(field))
//            {
//                getListFieldName().add(field);
//            }
//        }
//    }
//    
//    // 订单ID
//    @Id
//    private String id;
//    // 订单PId
//    @PId
//    private String pId;
//    // 订单code
//    @Code
//    private String code;
//    // 订单name
//    @Name
//    private String name;
//    // 订单Pk_org
//    @Org
//    private String pk_org;
//    // 订单pk_group
//    @Group
//    private String pk_group;
//    
//    public String getId()
//    {
//        return id;
//    }
//    
//    public void setId(String id)
//    {
//        super.setAttributeValue(ID, id);
//        this.id = id;
//    }
//    
//    public String getPId()
//    {
//        return pId;
//    }
//    
//    public void setpId(String pId)
//    {
//        super.setAttributeValue(PID, pId);
//        super.setAttributeValue(pid, pId);
//        this.pId = pId;
//    }
//    
//    public String getCode()
//    {
//        return code;
//    }
//    
//    public void setCode(String code)
//    {
//        super.setAttributeValue(CODE, code);
//        this.code = code;
//    }
//    
//    public String getName()
//    {
//        return name;
//    }
//    
//    public void setName(String name)
//    {
//        super.setAttributeValue(NAME, name);
//        this.name = name;
//    }
//    
//    public String getPk_org()
//    {
//        return pk_org;
//    }
//    
//    public void setPk_org(String pk_org)
//    {
//        super.setAttributeValue(PK_ORG, pk_org);
//        this.pk_org = pk_org;
//    }
//    
//    public String getPk_group()
//    {
//        return pk_group;
//    }
//    
//    public void setPk_group(String pk_group)
//    {
//        super.setAttributeValue(PK_GROUP, pk_group);
//        this.pk_group = pk_group;
//    }
//}
