package com.huseyin.kubernetes.dto;

public class ScaleResponseDto {

    private String data;

    public ScaleResponseDto(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
