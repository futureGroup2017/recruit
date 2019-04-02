package org.wlgzs.recruit.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.recruit.base.BaseController;
import org.wlgzs.recruit.domain.Student;
import org.wlgzs.recruit.util.CheckImage;
import org.wlgzs.recruit.util.result.Result;
import org.wlgzs.recruit.util.result.ResultCodeEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 阿杰
 * Create 2018-08-08 9:53
 * Description:
 */
@RestController
@RequestMapping("/student")
public class StudentController extends BaseController {

    /**
     * Description 前台根据姓名精确搜索学生
     * Param [name]
     **/
    @RequestMapping("/selectStudent")
    public ModelAndView selectStudent(Model model, String name) {
        if (name == null) {
            model.addAttribute("msg", "查询失败");
            return new ModelAndView("test");
        }
        Student student = studentService.selectStudent(name);
        if (student == null) {
            model.addAttribute("", "你查询的人员不存在");
        } else {
            model.addAttribute("student", student);
            model.addAttribute("msg", "查询成功");
        }
        return new ModelAndView("test");
    }

    /**
     * Description 开启报名
     **/
    @PostMapping("/startSignUp")
    public Result startSignUp() {
        canSignUp = true;
        Result result = new Result(ResultCodeEnum.SUCCESS);
        result.setMsg("已开启报名！");
        return result;
    }

    /**
     * Description 关闭报名
     **/
    @PostMapping("/stopSignUp")
    public Result stopSignUp() {
        canSignUp = false;
        Result result = new Result(ResultCodeEnum.FAIL);
        result.setMsg("已关闭报名！");
        return result;
    }

    /**
     * Description 下载面试人员表格
     * Param [response]
     **/
    @RequestMapping(value = "interviewStudentExcel", method = RequestMethod.GET)
    public void interviewStudentExcel(String phase, HttpServletResponse response) throws IOException {
        System.out.println(phase);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");
        sheet.setDefaultRowHeightInPoints(20);
        HSSFPrintSetup ps = sheet.getPrintSetup();
        ps.setLandscape(false); // 打印方向，true：横向，false：纵向
        ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); //纸张
        sheet.setHorizontallyCenter(true);//设置打印页面为水平居中

