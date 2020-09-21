package com.k2future.cms.core.bo;

import com.yunque.commons.util.PageProp;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author West
 * @date create in 2019/12/17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class InfoPage extends PageProp {

    public String keyWord;

    public String target;

    public String type;

    public List<String> tagList;

}
