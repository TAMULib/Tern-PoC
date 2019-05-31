package edu.tamu.app.model;

import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DirectProcessor implements Processor {

  private String name;

  private ProcessorType type;

  private String logic;
  
  public DirectProcessor() {
    this.name = "Direct Processor";
    this.type = ProcessorType.JS;
    StringBuilder logicBuilder = new StringBuilder();
    logicBuilder.append("var directProcessorLogic = function(rowsResultString, mappedValuesString) {");
    logicBuilder.append("  var rowsResult = JSON.parse(rowsResultString);");
    logicBuilder.append("  var mappedValues = JSON.parse(mappedValuesString);");
    logicBuilder.append("  var rowKeys = Object.keys(rowsResult);");
    logicBuilder.append("  var mappedValuesFirstKey = Object.keys(mappedValues)[0];");
    logicBuilder.append("  mappedValues[mappedValuesFirstKey] = rowsResult[rowKeys[0]];");
    logicBuilder.append("  return mappedValues;");
    logicBuilder.append("}");
    this.logic = logicBuilder.toString();
  }

  @Override
  public JsonNode process(RowsResult rowsResult, JsonNode mappedValues, Invocable invocable)
      throws ScriptException {
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode outboundValue;
    try {
      String rowsJson = objectMapper.writeValueAsString(rowsResult.getRows());
      Map<String, String> resultMap = (Map<String, String>) invocable.invokeFunction("directProcessorLogic", rowsJson, mappedValues.toString());
      outboundValue = objectMapper.valueToTree(resultMap);
    } catch (NoSuchMethodException | ScriptException | JsonProcessingException e) {
      e.printStackTrace();
      outboundValue = mappedValues;
    }
    return outboundValue;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public ProcessorType getType() {
    return type;
  }

  @Override
  public String getLogic() {
    return logic;
  }
}