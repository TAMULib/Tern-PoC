package edu.tamu.app.model;

import java.util.List;

public class TernSchema {

    private List<TernTable> tables;
    private String[] types;

    public TernSchema() {
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

    public static TernSchema of(List<TernTable> tables, String[] types) {
        TernSchema ternSchema = new TernSchema();
        ternSchema.setTables(tables);
        ternSchema.setTypes(types);
        return ternSchema;
    }
}