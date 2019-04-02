package org.wlgzs.recruit.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * </p>
 * 描述：
 * </p>
 *
 * @author AlgerFan
 * @since 2018/9/17
 */
@Data
@NoArgsConstructor
@TableName("tb_user")
public class User {

    @TableId(type = IdType.AUTO)
    private int userId;  //用户id
    private String userName;
    private String password;
    private String name;
}