        //设置要导出的文件的名字
        String fileName = "";
        if (phase.equals("报名人员")) {
            fileName = "报名人员名单" + ".xls";
        }
        if (phase.equals("一面人员")) {
            fileName = "一面人员名单" + ".xls";
        }
        if (phase.equals("二面人员")) {
            fileName = "二面人员名单" + ".xls";
        }
        if (phase.equals("三面人员")) {
            fileName = "三面人员名单" + ".xls";
        }
        fileName = URLEncoder.encode(fileName, "UTF-8");
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"姓名", "性别", "班级", "手机号", "QQ号", "面试时间"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //设置行高
        row.setHeightInPoints(25);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(0, 12 * 256);
        sheet.setColumnWidth(1, 12 * 256);
        sheet.setColumnWidth(2, 12 * 256);
        sheet.setColumnWidth(3, 16 * 256);
        sheet.setColumnWidth(4, 16 * 256);
        sheet.setColumnWidth(5, 19 * 256);
        //其他表样式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        HSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 10);//设置字体大小
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//设置字体水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        style.setFont(font);
        //表头样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        HSSFFont font2 = workbook.createFont();//其他字体样式
        font2.setFontName("微软雅黑");
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font2.setFontHeightInPoints((short) 10);//设置字体大小
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);//设置字体水平居中
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        style2.setFont(font2);

        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellStyle(style2);
            cell.setCellValue(text);
        }
        List<Student> students = studentService.interviewStudentExcel(phase);
        //在表中存放查询到的数据放入对应的列
        HSSFCell cell;
        for (Student student : students) {
            HSSFRow row1 = sheet.createRow(rowNum);
            //设置行高
            row1.setHeightInPoints(20);
            cell = row1.createCell(0);
            cell.setCellValue(student.getName());
            cell.setCellStyle(style);
            cell = row1.createCell(1);
            cell.setCellValue(student.getSex());
            cell.setCellStyle(style);
            cell = row1.createCell(2);
            cell.setCellValue(student.getStudentClass());
            cell.setCellStyle(style);
            cell = row1.createCell(3);
            cell.setCellValue(student.getPhone());
            cell.setCellStyle(style);
            cell = row1.createCell(4);
            cell.setCellValue(student.getQq());
            cell.setCellStyle(style);
            if (student.getInterviewTime() != null) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                cell = row1.createCell(5);
                cell.setCellValue(formatter.format(student.getInterviewTime()));
                cell.setCellStyle(style);
            } else {
                cell = row1.createCell(5);
                cell.setCellValue("");
                cell.setCellStyle(style);
            }
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    /**
     * Description 查找所有学生
     * Param [pageNum, pageSize]
     **/
    @RequestMapping("/findAllStudent")
    public ModelAndView findAllStudent(Model model, @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                       @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        Page<Student> pageInfo = studentService.findAllStudent(pageNum, pageSize);
        model.addAttribute("Number", pageInfo.getCurrent());  //当前页数
        model.addAttribute("TotalPages", pageInfo.getPages());      //总页数
        model.addAttribute("students", pageInfo.getRecords());    //学生集合
        List<String> times = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (int i = 0; i < pageInfo.getRecords().size(); i++) {
            if (pageInfo.getRecords().get(i).getInterviewTime() != null) {
                times.add(formatter.format(pageInfo.getRecords().get(i).getInterviewTime()));
            } else {
                times.add("暂无");
            }
        }
        model.addAttribute("times", times); //学生面试时间
        model.addAttribute("msg", "查询成功");
        return new ModelAndView("Enrolment");
    }

    /**
     * Description 删除学生
     * Param [studentId]
     **/
    @RequestMapping("/deleteStudent")
    public Result deleteStudent(int studentId) {
        return studentService.deleteStudent(studentId);
    }

    /**
     * Description 跳转至修改学生
     * Param [studentId]
     **/
    @RequestMapping("/toUpdateStudent")
    public ModelAndView toUpdateStudent(int studentId, Model model) {
        Student student = studentService.findStudentById(studentId);
        List<String> img = new ArrayList<>();
        if (student.getWrittenTestImg() != null && !student.getWrittenTestImg().equals("")) {
            String[] strings = student.getWrittenTestImg().split(",");
            img = Arrays.asList(strings);
        }
        model.addAttribute("img", img);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = "";
        if (student.getInterviewTime() != null) {
            time = formatter.format(student.getInterviewTime());
        } else {
            time = "YYYY-MM-DD hh:mm:ss";
        }
        model.addAttribute("time",time);
        model.addAttribute("student", student);
        model.addAttribute("msg", "查询成功");
        return new ModelAndView("EnrolmentEdit");
    }

    /**
     * Description 修改学生信息
     * Param [student, studentId, myFileNames]
     **/
    @RequestMapping("/updateStudent")
    public ModelAndView updateStudent(Model model, Student student, int studentId, @RequestParam("file1") MultipartFile myFileName1,
                                      @RequestParam("file2") MultipartFile myFileName2, HttpSession session,
                                      HttpServletRequest request) {
        if (student.getName().equals("") && student.getName().length() < 2 && student.getStudentClass().equals("") &&
                student.getPhone().length() != 11 && student.getQq().equals("") && student.getQq().length() > 11) {
            model.addAttribute("msg", "修改失败，信息有误");
            return new ModelAndView("test");
        }
        String[] fileNames = new String[2];
        if (!myFileName1.isEmpty()) {
            fileNames[0] = myFileName1.getOriginalFilename();
        }
        if (!myFileName2.isEmpty()) {
            fileNames[1] = myFileName2.getOriginalFilename();
        }
        if (!CheckImage.verifyImages(fileNames)) {
            model.addAttribute("msg", "图片格式不正确");
            return new ModelAndView("test");
        }
        studentService.updateStudent(student, studentId, myFileName1, myFileName2, request, session);
        model.addAttribute("msg", "修改成功");
        return new ModelAndView("redirect:/student/findAllStudent");
    }

    /**
     * Description 多条件搜索学生  名字模糊匹配，班级、学习方向精确匹配
     * Param [name, studentClass, direction, pageNum, pageSize]
     **/
    @RequestMapping("/searchStudent")
    public ModelAndView searchStudent(Model model, String keyword, @RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
                                      @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        Page<Student> studentPage = studentService.selectListByPage(keyword, pageNum, pageSize);
        model.addAttribute("Number", studentPage.getCurrent());  //当前页数
        model.addAttribute("TotalPages", studentPage.getPages());      //总页数
        model.addAttribute("students", studentPage.getRecords());    //学生集合
        System.out.println(studentPage.getPages());
        List<String> times = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (int i = 0; i < studentPage.getRecords().size(); i++) {
            if (studentPage.getRecords().get(i).getInterviewTime() != null) {
                times.add(formatter.format(studentPage.getRecords().get(i).getInterviewTime()));
            } else {
                times.add("暂无");
            }
        }
        model.addAttribute("times", times); //学生面试时间
        model.addAttribute("keyword", keyword);
        model.addAttribute("msg", "查询成功");
        return new ModelAndView("EnrolmentSearch");
    }
}
