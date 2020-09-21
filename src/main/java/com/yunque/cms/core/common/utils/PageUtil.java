package com.yunque.cms.core.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunque.commons.util.PageProp;

import org.springframework.util.Assert;

/**
 *  mybatis plus分页工具
 *
 * @author West
 * @date create in 2019/9/10
 */
public class PageUtil {

    private static final int MAX_PAGE = 500;
    private static final int MAX_LIMIT = 1000;


    public static Page page(int page, int size) {
        Assert.isTrue(page <= MAX_PAGE, "pageNum number too large");
        Assert.isTrue(size <= MAX_LIMIT, "pageSize number too large");
        if (page < 0) {
            page = 0;
        }
        if (size <= 0) {
            size = 10;
        }
        return new Page<>(page, size);
    }

    public static Page page(PageProp pageProp) {
        return page(pageProp.pageNum, pageProp.pageSize);
    }
}
