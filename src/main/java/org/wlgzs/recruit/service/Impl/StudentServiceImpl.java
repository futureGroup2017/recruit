package org.wlgzs.recruit.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.wlgzs.recruit.base.BaseController;
import org.wlgzs.recruit.dao.StudentDao;
import org.wlgzs.recruit.domain.Student;
import org.wlgzs.recruit.service.StudentService;
import org.wlgzs.recruit.util.UploadUtil;
import org.wlgzs.recruit.util.result.Result;
import org.wlgzs.recruit.util.result.ResultCodeEnum;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 阿杰
 * Create 2018-08-08 9:52
 * Description:
 */
@Service
public class StudentServiceImpl implements StudentService {
    private static final Log logger = LogFactory.getLog(StudentServiceImpl.class);

    @Resource
    private StudentDao studentDao;

    @Override
    public Result insert(Student student) {
        Result result = new Result(ResultCodeEnum.SUCCESS);
        if (!BaseController.canSignUp) {
            result.setMsg("抱歉，报名已关闭");
            return result;
        }
        if (student.getName()==null || student.getName().length() < 2 || student.getStudentClass()==null ||
                student.getPhone()== null || student.getPhone().length() != 11 || student.getQq()==null || student.getQq().length() > 11) {
            result.setMsg("报名失败，信息有误");
            return result;
        }
        Wrapper<Student> studentEntityWrapper = new EntityWrapper<>();
        studentEntityWrapper.eq("name", student.getName());
        studentEntityWrapper.eq("qq", student.getQq());
        studentEntityWrapper.eq("student_class", student.getStudentClass());
        List<Student> students = studentDao.selectList(studentEntityWrapper);
        if (students.size() != 0) {
            result.setMsg("你已经报过名了");
            return result;
        } else {
            student.setApplyTime(new Date());
            student.setStatus("已报名");
            student.setInterviewPhase("未面试");
            student.setScore(0);
            student.setInterviewStatus(0);
            studentDao.insert(student);
            result.setMsg("报名成功");
            return result;
        }
    }

    @Override
    public boolean checkStudent(Student student) {
        Wrapper<Student> studentEntityWrapper = new EntityWrapper<>();
        studentEntityWrapper.eq("name", student.getName());
        studentEntityWrapper.eq("qq", student.getQq());
        studentEntityWrapper.eq("student_class", student.getStudentClass());
        List<Student> students = studentDao.selectList(studentEntityWrapper);
        return students.size() == 0;
    }

    @Override
    public Student selectStudent(String name) {
        Wrapper<Student> studentEntityWrapper = new EntityWrapper<>();
        studentEntityWrapper.eq("name", name);
        List<Student> students = studentDao.selectList(studentEntityWrapper);
        Student student = new Student();
        if (students != null) {
            return students.get(0);
        }
        return student;
    }

    @Override
    public Page<Student> findAllStudent(int pageNum, int pageSize) {
        Page<Student> page = new Page<>(pageNum,pageSize);
        Wrapper<Student> studentEntityWrapper = new EntityWrapper<>();
        studentEntityWrapper.orderBy("student_id",true);
        page.setRecords(studentDao.selectPage(page,studentEntityWrapper));
        return page;
    }

    @Override
    public Result deleteStudent(int studentId) {
        if (studentId == 0) {
            return new Result(ResultCodeEnum.UNDELETE);
        }
        studentDao.deleteById(studentId);
        return new Result(ResultCodeEnum.DELETE);
    }

    @Override
    public Student findStudentById(int studentId) {
        return studentDao.selectById(studentId);
    }

