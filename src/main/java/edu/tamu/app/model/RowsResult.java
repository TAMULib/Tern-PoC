package edu.tamu.app.model;

import java.util.Map;

public class RowsResult {
  private Map<TernColumn, String> rows;

  public RowsResult() {
  }

  public Map<TernColumn, String> getRows() {
    return rows;
  }

  public void setRows(Map<TernColumn, String> rows) {
    this.rows = rows;
  }

}