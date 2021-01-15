package cn.lxsir.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * Header栏 点击次数的记录
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
@Data
@TableName("header_click")
public class HeaderClick implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("slide_id")
    private Integer slideId;

    @TableField("times")
    private LocalDateTime times;


}
