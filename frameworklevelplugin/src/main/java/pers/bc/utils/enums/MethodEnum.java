package pers.bc.utils.enums;

/**
 * 1->GET， 2->POST， 3->PUT， 4->DELETE
 * @qualiFild pers.bc.utils.enums.MethodEnum.java<br>
 * @author：licheng<br>
 * @date Created on 2020年4月3日<br>
 * @version 1.0<br>
 */
public enum MethodEnum
{
    
    /**
     * get请求方法
     */
    GET(1, "GET"),
    
    /**
     * post请求方法
     */
    POST(2, "POST"),
    
    /**
     * put请求方法
     */
    PUT(3, "PUT"),
    
    /**
     * delete请求方法
     */
    DELETE(4, "DELETE");
    
    private Integer value;
    private String name;
    
    MethodEnum(Integer value, String name)
    {
        this.value = value;
        this.name = name;
    }
}
