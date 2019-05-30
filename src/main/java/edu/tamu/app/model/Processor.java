package edu.tamu.app.model;

import java.util.Map;
import java.util.stream.Stream;

import javax.script.ScriptEngine;

import com.fasterxml.jackson.databind.JsonNode;

public interface Processor {

  public Stream<Map<String, String>> process(Stream<RowsResult> rowsResults, Map<String, String> outBoundData, ScriptEngine engine);

}