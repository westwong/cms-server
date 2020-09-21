package com.k2future.cms.core.gen.service;

import com.k2future.cms.core.gen.entity.BizTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author west
 * @since 2019-12-16
 */
public interface IBizTagService extends IService<BizTag> {

    /**
     * 根据标签名字查找标签
     * 如果不存在就创建一个新的
     * @param tagName name
     * @return 标签实体
     */
    BizTag findByNameIfAbsentCreate(String tagName);

    /**
     * 获取根据informationId获取
     * 标签名字列表
     * @param infoId id
     * @return 标签名字列表
     */
    List<String> listByInfoId(Long infoId);
}