    @Override
    public void updateStudent(Student student, int studentId, MultipartFile myFileName1, MultipartFile myFileName2, HttpServletRequest request, HttpSession session) {
        Student studentOne = studentDao.selectById(studentId);
        String str = "/upload/writtenTestImg/" + studentId;
        StringBuilder writtenTestImg = new StringBuilder();
        if(studentOne.getWrittenTestImg()!=null && !studentOne.getWrittenTestImg().equals("")){
            writtenTestImg.append(studentOne.getWrittenTestImg());
        }
        UploadUtil uploadUtil = new UploadUtil();
        if (!myFileName1.isEmpty()) {
            System.out.println("上传正面图片");
            //重新上传 删除旧图片
            if (studentOne.getWrittenTestImg()!=null && !studentOne.getWrittenTestImg().equals("")) {
                if(studentOne.getWrittenTestImg().length() > 100) {
                    uploadUtil.deleteFile(writtenTestImg.substring(0,writtenTestImg.indexOf(",")));
                } else if(studentOne.getWrittenTestImg().lastIndexOf(",") != 0){
                    uploadUtil.deleteFile(writtenTestImg.substring(0,writtenTestImg.length()-1));
                }
            }
            //上传图片
            String fileName = myFileName1.getOriginalFilename();
            assert fileName != null;
            String fileNameExtension = fileName.substring(fileName.indexOf("."));
            // 生成实际存储的真实文件名
            String realName = UUID.randomUUID().toString() + fileNameExtension;
            String path = str + "/" + realName;
            uploadUtil.saveFile(myFileName1, path);
            if(studentOne.getWrittenTestImg()!=null && !studentOne.getWrittenTestImg().equals("")){
                writtenTestImg.replace(0,writtenTestImg.indexOf(","),path);
            } else {
                writtenTestImg.append(path);
                if(myFileName2.isEmpty()) writtenTestImg.append(",");
            }
        }
        if (!myFileName2.isEmpty()) {
            System.out.println("上传背面图片");
            //重新上传 删除旧图片
            if (studentOne.getWrittenTestImg()!=null && !studentOne.getWrittenTestImg().equals("")) {
                if(studentOne.getWrittenTestImg().length() > 100) {
                    uploadUtil.deleteFile(writtenTestImg.substring(writtenTestImg.indexOf(",")+1,writtenTestImg.length()));
                } else if(studentOne.getWrittenTestImg().lastIndexOf(",") == 0){
                    uploadUtil.deleteFile(writtenTestImg.substring(1));
                }
            }
            //上传图片
            String fileName = myFileName2.getOriginalFilename();
            assert fileName != null;
            String fileNameExtension = fileName.substring(fileName.indexOf("."));
            // 生成实际存储的真实文件名
            String realName = UUID.randomUUID().toString() + fileNameExtension;
            String path = str + "/" + realName;
            uploadUtil.saveFile(myFileName2, path);
            if(studentOne.getWrittenTestImg()!=null && !studentOne.getWrittenTestImg().equals("")){
                writtenTestImg.replace(writtenTestImg.indexOf(",")+1,writtenTestImg.length(),path);
            } else {
                writtenTestImg.append(",");
                writtenTestImg.append(path);
            }
        }
        student.setWrittenTestImg(writtenTestImg.toString());

        student.setInterviewPhase(studentOne.getInterviewPhase());
        student.setApplyTime(studentOne.getApplyTime());
        student.setScore(studentOne.getScore());
        student.setInterviewStatus(studentOne.getInterviewStatus());
        student.setStudentId(studentId);
        studentDao.updateById(student);
    }

    @Override
    public Page<Student> selectListByPage(String keyword, int pageNum, int pageSize) {
        Page<Student> page = new Page<>(pageNum,pageSize);
        Wrapper<Student> studentEntityWrapper = new EntityWrapper<>();  //设置查询条件，可为空
        studentEntityWrapper.like(keyword != null && !keyword.equals(""), "name", keyword)
                .or().like(keyword != null && !keyword.equals(""), "student_class", keyword)
                .or().like(keyword != null && !keyword.equals(""), "qq", keyword);
        studentEntityWrapper.orderBy("student_id",true);
        page.setRecords(studentDao.selectPage(page,studentEntityWrapper));
        return page;
    }

    @Override
    public Result joinInterview(int studentId, HttpSession session) {
        Result result = new Result(ResultCodeEnum.FAIL);
        Student student = studentDao.selectById(studentId);
        if(!student.getStatus().equals("笔试通过") && !student.getStatus().equals("一面通过") && !student.getStatus().equals("二面通过")){
            result.setMsg("不能面试该学生");
            return result;
        }
        if (student.getStatus().equals("笔试通过") || student.getStatus().equals("一面通过") || student.getStatus().equals("二面通过")) {
            student.setInterviewStatus(1);
            studentDao.updateById(student);
            result.setMsg("请面试");
            return result;
        }
        result.setMsg("不能面试该学生");
        return result;
    }

