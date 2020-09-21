package com.k2future.cms.core.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.k2future.cms.core.gen.service.IInformationService;
import com.k2future.cms.core.bo.InfoDTO;
import com.k2future.cms.core.bo.InfoImage;
import com.k2future.cms.core.bo.InfoPage;
import com.k2future.cms.core.gen.entity.BizTag;
import com.k2future.cms.core.gen.entity.Information;
import com.k2future.cms.core.gen.entity.InformationTag;
import com.k2future.cms.core.gen.service.IBizTagService;
import com.k2future.cms.core.gen.service.IInformationTagService;
import com.yunque.commons.util.Assert;
import com.yunque.commons.util.BeanUtils;
import com.yunque.commons.util.RespBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * @author West
 * @date create in 2019/12/16
 */
@RestController
@Slf4j
public class InformationController {

    @Autowired
    private IBizTagService bizTagService;
    @Autowired
    private IInformationService informationService;
    @Autowired
    private IInformationTagService informationTagService;

    @PostMapping("/info/save")
    @Transactional(rollbackFor = Exception.class)
    public Map<Object, Object> save(@RequestBody InfoDTO dto) {
        Information info = dto.info;
        List<String> tagList = dto.tagList;
        InfoImage images = dto.images;
        Assert.notNull(info, "info null ?");
        Assert.notNull(images, "封面图片为空");
        info.setImages(JSON.toJSONString(images));

        informationService.saveInfo(info);
        // 保存标签
        if (tagList != null) {
            batchSave(tagList, info.getId());
        }
        return RespBuilder.succ();
    }

    @PostMapping("/info/update")
    @Transactional(rollbackFor = Exception.class)
    public Map<Object, Object> update(@RequestBody InfoDTO dto) {
        Information info = dto.info;
        List<String> tagList = dto.tagList;
        Assert.notNull(info, "info null ?");
        Long infoId = info.getId();
        Assert.notNull(infoId, "infoId null ?");
        Information db = informationService.getById(infoId);
        Assert.notNull(db, "infoId invalid !");
        if (dto.images != null){
            info.setImages(JSON.toJSONString(dto.images));
        }
        BeanUtils.copyBeanIgnoreNull(info, db);
        this.informationService.updateById(db);
        // 保存标签
        if (tagList != null) {
            informationTagService.removeByInfoId(db.getId());
            batchSave(tagList, infoId);
        }
        return RespBuilder.succ();
    }

    @PostMapping("/info/get")
    @Transactional(rollbackFor = Exception.class)
    public Map<Object, Object> get(@RequestBody Information dto) {
        Long infoId = dto.getId();
        Assert.notNull(infoId, "infoId null ?");
        Information db = informationService.getById(infoId);
        Assert.notNull(db, "infoId invalid !");
        List<String> tagList = bizTagService.listByInfoId(db.getId());
        //增加阅读量
        informationService.updateById(db.setViewTimes(db.getViewTimes() + 1));
        //返回
        InfoDTO vo = new InfoDTO().setInfo(db).setTagList(tagList);
        return RespBuilder.kv2Json(vo);
    }

    @PostMapping("/info/page")
    @Transactional(rollbackFor = Exception.class)
    public Map<Object, Object> page(@RequestBody InfoPage dto) {

        IPage<Information> page = informationService.customPage(dto);
        IPage<InfoDTO> result = page.convert(info -> {
            List<String> tagNameList = bizTagService.listByInfoId(info.getId());
            return new InfoDTO().setInfo(info).setTagList(tagNameList);
        });
        return RespBuilder.kv2Json(result);
    }
    @PostMapping("/info/delete")
    @Transactional(rollbackFor = Exception.class)
    public Map<Object, Object> delete(@RequestBody Information dto) {
        informationService.removeById(dto.getId());
        informationTagService.removeByInfoId(dto.getId());
        return RespBuilder.succ();
    }


    /**
     * 批量保存标签 和 info关系
     *
     * @param tagList 标签名字列表
     * @param infoId  information#id
     */
    private void batchSave(List<String> tagList, Long infoId) {
        tagList.forEach(tagName -> {
            BizTag tag = bizTagService.findByNameIfAbsentCreate(tagName);
            InformationTag informationTag = new InformationTag().setTagId(tag.getId()).setInformationId(infoId);
            informationTagService.save(informationTag);
        });
    }


}
