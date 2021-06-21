package pers.bc.utils.file;

import java.util.HashMap;
import java.util.Map;

/**
 * *********************************************************** <br>
 * @explain 类说明：
 * @项目 中经社
 * @author licheng
 * @date Created on 2019-3-15
 * @time 下午10:23:02
 * @version 1.0 <br>
 ************************************************************* <br>
 */
public class ExportPsnCons
{
    public static String[] NEED = new String[]{"0", "1", "2"};// 模板必填字段
    
    public static Map<String, String> initTableMap = null;
    public static Map<String, String> initTableCode = null;
    public static Map<String, String[]> init_export_code = null;
    public static Map<String, String[]> init_export_name = null;
    
    /**
     * *********************************************************** <br>
     * 说明： 导出的编码
     * @see nc.itf.hi.service.export.ExportPsnCons#initTableCode
     * @return Map<String,String>
     * @return
     * @author licheng
     * @date Created on 2019-3-18
     * @time 下午7:01:04
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Map<String, String> initTableCode()
    {
        if (null == initTableCode)
        {
            initTableCode = new HashMap<String, String>();
            initTableCode.put(PSN_BASE_FCODE, "PSN_BASE_CODE");
            initTableCode.put(PSN_EDUA_FCODE, "PSN_EDUA_CODE");
            initTableCode.put(PSN_EDU_FCODE, "PSN_EDU_CODE");
            initTableCode.put(PSN_DEGREE_FCODE, "PSN_DEGREE_CODE");
            initTableCode.put(PSN_POLITY_FCODE, "PSN_POLITY_CODE");
            initTableCode.put(PSN_ASSE_FCODE, "PSN_ASSE_CODE");
            initTableCode.put(PSN_TRAIN_FCODE, "PSN_TRAIN_CODE");
            initTableCode.put(PSN_FAMILY_FCODE, "PSN_FAMILY_CODE");
            initTableCode.put(PSN_DOC_FCODE, "PSN_DOC_CODE");
            initTableCode.put(PSN_PASS_FCODE, "PSN_PASS_CODE");
            initTableCode.put(PSN_PRIV_CARD_FCODE, "PSN_PRIV_CARD_CODE");
            initTableCode.put(PSN_WORK_FCODE, "PSN_WORK_CODE");
            initTableCode.put(PSN_POST_FCODE, "PSN_POST_CODE");
            initTableCode.put(PSN_EDU_JOB_FCODE, "PSN_EDU_JOB_CODE");
            initTableCode.put(PSN_CTRT_FCODE, "PSN_CTRT_CODE");
            initTableCode.put(PSN_MARITAL_FCODE, "PSN_MARITAL_CODE");
            initTableCode.put(PSN_EPOST_FCODE, "PSN_EPOST_CODE");
            
        }
        return initTableCode;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：导出的文件名
     * @see nc.itf.hi.service.export.ExportPsnCons#initTableMap
     * @return Map<String,String>
     * @return
     * @author licheng
     * @date Created on 2019-3-18
     * @time 下午7:01:07
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Map<String, String> initTableMap()
    {
        if (null == initTableMap)
        {
            initTableMap = new HashMap<String, String>();
            initTableMap.put(PSN_BASE_FCODE, PSN_BASE_FNAME);
            initTableMap.put(PSN_EDUA_FCODE, PSN_EDUA_FNAME);
            initTableMap.put(PSN_EDU_FCODE, PSN_EDU_FNAME);
            initTableMap.put(PSN_DEGREE_FCODE, PSN_DEGREE_FNAME);
            initTableMap.put(PSN_POLITY_FCODE, PSN_POLITY_FNAME);
            initTableMap.put(PSN_ASSE_FCODE, PSN_ASSE_FNAME);
            initTableMap.put(PSN_TRAIN_FCODE, PSN_TRAIN_FNAME);
            initTableMap.put(PSN_FAMILY_FCODE, PSN_FAMILY_FNAME);
            initTableMap.put(PSN_DOC_FCODE, PSN_DOC_FNAME);
            initTableMap.put(PSN_PASS_FCODE, PSN_PASS_FNAME);
            initTableMap.put(PSN_PRIV_CARD_FCODE, PSN_PRIV_CARD_FNAME);
            initTableMap.put(PSN_WORK_FCODE, PSN_WORK_FNAME);
            initTableMap.put(PSN_POST_FCODE, PSN_POST_FNAME);
            initTableMap.put(PSN_EDU_JOB_FCODE, PSN_EDU_JOB_FNAME);
            initTableMap.put(PSN_CTRT_FCODE, PSN_CTRT_FNAME);
            initTableMap.put(PSN_MARITAL_FCODE, PSN_MARITAL_FNAME);
            initTableMap.put(PSN_EPOST_FCODE, PSN_EPOST_FNAME);
            
        }
        return initTableMap;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：导出的列编码
     * @see nc.itf.hi.service.export.ExportPsnCons#init_EXPORT_CODE
     * @return Map<String,String[]>
     * @return
     * @author licheng
     * @date Created on 2019-3-18
     * @time 下午7:01:11
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Map<String, String[]> init_EXPORT_CODE()
    {
        if (null == init_export_code)
        {
            init_export_code = new HashMap<String, String[]>();
            init_export_code.put("PSN_BASE_CODE", PSN_BASE_CODE);
            init_export_code.put("PSN_EDUA_CODE", PSN_EDUA_CODE);
            init_export_code.put("PSN_EDU_CODE", PSN_EDU_CODE);
            init_export_code.put("PSN_DEGREE_CODE", PSN_DEGREE_CODE);
            init_export_code.put("PSN_POLITY_CODE", PSN_POLITY_CODE);
            init_export_code.put("PSN_ASSE_CODE", PSN_ASSE_CODE);
            init_export_code.put("PSN_TRAIN_CODE", PSN_TRAIN_CODE);
            init_export_code.put("PSN_FAMILY_CODE", PSN_FAMILY_CODE);
            init_export_code.put("PSN_DOC_CODE", PSN_DOC_CODE);
            init_export_code.put("PSN_PASS_CODE", PSN_PASS_CODE);
            init_export_code.put("PSN_PRIV_CARD_CODE", PSN_PRIV_CARD_CODE);
            init_export_code.put("PSN_WORK_CODE", PSN_WORK_CODE);
            init_export_code.put("PSN_POST_CODE", PSN_POST_CODE);
            init_export_code.put("PSN_EDU_JOB_CODE", PSN_EDU_JOB_CODE);
            init_export_code.put("PSN_CTRT_CODE", PSN_CTRT_CODE);
            init_export_code.put("PSN_MARITAL_CODE", PSN_MARITAL_CODE);
            init_export_code.put("PSN_EPOST_CODE", PSN_EPOST_CODE);
            
        }
        return init_export_code;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：导出的列名称
     * @see nc.itf.hi.service.export.ExportPsnCons#init_EXPORT_NAME
     * @return Map<String,String[]>
     * @return
     * @author licheng
     * @date Created on 2019-3-18
     * @time 下午7:01:16
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Map<String, String[]> init_EXPORT_NAME()
    {
        if (null == init_export_name)
        {
            init_export_name = new HashMap<String, String[]>();
            init_export_name.put("PSN_BASE_NAME", PSN_BASE_NAME);
            init_export_name.put("PSN_EDUA_NAME", PSN_EDUA_NAME);
            init_export_name.put("PSN_EDU_NAME", PSN_EDU_NAME);
            init_export_name.put("PSN_DEGREE_NAME", PSN_DEGREE_NAME);
            init_export_name.put("PSN_POLITY_NAME", PSN_POLITY_NAME);
            init_export_name.put("PSN_ASSE_NAME", PSN_ASSE_NAME);
            init_export_name.put("PSN_TRAIN_NAME", PSN_TRAIN_NAME);
            init_export_name.put("PSN_FAMILY_NAME", PSN_FAMILY_NAME);
            init_export_name.put("PSN_DOC_NAME", PSN_DOC_NAME);
            init_export_name.put("PSN_PASS_NAME", PSN_PASS_NAME);
            init_export_name.put("PSN_PRIV_CARD_NAME", PSN_PRIV_CARD_NAME);
            init_export_name.put("PSN_WORK_NAME", PSN_WORK_NAME);
            init_export_name.put("PSN_POST_NAME", PSN_POST_NAME);
            init_export_name.put("PSN_EDU_JOB_NAME", PSN_EDU_JOB_NAME);
            init_export_name.put("PSN_CTRT_NAME", PSN_CTRT_NAME);
            init_export_name.put("PSN_MARITAL_NAME", PSN_MARITAL_NAME);
            // 20190408 add
            init_export_name.put("PSN_EPOST_NAME", PSN_EPOST_NAME);
            
        }
        return init_export_name;
    }
    
    /** AA01基本信息 */
    public static String PSN_BASE_FCODE = "AA01";// 基本信息";
    
