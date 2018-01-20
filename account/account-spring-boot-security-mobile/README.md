Spring Boot Starter YTX Security Mobile
================================
在Spring Boot Mobile项目中整合Spring Security服务


### 如何使用

* 首先在pom.xml中添加对ytx-spring-boot-starter-security-mobile的引用
```xml
        <dependency>
            <groupId>com.ytx.email.boot</groupId>
            <artifactId>ytx-spring-boot-starter-security-mobile</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>       
```
* 接下来就是创建你系统中需要的Spring Security配置,代码如下:

```java
         /**
          * web security config demo
          *
          * @author linux_china
          */
         @Configuration
         @EnableWebSecurity
         @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
         public class WebSecurityConfig extends YtxMobileWebSecurityConfigurerAdapter {
             @Override
             protected void internalConfigure(HttpSecurity http) throws Exception {
                 http.authorizeRequests().
                         antMatchers("/home").authenticated()
                         .anyRequest().permitAll();
             }
         }
```


### todo 

* UserDetailsServiceImpl调整
