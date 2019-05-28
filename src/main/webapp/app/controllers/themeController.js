export default function ThemeController($controller, $scope, $window, ThemeRepo) {
	
  angular.extend(this, $controller('AbstractController', {$scope: $scope}));

  $scope.themes = ThemeRepo.getAll();

  $scope.refreshable = false;

  var reset = function() {
    $scope.newTheme = {
      active: false,
      name: ''
    };
  };

  reset();

  $scope.updateThemeProperty = function(theme,propertyId,value) {
    ThemeRepo.updateThemeProperty(theme.id, propertyId, value).then(function() {
      if (theme.active == true) {
        $scope.refreshable = true;
      }
    });
  };

  $scope.addTheme = function() {
    ThemeRepo.create($scope.newTheme).then(function() {
      reset();
    });
  };

  $scope.removeTheme = function(theme) {
    theme.delete().then(function() {
      reset();
    });
  };

  $scope.activateTheme = function(themeId) {
    ThemeRepo.activateTheme(themeId).then(function() {
      $scope.refreshPage();
    });
  };

  $scope.refreshPage = function() {
    $window.location.reload();
  };
}
