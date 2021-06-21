package pers.bc.utils.webservice;

/**
 * 用於webservice 传输数据接口,可以被继承使用
 * @qualiFild pers.bc.utils.webservice.My_Itf_PubWebService.java<br>
 * @author：LiBencheng<br>
 * @date Created on 2020-4-28<br>
 * @version 1.0<br>
 */
public interface My_Itf_PubWebService
{
    /**
     * *********************************************************** <br>
     * 说明： json 参数 支持 {tablename:"xxxxx"} 和 [{name:"李四"...},{name:"张三"...},{name:"王五"...}]
     * @param json
     * @return
     * @throws Exception
     * @author licheng
     * @date Created on 2018-12-20
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public Object returnData(String json) throws Exception;
    
    /**
     * *********************************************************** <br>
     * 说明：不定长参数列表方法，不建议用
     * @param prams
     * @return
     * @throws Exception
     * @author licheng
     * @date Created on 2018-12-20
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public Object returnDataExt(String... prams) throws Exception;
    
}
