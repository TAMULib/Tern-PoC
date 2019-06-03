package edu.tamu.app.model;

import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

public interface ExtractorService {

  public TernSchema describeSchema();

  public Stream<RowsResult> extractData(List<TernTable> ternTables);

}