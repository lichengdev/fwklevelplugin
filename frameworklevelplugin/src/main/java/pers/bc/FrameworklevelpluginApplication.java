package pers.bc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FrameworklevelpluginApplication
{
    
    public static void main(String[] args)
    {
        SpringApplication.run(FrameworklevelpluginApplication.class, args);
    }
    
}
