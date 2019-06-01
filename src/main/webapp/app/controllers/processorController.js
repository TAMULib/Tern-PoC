export default function ProcessorController($controller, $scope, $timeout, NgTableParams, ProcessorRepo) {

  angular.extend(this, $controller('AbstractController', {$scope: $scope}));

  $scope.processors = ProcessorRepo.getAll();

  $scope.processorToCreate = ProcessorRepo.getScaffold();
  $scope.processorToUpdate = ProcessorRepo.getScaffold();
  $scope.processorToDelete = {};

  $scope.processorForms = {
    validations: ProcessorRepo.getValidations(),
    getResults: ProcessorRepo.getValidationResults
  };

  $scope.submitClicked = false;

  $scope.resetCreateProcessorPropertyList = true;

  $scope.resetProcessorForms = function() {
    ProcessorRepo.clearValidationResults();
    for (var key in $scope.processorForms) {
      if ($scope.processorForms[key] !== undefined && !$scope.processorForms[key].$pristine && $scope.processorForms[key].$setPristine) {
        $scope.processorForms[key].$setPristine();
      }
    }
    $scope.closeModal();
  };

  $scope.resetProcessorForms();

  $scope.createProcessor = function() {
    $scope.submitClicked = true;
    ProcessorRepo.create($scope.processorToCreate).then(function(res) {
      if(angular.fromJson(res.body).meta.status === "SUCCESS") {
        $scope.cancelCreateProcessor();
      }
      $scope.submitClicked = false;
    });
  };

  $scope.cancelCreateProcessor = function() {
    angular.extend($scope.processorToCreate, ProcessorRepo.getScaffold());
    $scope.resetProcessorForms();
    $scope.resetCreateProcessorPropertyList = false;
    $timeout(function(){
      $scope.resetCreateProcessorPropertyList = true;
    });
  };

  $scope.beginUpdateProcessor = function(processor) {
    $scope.processorToUpdate = processor;
    $scope.openModal('#updateProcessorModal');
  };

  $scope.updateProcessor = function() {
    $scope.submitClicked = true;
    $scope.processorToUpdate.save().then(function() {
      $scope.cancelUpdateProcessor();
      $scope.submitClicked = false;
    });
  };

  $scope.cancelUpdateProcessor = function() {
    $scope.processorToUpdate.refresh();
    $scope.processorToUpdate = ProcessorRepo.getScaffold();
    $scope.resetProcessorForms();
  };

  $scope.confirmDeleteProcessor = function(processor) {
    $scope.processorToDelete = processor;
    $scope.openModal('#deleteProcessorModal');
  };

  $scope.cancelDeleteProcessor = function() {
    $scope.processorToDelete = {};
    $scope.closeModal();
  };

  $scope.deleteProcessor = function() {
    $scope.submitClicked = true;
    ProcessorRepo.delete($scope.processorToDelete).then(function(res) {
      if(angular.fromJson(res.body).meta.status === "SUCCESS") {
        $scope.resetProcessorForms();
      }
      $scope.submitClicked = false;
    });
  };

  $scope.testProcessor = function(processor) {
    console.log(processor);
    ProcessorRepo.runTest(processor).then(function(res){
      console.log(res);
    });
  };

  ProcessorRepo.ready().then(function() {
    $scope.setTable = function () {
      $scope.tableParams = new NgTableParams({
        count: $scope.processors.length,
        sorting: {
          name: 'asc'
        }
      }, {
        counts: [],
        total: 0,
        getData: function () {
          return $scope.processors;
        }
      });
    };
    $scope.setTable();
  });


}
