package org.wlgzs.recruit.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 阿杰
 * Create 2018-08-08 14:47
 * Description:
 */
@Data
@NoArgsConstructor
@TableName("tb_interview")
public class Interview implements Serializable {

    @TableId(type = IdType.AUTO)
    private int interviewId;    //面试id
    private int studentId;    //学生id
    private String interviewer;    //面试官
    private String detail;    //面试评分详情
    private String interviewPhase;    //面试阶段
}
