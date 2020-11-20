package com.chilx.entity;

import com.chilx.handler.ArrayTypeHandler;
import com.chilx.handler.HobbyArrayHandler;
import com.chilx.handler.UserInfoHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.*;

@Table(name = "public.user_info")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    /**
     * 主键
     * GeneratedValue
     *      strategy: 主键生成策略 这里使用序列;
     *      generator: 对应SequenceGenerator中的name属性,可自定义两者一直即可;
     * SequenceGenerator
     *      name: 对应GeneratedValue中的generator的属性,可自定义两者一直即可;
     *      sequenceName: 生成id的序列, 注意这里需要用单引号引着;
     *      allocationSize: 步增大小
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq")
    @SequenceGenerator(name = "seq", sequenceName = "'user_info_id_seq'", allocationSize = 1)
    private Integer id;

    /**
     * 姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 爱好
     */
    @Column(name = "hobbies")
    // 配置数组类型转化器
    @ColumnType(column = "hobbies", typeHandler = ArrayTypeHandler.class)
    private String[] hobbies;

    /**
     * 爱好详情
     */
    @Column(name = "hobbies_detail")
    // 配置实体数组类型转化器
    @ColumnType(column = "hobbies_detail", typeHandler = HobbyArrayHandler.class)
    private Hobby[] hobbiesDetail;

    /**
     * 喜欢的人
     */
    @Column(name = "lover")
    // 配置实体类型转化器
    @ColumnType(column = "lover", typeHandler = UserInfoHandler.class)
    private UserInfo lover;
}