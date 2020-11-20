package com.chilx.mybatis;

import com.alibaba.fastjson.JSON;
import com.chilx.dao.UserInfoMapper;
import com.chilx.entity.Hobby;
import com.chilx.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author chilx
 * @date 2020/11/20
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisTest {

    @Autowired
    private UserInfoMapper userInfoMapper;


    /**
     * 插入数据并且返回主键
     */
    @Test
    public void testInsert() {

        UserInfo userInfo = UserInfo
                .builder()
                .userName("小明")
                .hobbies(new String[]{"唱歌", "跳舞"})
                .hobbiesDetail(
                        new Hobby[]{
                                Hobby.builder().name("唱歌").desc("左边 跟我一起画个龙, 在你右边 画一道彩虹").build(),
                                Hobby.builder().name("跳舞").desc("咚恰恰, 咚恰恰").build()
                        })
                .lover(UserInfo.builder().userName("品如").build())
                .build();

        userInfoMapper.insert(userInfo);

        System.out.println("返回的主键Id ---> " + userInfo.getId());
        System.out.println("添加成功!!!");

    }


    /**
     * 修改
     */
    @Test
    public void testUpdate() {

        UserInfo userInfo = UserInfo
                .builder()
                .id(6)
                .lover(UserInfo.builder().userName("如萍").build())
                .build();

        // 修改全部字段
        // userInfoMapper.updateByPrimaryKey(userInfo);
        // 根据主键更新属性不为null的值
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
        System.out.println("修改成功!!!");
    }


    /**
     * 查询
     */
    @Test
    public void testSelect() {

        List<UserInfo> userInfos = userInfoMapper.selectAll();
        System.out.println(JSON.toJSONString(userInfos));
        System.out.println("查询成功!!!");
    }


    /**
     * 删除
     */
    @Test
    public void testDel() {

        userInfoMapper.deleteByPrimaryKey(6);
        System.out.println("删除成功!!!");

    }


}
