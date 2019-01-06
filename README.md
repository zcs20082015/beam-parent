# beam-parent

#### 项目介绍
- Beam基于SpringBoot 2，致力于做更简洁的后台管理系统。springmvc + shiro + mybatis-plus + vue。基础模块：用户管理，角色管理，部门管理，菜单模块，定时任务。
- 项目代码参考了
  - Guns:https://gitee.com/stylefeng/guns。
  - renren-security：https://gitee.com/renrenio/renren-security。
  - vue-manage-system：https://github.com/lin-xin/vue-manage-system。

#### 项目结构
````
beam-parent
├─beam-common     公共模块
│ 
├─beam-admin     管理后台
│        └─resources 
│           ├─application.yml  配置文件
│           ├─logback-spring.xml  日志配置文件
│ 
├─beam-rest        API服务
│        └─seckill 秒杀测试模块
│           
│ 
│ 
│ 
├─beam-generator  代码生成器
│        └─resources 
│           ├─mapper   MyBatis文件
│           ├─template 代码生成器模板（可增加或修改相应模板）
│           ├─application.yml    全局配置文件
│           └─generator.properties   代码生成器，配置文件
│       
│ 
├─beam-web  公用实体类、dao、service
│       
│ 
````

<br>

#### 技术选型
- 核心框架：Spring Boot 2.0
- 安全框架：Apache Shiro 1.4
- 视图框架：Spring MVC 5.0
- 持久层框架：MyBatis 3.3
- 定时器：Quartz 2.3
- 数据库连接池：hikari
- 页面交互：Vue2.x
- 前后端分离


#### 软件需求
- JDK1.8
- MySQL5.5+
- Maven3.0+


#### 演示地址
http://www.hsshy.cn

#### 项目截图
##### 后台管理系统：
![image.png](https://upload-images.jianshu.io/upload_images/13498144-c83089109737709c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![image.png](https://upload-images.jianshu.io/upload_images/13498144-3efb2aa334717c6b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![image.png](https://upload-images.jianshu.io/upload_images/13498144-47608fef199fac65.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![image.png](https://upload-images.jianshu.io/upload_images/13498144-23439e67ae3df2f7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![image.png](https://upload-images.jianshu.io/upload_images/13498144-01f8a58a32d513ca.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![image.png](https://upload-images.jianshu.io/upload_images/13498144-81e727c82affa1b0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![image.png](https://upload-images.jianshu.io/upload_images/13498144-32261ca3ec99e6ee.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![image.png](https://upload-images.jianshu.io/upload_images/13498144-5bb29689629e43c9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![image.png](https://upload-images.jianshu.io/upload_images/13498144-56d2d548f945d51b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)