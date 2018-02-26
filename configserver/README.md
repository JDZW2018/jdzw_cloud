#配置git仓库位置
spring.cloud.config.server.git.uri=https://git.oschina.net/honglan1009/wcb_cloud/
#配置仓库路径下的相对搜索位置，可以配置多个
spring.cloud.config.server.git.searchPaths=configrepo/config
#访问git仓库的用户名
spring.cloud.config.server.git.username=username
#访问git仓库的用户密码
spring.cloud.config.server.git.password=password


然后访问启动的项目，通过URL路径匹配相应的配置文件，URL与配置文件的映射关系如下：

/{application}/{profile}[/{label}] 
/{application}-{profile}.yml 
/{label}/{application}-{profile}.yml 
/{application}-{profile}.properties 
/{label}/{application}-{profile}.properties

访问configclient-dev.yml文件的两种方法：
第一种方法：http://127.0.0.1:7001/configclient-dev.yml
访问结果：
from: 11112211
server:
  port: 7003
spring:
  rabbitmq:
    host: 192.168.2.231
    password: wcbtest
    port: 5672
    username: wcbtest
    virtual-host: wcbtest
第二种方法：http://127.0.0.1:7001/configclient/dev
访问结果：
{"name":"configclient","profiles":["dev"],"label":null,"version":"c310f8dcc36aa206caaab2e20c1ab9a6362f20bd","state":null,"propertySources":[{"name":"https://git.oschina.net/honglan1009/wcb_cloud/configrepo/config/configclient-dev.yml","source":{"server.port":7003,"from":11112211,"spring.rabbitmq.virtual-host":"wcbtest","spring.rabbitmq.host":"192.168.2.231","spring.rabbitmq.port":5672,"spring.rabbitmq.username":"wcbtest","spring.rabbitmq.password":"wcbtest"}}]}