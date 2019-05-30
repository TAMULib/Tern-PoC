package edu.tamu.app.model;

import java.util.Map;
import java.util.stream.Stream;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DirectProcessor implements Processor {
  
  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public Stream<JsonNode> process(Stream<RowsResult> rowsResults, JsonNode mappedValues, ScriptEngine scriptEngine) throws ScriptException {

    StringBuilder logic = new StringBuilder();
    logic.append("var processorLogic = function(rowsResultString, mappedValues) {");
    logic.append("  var rowsResult = JSON.parse(rowsResultString);");

    logic.append("  ;");


    logic.append("  return rowsResult;");
    logic.append("}");

    scriptEngine.eval(logic.toString());
    Invocable invocable = (Invocable) scriptEngine;

    return rowsResults.map(rowsResult -> {
      JsonNode outboundValue;
      try {
        // String mappedValuesJson = objectMapper.writeValueAsString(mappedValues);
        Map<String,String> rowsResultJson = objectMapper.valueToTree(rowsResult.getRows());
        // System.out.println(mappedValuesJson);
        // System.out.println(rowsResultJson);
        outboundValue = (JsonNode) invocable.invokeFunction("processorLogic", rowsResultJson, mappedValues);
      } catch (NoSuchMethodException | ScriptException e) {
        e.printStackTrace();
        outboundValue = mappedValues;
      }
      return outboundValue;
    });
  }

}