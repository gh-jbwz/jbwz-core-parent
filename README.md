# jbwz-CORE-PARENT
本项目把jbwz常用通用代码构建成一个独立的项目，方便使用。以后不需要再复制源码到其它项目，只用配置maven私服,直接`pom`引用就可以了.

#### `pom`引用依赖`jar`包
> 在`pom.xml`中添加一个所有jar的依赖,jbwz-all包含了所有`jbwz-core`项目
```xml
<dependency>
    <groupId>com.jbwz.core</groupId>
    <artifactId>jbwz-all</artifactId>
    <version>0.0.1</version>
</dependency>

```
> 如果要单独引用某一个项目可以在`pom.xml`中添加一个相应名字的依赖即可，例如只是用缓存项目`jbwz-cache`：
```xml
<dependency>
    <groupId>com.jbwz.core</groupId>
    <artifactId>jbwz-cache</artifactId>
    <version>0.0.1</version>
</dependency>

```

