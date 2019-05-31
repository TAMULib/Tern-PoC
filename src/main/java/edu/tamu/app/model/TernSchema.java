package edu.tamu.app.model;

import java.util.List;

public class TernSchema {

    private String catalog;
    private String schema;
    private List<TernTable> tables;
    private List<String> types;

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

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getTypes() {
        return types;
    }

    public static TernSchema of(String catalog, String schema, List<TernTable> tables, List<String> types) {
        TernSchema ternSchema = new TernSchema();
        ternSchema.setCatalog(catalog);
        ternSchema.setSchema(schema);
        ternSchema.setTables(tables);
        ternSchema.setTypes(types);
        return ternSchema;
    }
}