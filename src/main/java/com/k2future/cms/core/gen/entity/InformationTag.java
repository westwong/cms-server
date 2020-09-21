package com.k2future.cms.core.gen.entity;

import com.k2future.cms.core.BaseEntityProp;
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
public class InformationTag extends BaseEntityProp {

    private static final long serialVersionUID = 1L;

    private Long tagId;

    private Long informationId;


}
