## 天天商城

基于淘淘商城修改的项目(TTMall), 基于JDK 11, 使用Docker部署

### 技术架构

后端基于JDK 11, 采用经典的SSM(Spring + Spring MVC + Mybatis架构), 页面采用经典的JSP, 部署采用基于Docker的Tomcat 9容器部署

-   父工程ttmall-parent
    -   项目通用模块: ttmall-common
    -   后端模块父工程: ttmall-manager
        -   ttmall-entity
        -   ttmall-mapper
        -   ttmall-service
        -   ttmall-web

### 项目特色

-   使用过时的JSP, 温故而知新~
-   基于JDK 11开发
-   整个项目基于SSM, 通过Docker镜像部署
-   项目采用多模块构建

<br/>

### 项目依赖

|       项目依赖名称        |    版本号     |
| :-----------------------: | :-----------: |
|            JDK            |    11.0.5     |
|          Spring           | 5.2.2.RELEASE |
|          Mybatis          |     3.5.2     |
|      Mybatis-Spring       |     2.0.2     |
|     Mybatis-Generator     |     1.3.6     |
|     Mybatis-Paginator     |    1.2.17     |
|        PageHelper         |    5.1.11     |
|           MySQL           |    8.0.18     |
|           Druid           |    1.1.21     |
|        HttpClient         |    4.5.10     |
|           Jedis           |     3.1.0     |
|           Solr            |     8.3.1     |
|           JSTL            |      1.2      |
|        JSqlParser         |      3.1      |
|          JSP-Api          |      2.0      |
|        Servlet-Api        |      2.5      |
|          Jackson          |    2.10.1     |
|   Apache Commons-lang3    |      3.9      |
|     Apache Commons-io     |      2.6      |
|    Apache Commons-net     |      3.6      |
| Apache Commons-fileupload |      1.4      |
|         Joda-time         |    2.10.5     |
|           slf4j           |    1.7.30     |
|          Lombok           |    1.18.10    |
|           Junit           |     4.12      |

