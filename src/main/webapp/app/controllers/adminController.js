export default function AdminController($controller, $injector, $route, $scope, appConfig) {
  
  appConfig;
  angular.extend(this, $controller('CoreAdminController', {$scope: $scope}));

}
