var app = angular.module('Application', ['ngRoute']);

app.config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/', {templateUrl: 'pages/lists.html', controller: 'ReportListControler'}).
      when('/NewReport', {templateUrl: 'pages/new.html', controller: 'ReportAddControler'}).
      when('/UpdateReport/:id', {templateUrl: 'pages/edit.html', controller: 'ReportEditControler'}).
      otherwise({redirectTo: '/'});
}]);

app.controller ('ReportListControler',[
  '$scope','$http',
  function ($scope, $http) {
      $http.get('api/Reports').success(function(data) {
        $scope.reports = data;  
      });
  }    
]),

app.controller ('ReportAddControler',[
  '$scope','$http','$location',
  function ($scope, $http, $location) {
  
      $scope.master = {};
      $scope.activePath = null;

      $scope.New_Report = function(report, AddNewForm) {
          console.log(report);

          $http.post('api/New_Report', report).success(function(){
              $scope.reset();
              $scope.activePath = $location.path('/');
          });

          $scope.reset = function() {
              $scope.report = angular.copy($scope.master);
          };

          $scope.reset();
      };

  }
]),

app.controller('ReportEditControler',[

  '$scope','$http','$location','$routeParams',
  function ($scope, $http, $location, $routeParams) {

      var id = $routeParams.id;
      $scope.activePath = null;

      $http.get('api/Reports/'+id).success(function(data) {
        $scope.ReportDetail = data;
      });

      $scope.Update_Report = function(report) {
          $http.put('api/Reports/'+id, report).success(function(data) {
          $scope.ReportDetail = data;
          $scope.activePath = $location.path('/');
        });

      };

      $scope.Delete_Report = function(report) {
          console.log(report);
          var deleteReport = confirm('Você tem certeza que deseja deletar está denúncia?');
          if (deleteReport) {
              $http.delete('api/Reports/'+report.id);
              $scope.activePath = $location.path('/');
          }        
      };

  }
]);