package com.atguigu.springboot01.entity;

import com.atguigu.springboot01.config.JsonDoubleSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.time.LocalDateTime;
import java.util.Date;

public class Person {

    private Integer age;

    private String ageStr;

    private String name;

    private Date birthday;

    private LocalDateTime schoolDate;

    private Integer timeOut=90;

    @JsonSerialize(using = JsonDoubleSerializer.class)
    private Double pocket;

    public Double getPocket() {
        return pocket;
    }

    public void setPocket(Double pocket) {
        this.pocket = pocket;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getSchoolDate() {
        return schoolDate;
    }

    public void setSchoolDate(LocalDateTime schoolDate) {
        this.schoolDate = schoolDate;
    }

    public String getAgeStr() {
        return ageStr;
    }

    public void setAgeStr(String ageStr) {
        this.ageStr = ageStr;
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }
}
