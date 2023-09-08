# SpringBoot3-核心特性

## **1、SpringBoot3-快速入门**

### 1、简介

#### 1. 前置知识

- Java17
- Spring / Spring MVC / MyBatis
- Maven / IDEA

#### 2. 环境要求

| 环境&工具          | 版本（or later） |
| ------------------ | ---------------- |
| SpringBoot         | 3.0.5+           |
| IDEA               | 2021.2.1+        |
| Java               | 17+              |
| Maven              | 3.5+             |
| Tomcat             | 10.0+            |
| Servlet            | 5.0+             |
| GraalVM Community  | 22.3+            |
| Native Build Tools | 0.9.19+          |

#### 3.SpringBoot是什么

SpringBoot 帮我们简单、快速地创建一个独立的、生产级别的 **Spring 应用（说明：SpringBoot底层是Spring）**

大多数 SpringBoot 应用只需要编写少量配置即可快速整合 Spring 平台以及第三方技术

**特性：**

- 快速创建独立 Spring 应用
  - SSM：导包、写配置、启动运行
- 直接嵌入Tomcat、Jetty or Undertow（无需部署 war 包）【Servlet容器】
  - **场景启动器**（starter）：web、json、邮件、oss（对象存储）、异步、定时任务、缓存...
  - 导包一堆，控制好版本
  - 为每一种场景准备了一个依赖；**web-starter。mybatis-starter**

- **重点：**按需自动配置 Spring 以及 第三方库
  - 如果这些场景我要使用（生效）。这个场景的所有配置都会自动配置好
  - **约定大于配置**：每个场景都有很多默认配置
  - 自定义：配置文件中修改几项就可以
- 提供生产级特性：如 监控指标、健康检查、外部化配置等
  - 监控指标、健康检查（k8s）、外部化配置
- 无代码生成、无xml

总结：简化开发，简化配置，简化整合，简化部署，简化监控，简化运维。

### **2、快速体验**

>  场景：浏览器发送/hello请求，返回"Hello,Spring Boot 3!"



#### **1. 开发流程**

##### **1. 创建项目**

###### maven 项目

```java
<!--    所有springboot项目都必须继承自 spring-boot-starter-parent -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.0.5</version>
    </parent>
```

##### 2.导入场景

###### 场景启动器

```java
    <dependencies>
<!--        web开发的场景启动器 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
```



##### **3. 主程序**

```java
@SpringBootApplication //这是一个SpringBoot应用
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}
```

##### 4. 业务

```java
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){

        return "Hello,Spring Boot 3!";
    }
}
```



##### 5. 测试

默认启动访问： localhost:8080

##### 6. 打包

```java
<!--    SpringBoot应用打包插件-->
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```

`mvn clean package`把项目打成可执行的jar包

`java -jar demo.jar`启动项目



#### 2. 特性小结

##### 1. 简化整合

导入相关的场景，拥有相关的功能。场景启动器

默认支持的所有场景：<https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.build-systems.starters>

- 官方提供的场景：命名为：`spring-boot-starter-*`
- 第三方提供场景：命名为：`*-spring-boot-starter`

场景一导入，万物皆就绪

##### 2. 简化开发

无需编写任何配置，直接开发业务

##### 3. 简化配置

application.properties :

- 集中式管理配置。只需要修改这个文件就行 。
- 配置基本都有默认值
- 能写的所有配置都在： <https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties>

##### 4. 简化部署

打包为可执行的jar包。linux服务器上有java环境。

##### 5. 简化运维

修改配置（外部放一个application.properties文件）、监控、健康检查。

.....

#### 3. Spring Initializr 创建向导

一键创建好整个项目结构

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1679922435118-bde3347e-b9fe-4138-8e16-0c231884ea5f.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_39%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

### 3、应用分析

#### 1. 依赖管理机制

思考：

##### 1、为什么导入`starter-web`所有相关依赖都导入进来？

- 开发什么场景，导入什么**场景启动器。**
- **maven依赖传递原则。A-B-C： A就拥有B和C**
- 导入 场景启动器。 场景启动器 自动把这个场景的所有核心依赖全部导入进来

##### 2、为什么版本号都不用写？

- 每个boot项目都有一个父项目`spring-boot-starter-parent`
- parent的父项目是`spring-boot-dependencies`
- 父项目 **版本仲裁中心**，把所有常见的jar的依赖版本都声明好了。
- 比如：`mysql-connector-j`

##### 3、自定义版本号

- 利用maven的就近原则

- - 直接在当前项目`properties`标签中声明父项目用的版本属性的key
  - 直接在**导入依赖的时候声明版本**

##### 4、第三方的jar包

- boot父项目没有管理的需要自行声明好

```java
<!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.2.16</version>
</dependency>
```

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1679294529375-4ee1cd26-8ebc-4abf-bff9-f8775e10c927.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_25%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)



#### 2. 自动配置机制

##### 1. 初步理解

- **自动配置**的 Tomcat、SpringMVC 等

- - **导入场景**，容器中就会自动配置好这个场景的核心组件。
  - 以前：DispatcherServlet、ViewResolver、CharacterEncodingFilter....
  - 现在：自动配置好的这些组件
  - 验证：**容器中有了什么组件，就具有什么功能**

```java
    public static void main(String[] args) {

        //java10： 局部变量类型的自动推断
        var ioc = SpringApplication.run(MainApplication.class, args);

        //1、获取容器中所有组件的名字
        String[] names = ioc.getBeanDefinitionNames();
        //2、挨个遍历：
        // dispatcherServlet、beanNameViewResolver、characterEncodingFilter、multipartResolver
        // SpringBoot把以前配置的核心组件现在都给我们自动配置好了。
        for (String name : names) {
            System.out.println(name);
        }
    }
```

- **默认的包扫描规则**

- - `@SpringBootApplication` 标注的类就是主程序类
  - **SpringBoot只会扫描主程序所在的包及其下面的子包，自动的component-scan功能**
  - **自定义扫描路径**

- - - @SpringBootApplication(scanBasePackages = "com.atguigu")
    - `@ComponentScan("com.atguigu")` 直接指定扫描的路径

- **配置默认值**

- - **配置文件**的所有配置项是和某个**类的对象**值进行一一绑定的。
  - 绑定了配置文件中每一项值的类： **属性类**。
  - 比如：

- - - `ServerProperties`绑定了所有Tomcat服务器有关的配置
    - `MultipartProperties`绑定了所有文件上传相关的配置
    - ....参照[官方文档](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties.server)：或者参照 绑定的  **属性类**。

- 按需加载自动配置

- - 导入场景`spring-boot-starter-web`
  - 场景启动器除了会导入相关功能依赖，导入一个`spring-boot-starter`，是所有`starter`的`starter`，基础核心starter
  - `spring-boot-starter`导入了一个包 `spring-boot-autoconfigure`。包里面都是各种场景的`AutoConfiguration`**自动配置类**
  - 虽然全场景的自动配置都在 `spring-boot-autoconfigure`这个包，但是不是全都开启的。

- - - 导入哪个场景就开启哪个自动配置



总结： 导入场景启动器、触发 `spring-boot-autoconfigure`这个包的自动配置生效、容器中就会具有相关场景的功能

##### 2. 完整流程

> 思考：
>
> **1、SpringBoot怎么实现导一个**`**starter**`**、写一些简单配置，应用就能跑起来，我们无需关心整合**
>
> 2、为什么Tomcat的端口号可以配置在`application.properties`中，并且`Tomcat`能启动成功？
>
> 3、导入场景后哪些**自动配置能生效**？
>
> 

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1679970508234-3c6b8ecc-6372-4eb5-8c67-563054d1a72d.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_37%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

***自动配置流程细节梳理：***

**1、**导入`starter-web`：导入了web开发场景

- 1、场景启动器导入了相关场景的所有依赖：`starter-json`、`starter-tomcat`、`springmvc`
- 2、每个场景启动器都引入了一个`spring-boot-starter`，核心场景启动器。
- 3、**核心场景启动器**引入了`spring-boot-autoconfigure`包。
- 4、`spring-boot-autoconfigure`里面囊括了所有场景的所有配置。
- 5、只要这个包下的所有类都能生效，那么相当于SpringBoot官方写好的整合功能就生效了。
- 6、SpringBoot默认却扫描不到 `spring-boot-autoconfigure`下写好的所有**配置类**。（这些**配置类**给我们做了整合操作），**默认只扫描主程序所在的包**。

**2、****主程序**：`@SpringBootApplication`

- 1、`@SpringBootApplication`由三个注解组成`@SpringBootConfiguration`、`@EnableAutoConfiguratio`、`@ComponentScan`
- 2、SpringBoot默认只能扫描自己主程序所在的包及其下面的子包，扫描不到 `spring-boot-autoconfigure`包中官方写好的**配置类**
- 3、`**@EnableAutoConfiguration**`：SpringBoot **开启自动配置的核心**。

- - \1. 是由`@Import(AutoConfigurationImportSelector.class)`提供功能：批量给容器中导入组件。
  - \2. SpringBoot启动会默认加载 142个配置类。
  - \3. 这**142个配置类**来自于`spring-boot-autoconfigure`下 `META-INF/spring/**org.springframework.boot.autoconfigure.AutoConfiguration**.imports`文件指定的
  - 项目启动的时候利用 @Import 批量导入组件机制把 `autoconfigure` 包下的142 `xxxxAutoConfiguration`类导入进来（**自动配置类**）
  - 虽然导入了`142`个自动配置类

- 4、按需生效：

- - 并不是这`142`个自动配置类都能生效
  - 每一个自动配置类，都有条件注解`@ConditionalOnxxx`，只有条件成立，才能生效 

**3、**`**xxxxAutoConfiguration**`**自动配置类**

- **1、给容器中使用@Bean 放一堆组件。**
- 2、每个**自动配置类**都可能有这个注解`@EnableConfigurationProperties(**ServerProperties**.class)`，用来把配置文件中配的指定前缀的属性值封装到 `xxxProperties`**属性类**中
- 3、以Tomcat为例：把服务器的所有配置都是以`server`开头的。配置都封装到了属性类中。
- 4、给**容器**中放的所有**组件**的一些**核心参数**，都来自于`**xxxProperties**`**。**`**xxxProperties**`**都是和配置文件绑定。**
- **只需要改配置文件的值，核心组件的底层参数都能修改**

**4、**写业务，全程无需关心各种整合（底层这些整合写好了，而且也生效了）



**核心流程总结：**

1、导入`starter`，就会导入`autoconfigure`包。

2、`autoconfigure` 包里面 有一个文件 `META-INF/spring/**org.springframework.boot.autoconfigure.AutoConfiguration**.imports`,里面指定的所有启动要加载的自动配置类

3、@EnableAutoConfiguration 会自动的把上面文件里面写的所有**自动配置类都导入进来。xxxAutoConfiguration 是有条件注解进行按需加载**

4、`xxxAutoConfiguration`给容器中导入一堆组件，组件都是从 `xxxProperties`中提取属性值

5、`xxxProperties`又是和**配置文件**进行了绑定



**效果：**导入`starter`、修改配置文件，就能修改底层行为。

##### 3. 如何学好SpringBoot

框架的框架、底层基于Spring。能调整每一个场景的底层行为。100%项目一定会用到**底层自定义**

摄影：

- 傻瓜：自动配置好。
- **单反**：焦距、光圈、快门、感光度....
- 傻瓜+**单反**：

1. 理解**自动配置原理**

1. 1. **导入starter** **--> 生效xxxxAutoConfiguration -->** **组件** **--> xxxProperties -->** **配置文件**

1. 理解**其他框架底层**

1. 1. 拦截器

1. 可以随时**定制化任何组件**

1. 1. **配置文件**
   2. **自定义组件**



普通开发：`导入starter`，Controller、Service、Mapper、偶尔修改配置文件

**高级开发**：自定义组件、自定义配置、自定义starter

核心：

- 这个场景自动配置导入了哪些组件，我们能不能Autowired进来使用
- 能不能通过修改配置改变组件的一些默认参数
- 需不需要自己完全定义这个组件
- 场景定制化



**最佳实战**：

- **选场景**，导入到项目

- - 官方：starter
  - 第三方：去仓库搜

- **写配置，改配置文件关键项**

- - 数据库参数（连接地址、账号密码...）

- 分析这个场景给我们导入了**哪些能用的组件**

- - **自动装配**这些组件进行后续使用
  - 不满意boot提供的自动配好的默认组件

- - - **定制化**

- - - - 改配置
      - 自定义组件

整合redis：

- [选场景](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.build-systems.starters)：`spring-boot-starter-data-redis `

- - 场景AutoConfiguration 就是这个场景的自动配置类

- 写配置：

- - 分析到这个场景的自动配置类开启了哪些属性绑定关系
  - `@EnableConfigurationProperties(RedisProperties.class)`
  - 修改redis相关的配置

- 分析组件：

- - 分析到 `RedisAutoConfiguration`  给容器中放了 `StringRedisTemplate`
  - 给业务代码中自动装配 `StringRedisTemplate`

- 定制化

- - 修改配置文件
  - 自定义组件，自己给容器中放一个 `StringRedisTemplate`



### 4、核心技能

#### 1. 常用注解

> SpringBoot摒弃XML配置方式，改为**全注解驱动**

##### 1. 组件注册

**@Configuration**、**@SpringBootConfiguration**

**@Bean**、**@Scope**

**@Controller、 @Service、@Repository、@Component**

**@Import**

@ComponentScan



步骤：

**1、@Configuration 编写一个配置类**

**2、在配置类中，自定义方法给容器中注册组件。配合@Bean**

**3、或使用@Import 导入第三方的组件**



##### 2. 条件注解

> 如果注解指定的**条件成立**，则触发指定行为

***@ConditionalOnXxx***

**@ConditionalOnClass：如果类路径中存在这个类，则触发指定行为**

**@ConditionalOnMissingClass：如果类路径中不存在这个类，则触发指定行为**

**@ConditionalOnBean：如果容器中存在这个Bean（组件），则触发指定行为**

**@ConditionalOnMissingBean：如果容器中不存在这个Bean（组件），则触发指定行为**

> 场景：
>
> - 如果存在`FastsqlException`这个类，给容器中放一个`Cat`组件，名cat01，
> - 否则，就给容器中放一个`Dog`组件，名dog01
> - 如果系统中有`dog01`这个组件，就给容器中放一个 User组件，名zhangsan 
> - 否则，就放一个User，名叫lisi

**@ConditionalOnBean（value=组件类型，name=组件名字）：判断容器中是否有这个类型的组件，并且名字是指定的值**

@ConditionalOnRepositoryType (org.springframework.boot.autoconfigure.data)
@ConditionalOnDefaultWebSecurity (org.springframework.boot.autoconfigure.security)
@ConditionalOnSingleCandidate (org.springframework.boot.autoconfigure.condition)
@ConditionalOnWebApplication (org.springframework.boot.autoconfigure.condition)
@ConditionalOnWarDeployment (org.springframework.boot.autoconfigure.condition)
@ConditionalOnJndi (org.springframework.boot.autoconfigure.condition)
@ConditionalOnResource (org.springframework.boot.autoconfigure.condition)
@ConditionalOnExpression (org.springframework.boot.autoconfigure.condition)
**@ConditionalOnClass** (org.springframework.boot.autoconfigure.condition)
@ConditionalOnEnabledResourceChain (org.springframework.boot.autoconfigure.web)
**@ConditionalOnMissingClass** (org.springframework.boot.autoconfigure.condition)
@ConditionalOnNotWebApplication (org.springframework.boot.autoconfigure.condition)
@ConditionalOnProperty (org.springframework.boot.autoconfigure.condition)
@ConditionalOnCloudPlatform (org.springframework.boot.autoconfigure.condition)
**@ConditionalOnBean** (org.springframework.boot.autoconfigure.condition)
**@ConditionalOnMissingBean** (org.springframework.boot.autoconfigure.condition)
@ConditionalOnMissingFilterBean (org.springframework.boot.autoconfigure.web.servlet)
@Profile (org.springframework.context.annotation)
@ConditionalOnInitializedRestarter (org.springframework.boot.devtools.restart)
@ConditionalOnGraphQlSchema (org.springframework.boot.autoconfigure.graphql)
@ConditionalOnJava (org.springframework.boot.autoconfigure.condition)

##### 3. 属性绑定

**@ConfigurationProperties： 声明组件的属性和配置文件哪些前缀开始项进行绑定**

**@EnableConfigurationProperties：快速注册注解：**

- **场景：**SpringBoot默认只扫描自己主程序所在的包。如果导入第三方包，即使组件上标注了 @Component、@ConfigurationProperties 注解，也没用。因为组件都扫描不进来，此时使用这个注解就可以快速进行属性绑定并把组件注册进容器

>将容器中任意**组件（Bean）的属性值**和**配置文件**的配置项的值**进行绑定**
>
>- 1、给容器中注册组件（@Component、@Bean）
>
>- 2、使用@ConfigurationProperties 声明组件和配置文件的哪些配置项进行绑定**

