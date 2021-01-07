### mybatis
- mybatis-config.xml 核心配置
- userMapper.xml 具体配置
### 通常
- 修改DAO中的接口方法
- 配置Mapper.xml中的SQL语句
- 编写Test代码
### 知识点
- namespace中的包名要和DAO/Mapper接口的包名一致。
- 增删改需要提交事务
- 在xml中所有的标签都可以规定其顺序。
### select
- id: 就是对应的namespace中的方法名。
- resultType: sql语句执行的返回值。
- parameterType: 参数类型。
### 配置解析
- environments可以配置多套环境，但是实际使用的环境设置在default中。默认事务管理器是JDBC。
- properties配置，编写UserDaoTest一个数据库配置文件，然后在核心配置文件中引入。也可以在外部配置部分，如果有相同字段外部优先。
- typeAliases给实体类取类型别名，package按照类名的小写名称去匹配，在类比较多的时候使用，另一种更灵活。@Alias("hello")注解也可以。
```
<typeAliases>
    <typeAlias type="com.bytedance.pojo.User" alias="User"/> 
    <package name="com.bytedance.pojo"/>
</typeAliases>
```
- setting设置，缓存和懒加载和下划线转驼峰命名和日志实现。
- mappers映射器，mapperRegistry注册绑定我们的mapper文件，推荐使用mapper的resources。使用mapper的class或者package的name文件绑定注册，要保证接口和mapper配置文件必须同名，且必须在同一个包下。

```
<mappers>
   <mapper class="com.bytedance.dao.UserDao"></mapper>
   <package name="com.bytedance.dao"/>
</mappers>
```
### 生命周期和作用域
- 容易在并发场景下出问题
- sqlSessionFactory一旦创建就应该在应用的运行期间一直存在，适合采用单例模式。
- sqlSession每个线程都应该有自己的实例，不是线程安全的，不能共享。用完之后赶紧关闭。
### 第一个mybatis程序
- 配置环境
- 导入mybatis
- 编写代码
- 测试
- 依赖包：mybatis/connector/junit
- mybatis把DAO层的实现类转化为了mapper配置文件，集中将sql语句放在一个文件中。
### 注意点
- 每个mapper.xml都需要在mybatis核心配置文件中注册。并且以斜杠隔开。
- mapper.xml资源过滤问题。
### 概念
- mybatis是持久层框架
### 日志
- log4j:自定义输出格式，日志级别
### 分页
- 减少数据的处理量，加快响应速度。
- 语法：select * from yaojun.user limit startIndex, pageSize; 如果只有一个参数表示0到n.
## 缓存
- 一级缓存：默认开启也关闭不掉，在sqlsession拿到和关闭之间有效，类似于map。
-一级缓存失效：查询中间有增删改操作，查询不同的东西，强制刷新缓存，查询不同的mapper.xml。
- 二级缓存：<cache/>,只要开启了二级缓存，在同一个mapper下有效，当前一个sqlsession关闭后，缓存的内容放入到二级缓存中再次利用。
- 缓存顺序：先看二级，再看一级缓存，再走数据库。
