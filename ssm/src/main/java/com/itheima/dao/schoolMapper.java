package com.itheima.dao;
import com.itheima.domain.School;

import java.util.List;

public interface schoolMapper {
    public List<School> findAllSchool();
    public int getSchoolCount();
    public School findSchoolBySchoolId(int id);
}