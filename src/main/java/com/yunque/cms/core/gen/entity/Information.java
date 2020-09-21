package com.yunque.cms.core.gen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.yunque.cms.core.BaseEntityProp;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class Information extends BaseEntityProp {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String title;

    private String author;

    private LocalDateTime createTime;

    private String operator;

    /**
     * video,text
     */
    private String type;

    /**
     * app,pc
     */
    private String target;

    private String content;

    private String images;

    private String instruction;

    private Integer price;

    private Integer viewTimes;


}