    /** AA04全日制学历 */
    public static String PSN_EDUA_FCODE = "AA04";// 全日制学历";
    
    /** AA08学历信息 */
    public static String PSN_EDU_FCODE = "AA08";// 学历信息";
    
    /** AA09学位信息 */
    public static String PSN_DEGREE_FCODE = "AA09";// 学位信息";
    
    /** AA22政治面貌 */
    public static String PSN_POLITY_FCODE = "AA22";// 政治面貌";
    
    /** AA26考核情况 */
    public static String PSN_ASSE_FCODE = "AA26";// 考核情况";
    
    /** AA37培训记录 */
    public static String PSN_TRAIN_FCODE = "AA37";// 培训记录";
    
    /** AA79家庭信息 */
    public static String PSN_FAMILY_FCODE = "AA79";// 家庭信息";
    
    /** AA85人事档案管理 */
    public static String PSN_DOC_FCODE = "AA85";// 人事档案管理";
    
    /** AAB0出入证信息 */
    public static String PSN_PASS_FCODE = "AAB0";// 出入证信息";
    
    /** AAB3因私证件 */
    public static String PSN_PRIV_CARD_FCODE = "AAB3";// 因私证件";
    
    /** AAL2个人简历 */
    public static String PSN_WORK_FCODE = "AAL2";// 个人简历";
    
