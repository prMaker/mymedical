package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.dto.JSONResult;
import com.kaishengit.pojo.Case;
import com.kaishengit.pojo.Department;
import com.kaishengit.pojo.Disease;
import com.kaishengit.pojo.Patient;
import com.kaishengit.serivce.CaseService;
import com.kaishengit.serivce.DepartmentService;
import com.kaishengit.serivce.DiseaseService;
import com.kaishengit.serivce.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/31.
 */
@Controller
@RequestMapping("/visit")
public class CaseController {

    @Inject
    private CaseService caseService;
    @Inject
    private PatientService patientService;
    @Inject
    private DepartmentService departmentService;
    @Inject
    private DiseaseService diseaseService;

    @RequestMapping
    public String vistList(){
        return "visit/list";
    }

    @RequestMapping("/dataLoad")
    @ResponseBody
    public DataTablesResult<Case> dataTablesResultLoad(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("lenght");

        String patientname = request.getParameter("patientname");
        String tel = request.getParameter("tel");

//        TODO dataTable 数据获取

        return null;
    }

    @RequestMapping("/new")
    public String newVisit(Model model){
        List<Department> departmentList = departmentService.findAllDepartment();
        List<Disease> diseaseList = diseaseService.finaALLDisease();

        model.addAttribute("departmentList",departmentList);
        model.addAttribute("diseaseList",diseaseList);

        return "visit/newvisit";
    }

    @RequestMapping(value = "patient/name/data",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult getPatientData(String patientname) throws UnsupportedEncodingException {
        Map<String,Object> param = Maps.newHashMap();
        System.out.println(patientname);
        System.out.println(patientname);
        System.out.println(patientname);
        param.put("patientname",patientname);
        Patient patient = patientService.findByparam(param);
        return new JSONResult(patient);
    }


    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public String saveVisit(Case newcase, RedirectAttributes redirectAttributes){
        caseService.saveOrUpdate(newcase);

        redirectAttributes.addAttribute("message","成功新建新病例");
        return "redirect:/visit";

    }











}
