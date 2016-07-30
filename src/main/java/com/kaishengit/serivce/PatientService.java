package com.kaishengit.serivce;

import com.kaishengit.dao.PatientDao;
import com.kaishengit.pojo.Patient;
import com.kaishengit.util.Strings;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

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

    public List<Patient> findPatientByPage(Map<String,Object> params) {
        return patientDao.findPatientByPage(params);
    }

    public Long countAllPatient() {
        return patientDao.countAll();
    }

    public Long countAllByParams(Map<String, Object> params) {
        return patientDao.countAllByparams(params);
    }

    public Patient findPatientById(Integer id) {
        return patientDao.findById(id);
    }

    public void delPatientById(Integer id) {
        patientDao.delById(id);
    }

    /**
     * 重新编辑客户
     * @param patient
     */
    public void edit(Patient patient) {
        patient.setPinyin(Strings.toPinyin(patient.getPatientname()));
        patientDao.saveOrUpdate(patient);
    }
}
