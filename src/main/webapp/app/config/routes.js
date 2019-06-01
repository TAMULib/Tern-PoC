
export default function routes($routeProvider) {	
  $routeProvider.
    when('/register', {
      templateUrl: 'bower_components/core/app/views/register.html'
    }).
    when('/users', {
      templateUrl: 'views/users.html',
      access: ["ROLE_ADMIN", "ROLE_MANGER"]
    }).
    when('/dashboard', {
      redirectTo: '/dashboard/processor-management'
    }).
    when('/dashboard/:tab', {
      templateUrl:  'views/management/dashboard.html',
      reloadOnSearch: false
    }).
    when('/dashboard/processor-management', {
      templateUrl: 'views/management/dashboard.html',
      reloadOnSearch: false
    }).
    when('/home', {
      redirectTo: '/'
    }).
    when('/', {
      templateUrl: 'views/home.html'
    }).


  // Error Routes
    when('/error/403', {
      templateUrl: 'views/errors/403.html',
      controller: 'ErrorPageController'
    }).
    when('/error/404', {
      templateUrl: 'views/errors/404.html',
      controller: 'ErrorPageController'

    }).
    when('/error/500', {
      templateUrl: 'views/errors/500.html',
      controller: 'ErrorPageController'
    }).
    otherwise({
      redirectTo: '/error/404'
    });
}
