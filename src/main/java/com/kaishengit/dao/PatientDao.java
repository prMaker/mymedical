package com.kaishengit.dao;

import com.kaishengit.pojo.Patient;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.inject.Named;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/29.
 */
@Named
public class PatientDao extends BaseDao<Patient,Integer> {
}
