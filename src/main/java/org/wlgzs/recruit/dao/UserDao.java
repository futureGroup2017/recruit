package org.wlgzs.recruit.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.wlgzs.recruit.domain.User;

/**
 * </p>
 * 描述：
 * </p>
 *
 * @author AlgerFan
 * @since 2018/9/17
 */
@Mapper
@Repository
public interface UserDao  extends BaseMapper<User> {
}
