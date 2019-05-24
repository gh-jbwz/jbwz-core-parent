package com.jbwz.core.mybatis.generator.config;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class CodeGenerator {
    final GlobalConfig globalConfig;
    final DataSourceConfig dataSourceConfig;
    final TemplateConfig templateConfig;
    final InjectionConfig injectionConfig;
    final PackageConfig packageConfig;
    final StrategyConfig strategyConfig;

    CodeGenerator(GeneratorBuilder builder) {
        globalConfig = builder.globalConfig;
        dataSourceConfig = builder.dataSourceConfig;
        templateConfig = builder.templateConfig;
        injectionConfig = builder.injectionConfig;
        packageConfig = builder.packageConfig;
        strategyConfig = builder.strategyConfig;

    }

    public static GeneratorBuilder newBuilder() {
        return new GeneratorBuilder();
    }

    public void generate() {
        AutoGenerator generator = new AutoGenerator();
        generator.setGlobalConfig(globalConfig);
        generator.setStrategy(strategyConfig);
        generator.setDataSource(dataSourceConfig);
        generator.setPackageInfo(packageConfig);
        generator.setTemplate(templateConfig);
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        generator.setCfg(injectionConfig);
        generator.execute();
    }

    public final static class GeneratorBuilder {

        TemplateConfig templateConfig = new TemplateConfig();
        PackageConfig packageConfig = new PackageConfig();
        StrategyConfig strategyConfig = new StrategyConfig();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        GlobalConfig globalConfig = new GlobalConfig();
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        public GeneratorBuilder() {
            templateConfig.setEntity("/template/entity.java");
            templateConfig.setController("/template/controller.java");
            templateConfig.setService("/template/service.java");
            templateConfig.setServiceImpl("/template/serviceImpl.java");
            templateConfig.setMapper("/template/mapper.java");
            templateConfig.setXml("/template/mapper.xml");
        }

        public GeneratorBuilder setTemplateConfig(TemplateConfig templateConfig) {
            this.templateConfig = templateConfig;
            return this;
        }

        public GeneratorBuilder setInjectionConfig(InjectionConfig injectionConfig) {
            this.injectionConfig = injectionConfig;
            return this;
        }

        public GeneratorBuilder setPackageConfig(PackageConfig packageConfig) {
            this.packageConfig = packageConfig;
            return this;
        }

        public GeneratorBuilder setStrategyConfig(IStrategyConfig strategyConfigService) {
            strategyConfigService.configStrategy(this.strategyConfig);
            return this;
        }

        public GeneratorBuilder setDataSourceConfig(DataSourceConfig dataSourceConfig) {
            this.dataSourceConfig = dataSourceConfig;
            return this;
        }

        public GeneratorBuilder setGlobalConfig(GlobalConfig globalConfig) {
            this.globalConfig = globalConfig;
            return this;
        }

        /**
         * 设置输出路径
         *
         * @param path 文件输出路径
         * @return
         */
        public GeneratorBuilder setOutputDir(String path) {
            globalConfig.setOutputDir(path);
            globalConfig.setOpen(true);
            globalConfig.setFileOverride(true);
            globalConfig.setMapperName("%sDao");
            globalConfig.setAuthor("yyh");
            return this;
        }

        /**
         * 设置数据源，默认mysql数据库
         *
         * @param hostAndDbName 数据库连接地址和数据名称
         * @return
         */
        public GeneratorBuilder setDbUrl(String hostAndDbName) {
            dataSourceConfig.setUrl("jdbc:mysql://" + hostAndDbName + "?useUnicode=true&useSSL=false&characterEncoding=utf8");
            dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
            return this;
        }

        /**
         * 设置连接数据库用户名密码
         *
         * @param username 用户名
         * @param password 密码
         * @return
         */
        public GeneratorBuilder setDbUP(String username, String password) {
            dataSourceConfig.setUsername(username);
            dataSourceConfig.setPassword(password);
            return this;
        }

        /**
         * 设置根包名
         *
         * @param packageName
         * @return
         */
        public GeneratorBuilder setRootPackage(String packageName) {
            this.packageConfig.setParent(packageName);
            return this;
        }

        public CodeGenerator build() {
            return new CodeGenerator(this);
        }

    }

}
