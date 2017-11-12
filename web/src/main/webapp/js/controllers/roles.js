angular.module('DiagnosticApp').controller('RolesCtrl', ['$rootScope', '$scope', 'settings', 'portalUtil', '$sce', function ($rootScope, $scope, settings, portalUtil, $sce) {
        $scope.$on('$viewContentLoaded', function () {
            // initialize core components
            App.initAjax();
            // set default layout mode
            $rootScope.settings.layout.pageContentWhite = true;
            $rootScope.settings.layout.pageBodySolid = false;
            $rootScope.settings.layout.pageSidebarClosed = false;
            $rootScope.settings.layout.isPublic = false;
            $rootScope.settings.layout.isPrivate = true;


            /*
            $scope.optionsRoles = [];
            $scope.empresas = [];
            $scope.sucursalesOptions = [];
            $scope.allSucursalesOptions = [];

            $scope.loadRoles();
            $scope.loadEmpresas();
            $scope.loadSucursales();
            */
            $scope.loadPermisos();
            $scope.clearFilter();
            



        });
  
    $scope.paginaActual = 1;
    
        $scope.crearRolModal = false;
        
        $scope.rol = {
            nombre: "",
            codigo: "",
            descripcion: "",
            permisos: []     
        };

        $scope.currentRol = "";
        
       
        $scope.clearFilter = function () {
            $scope.filtroNombre = "";
            $scope.filtroDescripcion= "";
            $scope.filtroCodigo = "";
          $scope.filtroPermisos = {
            "permisos" : []
        };
            $scope.listar();
        };
        $scope.enableActions = function (id) {
            $scope.permiteAcciones = true;
            // console.log("ID Seleccionado: " + id + "(" + $scope.permiteAcciones + ")");
            $scope.currentRol = id;
        };

        var api = portalUtil.getApi();

        $scope.loadPermisos = function () {
            api.one('permisos').get().then(function (data) {
                console.log(data.permisos, "DATA ERMISPS");
                $scope.optionsPermisos = data.permisos;
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
            if ($scope.filtroDescripcion !== "") {
                filterrier['descripcion'] = $scope.filtroDescripcion;
            }
         if ($scope.filtroCodigo !== "") {
                filterrier['codigo'] = $scope.filtroCodigo;
            }
        console.log($scope.filtroPermisos.permisos, "FP");
           if($scope.filtroPermisos.permisos[0] !== ''){
                filterrier['permiso'] = $scope.filtroPermisos.permisos;
           }
            api.one('roles').get(filterrier).then(function (data) {


                $scope.roles = data.roles;
                $scope.paginaActual = data.paginaActual;
                $scope.totales = data.total;
             
            });


        };

        $scope.save = function (form) {
            console.log($scope.rol, "EL ROL");

            $scope.rolError = [];
            portalUtil.validateForm(form, $scope.rolError);
            if ($scope.rolError.length)
                return;
            if ($scope.rol.id === "") {
                
            } else {

                $scope.rol.put().then(function () {
                    $scope.listar();
                    $scope.crearRolModal = false;
                }, saveError);
            }

        };

        var saveError = function (error) {
            if (error.data && error.data.message)
                $scope.rolError.push(error.data.message);
            else
                $scope.rolError.push("Error guardando rol.");
            console.log("Error al guardar");
        };

        $scope.createRol = function () {

            $scope.rol = {
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
            $scope.currentRol = "";
            $scope.crearRolModal = true;

        };

        $scope.modificarRol = function () {
            //Llamada  a buscar USUARIO y setearlo en $scope.rol
            api.one('roles', $scope.currentRol).get().then(function (data) {
               
                $scope.rol = data;
                $scope.rol.permisos = portalUtil.objectToIdArray(data.permisos);
               
            });
            $scope.crearRolModal = true;

        };



    }]);
