package edu.tamu.app.model;

public class TernColumn {

    private String name;
    private String type;
    private Integer size;
    private Integer digits;
    private Integer precision;
    private String defaultValue;

    public TernColumn() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getDigits() {
        return digits;
    }

    public void setDigits(Integer digits) {
        this.digits = digits;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public static TernColumn of(String name, String type, Integer size, Integer digits, Integer precision, String defaultValue) {
        TernColumn ternColumn = new TernColumn();
        ternColumn.setName(name);
        ternColumn.setType(type);
        ternColumn.setSize(size);
        ternColumn.setDigits(digits);
        ternColumn.setPrecision(precision);
        ternColumn.setDefaultValue(defaultValue);
        return ternColumn;
    }

}