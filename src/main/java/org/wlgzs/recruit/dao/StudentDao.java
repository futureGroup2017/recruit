package org.wlgzs.recruit.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.wlgzs.recruit.domain.Student;

/**
 * @author 阿杰
 * Create 2018-08-08 9:53
 * Description:
 */
@Mapper
@Repository
public interface StudentDao extends BaseMapper<Student>{

}