    @Override
    public Result exitInterview(int studentId) {
        Student student = studentDao.selectById(studentId);
        student.setInterviewStatus(0);
        studentDao.updateById(student);
        return new Result(ResultCodeEnum.SUCCESS);
    }

    @Override
    public Page<Student> findInterviewStudent(int pageNum, int pageSize) {
        Page<Student> page = new Page<>(pageNum,pageSize);
        Wrapper<Student> studentWrapper = new EntityWrapper<>();
        studentWrapper.eq("interview_status","1");
        page.setRecords(studentDao.selectPage(page,studentWrapper));
        return page;
    }

    @Override
    public Page<Student> searchInterview(String keyword, int pageNum, int pageSize) {
        Page<Student> page = new Page<>(pageNum,pageSize);
        Wrapper<Student> studentWrapper = new EntityWrapper<>();
        studentWrapper.like(keyword != null && !keyword.equals(""), "name", keyword);
        studentWrapper.eq("interview_status","1");
        page.setRecords(studentDao.selectPage(page,studentWrapper));
        return page;
    }

    @Override
    public Page<Student> allInterviewResult(int pageNum, int pageSize) {
        Page<Student> page = new Page<>(pageNum,pageSize);
        Wrapper<Student> studentWrapper = new EntityWrapper<>();
        studentWrapper.eq("interview_phase", "一面");
        studentWrapper.or().eq("interview_phase", "二面");
        studentWrapper.or().eq("interview_phase", "三面");
        studentWrapper.orderBy("interview_time",false);
        page.setRecords(studentDao.selectPage(page,studentWrapper));
        return page;
    }

    @Override
    public Page<Student> searchInterviewResult(String keyword, int pageNum, int pageSize) {
        Page<Student> page = new Page<>(pageNum,pageSize);
        Wrapper<Student> studentWrapper = new EntityWrapper<>();
        studentWrapper.like(keyword != null && !keyword.equals(""), "name", keyword);
        studentWrapper.and("(interview_phase = '一面' OR interview_phase = '二面' OR interview_phase = '三面')","interview_phase");
        /*studentWrapper.and().eq("interview_phase", "一面")
                .or().eq("interview_phase", "二面")
                .or().eq("interview_phase", "三面");*/
        studentWrapper.orderBy("interview_time",false);
        page.setRecords(studentDao.selectPage(page,studentWrapper));
        return page;
    }

    @Override
    public Page<Student> personalResult(String interviewer, int pageNum, int pageSize) {
        Page<Student> page = new Page<>(pageNum,pageSize);
        Wrapper<Student> studentWrapper = new EntityWrapper<>();
        studentWrapper.like("interviewers",interviewer);
        studentWrapper.and("(interview_phase = '一面' OR interview_phase = '二面' OR interview_phase = '三面')","interview_phase");
        /*studentWrapper.eq("interview_phase", "一面");
        studentWrapper.or().eq("interview_phase", "二面");
        studentWrapper.or().eq("interview_phase", "三面");*/
        studentWrapper.orderBy("interview_time",false);
        page.setRecords(studentDao.selectPage(page,studentWrapper));
        return page;
    }

    @Override
    public Page<Student> searchPersonalResult(String keyword, String interviewer, int pageNum, int pageSize) {
        Page<Student> page = new Page<>(pageNum,pageSize);
        Wrapper<Student> studentWrapper = new EntityWrapper<>();
        studentWrapper.like("interviewers",interviewer);
        studentWrapper.like(keyword != null && !keyword.equals(""), "name", keyword);
        studentWrapper.and("(interview_phase = '一面' OR interview_phase = '二面' OR interview_phase = '三面')","interview_phase");
        /*studentWrapper.eq("interview_phase", "一面");
        studentWrapper.or().eq("interview_phase", "二面");
        studentWrapper.or().eq("interview_phase", "三面");*/
        studentWrapper.orderBy("interview_time",false);
        page.setRecords(studentDao.selectPage(page,studentWrapper));
        return page;
    }

