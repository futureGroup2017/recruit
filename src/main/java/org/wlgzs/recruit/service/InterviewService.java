package org.wlgzs.recruit.service;

import org.wlgzs.recruit.domain.Interview;
import org.wlgzs.recruit.domain.Student;
import org.wlgzs.recruit.util.result.Result;

import java.util.List;

public interface InterviewService {

    void submitInterview(Student student,String detail, String interviewer);

    List<Interview> oneInterviewResult(int studentId);

    List<Interview> twoInterviewResult(int studentId);

    List<Interview> threeInterviewResult(int studentId);

}
