package edu.tamu.app.model;

import java.util.List;
import java.util.stream.Stream;

public interface Extractor {

  public TernSchema describeSchema();

  public Stream<RowsResult> extractData(List<TernColumn> columns);

}