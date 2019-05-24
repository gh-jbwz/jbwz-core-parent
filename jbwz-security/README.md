# jbwz-Security
引入jbwz-security项目的同时已经添加了如下功能:

```text
LoginController         登录类，已有login方法,登陆必须要传入的3个参数:username,password,loginType

BaseUserDetailService   查询用户及校验密码，必须要有一个类继承此类。
AuthorizationRoleResourceService  获取所有的角色和资源的对应关系,必须有一个类继承此类.
```
#### 可配置的属性
```
login.security.excludes.urls   列表，不需要安全认证的url
login.session-timeout.web    web端登陆超时时间，默认30分钟    
login.session-timeout.app    app手机端登陆超时时间，默认15天    
```

