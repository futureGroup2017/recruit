package org.wlgzs.recruit.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.wlgzs.recruit.domain.Interview;

/**
 * @author 阿杰
 * Create 2018-08-08 14:54
 * Description:
 */
@Mapper
@Repository
public interface InterviewDao extends BaseMapper<Interview> {
}
