package edu.tamu.app.model;

public class TernField {

  private String data;
  private String dataType;
  private String columnName;
  private String tableName;

  public TernField() {}

  public String getData() {
    return data;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getColumnName() {
    return columnName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  public String getDataType() {
    return dataType;
  }

  public void setDataType(String dataType) {
    this.dataType = dataType;
  }

  public void setData(String data) {
    this.data = data;
  }

  public static TernField of(String data, String dataType, String columnName, String tableName) {
    TernField ternField = new TernField();
    ternField.setData(data);
    ternField.setDataType(dataType);
    ternField.setColumnName(columnName);
    ternField.setTableName(tableName);
    return ternField;
  }
  
}