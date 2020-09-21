package com.yunque.cms.core.bo;

import com.yunque.cms.core.gen.entity.Information;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author West
 * @date create in 2019/12/17
 */
@Data
@Accessors(chain = true)
public class InfoDTO {

    public Information info;

    public InfoImage images;

    public List<String> tagList;
}
