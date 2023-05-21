package com.itheima.service;
import com.itheima.domain.School;

import java.util.List;

public interface SchoolService {
    public List<School> findAllSchool();
    public int getSchoolCount();
    public School findSchoolBySchoolId(int id);
}
