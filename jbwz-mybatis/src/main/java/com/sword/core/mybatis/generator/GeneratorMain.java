package com.jbwz.core.mybatis.generator;


import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.jbwz.core.mybatis.generator.config.CodeGenerator;


public class GeneratorMain {

    public static void main(String[] args) {
        // 根包名
        String ROOT_PACKAGE_NAME = "com.jbwz.realty";
        // 通用类包名
        String BASE_PACKAGE_NAME = "com.jbwz.core";
        // 表名前缀
        String tablePrefix = "";
        // 表名字，可以是正则
        String[] tableNames = {".*"};
        CodeGenerator codeGenerator = CodeGenerator.newBuilder()
                .setOutputDir("D:\\code-generator\\")
                .setDbUrl("192.168.2.188:3306/realtydb")
                .setDbUP("root", "root")
                .setRootPackage(ROOT_PACKAGE_NAME)
                .setStrategyConfig(strategyConfig -> {
                    // 下划线转驼峰
                    strategyConfig.setNaming(NamingStrategy.underline_to_camel);
                    strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
                    strategyConfig.setEntityTableFieldAnnotationEnable(true);
                    //实体类用 Lombok
                    strategyConfig.setEntityLombokModel(true);
                    //是否是RestController
                    strategyConfig.setRestControllerStyle(true);
                    //设置BaseController
                    strategyConfig.setSuperControllerClass(BASE_PACKAGE_NAME + "common.base.BaseController");
                    //设置BaseDao
                    strategyConfig.setSuperMapperClass(BASE_PACKAGE_NAME + ".mybatis.base.BaseDao");
                    //设置BaseService
                    strategyConfig.setSuperServiceClass(BASE_PACKAGE_NAME + ".mybatis.base.BaseService");
                    //设置BaseServiceImpl
                    strategyConfig.setSuperServiceImplClass(BASE_PACKAGE_NAME + ".mybatis.base.BaseServiceImpl");
                    strategyConfig.setControllerMappingHyphenStyle(true);
                    strategyConfig.setTablePrefix(tablePrefix); //设置表名前缀
                    strategyConfig.setInclude(tableNames);//设置哪些数据库表需要生成代码,可以正则
                })
                .build();
        codeGenerator.generate();

    }

}
