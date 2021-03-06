angular.module('DiagnosticApp').controller('AlumnosCtrl', ['$rootScope', '$scope', 'settings', 'portalUtil', '$sce', function ($rootScope, $scope, settings, portalUtil, $sce) {
        $scope.$on('$viewContentLoaded', function () {
            // initialize core components
            App.initAjax();
            // set default layout mode
            $rootScope.settings.layout.pageContentWhite = true;
            $rootScope.settings.layout.pageBodySolid = false;
            $rootScope.settings.layout.pageSidebarClosed = false;
            $rootScope.settings.layout.isPublic = false;
            $rootScope.settings.layout.isPrivate = true;


            $scope.listar();



        });
    
    
    $scope.paginaActual = 1;
    
        $scope.crearAlumnoModal = false;
        $scope.usuario = {
            nombre: "",
            apellido: "",
            run: "",
            correo: "",
            sucursales: [],
            password: "",
            id: ""
        };

        $scope.currentAlumno = "";
        $scope.optionsEstado = [{"value": 1, "text": "Activos"}, {"value": 2, "text": "Inactivos"}];


        //Inicializa filters
        $scope.filtroNombre = "";
        $scope.filtroApellido = "";
        $scope.filtroEmail = "";
        $scope.filtroEstado = "";
        $scope.filtroRun = [];
        $scope.filtroRol = "";

//Get de sucursales
    $scope.holaMundo= "Hola Mundo";
    
    
        $scope.clearFilter = function () {
            $scope.filtroNombre = "";
            $scope.filtroApellido = "";
            $scope.filtroEmail = "";
            $scope.filtroEstado = "";
            $scope.filtroRun = [];
            $scope.filtroRol = "";
            $scope.listar();
        };
        $scope.enableActions = function (id) {
            $scope.permiteAcciones = true;
            // console.log("ID Seleccionado: " + id + "(" + $scope.permiteAcciones + ")");
            $scope.currentAlumno = id;
        };

        var api = portalUtil.getApi();

        $scope.loadRoles = function () {
            api.one('roles').get().then(function (data) {
                console.log(data.roles, "ROLES??");
                $scope.optionsRoles = data.roles;
            });
        };

        

        $scope.listar = function () {

            var filterrier = {
                pagina: $scope.paginaActual,
                filas: 15
            };
            if ($scope.filtroNombre !== "") {
                filterrier['nombre'] = $scope.filtroNombre;
            }
            if ($scope.filtroApellido !== "") {
                filterrier['apellido'] = $scope.filtroApellido;
            }

            if ($scope.filtroEmail !== "") {
                filterrier['correo'] = $scope.filtroEmail;
            }
            
            if ($scope.filtroEstado !== "") {
                filterrier['estado'] = $scope.filtroEstado;
            }
            if ($scope.filtroRun.length > 0) {
                filterrier['run'] = $scope.filtroRun;
            }
            if ($scope.filtroRol !== "") {
                filterrier['rol'] = $scope.filtroRol;
            }
          
            api.one('usuarios').get(filterrier).then(function (data) {
                $scope.usuarios = data.usuarios;
                $scope.paginaActual = data.paginaActual;
                $scope.totales = data.total;
            });

        };

        $scope.save = function (form) {
            console.log($scope.usuario, "EL USER");

            $scope.userError = [];
            portalUtil.validateForm(form, $scope.userError);
            if ($scope.userError.length)
                return;
            if ($scope.usuario.id === "") {
                api.all('usuarios').post($scope.usuario).then(function () {
                    $scope.listar();
                    $scope.crearAlumnoModal = false;
                }, saveError);
            } else {

                $scope.usuario.put().then(function () {
                    $scope.listar();
                    $scope.crearAlumnoModal = false;
                }, saveError);
            }

        };

        var saveError = function (error) {
            if (error.data && error.data.message)
                $scope.userError.push(error.data.message);
            else
                $scope.userError.push("Error guardando usuario.");
            console.log("Error al guardar");
        };

        $scope.createAlumno = function () {

            $scope.usuario = {
                nombre: "",
                apellido: "",
                username: "",
                rut: "",
                correo: "",
                cargo: "",
                roles: [],
                sucursales: [],
                password: "",
                id: ""
            };
            $scope.currentAlumno = "";
            $scope.crearAlumnoModal = true;

        };

        $scope.modificarAlumno = function () {
            //Llamada  a buscar USUARIO y setearlo en $scope.usuario
            api.one('usuarios', $scope.currentAlumno).get().then(function (data) {
                console.log(data, "EL USUARIO A MODIFICAR");
                $scope.usuario = data;
                //$scope.usuario.roles = portalUtil.objectToIdArray(data.roles);
            });
            $scope.crearAlumnoModal = true;

        };


    }]);
