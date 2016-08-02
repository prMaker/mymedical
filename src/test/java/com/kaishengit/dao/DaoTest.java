package com.kaishengit.dao;

import com.google.common.collect.Maps;
import com.kaishengit.pojo.Patient;
import com.kaishengit.serivce.PatientService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DaoTest {

    @Inject
    private PatientService patientService;

    @Test
    public void testFindByParam(){



        Map<String,Object> params = Maps.newHashMap();
        params.put("patientname","张飞");
        params.put("idcard","");
        params.put("tel","");
        params.put("start",1);
        params.put("length",3);

        List<Patient> patientList = patientService.findPatientByPage(params);
        for(Patient patient : patientList){
            System.out.println(patient);
        }
    }

    @Test
    public void TestDel(){
        patientService.delPatientById(900);
    }

    @Test
    public void ListDataTableTest(){
        Map<String,Object> numAndParams = Maps.newHashMap();
        numAndParams.put("start",0);
        numAndParams.put("length",10);
        numAndParams.put("patientname",null);
        numAndParams.put("idcard",null);
        numAndParams.put("tel",null);

        List<Patient> patientList = patientService.findPatientByPage(numAndParams);
        Long recordsTotal = patientService.countAllPatient();
//        TODO 问老师  session中单例
        Long recordsFiltered = patientService.countAllByParams();
        for(Patient patient : patientList){
            System.out.println(patient);
        }
    }

    @Test
    public void findByParam(){
        Map<String,Object> param = Maps.newHashMap();
        param.put("patientname","李四");


        System.out.println(patientService.findByparam(param));
    }

}
