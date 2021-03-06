package edu.tamu.app.model;

import javax.script.Invocable;
import javax.script.ScriptException;

import com.fasterxml.jackson.databind.JsonNode;

public interface Processor {

  public String getName();
  public ProcessorType getType();
  public String getLogic();

  public JsonNode process(RowsResult rowsResults, JsonNode outBoundData, Invocable invocable) throws ScriptException;


  default public String getLogicFunctionName() { 
    return getName().replaceAll("\\s","");
  }

  default public String getLogicFunction() {
    String logicTemplate = "var %s = function(rowsResultString, mappedValuesString) {%s return mappedValues; }";
   return String.format(logicTemplate, getLogicFunctionName(), getLogic());
  }

}