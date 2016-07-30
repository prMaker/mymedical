package com.kaishengit.serivce;

import com.kaishengit.dao.PatientDao;
import com.kaishengit.pojo.Patient;
import com.kaishengit.util.Strings;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Administrator on 2016/7/29.
 */
@Named
@Transactional
public class PatientService {

    @Inject
    private PatientDao patientDao;

    public List<Patient> findPatientList() {
        return patientDao.findAll();
    }

    public void saveOrUpdate(Patient patient) {
        patient.setPinyin(Strings.toPinyin(patient.getPatientname()));
        patient.setAge(Strings.getAge(patient.getIdcard()));
        patient.setState(Patient.PATIENT_STATE_NEW);

        patientDao.saveOrUpdate(patient);
    }
}
