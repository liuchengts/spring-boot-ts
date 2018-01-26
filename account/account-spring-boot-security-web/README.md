
### 权限判断的使用
* 1：接口权限判断
```
XxxController中，具体某个接口的方法上面添加：

@PreAuthorize("hasPermission('decoration', 'PERMISSION_ORDER_TOTAL_DELIVER') or hasPermission('decoration', 'PERMISSION_ORDER_EXPORT')")

第一个参数为当前应用的domain名称
第二个参数为访问本接口的权限名称，与权限系统对应
多个权限则用or进行添加多个
```

* 2：前端页面权限判断
```
比如按钮的权限：
<div class="release-btn" v-on:click="publish" sec:authorize="hasPermission('decoration', 'PERMISSION_ORDER_EXPORT')">发布</div>

写法与接口方法方面的写法一致
```
* 2.1：前端页面权限判断pom.xml中加入以下依赖
```
<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-springsecurity4</artifactId>
</dependency>
```
* 2.2：前端页面权限判断需要添加如下配置才能生效
```
各自应用的SecurityConfigurerAdapter添加配置：
YtxXxxSecurityConfigurerAdapter的configure方法添加
web.expressionHandler(myWebSecurityExpressionHandler());
具体如下：
public void configure(WebSecurity web) throws Exception {
    //对应页面的hasPermission的处理
    web.expressionHandler(myWebSecurityExpressionHandler());
    web.ignoring().antMatchers(whiteUrls);
}
```

* 权限相关单词如下：
```
    (null, 'SELLER', '商家', 'SELLER_ADMIN', '商家管理员', now()),
    (null, 'ORDER', '订单', 'ORDER_VIEW', '查看', now()),
    (null, 'ORDER', '订单', 'ORDER_DELIVER', '发货', now()),
    (null, 'ORDER', '订单', 'ORDER_MEMO', '备注', now()),
    (null, 'ORDER', '订单', 'ORDER_EXPORT', '导出', now()),
    (null, 'ITEM', '商品', 'ITEM_VIEW', '查看', now()),
    (null, 'ITEM', '商品', 'ITEM_MANAGER', '管理', now()),
    (null, 'SIZE', '尺码表', 'SIZE_VIEW', '查看', now()),
    (null, 'SIZE', '尺码表', 'SIZE_MANAGER', '管理', now()),
    (null, 'EVALUATION', '评价', 'EVALUATION_VIEW', '查看', now()),
    (null, 'EVALUATION', '评价', 'EVALUATION_REPLY', '回复', now()),
    (null, 'DISPUTE', '售后', 'DISPUTE_VIEW', '查看', now()),
    (null, 'DISPUTE', '售后', 'DISPUTE_DEAL', '处理', now()),
    (null, 'MEMBER', '会员管理', 'MEMBER_VIEW', '查看', now()),
    (null, 'MEMBER', '会员管理', 'MEMBER_MANAGER', '管理', now()),
    (null, 'DELIVER', '物流', 'DELIVER_VIEW', '查看', now()),
    (null, 'DELIVER', '物流', 'DELIVER_MANAGER', '管理', now()),
    (null, 'PHOTO', '图片空间', 'PHOTO_VIEW', '查看', now()),
    (null, 'PHOTO', '图片空间', 'PHOTO_UPLOAD', '上传', now()),
    (null, 'PHOTO', '图片空间', 'PHOTO_MANAGER', '管理', now()),
    (null, 'PROMOTION', '营销', 'PROMOTION_MONEY_OFF', '满减', now()),
    (null, 'PROMOTION', '营销', 'PROMOTION_LIMIT_TIME', '限时折扣', now()),
    (null, 'PROMOTION', '营销', 'PROMOTION_COUPON', '优惠券', now()),
    (null, 'PROMOTION', '营销', 'PROMOTION_COMBO', '套餐搭配', now()),
    (null, 'FINANCING', '结算', 'FINANCING_CAPITAL', '资金账户', now()),
    (null, 'FINANCING', '结算', 'FINANCING_SETTING', '结算设置', now()),
    (null, 'FINANCING', '结算', 'FINANCING_INVOICE', '发票', now()),
    (null, 'DECORATION', '店铺装修', 'PC_DECORATION', 'PC店铺装修', now()),
    (null, 'DECORATION', '店铺装修', 'APP_DECORATION', 'APP店铺装修', now()),
    (null, 'SHOP', '店铺信息', 'SHOP_VIEW', '查看', now()),
    (null, 'SHOP', '店铺信息', 'SHOP_MANAGER', '管理', now()),
    (null, 'ERP', 'ERP设置', 'ERP_VIEW', '查看', now()),
    (null, 'ERP', 'ERP设置', 'ERP_MANAGER', '管理', now()),
    (null, 'LETTER', '站内信', 'LETTER_VIEW', '查看', now()),
    (null, 'LETTER', '站内信', 'LETTER_MANAGER', '管理', now());

```
