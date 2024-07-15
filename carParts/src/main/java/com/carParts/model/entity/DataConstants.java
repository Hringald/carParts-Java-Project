package com.carParts.model.entity;

public class DataConstants {
    public static class Admin {
        public static final int NameMaxLength = 30;
        public static final int NameMinLength = 2;
    }

    public static class Part {

        public static final int NameMaxLength = 500;
        public static final int NameMinLength = 2;
        public static final int DescriptionMaxLength = 5000;
        public static final int DescriptionMinLength = 20;
        public static final String DecimalMinValue = "0.01";
        public static final String DecimalMaxValue = "79228162514264337593543950335";
        public static final int QuantityMinValue = 1;
        public static final int QuantityMaxValue = 100;
    }

    public static class Category {

        public static final int NameMaxLength = 100;
    }

    public static class Make {

        public static final int NameMaxLength = 100;
        public static final int NameMinLength = 2;
    }

    public static class Model {

        public static final int NameMaxLength = 100;
        public static final int NameMinLength = 2;
        public static final int MinYear = 1886;
        public static final int MaxYear = 2021;
    }

    public static class Offer {
        public static final int NameMaxLength = 100;
        public static final int NameMinLength = 2;

        public static final String PhoneRegex = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$";

        public static final int AddressMinLength = 8;
        public static final int AddressMaxLength = 189;

        public static final int CityMinLength = 2;
        public static final int CityMaxLength = 189;

        public static final int ZipMinLength = 4;
        public static final int ZipMaxLength = 18;
    }
}