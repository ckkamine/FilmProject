var app= angular.module('myApp', [ 'ui.router', 'ngCookies',
                           		'ui.bootstrap' ]);
var isConnected= false;

app.config(['$stateProvider', '$locationProvider', '$httpProvider','$urlRouterProvider',
function($stateProvider, $locationProvider, $httpProvider, $urlRouterProvider) {
	
	$stateProvider.state('login', {
		url : '/login',
		templateUrl : 'partials/login.html',
		controller : loginCtrl,
		resolve : {
			connection : checkIsNonConnected
		}
	});
	$stateProvider.state('nous', {
		url : '/about',
		templateUrl : 'partials/nous.html',
		controller : nousCtrl
	});
	$stateProvider.state('projections', {
		url : '/projections',
		templateUrl : 'partials/projections.html',
		controller : projectionsCtrl
	});
	$stateProvider.state('reservations', {
		url : '/reservations',
		templateUrl : 'partials/reservations.html',
		controller : reservationsCtrl,
		resolve:{
			connection: checkIsClient
		}
	});
	$httpProvider.interceptors
	.push(function($q, $rootScope, $location,
			$cookieStore) {
		return {
			'request' : function(config) {
				var isRestCall = config.url
						.indexOf('rest') == 0;
				if (isRestCall
						&& angular
								.isDefined($cookieStore
										.get('token'))) {
					var authToken = $cookieStore.get('token');
					config.headers['Authorization'] = 'Bearer '+authToken;
				}
				return config
						|| $q.when(config);
			}
		};
	});
}]);

app.run(
		function($rootScope, $location, $cookieStore, $http, $state) {
			$rootScope.logout = function() {
				delete $rootScope.user;
				$cookieStore.remove('user');
				$cookieStore.remove('token');
				$cookieStore.remove('expires');
				isConnected = false;
				$location.url("/login");
			};
			$rootScope.redirectRole = function() {
				switch ($rootScope.user.role) {
				case 'GERANT':
					$location.url('/gerant');
					break;
				case 'ADMINISTRATEUR':
					$location.url('/admin');
					break;
				case 'PRODUCTEUR':
					$location.url('/producteur');
					break;
				default:
					$location.url('/projections');
				};

			};
			var token = $cookieStore.get('token');
			var user = $cookieStore.get('user');
			var expires = $cookieStore.get('expires');
			if (token !== undefined && user !== undefined && expires !== undefined) {
				if(new Date().getTime() < expires){
					$rootScope.user=user;
					isConnected= true;
					$rootScope.redirectRole();
				}else{
					$rootScope.logout();
				}
				
			} else {
				$rootScope.logout();
			}
			$rootScope.isGerant=function(){
				if(angular.isDefined($rootScope.user)){
					if($rootScope.user.role=='GERANT'){
						return true;
					}else{
						return false;
					}
				}else{
					return false;
				}
			}
			
			$rootScope.isClient=function(){
				if(angular.isDefined($rootScope.user)){
					if($rootScope.user.role=='USER'){
						return true;
					}else{
						return false;
					}
				}else{
					return false;
				}
			}
			/* Reset error when a new view is loaded 
			$rootScope.$on('$viewContentLoaded', function() {
				delete $rootScope.error;
			});*/
			
			
			
			$rootScope.isConnected=function(){
				if(angular.isDefined($rootScope.user)){
					return true;
				}else{
					return false;
				}
			}
			

			

		});



