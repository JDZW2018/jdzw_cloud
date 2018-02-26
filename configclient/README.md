spring.application.name：对应前配置文件中的{application}部分 
spring.cloud.config.profile：对应前配置文件中的{profile}部分 
spring.cloud.config.label：对应前配置文件的git分支 
spring.cloud.config.uri：配置中心的地址


为什么要统一管理配置？

1、集中管理 
2、不同环境不同配置 
3、运行期间动态调整配置 
4、自动刷新（spring-cloud 实现更新配置不用重启服务 @FreshScope）

简介

Spring Cloud Config为分布式系统外部化配置提供了服务器端和客户端的支持，它包括Config Server和Config Client两部分。由于Config Server和Config Cleint都实现了对Spring Environment和PropertySource抽象的映射，因此，Spring Cloud非常适合Spring应用程序，当然也可与任何其他语言编写的应用 
程序配合使用。

Config Server是一个可横向扩展、集中式的配置服务器，它用于集中管理应用程序各个环境下的配置，默认使用Git存储配置内容(也可使用Subversion、本地文件系统或Vault存储配置),因此可以方便的实现对配置的版本控制与内容审计。

Config Client 是Config Server的客户端，用于操作存储在Config Server中的配置属性。