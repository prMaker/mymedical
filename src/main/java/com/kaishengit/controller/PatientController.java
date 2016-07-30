package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.dto.JSONResult;
import com.kaishengit.exception.NotFountException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

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
        return "patient/list";
    }

    @RequestMapping(value = "/dataload",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult<Patient> dataLoad(HttpServletRequest request) throws UnsupportedEncodingException {
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");

        String patientname = request.getParameter("patientname");
        String idcard = request.getParameter("idcard");
        String tel = request.getParameter("tel");

        Map<String,Object> params = Maps.newHashMap();
        params.put("patientname",new String(patientname.getBytes("ISO8859-1"),"UTF-8"));
        params.put("idcard",idcard);
        params.put("tel",tel);
        params.put("start",start);
        params.put("length",length);

        List<Patient> patientList = patientService.findPatientByPage(params);
        Long recordsTotal = patientService.countAllPatient();
        Long recordsFiltered = patientService.countAllByParams(params);

        return new DataTablesResult<>(draw,patientList,recordsTotal,recordsFiltered);
    }

    @RequestMapping("/new")
    public String newPatient(Model model){
        List<MedicalType> medicalTypeList = medicalTypeService.findAllMediclType();
        model.addAttribute("medicalTypeList",medicalTypeList);
        return "patient/new";
    }

    @RequestMapping(value = "/new",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult savenewPatient(Patient patient){
        patientService.saveOrUpdate(patient);
        return new JSONResult(JSONResult.DTO_STATE_SUCCESS);
    }

    @RequestMapping("/idcard/{idcard:\\w+}")
    @ResponseBody
    public JSONResult getIdcard(@PathVariable String idcard){

        if(idcard == null){
            throw new NotFountException();
        }

        return new JSONResult(Strings.getAge(idcard).toString());
    }

    @RequestMapping("/{id:\\d+}")
    public String patient(@PathVariable Integer id,Model model){
        Patient patient = patientService.findPatientById(id);

        if(patient == null){
            throw new NotFountException();
        }

        model.addAttribute("patient",patient);
        return "patient/patient";
    }

    @RequestMapping(value = "/edit/{id:\\d+}",method = RequestMethod.GET)
    public String editData(@PathVariable Integer id,Model model){

        List<MedicalType> medicalTypeList = medicalTypeService.findAllMediclType();
        Patient patient = patientService.findPatientById(id);

        if(patient == null){
            throw new NotFountException();
        }

        model.addAttribute("patient",patient);
        model.addAttribute("medicalTypeList",medicalTypeList);

        return "patient/edit";
    }

    @RequestMapping(value = "/edit/{id:\\d+}",method = RequestMethod.POST)
    @ResponseBody
    public String editNow(@PathVariable Integer id, Patient patient){
        if(patient == null){
            throw new NotFountException();
        }
        patientService.edit(patient);
        return "success";
    }

    @RequestMapping("del/{id:\\d+}")
    public String del(@PathVariable Integer id){
        patientService.delPatientById(id);
        return "redirect:/patient";
    }
}
