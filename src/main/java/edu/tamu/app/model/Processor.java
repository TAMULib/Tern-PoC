package edu.tamu.app.model;

import java.util.Map;
import java.util.stream.Stream;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import com.fasterxml.jackson.databind.JsonNode;

public interface Processor {

  public Stream<JsonNode> process(Stream<RowsResult> rowsResults, JsonNode outBoundData, ScriptEngine engine) throws ScriptException;

}