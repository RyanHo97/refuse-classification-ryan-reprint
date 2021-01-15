package cn.lxsir.uniapp.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 题库表
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
@Data
public class QuestionBankVo extends BaseRowModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "question_id", type = IdType.AUTO)
    private Integer questionId;

    @TableField("garbage_type")
    @ExcelProperty(index = 0)
    private Integer garbageType;

    /**
     * 垃圾名称
     */
    @TableField("garbage_name")
    @ExcelProperty(index = 1)
    private String garbageName;

    /**
     * 解析
     */
    @TableField("analysis")
    private String analysis;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

}
