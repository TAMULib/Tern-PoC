package edu.tamu.app.controller;

import static edu.tamu.weaver.response.ApiStatus.SUCCESS;
import static edu.tamu.weaver.validation.model.BusinessValidationType.CREATE;
import static edu.tamu.weaver.validation.model.BusinessValidationType.DELETE;
import static edu.tamu.weaver.validation.model.BusinessValidationType.UPDATE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptException;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tamu.app.model.CustomProcessor;
import edu.tamu.app.model.Processor;
import edu.tamu.app.model.RowsResult;
import edu.tamu.app.model.TernColumn;
import edu.tamu.app.model.repo.CustomProcessorRepo;
import edu.tamu.app.service.ProcessRunnerService;
import edu.tamu.weaver.response.ApiResponse;
import edu.tamu.weaver.validation.aspect.annotation.WeaverValidatedModel;
import edu.tamu.weaver.validation.aspect.annotation.WeaverValidation;

/**
 * User Controller
 * 
 */
@RestController
@RequestMapping("/processor")
public class ProcessorController {

    @Autowired
    CustomProcessorRepo customProcessorRepo;

    @Autowired
    ProcessRunnerService processRunnerService;

    @Autowired
    private ObjectMapper objectMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    @PreAuthorize("hasRole('ANONYMOUS')")
    public ApiResponse all() {
      List<CustomProcessor> customProcessors = customProcessorRepo.findAll();
      List<Processor> defaultProcessors = processRunnerService.getAllDefaultProcessors();
      List<Processor> allProcessors = new ArrayList<Processor>(defaultProcessors);
      allProcessors.addAll(customProcessors);
      return new ApiResponse(SUCCESS, allProcessors);
    }

    @GetMapping("/custom")
    @PreAuthorize("hasRole('ANONYMOUS')")
    public ApiResponse allCustom() {
      return new ApiResponse(SUCCESS, customProcessorRepo.findAll());
    }

    @PostMapping("/custom")
    @PreAuthorize("hasRole('ANONYMOUS')")
    @WeaverValidation(business = { @WeaverValidation.Business(value = CREATE) })
    public ApiResponse createCustom(@RequestBody @WeaverValidatedModel CustomProcessor customProcessor) {
      logger.info("Creating Custom Processor:  " + customProcessor.getName());
      return new ApiResponse(SUCCESS, customProcessorRepo.create(customProcessor));
    }

    @GetMapping("/custom/{id}")
    @PreAuthorize("hasRole('ANONYMOUS')")
    public ApiResponse readCustom(@PathVariable Long id) {
      //return new ApiResponse(SUCCESS, customProcessorRepo.read(id));
      return new ApiResponse(SUCCESS, new CustomProcessor());
    }

    @PutMapping("/custom")
    @PreAuthorize("hasRole('ANONYMOUS')")
    @WeaverValidation(business = { @WeaverValidation.Business(value = UPDATE) })
    public ApiResponse updateCustom(@RequestBody @WeaverValidatedModel CustomProcessor customProcessor) {
      logger.info("Updating Custom Processor:  " + customProcessor.getName());
      return new ApiResponse(SUCCESS, customProcessorRepo.update(customProcessor));
    }

    @DeleteMapping("/custom")
    @PreAuthorize("hasRole('ANONYMOUS')")
    @WeaverValidation(business = { @WeaverValidation.Business(value = DELETE) })
    public ApiResponse deleteCustom(@RequestBody @WeaverValidatedModel CustomProcessor customProcessor) {
      logger.info("Deleating schema:  " + customProcessor.getName());
      customProcessorRepo.delete(customProcessor);
      return new ApiResponse(SUCCESS);
    }

    @RequestMapping("/test-run")
    @PreAuthorize("hasRole('ANONYMOUS')")
    public ApiResponse testRun(@RequestBody Processor processor) throws IllegalArgumentException, ScriptException {
        
        RowsResult rowsResult = new RowsResult();
        Map<String, String> rows = new HashMap<String, String>();
        TernColumn ternColumn = TernColumn.of("foo", "bar", "biz");
        rows.put(ternColumn.getColumnName(), "The Value");
        rowsResult.setRows(rows);

        Map<String, String> mappedValues = new HashMap<String,String>();
        mappedValues.put("fieldOne", "");
        
        return new ApiResponse(SUCCESS, processRunnerService.runProcessor(rowsResult, objectMapper.valueToTree(mappedValues), processor));
    }

}
