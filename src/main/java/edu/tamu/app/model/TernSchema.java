package edu.tamu.app.model;

import java.util.List;

public class TernSchema {

    private String catalog;
    private String schema;
    private List<TernTable> tables;
    private String[] types;

    public TernSchema() {
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

    public void setTables(List<TernTable> tables) {
        this.tables = tables;
    }

    public List<TernTable> getTables() {
        return tables;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public String[] getTypes() {
        return types;
    }

    public static TernSchema of(String catalog, String schema, List<TernTable> tables, String[] types) {
        TernSchema ternSchema = new TernSchema();
        ternSchema.setCatalog(catalog);
        ternSchema.setSchema(schema);
        ternSchema.setTables(tables);
        ternSchema.setTypes(types);
        return ternSchema;
    }
}