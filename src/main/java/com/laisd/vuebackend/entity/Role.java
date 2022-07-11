package com.laisd.vuebackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author Shudong
 * @since 2022-07-11
 */
@Data
@TableName("sys_role")
public class Role implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String description;

}
