package org.wlgzs.recruit.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.stereotype.Service;
import org.wlgzs.recruit.dao.InterviewDao;
import org.wlgzs.recruit.dao.StudentDao;
import org.wlgzs.recruit.domain.Interview;
import org.wlgzs.recruit.domain.Student;
import org.wlgzs.recruit.service.InterviewService;
import org.wlgzs.recruit.util.result.Result;
import org.wlgzs.recruit.util.result.ResultCodeEnum;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 阿杰
 * Create 2018-08-08 14:55
 * Description:
 */
@Service
public class InterviewServiceImpl implements InterviewService {
    @Resource
    private InterviewDao interviewDao;
    @Resource
    private StudentDao studentDao;

    @Override
    public void submitInterview(Student student,String detail, String interviewer) {
        Interview interview = new Interview();
        if(student.getInterviewers().equals("") || student.getInterviewers()==null){
            student.setInterviewers(interviewer);
        } else {
            student.setInterviewers(student.getInterviewers()+","+interviewer);
        }
        student.setInterviewTime(new Date());
        if(student.getStatus().equals("笔试通过")) {
            interview.setInterviewPhase("一面");
            if(!student.getInterviewPhase().equals("一面")){
                student.setInterviewPhase("一面");
            }
        }
        if(student.getStatus().equals("一面通过")) {
            interview.setInterviewPhase("二面");
            if(!student.getInterviewPhase().equals("二面")){
                student.setInterviewPhase("二面");
            }
        }
        if(student.getStatus().equals("二面通过")) {
            interview.setInterviewPhase("三面");
            if(!student.getInterviewPhase().equals("三面")){
                student.setInterviewPhase("三面");
            }
        }
        studentDao.updateById(student);
        Wrapper<Interview> interviewWrapper = new EntityWrapper<>();
        interviewWrapper.eq("student_id",student.getStudentId());
        interviewWrapper.eq("interviewer",interviewer);
        List<Interview> interviews = interviewDao.selectList(interviewWrapper);
        //Interview interview1 = interviews.get(0);
        if(interviews.size()!=0){
            Interview interview1 = interviews.get(0);
            interview1.setStudentId(student.getStudentId());
            interview1.setDetail(detail);
            interview1.setInterviewer(interviewer);
            interviewDao.updateById(interview1);
        } else {
            interview.setStudentId(student.getStudentId());
            interview.setDetail(detail);
            interview.setInterviewer(interviewer);
            interviewDao.insert(interview);
        }
    }

    @Override
    public List<Interview> oneInterviewResult(int studentId) {
        Wrapper<Interview> interviewWrapper = new EntityWrapper<>();
        interviewWrapper.eq("student_id",studentId);
        interviewWrapper.eq("interview_phase","一面");
        return interviewDao.selectList(interviewWrapper);
    }

    @Override
    public List<Interview> twoInterviewResult(int studentId) {
        Wrapper<Interview> interviewWrapper = new EntityWrapper<>();
        interviewWrapper.eq("student_id",studentId);
        interviewWrapper.eq("interview_phase","二面");
        return interviewDao.selectList(interviewWrapper);
    }

    @Override
    public List<Interview> threeInterviewResult(int studentId) {
        Wrapper<Interview> interviewWrapper = new EntityWrapper<>();
        interviewWrapper.eq("student_id",studentId);
        interviewWrapper.eq("interview_phase","三面");
        return interviewDao.selectList(interviewWrapper);
    }

}
