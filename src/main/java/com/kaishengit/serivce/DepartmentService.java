package com.kaishengit.serivce;

import com.kaishengit.dao.DepartmentDao;
import com.kaishengit.pojo.Department;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Administrator on 2016/7/31.
 */
@Named
@Transactional
public class DepartmentService {

    @Inject
    private DepartmentDao departmentDao;

    public List<Department> findAllDepartment() {
        return departmentDao.findAll();
    }

    public void saveOrUpdate(Department department) {
        departmentDao.saveOrUpdate(department);
    }

    public Department findDepartmentById(Integer id) {
        return departmentDao.findById(id);
    }

    public void delDepartmentById(Integer id) {
        departmentDao.delById(id);
    }
}
