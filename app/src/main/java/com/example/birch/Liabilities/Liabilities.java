package com.example.birch.Liabilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Liabilities {
    @SerializedName("mortgage")
    @Expose
    private Mortgage[] mortgage;

    @SerializedName("student")
    @Expose
    private Student[] student;

    @SerializedName("credit")
    @Expose
    private Credit[] credit;

    public Mortgage[] getMortgage ()
    {
        return mortgage;
    }

    public void setMortgage (Mortgage[] mortgage)
    {
        this.mortgage = mortgage;
    }

    public Student[] getStudent ()
    {
        return student;
    }

    public void setStudent (Student[] student)
    {
        this.student = student;
    }

    public Credit[] getCredit ()
    {
        return credit;
    }

    public void setCredit (Credit[] credit)
    {
        this.credit = credit;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [mortgage = "+mortgage+", student = "+student+", credit = "+credit+"]";
    }
}
