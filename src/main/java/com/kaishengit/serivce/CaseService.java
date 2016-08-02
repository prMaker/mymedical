package com.kaishengit.serivce;

import com.kaishengit.dao.CaseDao;
import com.kaishengit.pojo.Case;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Administrator on 2016/7/31.
 */
@Named
@Transactional
public class CaseService {

    @Inject
    private CaseDao caseDao;

    public void saveOrUpdate(Case newcase) {
        caseDao.saveOrUpdate(newcase);
    }
}
