package com.mall.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2019/12/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult  implements Serializable {
    private static final long serialVersionUID = 2302873019826104491L;
    public static final int SUCCESS = 200;
    private int status;
    private String message;
}
