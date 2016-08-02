package com.kaishengit.controller;

import com.kaishengit.dto.JSONResult;
import com.kaishengit.exception.NotFountException;
import com.kaishengit.pojo.Department;
import com.kaishengit.pojo.Disease;
import com.kaishengit.pojo.MedicalType;
import com.kaishengit.serivce.DepartmentService;
import com.kaishengit.serivce.DiseaseService;
import com.kaishengit.serivce.MedicalTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Administrator on 2016/7/31.
 */
@Controller
@RequestMapping("/settings")
public class SettingsManagerController {

    @Inject
    private DepartmentService departmentService;
    @Inject
    private DiseaseService diseaseService;
    @Inject
    private MedicalTypeService medicalTypeService;


    /**
     * 以下为病种设置页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/medicaltype")
    public String medicalTypeList(Model model) {
        List<MedicalType> medicalTypeList = medicalTypeService.finaALLMedicalType();

        model.addAttribute("medicalTypeList", medicalTypeList);
        return "settings/medicaltype";
    }

    @RequestMapping(value = "/medicaltype/new", method = RequestMethod.POST)
    @ResponseBody
    public String medicalTypeNewSave(MedicalType medicalType) {
        medicalTypeService.saveOrUpdate(medicalType);
        return "success";
    }

    @RequestMapping("/medicaltype/edit/{id:\\d+}")
    @ResponseBody
    public JSONResult medicalTypeEditLoad(@PathVariable Integer id) {
        Disease disease = diseaseService.findDiseasById(id);
        if (disease == null) {
            throw new NotFountException("未找到该id对应的数据");
        }
        return new JSONResult(disease);
    }

    @RequestMapping(value = "/medicaltype/edit", method = RequestMethod.POST)
    @ResponseBody
    public String medicalTypeEditSave(Disease disease) {
        diseaseService.saveOrUpdate(disease);
        return "success";
    }

    @RequestMapping(value = "/medicaltype/del/{id:\\d+}")
    @ResponseBody
    public String medicalTypeDel(@PathVariable Integer id) {
        diseaseService.delDieaseById(id);
        return "success";
    }


    /**
     * 以下为病种设置页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/disease")
    public String dieaseList(Model model) {
        List<Disease> diseaseList = diseaseService.finaALLDisease();
        List<Department> departmentList = departmentService.findAllDepartment();

        model.addAttribute("departmentList", departmentList);
        model.addAttribute("diseaseList", diseaseList);
        return "settings/disease";
    }

    @RequestMapping(value = "/disease/new", method = RequestMethod.POST)
    @ResponseBody
    public String dieaseNewSave(Disease disease) {
        if (StringUtils.isEmpty(disease.getDepartment().getId().toString())) {
            throw new NotFountException("科室选择异常");
        }
        diseaseService.saveOrUpdate(disease);
        return "success";
    }

    @RequestMapping("/disease/edit/{id:\\d+}")
    @ResponseBody
    public JSONResult dieaseEditLoad(@PathVariable Integer id) {
        Disease disease = diseaseService.findDiseasById(id);
        if (disease == null) {
            throw new NotFountException("未找到该id对应的数据");
        }
        return new JSONResult(disease);
    }

    @RequestMapping(value = "/disease/edit", method = RequestMethod.POST)
    @ResponseBody
    public String dieaseEditSave(Disease disease) {
        diseaseService.saveOrUpdate(disease);
        return "success";
    }

    @RequestMapping(value = "/disease/del/{id:\\d+}")
    @ResponseBody
    public String dieaseDel(@PathVariable Integer id) {
        diseaseService.delDieaseById(id);
        return "success";
    }

    /**
     * 以下内容为 Department
     *
     * @param model
     * @return
     */
    @RequestMapping("/department")
    public String departmentList(Model model) {
        List<Department> departmentList = departmentService.findAllDepartment();
        model.addAttribute("departmentList", departmentList);
        return "settings/department";
    }

    @RequestMapping(value = "/department/new", method = RequestMethod.POST)
    @ResponseBody
    public String departmentNewSave(Department department) {
        departmentService.saveOrUpdate(department);
        return "success";
    }

    @RequestMapping("/department/edit/{id:\\d+}")
    @ResponseBody
    public JSONResult departmentEditLoad(@PathVariable Integer id) {
        Department department = departmentService.findDepartmentById(id);
        if (department == null) {
            throw new NotFountException("未找到该id对应的数据");
        }
        return new JSONResult(department);
    }

    @RequestMapping(value = "/department/edit", method = RequestMethod.POST)
    @ResponseBody
    public String departmentEditSave(Department department) {
        departmentService.saveOrUpdate(department);
        return "success";
    }

    @RequestMapping(value = "/department/del/{id:\\d+}")
    @ResponseBody
    public String departmentDel(@PathVariable Integer id) {
        departmentService.delDepartmentById(id);
        return "success";
    }
}
