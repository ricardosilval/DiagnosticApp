angular.module('DiagnosticApp').controller('AuthCtrl', ['$rootScope', '$scope', 'settings', 'auth', '$state', 'portalUtil', 'session',
    function ($rootScope, $scope, settings, auth, $state, portalUtil, session) {
        $scope.$on('$viewContentLoaded', function () {
            // initialize core components
            App.initAjax();

            // set default layout mode
            $rootScope.settings.layout.pageContentWhite = false;
            $rootScope.settings.layout.pageBodySolid = false;
            $rootScope.settings.layout.pageSidebarClosed = false;
            $rootScope.settings.layout.isPublic = true;
            $rootScope.settings.layout.isPrivate = false;


            //Control
            $scope.loginForm = true;
            $scope.recoveryPass = false;
            $scope.changePass = false;
            $scope.loadingAuth = false;
            $scope.loadingAuthMsg = "";
            $scope.recoveryEmail = "";
            $scope.firstLogin = false;
            $scope.resetPass = {
                "password": "",
                "rePassword": ""
            };

        });
        var mailRegex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
        var rutRegex = /^0*(\d{1,3}(\.?\d{3})*)\-?([\dkK])$/;
        $scope.user = {};
        $scope.authError = null;

        $scope.login = function () {

            $scope.authError = null;
            var credentials = {
                password: $scope.user.password
            };
            if (rutRegex.test($scope.user.username)) {
                credentials.rut = $scope.user.username;

            } else if (mailRegex.test($scope.user.username)) {

                credentials.mail = $scope.user.username;
            } else {
                $scope.authError = "Favor ingresar con RUT o email válido";
                return;
            }
            // Try to login
            auth.logIn(credentials)
                    .then(function (response) {
                        console.log(response, "LOGIN RESPONSE");
                        if (response.status !== 200) {
                            $scope.authError = 'Error de conexión: ' + response.statusText;
                        } else if (response.status === 200 && !response.data.token) {
                            $scope.authError = 'Nombre de usuario o contraseña incorrectos.';
                        } else if (response.status === 206 && !response.data.token) {
                            $scope.authError = response.message;
                        } 
                        
                        
                        if (response.cambioPassword) {
                            console.log(response.cambioPassword, "CP");
                            $scope.changePass = true;

                        } else {
                            
                            $state.go('private.dashboard');
                        }
                    },
                            function (error) {
                                $scope.authError = error;
                            });
            
           // $state.go('private.dashboard');
        };

        $scope.passwordRecovery = function () {
            console.log("Passowrdrecovery");
            var email = {"email": $scope.recoveryEmail};
            var api = portalUtil.getLoginApi();
            api.all('auth/recovery').post(email).then(
                    function (response) {
                        console.log("Envió", response);
                        $scope.recoveryPass = false;
                        $scope.loadingAuth = true;
                        $scope.loadingAuthMsg = response.data.message;
                    },
                    function (error) {
                        console.log("Error", error);
                        /** do some other thing **/
                        $scope.recoveryPass = false;
                        $scope.loadingAuth = true;
                        $scope.authError = error.data.message;

                    }, "");


        };

        $scope.resetPassword = function () {
            console.log("Passowrdrecovery");

            console.log($scope.resetPass, "VAA");
            var api = portalUtil.getApi();
            // api.all('subastas/' + $scope.currentSubasta).customPUT({
            api.all('usuarios/' + session.getUser().id).customPUT($scope.resetPass).then(
                    function (response) {
                        console.log("Envió", response);
                        $scope.changePass = false;
                        $scope.loadingAuth = true;
                        $scope.loadingAuthMsg = response.message;
                    },
                    function (error) {
                        console.log("Error", error);
                        /** do some other thing **/

                        $scope.recoveryPass = true;
                        $scope.loadingAuth = true;
                        $scope.loadingAuthMsg = error.message;

                    }, null);


        };
    }]);

