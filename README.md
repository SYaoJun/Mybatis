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
### 数据库字段和类属性名不匹配问题
