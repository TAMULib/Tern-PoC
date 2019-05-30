package edu.tamu.app.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tamu.weaver.response.ApiResponse;

import static edu.tamu.weaver.response.ApiStatus.SUCCESS;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/extraction")
public class ExtractionController {

  @PersistenceContext(unitName = "extractor-database")
  EntityManager extrationDatasourceManager;

  @Transactional
  @RequestMapping("/schema")
  @PreAuthorize("hasRole('ANONYMOUS')")
  public ApiResponse describeSchema() {

    Map<String, List<String>> tables = new HashMap<>();

    Session session = extrationDatasourceManager.unwrap(Session.class);
    SessionFactory factory = session.getSessionFactory();

    session.doWork(new Work() {

      @Override
      public void execute(Connection connection) throws SQLException {

        DatabaseMetaData metaData = connection.getMetaData();

        ResultSet rsTables = metaData.getTables("evans", "AMDB", "%", new String[] { "TABLE" });
        ResultSetMetaData rsTablesMd = rsTables.getMetaData(); 


        ResultSet rsColumns = metaData.getColumns("evans", "AMDB", "%", null);
        ResultSetMetaData rsColumnsMd = rsColumns.getMetaData(); 

        int columnCount1 = rsTablesMd.getColumnCount();

        List<String[]> tablesMeta = new ArrayList<String[]>();
        while (rsTables.next()) {
          String[] table = new String[columnCount1];
          int i = 1;

          while(i <= columnCount1) {
            table[i - 1] = rsTables.getString(i++);
          }
          tablesMeta.add(table);
        }


        int columnCount2 = rsColumnsMd.getColumnCount();

        List<String[]> columnsMeta = new ArrayList<String[]>();
        while (rsColumns.next()) {
          String[] column = new String[columnCount2];
          int i = 1;

          while(i <= columnCount2) {
            column[i - 1] = rsColumns.getString(i++);
          }
          columnsMeta.add(column);
        }

        connection.close();

        // System.out.println(tablesMeta.get(0)[0]); // null
        // System.out.println(tablesMeta.get(0)[1]); // schema
        // System.out.println(tablesMeta.get(0)[2]); // name
        // System.out.println(tablesMeta.get(0)[3]); // type

        // System.out.println(columnsMeta.get(0)[0]); // null
        // System.out.println(columnsMeta.get(0)[1]); // schema
        // System.out.println(columnsMeta.get(0)[2]); // table name
        // System.out.println(columnsMeta.get(0)[3]); // column name
        // System.out.println(columnsMeta.get(0)[4]); // ?
        // System.out.println(columnsMeta.get(0)[5]); // data type
        // System.out.println(columnsMeta.get(0)[6]); // ?
        // System.out.println(columnsMeta.get(0)[7]); // ?
        // System.out.println(columnsMeta.get(0)[8]); // ?
        // System.out.println(columnsMeta.get(0)[9]); // ?


        tablesMeta.forEach(table -> {

          List<String> columns = new ArrayList<>();

          String tableName = table[2];

          System.out.println(String.format("Table: %s", tableName));

          for(int i = 0; i < columnsMeta.size(); i++) {

            String[] columnMeta = columnsMeta.get(i);

            if(tableName.equals(columnMeta[2])) {
              String col = "Column (Type = " + columnMeta[5] + "): " + columnMeta[3];

              System.out.println(String.format("  %s", col));
              columns.add(col);
            }

          }

          tables.put(tableName, columns);

        });

        }
    });
    return new ApiResponse(SUCCESS, tables);
  }

}


