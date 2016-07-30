package com.kaishengit.serivce;

import com.kaishengit.dao.MedicalTypeDao;
import com.kaishengit.pojo.MedicalType;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Administrator on 2016/7/29.
 */
@Named
@Transactional
public class MedicalTypeService {

    @Inject
    private MedicalTypeDao medicalTypeDao;

    public List<MedicalType> findAllMediclType() {
        return medicalTypeDao.findAll();
    }
}