    /** AAQ1企业岗位 */
    public static String PSN_POST_FCODE = "AAQ1";// 企业岗位";
    
    /** AAR0在职学历 */
    public static String PSN_EDU_JOB_FCODE = "AAR0";// 在职学历";
    
    /** AARH合同信息 */
    public static String PSN_CTRT_FCODE = "AARH";// 合同信息";
    
    /** AAX2配偶信息 */
    public static String PSN_MARITAL_FCODE = "AAX2";// 配偶信息";
    
    /** AAX2-配偶信息 */
    public static String PSN_EPOST_FCODE = "AAQ1";
    
    // public static String hi_psndoc_glbdef12; -企业岗位(1)
    // ////////////////////////////////////////////////////
    
    /** AA01-基本信息 */
    public static String PSN_BASE_FNAME = "AA01-基本信息";
    
    /** AA04-全日制学历 */
    public static String PSN_EDUA_FNAME = "AA04-全日制学历";
    
    /** AA08-学历信息 */
    public static String PSN_EDU_FNAME = "AA08-学历信息";
    
    /** AA09-学位信息 */
    public static String PSN_DEGREE_FNAME = "AA09-学位信息";
    
    /** AA22-政治面貌 */
    public static String PSN_POLITY_FNAME = "AA22-政治面貌";
    
    /** AA26-考核情况 */
    public static String PSN_ASSE_FNAME = "AA26-考核情况";
    
    /** AA37-培训记录 */
    public static String PSN_TRAIN_FNAME = "AA37-培训记录";
    
    /** AA79-家庭信息 */
    public static String PSN_FAMILY_FNAME = "AA79-家庭信息";
    
    /** AA85-人事档案管理 */
    public static String PSN_DOC_FNAME = "AA85-人事档案管理";
    
    /** AAB0-出入证信息 */
    public static String PSN_PASS_FNAME = "AAB0-出入证信息";
    
    /** AAB3-因私证件 */
    public static String PSN_PRIV_CARD_FNAME = "AAB3-因私证件";
    
    /** AAL2-个人简历 */
    public static String PSN_WORK_FNAME = "AAL2-个人简历";
    
    /** AAQ1-企业岗位 */
    public static String PSN_POST_FNAME = "AAQ1-企业岗位";
    
    /** AAR0-在职学历 */
    public static String PSN_EDU_JOB_FNAME = "AAR0-在职学历";
    
    /** AARH-合同信息 */
    public static String PSN_CTRT_FNAME = "AARH-合同信息";
    
    /** AAX2-配偶信息 */
    public static String PSN_MARITAL_FNAME = "AAX2-配偶信息";
    
    /** 配偶信息 */
    public static String PSN_EPOST_FNAME = "AAQ1-企业岗位";
    
