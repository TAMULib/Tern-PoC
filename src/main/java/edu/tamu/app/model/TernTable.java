package edu.tamu.app.model;

import java.util.List;

public class TernTable {

    private String catalog;
    private String schema;
    private String name;
    private String type;
    private List<TernColumn> columns;

    public TernTable() {
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getSchema() {
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

    public static TernTable of(String catalog, String schema, String name, String type, List<TernColumn> columns) {
        TernTable ternTable = new TernTable();
        ternTable.setCatalog(catalog);
        ternTable.setSchema(schema);
        ternTable.setName(name);
        ternTable.setType(type);
        ternTable.setColumns(columns);
        return ternTable;
    }

}