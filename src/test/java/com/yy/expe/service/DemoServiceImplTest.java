package com.yy.expe.service;

import com.yy.expe.BaseCase;
import com.yy.expe.bean.Demo;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DemoServiceImplTest extends BaseCase{

    @Autowired
    private IDemoService demoService;

    /**
     * Method: getAllDemos()
     */
    @Ignore
    @Test
    public void testGetAllDemos() throws Exception {
        Assert.assertNotNull(null);

    }

    /**
     * Method: addOrUpdateDemo(Demo demo)
     */
    @Test(/*timeout = 100*/)
    public void testAddOrUpdateDemo() throws Exception {

        //初始化一个对象name为sa,根据业务逻辑是不能被保存的
        Demo demo = new Demo();
        demo.setName("sa");
        demo.setDesc("test2");
        Demo demo2 = demoService.addOrUpdateDemo(demo);
        Assert.assertNull(demo2);

        //修改name为符合规则的name,重新保存
        demo.setName("test");
        demo = demoService.addOrUpdateDemo(demo);

        //新增成功,id属性应该被回覆
        Integer id = demo.getId();
        Assert.assertNotNull(id);
        Assert.assertNotNull(demoService.getDemoById(id));

        //更新对象
        demo.setName("test1update");
        demo = demoService.addOrUpdateDemo(demo);
        Assert.assertEquals(id,demo.getId());

        //清除对象
        Integer nid = demo.getId();
        demoService.removeDemoById(nid);
        Demo deletedDemo = demoService.getDemoById(nid);
        Assert.assertNull(deletedDemo);
    }

    /**
     * Method: removeDemoById(Integer id)
     */
    @Ignore
    @Test
    public void testRemoveDemoById() throws Exception {
    }


} 