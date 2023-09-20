package model;

import java.util.Objects;

public class Number {

    private final String number;
    private final String country;
    private final String updatedAt;
    private final String dataHumans;
    private final String fullNumber;
    private final String countryText;
    private final String maxDate;
    private final String status;

    public String getNumber() {
        return number;
    }

    public String getCountry() {
        return country;
    }

    public String getUpdatedAat() {
        return updatedAt;
    }

    public String getDataHumans() {
        return dataHumans;
    }

    public String getFullNumber() {
        return fullNumber;
    }

    public String getCountryText() {
        return countryText;
    }

    public String getMaxDate() {
        return maxDate;
    }

    public String getStatus() {
        return status;
    }

    private Number(NumberBuilder numberBuilder) {
        this.number = numberBuilder.number;
        this.country = numberBuilder.country;
        this.updatedAt = numberBuilder.updatedAt;
        this.dataHumans = numberBuilder.dataHumans;
        this.fullNumber = numberBuilder.fullNumber;
        this.countryText = numberBuilder.countryText;
        this.maxDate = numberBuilder.maxDate;
        this.status = numberBuilder.status;
    }

    public static class NumberBuilder {
        private String number;
        private String country;
        private String updatedAt;
        private String dataHumans;
        private String fullNumber;
        private String countryText;
        private String maxDate;
        private String status;

        public NumberBuilder setNumber(String number) {
            this.number = number;
            return this;
        }

        public NumberBuilder setCountry(String country) {
            this.country = country;
            return this;
        }

        public NumberBuilder setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public NumberBuilder setDataHumans(String dataHumans) {
            this.dataHumans = dataHumans;
            return this;
        }

        public NumberBuilder setFullNumber(String fullNumber) {
            this.fullNumber = fullNumber;
            return this;
        }

        public NumberBuilder setCountryText(String countryText) {
            this.countryText = countryText;
            return this;
        }

        public NumberBuilder setMaxDate(String maxDate) {
            this.maxDate = maxDate;
            return this;
        }

        public NumberBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Number build() {
            return new Number(this);
        }

    }

    @Override
    public String toString() {
        StringBuilder dataBuilder = new StringBuilder();
        appendFieldValue(dataBuilder, number);
        appendFieldValue(dataBuilder, country);
        appendFieldValue(dataBuilder, updatedAt);
        appendFieldValue(dataBuilder, dataHumans);
        appendFieldValue(dataBuilder, fullNumber);
        appendFieldValue(dataBuilder, countryText);
        appendFieldValue(dataBuilder, maxDate);
        appendFieldValue(dataBuilder, status);


        return dataBuilder.toString();
    }

    private void appendFieldValue(StringBuilder dataBuilder, String fieldValue) {
        dataBuilder.append(Objects.requireNonNullElse(fieldValue, "")).append(",");
    }

}
