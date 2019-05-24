### Mybatis 集成
这个项目相当于把数据库操作需要用到的pom集成到了这个项目中
1. 引入此项目到pom.xml
2. 主类上添加注解`@MapperScan("com.x.x.dao")`

做完上面2步,在`application.yml`配置`mybatis`以`mybatis-plus`开头

例如:
```
mybatis-plus:
  config-location: 
```


### 自动生成代码
修改`GeneratorMain.java`中的配置，直接运行就可以把数据库表生成代码了
