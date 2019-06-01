export default function ProcessorRepo(WsApi, api) {

  var processorRepo = this;

  processorRepo.getScaffold = function() {
    return {
      "name": "Custom Processor",
      "type": "JS",
      "logic": "  var rowsResult = JSON.parse(rowsResultString);  var mappedValues = JSON.parse(mappedValuesString);  var rowKeys = Object.keys(rowsResult);  var mappedValuesFirstKey = Object.keys(mappedValues)[0];  mappedValues[mappedValuesFirstKey] = rowsResult[rowKeys[0]];"
    };
  };

  processorRepo.runTest = function(processor) {
    return WsApi.fetch(api.Processor.test, {
      data: processor
    });
  };

  return processorRepo;

}