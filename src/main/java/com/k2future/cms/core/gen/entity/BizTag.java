package com.k2future.cms.core.gen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.k2future.cms.core.BaseEntityProp;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author west
 * @since 2019-12-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BizTag extends BaseEntityProp {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Long parentId;

    private Integer level;

    /**
     * tree:树状标签,grass： 散列标签
     */
    private String type;


}