    /** AA01-基本信息编码 */
    public static String[] PSN_BASE_CODE = new String[]{
        "ZA0100",
        "AA01ID",
        "ZC9993",
        "AB0110",
        "AE0122",
        "ZA0101",
        "AA0107",
        "AA0121",
        "AA0117",
        "AA0111",
        "AA0114",
        "AA0141",
        "AX0165",
        "AA1601",
        "AX1631",
        "AA0177",
        "AA0171",
        "AA0124",
        "AA0127",
        "AA0137",
        "AX0195",
        "AA0138",
        "AA0146",
        "AX0189",
        "AX0196",
        "AX0193",
        "AX0194",
        "AA0148",
        "AX0192",
        "AX0178",
        "AA0103",
        "AX0187",
        "UpdateTime"};
    
    /** AA01-基本信息名称 */
    public static String[] PSN_BASE_NAME = new String[]{
        "人员编码",
        "人员序号",
        "状态标志",
        "部门",
        "处室",
        "姓名",
        "性别",
        "民族",
        "出生地",
        "出生日期",
        "籍贯",
        "参加工作时间",
        "入社时间",
        "入社方式",
        "从何处来社",
        "身份证号",
        "户籍所在地",
        "健康状况",
        "婚姻状况",
        "现住址",
        "住址邮政编码",
        "住址电话",
        "电子邮箱",
        "办公地址",
        "办公电话",
        "宽带电话",
        "VPN电话",
        "移动电话",
        "传真号码",
        "记者证号",
        "姓氏拼音",
        "特长",
        "更新时间"};
    
    /** AA04-全日制学历编码 */
    public static String[] PSN_EDUA_CODE = new String[]{
        "ZA0100",
        "AA04ID",
        "ZC9993",
        "AA0407",
        "AA0440",
        "AA0435",
        "AA0411",
        "AA0415",
        "AA0430",
        "AA0420",
        "AA0406",
        "AA0443",
        "AA0425",
        "UpdateTime"};
    /** AA04-全日制学历名称 */
    public static String[] PSN_EDUA_NAME = new String[]{
        "人员编码",
        "学历学位子集序号",
        "状态标志",
        "全日制学历",
        "全日制学位",
        "全日制毕业院校",
        "全日制所学专业",
        "全日制入学时间",
        "全日制毕业时间",
        "全日制类型",
        "全日制学历证书号",
        "全日制学位证书号",
        "全日制学制",
        "更新时间"};
    
    /** AA08-学历信息编码 */
    public static String[] PSN_EDU_CODE = new String[]{
        "ZA0100",
        "AA08ID",
        "ZC9993",
        "AA0807",
        "AA0835",
        "AA0811",
        "AA0815",
        "AA0830",
        "AA0849",
        "AA0806",
        "UpdateTime"};
    /** AA08-学历信息名称 */
    public static String[] PSN_EDU_NAME = new String[]{
        "人员编码",
        "学历序号",
        "状态标志",
        "学历",
        "毕业院校",
        "所学专业",
        "入学时间",
        "毕业时间",
        "教育类别",
        "学历证书号",
        "更新时间"};
    
    /** AA09-学位信息编码 */
    public static String[] PSN_DEGREE_CODE = new String[]{
        "ZA0100",
        "AA09ID",
        "ZC9993",
        "AA0941",
        "AA0945",
        "AA0955",
        "AA0943",
        "UpdateTime"};
    /** AA09-学位信息名称 */
    public static String[] PSN_DEGREE_NAME = new String[]{"人员编码", "学位序号", "状态标志", "学位", "授予时间", "授予单位", "学位证书号", "更新时间"};
    
    /** AA22-政治面貌编码 */
    public static String[] PSN_POLITY_CODE = new String[]{
        "ZA0100",
        "AA22ID",
        "ZC9993",
        "AA2205",
        "AA2210",
        "AA2220",
        "AA2225",
        "AA2230",
        "AA2233",
        "AA2235",
        "UpdateTime"};
    /** AA22-政治面貌名称 */
    public static String[] PSN_POLITY_NAME = new String[]{
        "人员编码",
        "政治面貌序号",
        "状态标志",
        "政治面貌",
        "参加时间",
        "介绍人",
        "转正时间",
        "备注",
        "登记人",
        "登记时间",
        "更新时间"};
    
