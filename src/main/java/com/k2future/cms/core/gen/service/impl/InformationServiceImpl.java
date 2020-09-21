package com.k2future.cms.core.gen.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.k2future.cms.core.common.utils.PageUtil;
import com.k2future.cms.core.bo.InfoPage;
import com.k2future.cms.core.gen.entity.Information;
import com.k2future.cms.core.gen.mapper.InformationMapper;
import com.k2future.cms.core.gen.service.IInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunque.commons.util.Assert;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author west
 * @since 2019-12-16
 */
@Service
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information> implements IInformationService {

    @Override
    public void saveInfo(Information info) {
        Assert.hasText(info.getTitle(), "标题为空");
        Assert.hasText(info.getContent(), "内容为空");
        Assert.hasText(info.getTarget(), "推送对象为空");
        Assert.hasText(info.getContent(), "信息为类型为空");
        if (info.getCreateTime() == null){
            info.setCreateTime(LocalDateTime.now());
        }
        this.save(info);
    }

    @Override
    public IPage<Information> customPage(InfoPage dto) {
        return baseMapper.customPage(PageUtil.page(dto),
                dto.keyWord,
                dto.type,
                dto.target,
                dto.tagList);
    }
}
