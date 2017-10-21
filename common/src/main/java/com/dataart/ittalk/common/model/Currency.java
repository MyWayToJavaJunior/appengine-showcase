package com.dataart.ittalk.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Currency {

    public static final String CURRENCY_CODE = "currencyCode";
    public static final String VALUE = "value";
    public static final String UPLOAD_TIME = "uploadTime";

    private String currencyCode;
    private String value;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private Date uploadTime;

    public Currency(Builder builder) {
        this.currencyCode = builder.currencyCode;
        this.value = builder.value;
        this.uploadTime = builder.uploadTime;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyCode='" + currencyCode + '\'' +
                ", value='" + value + '\'' +
                ", uploadTime=" + uploadTime +
                '}';
    }

    public static class Builder {
        private String currencyCode;
        private String value;
        private Date uploadTime;

        public Builder currencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
            return this;
        }

        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public Builder uploadTime(Date uploadTime) {
            this.uploadTime = uploadTime;
            return this;
        }

        public Currency build() {
            return new Currency(this);
        }

    }
}