    /** AA26-考核情况编码 */
    public static String[] PSN_ASSE_CODE = new String[]{"ZA0100", "AA26ID", "ZC9993", "AA2502", "AA2520", "UpdateTime"};
    /** AA26-考核情况名称 */
    public static String[] PSN_ASSE_NAME = new String[]{"人员编码", "本年度考核情况序号", "状态标志", "考核年度", "考核结果", "更新时间"};
    
    /** AA37-培训记录编码 */
    public static String[] PSN_TRAIN_CODE = new String[]{
        "ZA0100",
        "AA37ID",
        "ZC9993",
        "AK3701",
        "AX3735",
        "AA3715",
        "AA3720",
        "AA3730",
        "AX3743",
        "AX3746",
        "AK3744",
        "AA3710",
        "AA3740",
        "AA3705",
        "UpdateTime"};
    /** AA37-培训记录名称 */
    public static String[] PSN_TRAIN_NAME = new String[]{
        "人员编码",
        "教育培训序号",
        "状态标志",
        "培训类别",
        "培训班名称",
        "培训开始时间",
        "培训结束时间",
        "培训主要内容",
        "培训学时",
        "当年合计学时",
        "培训成绩",
        "学习方式",
        "主办单位",
        "教育培训性质",
        "更新时间"};
    
    /** AA79-家庭信息编码 */
    public static String[] PSN_FAMILY_CODE = new String[]{
        "ZA0100",
        "AA7905",
        "AA7908",
        "AA7910",
        "AA7915",
        "AA7920",
        "AA7925",
        "AA7930",
        "AA79ID"};
    /** AA79家庭信息名称 */
    public static String[] PSN_FAMILY_NAME = new String[]{"人员编码", "姓名", "性别", "与本人的关系", "出生日期", "工作单位及职务", "政治面貌", "民族", "家庭成员及社会关系子集序号"};
    
    /** AA85-人事档案管理编码 */
    public static String[] PSN_DOC_CODE = new String[]{
        "ZA0100",
        "AA85ID",
        "ZC9993",
        "AX8501",
        "AA8505",
        "AA8510",
        "AA8515",
        "AA8520",
        "AA8525",
        "AA8530",
        "AA8535",
        "AA8540",
        "AA8545",
        "AA8565",
        "AA8560",
        "AA8555",
        "AA8550",
        "AA8570",
        "AA8575",
        "UpdateTime"};
    /** AA85-人事档案管理名称 */
    public static String[] PSN_DOC_NAME = new String[]{
        "人员编码",
        "人事档案子集序号",
        "状态标志",
        "档案传递号",
        "档案编号",
        "档案类别",
        "档案转入时间",
        "档案来处",
        "档案转出时间",
        "档案去处",
        "本人转档（或代办）",
        "联系电话",
        "保管状态",
        "所在单位",
        "备注",
        "档案系统编码",
        "管档类别",
        "审核人",
        "审核时间",
        "更新时间"};
    
    /** AAB0-出入证信息编码 */
    public static String[] PSN_PASS_CODE = new String[]{"ZA0100", "AAB0ID", "ZC9993", "AAB005", "AAB010", "UpdateTime"};
    /** AAB0-出入证信息名称 */
    public static String[] PSN_PASS_NAME = new String[]{"人员编码", "出入证编码序号", "状态标志", "部门", "部门人员", "更新时间"};
    
    /** AAB3-因私证件编码 */
    public static String[] PSN_PRIV_CARD_CODE = new String[]{
        "ZA0100",
        "AAB3ID",
        "ZC9993",
        "AAB301",
        "AAB302",
        "AAB303",
        "AAB304",
        "AAB305",
        "AAB312",
        "AAB313",
        "AAB314",
        "AAB315",
        "AAB322",
        "AAB323",
        "AAB324",
        "AAB325",
        "UpdateTime"};
    /** AAB3-因私证件名称 */
    public static String[] PSN_PRIV_CARD_NAME = new String[]{
        "人员编码",
        "证件信息序号",
        "状态标志",
        "因私护照证件类别",
        "因私护照证件号码",
        "因私护照有效期起始时间",
        "因私护照有效期终止时间",
        "因私护照证件状态",
        "港澳通行证号码",
        "港澳通行证有效期起始时间",
        "港澳通行证有效期终止时间",
        "港澳通行证证件状态",
        "台湾通行证号码",
        "台湾通行证有效期起始时间",
        "台湾通行证有效期终止时间",
        "台湾通行证件状态",
        "更新时间"};
    
