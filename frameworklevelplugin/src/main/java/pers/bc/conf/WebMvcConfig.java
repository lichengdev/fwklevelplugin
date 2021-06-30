package pers.bc.conf;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
//@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{
    // 使用配置方式这里会有中文乱码的现象
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/").setViewName("forward:index.html");
    }
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void FindUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    { 
        request.getRequestDispatcher("/index.html").forward(request, response);
    }
}
