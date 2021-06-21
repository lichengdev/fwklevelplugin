package pers.bc.utils.ant;

/**
 * 
 * @qualiFild pers.bc.utils.ant.Student.java<br>
 * @author：licheng<br>
 * @date Created on 2019年11月8日<br>
 * @version 1.0<br>
 */
@TableName("Student")
public class Student
{
    @Property("id")
    private int id;
    @Property("user_name")
    private String username;
    @Property("age")
    private int age;
    @Property("city")
    private String city;
    @Property("phone")
    private String phone;
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public int getAge()
    {
        return age;
    }
    
    public void setAge(int age)
    {
        this.age = age;
    }
    
    public String getCity()
    {
        return city;
    }
    
    public void setCity(String city)
    {
        this.city = city;
    }
    
    public String getPhone()
    {
        return phone;
    }
    
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
}
