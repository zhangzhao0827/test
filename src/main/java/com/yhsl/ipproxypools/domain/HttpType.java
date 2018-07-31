package com.yhsl.ipproxypools.domain;

public enum HttpType {

    HTTP(1, "http"), HTTPS(2, "https");

    private int index;
    private String val;

    HttpType(int index, String val) {
        this.index = index;
        this.val = val;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
