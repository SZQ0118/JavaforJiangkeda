package com.neusoft.domain;
/*


 */

import java.util.Date;

public class Emp {
    private int emp;
    private String ename;
    private String job;
    private int mgr;
    private Date hireDate;
    private int sal;
    private int comm;
    private int depton;
    public Emp(int emp, String ename, String job, int mgr, Date hireDate, int sal, int comm, int depton) {
        this.emp = emp;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hireDate = hireDate;
        this.sal = sal;
        this.comm = comm;
        this.depton = depton;
    }


    public int getEmp() {
        return emp;
    }

    public String getEname() {
        return ename;
    }

    public String getJob() {
        return job;
    }

    public int getMgr() {
        return mgr;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public int getSal() {
        return sal;
    }

    public int getComm() {
        return comm;
    }

    public int getDepton() {
        return depton;
    }

    public void setEmp(int emp) {
        this.emp = emp;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setMgr(int mgr) {
        this.mgr = mgr;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public void setComm(int comm) {
        this.comm = comm;
    }

    public void setDepton(int depton) {
        this.depton = depton;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "emp=" + emp +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hireDate=" + hireDate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", depton=" + depton +
                '}';
    }
}
