package com.jbwz.core.common.base;

import lombok.Data;

import java.io.Serializable;

/**
 * common pageList
 *
 * @author yyh
 */
@Data
public class Page implements Serializable {

    private static final long serialVersionUID = -18989382982494L;
    protected int pageNum = 0;
    protected int pageSize = 0;
    protected long total = 0;


}
