package edu.tamu.app.model;

import java.util.Map;
import java.util.stream.Stream;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

public class DirectProcessor implements Processor {
  
  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public Stream<Map<String, String>> process(Stream<RowsResult> rowsResults, Map<String, String> mappedValues, ScriptEngine scriptEngine) throws ScriptException {

    StringBuilder logic = new StringBuilder();

    logic.append("var processorLogic = function(rowsResultString, mappedValuesString) {");
    logic.append("var rowsResult = JSON.parse(rowsResultString);");
    logic.append("var mappedValues = JSON.parse(mappedValuesString);");
    logic.append("var mappedValues = Object.keys(rowsResult)[0];");
    logic.append("var firstKey = Object.keys(mappedValues)[0];");
    logic.append("var firstValue = Object.keys(rowsResult)[0];");
    logic.append("mappedValues[firstKey] = firstValue;");
    logic.append("return mappedValues;");
    logic.append("}");
    scriptEngine.eval(logic.toString());
    Invocable invocable = (Invocable) scriptEngine;

    return rowsResults.map(rowsResult -> {
      Map<String, String> outboundValue;
      try {
        String mappedValuesJson = objectMapper.writeValueAsString(mappedValues);
        System.out.println(mappedValuesJson);
        outboundValue = (Map<String, String>) invocable.invokeFunction("processorLogic", rowsResult, mappedValuesJson);
      } catch (NoSuchMethodException | ScriptException | JsonProcessingException e) {
        e.printStackTrace();
        outboundValue = mappedValues;
      }
      return outboundValue;
    });
  }

}