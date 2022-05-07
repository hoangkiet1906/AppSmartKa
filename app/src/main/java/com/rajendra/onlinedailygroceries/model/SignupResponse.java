package com.rajendra.onlinedailygroceries.model;

public class SignupResponse {
    int i;
    String mess;

    public SignupResponse(int i, String mess) {
        this.i = i;
        this.mess = mess;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    @Override
    public String toString() {
        return "SignupResponse{" +
                "i=" + i +
                ", mess='" + mess + '\'' +
                '}';
    }
}
