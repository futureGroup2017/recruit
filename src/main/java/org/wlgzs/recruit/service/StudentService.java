package org.wlgzs.recruit.service;

import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.multipart.MultipartFile;
import org.wlgzs.recruit.domain.Student;
import org.wlgzs.recruit.util.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface StudentService {
    /**
     * Description 学生报名
     * Param [student]
     **/
    Result insert(Student student);
    /**
     * Description 查询学生是否已报名
     * Param [student]
     **/
    boolean checkStudent(Student student);
    /**
     * Description 前台根据姓名精确搜索学生
     * Param [name]
     **/
    Student selectStudent(String name);
    /**
     * Description 查找所有学生
     * Param [pageNum, pageSize]
     **/
    Page<Student> findAllStudent(int pageNum, int pageSize);
    /**
     * Description 删除学生
     * Param [studentId]
     **/
    Result deleteStudent(int studentId);
    /**
     * Description 根据id查找学生
     * Param [studentId]
     **/
    Student findStudentById(int studentId);
    /**
     * Description 修改学生信息
     * Param [student, studentId, myFileNames]
     **/
    void updateStudent(Student student, int studentId, MultipartFile myFileName1, MultipartFile myFileName2, HttpServletRequest request, HttpSession session);
    /**
     * Description 多条件搜索学生  名字模糊匹配，班级、学习方向精确匹配
     * Param [name, studentClass, direction, pageNum, pageSize]
     **/
    Page<Student> selectListByPage(String keyword, int pageNum, int pageSize);
    /**
     * Description 加入面试
     * Param [studentId]
     **/
    Result joinInterview(int studentId, HttpSession session);
    /**
     * Description 退出面试
     * Param [studentId]
     **/
    Result exitInterview(int studentId);

    Page<Student> findInterviewStudent(int pageNum, int pageSize);

    Page<Student> searchInterview(String keyword, int pageNum, int pageSize);

    Page<Student> allInterviewResult(int pageNum, int pageSize);

    Page<Student> searchInterviewResult(String keyword, int pageNum, int pageSize);

    Page<Student> personalResult(String interviewer, int pageNum, int pageSize);

    Page<Student> searchPersonalResult(String keyword, String interviewer, int pageNum, int pageSize);

    Page<Student> OneInterviewList(int pageNum, int pageSize);

    Page<Student> TwoInterviewList(int pageNum, int pageSize);

    Page<Student> ThreeInterviewList(int pageNum, int pageSize);

    Result cancelResult(int studentId);

    Result passInterview(int studentId);

    Result outInterview(int studentId);

    List<Student> interviewStudentExcel(String phase);

}
