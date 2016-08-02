package com.kaishengit.serivce;

import com.kaishengit.dao.DiseaseDao;
import com.kaishengit.pojo.Disease;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Administrator on 2016/7/31.
 */
@Named
@Transactional
public class DiseaseService {

    @Inject
    private DiseaseDao diseaseDao;

    public void saveOrUpdate(Disease disease) {
        diseaseDao.saveOrUpdate(disease);
    }

    public Disease findDiseasById(Integer id) {
        return diseaseDao.findById(id);
    }

    public void delDieaseById(Integer id) {
        diseaseDao.delById(id);
    }

    public List<Disease> finaALLDisease() {
        return diseaseDao.findAll();
    }
}
