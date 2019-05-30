package edu.tamu.app.model;

import java.util.Map;
import java.util.stream.Stream;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

public interface Processor {

  public Stream<Map<String, String>> process(Stream<RowsResult> rowsResults, Map<String, String> outBoundData, ScriptEngine engine) throws ScriptException;

}