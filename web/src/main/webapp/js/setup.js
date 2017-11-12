angular.module('DiagnosticApp')
.run(['$rootScope', 'auth', 'session', function ($rootScope, auth, session) {
  $rootScope.auth = auth;
  $rootScope.session = session;
}])
.run(['$rootScope', 'auth', '$state', function ($rootScope, auth, $state) {
  // Listen for state changes when using ui-router
  $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams){

    // Prevent endless loop
    if(toState.name === 'public.auth' ){
      return;
    }

    if(!auth.isLoggedIn()){
      // Redirect to login
      $state.go('public.auth');
      // Prevent state change
      event.preventDefault();
    }
  });
}]);
