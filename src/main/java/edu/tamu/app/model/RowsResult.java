package edu.tamu.app.model;

import java.util.Map;

public class RowsResult {
  private Map<TernField, String> rows;

  public RowsResult() {
  }

  public Map<TernField, String> getRows() {
    return rows;
  }

  public void setRows(Map<TernField, String> rows) {
    this.rows = rows;
  }

}