function loginCtrl($scope, $http, $cookieStore, $rootScope){
	var section = angular.element(document.querySelectorAll('.section'));
	var login = angular.element(document.querySelector('#login'));
	section.removeClass("active");
	login.addClass("active");
	$scope.login= function(){
		$http({
			method : 'POST',
				url : 'oauth/token',
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded',
					'Authorization' : 'Basic V2ViQ2xpZW50OnNlY3JldA=='
				},
				data : serializeData({
					username : $scope.username,
					password : $scope.password,
					grant_type : 'password'
				})
		}).then(function(response){
			$cookieStore.put('token',response.data.access_token);
			$cookieStore.put('expires', new Date().getTime()+response.data.expires_in*1000);
			$http({
				method : 'GET',
					url : 'rest/user',
					headers : {
						'Content-Type' : 'application/x-www-form-urlencoded'
					},
					params :{
						'username' : $scope.username
					}
			}).then(function(response) {
				$scope.username='';
				$scope.password='';
				$rootScope.user=response.data;
				$cookieStore.put('user', $rootScope.user);
				isConnected= true;
				$rootScope.redirectRole();
			})
		});
	}
}
function nousCtrl(){
	var section = angular.element(document.querySelectorAll('.section'));
	var nous = angular.element(document.querySelector('#a_propos'));
	section.removeClass("active");
	nous.addClass("active");
}
function projectionsCtrl($scope, $http, $rootScope){
	var section = angular.element(document.querySelectorAll('.section'));
	var projections = angular.element(document.querySelector('#projections'));
	section.removeClass("active");
	projections.addClass("active");
	$scope.today = function() {
		$scope.finInter = new Date();
	};
	$scope.today();

	$scope.clear = function() {
		$scope.debutInter = null;
		$scope.finInter = null;
	};
	$scope.minDate = new Date();

	//$scope.maxDate = new Date();

	$scope.open = function($event) {
		$scope.status.opened = true;
	};
	$scope.setDate = function(year, month, day) {
		$scope.dateProjection = new Date(year, month, day);
	};
	
	$scope.status = {
		opened : false
	};
	$http({
		method : 'GET',
			url : 'rest/client/salles'
	}).then(function(response) {
		$scope.salles=response.data;
	});
	$scope.chargerProjections= function(){
		$http({
			method : 'GET',
				url : 'rest/client/projections',
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				},
				params :{
					'idSalle': $scope.idSalle,
					'date': $scope.dateProjection
				}
		}).then(function(response) {
			$scope.dateCurrent= $scope.dateProjection;
			$scope.projections= response.data;
		});
	};
	$scope.dateProjection= new Date($scope.minDate.getFullYear(), $scope.minDate.getMonth(), $scope.minDate.getDate());
	$scope.idSalle= 1;
	$scope.chargerProjections();
	$scope.reserver= function(projection){
		$scope.reservation= {
				'projection': projection, 'client': $rootScope.user, 'dateReservation': new Date()
		};
		$http({
			method : 'POST',
				url : 'rest/client/reserver',
				headers : {
					'Content-Type' : 'application/json',
					'Authorization' : 'Basic V2ViQ2xpZW50OnNlY3JldA=='
				},
				data : $scope.reservation
		}).then(function(response){
			
		});
	}
	$scope.isLate=function(p){
		if(p.date > new Date().getTime()){
			return false;
		}else{
			return true;
		}
	}
}
function reservationsCtrl($http, $scope, $rootScope){
	var section = angular.element(document.querySelectorAll('.section'));
	var reservations = angular.element(document.querySelector('#reservations'));
	section.removeClass("active");
	reservations.addClass("active");
	$scope.chargerReservations= function(){
		$http({
			method : 'GET',
				url : 'rest/client/reservations',
				headers : {
					'Content-Type' : 'application/json',
					'Authorization' : 'Basic V2ViQ2xpZW50OnNlY3JldA=='
				},
				params : {
					'page': 0, 'idClient': $rootScope.user.idUtilisateur
				}
		}).then(function(response){
			$scope.reservations= response.data;
		});
	}
	$scope.chargerReservations();
	
	$scope.annuler=function(idReservation){
		$http({
			method : 'PUT',
				url : 'rest/client/annunuler',
				headers : {
					'Content-Type' : 'application/json',
					'Authorization' : 'Basic V2ViQ2xpZW50OnNlY3JldA=='
				},
				params : {
					'idReservation': idReservation
				}
		}).then(function(response){
			$scope.chargerReservations();
		});
	}
}
