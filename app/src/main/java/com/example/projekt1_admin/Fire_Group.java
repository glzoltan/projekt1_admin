package com.example.projekt1_admin;

public class Fire_Group {
    private String GROUP_NAME;
    private String GROUP_CODE;
    public Fire_Group(){

    }
    public Fire_Group(String NAME,String CODE){
        this.GROUP_NAME = NAME;
        this.GROUP_CODE = CODE;
    }
    public String getNAME() {
        return GROUP_NAME;
    }
    public String getGROUP_CODE() {
        return GROUP_CODE;
    }
}
