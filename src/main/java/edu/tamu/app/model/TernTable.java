package edu.tamu.app.model;

import java.util.List;

public class TernTable {

  private TernSchema schema;
  private String name;
  private String type;
  private List<TernColumn> columns;

  public TernTable() {
  }

  public void setSchema(TernSchema schema) {
      this.schema = schema;
  }

  public TernSchema getSchema() {
      return schema;
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

  public void setColumns(List<TernColumn> columns) {
      this.columns = columns;
  }

  public List<TernColumn> getColumns() {
      return columns;
  }

  public static TernTable of(TernSchema schema, String name, String type, List<TernColumn> columns) {
      TernTable ternTable = new TernTable();
      ternTable.setSchema(schema);
      ternTable.setName(name);
      ternTable.setType(type);
      ternTable.setColumns(columns);
      return ternTable;
  }

}