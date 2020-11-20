package com.chilx.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chilx
 * @date 2020/11/20
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hobby {

    private String name;

    private String desc;

}
