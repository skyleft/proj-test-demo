package com.yy.expe.service;

import com.yy.expe.bean.Demo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by andy on 15/11/21.
 */
public interface IDemoService{

    /**
     * 根据键获取demo
     * @return demo
     */
    Demo getDemoById(Integer id);


    /**
     * 根绝某业务逻辑获取所有Demo实体
     * @return
     */
    Iterable<Demo> getAllDemos();

    /**
     * 增加or更新demo实体,可能含有业务检查
     * @param demo
     * @return
     */
    Demo addOrUpdateDemo(Demo demo);

    /**
     *
     * @param id
     */
    void removeDemoById(Integer id);
}
