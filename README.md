# 关于单元测试

### 几点说明

* 三层架构哪些需要测试

    * Dao
    
        Dao层往往是紧贴数据库层面的CRUD操作,不应该有业务代码
        使用mybatis或者更抽象spring data jpa基本上可以零代码或者极少代码实现
        所以Dao层面上不太需要做UnitTest
        如果是为了谨慎起见,想测试dao与底层数据库的结合是否良好
        可以考虑[DBUnit](http://dbunit.sourceforge.net/)
    
    * Service
        
        Service层是业务逻辑的主要处理层
         * 个人感觉UnitTest主要应该针对这一层展开
         * 每个方法对应一个测试case
         * case之间不能相互调用和依赖
         * case结束最好要消除case运行过程中对持久化数据的影响
         * 可以考虑使用in-memory的db来做临时的持久化层
    
    * Controller
        
        Controller层面上往往是对接收外部请求并做出响应的正确性的检查
        HTTP参数,响应码
        Controller层面上如果是使用的MVC则可用Spring的mockmvc进行测试
        如果是使用基于servlet的技术,如NyyFramework,则可以使用mockito进行测试


* 可以使用spring embedded datasource + 一些基于内存的db模拟持久化层，方便快捷不留痕迹


* 最好是定义一个BaseCase，其他的case都继承它，免去重复配置context
    
    例如

        @RunWith(SpringJUnit4ClassRunner.class)
        @SpringApplicationConfiguration(classes = ProjTestDemoApplication.class)
        public class BaseCase extends AbstractJUnit4SpringContextTests{
        }
        
* 虽然最好是每一个case对应一个方法，但是有时候一个case往往就覆盖了整个service
    例如
    
        rawlen = service.list.len
        pojo
        service.add(pojo)
        newlen = service.list.len
        Assert.assertEquals(rawlen+1,newlen)
        ....
    这样的话可以使用@Ignore注解标记在此case中已经覆盖过的case\
    
    

* 有些方法的执行时间也可能作为测试指标，如果超时还未返回，就是失败，这样的情况可以对test注解加一个timeout属性
    
    例如
        
        @Test(timeout = 1000) //1000微秒以内返回
        public void testXXX(){}

* 有些方法可能对特定入参会抛出特定异常，这样的情况可以对test注解加一个expect属性
    
    例如
        
        @Test(expected = SQLSyntaxErrorException.class) //预计会抛出SQL语法错误的异常

* 对于需要登陆才能正常请求的接口测试，可以使用响应的浏览器工具拿到cookie，然后直接在mockmvc请求时附加这些cookie即可，也可以直接在接口的before里执行mock登陆


* Assert有好多，最好用这个 *org.junit.Assert*
    
### 使用Spring MVC的项目

    此项目可做参考
    基于Spring Boot + Spring MVC + JPA + HSQLDB构件
    
    运行方式
    直接执行ProjTestDemoApplication的main方法即可启动server
    
    测试方式
    mvn test
    或者单独执行某个测试用例



### 使用Nyy Framework的项目
    
    Nyy Framework 是由servlet来响应用户请求的，可以参考 [Mockito](http://mockito.org/)
    其实 Mockito 也可以对spring mvc进行测试。