package com.yunque.cms.core.gen.service;

import com.yunque.cms.core.gen.entity.InformationTag;
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
public interface IInformationTagService extends IService<InformationTag> {

    /**
     * 根据信息id删除 信息和标签的关联
     * @param infoId information#id
     */
    void removeByInfoId(Long infoId);


}
