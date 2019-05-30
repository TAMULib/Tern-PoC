package edu.tamu.app.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static edu.tamu.weaver.response.ApiStatus.SUCCESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tamu.app.model.DirectProcessor;
import edu.tamu.app.model.RowsResult;
import edu.tamu.app.model.TernColumn;
import edu.tamu.weaver.response.ApiResponse;

import static java.util.stream.Collectors.*;

@RestController
@RequestMapping("/process")
public class ProcessController {

  @Autowired
  private ObjectMapper objectMapper;

  ScriptEngineManager manager = new ScriptEngineManager();

  @RequestMapping("/run")
  @PreAuthorize("hasRole('ANONYMOUS')")
  public ApiResponse run() throws JsonProcessingException, IOException, ScriptException {

    DirectProcessor dp = new DirectProcessor();

    RowsResult rowsResult = new RowsResult();
    Map<TernColumn, String> rows = new HashMap<TernColumn, String>();
    TernColumn ternColumn = TernColumn.of("foo", "bar", "biz");
    rows.put(ternColumn, "The Value");
    rowsResult.setRows(rows);
    ScriptEngine engine = manager.getEngineByExtension("js");
    Map<String, String> mappedValues = new HashMap<String,String>();
    mappedValues.put("fieldOne", "");

    List< Map<String, String>> results = dp.process(Stream.of(rowsResult), mappedValues, engine)
      .collect(toList());

    return new ApiResponse(SUCCESS, results);
  }

}