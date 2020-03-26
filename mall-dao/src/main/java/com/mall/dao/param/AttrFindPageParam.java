package com.mall.dao.param;

import lombok.Data;

@Data
public class AttrFindPageParam extends BaseParam {
    /**
     * 属性名称
     */
    private String name;
    /**
     * 类目名称
     */
    private String typeName;

    /**
     * 类目ID
     */
    private Long typeId;
}
