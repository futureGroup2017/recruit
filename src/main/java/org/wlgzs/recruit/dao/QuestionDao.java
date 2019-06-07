package org.wlgzs.recruit.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.wlgzs.recruit.domain.Question;

/**
 * @author 阿杰
 * Create 2018-08-08 14:54
 * Description:
 */
@Mapper
@Repository
public interface QuestionDao extends BaseMapper<Question> {

    @Select("SELECT * FROM tb_question ORDER BY RAND() LIMIT 1")
    Question findOne();
}
