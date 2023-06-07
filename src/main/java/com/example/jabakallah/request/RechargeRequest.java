package com.example.jabakallah.request;

import javax.validation.constraints.NotBlank;

public class RechargeRequest {

    @NotBlank
    private int value;
    @NotBlank
    private String ownerphone;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getOwnerphone() {
        return ownerphone;
    }

    public void setOwnerphone(String ownerphone) {
        this.ownerphone = ownerphone;
    }
}
