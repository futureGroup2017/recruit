package org.wlgzs.recruit.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 阿杰
 * Create 2018-08-08 15:00
 * Description: 打分项
 */
@Data
@NoArgsConstructor
@TableName("tb_score_item")
public class ScoreItem implements Serializable {

    @TableId(type = IdType.AUTO)
    private int scoreItemId;    //打分项id
    private String scoreName;    //打分项名称
    private int fullMarks=10;    //满分
}
