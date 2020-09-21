package com.yunque.cms.core.gen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yunque.cms.core.gen.entity.InformationTag;
import com.yunque.cms.core.gen.mapper.InformationTagMapper;
import com.yunque.cms.core.gen.service.IInformationTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunque.commons.util.Assert;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author west
 * @since 2019-12-16
 */
@Service
public class InformationTagServiceImpl extends ServiceImpl<InformationTagMapper, InformationTag> implements IInformationTagService {


    @Override
    public void removeByInfoId(Long infoId) {
        Assert.notNull(infoId,"informationId null ?");
        QueryWrapper<InformationTag> qw = new QueryWrapper<>();
         qw.eq("information_id",infoId);
        this.remove(qw);
    }
}