更多注解参照：[Spring注解驱动开发](https://www.bilibili.com/video/BV1gW411W7wy)【1-26集】



#### 2. YAML配置文件

> **痛点**：SpringBoot 集中化管理配置，`application.properties`
>
> **问题**：配置多以后难阅读和修改，**层级结构辨识度不高**

> YAML 是 "YAML Ain't a Markup Language"（YAML 不是一种标记语言）。在开发的这种语言时，YAML 的意思其实是："Yet Another Markup Language"（是另一种标记语言）。
>
> - 设计目标，就是**方便人类读写**
> - **层次分明**，更适合做配置文件
> - 使用`.yaml`或 `.yml`作为文件后缀

##### 1. 基本语法

- **大小写敏感**
- 使用**缩进表示层级关系，k: v，使用空格分割k,v**
- 缩进时不允许使用Tab键，只允许**使用空格**。换行
- 缩进的空格数目不重要，只要**相同层级**的元素**左侧对齐**即可
- **# 表示注释**，从这个字符一直到行尾，都会被解析器忽略

支持的写法：

- **对象**：**键值对**的集合，如：映射（map）/ 哈希（hash） / 字典（dictionary）
- **数组**：一组按次序排列的值，如：序列（sequence） / 列表（list）
- **纯量**：单个的、不可再分的值，如：字符串、数字、bool、日期



##### **2. 示例**

```java
@Component
@ConfigurationProperties(prefix = "person") //和配置文件person前缀的所有配置进行绑定
@Data //自动生成JavaBean属性的getter/setter
//@NoArgsConstructor //自动生成无参构造器
//@AllArgsConstructor //自动生成全参构造器
public class Person {
    private String name;
    private Integer age;
    private Date birthDay;
    private Boolean like;
    private Child child; //嵌套对象
    private List<Dog> dogs; //数组（里面是对象）
    private Map<String,Cat> cats; //表示Map
}

@Data
public class Dog {
    private String name;
    private Integer age;
}

@Data
public class Child {
    private String name;
    private Integer age;
    private Date birthDay;
    private List<String> text; //数组
}

@Data
public class Cat {
    private String name;
    private Integer age;
}
```

properties表示法

```java
person.name=张三
person.age=18
person.birthDay=2010/10/12 12:12:12
person.like=true
person.child.name=李四
person.child.age=12
person.child.birthDay=2018/10/12
person.child.text[0]=abc
person.child.text[1]=def
person.dogs[0].name=小黑
person.dogs[0].age=3
person.dogs[1].name=小白
person.dogs[1].age=2
person.cats.c1.name=小蓝
person.cats.c1.age=3
person.cats.c2.name=小灰
person.cats.c2.age=2
```

yaml表示法

```java
person:
  name: 张三
  age: 18
  birthDay: 2010/10/10 12:12:12
  like: true
  child:
    name: 李四
    age: 20
    birthDay: 2018/10/10
    text: ["abc","def"]
  dogs:
    - name: 小黑
      age: 3
    - name: 小白
      age: 2
  cats:
    c1:
      name: 小蓝
      age: 3
    c2: {name: 小绿,age: 2} #对象也可用{}表示
```

##### 3. 细节

- birthDay 推荐写为 birth-day
- **文本**：

- - **单引号**不会转义【\n 则为普通字符串显示】
  - **双引号**会转义【\n会显示为**换行符**】

- **大文本**

- - `|`开头，大文本写在下层，**保留文本格式**，**换行符正确显示**
  - `>`开头，大文本写在下层，折叠换行符

- **多文档合并**

- - 使用`---`可以把多个yaml文档合并在一个文档中，每个文档区依然认为内容独立

##### 4. 小技巧：lombok

> 简化JavaBean 开发。自动生成构造器、getter/setter、自动生成Builder模式等

```java
<dependency>	// 2023.3自带
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <scope>compile</scope>
</dependency>
```

使用`@Data`等注解

#### **3. 日志配置**

> 规范：项目开发不要编写`System.out.println()`，应该用**日志**记录信息
>
> ![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1680232037132-d2fa8085-3847-46f2-ac62-14a6188492aa.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_29%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

> **感兴趣日志框架关系与起源可参考**：<https://www.bilibili.com/video/BV1gW411W76m> 视频 21~27集

##### 1.  简介

1. Spring使用commons-logging作为内部日志，但底层日志实现是开放的。可对接其他日志框架。

1. 1. spring5及以后 commons-logging被spring直接自己写了。

1. 支持 jul，log4j2,logback。SpringBoot 提供了默认的控制台输出配置，也可以配置输出为文件。
2. logback是默认使用的。
3. 虽然**日志框架很多**，但是我们不用担心，使用 SpringBoot 的**默认配置就能工作的很好**

**SpringBoot怎么把日志默认配置好的**

1、每个`starter`场景，都会导入一个核心场景`spring-boot-starter`

2、核心场景引入了日志的所用功能`spring-boot-starter-logging`

3、默认使用了`logback + slf4j` 组合作为默认底层日志

4、`日志是系统一启动就要用`，`xxxAutoConfiguration`是系统启动好了以后放好的组件，后来用的。

5、日志是利用**监听器机制**配置好的。`ApplicationListener`。

6、日志所有的配置都可以通过修改配置文件实现。以`logging`开始的所有配置。

##### 2. 日志格式

```
2023-03-31T13:56:17.511+08:00  INFO 4944 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2023-03-31T13:56:17.511+08:00  INFO 4944 --- [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.7]
```

默认输出格式：

- 时间和日期：毫秒级精度
- 日志级别：ERROR, WARN, INFO, DEBUG, or TRACE.
- 进程 ID
- ---： 消息分割符
- 线程名： 使用[]包含
- Logger 名： 通常是产生日志的**类名**
- 消息： 日志记录的内容

注意： logback 没有FATAL级别，对应的是ERROR

默认值：参照：`spring-boot`包`additional-spring-configuration-metadata.json`文件

默认输出格式值：`%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd'T'HH:mm:ss.SSSXXX}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}`

可修改为：`'%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level [%thread] %logger{15} ===> %msg%n'`

##### 3. 记录日志

```java
Logger logger = LoggerFactory.getLogger(getClass());

或者使用Lombok的@Slf4j注解
```

##### 4. 日志级别

- 由低到高：`ALL,TRACE, DEBUG, INFO, WARN, ERROR,FATAL,OFF`；

- - **只会打印指定级别及以上级别的日志**
  - ALL：打印所有日志
  - TRACE：追踪框架详细流程日志，一般不使用
  - DEBUG：开发调试细节日志
  - INFO：关键、感兴趣信息日志
  - WARN：警告但不是错误的信息日志，比如：版本过时
  - ERROR：业务错误日志，比如出现各种异常
  - FATAL：致命错误日志，比如jvm系统崩溃
  - OFF：关闭所有日志记录

- 不指定级别的所有类，都使用root指定的级别作为默认级别
- SpringBoot日志**默认级别是** **INFO**

1. 在application.properties/yaml中配置logging.level.<logger-name>=<level>指定日志级别
2. level可取值范围：`TRACE, DEBUG, INFO, WARN, ERROR, FATAL, or OFF`，定义在 `LogLevel`类中
3. root 的logger-name叫root，可以配置logging.level.root=warn，代表所有未指定日志级别都使用 root 的 warn 级别

##### 5. 日志分组

比较有用的技巧是：

将相关的logger分组在一起，统一配置。SpringBoot 也支持。比如：Tomcat 相关的日志统一设置

```xml
logging.group.tomcat=org.apache.catalina,org.apache.coyote,org.apache.tomcat
logging.level.tomcat=trace
```



SpringBoot 预定义两个组

| Name | Loggers                                                      |
| ---- | ------------------------------------------------------------ |
| web  | org.springframework.core.codec, org.springframework.http, org.springframework.web, org.springframework.boot.actuate.endpoint.web, org.springframework.boot.web.servlet.ServletContextInitializerBeans |
| sql  | org.springframework.jdbc.core, org.hibernate.SQL, org.jooq.tools.LoggerListener |

##### 6. 文件输出

SpringBoot 默认只把日志写在控制台，如果想额外记录到文件，可以在application.properties中添加logging.file.name or logging.file.path配置项。

| logging.file.name | logging.file.path | 示例     | 效果                             |
| ----------------- | ----------------- | -------- | -------------------------------- |
| 未指定            | 未指定            |          | 仅控制台输出                     |
| **指定**          | 未指定            | my.log   | 写入指定文件。可以加路径         |
| 未指定            | **指定**          | /var/log | 写入指定目录，文件名为spring.log |
| **指定**          | **指定**          |          | 以logging.file.name为准          |

##### 7. 文件归档与滚动切割

> 归档：每天的日志单独存到一个文档中。

> 切割：每个文件10MB，超过大小切割成另外一个文件。

1. 每天的日志应该独立分割出来存档。如果使用logback（SpringBoot 默认整合），可以通过application.properties/yaml文件指定日志滚动规则。
2. 如果是其他日志系统，需要自行配置（添加log4j2.xml或log4j2-spring.xml）
3. 支持的滚动规则设置如下

| 配置项                                               | 描述                                                         |
| ---------------------------------------------------- | ------------------------------------------------------------ |
| logging.logback.rollingpolicy.file-name-pattern      | 日志存档的文件名格式（默认值：${LOG_FILE}.%d{yyyy-MM-dd}.%i.gz） |
| logging.logback.rollingpolicy.clean-history-on-start | 应用启动时是否清除以前存档（默认值：false）                  |
| logging.logback.rollingpolicy.max-file-size          | 存档前，每个日志文件的最大大小（默认值：10MB）               |
| logging.logback.rollingpolicy.total-size-cap         | 日志文件被删除之前，可以容纳的最大大小（默认值：0B）。设置1GB则磁盘存储超过 1GB 日志后就会删除旧日志文件 |
| logging.logback.rollingpolicy.max-history            | 日志文件保存的最大天数(默认值：7).                           |

##### 8. 自定义配置

通常我们配置 application.properties 就够了。当然也可以自定义。比如：

| 日志系统                | 自定义                                                       |
| ----------------------- | ------------------------------------------------------------ |
| Logback                 | logback-spring.xml, logback-spring.groovy, logback.xml, or logback.groovy |
| Log4j2                  | log4j2-spring.xml or log4j2.xml                              |
| JDK (Java Util Logging) | logging.properties                                           |

如果可能，我们建议您在日志配置中使用`-spring` 变量（例如，`logback-spring.xml` 而不是 `logback.xml`）。如果您使用标准配置文件，spring 无法完全控制日志初始化。

最佳实战：自己要写配置，配置文件名加上 `xx-spring.xml`

##### 9. 切换日志组合

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

log4j2支持yaml和json格式的配置文件

| 格式 | 依赖                                                         | 文件名                   |
| ---- | ------------------------------------------------------------ | ------------------------ |
| YAML | com.fasterxml.jackson.core:jackson-databind + com.fasterxml.jackson.dataformat:jackson-dataformat-yaml | log4j2.yaml + log4j2.yml |
| JSON | com.fasterxml.jackson.core:jackson-databind                  | log4j2.json + log4j2.jsn |

##### 10. 最佳实战

1. 导入任何第三方框架，先排除它的日志包，因为Boot底层控制好了日志
2. 修改 `application.properties` 配置文件，就可以调整日志的所有行为。如果不够，可以编写日志框架自己的配置文件放在类路径下就行，比如`logback-spring.xml`，`log4j2-spring.xml`
3. 如需对接**专业日志系统**，也只需要把 logback 记录的**日志**灌倒 **kafka**之类的中间件，这和SpringBoot没关系，都是日志框架自己的配置，**修改配置文件即可**
4. **业务中使用slf4j-api记录日志。不要再 sout 了**

------



## **2、SpringBoot3-Web开发**

### 0. WebMvcAutoConfiguration原理

#### 1. 生效条件

```java
@AutoConfiguration(after = { DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class,
		ValidationAutoConfiguration.class }) //在这些自动配置之后
@ConditionalOnWebApplication(type = Type.SERVLET) //如果是web应用就生效，类型SERVLET、REACTIVE 响应式web
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class })
@ConditionalOnMissingBean(WebMvcConfigurationSupport.class) //容器中没有这个Bean，才生效。默认就是没有
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)//优先级
@ImportRuntimeHints(WebResourcesRuntimeHints.class)
public class WebMvcAutoConfiguration { 
}
```

#### 2. 效果    

1. 放了两个Filter：

1. 1.  `HiddenHttpMethodFilter`；页面表单提交Rest请求（GET、POST、PUT、DELETE）
   2. `FormContentFilter`： 表单内容Filter，GET（数据放URL后面）、POST（数据放请求体）请求可以携带数据，PUT、DELETE 的请求体数据会被忽略

1. 给容器中放了`WebMvcConfigurer`组件；给SpringMVC添加各种定制功能

1. 1. 所有的功能最终会和配置文件进行绑定
   2. WebMvcProperties： `spring.mvc`配置文件
   3. WebProperties： `spring.web`配置文件

```java
	@Configuration(proxyBeanMethods = false)
	@Import(EnableWebMvcConfiguration.class) //额外导入了其他配置
	@EnableConfigurationProperties({ WebMvcProperties.class, WebProperties.class })
	@Order(0)
	public static class WebMvcAutoConfigurationAdapter implements WebMvcConfigurer, ServletContextAware{
        
    }
```

3. WebMvcConfigurer接口

提供了配置SpringMVC底层的所有组件入口

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1681093891854-26205c88-4c20-4b63-a2c3-02574778072f.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_36%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)



#### 4. 静态资源规则源码

```java
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (!this.resourceProperties.isAddMappings()) {
        logger.debug("Default resource handling disabled");
        return;
    }
    //1、
    addResourceHandler(registry, this.mvcProperties.getWebjarsPathPattern(),
            "classpath:/META-INF/resources/webjars/");
    addResourceHandler(registry, this.mvcProperties.getStaticPathPattern(), (registration) -> {
        registration.addResourceLocations(this.resourceProperties.getStaticLocations());
        if (this.servletContext != null) {
            ServletContextResource resource = new ServletContextResource(this.servletContext, SERVLET_LOCATION);
            registration.addResourceLocations(resource);
        }
    });
}
```

1. 规则一：访问： `/webjars/**`路径就去 `classpath:/META-INF/resources/webjars/`下找资源.

1. 1. maven 导入依赖
   2. 

1. 规则二：访问： `/**`路径就去 `静态资源默认的四个位置找资源`

1. 1. `classpath:/META-INF/resources/`

2. 2. `classpath:/resources/`

3. 3. `classpath:/static/`

4. 4. `classpath:/public/`

1. 规则三：**静态资源默认都有缓存规则的设置**

1. 1. 所有缓存的设置，直接通过**配置文件**： `spring.web`
   2. cachePeriod： 缓存周期； 多久不用找服务器要新的。 默认没有，以s为单位
   3. cacheControl： **HTTP缓存**控制；[https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Caching](https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Caching#%E6%A6%82%E8%A7%88)
   4. **useLastModified**：是否使用最后一次修改。配合HTTP Cache规则

> 如果浏览器访问了一个静态资源 `index.js`，如果服务这个资源没有发生变化，下次访问的时候就可以直接让浏览器用自己缓存中的东西，而不用给服务器发请求。

```java
registration.setCachePeriod(getSeconds(this.resourceProperties.getCache().getPeriod()));
registration.setCacheControl(this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl());
registration.setUseLastModified(this.resourceProperties.getCache().isUseLastModified());
```

#### 5. EnableWebMvcConfiguration 源码

```java
//SpringBoot 给容器中放 WebMvcConfigurationSupport 组件。
//我们如果自己放了 WebMvcConfigurationSupport 组件，Boot的WebMvcAutoConfiguration都会失效。
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(WebProperties.class)
public static class EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration implements ResourceLoaderAware 
{

    
}

```

1. `HandlerMapping`： 根据请求路径 ` /a` 找那个handler能处理请求

1. 1. `WelcomePageHandlerMapping`： 

1. 1. 1. 访问 `/**`路径下的所有请求，都在以前四个静态资源路径下找，欢迎页也一样
      2. 找`index.html`：只要静态资源的位置有一个 `index.html`页面，项目启动默认访问

#### 6. 为什么容器中放一个`WebMvcConfigurer`就能配置底层行为

1. WebMvcAutoConfiguration 是一个自动配置类，它里面有一个 `EnableWebMvcConfiguration`
2. `EnableWebMvcConfiguration`继承与 `DelegatingWebMvcConfiguration`，这两个都生效
3. `DelegatingWebMvcConfiguration`利用 DI 把容器中 所有 `WebMvcConfigurer `注入进来
4. 别人调用 ``DelegatingWebMvcConfiguration`` 的方法配置底层规则，而它调用所有 `WebMvcConfigurer`的配置底层方法。

#### 7. WebMvcConfigurationSupport

提供了很多的默认设置。

判断系统中是否有相应的类：如果有，就加入相应的`HttpMessageConverter`

```java
jackson2Present = ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", classLoader) &&				ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator", classLoader);

jackson2XmlPresent = ClassUtils.isPresent("com.fasterxml.jackson.dataformat.xml.XmlMapper", classLoader);

jackson2SmilePresent = ClassUtils.isPresent("com.fasterxml.jackson.dataformat.smile.SmileFactory", classLoader);
```



### 1. Web场景

#### 1. 自动配置

1、整合web场景

```java
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
```

2、引入了 `autoconfigure`功能

3、`@EnableAutoConfiguration`注解使用`@Import(AutoConfigurationImportSelector.class)`批量导入组件

4、加载 `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` 文件中配置的所有组件

5、所有自动配置类如下

```text
org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration
org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration
====以下是响应式web场景和现在的没关系======
org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration
org.springframework.boot.autoconfigure.web.reactive.ReactiveMultipartAutoConfiguration
org.springframework.boot.autoconfigure.web.reactive.ReactiveWebServerFactoryAutoConfiguration
org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration
org.springframework.boot.autoconfigure.web.reactive.WebSessionIdResolverAutoConfiguration
org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration
org.springframework.boot.autoconfigure.web.reactive.function.client.ClientHttpConnectorAutoConfiguration
org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration
================以上没关系=================
org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration
org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration
org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration
org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration
org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
```

6、绑定了配置文件的一堆配置项

- 1、SpringMVC的所有配置 `spring.mvc`
- 2、Web场景通用配置 `spring.web`
- 3、文件上传配置 `spring.servlet.multipart`
- 4、服务器的配置 `server`: 比如：编码方式

#### 2. 默认效果

默认配置：

1. 包含了 ContentNegotiatingViewResolver 和 BeanNameViewResolver 组件，**方便视图解析**
2. **默认的静态资源处理机制**： 静态资源放在 static 文件夹下即可直接访问
3. **自动注册**了 **Converter**,GenericConverter,**Formatter**组件，适配常见**数据类型转换**和**格式化需求**
4. **支持** **HttpMessageConverters**，可以**方便返回**json等**数据类型**
5. **注册** MessageCodesResolver，方便**国际化**及错误消息处理
6. **支持 静态** index.html
7. **自动使用**ConfigurableWebBindingInitializer，实现消息处理、数据绑定、类型转化、数据校验等功能

> **重要：**
>
> - *如果想保持* **boot mvc 的默认配置***，并且自定义更多的 mvc 配置，如：***interceptors***,* **formatters***,* **view controllers** *等。可以使用**@Configuration**注解添加一个* *WebMvcConfigurer* *类型的配置类，并不要标注* *@EnableWebMvc*
> - *如果想保持 boot mvc 的默认配置，但要自定义核心组件实例，比如：**RequestMappingHandlerMapping**,* *RequestMappingHandlerAdapter**, 或**ExceptionHandlerExceptionResolver**，给容器中放一个* *WebMvcRegistrations* *组件即可*
> - *如果想全面接管 Spring MVC，**@Configuration* *标注一个配置类，并加上* *@EnableWebMvc**注解，实现* *WebMvcConfigurer* *接口*

### 2. 静态资源

#### 1. 默认规则

##### 1. 静态资源映射

静态资源映射规则在 WebMvcAutoConfiguration 中进行了定义：

1. /webjars/** 的所有路径 资源都在 classpath:/META-INF/resources/webjars/
2. /** 的所有路径 资源都在 classpath:/META-INF/resources/、classpath:/resources/、classpath:/static/、classpath:/public/
3. 所有静态资源都定义了缓存规则。【浏览器访问过一次，就会缓存一段时间】，但此功能参数无默认值

1. 1. period： 缓存间隔。 默认 0S；
   2. cacheControl：缓存控制。 默认无；
   3. useLastModified：是否使用lastModified头。 默认 false；

##### 2.静态资源缓存

如前面所述

1. 所有静态资源都定义了缓存规则。【浏览器访问过一次，就会缓存一段时间】，但此功能参数无默认值

1. 1. period： 缓存间隔。 默认 0S；
   2. cacheControl：缓存控制。 默认无；
   3. useLastModified：是否使用lastModified头。 默认 false；

##### 3. 欢迎页

欢迎页规则在 WebMvcAutoConfiguration 中进行了定义：

1. 在**静态资源**目录下找 index.html
2. 没有就在 templates下找index模板页

##### 4. Favicon

1. 在静态资源目录下找 favicon.ico



##### 5. 缓存实验

```properties
server.port=9000

#1、spring.web：
# 1.配置国际化的区域信息
# 2.静态资源策略(开启、处理链、缓存)

#开启静态资源映射规则
spring.web.resources.add-mappings=true

#设置缓存
#spring.web.resources.cache.period=3600
##缓存详细合并项控制，覆盖period配置：
## 浏览器第一次请求服务器，服务器告诉浏览器此资源缓存7200秒，7200秒以内的所有此资源访问不用发给服务器请求，7200秒以后发请求给服务器
spring.web.resources.cache.cachecontrol.max-age=7200
#使用资源 last-modified 时间，来对比服务器和浏览器的资源是否相同没有变化。相同返回 304
spring.web.resources.cache.use-last-modified=true
```

#### 2. 自定义静态资源规则

自定义静态资源路径、自定义缓存规则

##### 1. 配置方式

`spring.mvc`： 静态资源访问前缀路径

`spring.web`：

- 静态资源目录
- 静态资源缓存策略

```properties
#1、spring.web：
# 1.配置国际化的区域信息
# 2.静态资源策略(开启、处理链、缓存)

#开启静态资源映射规则
spring.web.resources.add-mappings=true

#设置缓存
spring.web.resources.cache.period=3600
##缓存详细合并项控制，覆盖period配置：
## 浏览器第一次请求服务器，服务器告诉浏览器此资源缓存7200秒，7200秒以内的所有此资源访问不用发给服务器请求，7200秒以后发请求给服务器
spring.web.resources.cache.cachecontrol.max-age=7200
## 共享缓存
spring.web.resources.cache.cachecontrol.cache-public=true
#使用资源 last-modified 时间，来对比服务器和浏览器的资源是否相同没有变化。相同返回 304
spring.web.resources.cache.use-last-modified=true

#自定义静态资源文件夹位置
spring.web.resources.static-locations=classpath:/a/,classpath:/b/,classpath:/static/

#2、 spring.mvc
## 2.1. 自定义webjars路径前缀
spring.mvc.webjars-path-pattern=/wj/**
## 2.2. 静态资源访问路径前缀
spring.mvc.static-path-pattern=/static/**
```



##### 2. 代码方式

> - 容器中只要有一个 WebMvcConfigurer 组件。配置的底层行为都会生效
> - @EnableWebMvc //禁用boot的默认配

```java
@Configuration //这是一个配置类
public class MyConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //保留以前规则
        //自己写新的规则。
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/a/","classpath:/b/")
                .setCacheControl(CacheControl.maxAge(1180, TimeUnit.SECONDS));
    }
}
```

```java
@Configuration //这是一个配置类,给容器中放一个 WebMvcConfigurer 组件，就能自定义底层
public class MyConfig  /*implements WebMvcConfigurer*/ {


    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/**")
                        .addResourceLocations("classpath:/a/", "classpath:/b/")
                        .setCacheControl(CacheControl.maxAge(1180, TimeUnit.SECONDS));
            }
        };
    }

}
```

### 3. 路径匹配

> **Spring5.3** 之后加入了更多的请求路径匹配的实现策略；
>
> 以前只支持 AntPathMatcher 策略, 现在提供了 **PathPatternParser** 策略。并且可以让我们指定到底使用那种策略。

#### 1. Ant风格路径用法

Ant 风格的路径模式语法具有以下规则：

- *：表示**任意数量**的字符。
- ?：表示任意**一个字符**。
- **：表示**任意数量的目录**。
- {}：表示一个命名的模式**占位符**。
- []：表示**字符集合**，例如[a-z]表示小写字母。

例如：

- *.html 匹配任意名称，扩展名为.html的文件。
- /folder1/*/*.java 匹配在folder1目录下的任意两级目录下的.java文件。
- /folder2/**/*.jsp 匹配在folder2目录下任意目录深度的.jsp文件。
- /{type}/{id}.html 匹配任意文件名为{id}.html，在任意命名的{type}目录下的文件。

注意：Ant 风格的路径模式语法中的特殊字符需要转义，如：

- 要匹配文件路径中的星号，则需要转义为\\*。
- 要匹配文件路径中的问号，则需要转义为\\?。







#### 2. 模式切换

> #### AntPathMatcher 与 `PathPatternParser`
>
> - PathPatternParser 在 jmh 基准测试下，有 6~8 倍吞吐量提升，降低 30%~40%空间分配率
> - PathPatternParser 兼容 AntPathMatcher语法，并支持更多类型的路径模式
> - PathPatternParser  "***\***" **多段匹配**的支持**仅允许在模式末尾使用**

```java
    @GetMapping("/a*/b?/{p1:[a-f]+}")
    public String hello(HttpServletRequest request, 
                        @PathVariable("p1") String path) {

        log.info("路径变量p1： {}", path);
        //获取请求路径
        String uri = request.getRequestURI();
        return uri;
    }
```

总结： 

- 使用默认的路径匹配规则，是由 PathPatternParser  提供的
- 如果路径中间需要有 **，替换成ant风格路径

```properties
# 改变路径匹配策略：
# ant_path_matcher 老版策略；
# path_pattern_parser 新版策略；
spring.mvc.pathmatch.matching-strategy=ant_path_matcher
```

### 4. 内容协商

> 一套系统适配多端数据返回

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1681217799861-dde49224-a767-489b-80b7-7d8d503e33cf.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_23%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

#### 1.  多端内容适配

##### 1. 默认规则

1. **SpringBoot 多端内容适配**。

1. 1. **基于****请求头****内容协商**：（默认开启）

1. 1. 1. 客户端向服务端发送请求，携带HTTP标准的**Accept请求头**。

1. 1. 1. 1. **Accept**: `application/json`、`text/xml`、`text/yaml`
         2. 服务端根据客户端**请求头期望的数据类型**进行**动态返回**

1. 1. **基于****请求参数****内容协商：（需要开启）**

1. 1. 1. 发送请求 GET /projects/spring-boot?format=json 

      2. 匹配到 @GetMapping("/projects/spring-boot") 

      3. 根据**参数协商**，优先返回 json 类型数据【**需要开启参数匹配设置**】

      4. ##### 发送请求 GET /projects/spring-boot?format=xml,优先返回 xml 类型数据

##### 2. 效果演示

> 请求同一个接口，可以返回json和xml不同格式数据

1. 引入支持写出xml内容依赖

```xml
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```

2. 标注注解

```java
@JacksonXmlRootElement  // 可以写出为xml文档
@Data
public class Person {
    private Long id;
    private String userName;
    private String email;
    private Integer age;
}
```

3. 开启基于请求参数的内容协商

```properties
# 开启基于请求参数的内容协商功能。 默认参数名：format。 默认此功能不开启
spring.mvc.contentnegotiation.favor-parameter=true
# 指定内容协商时使用的参数名。默认是 format
spring.mvc.contentnegotiation.parameter-name=type
```

4. 效果

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1681220124448-e8611612-97bc-4823-9b00-20dd9d579abf.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_17%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1681220145378-86fabd90-a78c-4f60-9efa-eb2960915832.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_16%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

##### 3. 配置协商规则与支持类型

1. 修改**内容协商方式**

```properties
#使用参数进行内容协商
spring.mvc.contentnegotiation.favor-parameter=true  
#自定义参数名，默认为format
spring.mvc.contentnegotiation.parameter-name=myparam 
```

2. 大多数 MediaType 都是开箱即用的。也可以**自定义内容类型，如：**

```properties
spring.mvc.contentnegotiation.media-types.yaml=text/yaml
```

#### 2. 自定义内容返回

#####   1. 增加yaml返回支持

##### 导入依赖

```xml
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-yaml</artifactId>
</dependency>
```

把对象写出成YAML

```java
    public static void main(String[] args) throws JsonProcessingException {
        Person person = new Person();
        person.setId(1L);
        person.setUserName("张三");
        person.setEmail("aaa@qq.com");
        person.setAge(18);

        YAMLFactory factory = new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        ObjectMapper mapper = new ObjectMapper(factory);

        String s = mapper.writeValueAsString(person);
        System.out.println(s);
    }
```

编写配置

```properties
#新增一种媒体类型
spring.mvc.contentnegotiation.media-types.yaml=text/yaml
```

增加`HttpMessageConverter`组件，专门负责把对象写出为yaml格式

```java
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override //配置一个能把对象转为yaml的messageConverter
            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new MyYamlHttpMessageConverter());
            }
        };
    }
```

##### 2. 思考：如何增加其他

- 配置媒体类型支持: 

- - `spring.mvc.contentnegotiation.media-types.yaml=text/yaml`

- 编写对应的`HttpMessageConverter`，要告诉Boot这个支持的媒体类型

- - 按照3的示例

- 把MessageConverter组件加入到底层

- - 容器中放一个``WebMvcConfigurer`` 组件，并配置底层的`MessageConverter`

##### 3. HttpMessageConverter的示例写法

```java
public class MyYamlHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    private ObjectMapper objectMapper = null; //把对象转成yaml

    public MyYamlHttpMessageConverter(){
        //告诉SpringBoot这个MessageConverter支持哪种媒体类型  //媒体类型
        super(new MediaType("text", "yaml", Charset.forName("UTF-8")));
        YAMLFactory factory = new YAMLFactory()
                .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        this.objectMapper = new ObjectMapper(factory);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        //只要是对象类型，不是基本类型
        return true;
    }

    @Override  //@RequestBody
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override //@ResponseBody 把对象怎么写出去
    protected void writeInternal(Object methodReturnValue, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        //try-with写法，自动关流
        try(OutputStream os = outputMessage.getBody()){
            this.objectMapper.writeValue(os,methodReturnValue);
        }

    }
}
```

#### 3. 内容协商原理-`HttpMessageConverter`

> - `HttpMessageConverter` 怎么工作？合适工作？
> - 定制 `HttpMessageConverter`  来实现多端内容协商
> - 编写`WebMvcConfigurer`提供的`configureMessageConverters`底层，修改底层的`MessageConverter`

##### 1. `@ResponseBody`由`HttpMessageConverter`处理

> 标注了`@ResponseBody`的返回值 将会由支持它的 `HttpMessageConverter`写给浏览器

1. 如果controller方法的返回值标注了 `@ResponseBody `注解

1. 1. 请求进来先来到`DispatcherServlet`的`doDispatch()`进行处理
   2. 找到一个 `HandlerAdapter `适配器。利用适配器执行目标方法
   3. `RequestMappingHandlerAdapter`来执行，调用`invokeHandlerMethod（）`来执行目标方法
   4. 目标方法执行之前，准备好两个东西

1. 1. 1. `HandlerMethodArgumentResolver`：参数解析器，确定目标方法每个参数值
      2. `HandlerMethodReturnValueHandler`：返回值处理器，确定目标方法的返回值改怎么处理

1. 1. `RequestMappingHandlerAdapter` 里面的`invokeAndHandle()`真正执行目标方法
   2. 目标方法执行完成，会返回**返回值对象**
   3. **找到一个合适的返回值处理器** `HandlerMethodReturnValueHandler`
   4. 最终找到 `RequestResponseBodyMethodProcessor`能处理 标注了 `@ResponseBody`注解的方法
   5. `RequestResponseBodyMethodProcessor` 调用`writeWithMessageConverters `,利用`MessageConverter`把返回值写出去

> 上面解释：`@ResponseBody`由`HttpMessageConverter`处理

2. `HttpMessageConverter` 会**先进行内容协商**

1. 1. 遍历所有的`MessageConverter`看谁支持这种**内容类型的数据**
   2. 默认`MessageConverter`有以下

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1681275459547-89d8d651-b52f-4d47-bff9-6db123624424.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_15%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

1. 3. 最终因为要`json`所以`MappingJackson2HttpMessageConverter`支持写出json

2. 4. jackson用`ObjectMapper`把对象写出去

##### 2. `WebMvcAutoConfiguration`提供几种默认`HttpMessageConverters`

- `EnableWebMvcConfiguration`通过 `addDefaultHttpMessageConverters`添加了默认的`MessageConverter`；如下：

- - `ByteArrayHttpMessageConverter`： 支持字节数据读写
  - `StringHttpMessageConverter`： 支持字符串读写
  - `ResourceHttpMessageConverter`：支持资源读写
  - `ResourceRegionHttpMessageConverter`: 支持分区资源写出
  - `AllEncompassingFormHttpMessageConverter`：支持表单xml/json读写
  - `MappingJackson2HttpMessageConverter`： 支持请求响应体Json读写

默认8个：

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1681302411019-0c0425aa-6679-4b2b-a456-b31c151c6e83.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_15%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

> 系统提供默认的MessageConverter 功能有限，仅用于json或者普通返回数据。额外增加新的内容协商功能，必须增加新的`HttpMessageConverter`

### 5. 模板引擎

> - 由于 **SpringBoot** 使用了**嵌入式 Servlet 容器**。所以 **JSP** 默认是**不能使用**的。
> - 如果需要**服务端页面渲染**，优先考虑使用 模板引擎。

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1681354523290-b89d7e0d-b9aa-40f5-8d22-d3d09d02b136.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_28%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

模板引擎页面默认放在 src/main/resources/templates

SpringBoot 包含以下模板引擎的自动配置

●FreeMarker

●Groovy

**●**Thymeleaf

●Mustache

Thymeleaf官网：<https://www.thymeleaf.org/> 

```xml
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
	<title>Good Thymes Virtual Grocery</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" media="all" th:href="@{/css/gtvg.css}" />
</head>
<body>
	<p th:text="#{home.welcome}">Welcome to our grocery store!</p>
</body
</html>
```

#### 1. Thymeleaf整合

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

自动配置原理

1. 开启了 org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration 自动配置
2. 属性绑定在 ThymeleafProperties 中，对应配置文件 spring.thymeleaf 内容
3. 所有的模板页面默认在 `classpath:/templates`文件夹下
4. 默认效果

1. 1. 所有的模板页面在 `classpath:/templates/`下面找
   2. 找后缀名为`.html`的页面

#### 2. 基础语法

##### 1. 核心用法

`**th:xxx**`**：动态渲染指定的 html 标签属性值、或者th指令（遍历、判断等）**

- `th:text`：标签体内文本值渲染

- - `th:utext`：不会转义，显示为html原本的样子。

- `th:属性`：标签指定属性渲染
- `th:attr`：标签任意属性渲染
- `th:if``th:each``...`：其他th指令
- 例如：

```xml
<p th:text="${content}">原内容</p>
<a th:href="${url}">登录</a>
<img src="../../images/gtvglogo.png" 
     th:attr="src=@{/images/gtvglogo.png},title=#{logo},alt=#{logo}" />
```

`**表达式**`**：用来动态取值**

- `**${}**`**：变量取值；使用model共享给页面的值都直接用${}**
- `**@{}**`**：url路径；**
- `#{}`：国际化消息
- `~{}`：片段引用
- `*{}`：变量选择：需要配合th:object绑定对象

**系统工具&内置对象：**[**详细文档**](https://www.thymeleaf.org/doc/tutorials/3.1/usingthymeleaf.html#appendix-a-expression-basic-objects)

- `param`：请求参数对象
- `session`：session对象
- `application`：application对象
- `#execInfo`：模板执行信息
- `#messages`：国际化消息
- `#uris`：uri/url工具
- `#conversions`：类型转换工具
- `#dates`：日期工具，是`java.util.Date`对象的工具类
- `#calendars`：类似#dates，只不过是`java.util.Calendar`对象的工具类
- `#temporals`： JDK8+ `**java.time**` API 工具类
- `#numbers`：数字操作工具
- `#strings`：字符串操作
- `#objects`：对象操作
- `#bools`：bool操作
- `#arrays`：array工具
- `#lists`：list工具
- `#sets`：set工具
- `#maps`：map工具
- `#aggregates`：集合聚合工具（sum、avg）
- `#ids`：id生成工具

##### 2. 语法示例

**表达式：**

- 变量取值：${...}
- url 取值：@{...}
- 国际化消息：#{...}
- 变量选择：*{...}
- 片段引用: ~{...}

**常见：**

- 文本： 'one text'，'another one!',...
- 数字： 0,34,3.0,12.3,...
- 布尔：true、false
- null: null
- 变量名： one,sometext,main...

**文本操作：**

- 拼串： +
- 文本替换：| The name is ${name} |

**布尔操作：**

- 二进制运算： and,or
- 取反：!,not

**比较运算：**

- 比较：>，<，<=，>=（gt，lt，ge,le）
- 等值运算：==,!=（eq，ne）

**条件运算：**

- if-then： (if)?(then)
- if-then-else: (if)?(then):(else)
- default: (value)?:(defaultValue)

**特殊语法：**

- 无操作：_

**所有以上都可以嵌套组合**

```xml
'User is of type ' + (${user.isAdmin()} ? 'Administrator' : (${user.type} ?: 'Unknown'))
```

##### 3. 属性设置

1. th:href="@{/product/list}"
2. th:attr="class=${active}"
3. th:attr="src=@{/images/gtvglogo.png},title=${logo},alt=#{logo}"
4. th:checked="${user.active}"

```xml
<p th:text="${content}">原内容</p>
<a th:href="${url}">登录</a>
<img src="../../images/gtvglogo.png" 
     th:attr="src=@{/images/gtvglogo.png},title=#{logo},alt=#{logo}" />
```

##### 4. 遍历

> 语法：  `th:each="元素名,迭代状态 : ${集合}"`

```xml
<tr th:each="prod : ${prods}">
  <td th:text="${prod.name}">Onions</td>
  <td th:text="${prod.price}">2.41</td>
  <td th:text="${prod.inStock}? #{true} : #{false}">yes</td>
</tr>

<tr th:each="prod,iterStat : ${prods}" th:class="${iterStat.odd}? 'odd'">
  <td th:text="${prod.name}">Onions</td>
  <td th:text="${prod.price}">2.41</td>
  <td th:text="${prod.inStock}? #{true} : #{false}">yes</td>
</tr>
```

iterStat 有以下属性：

- index：当前遍历元素的索引，从0开始
- count：当前遍历元素的索引，从1开始
- size：需要遍历元素的总数量
- current：当前正在遍历的元素对象
- even/odd：是否偶数/奇数行
- first：是否第一个元素
- last：是否最后一个元素

##### 5. 判断

**th:if**

```xml
<a
  href="comments.html"
  th:href="@{/product/comments(prodId=${prod.id})}"
  th:if="${not #lists.isEmpty(prod.comments)}"
  >view</a
```

th:switch

```xml
<div th:switch="${user.role}">
  <p th:case="'admin'">User is an administrator</p>
  <p th:case="#{roles.manager}">User is a manager</p>
  <p th:case="*">User is some other thing</p>
</div>
```

##### 6. 属性优先级

- 片段
- 遍历
- 判断

```xml
<ul>
  <li th:each="item : ${items}" th:text="${item.description}">Item description here...</li>
</ul>
```

| Order | Feature          | Attributes                           |
| ----- | ---------------- | ------------------------------------ |
| 1     | 片段包含         | th:insert th:replace                 |
| 2     | 遍历             | th:each                              |
| 3     | 判断             | th:if th:unless th:switch th:case    |
| 4     | 定义本地变量     | th:object th:with                    |
| 5     | 通用方式属性修改 | th:attr th:attrprepend th:attrappend |
| 6     | 指定属性修改     | th:value th:href th:src ...          |
| 7     | 文本值           | th:text th:utext                     |
| 8     | 片段指定         | th:fragment                          |
| 9     | 片段移除         | th:remove                            |

##### **7. 行内写法**

[[...]] or [(...)]

```xml
<p>Hello, [[${session.user.name}]]!</p>
```

##### **8. 变量选择**

```xml
<div th:object="${session.user}">
  <p>Name: <span th:text="*{firstName}">Sebastian</span>.</p>
  <p>Surname: <span th:text="*{lastName}">Pepper</span>.</p>
  <p>Nationality: <span th:text="*{nationality}">Saturn</span>.</p>
</div>
```

##### 9. 模板布局

- 定义模板： `th:fragment`
- 引用模板：`~{templatename::selector}`
- 插入模板：`th:insert`、`th:replace`

```xml
<footer th:fragment="copy">&copy; 2011 The Good Thymes Virtual Grocery</footer>

<body>
  <div th:insert="~{footer :: copy}"></div>
  <div th:replace="~{footer :: copy}"></div>
</body>
<body>
  结果：
  <body>
    <div>
      <footer>&copy; 2011 The Good Thymes Virtual Grocery</footer>
    </div>

    <footer>&copy; 2011 The Good Thymes Virtual Grocery</footer>
  </body>
</body>
```

##### 10. devtools

```xml
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
      </dependency>
```

修改页面后；`ctrl+F9`刷新效果；

java代码的修改，如果`devtools`热启动了，可能会引起一些bug，难以排查

### 6. 国际化

国际化的自动配置参照`MessageSourceAutoConfiguration`



**实现步骤**：

1. Spring Boot 在类路径根下查找messages资源绑定文件。文件名为：messages.properties
2. 多语言可以定义多个消息文件，命名为`messages_区域代码.properties`。如：

1. 1. `messages.properties`：默认
   2. `messages_zh_CN.properties`：中文环境
   3. `messages_en_US.properties`：英语环境

3. 在**程序中**可以自动注入 `MessageSource`组件，获取国际化的配置项值

4. 在**页面中**可以使用表达式 ` #{}`获取国际化的配置项值

```java
    @Autowired  //国际化取消息用的组件
    MessageSource messageSource;
    @GetMapping("/haha")
    public String haha(HttpServletRequest request){

        Locale locale = request.getLocale();
        //利用代码的方式获取国际化配置文件中指定的配置项的值
        String login = messageSource.getMessage("login", null, locale);
        return login;
    }

```

### 7. 错误处理

#### 1. 默认机制

> **错误处理的自动配置**都在`ErrorMvcAutoConfiguration`中，两大核心机制：
>
> - \1. SpringBoot 会**自适应****处理错误**，**响应页面**或**JSON数据**
> - \2. **SpringMVC的错误处理机制**依然保留，**MVC处理不了**，才会**交给boot进行处理**

![未命名绘图.svg](https://cdn.nlark.com/yuque/0/2023/svg/1613913/1681723795095-828d2034-1e6c-4d98-8e47-573dd6b5463b.svg)

- 发生错误以后，转发给/error路径，SpringBoot在底层写好一个 BasicErrorController的组件，专门处理这个请求

```java
	@RequestMapping(produces = MediaType.TEXT_HTML_VALUE) //返回HTML
	public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
		HttpStatus status = getStatus(request);
		Map<String, Object> model = Collections
			.unmodifiableMap(getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.TEXT_HTML)));
		response.setStatus(status.value());
		ModelAndView modelAndView = resolveErrorView(request, response, status, model);
		return (modelAndView != null) ? modelAndView : new ModelAndView("error", model);
	}

	@RequestMapping  //返回 ResponseEntity, JSON
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		HttpStatus status = getStatus(request);
		if (status == HttpStatus.NO_CONTENT) {
			return new ResponseEntity<>(status);
		}
		Map<String, Object> body = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));
		return new ResponseEntity<>(body, status);
	}
```

- 错误页面是这么解析到的

```java
//1、解析错误的自定义视图地址
ModelAndView modelAndView = resolveErrorView(request, response, status, model);
//2、如果解析不到错误页面的地址，默认的错误页就是 error
return (modelAndView != null) ? modelAndView : new ModelAndView("error", model);
```

容器中专门有一个错误视图解析器

```java
@Bean
@ConditionalOnBean(DispatcherServlet.class)
@ConditionalOnMissingBean(ErrorViewResolver.class)
DefaultErrorViewResolver conventionErrorViewResolver() {
    return new DefaultErrorViewResolver(this.applicationContext, this.resources);
}
```

SpringBoot解析自定义错误页的默认规则

```java
	@Override
	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
		ModelAndView modelAndView = resolve(String.valueOf(status.value()), model);
		if (modelAndView == null && SERIES_VIEWS.containsKey(status.series())) {
			modelAndView = resolve(SERIES_VIEWS.get(status.series()), model);
		}
		return modelAndView;
	}

	private ModelAndView resolve(String viewName, Map<String, Object> model) {
		String errorViewName = "error/" + viewName;
		TemplateAvailabilityProvider provider = this.templateAvailabilityProviders.getProvider(errorViewName,
				this.applicationContext);
		if (provider != null) {
			return new ModelAndView(errorViewName, model);
		}
		return resolveResource(errorViewName, model);
	}

	private ModelAndView resolveResource(String viewName, Map<String, Object> model) {
		for (String location : this.resources.getStaticLocations()) {
			try {
				Resource resource = this.applicationContext.getResource(location);
				resource = resource.createRelative(viewName + ".html");
				if (resource.exists()) {
					return new ModelAndView(new HtmlResourceView(resource), model);
				}
			}
			catch (Exception ex) {
			}
		}
		return null;
	}
```

容器中有一个默认的名为error的view; 提供了默认白页功能

```java
@Bean(name = "error")
@ConditionalOnMissingBean(name = "error")
public View defaultErrorView() {
    return this.defaultErrorView;
}
```

封装了JSON格式的错误信息

```java
	@Bean
	@ConditionalOnMissingBean(value = ErrorAttributes.class, search = SearchStrategy.CURRENT)
	public DefaultErrorAttributes errorAttributes() {
		return new DefaultErrorAttributes();
	}
```

规则：

1. **解析一个错误页**

1. 1. 如果发生了500、404、503、403 这些错误

1. 1. 1. 如果有**模板引擎**，默认在 `classpath:/templates/error/**精确码.html**`
      2. 如果没有模板引擎，在静态资源文件夹下找  `**精确码.html**`

1. 2. 如果匹配不到`精确码.html`这些精确的错误页，就去找`5xx.html`，`4xx.html`**模糊匹配**

1. 1. 1. 如果有模板引擎，默认在 `classpath:/templates/error/5xx.html`
      2. 如果没有模板引擎，在静态资源文件夹下找  `5xx.html`

2. 如果模板引擎路径`templates`下有 `error.html`页面，就直接渲染

#### 2. 自定义错误响应

##### 1. 自定义json响应

> 使用@ControllerAdvice + @ExceptionHandler进行统一异常处理
>
> @ExceptionHandler标识一个方法处理错误，默认只能处理这个类发生的指定错误

##### 2. 自定义页面响应

> 根据boot的错误页面规则，自定义页面模板

##### 3. 最佳实战

- **前后分离**

- - 后台发生的所有错误，`@ControllerAdvice + @ExceptionHandler`进行统一异常处理。

- **服务端页面渲染**

- - **不可预知的一些，HTTP码表示的服务器或客户端错误**

- - - 给`classpath:/templates/error/`下面，放常用精确的错误码页面。`500.html`，`404.html`
    - 给`classpath:/templates/error/`下面，放通用模糊匹配的错误码页面。 `5xx.html`，`4xx.html`

- - **发生业务错误**

- - - **核心业务**，每一种错误，都应该代码控制，**跳转到自己定制的错误页**。
    - **通用业务**，`classpath:/templates/error.html`页面，**显示错误信息**。



页面，JSON，可用的Model数据如下

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1681724501227-077073b7-349d-414f-8916-a822eb86c772.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_26%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

### 8. 嵌入式容器

> Servlet容器：管理、运行Servlet组件（Servlet、Filter、Listener）的环境，一般指服务器

#### 1. 自动配置原理

> SpringBoot 默认嵌入Tomcat作为Servlet容器。
> ●自动配置类是ServletWebServerFactoryAutoConfiguration，EmbeddedWebServerFactoryCustomizerAutoConfiguration
> ●自动配置类开始分析功能。`xxxxAutoConfiguration`

```java
@AutoConfiguration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@ConditionalOnClass(ServletRequest.class)
@ConditionalOnWebApplication(type = Type.SERVLET)
@EnableConfigurationProperties(ServerProperties.class)
@Import({ ServletWebServerFactoryAutoConfiguration.BeanPostProcessorsRegistrar.class,
		ServletWebServerFactoryConfiguration.EmbeddedTomcat.class,
		ServletWebServerFactoryConfiguration.EmbeddedJetty.class,
		ServletWebServerFactoryConfiguration.EmbeddedUndertow.class })
public class ServletWebServerFactoryAutoConfiguration {
    
}
```

1. ServletWebServerFactoryAutoConfiguration 自动配置了嵌入式容器场景
2. 绑定了ServerProperties配置类，所有和服务器有关的配置 server
3. ServletWebServerFactoryAutoConfiguration 导入了 嵌入式的三大服务器 Tomcat、Jetty、Undertow
     a. 导入 Tomcat、Jetty、Undertow 都有条件注解。系统中有这个类才行（也就是导了包）
       b. 默认  Tomcat配置生效。给容器中放 TomcatServletWebServerFactory
       c. 都给容器中 ServletWebServerFactory放了一个 web服务器工厂（造web服务器的）
       d. web服务器工厂 都有一个功能，getWebServer获取web服务器
       e. TomcatServletWebServerFactory 创建了 tomcat。
4. ServletWebServerFactory 什么时候会创建 webServer出来。
5. ServletWebServerApplicationContextioc容器，启动的时候会调用创建web服务器
6. Spring容器刷新（启动）的时候，会预留一个时机，刷新子容器。onRefresh()
7. refresh() 容器刷新 十二大步的刷新子容器会调用 onRefresh()；

```java
	@Override
	protected void onRefresh() {
		super.onRefresh();
		try {
			createWebServer();
		}
		catch (Throwable ex) {
			throw new ApplicationContextException("Unable to start web server", ex);
		}
	}
```

> Web场景的Spring容器启动，在onRefresh的时候，会调用创建web服务器的方法。
>
> Web服务器的创建是通过WebServerFactory搞定的。容器中又会根据导了什么包条件注解，启动相关的 服务器配置，默认`EmbeddedTomcat`会给容器中放一个 `TomcatServletWebServerFactory`，导致项目启动，自动创建出Tomcat。

#### 2. 自定义

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1681725850466-2ecf12f4-8b66-469f-9d5d-377a33923b3c.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_19%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

> 切换 其它几种服务器；
>
> ```xml
> <properties>
>     <servlet-api.version>3.1.0</servlet-api.version>
> </properties>
> <dependency>
>     <groupId>org.springframework.boot</groupId>
>     <artifactId>spring-boot-starter-web</artifactId>
>     <exclusions>
>         <!-- Exclude the Tomcat dependency禁用掉tomcat -->
>         <exclusion>
>             <groupId>org.springframework.boot</groupId>
>             <artifactId>spring-boot-starter-tomcat</artifactId>
>         </exclusion>
>     </exclusions>
> </dependency>
> <!-- Use Jetty instead 使用jetty服务器 -->
> <dependency>
>     <groupId>org.springframework.boot</groupId>
>     <artifactId>spring-boot-starter-jetty</artifactId>
> </dependency>
> ```
>
> 

#### 3. 最佳实践

**用法：**

- 修改`server`下的相关配置就可以修改**服务器参数**
- 通过给容器中放一个`**ServletWebServerFactory**`，来禁用掉SpringBoot默认放的服务器工厂，实现自定义嵌入**任意服务器**

### 9. 全面接管SpringMVC

> - SpringBoot 默认配置好了 SpringMVC 的所有常用特性。
> - 如果我们需要全面接管SpringMVC的所有配置并**禁用默认配置**，仅需要编写一个`WebMvcConfigurer`配置类，并标注 `@EnableWebMvc` 即可
> - 全手动模式
>
> - - `@EnableWebMvc` : 禁用默认配置
>   - `**WebMvcConfigurer**`组件：定义MVC的底层行为

#### 1. WebMvcAutoConfiguration 到底自动配置了哪些规则

> SpringMVC自动配置场景给我们配置了如下所有默认行为

1. `WebMvcAutoConfiguration`web场景的自动配置类

1. 1. 支持RESTful的filter：HiddenHttpMethodFilter
   2. 支持非POST请求，请求体携带数据：FormContentFilter
   3. 导入`**EnableWebMvcConfiguration**`：

1. 1. 1. `RequestMappingHandlerAdapter`
      2. `WelcomePageHandlerMapping`： **欢迎页功能**支持（模板引擎目录、静态资源目录放index.html），项目访问/ 就默认展示这个页面.
      3. `RequestMappingHandlerMapping`：找每个请求由谁处理的映射关系
      4. `ExceptionHandlerExceptionResolver`：默认的异常解析器 
      5. `LocaleResolver`：国际化解析器
      6. `ThemeResolver`：主题解析器
      7. `FlashMapManager`：临时数据共享
      8. `FormattingConversionService`： 数据格式化 、类型转化
      9. `Validator`： 数据校验`JSR303`提供的数据校验功能
      10. `WebBindingInitializer`：请求参数的封装与绑定
      11. `ContentNegotiationManager`：内容协商管理器

1. 4. `**WebMvcAutoConfigurationAdapter**`配置生效，它是一个`WebMvcConfigurer`，定义mvc底层组件

1. 1. 1. 定义好 `WebMvcConfigurer` **底层组件默认功能；所有功能详见列表**
      2. 视图解析器：`InternalResourceViewResolver`
      3. 视图解析器：`BeanNameViewResolver`,**视图名（controller方法的返回值字符串）**就是组件名
      4. 内容协商解析器：`ContentNegotiatingViewResolver`
      5. 请求上下文过滤器：`RequestContextFilter`: 任意位置直接获取当前请求
      6. 静态资源链规则
      7. `ProblemDetailsExceptionHandler`：错误详情

1. 1. 1. 1. SpringMVC内部场景异常被它捕获：

1. 5. 定义了MVC默认的底层行为: `WebMvcConfigurer`

#### 2. @EnableWebMvc 禁用默认行为.

1. `@EnableWebMvc`给容器中导入 `DelegatingWebMvcConfiguration`组件，

​        他是 `WebMvcConfigurationSupport`

2. `WebMvcAutoConfiguration`有一个核心的条件注解, `@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)`，容器中没有`WebMvcConfigurationSupport`，`WebMvcAutoConfiguration`才生效.

3. @EnableWebMvc 导入 `WebMvcConfigurationSupport` 导致 `WebMvcAutoConfiguration` 失效。导致禁用了默认行为

> ● @EnableWebMVC 禁用了 Mvc的自动配置
> ● WebMvcConfigurer 定义SpringMVC底层组件的功能类

2. WebMvcConfigurer 功能

> 定义扩展SpringMVC底层功能

 

| FormatterRegistry                  | addFormatters                         | 格式化器：支持属性上@NumberFormat和@DatetimeFormat的数据类型转换 | GenericConversionService                                     |
| ---------------------------------- | ------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 无                                 | getValidator                          | 数据校验：校验 Controller 上使用@Valid标注的参数合法性。需要导入starter-validator | 无                                                           |
| InterceptorRegistry                | addInterceptors                       | 拦截器：拦截收到的所有请求                                   | 无                                                           |
| ContentNegotiationConfigurer       | configureContentNegotiation           | 内容协商：支持多种数据格式返回。需要配合支持这种类型的HttpMessageConverter | 支持 json                                                    |
| configureMessageConverters         | List<HttpMessageConverter<?>>         | 消息转换器：标注@ResponseBody的返回值会利用MessageConverter直接写出去 | 8 个，支持byte，string,multipart,resource，json              |
| addViewControllers                 | ViewControllerRegistry                | 视图映射：直接将请求路径与物理视图映射。用于无 java 业务逻辑的直接视图页渲染 | 无<br/><mvc:view-controller                                  |
| configureViewResolvers             | ViewResolverRegistry                  | 视图解析器：逻辑视图转为物理视图                             | ViewResolverComposite                                        |
| addResourceHandlers                | ResourceHandlerRegistry               | 静态资源处理：静态资源路径映射、缓存控制                     | ResourceHandlerRegistry                                      |
| configureDefaultServletHandling    | DefaultServletHandlerConfigurer       | 默认 Servlet：可以覆盖 Tomcat 的DefaultServlet。让DispatcherServlet拦截/ | 无                                                           |
| configurePathMatch                 | PathMatchConfigurer                   | 路径匹配：自定义 URL 路径匹配。可以自动为所有路径加上指定前缀，比如 /api | 无                                                           |
| configureAsyncSupport              | AsyncSupportConfigurer                | 异步支持                                                     | TaskExecutionAutoConfiguration                               |
| addCorsMappings                    | CorsRegistry                          | 跨域：                                                       | 无                                                           |
| addArgumentResolvers               | List<HandlerMethodArgumentResolver>   | 参数解析器：                                                 | mvc 默认提供                                                 |
| addReturnValueHandlers             | List<HandlerMethodReturnValueHandler> | 返回值解析器：                                               | mvc 默认提供                                                 |
| configureHandlerExceptionResolvers | List<HandlerExceptionResolver>        | 异常处理器：                                                 | 默认 3 个ExceptionHandlerExceptionResolver
ResponseStatusExceptionResolver
DefaultHandlerExceptionResolver |
| getMessageCodesResolver            | 无                                    | 消息码解析器：国际化使用                                     | 无                                                           |
|                                    |                                       |                                                              |                                                              |
|                                    |                                       |                                                              |                                                              |
|                                    |                                       |                                                              |                                                              |
|                                    |                                       |                                                              |                                                              |

### 10. 最佳实践

> SpringBoot 已经默认配置好了Web开发场景常用功能。我们直接使用即可。

三种方式	

| 方式     | 用法                                                         |                            | 效果                                            |
| -------- | ------------------------------------------------------------ | -------------------------- | ----------------------------------------------- |
| 全自动   | 直接编写控制器逻辑                                           |                            | 全部使用自动配置默认效果                        |
| 手自一体 | @Configuration + <br/> 配置WebMvcConfigurer+
配置 WebMvcRegistrations | 不要标注<br/>@EnableWebMvc | 保留自动配置效果手动设置部分功能
定义MVC底层组件 |
| 全手动   | @Configuration + <br/> 配置WebMvcConfigurer                  | 标注<br/>@EnableWebMvc     | 禁用自动配置效果<br/>全手动设置                 |

总结：

**给容器中写一个配置类**`**@Configuration**`**实现** `**WebMvcConfigurer**`**但是不要标注** `**@EnableWebMvc**`**注解，实现手自一体的效果。**

#### 两种模式

1、`前后分离模式`： `@RestController `响应JSON数据

2、`前后不分离模式`：@Controller + Thymeleaf模板引擎

### 11. Web新特性

#### 1. Problemdetails

> RFC 7807: <https://www.rfc-editor.org/rfc/rfc7807>
>
> **错误信息**返回新格式

原理

```java
@Configuration(proxyBeanMethods = false)
//配置过一个属性 spring.mvc.problemdetails.enabled=true
@ConditionalOnProperty(prefix = "spring.mvc.problemdetails", name = "enabled", havingValue = "true")
static class ProblemDetailsErrorHandlingConfiguration {

    @Bean
    @ConditionalOnMissingBean(ResponseEntityExceptionHandler.class)
    ProblemDetailsExceptionHandler problemDetailsExceptionHandler() {
        return new ProblemDetailsExceptionHandler();
    }
}
```

1. ProblemDetailsExceptionHandler 是一个 @ControllerAdvice集中处理系统异常
2. 处理以下异常。如果系统出现以下异常，会被SpringBoot支持以 RFC 7807规范方式返回错误数据

```java
	@ExceptionHandler({
			HttpRequestMethodNotSupportedException.class, //请求方式不支持
			HttpMediaTypeNotSupportedException.class,
			HttpMediaTypeNotAcceptableException.class,
			MissingPathVariableException.class,
			MissingServletRequestParameterException.class,
			MissingServletRequestPartException.class,
			ServletRequestBindingException.class,
			MethodArgumentNotValidException.class,
			NoHandlerFoundException.class,
			AsyncRequestTimeoutException.class,
			ErrorResponseException.class,
			ConversionNotSupportedException.class,
			TypeMismatchException.class,
			HttpMessageNotReadableException.class,
			HttpMessageNotWritableException.class,
			BindException.class
		})
```

> 效果：

默认响应错误的json。状态码 405

```JSON
{
    "timestamp": "2023-04-18T11:13:05.515+00:00",
    "status": 405,
    "error": "Method Not Allowed",
    "trace": "org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'POST' is not supported\r\n\tat org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping.handleNoMatch(RequestMappingInfoHandlerMapping.java:265)\r\n\tat org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.lookupHandlerMethod(AbstractHandlerMethodMapping.java:441)\r\n\tat org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.getHandlerInternal(AbstractHandlerMethodMapping.java:382)\r\n\tat org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping.getHandlerInternal(RequestMappingInfoHandlerMapping.java:126)\r\n\tat org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping.getHandlerInternal(RequestMappingInfoHandlerMapping.java:68)\r\n\tat org.springframework.web.servlet.handler.AbstractHandlerMapping.getHandler(AbstractHandlerMapping.java:505)\r\n\tat org.springframework.web.servlet.DispatcherServlet.getHandler(DispatcherServlet.java:1275)\r\n\tat org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1057)\r\n\tat org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:974)\r\n\tat org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1011)\r\n\tat org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:914)\r\n\tat jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)\r\n\tat org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885)\r\n\tat jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:205)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)\r\n\tat org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)\r\n\tat org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)\r\n\tat org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)\r\n\tat org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)\r\n\tat org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:166)\r\n\tat org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)\r\n\tat org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:493)\r\n\tat org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:115)\r\n\tat org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)\r\n\tat org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\n\tat org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:341)\r\n\tat org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:390)\r\n\tat org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)\r\n\tat org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:894)\r\n\tat org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1741)\r\n\tat org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)\r\n\tat org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)\r\n\tat org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)\r\n\tat org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n\tat java.base/java.lang.Thread.run(Thread.java:833)\r\n",
    "message": "Method 'POST' is not supported.",
    "path": "/list"
}
```

开启ProblemDetails返回, 使用新的MediaType

###### problemdetails 默认为False

spring.mvc.problemdetails.enabled=true

Content-Type: application/problem+json+ 额外扩展返回

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1681816524680-e75cbe89-f90c-4ac4-8247-ec850308df65.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_29%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

```JSON
{
    "type": "about:blank",
    "title": "Method Not Allowed",
    "status": 405,
    "detail": "Method 'POST' is not supported.",
    "instance": "/list"
}
```

#### 2. 函数式Web

> `SpringMVC 5.2` 以后 允许我们使用**函数式**的方式，**定义Web的请求处理流程**。
>
> 函数式接口
>
> Web请求处理的方式：
>
> 1. `@Controller + @RequestMapping`：**耦合式** （**路由**、**业务**耦合）
> 2. **函数式Web**：分离式（路由、业务分离）

##### 1. 场景

> 场景：User RESTful - CRUD

● GET /user/1  获取1号用户
● GET /users   获取所有用户
● POST /user  请求体携带JSON，新增一个用户
● PUT /user/1 请求体携带JSON，修改1号用户
● DELETE /user/1 删除1号用户

##### 2. 核心类

- **RouterFunction**
- **RequestPredicate**
- **ServerRequest**
- **ServerResponse**

##### 3. 示例

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RequestPredicate;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.accept;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration(proxyBeanMethods = false)
public class MyRoutingConfiguration {

    private static final RequestPredicate ACCEPT_JSON = accept(MediaType.APPLICATION_JSON);

    @Bean
    public RouterFunction<ServerResponse> routerFunction(MyUserHandler userHandler) {
        return route()
                .GET("/{user}", ACCEPT_JSON, userHandler::getUser)
                .GET("/{user}/customers", ACCEPT_JSON, userHandler::getUserCustomers)
                .DELETE("/{user}", ACCEPT_JSON, userHandler::deleteUser)
                .build();
    }
}
```



```java
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Component
public class MyUserHandler {

    public ServerResponse getUser(ServerRequest request) {
        ...
        return ServerResponse.ok().build();
    }

    public ServerResponse getUserCustomers(ServerRequest request) {
        ...
        return ServerResponse.ok().build();
    }

    public ServerResponse deleteUser(ServerRequest request) {
        ...
        return ServerResponse.ok().build();
    }
}
```



------

## **3、SpringBoot3-数据访问**

整合SSM场景

> SpringBoot 整合 Spring、SpringMVC、MyBatis 进行数据访问场景开发

### 1. 创建SSM整合项目

```xml
<!-- https://mvnrepository.com/artifact/org.mybatis.spring.boot/mybatis-spring-boot-starter -->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>3.0.1</version>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

### 2. 配置数据源

```properties
spring.datasource.url=jdbc:mysql://192.168.200.100:3306/demo
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.type=com.zaxxer.hikari.HikariDataSource
```

安装MyBatisX 插件，帮我们生成Mapper接口的xml文件即可

### 3. 配置MyBatis

```properties
#指定mapper映射文件位置
mybatis.mapper-locations=classpath:/mapper/*.xml
#参数项调整
mybatis.configuration.map-underscore-to-camel-case=true
```

### 4. CRUD编写

- 编写Bean
- 编写Mapper
- 使用`mybatisx`插件，快速生成MapperXML
- 测试CRUD



### 5. 自动配置原理

**SSM整合总结：**

> 1. **导入** `mybatis-spring-boot-starter`
> 2. 配置**数据源**信息
> 3. 配置mybatis的`**mapper接口扫描**`与`**xml映射文件扫描**`
> 4. 编写bean，mapper，生成xml，编写sql 进行crud。**事务等操作依然和Spring中用法一样**
> 5. 效果：
>
> 1. 1. 所有sql写在xml中
>    2. 所有`mybatis配置`写在`application.properties`下面

- `jdbc场景的自动配置`： 

- - `mybatis-spring-boot-starter`导入 `spring-boot-starter-jdbc`，jdbc是操作数据库的场景
  - `Jdbc`场景的几个自动配置

- - - org.springframework.boot.autoconfigure.jdbc.**DataSourceAutoConfiguration**

- - - - **数据源的自动配置**
      - 所有和数据源有关的配置都绑定在`DataSourceProperties`
      - 默认使用 `HikariDataSource`

- - - org.springframework.boot.autoconfigure.jdbc.**JdbcTemplateAutoConfiguration**

- - - - 给容器中放了`JdbcTemplate`操作数据库

- - - org.springframework.boot.autoconfigure.jdbc.**JndiDataSourceAutoConfiguration**
    - org.springframework.boot.autoconfigure.jdbc.**XADataSourceAutoConfiguration**

- - - - **基于XA二阶提交协议的分布式事务数据源**

- - - org.springframework.boot.autoconfigure.jdbc.**DataSourceTransactionManagerAutoConfiguration**

- - - - **支持事务**

- - **具有的底层能力：数据源、**`JdbcTemplate`、**事务**





- `MyBatisAutoConfiguration`：配置了MyBatis的整合流程

- - `mybatis-spring-boot-starter`导入 `mybatis-spring-boot-autoconfigure（mybatis的自动配置包）`，
  - 默认加载两个自动配置类：

- - - org.mybatis.spring.boot.autoconfigure.MybatisLanguageDriverAutoConfiguration
    - org.mybatis.spring.boot.autoconfigure.**MybatisAutoConfiguration**

- - - - **必须在数据源配置好之后才配置**
      - 给容器中`SqlSessionFactory`组件。创建和数据库的一次会话
      - 给容器中`SqlSessionTemplate`组件。操作数据库

- - **MyBatis的所有配置绑定在**`MybatisProperties`
  - 每个**Mapper接口**的**代理对象**是怎么创建放到容器中。详见**@MapperScan**原理：

- - - 利用`@Import(MapperScannerRegistrar.class)`批量给容器中注册组件。解析指定的包路径里面的每一个类，为每一个Mapper接口类，创建Bean定义信息，注册到容器中。

> 如何分析哪个场景导入以后，开启了哪些自动配置类。
>
> 找：`classpath:/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`文件中配置的所有值，就是要开启的自动配置类，但是每个类可能有条件注解，基于条件注解判断哪个自动配置类生效了。

### 6. 快速定位生效的配置 

```properties
#开启调试模式，详细打印开启了哪些自动配置
debug=true
# Positive（生效的自动配置）  Negative（不生效的自动配置）
```

### 7. 扩展：整合其他数据源

#### 1. Druid 数据源

> 暂不支持 `SpringBoot3`
>
> - 导入`druid-starter`
> - 写配置
> - 分析自动配置了哪些东西，怎么用

Druid官网：<https://github.com/alibaba/druid>

```properties
#数据源基本配置
spring.datasource.url=jdbc:mysql://192.168.200.100:3306/demo
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource

# 配置StatFilter监控
spring.datasource.druid.filter.stat.enabled=true
spring.datasource.druid.filter.stat.db-type=mysql
spring.datasource.druid.filter.stat.log-slow-sql=true
spring.datasource.druid.filter.stat.slow-sql-millis=2000
# 配置WallFilter防火墙
spring.datasource.druid.filter.wall.enabled=true
spring.datasource.druid.filter.wall.db-type=mysql
spring.datasource.druid.filter.wall.config.delete-allow=false
spring.datasource.druid.filter.wall.config.drop-table-allow=false
# 配置监控页，内置监控页面的首页是 /druid/index.html
spring.datasource.druid.stat-view-servlet.enabled=true
spring.datasource.druid.stat-view-servlet.login-username=admin
spring.datasource.druid.stat-view-servlet.login-password=admin
spring.datasource.druid.stat-view-servlet.allow=*

# 其他 Filter 配置不再演示
# 目前为以下 Filter 提供了配置支持，请参考文档或者根据IDE提示（spring.datasource.druid.filter.*）进行配置。
# StatFilter
# WallFilter
# ConfigFilter
# EncodingConvertFilter
# Slf4jLogFilter
# Log4jFilter
# Log4j2Filter
# CommonsLogFilter

```

#### 附录：示例数据库

```sql
CREATE TABLE `t_user`
(
    `id`         BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '编号',
    `login_name` VARCHAR(200) NULL DEFAULT NULL COMMENT '用户名称' COLLATE 'utf8_general_ci',
    `nick_name`  VARCHAR(200) NULL DEFAULT NULL COMMENT '用户昵称' COLLATE 'utf8_general_ci',
    `passwd`     VARCHAR(200) NULL DEFAULT NULL COMMENT '用户密码' COLLATE 'utf8_general_ci',
    PRIMARY KEY (`id`)
);
insert into t_user(login_name, nick_name, passwd) VALUES ('zhangsan','张三','123456');
```

------

## 4、SpringBoot3-基础特性

### 1. SpringApplication

#### 1.1. 自定义 banner

1. 类路径添加banner.txt或设置spring.banner.location就可以定制 banner
2. 推荐网站：[Spring Boot banner 在线生成工具，制作下载英文 banner.txt，修改替换 banner.txt 文字实现自定义，个性化启动 banner-bootschool.net](https://www.bootschool.net/ascii)

#### 1.2. 自定义 SpringApplication

```java
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MyApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
```

#### 1.3 FluentBuilder API  

```java
new SpringApplicationBuilder()
    .sources(Parent.class)
    .child(Application.class)
    .bannerMode(Banner.Mode.OFF)
    .run(args);
```

### 2. Profiles

> 环境隔离能力；快速切换开发、测试、生产环境
>
> 步骤：
>
> 1. **标识环境**：指定哪些组件、配置在哪个环境生效
> 2. **切换环境**：这个环境对应的所有组件和配置就应该生效

#### 2.1. 使用

##### 2.1.1 指定环境

- Spring Profiles 提供一种**隔离配置**的方式，使其仅在**特定环境**生效；
- 任何@Component, @Configuration 或 @ConfigurationProperties 可以使用 @Profile 标记，来指定何时被加载。【**容器中的组件**都可以被 `@Profile`标记】



##### 2.1.2 环境激活

1. 配置激活指定环境； 配置文件

```properties
spring.profiles.active=production,hsqldb
```

2. 也可以使用命令行激活。--spring.profiles.active=dev,hsqldb

3. 还可以配置**默认环境**； 不标注@Profile 的组件永远都存在。

1. 1. 以前默认环境叫default
   2. `spring.profiles.default=test`

4. 推荐使用激活方式激活指定环境

##### 2.1.3 环境包含

注意：

1. spring.profiles.active 和spring.profiles.default 只能用到 **无 profile 的文件**中，如果在application-dev.yaml中编写就是**无效的**
2. 也可以额外添加生效文件，而不是激活替换。比如：

```properties
spring.profiles.include[0]=common
spring.profiles.include[1]=local
```

最佳实战：

- **生效的环境** = **激活的环境/默认环境**  + **包含的环境**
- 项目里面这么用

- - 基础的配置`mybatis`、`log`、`xxx`：写到**包含环境中**
  - 需要动态切换变化的 `db`、`redis`：写到**激活的环境中**

#### 2.2. Profile 分组

创建prod组，指定包含db和mq配置

```properties
spring.profiles.group.prod[0]=db
spring.profiles.group.prod[1]=mq
```

使用--spring.profiles.active=prod ，就会激活prod，db，mq配置文件

#### 2.3. Profile 配置文件

- `application-{profile}.properties`可以作为**指定环境的配置文件**。
- 激活这个环境，**配置**就会生效。最终生效的所有**配置**是

- - `application.properties`：主配置文件，任意时候都生效
  - `application-{profile}.properties`：指定环境配置文件，激活指定环境生效

profile优先级 > application 

### 3. 外部化配置

> **场景**：线上应用如何**快速修改配置**，并应**用最新配置**？
>
> - SpringBoot 使用  **配置优先级** + **外部配置**  简化配置更新、简化运维。
> - 只需要给`jar`应用所在的文件夹放一个`application.properties`最新配置文件，重启项目就能自动应用最新配置



#### 3.1. 配置优先级

Spring Boot 允许将**配置外部化**，以便可以在不同的环境中使用相同的应用程序代码。

我们可以使用各种**外部配置源**，包括Java Properties文件、YAML文件、环境变量和命令行参数。

@Value可以获取值，也可以用@ConfigurationProperties将所有属性绑定到java object中

**以下是 SpringBoot 属性源加载顺序。****后面的会覆盖前面的值**。由低到高，高优先级配置覆盖低优先级

1. **默认属性**（通过`SpringApplication.setDefaultProperties`指定的）
2. @PropertySource指定加载的配置（需要写在@Configuration类上才可生效）
3. **配置文件（****application.properties/yml****等）**
4. RandomValuePropertySource支持的random.*配置（如：@Value("${random.int}")）
5. OS 环境变量
6. Java 系统属性（System.getProperties()）
7. JNDI 属性（来自java:comp/env）
8. ServletContext 初始化参数
9. ServletConfig 初始化参数
10. SPRING_APPLICATION_JSON属性（内置在环境变量或系统属性中的 JSON）
11. **命令行参数**
12. 测试属性。(@SpringBootTest进行测试时指定的属性)
13. 测试类@TestPropertySource注解
14. Devtools 设置的全局属性。($HOME/.config/spring-boot)

> 结论：配置可以写到很多位置，常见的优先级顺序：
>
> - `命令行`> `配置文件`> `springapplication配置`



**配置文件优先级**如下：(**后面覆盖前面**)

1. **jar 包内**的application.properties/yml
2. **jar 包内**的application-{profile}.properties/yml
3. **jar 包外**的application.properties/yml
4. **jar 包外**的application-{profile}.properties/yml

**建议**：**用一种格式的配置文件**。`**如果****.properties****和****.yml****同时存在,则****.properties****优先**`

> 结论：`包外 > 包内`； 同级情况：`profile配置 > application配置`

**所有参数均可由命令行传入，使用**`**--参数项=参数值**`**，将会被添加到环境变量中，并优先于**`**配置文件**`**。**

**比如**`**java -jar app.jar --name="Spring"**`**,可以使用**`**@Value("${name}")**`**获取**



演示场景：

- 包内： application.properties   `server.port=8000`
- 包内： application-dev.properties    `server.port=9000`
- 包外：  application.properties   `server.port=8001`
- 包外： application-dev.properties    `server.port=9001`

启动端口？：命令行 > `9001` > `8001` > `9000` > `8000`



#### 3.2. 外部配置

SpringBoot 应用启动时会自动寻找application.properties和application.yaml位置，进行加载。顺序如下：（**后面覆盖前面**）

1. 类路径: 内部

1. 1. 类根路径
   2. 类下/config包

1. 当前路径（项目所在的位置）

1. 1. 当前路径
   2. 当前下/config子目录
   3. /config目录的直接子目录





最终效果：优先级由高到低，前面覆盖后面

- 命令行 > 包外config直接子目录 > 包外config目录 > 包外根目录 > 包内目录
- 同级比较： 

- - profile配置 > 默认配置
  - properties配置 > yaml配置

![未命名绘图.svg](https://cdn.nlark.com/yuque/0/2023/svg/1613913/1682073869709-2cba18c8-55bd-4bf1-a9df-ac784e30d89a.svg)

规律：最外层的最优先。

- 命令行 > 所有
- 包外 > 包内
- config目录 > 根目录
- profile > application 

配置不同就都生效（互补），配置相同高优先级覆盖低优先级

#### 3.3. 导入配置

使用spring.config.import可以导入额外配置

```properties
spring.config.import=my.properties
my.property=value
```

无论以上写法的先后顺序，my.properties的值总是优先于直接在文件中编写的my.property。

#### 3.4. 属性占位符

配置文件中可以使用 ${name:default}形式取出之前配置过的值

```properties
app.name=MyApp
app.description=${app.name} is a Spring Boot application written by ${username:Unknown}
```

### 4. 单元测试-JUnit5

#### 4.1. 整合

SpringBoot 提供一系列测试工具集及注解方便我们进行测试。

spring-boot-test提供核心测试能力，spring-boot-test-autoconfigure 提供测试的一些自动配置。

我们只需要导入spring-boot-starter-test 即可整合测试

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

spring-boot-starter-test 默认提供了以下库供我们测试使用

- [JUnit 5](https://junit.org/junit5/)
- [Spring Test](https://docs.spring.io/spring-framework/docs/6.0.4/reference/html/testing.html#integration-testing)
- [AssertJ](https://assertj.github.io/doc/)
- [Hamcrest](https://github.com/hamcrest/JavaHamcrest)
- [Mockito](https://site.mockito.org/)
- [JSONassert](https://github.com/skyscreamer/JSONassert)
- [JsonPath](https://github.com/jayway/JsonPath)



#### 4.2. 测试

##### 4.2.0 组件测试

直接`@Autowired`容器中的组件进行测试

##### 4.2.1 注解

JUnit5的注解与JUnit4的注解有所变化

<https://junit.org/junit5/docs/current/user-guide/#writing-tests-annotations>

- **@Test :**表示方法是测试方法。但是与JUnit4的@Test不同，他的职责非常单一不能声明任何属性，拓展的测试将会由Jupiter提供额外测试
- **@ParameterizedTest :**表示方法是参数化测试，下方会有详细介绍
- **@RepeatedTest :**表示方法可重复执行，下方会有详细介绍
- **@DisplayName :**为测试类或者测试方法设置展示名称
- **@BeforeEach :**表示在每个单元测试之前执行
- **@AfterEach :**表示在每个单元测试之后执行
- **@BeforeAll :**表示在所有单元测试之前执行
- **@AfterAll :**表示在所有单元测试之后执行
- **@Tag :**表示单元测试类别，类似于JUnit4中的@Categories
- **@Disabled :**表示测试类或测试方法不执行，类似于JUnit4中的@Ignore
- **@Timeout :**表示测试方法运行如果超过了指定时间将会返回错误
- **@ExtendWith :**为测试类或测试方法提供扩展类引用

```java
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class StandardTests {

    @BeforeAll
    static void initAll() {
    }

    @BeforeEach
    void init() {
    }

    @DisplayName("😱")
    @Test
    void succeedingTest() {
    }

    @Test
    void failingTest() {
        fail("a failing test");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
    }

    @Test
    void abortedTest() {
        assumeTrue("abc".contains("Z"));
        fail("test should have been aborted");
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }

}
```

##### 4.2.2 断言

| 方法              | 说明                                 |
| ----------------- | ------------------------------------ |
| assertEquals      | 判断两个对象或两个原始类型是否相等   |
| assertNotEquals   | 判断两个对象或两个原始类型是否不相等 |
| assertSame        | 判断两个对象引用是否指向同一个对象   |
| assertNotSame     | 判断两个对象引用是否指向不同的对象   |
| assertTrue        | 判断给定的布尔值是否为 true          |
| assertFalse       | 判断给定的布尔值是否为 false         |
| assertNull        | 判断给定的对象引用是否为 null        |
| assertNotNull     | 判断给定的对象引用是否不为 null      |
| assertArrayEquals | 数组断言                             |
| assertAll         | 组合断言                             |
| assertThrows      | 异常断言                             |
| assertTimeout     | 超时断言                             |
| fail              | 快速失败                             |

##### 4.2.3 嵌套测试

> JUnit 5 可以通过 Java 中的内部类和@Nested 注解实现嵌套测试，从而可以更好的把相关的测试方法组织在一起。在内部类中可以使用@BeforeEach 和@AfterEach 注解，而且嵌套的层次没有限制。

```java
@DisplayName("A stack")
class TestingAStackDemo {

    Stack<Object> stack;

    @Test
    @DisplayName("is instantiated with new Stack()")
    void isInstantiatedWithNew() {
        new Stack<>();
    }

    @Nested
    @DisplayName("when new")
    class WhenNew {

        @BeforeEach
        void createNewStack() {
            stack = new Stack<>();
        }

        @Test
        @DisplayName("is empty")
        void isEmpty() {
            assertTrue(stack.isEmpty());
        }

        @Test
        @DisplayName("throws EmptyStackException when popped")
        void throwsExceptionWhenPopped() {
            assertThrows(EmptyStackException.class, stack::pop);
        }

        @Test
        @DisplayName("throws EmptyStackException when peeked")
        void throwsExceptionWhenPeeked() {
            assertThrows(EmptyStackException.class, stack::peek);
        }

        @Nested
        @DisplayName("after pushing an element")
        class AfterPushing {

            String anElement = "an element";

            @BeforeEach
            void pushAnElement() {
                stack.push(anElement);
            }

            @Test
            @DisplayName("it is no longer empty")
            void isNotEmpty() {
                assertFalse(stack.isEmpty());
            }

            @Test
            @DisplayName("returns the element when popped and is empty")
            void returnElementWhenPopped() {
                assertEquals(anElement, stack.pop());
                assertTrue(stack.isEmpty());
            }

            @Test
            @DisplayName("returns the element when peeked but remains not empty")
            void returnElementWhenPeeked() {
                assertEquals(anElement, stack.peek());
                assertFalse(stack.isEmpty());
            }
        }
    }
}
```

##### 4.2.4 参数化测试

参数化测试是JUnit5很重要的一个新特性，它使得用不同的参数多次运行测试成为了可能，也为我们的单元测试带来许多便利。



利用**@ValueSource**等注解，指定入参，我们将可以使用不同的参数进行多次单元测试，而不需要每新增一个参数就新增一个单元测试，省去了很多冗余代码。



**@ValueSource**: 为参数化测试指定入参来源，支持八大基础类以及String类型,Class类型

**@NullSource**: 表示为参数化测试提供一个null的入参

**@EnumSource**: 表示为参数化测试提供一个枚举入参

**@CsvFileSource**：表示读取指定CSV文件内容作为参数化测试入参

**@MethodSource**：表示读取指定方法的返回值作为参数化测试入参(注意方法返回需要是一个流)

```java
@ParameterizedTest
@ValueSource(strings = {"one", "two", "three"})
@DisplayName("参数化测试1")
public void parameterizedTest1(String string) {
    System.out.println(string);
    Assertions.assertTrue(StringUtils.isNotBlank(string));
}


@ParameterizedTest
@MethodSource("method")    //指定方法名
@DisplayName("方法来源参数")
public void testWithExplicitLocalMethodSource(String name) {
    System.out.println(name);
    Assertions.assertNotNull(name);
}

static Stream<String> method() {
    return Stream.of("apple", "banana");
}
```

------

## 5、SpringBoot3-核心原理

### 1. 事件和监听器 

#### 1. 生命周期监听 

场景：监听应用的生命周期

##### 1. 监听器-SpringApplicationRunListener 

###### 1自定义SpringApplicationRunListener来监听事件；

1.1编写SpringApplicationRunListener 实现类

1.2在 META-INF/spring.factories 中配置 org.springframework.boot.SpringApplicationRunListener=自己的Listener，还可以指定一个有参构造器，接受两个参数(SpringApplication application, String[] args)

1.3springboot 在spring-boot.jar中配置了默认的 Listener，如下

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1681829576654-d5e4b889-6fcf-4e65-91f1-8de8c78e98f1.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_23%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

```java
/**
 * Listener先要从 META-INF/spring.factories 读到
 *
 * 1、引导： 利用 BootstrapContext 引导整个项目启动
 *      starting：              应用开始，SpringApplication的run方法一调用，只要有了 BootstrapContext 就执行
 *      environmentPrepared：   环境准备好（把启动参数等绑定到环境变量中），但是ioc还没有创建；【调一次】
 * 2、启动：
 *      contextPrepared：       ioc容器创建并准备好，但是sources（主配置类）没加载。并关闭引导上下文；组件都没创建  【调一次】
 *      contextLoaded：         ioc容器加载。主配置类加载进去了。但是ioc容器还没刷新（我们的bean没创建）。
 *      =======截止以前，ioc容器里面还没造bean呢=======
 *      started：               ioc容器刷新了（所有bean造好了），但是 runner 没调用。
 *      ready:                  ioc容器刷新了（所有bean造好了），所有 runner 调用完了。
 * 3、运行
 *     以前步骤都正确执行，代表容器running。
 */
```

##### 2. 生命周期全流程

![img](https://cdn.nlark.com/yuque/0/2023/png/1613913/1682322663331-25a89875-7ce3-40ae-9be7-9ea752fbab20.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_31%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

#### 2. 事件触发时机

##### 1. 各种回调监听器

- `BootstrapRegistryInitializer`：    **感知特定阶段：**感知**引导初始化**

- - `META-INF/spring.factories`
  - 创建引导上下文`bootstrapContext`的时候触发。
  - application.`addBootstrapRegistryInitializer`();
  - 场景：`进行密钥校对授权。`

- ApplicationContextInitializer：   **感知特定阶段：** 感知ioc容器初始化

- - `META-INF/spring.factories`
  - application.addInitializers();

- **ApplicationListener：    感知全阶段：基于事件机制，感知事件。 一旦到了哪个阶段可以做别的事**

- - `@Bean`或`@EventListener`： `事件驱动`
  - `SpringApplication.addListeners(…)`或 `SpringApplicationBuilder.listeners(…)`
  - `META-INF/spring.factories`

- **SpringApplicationRunListener：       感知全阶段生命周期 + 各种阶段都能自定义操作； 功能更完善。**

- - `META-INF/spring.factories`

- **ApplicationRunner:          感知特定阶段：感知应用就绪Ready。卡死应用，就不会就绪**

- - `@Bean`

- **CommandLineRunner：   感知特定阶段：感知应用就绪Ready。卡死应用，就不会就绪**

- - `@Bean`





最佳实战：

- 如果项目启动前做事： `BootstrapRegistryInitializer` 和 `ApplicationContextInitializer`
- 如果想要在项目启动完成后做事：`**ApplicationRunner**`**和** `**CommandLineRunner**`
- **如果要干涉生命周期做事：**`**SpringApplicationRunListener**`
- **如果想要用事件机制：**`**ApplicationListener**`





##### 2. 完整触发流程

`**9大事件**`触发顺序&时机

1. `ApplicationStartingEvent`：应用启动但未做任何事情, 除过注册listeners and initializers.
2. `ApplicationEnvironmentPreparedEvent`：  Environment 准备好，但context 未创建.
3. `ApplicationContextInitializedEvent`: ApplicationContext 准备好，ApplicationContextInitializers 调用，但是任何bean未加载
4. `ApplicationPreparedEvent`： 容器刷新之前，bean定义信息加载
5. `ApplicationStartedEvent`： 容器刷新完成， runner未调用

=========以下就开始插入了**探针机制**============

1. `AvailabilityChangeEvent`： `LivenessState.CORRECT`应用存活； **存活探针**
2. `ApplicationReadyEvent`: 任何runner被调用
3. `AvailabilityChangeEvent`：`ReadinessState.ACCEPTING_TRAFFIC`**就绪探针**，可以接请求
4.  `ApplicationFailedEvent `：启动出错

![img](https://cdn.nlark.com/yuque/0/2023/png/1613913/1682332243584-e7dd3527-b00f-4f65-a44c-19b88e0943fc.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_32%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)



应用事件发送顺序如下：

![img](https://cdn.nlark.com/yuque/0/2023/png/1613913/1681829576515-f8e3e993-f696-4d9d-9cdd-76ba3ba396c3.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_53%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

感知应用是否**存活**了：可能植物状态，虽然活着但是不能处理请求。

应用是否**就绪**了：能响应请求，说明确实活的比较好。



##### 3. SpringBoot 事件驱动开发

**应用启动过程生命周期事件感知（9大事件）**、**应用运行中事件感知（无数种）**。

- **事件发布**：`ApplicationEventPublisherAware`或`注入：ApplicationEventMulticaster`
- **事件监听**：`组件 + @EventListener`

![img](https://cdn.nlark.com/yuque/0/2023/png/1613913/1682327167479-8f634931-f8ca-48fb-9566-c914f1795ff2.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_33%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

![img](https://cdn.nlark.com/yuque/0/2023/png/1613913/1682341921101-aa095a84-00cc-4815-b675-f4ed81cecf3b.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_22%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

> 事件发布者

```java
@Service
public class EventPublisher implements ApplicationEventPublisherAware {

    /**
     * 底层发送事件用的组件，SpringBoot会通过ApplicationEventPublisherAware接口自动注入给我们
     * 事件是广播出去的。所有监听这个事件的监听器都可以收到
     */
    ApplicationEventPublisher applicationEventPublisher;

    /**
     * 所有事件都可以发
     * @param event
     */
    public void sendEvent(ApplicationEvent event) {
        //调用底层API发送事件
        applicationEventPublisher.publishEvent(event);
    }

    /**
     * 会被自动调用，把真正发事件的底层组组件给我们注入进来
     * @param applicationEventPublisher event publisher to be used by this object
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
```

> 事件订阅者

```java
@Service
public class CouponService {

    @Order(1)
    @EventListener
    public void onEvent(LoginSuccessEvent loginSuccessEvent){
        System.out.println("===== CouponService ====感知到事件"+loginSuccessEvent);
        UserEntity source = (UserEntity) loginSuccessEvent.getSource();
        sendCoupon(source.getUsername());
    }

    public void sendCoupon(String username){
        System.out.println(username + " 随机得到了一张优惠券");
    }
}
```

### 2. 自动配置原理

#### 1. 入门理解

应用关注的**三大核心**：**场景**、**配置**、**组件**

##### 1. 自动配置流程

![img](https://cdn.nlark.com/yuque/0/2023/png/1613913/1681829645812-0f0cad01-66d4-42fc-8111-091e33a062c6.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_32%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

1. 导入`starter`
2. 依赖导入`autoconfigure`
3. 寻找类路径下 `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`文件
4. 启动，加载所有 `自动配置类` `xxxAutoConfiguration`

1. 1. 给容器中配置**功能**`组件`
   2. `组件参数`绑定到 `属性类`中。`xxxProperties`
   3. `属性类`和`配置文件`前缀项绑定
   4. `@Contional派生的条件注解`进行判断**是否组件生效**

1. 效果：

1. 1. 修改配置文件，修改底层参数
   2. 所有场景自动配置好直接使用
   3. 可以注入SpringBoot配置好的组件随时使用



##### 2. SPI机制

- **Java中的SPI（Service Provider Interface）是一种软件设计模式，用于****在应用程序中动态地发现和加载组件****。****SPI的思想**是，定义一个接口或抽象类，然后通过在classpath中定义实现该接口的类来实现对组件的动态发现和加载。
- SPI的主要目的是解决在应用程序中使用可插拔组件的问题。例如，一个应用程序可能需要使用不同的日志框架或数据库连接池，但是这些组件的选择可能取决于运行时的条件。通过使用SPI，应用程序可以在运行时发现并加载适当的组件，而无需在代码中硬编码这些组件的实现类。
- 在Java中，**SPI**的实现方式是通过在`META-INF/services`目录下创建一个以服务接口全限定名为名字的文件，文件中包含实现该服务接口的类的全限定名。当应用程序启动时，Java的SPI机制会自动扫描classpath中的这些文件，并根据文件中指定的类名来加载实现类。
- 通过使用SPI，应用程序可以实现更灵活、可扩展的架构，同时也可以避免硬编码依赖关系和增加代码的可维护性。

以上回答来自`ChatGPT-3.5`



在SpringBoot中，`META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`

作业：写一段java的spi机制代码



##### 3. 功能开关

- 自动配置：全部都配置好，什么都不用管。   自动批量导入

- - 项目一启动，spi文件中指定的所有都加载。

- `@EnableXxxx`：手动控制哪些功能的开启； 手动导入。

- - 开启xxx功能
  - 都是利用 @Import 把此功能要用的组件导入进去





#### 2. 进阶理解

##### 1. @SpringBootApplication

@SpringBootConfiguration

就是： @Configuration ，容器中的组件，配置类。spring ioc启动就会加载创建这个类对象

@EnableAutoConfiguration：开启自动配置

开启自动配置

@AutoConfigurationPackage：扫描主程序包：加载自己的组件

- 利用 `@Import(AutoConfigurationPackages.Registrar.class)` 想要给容器中导入组件。
- 把主程序所在的**包**的所有组件导入进来。
- **为什么SpringBoot默认只扫描主程序所在的包及其子包**

@Import(AutoConfigurationImportSelector.class)：加载所有自动配置类：加载starter导入的组件

```java
		List<String> configurations = ImportCandidates.load(AutoConfiguration.class, getBeanClassLoader())
			.getCandidates();
```

> 扫描SPI文件：META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports

@ComponentScan

> 组件扫描：排除一些组件（哪些不要）
>
> 排除前面已经扫描进来的`配置类`、和`自动配置类`。

```java
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
      @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
```

##### 2. 完整启动加载流程

生命周期启动加载流程

![img](https://cdn.nlark.com/yuque/0/2023/svg/1613913/1682569555020-b6cbc750-3171-44c6-810f-1c59e590b792.svg)



### 3. 自定义starter

> 场景：**抽取聊天机器人场景，它可以打招呼**。
>
> 效果：任何项目导入此`starter`都具有打招呼功能，并且**问候语**中的**人名**需要可以在**配置文件**中修改

- 1. 创建`自定义starter`项目，引入`spring-boot-starter`基础依赖
- 2. 编写模块功能，引入模块所有需要的依赖。
- 3. 编写`xxxAutoConfiguration`自动配置类，帮其他项目导入这个模块需要的所有组件
- 4. 编写配置文件`META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`指定启动需要加载的自动配置
- 5. 其他项目引入即可使用





#### 1. 业务代码

> 自定义配置有提示。导入以下依赖重启项目，再写配置文件就有提示

```java
@ConfigurationProperties(prefix = "robot")  //此属性类和配置文件指定前缀绑定
@Component
@Data
public class RobotProperties {

    private String name;
    private String age;
    private String email;
}

```

```xml
<!--        导入配置处理器，配置文件自定义的properties配置都会有提示-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
```

#### 2. 基本抽取

- 创建starter项目，把公共代码需要的所有依赖导入
- 把公共代码复制进来
- 自己写一个 `RobotAutoConfiguration`，给容器中导入这个场景需要的所有组件

- - 为什么这些组件默认不会扫描进去？
  - **starter所在的包和 引入它的项目的主程序所在的包不是父子层级**

- 别人引用这个`starter`，直接导入这个 `RobotAutoConfiguration`,就能把这个场景的组件导入进来
- 功能生效。
- 测试编写配置文件



#### 3. 使用@EnableXxx机制

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(RobotAutoConfiguration.class)
public @interface EnableRobot {

}
```

别人引入`starter`需要使用 `@EnableRobot`开启功能  

#### 4. 完全自动配置

- 依赖SpringBoot的SPI机制
- META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports 文件中编写好我们自动配置类的全类名即可
- 项目启动，自动加载我们的自动配置类

------

## 附录：SpringBoot3改变 & 新特性 快速总结

1、自动配置包位置变化【参照视频：07、11】
META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports

2、jakata api迁移
**●**druid有问题

3、新特性 - 函数式Web、ProblemDetails【参照视频：50、51】


4、GraalVM 与 AOT【参照视频：86~93】

5、响应式编程全套【第三季：预计7~8月份发布】

6、剩下变化都是版本升级，意义不大



# **SpringBoot3-场景整合**



## 环境准备

### 0. 云服务器

● 阿里云、腾讯云、华为云 服务器开通； 按量付费，省钱省心
● 安装以下组件

- [ ]   docke
- [ ]   redis
- [ ]   kafka
- [ ]   prometheus
- [ ]   grafana
  ● https://github.com/kingToolbox/WindTerm/releases/download/2.5.0/WindTerm_2.5.0_Windows_Portable_x86_64.zip  下载windterm



**重要：开通云服务器以后，请一定在安全组设置规则，放行端口**

**重要：开通云服务器以后，请一定在安全组设置规则，放行端口**

**重要：开通云服务器以后，请一定在安全组设置规则，放行端口**



### 1. Docker安装

> 还不会docker的同学，参考【云原生实战（10~25集）】快速入门
>
> <https://www.bilibili.com/video/BV13Q4y1C7hS?p=10>

```shell
sudo yum install -y yum-utils

sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo

sudo yum install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

sudo systemctl enable docker --now

#测试工作
docker ps
#  批量安装所有软件
docker compose  
```

**创建** `**/prod**` **文件夹，准备以下文件**

### 2. prometheus.yml

```yaml
global:
  scrape_interval: 15s
  evaluation_interval: 15s

scrape_configs:
  - job_name: 'prometheus'
    static_configs:
      - targets: ['localhost:9090']

  - job_name: 'redis'
    static_configs:
      - targets: ['redis:6379']

  - job_name: 'kafka'
    static_configs:
      - targets: ['kafka:9092']
```

### 3. docker-compose.yml

```yaml
version: '3.9'

services:
  redis:
    image: redis:latest
    container_name: redis
    restart: always
    ports:
      - "6379:6379"
    networks:
      - backend

  zookeeper:
    image: bitnami/zookeeper:latest
    container_name: zookeeper
    restart: always
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000
    networks:
      - backend

  kafka:
    image: bitnami/kafka:3.4.0
    container_name: kafka
    restart: always
    depends_on:
      - zookeeper
    ports:
      - "9092:9092"
    environment:
      ALLOW_PLAINTEXT_LISTENER: yes
      KAFKA_CFG_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
    networks:
      - backend
  
  kafka-ui:
    image: provectuslabs/kafka-ui:latest
    container_name:  kafka-ui
    restart: always
    depends_on:
      - kafka
    ports:
      - "8080:8080"
    environment:
      KAFKA_CLUSTERS_0_NAME: dev
      KAFKA_CLUSTERS_0_BOOTSTRAPSERVERS: kafka:9092
    networks:
      - backend

  prometheus:
    image: prom/prometheus:latest
    container_name: prometheus
    restart: always
    volumes:
      - ./prometheus.yml:/etc/prometheus/prometheus.yml
    ports:
      - "9090:9090"
    networks:
      - backend

  grafana:
    image: grafana/grafana:latest
    container_name: grafana
    restart: always
    depends_on:
      - prometheus
    ports:
      - "3000:3000"
    networks:
      - backend

networks:
  backend:
    name: backend
```

### 4. 启动环境

```shell
docker compose -f docker-compose.yml up -d
```

### 5. 验证

- Redis：你的ip:6379

- - 填写表单，下载官方可视化工具：
  - <https://redis.com/redis-enterprise/redis-insight/#insight-form>

- Kafka：你的ip:9092

- - idea安装大数据插件

- Prometheus：你的ip:9090

- - 直接浏览器访问

- Grafana：你的ip:3000

- - 直接浏览器访问

------

## 1、NoSQL

0. Redis整合

> Redis不会的同学：参照 阳哥-《Redis7》 <https://www.bilibili.com/video/BV13R4y1v7sP?p=1>
>
> HashMap： key：value

### 1. 场景整合

依赖导入

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

配置

```properties
spring.data.redis.host=192.168.200.100
spring.data.redis.password=Lfy123!@!
```

测试

```java
@Autowired
StringRedisTemplate redisTemplate;

@Test
void redisTest(){
    redisTemplate.opsForValue().set("a","1234");
    Assertions.assertEquals("1234",redisTemplate.opsForValue().get("a"));
}
```

### 2. 自动配置原理

1. META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports中导入了`RedisAutoConfiguration`、RedisReactiveAutoConfiguration和RedisRepositoriesAutoConfiguration。所有属性绑定在`RedisProperties`中
2. RedisReactiveAutoConfiguration属于响应式编程，不用管。RedisRepositoriesAutoConfiguration属于 JPA 操作，也不用管
3. RedisAutoConfiguration 配置了以下组件

1. 1. LettuceConnectionConfiguration： 给容器中注入了连接工厂LettuceConnectionFactory，和操作 redis 的客户端DefaultClientResources。
   2. `RedisTemplate<Object, Object>`： 可给 redis 中存储任意对象，会使用 jdk 默认序列化方式。
   3. `StringRedisTemplate`： 给 redis 中存储字符串，如果要存对象，需要开发人员自己进行序列化。key-value都是字符串进行操作··

### 3. 定制化

#### 1. 序列化机制

```java
@Configuration
public class AppRedisConfiguration {


    /**
     * 允许Object类型的key-value，都可以被转为json进行存储。
     * @param redisConnectionFactory 自动配置好了连接工厂
     * @return
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        //把对象转为json字符串的序列化工具
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
```

#### 2. redis客户端

> RedisTemplate、StringRedisTemplate： 操作redis的工具类
>
> - 要从redis的连接工厂获取链接才能操作redis
> - **Redis客户端**
>
> - - Lettuce： 默认
>   - Jedis：可以使用以下切换

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>io.lettuce</groupId>
                    <artifactId>lettuce-core</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

<!--        切换 jedis 作为操作redis的底层客户端-->
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
        </dependency>
```

#### 3. 配置参考

```properties
spring.data.redis.host=8.130.74.183
spring.data.redis.port=6379
#spring.data.redis.client-type=lettuce

#设置lettuce的底层参数
#spring.data.redis.lettuce.pool.enabled=true
#spring.data.redis.lettuce.pool.max-active=8

spring.data.redis.client-type=jedis
spring.data.redis.jedis.pool.enabled=true
spring.data.redis.jedis.pool.max-active=8
```

------

## **2、接口文档**

### OpenAPI 3 与 Swagger

> Swagger 可以快速生成**实时接口**文档，方便前后开发人员进行协调沟通。遵循 **OpenAPI** 规范。
>
> 文档：<https://springdoc.org/v2/>

### 1.  OpenAPI 3 架构

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1681977849796-4b79ef53-8969-488d-aaa8-73fc333e8be7.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_40%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

### 2. 整合

导入场景

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.1.0</version>
</dependency>
```

配置

```properties
# /api-docs endpoint custom path 默认 /v3/api-docs
springdoc.api-docs.path=/api-docs

# swagger 相关配置在  springdoc.swagger-ui
# swagger-ui custom path
springdoc.swagger-ui.path=/swagger-ui.html

springdoc.show-actuator=true
```

### 3. 使用

#### 1. 常用注解

| 注解         | 标注位置            | 作用                   |
| ------------ | ------------------- | ---------------------- |
| @Tag         | controller 类       | 标识 controller 作用   |
| @Parameter   | 参数                | 标识参数作用           |
| @Parameters  | 参数                | 参数多重说明           |
| @Schema      | model 层的 JavaBean | 描述模型作用及每个属性 |
| @Operation   | 方法                | 描述方法作用           |
| @ApiResponse | 方法                | 描述响应状态码等       |



#### 2. Docket配置

> 如果有多个Docket，配置如下

```java
  @Bean
  public GroupedOpenApi publicApi() {
      return GroupedOpenApi.builder()
              .group("springshop-public")
              .pathsToMatch("/public/**")
              .build();
  }
  @Bean
  public GroupedOpenApi adminApi() {
      return GroupedOpenApi.builder()
              .group("springshop-admin")
              .pathsToMatch("/admin/**")
              .addMethodFilter(method -> method.isAnnotationPresent(Admin.class))
              .build();
  }
```

> 如果只有一个Docket，可以配置如下

```properties
springdoc.packagesToScan=package1, package2
springdoc.pathsToMatch=/v1, /api/balance/**
```

#### 3. OpenAPI配置

```java
  @Bean
  public OpenAPI springShopOpenAPI() {
      return new OpenAPI()
              .info(new Info().title("SpringShop API")
              .description("Spring shop sample application")
              .version("v0.0.1")
              .license(new License().name("Apache 2.0").url("http://springdoc.org")))
              .externalDocs(new ExternalDocumentation()
              .description("SpringShop Wiki Documentation")
              .url("https://springshop.wiki.github.org/docs"));
  }
```

### 4. Springfox 迁移

#### 1 注解变化

| 原注解                                      | 现注解                                                       | 作用           |
| ------------------------------------------- | ------------------------------------------------------------ | -------------- |
| @Api                                        | @Tag                                                         | 描述Controller |
| @ApiIgnore                                  | @Parameter(hidden = true)  @Operation(hidden = true) @Hidden | 描述忽略操作   |
| @ApiImplicitParam                           | @Parameter                                                   | 描述参数       |
| @ApiImplicitParams                          | @Parameters                                                  | 描述参数       |
| @ApiModel                                   | @Schema                                                      | 描述对象       |
| @ApiModelProperty(hidden = true)            | @Schema(accessMode = READ_ONLY)                              | 描述对象属性   |
| @ApiModelProperty                           | @Schema                                                      | 描述对象属性   |
| @ApiOperation(value = "foo", notes = "bar") | @Operation(summary = "foo", description = "bar")             | 描述方法       |
| @ApiParam                                   | @Parameter                                                   | 描述参数       |
| @ApiResponse(code = 404, message = "foo")   | @ApiResponse(responseCode = "404", description = "foo")      | 描述响应       |



#### 2 Docket配置

##### 1. 以前写法

```java
  @Bean
  public Docket publicApi() {
      return new Docket(DocumentationType.SWAGGER_2)
              .select()
              .apis(RequestHandlerSelectors.basePackage("org.github.springshop.web.public"))
              .paths(PathSelectors.regex("/public.*"))
              .build()
              .groupName("springshop-public")
              .apiInfo(apiInfo());
  }

  @Bean
  public Docket adminApi() {
      return new Docket(DocumentationType.SWAGGER_2)
              .select()
              .apis(RequestHandlerSelectors.basePackage("org.github.springshop.web.admin"))
              .paths(PathSelectors.regex("/admin.*"))
              .apis(RequestHandlerSelectors.withMethodAnnotation(Admin.class))
              .build()
              .groupName("springshop-admin")
              .apiInfo(apiInfo());
  }
```

##### 2. 新写法

```java
  @Bean
  public GroupedOpenApi publicApi() {
      return GroupedOpenApi.builder()
              .group("springshop-public")
              .pathsToMatch("/public/**")
              .build();
  }
  @Bean
  public GroupedOpenApi adminApi() {
      return GroupedOpenApi.builder()
              .group("springshop-admin")
              .pathsToMatch("/admin/**")
              .addOpenApiMethodFilter(method -> method.isAnnotationPresent(Admin.class))
              .build();
  }
```

##### 3. 添加OpenAPI组件

```java
  @Bean
  public OpenAPI springShopOpenAPI() {
      return new OpenAPI()
              .info(new Info().title("SpringShop API")
              .description("Spring shop sample application")
              .version("v0.0.1")
              .license(new License().name("Apache 2.0").url("http://springdoc.org")))
              .externalDocs(new ExternalDocumentation()
              .description("SpringShop Wiki Documentation")
              .url("https://springshop.wiki.github.org/docs"));
  }
```

------

## 3、远程调用


RPC（Remote Procedure Call）：远程过程调用

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1683348699620-39b5acb1-9f2a-4a7f-af3a-7829769ca567.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_18%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

**本地过程调用**： a()； b()； a() { b()；}： 不同方法都在**同一个JVM运行**

**远程过程调用**：

- 服务提供者：
- 服务消费者：
- 通过连接对方服务器进行请求\响应交互，来实现调用效果



API/SDK的区别是什么？

- api：接口（Application Programming Interface）

- - 远程提供功能； 

- sdk：工具包（Software Development Kit）

- - 导入jar包，直接调用功能即可

> **开发过程中***，我们经常需要调用别人写的功能*
>
> - *如果是***内部***微服务，可以通过***依赖cloud***、***注册中心、openfeign***等进行调用*
> - *如果是***外部***暴露的，可以***发送 http 请求、或遵循外部协议***进行调用*
>
> *SpringBoot 整合提供了很多方式进行远程调用*
>
> - **轻量级客户端方式**
>
> - - **RestTemplate***： 普通开发*
>   - **WebClient***： 响应式编程开发*
>   - **Http Interface***： 声明式编程*
>
> - **Spring Cloud分布式***解决方案方式*
>
> - - *Spring Cloud OpenFeign*
>
> - **第三方框架**
>
> - - *Dubbo*
>   - *gRPC*
>   - *...*

### 1. WebClient

非阻塞、响应式HTTP客户端

#### 1.1 创建与配置

发请求：

- 请求方式： GET\POST\DELETE\xxxx
- 请求路径： /xxx
- 请求参数：aa=bb&cc=dd&xxx
- 请求头： aa=bb,cc=ddd
- 请求体：

创建 WebClient 非常简单:

- WebClient.create()
- WebClient.create(String baseUrl)

还可以使用 WebClient.builder() 配置更多参数项:

- uriBuilderFactory: 自定义UriBuilderFactory ，定义 baseurl.
- defaultUriVariables: 默认 uri 变量.
- defaultHeader: 每个请求默认头.
- defaultCookie: 每个请求默认 cookie.
- defaultRequest: Consumer 自定义每个请求.
- filter: 过滤 client 发送的每个请求
- exchangeStrategies: HTTP 消息 reader/writer 自定义.
- clientConnector: HTTP client 库设置.

```java
 //获取响应完整信息
WebClient client = WebClient.create("https://example.org");
```

#### 1.2 获取响应

> retrieve()方法用来声明如何提取响应数据。比如

```java
//获取响应完整信息
WebClient client = WebClient.create("https://example.org");

Mono<ResponseEntity<Person>> result = client.get()
        .uri("/persons/{id}", id).accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .toEntity(Person.class);

//只获取body
WebClient client = WebClient.create("https://example.org");

Mono<Person> result = client.get()
        .uri("/persons/{id}", id).accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(Person.class);

//stream数据
Flux<Quote> result = client.get()
        .uri("/quotes").accept(MediaType.TEXT_EVENT_STREAM)
        .retrieve()
        .bodyToFlux(Quote.class);

//定义错误处理
Mono<Person> result = client.get()
        .uri("/persons/{id}", id).accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError, response -> ...)
        .onStatus(HttpStatus::is5xxServerError, response -> ...)
        .bodyToMono(Person.class);
```

#### 1.3 定义请求体

```java
//1、响应式-单个数据
Mono<Person> personMono = ... ;

Mono<Void> result = client.post()
        .uri("/persons/{id}", id)
        .contentType(MediaType.APPLICATION_JSON)
        .body(personMono, Person.class)
        .retrieve()
        .bodyToMono(Void.class);

//2、响应式-多个数据
Flux<Person> personFlux = ... ;

Mono<Void> result = client.post()
        .uri("/persons/{id}", id)
        .contentType(MediaType.APPLICATION_STREAM_JSON)
        .body(personFlux, Person.class)
        .retrieve()
        .bodyToMono(Void.class);

//3、普通对象
Person person = ... ;

Mono<Void> result = client.post()
        .uri("/persons/{id}", id)
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(person)
        .retrieve()
        .bodyToMono(Void.class);
```

### 2. HTTP Interface

> Spring 允许我们通过定义接口的方式，给任意位置发送 http 请求，实现远程调用，可以用来简化 HTTP 远程访问。需要webflux场景才可

#### 2.1 导入依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

#### 2.2 定义接口

```java
public interface BingService {

    @GetExchange(url = "/search")
    String search(@RequestParam("q") String keyword);
}
```

#### 2.3 创建代理&测试

```java
@SpringBootTest
class Boot05TaskApplicationTests {

    @Test
    void contextLoads() throws InterruptedException {
        //1、创建客户端
        WebClient client = WebClient.builder()
                .baseUrl("https://cn.bing.com")
                .codecs(clientCodecConfigurer -> {
                    clientCodecConfigurer
                            .defaultCodecs()
                            .maxInMemorySize(256*1024*1024);
                            //响应数据量太大有可能会超出BufferSize，所以这里设置的大一点
                })
                .build();
        //2、创建工厂
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(client)).build();
        //3、获取代理对象
        BingService bingService = factory.createClient(BingService.class);


        //4、测试调用
        Mono<String> search = bingService.search("尚硅谷");
        System.out.println("==========");
        search.subscribe(str -> System.out.println(str));

        Thread.sleep(100000);

    }

}
```

------

## **4、消息服务**

### 消息队列-场景

#### 1. 异步

![img](https://cdn.nlark.com/yuque/0/2023/png/1613913/1683508559265-41439f88-8a77-421b-8072-64ac18836e14.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_24%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)



#### 2. 解耦

![img](https://cdn.nlark.com/yuque/0/2023/png/1613913/1683509053856-89249929-3fd0-46c9-bef0-05fdf1d4a57a.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_22%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)





#### 3. 削峰

![img](https://cdn.nlark.com/yuque/0/2023/png/1613913/1683509270156-4f382e30-4c48-48f4-be07-502178966813.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_26%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)



#### 4. 缓冲

![img](https://cdn.nlark.com/yuque/0/2023/png/1613913/1683509501803-432efaf3-227a-488c-9751-67bdb8cbeb5e.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_28%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)





### 消息队列-Kafka

#### 1. 消息模式

![img](https://cdn.nlark.com/yuque/0/2023/png/1613913/1682662791504-6cf21127-d9da-4602-a076-ae38c298f0ac.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_34%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)



#### 2. Kafka工作原理

![img](https://cdn.nlark.com/yuque/0/2023/png/1613913/1683170677428-6ffa28b6-d522-435f-9e50-20fe3ddfd024.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_36%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)



#### 3. SpringBoot整合

> 参照：https://docs.spring.io/spring-kafka/docs/current/reference/html/#preface

```xml
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>
```

配置

```properties
spring.kafka.bootstrap-servers=172.20.128.1:9092
```

> 修改C:\Windows\System32\drivers\etc\hosts文件，配置8.130.32.70 kafka

#### 4. 消息发送

```java
@SpringBootTest
class Boot07KafkaApplicationTests {

    @Autowired
    KafkaTemplate kafkaTemplate;
    @Test
    void contextLoads() throws ExecutionException, InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        CompletableFuture[] futures = new CompletableFuture[10000];
        for (int i = 0; i < 10000; i++) {
            CompletableFuture send = kafkaTemplate.send("order", "order.create."+i, "订单创建了："+i);
            futures[i]=send;
        }
        CompletableFuture.allOf(futures).join();
        watch.stop();
        System.out.println("总耗时："+watch.getTotalTimeMillis());
    }

}
```

```java
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public MyBean(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void someMethod() {
        this.kafkaTemplate.send("someTopic", "Hello");
    }

}
```

#### 5. 消息监听

```java
@Component
public class OrderMsgListener {

    @KafkaListener(topics = "order",groupId = "order-service")
    public void listen(ConsumerRecord record){
        System.out.println("收到消息："+record); //可以监听到发给kafka的新消息，以前的拿不到
    }

    @KafkaListener(groupId = "order-service-2",topicPartitions = {
            @TopicPartition(topic = "order",partitionOffsets = {
                    @PartitionOffset(partition = "0",initialOffset = "0")
            })
    })
    public void listenAll(ConsumerRecord record){
        System.out.println("收到partion-0消息："+record);
    }
}
```

#### 6. 参数配置

```properties
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
spring.kafka.consumer.properties[spring.json.value.default.type]=com.example.Invoice
spring.kafka.consumer.properties[spring.json.trusted.packages]=com.example.main,com.example.another
```

生产者

```properties
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer
spring.kafka.producer.properties[spring.json.add.type.headers]=false
```

#### 7. 自动配置原理

kafka 自动配置在KafkaAutoConfiguration

1. 容器中放了 KafkaTemplate 可以进行消息收发
2. 容器中放了KafkaAdmin 可以进行 Kafka 的管理，比如创建 topic 等
3. kafka 的配置在KafkaProperties中
4. @EnableKafka可以开启基于注解的模式

------

## 5、Web安全

●Apache Shiro

●Spring Security

●自研：Filter

### Spring Security

#### 1.安全架构

##### 1. 认证：Authentication

> who are you?
>
> 登录系统，用户系统

##### 2. 授权：Authorization

> what are you allowed to do？
>
> 权限管理，用户授权

##### 3. 攻击防护

> ● XSS（Cross-site scripting）
> ● CSRF（Cross-site request forgery）
> ● CORS（Cross-Origin Resource Sharing）
> ● SQL注入
> ● ...

##### 扩展. 权限模型

###### 1. RBAC(Role Based Access Controll)

> - 用户（t_user）
>
> - - id,username,password，xxx
>   - 1,zhangsan
>   - 2,lisi 
>
> - 用户_角色（t_user_role）【N对N关系需要中间表】
>
> - - zhangsan, admin
>   - zhangsan,common_user
>   - **lisi,** **hr**
>   - **lisi, common_user**
>
> - 角色（t_role）
>
> - - id,role_name
>   - admin
>   - hr
>   - common_user
>
> - 角色_权限(t_role_perm)
>
> - - admin, 文件r
>   - admin, 文件w
>   - admin, 文件执行
>   - admin, 订单query，create,xxx
>   - **hr, 文件r**
>
> - 权限（t_permission）
>
> - - id,perm_id
>   - 文件 r,w,x
>   - 订单 query,create,xxx

###### 2. ACL(Access Controll List)

> 直接用户和权限挂钩
>
> - 用户（t_user）
>
> - - zhangsan
>   - lisi
>
> - 用户_权限(t_user_perm)
>
> - - zhangsan,文件 r
>   - zhangsan,文件 x
>   - zhangsan,订单 query
>
> - 权限（t_permission）
>
> - - id,perm_id
>   - 文件 r,w,x
>   - 订单 query,create,xxx

```java
 @Secured("文件 r")
public void readFile(){
    //读文件
}
```

#### 2. Spring Security 原理

##### 1. 过滤器链架构

> Spring Security利用 FilterChainProxy 封装一系列拦截器链，实现各种安全拦截功能
>
> Servlet三大组件：Servlet、Filter、Listener

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1682513527616-e1e9054a-9049-4005-8c92-86392f3012a2.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_34%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

##### 2. FilterChainProxy

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1682513900851-013516c0-b3d4-4a09-823a-e924a5fa8f2c.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_34%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

##### 3. SecurityFilterChain

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1683548784456-8c66fd8e-783e-4f89-b81f-d3f2771a3ef9.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_20%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

#### 3. 使用

##### 1. HttpSecurity

```java
@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER - 10)
public class ApplicationConfigurerAdapter extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.antMatcher("/match1/**")
      .authorizeRequests()
        .antMatchers("/match1/user").hasRole("USER")
        .antMatchers("/match1/spam").hasRole("SPAM")
        .anyRequest().isAuthenticated();
  }
}
```

##### 2. MethodSecurity

```java
@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SampleSecureApplication {
}

@Service
public class MyService {

  @Secured("ROLE_USER")
  public String secure() {
    return "Hello Security";
  }

}
```



核心

- **WebSecurityConfigurerAdapter**
- @**EnableGlobalMethodSecurity**： 开启全局方法安全配置

- - @Secured
  - @PreAuthorize
  - @PostAuthorize

- **UserDetailService： 去数据库查询用户详细信息的service（用户基本信息、用户角色、用户权限**

#### 4. 实战

##### 1. 引入依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-springsecurity6</artifactId>
    <!-- Temporary explicit version to fix Thymeleaf bug -->
    <version>3.1.1.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
</dependency>
```

##### 2. 页面

###### 首页

```html
<p>Click <a th:href="@{/hello}">here</a> to see a greeting.</p>
```

###### 登录页

```html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">
  <head>
    <title>Spring Security Example</title>
  </head>
  <body>
    <div th:if="${param.error}">Invalid username and password.</div>
    <div th:if="${param.logout}">You have been logged out.</div>
    <form th:action="@{/login}" method="post">
      <div>
        <label> User Name : <input type="text" name="username" /> </label>
      </div>
      <div>
        <label> Password: <input type="password" name="password" /> </label>
      </div>
      <div><input type="submit" value="Sign In" /></div>
    </form>
  </body>
</html>
```

##### 3. 配置类

###### 视图控制

```java
package com.example.securingweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }
}
```

###### Security配置

```java
package com.atguigu.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author lfy
 * @Description
 * @create 2023-03-08 16:54
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("admin")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
```

##### 4. 改造Hello页

```html
<!DOCTYPE html>
<html
  xmlns="http://www.w3.org/1999/xhtml"
  xmlns:th="https://www.thymeleaf.org"
  xmlns:sec="https://www.thymeleaf.org/thymeleaf-extras-springsecurity6"
>
  <head>
    <title>Hello World!</title>
  </head>
  <body>
    <h1 th:inline="text">
      Hello <span th:remove="tag" sec:authentication="name">thymeleaf</span>!
    </h1>
    <form th:action="@{/logout}" method="post">
      <input type="submit" value="Sign Out" />
    </form>
  </body>
</html>
```



------

## 6、可观测性

> 可观测性 Observability

对线上应用进行观测、监控、预警...

- 健康状况【组件状态、存活状态】Health
- 运行**指标**【cpu、内存、垃圾回收、吞吐量、响应成功率...】**Metrics**
- 链路追踪
- ...

### 1. SpringBoot Actuator

#### 1. 实战

##### 1. 场景引入

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

##### 2. 暴露指标

```yaml
management:
  endpoints:
    enabled-by-default: true #暴露所有端点信息
    web:
      exposure:
        include: '*'  #以web方式暴露
```

##### 3. 访问数据

● 访问 http://localhost:8080/actuator；展示出所有可以用的监控端点
● http://localhost:8080/actuator/beans
● http://localhost:8080/actuator/configprops
● http://localhost:8080/actuator/metrics
● http://localhost:8080/actuator/metrics/jvm.gc.pause
● http://localhost:8080/actuator/endpointName/detailPath

#### 2. Endpoint

##### 1. 常用端点



| ID                 | 描述                                                         |
| ------------------ | ------------------------------------------------------------ |
| `auditevents`      | 暴露当前应用程序的审核事件信息。需要一个`AuditEventRepository组件`。 |
| `beans`            | 显示应用程序中所有Spring Bean的完整列表。                    |
| `caches`           | 暴露可用的缓存。                                             |
| `conditions`       | 显示自动配置的所有条件信息，包括匹配或不匹配的原因。         |
| `configprops`      | 显示所有`@ConfigurationProperties`。                         |
| `env`              | 暴露Spring的属性`ConfigurableEnvironment`                    |
| `flyway`           | 显示已应用的所有Flyway数据库迁移。 需要一个或多个`Flyway`组件。 |
| `health`           | 显示应用程序运行状况信息。                                   |
| `httptrace`        | 显示HTTP跟踪信息（默认情况下，最近100个HTTP请求-响应）。需要一个`HttpTraceRepository`组件。 |
| `info`             | 显示应用程序信息。                                           |
| `integrationgraph` | 显示Spring `integrationgraph` 。需要依赖`spring-integration-core`。 |
| `loggers`          | 显示和修改应用程序中日志的配置。                             |
| `liquibase`        | 显示已应用的所有Liquibase数据库迁移。需要一个或多个`Liquibase`组件。 |
| `metrics`          | 显示当前应用程序的“指标”信息。                               |
| `mappings`         | 显示所有`@RequestMapping`路径列表。                          |
| `scheduledtasks`   | 显示应用程序中的计划任务。                                   |
| `sessions`         | 允许从Spring Session支持的会话存储中检索和删除用户会话。需要使用Spring Session的基于Servlet的Web应用程序。 |
| `shutdown`         | 使应用程序正常关闭。默认禁用。                               |
| `startup`          | 显示由`ApplicationStartup`收集的启动步骤数据。需要使用`SpringApplication`进行配置`BufferingApplicationStartup`。 |
| `threaddump`       | 执行线程转储。                                               |
| `heapdump`         | 返回`hprof`堆转储文件。                                      |
| `jolokia`          | 通过HTTP暴露JMX bean（需要引入Jolokia，不适用于WebFlux）。需要引入依赖`jolokia-core`。 |
| `logfile`          | 返回日志文件的内容（如果已设置`logging.file.name`或`logging.file.path`属性）。支持使用HTTP`Range`标头来检索部分日志文件的内容。 |
| `prometheus`       | 以Prometheus服务器可以抓取的格式公开指标。需要依赖`micrometer-registry-prometheus`。 |

```
threaddump`、`heapdump`、`metrics
```

##### 2. 定制端点

- 健康监控：返回存活、死亡
- 指标监控：次数、率

###### 1. HealthEndpoint

```java
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        int errorCode = check(); // perform some specific health check
        if (errorCode != 0) {
            return Health.down().withDetail("Error Code", errorCode).build();
        }
        return Health.up().build();
    }

}

构建Health
Health build = Health.down()
                .withDetail("msg", "error service")
                .withDetail("code", "500")
                .withException(new RuntimeException())
                .build();
```

```yaml
management:
    health:
      enabled: true
      show-details: always #总是显示详细信息。可显示每个模块的状态信息
```

```java
@Component
public class MyComHealthIndicator extends AbstractHealthIndicator {

    /**
     * 真实的检查方法
     * @param builder
     * @throws Exception
     */
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        //mongodb。  获取连接进行测试
        Map<String,Object> map = new HashMap<>();
        // 检查完成
        if(1 == 2){
//            builder.up(); //健康
            builder.status(Status.UP);
            map.put("count",1);
            map.put("ms",100);
        }else {
//            builder.down();
            builder.status(Status.OUT_OF_SERVICE);
            map.put("err","连接超时");
            map.put("ms",3000);
        }


        builder.withDetail("code",100)
                .withDetails(map);

    }
}
```

###### 2. MetricsEndpoint 

```java
class MyService{
    Counter counter;
    public MyService(MeterRegistry meterRegistry){
         counter = meterRegistry.counter("myservice.method.running.counter");
    }

    public void hello() {
        counter.increment();
    }
}

```

### 2. 监控案例落地

> 基于 Prometheus + Grafana
>
> ![img](https://cdn.nlark.com/yuque/0/2023/png/1613913/1683607956768-13b9f5cb-30fc-42a2-9119-ec7570b921f0.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_19%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

#### 1. 安装 Prometheus + Grafana

```shell
#安装prometheus:时序数据库
docker run -p 9090:9090 -d \
-v pc:/etc/prometheus \
prom/prometheus

#安装grafana；默认账号密码 admin:admin
docker run -d --name=grafana -p 3000:3000 grafana/grafana
```

#### 2. 导入依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
    <version>1.10.6</version>
</dependency>
```

```yaml
management:
  endpoints:
    web:
      exposure: #暴露所有监控的端点
        include: '*'
```

访问： http://localhost:8001/actuator/prometheus  验证，返回 prometheus 格式的所有指标

> 部署Java应用

```shell
#安装上传工具
yum install lrzsz

#安装openjdk
# 下载openjdk
wget https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.tar.gz

mkdir -p /opt/java
tar -xzf jdk-17_linux-x64_bin.tar.gz -C /opt/java/
sudo vi /etc/profile
#加入以下内容
export JAVA_HOME=/opt/java/jdk-17.0.7
export PATH=$PATH:$JAVA_HOME/bin

#环境变量生效
source /etc/profile


# 后台启动java应用
nohup java -jar boot3-14-actuator-0.0.1-SNAPSHOT.jar > output.log 2>&1 &

```

确认可以访问到： http://8.130.32.70:9999/actuator/prometheus

#### 3. 配置 Prometheus 拉取数据

```yaml
## 修改 prometheus.yml 配置文件
scrape_configs:
  - job_name: 'spring-boot-actuator-exporter'
    metrics_path: '/actuator/prometheus' #指定抓取的路径
    static_configs:
      - targets: ['192.168.200.1:8001']
        labels:
          nodename: 'app-demo'
```

#### 4. 配置 Grafana 监控面板

- 添加数据源（Prometheus）
- 添加面板。可去 dashboard 市场找一个自己喜欢的面板，也可以自己开发面板;[Dashboards | Grafana Labs](https://grafana.com/grafana/dashboards/?plcmt=footer)

#### 5. 效果

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1682516132127-cfbc53c8-5641-4098-9de8-025e2e71b2cf.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_38%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10%2Fresize%2Cw_750%2Climit_0)



## 7、AOT

### 1. AOT与JIT 

**AOT**：Ahead-of-Time（提前编译）：程序执行前，全部被编译成机器码

**JIT**：Just in Time（即时编译）: 程序边编译，边运行；

**编译**： 

**●**源代码（.c、.cpp、.go、.java。。。） ===编译===  机器码



**语言**：

​	**●**编译型语言：编译器

​	**●**解释型语言：解释器

#### 1. Complier 与 Interpreter 

Java：半编译半解释

<https://anycodes.cn/editor>

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1683766057952-6f218ecf-4d0a-44ee-a930-1fc7f19085db.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_26%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)



| 对比项             | 编译器                                             | 解释器                                                   |
| ------------------ | -------------------------------------------------- | -------------------------------------------------------- |
| 机器执行速度       | 快，因为源代码只需被转换一次                       | 慢，因为每行代码都需要被解释执行                         |
| 开发效率           | 慢，因为需要耗费大量时间编译                       | 快，无需花费时间生成目标代码，更快的开发和测试           |
| 调试               | 难以调试编译器生成的目标代码                       | 容易调试源代码，因为解释器一行一行地执行                 |
| 可移植性（跨平台） | 不同平台需要重新编译目标平台代码                   | 同一份源码可以跨平台执行，因为每个平台会开发对应的解释器 |
| 学习难度           | 相对较高，需要了解源代码、编译器以及目标机器的知识 | 相对较低，无需了解机器的细节                             |
| 错误检查           | 编译器可以在编译代码时检查错误                     | 解释器只能在执行代码时检查错误                           |
| 运行时增强         | 无                                                 | 可以动态增强                                             |

#### 2. AOT 与 JIT 对比 

|      | JIT                                                          | AOT                                                          |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 优点 | 1.具备实时调整能力 2.生成最优机器指令 3.根据代码运行情况优化内存占用 | 1.速度快，优化了运行时编译时间和内存消耗 2.程序初期就能达最高性能 3.加快程序启动速度 |
| 缺点 | 1.运行期边编译速度慢 2.初始编译不能达到最高性能              | 1.程序第一次编译占用时间长 2.牺牲高级语言一些特性            |

在 OpenJDK 的官方 Wiki 上，介绍了HotSpot 虚拟机一个相对比较全面的、**即时编译器（JIT）**中采用的[优化技术列表](https://xie.infoq.cn/link?target=https%3A%2F%2Fwiki.openjdk.java.net%2Fdisplay%2FHotSpot%2FPerformanceTacticIndex)。

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1683773230399-b3fe0f68-f85a-4efb-bf38-d1783ea63d49.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_55%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10%2Fresize%2Cw_750%2Climit_0)

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1683773247546-787b3fcf-ad8a-42d2-9a7b-2980eccff97d.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_55%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10%2Fresize%2Cw_750%2Climit_0)

可使用：-XX:+PrintCompilation 打印JIT编译信息

#### 3. JVM架构 

.java === .class === 机器码

![未命名绘图.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1684124528903-feb1ce8f-302a-4872-a63d-ae5da99501eb.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_23%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)



**JVM:** 既有**解释器**，又有**编辑器（JIT：即时编译）**；

#### 4. Java的执行过程 

> 建议阅读：
> ●美团技术：<https://tech.meituan.com/2020/10/22/java-jit-practice-in-meituan.html>
> ●openjdk官网：<https://wiki.openjdk.org/display/HotSpot/Compiler>

##### 1.流程概要 

![img](https://cdn.nlark.com/yuque/0/2023/png/1613913/1683774822566-f6860477-868e-4115-8ee9-7fe9d787e7a9.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_18%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10%2Fresize%2Cw_648%2Climit_0)



解释执行：

编译执行：

##### 2.详细流程 

热点代码：调用次数非常多的代码

![未命名绘图2.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1684143084003-df41f505-f8d0-4ab9-a684-5c39037e8e30.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_20%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

#### 5. JVM编译器 

**JVM中集成了两种编译器，Client Compiler 和 Server Compiler**；

​	●Client Compiler注重启动速度和局部的优化

​	●Server Compiler更加关注全局优化，性能更好，但由于会进行更多的全局分析，所以启		动速度会慢。



**Client Compiler**：

​	●HotSpot VM带有一个Client Compiler C1编译器

​	●这种编译器启动速度快，但是性能比较Server Compiler来说会差一些。

​	●编译后的机器码执行效率没有C2的高



**Server Compiler**：

​	●Hotspot虚拟机中使用的Server Compiler有两种：C2 和 Graal。

​	●在Hotspot VM中，默认的Server Compiler是C2编译器。

#### 6. 分层编译 

Java 7开始引入了分层编译(**Tiered Compiler)**的概念，它结合了**C1**和**C2**的优势，追求启动速度和峰值性能的一个平衡。分层编译将JVM的执行状态分为了五个层次。**五个层级**分别是：

​	●解释执行。

​	●执行不带profiling的C1代码。

​	●执行仅带方法调用次数以及循环回边执行次数profiling的C1代码。

​	●执行带所有profiling的C1代码。

​	●执行C2代码。

**profiling就是收集能够反映程序执行状态的数据**。其中最基本的统计数据就是方法的调用次数，以及循环回边的执行次数。

![img](https://cdn.nlark.com/yuque/0/2023/png/1613913/1683775747739-e428b122-ace8-4b33-b860-6a6c7ea11abd.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_25%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10%2Fresize%2Cw_750%2Climit_0)



●图中第①条路径，代表编译的一般情况，**热点方法**从解释执行到被3层的C1编译，最后被4层的C2编译。

●如果**方法比较小**（比如Java服务中常见的**getter/setter**方法），3层的profiling没有收集到有价值的数据，JVM就会断定该方法对于C1代码和C2代码的执行效率相同，就会执行图中第②条路径。在这种情况下，JVM会在3层编译之后，放弃进入C2编译，**直接选择用1层的C1编译运行。**

●在**C1忙碌**的情况下，执行图中第③条路径，在解释执行过程中对程序进行**profiling** ，根据信息直接由第4层的**C2编译**。

●前文提到C1中的执行效率是**1层>2层>3层**，**第3层**一般要比**第2层**慢35%以上，所以在**C2忙碌**的情况下，执行图中第④条路径。这时方法会被2层的C1编译，然后再被3层的C1编译，以减少方法在**3层**的执行时间。

●如果**编译器**做了一些比较**激进的优化**，比如分支预测，在实际运行时**发现预测出错**，这时就会进行**反优化**，重新进入**解释执行**，图中第⑤条执行路径代表的就是**反优化**。

总的来说，C1的编译速度更快，C2的编译质量更高，分层编译的不同编译路径，也就是JVM根据当前服务的运行情况来寻找当前服务的最佳平衡点的一个过程。从JDK 8开始，JVM默认开启分层编译。



**云原生**：Cloud Native； Java小改版；



最好的效果：

存在的问题：

​	●java应用如果用jar，解释执行，热点代码才编译成**机器码**；初始启动速度慢，初始处理请		求数量少。

​	●大型云平台，要求每一种应用都必须秒级启动。每个应用都要求效率高。

​		希望的效果：

​	●java应用也能提前被编译成**机器码**，随时**急速启动**，一启动就急速运行，最高性能

​	●编译成机器码的好处：

​		○另外的服务器还需要安装Java环境

​		○编译成**机器码**的，可以在这个平台 Windows X64 **直接运行**。



原生镜像：native-image（机器码、本地镜像）

●把应用打包成能适配本机平台 的可执行文件（机器码、本地镜像）

### 2. GraalVM 

<https://www.graalvm.org/>

> GraalVM是一个高性能的JDK，旨在加速用Java和其他JVM语言编写的应用程序的执行，同时还提供JavaScript、Python和许多其他流行语言的运行时。 
> GraalVM提供了两种运行Java应用程序的方式：
> ●1. 在HotSpot JVM上使用Graal即时（JIT）编译器
> ●2. 作为预先编译（AOT）的本机可执行文件运行（本地镜像）。
>  GraalVM的多语言能力使得在单个应用程序中混合多种编程语言成为可能，同时消除了外部语言调用的成本。

#### 1. 架构 

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1683779334574-5040fa9c-d4cc-497a-aa2c-c937ccd2078d.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_37%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

#### 2. 安装 

> 跨平台提供原生镜像原理：

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1684149328177-6e1474c9-bec3-4b9a-afbe-b17b851f3ab1.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_22%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

##### 1. VisualStudio 

<https://visualstudio.microsoft.com/zh-hans/free-developer-offers/>

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1684143751616-6c4e0a2d-8507-452b-a6fc-8fe16d4c772d.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_35%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10%2Fresize%2Cw_750%2Climit_0)

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1684143930294-6e36dd3d-c994-44b2-aa7c-a120b23ab044.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_46%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10%2Fresize%2Cw_750%2Climit_0)

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1684152594737-ef7b01c4-304e-42d8-bcf7-00727014ec34.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_32%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10%2Fresize%2Cw_750%2Climit_0)

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1684143982484-bac17232-7e72-4bca-9a74-311fa888a8ca.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_28%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

记住你安装的地址；

##### 2. GraalVM  

###### 1. 安装 

下载 GraalVM + native-image

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1684144114094-7910b4e6-0073-4e01-9e37-7c165426a1d8.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_41%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1684144131164-13bd554b-1c97-4471-909e-7b0c7d5ebcd1.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_20%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1684144159433-2fbcf2fa-c2f3-482f-8fee-3473f5bb0fcf.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_35%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10%2Fresize%2Cw_750%2Climit_0)

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1684144366433-026a2e7c-a5fb-48ec-8b5a-ae390063b67e.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_41%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

###### 2. 配置 

修改 JAVA_HOME 与 Path，指向新bin路径

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1684144739494-b078d166-5e09-421d-b237-7ee1a2c153f6.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_24%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)



编辑环境变量

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1684144848621-d8577753-5a5b-402a-863b-617f43e35db1.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_19%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

验证JDK环境为GraalVM提供的即可：

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1684144703862-26be3be1-dd2d-491e-8eca-2317495d77cb.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_28%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

###### 3. 依赖

安装 native-image 依赖：

1. 网络环境好：参考：<https://www.graalvm.org/latest/reference-manual/native-image/#install-native-image>

```powershell
gu install native-image
```

2. 网络不好，使用我们下载的离线jar;native-image-xxx.jar文件

```powershell
gu install --file native-image-installable-svm-java17-windows-amd64-22.3.2.jar
```

###### 4. 验证

```powershell
native-image
```



#### 3. 测试

##### 1.创建项目

- 1. 创建普通java项目。编写HelloWorld类；

- - 使用`mvn clean package`进行打包
  - 确认jar包是否可以执行`java -jar xxx.jar`
  - 可能需要给 `MANIFEST.MF`添加 `Main-Class: 你的主类`



##### 2. 编译镜像

- 编译为原生镜像（native-image）：使用`native-tools`终端

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1684147385110-bd82ed80-a65a-439f-b82d-fec40e40edec.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_22%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

```powershell
#从入口开始，编译整个jar
native-image -cp boot3-15-aot-common-1.0-SNAPSHOT.jar com.atguigu.MainApplication -o Haha

#编译某个类【必须有main入口方法，否则无法编译】
native-image -cp .\classes org.example.App
```

##### 3. Linux平台测试

- 1. 安装gcc等环境

```shell
yum install lrzsz
sudo yum install gcc glibc-devel zli
```

- 2. 下载安装配置Linux下的GraalVM、native-image

- - 下载：<https://www.graalvm.org/downloads/>
  - 安装：GraalVM、native-image
  - 配置：JAVA环境变量为GraalVM

```shell
tar -zxvf graalvm-ce-java17-linux-amd64-22.3.2.tar.gz -C /opt/java/

sudo vim /etc/profile
#修改以下内容
export JAVA_HOME=/opt/java/graalvm-ce-java17-22.3.2
export PATH=$PATH:$JAVA_HOME/bin

source /etc/profile
```

- ​     3. 安装native-image

```shell
gu install --file native-image-installable-svm-java17-linux-amd64-22.3.2.jar
```

- ​	4.使用native-image编译jar为原生程序

```shell
native-image -cp xxx.jar org.example.App
```

### 3. SpringBoot整合

#### 1. 依赖导入

```xml
 <build>
        <plugins>
            <plugin>
                <groupId>org.graalvm.buildtools</groupId>
                <artifactId>native-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```

#### 2. 生成native-image

1、运行aot提前处理命令：`mvn springboot:process-aot`

2、运行native打包：`mvn -Pnative native:build`

```shell
# 推荐加上 -Pnative
mvn -Pnative native:build -f pom.xml
```

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1684152780642-b82e9976-170c-4118-bcd3-a319bc325774.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_17%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

#### 3. 常见问题

可能提示如下各种错误，无法构建原生镜像，需要配置环境变量；

- 出现`cl.exe`找不到错误
- 出现乱码
- 提示`no include path set`
- 提示fatal error LNK1104: cannot open file 'LIBCMT.lib'
- 提示 LINK : fatal error LNK1104: cannot open file 'kernel32.lib'
- 提示各种其他找不到



**需要修改三个环境变量**：`Path`、`INCLUDE`、`lib`

- 1、 Path：添加如下值

- - `C:\Program Files\Microsoft Visual Studio\2022\Community\VC\Tools\MSVC\14.33.31629\bin\Hostx64\x64`

- 2、新建`INCLUDE`环境变量：值为

```reStructuredText
C:\Program Files\Microsoft Visual Studio\2022\Community\VC\Tools\MSVC\14.33.31629\include;C:\Program Files (x86)\Windows Kits\10\Include\10.0.19041.0\shared;C:\Program Files (x86)\Windows Kits\10\Include\10.0.19041.0\ucrt;C:\Program Files (x86)\Windows Kits\10\Include\10.0.19041.0\um;C:\Program Files (x86)\Windows Kits\10\Include\10.0.19041.0\winrt
```

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1684154634081-c986db1e-7ab8-4fb6-ada7-f6999570310a.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_29%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

- 3、 新建`lib`环境变量：值为

```reStructuredText
C:\Program Files\Microsoft Visual Studio\2022\Community\VC\Tools\MSVC\14.33.31629\lib\x64;C:\Program Files (x86)\Windows Kits\10\Lib\10.0.19041.0\um\x64;C:\Program Files (x86)\Windows Kits\10\Lib\10.0.19041.0\ucrt\x64
```

![image.png](https://cdn.nlark.com/yuque/0/2023/png/1613913/1684156564048-2dbf1d6f-b0f0-4493-aef9-a2c1b7d22d58.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_28%2Ctext_5bCa56GF6LC3IGF0Z3VpZ3UuY29t%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

















