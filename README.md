### 第一个项目

## 项目描述

一款简单的分布式定时任务解决方案

## 项目特色

1、市面上有很多分布式定时任务，例如：xxljob等，但是部署麻烦。如果需要本地化分布式部署，可以采用该插件<br>
2、完全兼容spring自带的定时任务实现方案，只增加了分布式的实现机制

## 插件依赖

只依赖redis配置

## 使用过程

1、拷贝com.example.xddemo.scheduled包下面的文件到新项目中<br>
2、参考com.example.xddemo.scheduled.TestJob类，提供了使用的demo<br>
3、引入redis，spring-boot-starter-data-redis包依赖<br>
4、添加redis配置<br>
spring.redis.sentinel.nodes=<br>
spring.redis.sentinel.master=<br>
spring.redis.password=<br>
spring.redis.client-type=jedis

### 第二个项目

## 项目描述

一种支持feign调用get请求传对象的解决方案

## 项目特色

1、在某些低版本的feign调用，不支持get请求传对象，所以开发了该插件

## 插件依赖

依赖fastjson包

## 使用过程

1、拷贝com.example.xddemo.feign包下面的FeignInterceptor类到新项目中即可<br>
2、参考com.example.xddemo.feign.FeignTest类，提供了使用的demo<br>


