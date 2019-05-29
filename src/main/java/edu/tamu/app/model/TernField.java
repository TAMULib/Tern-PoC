package edu.tamu.app.model;

public class TernField {

  private String tableName;
  private String columnName;
  private String dataType;
  
  public TernField() {}

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

  public static TernField of(String dataType, String columnName, String tableName) {
    TernField ternField = new TernField();
    ternField.setDataType(dataType);
    ternField.setColumnName(columnName);
    ternField.setTableName(tableName);
    return ternField;
  }
  
}