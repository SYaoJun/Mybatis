## mybatis
- mybatis-config.xml
- userMapper.xml
## 通常
- 修改DAO中的接口方法
- 配置Mapper.xml中的SQL语句
- 编写Test代码
## 知识点
- namespace中的包名要和DAO/Mapper接口的包名一致。
- 增删改需要提交事务
### select
- id: 就是对应的namespace中的方法名。
- resultType: sql语句执行的返回值。
- parameterType: 参数类型。
