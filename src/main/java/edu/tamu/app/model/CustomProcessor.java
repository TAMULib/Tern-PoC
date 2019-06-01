package edu.tamu.app.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.script.Invocable;
import javax.script.ScriptException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.tamu.app.model.validation.CustomProcessorValidator;
import edu.tamu.weaver.validation.model.ValidatingBaseEntity;

@Entity
public class CustomProcessor extends ValidatingBaseEntity implements Processor {

  @Column(unique = true)
  private String name;

  @Column
  @Enumerated(EnumType.STRING)
  private ProcessorType type;
  
  @Column(length=1000)
  private String logic;
  
  public CustomProcessor() {
    setModelValidator(new CustomProcessorValidator());
  }

  @Override
  public JsonNode process(RowsResult rowsResult, JsonNode mappedValues, Invocable invocable)
      throws ScriptException {
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode outboundValue;
    try {
      String rowsJson = objectMapper.writeValueAsString(rowsResult.getRows());
      Map<String, String> resultMap = (Map<String, String>) invocable.invokeFunction(getLogicFunctionName(), rowsJson, mappedValues.toString());
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

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public ProcessorType getType() {
    return type;
  }

  public void setType(ProcessorType type) {
    this.type = type;
  }

  @Override
  public String getLogic() {
    return logic;
  }

  public void setLogic(String logic) {
    this.logic = logic;
  }
  
}