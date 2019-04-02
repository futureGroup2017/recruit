package org.wlgzs.recruit.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 阿杰
 * Create 2018-08-09 20:54
 * Description:
 */
@Data
@TableName("tb_question")
@NoArgsConstructor
public class Question implements Serializable {

    @TableId(type = IdType.AUTO)
    private int questionId;
    private String questionName;

}
