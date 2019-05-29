package edu.tamu.app.model;

public class TernColumn {

  private String tableName;
  private String columnName;
  private String dataType;

  public TernColumn() {
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

  public static TernColumn of(String dataType, String columnName, String tableName) {
    TernColumn ternField = new TernColumn();
    ternField.setDataType(dataType);
    ternField.setColumnName(columnName);
    ternField.setTableName(tableName);
    return ternField;
  }
  
}