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
 * 访问次数的记录表
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
@Data
@TableName("uv_page")
public class UvPage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("path")
    private String path;

    @TableField("time")
    private LocalDateTime time;

}
