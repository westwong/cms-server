package com.yunque.cms.core.gen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yunque.cms.core.bo.InfoPage;
import com.yunque.cms.core.gen.entity.Information;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author west
 * @since 2019-12-16
 */
public interface IInformationService extends IService<Information> {
    /**
     * 验证属性
     * 保存实体
     * @param info 实体
     */
    void saveInfo(Information info);

    /**
     * 自定义分页查询
     * @param dto 查询参数
     * @return
     */
    IPage<Information> customPage(InfoPage dto);
}
