package edu.tamu.app.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import edu.tamu.app.model.ExtractorService;
import edu.tamu.app.model.RowsResult;
import edu.tamu.app.model.TernColumn;
import edu.tamu.app.model.TernSchema;
import edu.tamu.app.model.TernTable;

@Service("VoyagerExtractorService")
public class VoyagerExtractorService implements ExtractorService {
    @Value("${extraction.schema.voyager.tableTypes}")
    private String[] tableTypes;

    @Value("${extraction.schema.voyager.selection}")
    private String[] schemaSelection;

    public VoyagerExtractorService() {
    }

    @Override
    public TernSchema describeSchema(EntityManager entityManager) {
        Session session = entityManager.unwrap(Session.class);
        List<TernTable> ternTables = new ArrayList<>();
        TernSchema ternSchema = TernSchema.of(ternTables, tableTypes);

        // TODO: provide a better implementation than selecting a simple list of arrays and then applying a split on ':' to get pre-configured
        if (schemaSelection == null || schemaSelection.length == 0) {
            describeSchema(session, null, null, ternTables);
        } else {
            for (String selection : schemaSelection) {
                String[] sel = selection.split(":", 2);

                if (sel.length == 2) {
                    describeSchema(session, sel[0], sel[1], ternTables);
                } else {
                    // TODO: handle error case of bad configuration.
                }
            }
        }

        return ternSchema;
    }

    @Override
    public Stream<RowsResult> extractData(List<TernTable> ternTables) {
        StringBuilder query = null;

        // TODO: not safe, escape parameter names and table names.
        // TODO: use something better than StringBuilder for building SQL.
        for (TernTable ternTable : ternTables) {
            if (ternTable.getColumns().size() == 0) continue;

            query = new StringBuilder("SELECT ");
            for (TernColumn ternColumn : ternTable.getColumns()) {
                query.append(ternColumn.getName()).append(", ");
            }

            query.setLength(query.length() - 2);
            query.append(" FROM ").append(ternTable.getSchema()).append(".").append(ternTable.getName()).append(";");
        }

       // TODO: execute query and return results as stream.
        Stream<RowsResult> rowResultStream = Stream.empty();
        return rowResultStream;
    }

    private void describeSchema(Session session, String catalog, String schema, List<TernTable> ternTables) {
        session.doWork(new Work() {

            @Override
            public void execute(Connection connection) throws SQLException {
                //connection.setReadOnly(true);

                // TODO: redesign this to be more efficient.
                DatabaseMetaData metaData = connection.getMetaData();

                ResultSet rsTables = metaData.getTables(catalog, schema, "%", tableTypes);
                ResultSetMetaData rsTablesMd = rsTables.getMetaData();

                ResultSet rsColumns = metaData.getColumns(catalog, schema, "%", null);
                ResultSetMetaData rsColumnsMd = rsColumns.getMetaData();

                int columnCount1 = rsTablesMd.getColumnCount();

                List<String[]> tablesMeta = new ArrayList<String[]>();
                while (rsTables.next()) {
                    String[] table = new String[columnCount1];
                    int i = 1;

                    while (i <= columnCount1) {
                        table[i - 1] = rsTables.getString(i++);
                    }
                    tablesMeta.add(table);
                }

                int columnCount2 = rsColumnsMd.getColumnCount();

                List<String[]> columnsMeta = new ArrayList<String[]>();
                while (rsColumns.next()) {
                    String[] column = new String[columnCount2];
                    int i = 1;

                    while (i <= columnCount2) {
                        column[i - 1] = rsColumns.getString(i++);
                    }
                    columnsMeta.add(column);
                }

                tablesMeta.forEach(table -> {
                    String tableName = table[2];
                    List<TernColumn> ternColumns = new ArrayList<>();
                    TernTable ternTable = TernTable.of(table[0], table[1], tableName, table[3], ternColumns);

                    for (int i = 0; i < columnsMeta.size(); i++) {
                        String[] columnMeta = columnsMeta.get(i);

                        if (tableName.equals(columnMeta[2])) {
                            Integer size = columnMeta[6] == null ? null : Integer.valueOf(columnMeta[6]);
                            Integer digits = columnMeta[8] == null ? null : Integer.valueOf(columnMeta[8]);
                            Integer precision = columnMeta[9] == null ? null : Integer.valueOf(columnMeta[9]);
                            TernColumn ternColumn = TernColumn.of(columnMeta[3], columnMeta[5], size, digits, precision, columnMeta[12]);

                            ternColumns.add(ternColumn);
                        }

                    }

                    ternTables.add(ternTable);
                });
            }
        });
    }
}