package com.kaishengit.controller;

import com.kaishengit.dto.JSONResult;
import com.kaishengit.pojo.MedicalType;
import com.kaishengit.pojo.Patient;
import com.kaishengit.serivce.MedicalTypeService;
import com.kaishengit.serivce.PatientService;
import com.kaishengit.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Administrator on 2016/7/29.
 */
@Controller
@RequestMapping("/patient")
public class PatientController {

    @Inject
    private PatientService patientService;
    @Inject
    private MedicalTypeService medicalTypeService;

    @RequestMapping
    public String list(Model model){
        List<Patient> patientList = patientService.findPatientList();

        model.addAttribute("patientList",patientList);
        return "patient/list";
    }

    @RequestMapping("/new")
    public String newPatient(Model model){
        List<MedicalType> medicalTypeList = medicalTypeService.findAllMediclType();

        model.addAttribute("medicalTypeList",medicalTypeList);
        return "patient/new";
    }

    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public JSONResult savenewPatient(Patient patient){
        patientService.saveOrUpdate(patient);
        return new JSONResult(JSONResult.DTO_STATE_SUCCESS);
    }

    @RequestMapping("/idcard/{idcard:\\w+}")
    @ResponseBody
    public JSONResult getIdcard(@PathVariable String idcard){
        return new JSONResult(Strings.getAge(idcard).toString());
    }
}
