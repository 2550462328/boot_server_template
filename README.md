这是一个 云医声的模板业务服务，快速搭建项目，集成以下优点

1、快速便捷的 一站式搭建，即装即启动

根据 云医声实际的业务场景，融入了 多样的 业务工具方法（经过线上实战） 

2、丰富的日志系统

包括但不限于多环境的日志输出环境 和 有效的请求链路追踪日志

3、安全的 请求 和 响应报文加密

针对 业务的请求 和 响应报文 可采用多方方式进行加密，目前主流使用AES

4、安全的请求来源校验

安全的拦截器机制 可以 有效的 校验 请求中的 token（用户权限） 和 sign（方法签名） 的合法性

5、异常机制

本系统 采用 Assert（断言）的方式 替代传统的 try-catch 处理异常的方式，结合 国际化的 报错信息处理，可以比较快速 和 清爽的处理异常情况



##############################启动###############################################################

如果 在本地采用远程 nacos的方式启动本项目 可以 尝试在Program options中加入以下参数

// 启动参数

 --spring.application.name=yys-paas-server-template
 --spring.cloud.nacos.config.server-addr=172.31.242.161:8848
 --spring.cloud.nacos.config.namespace=yysqyb-dev
 --spring.cloud.nacos.config.username=nacos
 --spring.cloud.nacos.config.password=nacos
 --spring.cloud.nacos.config.group=DEFAULT_GROUP
 --spring.cloud.nacos.config.file-extension=yaml
 --spring.main.allow-bean-definition-overriding=true
 --spring.cloud.nacos.config.shared-dataids=yys-paas-server-template.yml
 --spring.cloud.nacos.config.refreshable-dataids=yys-paas-server-template.yml