    @Override
    public Page<Student> OneInterviewList(int pageNum, int pageSize) {
        Page<Student> page = new Page<>(pageNum,pageSize);
        Wrapper<Student> studentWrapper = new EntityWrapper<>();
        studentWrapper.eq("interview_phase", "一面");
        studentWrapper.orderBy("interview_time",false);
        page.setRecords(studentDao.selectPage(page,studentWrapper));
        return page;
    }

    @Override
    public Page<Student> TwoInterviewList(int pageNum, int pageSize) {
        Page<Student> page = new Page<>(pageNum,pageSize);
        Wrapper<Student> studentWrapper = new EntityWrapper<>();
        studentWrapper.eq("interview_phase", "二面");
        studentWrapper.orderBy("interview_time",false);
        page.setRecords(studentDao.selectPage(page,studentWrapper));
        return page;
    }

    @Override
    public Page<Student> ThreeInterviewList(int pageNum, int pageSize) {
        Page<Student> page = new Page<>(pageNum,pageSize);
        Wrapper<Student> studentWrapper = new EntityWrapper<>();
        studentWrapper.eq("interview_phase", "三面");
        studentWrapper.orderBy("interview_time",false);
        page.setRecords(studentDao.selectPage(page,studentWrapper));
        return page;
    }

    @Override
    public Result cancelResult(int studentId) {
        Student student = studentDao.selectById(studentId);
        if(student.getStatus().equals("一面通过") || student.getStatus().equals("一面失败")){
            student.setStatus("笔试通过");
            student.setInterviewPhase("一面");
            studentDao.updateById(student);
        }
        if(student.getStatus().equals("二面通过") || student.getStatus().equals("二面失败")){
            student.setStatus("一面通过");
            student.setInterviewPhase("二面");
            studentDao.updateById(student);
        }
        if(student.getStatus().equals("三面通过") || student.getStatus().equals("三面失败")){
            student.setStatus("二面通过");
            student.setInterviewPhase("三面");
            studentDao.updateById(student);
        }
        return new Result(ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result passInterview(int studentId) {
        Student student = studentDao.selectById(studentId);
        Result result = new Result(ResultCodeEnum.UPDATE);
        result.setMsg("已通过");
        if (student.getInterviewPhase().equals("一面")) {
            student.setStatus("一面通过");
            studentDao.updateById(student);
            return result;
        }
        if (student.getInterviewPhase().equals("二面")) {
            student.setStatus("二面通过");
            studentDao.updateById(student);
            return result;
        }
        if (student.getInterviewPhase().equals("三面")) {
            student.setStatus("三面通过");
            studentDao.updateById(student);
            return result;
        }
        return new Result(ResultCodeEnum.UNUPDATE);
    }

    @Override
    public Result outInterview(int studentId) {
        Student student = studentDao.selectById(studentId);
        Result result = new Result(ResultCodeEnum.UPDATE);
        result.setMsg("已淘汰");
        if (student.getInterviewPhase().equals("一面")) {
            student.setStatus("一面失败");
            studentDao.updateById(student);
            return result;
        }
        if (student.getInterviewPhase().equals("二面")) {
            student.setStatus("二面失败");
            studentDao.updateById(student);
            return result;
        }
        if (student.getInterviewPhase().equals("三面")) {
            student.setStatus("三面失败");
            studentDao.updateById(student);
            return result;
        }
        return new Result(ResultCodeEnum.UNUPDATE);
    }

    @Override
    public List<Student> interviewStudentExcel(String phase) {
        Wrapper<Student> studentWrapper = new EntityWrapper<>();
        if(phase.equals("报名人员")){
            studentWrapper.eq("status","已报名");
        }
        if(phase.equals("一面人员")){
            studentWrapper.eq("status","笔试通过");
        }
        if(phase.equals("二面人员")){
            studentWrapper.eq("status","一面通过");
        }
        if(phase.equals("三面人员")){
            studentWrapper.eq("status","二面通过");
        }
        return studentDao.selectList(studentWrapper);
    }

}