    /** AAL2-个人简历编码 */
    public static String[] PSN_WORK_CODE = new String[]{"ZA0100", "AA19ID", "ZC9993", "AA1905", "AA1910", "AA1915", "AA1920", "AA1925"};
    /** AAL2个人简历名称 */
    public static String[] PSN_WORK_NAME = new String[]{"人员编码x", "简历序号", "状态标志", "起始时间", "终止时间", "所在单位", "从事工作或担任职务", "证明人"};
    
    /** AAQ1-企业岗位编码 */
    public static String[] PSN_POST_CODE = new String[]{
        "ZA0100",
        "AAQ1ID",
        "ZC9993",
        "AAQ101",
        "AAQ105",
        "AAQ107",
        "AAQ109",
        "AAQ111",
        "AAQ113",
        "AAQ115",
        "AAQ117",
        "UpdateTime"};
    /** AAQ1-企业岗位名称 */
    public static String[] PSN_POST_NAME = new String[]{
        "人员编码",
        "企业岗位序号",
        "状态标志",
        "岗位名称",
        "聘任时间",
        "聘期起始时间",
        "聘期终止时间",
        "岗位级别",
        "岗位类别",
        "聘任文号",
        "聘任单位",
        "更新时间"};
    
    /** AAR0-在职学历编码 */
    public static String[] PSN_EDU_JOB_CODE = new String[]{
        "ZA0100",
        "AAR0ID",
        "ZC9993",
        "AAR007",
        "AAR040",
        "AAR035",
        "AAR011",
        "AAR015",
        "AAR030",
        "AAR020",
        "AAR006",
        "AAR043",
        "UpdateTime"};
    /** AAR0-在职学历名称 */
    public static String[] PSN_EDU_JOB_NAME = new String[]{
        "人员编码",
        "参加工作学历序号",
        "状态标志",
        "学历",
        "学位",
        "毕业院校",
        "所学专业",
        "入学时间",
        "毕业时间",
        "学习形式",
        "学历证书号",
        "学位证书号",
        "最后修改时间"};
    /** AARH-合同信息编码 */
    public static String[] PSN_CTRT_CODE = new String[]{
        "ZA0100",
        "AARHID",
        "ZC9993",
        "AARH02",
        "AARH01",
        "AARH03",
        "AARH05",
        "AARH07",
        "UpdateTime"};
    /** AARH-合同信息名称 */
    public static String[] PSN_CTRT_NAME = new String[]{"人员编码", "合同管理序号", "状态标志", "合同类别", "合同编号", "签订时间", "起始时间", "终止时间", "更新时间"};
    
    /** AAX2-配偶信息编码 */
    public static String[] PSN_MARITAL_CODE = new String[]{
        "ZA0100",
        "AAX2ID",
        "ZC9993",
        "AAX201",
        "AAX205",
        "AAX277",
        "AAX210",
        "AAX250",
        "AAX225",
        "AAX220",
        "AAX215",
        "AAX216",
        "AAX203",
        "AAX236",
        "AAX230",
        "AAX235",
        "UpdateTime"};
    /** AAX2-配偶信息名称 */
    public static String[] PSN_MARITAL_NAME = new String[]{
        "人员编码",
        "外派情况序号",
        "状态标志",
        "配偶姓名",
        "出生日期",
        "身份证号",
        "民族",
        "健康状况",
        "政治面貌",
        "学历",
        "籍贯",
        "国籍",
        "工作单位",
        "任何职务",
        "懂何外语与专业技术",
        "专业技术职务",
        "更新时间"};
    /** AAQ1-企业岗位编码 */
    public static String[] PSN_EPOST_CODE = new String[]{
        "ZA0100",
        "AAQ1ID",
        "ZC9993",
        "AAQ101",
        "AAQ105",
        "AAQ107",
        "AAQ109",
        "AAQ111",
        "AAQ113",
        "AAQ115",
        "AAQ117",
        "UpdateTime"};
    
    /** AAQ1-企业岗位名称 */
    public static String[] PSN_EPOST_NAME = new String[]{
        "人员编码",
        "企业岗位序号",
        "状态标志",
        "岗位名称",
        "聘任时间",
        "聘期起始时间",
        "聘期终止时间",
        "岗位级别",
        "岗位类别",
        "聘任文号",
        "聘任单位",
        "更新时间"};
}
