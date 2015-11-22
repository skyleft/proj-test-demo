package com.yy.expe.dao;

import com.yy.expe.bean.Demo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by andy on 15/11/21.
 * Dao层往往是紧贴数据库层面的CRUD操作,不应该有业务代码
 * 使用mybatis或者更抽象spring data jpa基本上可以零代码或者极少代码实现
 * 所以Dao层面上不太需要做UnitTest
 * 如果是为了谨慎起见,想测试dao与底层数据库的结合是否良好
 * 可以考虑DBUnit
 */
@Repository
public interface IDemoDao extends CrudRepository<Demo,Integer>{
}
