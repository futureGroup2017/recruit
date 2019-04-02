package org.wlgzs.recruit.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 阿杰
 * Create 2018-08-08 9:54
 * Description:
 */
@Data
@NoArgsConstructor
@TableName("tb_student")
public class Student implements Serializable {

    @TableId(type = IdType.AUTO)
    private int studentId;  //学生id
    private String name;    //姓名
    private String sex;    //性别
    private String studentClass;    //班级
    private String phone;   //手机号
    private String qq;  //QQ号

    private String interviewers;   //面试官们
    private Date applyTime;   //申请时间
    private String status;   //学生状态
    private String interviewPhase;  //面试阶段
    private int score;     //平均得分（百分制）
    private String writtenTestImg;  //笔试照片
    private Date interviewTime;   //面试时间
    private int interviewStatus;    //是否正在面试
}
