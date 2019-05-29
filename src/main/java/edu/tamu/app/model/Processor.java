package edu.tamu.app.model;

import java.util.stream.Stream;

import com.fasterxml.jackson.databind.JsonNode;

public interface Processor {

  public JsonNode process(Stream<RowsResult> rowsResults);

}