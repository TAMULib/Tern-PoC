package edu.tamu.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.fasterxml.jackson.databind.JsonNode;

import javax.script.Invocable;
import javax.script.ScriptEngine;

import org.springframework.stereotype.Service;

import edu.tamu.app.model.DirectProcessor;
import edu.tamu.app.model.Processor;
import edu.tamu.app.model.ProcessorType;
import edu.tamu.app.model.RowsResult;

@Service
public class ProcessRunnerService {

  private Map<String, Processor> processors;

  private ScriptEngineManager scriptEngineManager;

  private Map<ProcessorType, ScriptEngine> scriptEngines;

  public ProcessRunnerService() throws ScriptException {
    processors = new HashMap<String, Processor>();
    configureScriptEngines();
    preLoadProcessors();
  }

  private void configureScriptEngines() {
    scriptEngineManager = new ScriptEngineManager();
    scriptEngines = new HashMap<ProcessorType, ScriptEngine>();
    scriptEngines.put(ProcessorType.JS, scriptEngineManager.getEngineByExtension("js"));
  }

  private void preLoadProcessors() throws ScriptException {
    DirectProcessor directProcessor = new DirectProcessor();
    registerProcessor(directProcessor);
    processors.put(directProcessor.getName(), directProcessor);
  }

  public void registerProcessor(Processor processor) throws ScriptException {
    scriptEngines
      .get(processor.getType())
      .eval(processor.getLogicFunction());
  }

  public JsonNode runProcessor(RowsResult rowsResults, JsonNode outBoundData, Processor processor)
      throws ScriptException {
    Invocable invocable = (Invocable) scriptEngines.get(processor.getType());
    return processor.process(rowsResults, outBoundData, invocable);
  }

  public List<Processor> getAllDefaultProcessors() {
    return new ArrayList<Processor>(processors.values());
  }

}