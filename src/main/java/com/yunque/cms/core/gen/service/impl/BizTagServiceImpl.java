package com.yunque.cms.core.gen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yunque.cms.core.gen.entity.BizTag;
import com.yunque.cms.core.gen.mapper.BizTagMapper;
import com.yunque.cms.core.gen.service.IBizTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author west
 * @since 2019-12-16
 */
@Service
public class BizTagServiceImpl extends ServiceImpl<BizTagMapper, BizTag> implements IBizTagService {

    @Override
    public BizTag findByNameIfAbsentCreate(String tagName) {
         QueryWrapper<BizTag> qw = new QueryWrapper<>();
         qw.eq("name",tagName);
        BizTag one = this.getOne(qw, false);
        if (one == null) {
            one = new BizTag().setName(tagName);
            this.save(one);
        }
        return one;
    }

    @Override
    public List<String> listByInfoId(Long infoId) {
         QueryWrapper<BizTag> qw = new QueryWrapper<>();
         qw.select("name");
         qw.inSql("id","select tag_id from information_tag where information_id = " + infoId);
        List<BizTag> list = this.list(qw);
        List<String> result = new ArrayList<>(list.size());
        list.forEach(bizTag -> result.add(bizTag.getName()));
        return result;
    }
}
