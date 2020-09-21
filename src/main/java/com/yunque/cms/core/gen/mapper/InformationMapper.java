package com.yunque.cms.core.gen.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunque.cms.core.gen.entity.Information;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author west
 * @since 2019-12-16
 */
public interface InformationMapper extends BaseMapper<Information> {
    /**
     * 自定义分页查询
     * @param page 分页参数
     * @param keyWord 关键字
     * @param target 推送目标
     * @param type 类型
     * @param tagNameList 标签名列表
     * @return
     */
    IPage<Information> customPage(Page page, String keyWord, String type,String target, List<String> tagNameList);
}
