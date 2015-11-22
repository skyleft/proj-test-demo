package com.yy.expe.service.impl;

import com.yy.expe.bean.Demo;
import com.yy.expe.dao.IDemoDao;
import com.yy.expe.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by andy on 15/11/21.
 */

/**
 * Service层是业务逻辑的主要处理层
 * 个人感觉UnitTest主要应该针对这一层展开
 * 每个方法对应一个测试case
 * case之间不能相互调用和依赖
 * case结束最好要消除case运行过程中对持久化数据的影响
 * 可以考虑使用in-memory的db来做临时的持久化层
 */

@Service
public class DemoServiceImpl implements IDemoService {

    @Autowired
    private IDemoDao demoDao;

    @Override
    public Demo getDemoById(Integer id) {
        return this.demoDao.findOne(id);
    }

    @Override
    public Iterable<Demo> getAllDemos() {
        return this.demoDao.findAll();
    }

    @Override
    public Demo addOrUpdateDemo(Demo demo) {
        /**
         * @Mock_Logic_Check
         * 此处模拟一段逻辑
         * 如果demo的name成员是admin或者sa或者administrator,则不予保存
         */
        String name = demo.getName();
        if ("admin".equals(name)||"sa".equals(name)||"administrator".equals(name)){
            return null;
        }
        return this.demoDao.save(demo);
    }

    @Override
    public void removeDemoById(Integer id) {
        this.demoDao.delete(id);
    }
}