package pers.bc.utils.sql;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig.Builder;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.builder.Controller;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.builder.Mapper;
import com.baomidou.mybatisplus.generator.config.builder.Service;
import com.baomidou.mybatisplus.generator.config.rules.DateType;

import pers.bc.utils.pub.DateUtil;

public class MyBatisPlusGenerator
{
    private static String[] tables = {
        
        " "
    
    };
    
    public static void main(String[] args) throws IOException
    {
        String canonicalPath = new File("").getCanonicalPath();
        String time = DateUtil.dateTime(new Date());
        // PackageConfig packageConfigBuilder =
        PackageConfig.Builder packageBuilder = new PackageConfig.Builder();
        packageBuilder.joinPackage(canonicalPath);
        
        // packageBuilder.parent(basePackage);// 自定义包路径
        packageBuilder.controller("controller");// 这里是控制器包名，默认 web
        packageBuilder.entity("entity"); // 设置Entity包名，默认entity
        packageBuilder.mapper("mapper"); // 设置Mapper包名，默认mapper
        packageBuilder.service("service"); // 设置Service包名，默认service
        packageBuilder.serviceImpl("service.impl"); // 设置Service Impl包名，默认service.impl
        packageBuilder.xml("mapper"); // 设置Mapper XML包名，默认mapper.xml
        packageBuilder.parent("com.gameley.foodgame");
        packageBuilder.moduleName("fgconf");
        
        Builder dataSourceBuilder = new DataSourceConfig.Builder("jdbc:mysql://localhost:3306/dbfoodgame", "foodgame", "foodgame");
        DataSourceConfig dataSourceConfig = dataSourceBuilder.build();
        
        // StrategyConfig strategyConfig =
        StrategyConfig.Builder trategyBuilder = new StrategyConfig.Builder();
        trategyBuilder.addInclude(tables);
        trategyBuilder.addTablePrefix("fg_show_", "fg_", "fg_conf_", "fg_scrapecard_");
        
        Entity.Builder entityBuilder = trategyBuilder.entityBuilder();
        entityBuilder.superClass("com.gameley.foodgame.common.base.SuperEntity");
        entityBuilder.formatFileName("%sEntity");
        entityBuilder.enableRemoveIsPrefix();
        entityBuilder.enableSerialVersionUID();
        
        // entityBuilder.addIgnoreColumns(args);//忽略列
        
        Mapper.Builder mapperBuilder = trategyBuilder.mapperBuilder();
        mapperBuilder.formatMapperFileName("%sMapper");
        mapperBuilder.formatXmlFileName("%sMapper");
        
        Service.Builder serviceBuilder = trategyBuilder.serviceBuilder();
        serviceBuilder.formatServiceFileName("%sService");
        serviceBuilder.formatServiceImplFileName("%sServiceImpl");
        
        Controller.Builder controllerBuilder = trategyBuilder.controllerBuilder();
        controllerBuilder.formatFileName("%sController");
        
        GlobalConfig.Builder globalBuilder = new GlobalConfig.Builder();
        globalBuilder.author("licheng");
        globalBuilder.commentDate(time);
        globalBuilder.dateType(DateType.TIME_PACK);
        globalBuilder.outputDir(canonicalPath + "/src/main/java");
        
        TemplateConfig templateConfig = new TemplateConfig.Builder().build();
        
        InjectionConfig injectionConfig = new InjectionConfig.Builder().build();
        
        AutoGenerator gen = new AutoGenerator(dataSourceConfig);
        ConfigBuilder config =
            new ConfigBuilder(packageBuilder.build(), dataSourceConfig, trategyBuilder.build(), null, globalBuilder.build(), null);
        gen.config(config);
        // 执行生成
        gen.execute();
    }
}
