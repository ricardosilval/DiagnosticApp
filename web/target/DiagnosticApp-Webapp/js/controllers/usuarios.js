angular.module('DiagnosticApp').controller('UsuariosCtrl', ['$rootScope', '$scope', 'settings', 'portalUtil', '$sce', function ($rootScope, $scope, settings, portalUtil, $sce) {
        $scope.$on('$viewContentLoaded', function () {
            // initialize core components
            App.initAjax();
            // set default layout mode
            $rootScope.settings.layout.pageContentWhite = true;
            $rootScope.settings.layout.pageBodySolid = false;
            $rootScope.settings.layout.pageSidebarClosed = false;
            $rootScope.settings.layout.isPublic = false;
            $rootScope.settings.layout.isPrivate = true;



            $scope.optionsRoles = [];
            $scope.empresas = [];
            $scope.sucursalesOptions = [];
            $scope.allSucursalesOptions = [];

            $scope.loadRoles();
            $scope.loadEmpresas();
            $scope.loadSucursales();

            $scope.listar();



        });
  
    $scope.paginaActual = 1;
    
        $scope.crearUsuarioModal = false;
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

        $scope.currentUsuario = "";
        $scope.optionsEstado = [{"value": 1, "text": "Activos"}, {"value": 2, "text": "Inactivos"}];


        //Inicializa filters
        $scope.filtroNombre = "";
        $scope.filtroApellido = "";
        $scope.filtroEmail = "";
        $scope.filtroEmpresa = "";
        $scope.filtroSucursales = {
            "sucursales" : []
        };
        $scope.filtroEstado = "";
        $scope.filtroRut = [];
        $scope.filtroRol = "";

//Get de sucursales

        $scope.clearFilter = function () {
            $scope.filtroNombre = "";
            $scope.filtroApellido = "";
            $scope.filtroEmail = "";
            $scope.filtroEmpresa = "";
            $scope.filtroSucursales.sucursales = [];
            $scope.filtroEstado = "";
            $scope.filtroRut = [];
            $scope.filtroRol = "";
            $scope.listar();
        };
        $scope.enableActions = function (id) {
            $scope.permiteAcciones = true;
            // console.log("ID Seleccionado: " + id + "(" + $scope.permiteAcciones + ")");
            $scope.currentUsuario = id;
        };

        var api = portalUtil.getApi();

        $scope.loadRoles = function () {
            api.one('roles').get().then(function (data) {
                console.log(data.roles, "ROLES??");
                $scope.optionsRoles = data.roles;
            });
        };

        $scope.loadEmpresas = function () {
            api.one('empresas').get().then(function (data) {
                console.log(data.empresas, "ADUANAS GESTORAS??");
                $scope.empresas = data.empresas;
            });
        };

        $scope.loadSucursales = function () {
            console.log("Carga Sucursales");
            $scope.filtroSucursales.sucursales = [];
            var filterSucursal = {
            };
            if ($scope.currentEmpresaRut !== "") {
                filterSucursal['empresaId'] = $scope.filtroEmpresa;
                api.one('sucursales').get(filterSucursal).then(function (data) {

                    $scope.sucursalesOptions = data.sucursales;
                    console.log($scope.sucursalesOptions, "SUCURSALES DISPONIBLES");
                });
            }
            console.log("Carga Sucursales PARA MODAL");
            api.one('sucursales').get().then(function (data) {

                $scope.allSucursalesOptions = data.sucursales;
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
            if ($scope.filtroEmpresa !== "") {
                console.log($scope.filtroEmpresa, "FILTERRIER EMPRESA");
                filterrier['aduanaGestora'] = $scope.filtroEmpresa;
            }

            if ($scope.filtroSucursales.sucursales[0] !== "") {
                filterrier['aduanaLoteadora'] = $scope.filtroSucursales.sucursales;
            }

            if ($scope.filtroEstado !== "") {
                filterrier['estado'] = $scope.filtroEstado;
            }
            if ($scope.filtroRut.length > 0) {
                filterrier['rut'] = $scope.filtroRut;
            }
            if ($scope.filtroRol !== "") {
                filterrier['rol'] = $scope.filtroRol;
            }
            if ($scope.filtroEstado !== "") {
                filterrier['habilitado'] = $scope.filtroEstado;
            }
          
            // var api = portalUtil.getApi();

// $scope.totales  =0;
            api.one('usuarios').get(filterrier).then(function (data) {


                $.each(
                        data.usuarios,
                        function (i, o) {
                            var ls = '';
                            $.each(o.sucursales, function (j, s) {

                                ls += '<span class="badge badge-info">' + s.nombre + ' (' + s.codigo + ')</span>';
                            });

                            data.usuarios[i]["htmlSucursal"] = ls;
                        });
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
                    $scope.crearUsuarioModal = false;
                }, saveError);
            } else {

                $scope.usuario.put().then(function () {
                    $scope.listar();
                    $scope.crearUsuarioModal = false;
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

        $scope.createUsuario = function () {

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
            $scope.currentUsuario = "";
            $scope.crearUsuarioModal = true;

        };

        $scope.modificarUsuario = function () {
            //Llamada  a buscar USUARIO y setearlo en $scope.usuario
            api.one('usuarios', $scope.currentUsuario).get().then(function (data) {
                console.log(data, "EL USUARIO A MODIFICAR");
                $scope.usuario = data;
                $scope.usuario.roles = portalUtil.objectToIdArray(data.roles);
                $scope.usuario.sucursales = portalUtil.objectToIdArray(data.sucursales);

            });
            $scope.crearUsuarioModal = true;

        };



    }]);
