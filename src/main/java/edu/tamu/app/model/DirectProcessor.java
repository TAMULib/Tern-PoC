package edu.tamu.app.model;

import java.util.Map;
import java.util.stream.Stream;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import com.fasterxml.jackson.databind.JsonNode;

public class DirectProcessor implements Processor {
  @Override
  public Stream<Map<String, String>> process(Stream<RowsResult> rowsResults, Map<String, String> mappedValues, ScriptEngine scriptEngine) {

    StringBuilder logic = new StringBuilder();

    logic.append("var firstKey = Object.keys(mappedValues)[0];");
    logic.append("var firstValue = Object.keys(rowsResult)[0];");
    logic.append("mappedValues[firstKey] = firstValue;");
    logic.append("mappedValues;");

    return rowsResults.map(rowsResult -> {
      scriptEngine.put("rowsResult", rowsResult);
      scriptEngine.put("mappedValues", mappedValues);
      Map<String, String> outboundValue;
      try {
        outboundValue = (Map<String, String>) scriptEngine.eval(logic.toString());
      } catch (ScriptException e) {
        e.printStackTrace();
        outboundValue = mappedValues;
      }
      return outboundValue;
    });
  }

}