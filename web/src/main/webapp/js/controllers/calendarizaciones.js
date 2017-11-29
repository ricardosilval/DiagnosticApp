angular.module('DiagnosticApp').controller('CalendarizacionesCtrl', ['$rootScope', '$scope', 'settings', 'portalUtil', '$sce', function ($rootScope, $scope, settings, portalUtil, $sce) {
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

    $scope.crearCalendarizacionModal = false;
    $scope.calendarzacion = {
        fechanInicio: "",
        fechaTermino: "",
        estado: 0,
        titulo: "",
        descripcion: "",
        id: ""
    };

    $scope.currentCalendarizacion = "";
    $scope.optionsEstado = [{
        "value": 1,
        "text": "Activos"
    }, {
        "value": 2,
        "text": "Inactivos"
    }];


    //Inicializa filters
    $scope.filtroFechaInicio = "";
    $scope.filtroFechaTermino = "";
    $scope.filtroEstado = 0;


    $scope.clearFilter = function () {
        $scope.filtroFechaInicio = "";
        $scope.filtroFechaTermino = "";
        $scope.filtroEstado = 0;

        $scope.listar();
    };
    $scope.enableActions = function (id) {
        $scope.habilitaAcciones = true;
        // console.log("ID Seleccionado: " + id + "(" + $scope.permiteAcciones + ")");
        $scope.currentCalendarizacion = id;
    };

    var api = portalUtil.getApi();

    $scope.listar = function () {

        /*
        Llamada API de listar calendarizaciones
        */
        
        /*
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
        if ($scope.filtroRut.length > 0) {
            filterrier['run'] = $scope.filtroRut;
        }
        if ($scope.filtroRol !== "") {
            filterrier['rol'] = $scope.filtroRol;
        }


        api.one('Calendarizacions').get(filterrier).then(function (data) {

            $scope.Calendarizacions = data.Calendarizacions;
            $scope.paginaActual = data.paginaActual;
            $scope.totales = data.total;

        });
        */
        
        


    };

    $scope.save = function (form) {
        /*
        console.log($scope.Calendarizacion, "EL USER");

        $scope.userError = [];
        portalUtil.validateForm(form, $scope.userError);
        if ($scope.userError.length)
            return;
        if ($scope.Calendarizacion.id === "") {
            api.all('Calendarizacions').post($scope.Calendarizacion).then(function () {
                $scope.listar();
                $scope.crearCalendarizacionModal = false;
            }, saveError);
        } else {

            $scope.Calendarizacion.put().then(function () {
                $scope.listar();
                $scope.crearCalendarizacionModal = false;
            }, saveError);
        }
        */

    };

    var saveError = function (error) {
        if (error.data && error.data.message)
            $scope.userError.push(error.data.message);
        else
            $scope.userError.push("Error guardando Calendarizacion.");
        console.log("Error al guardar");
    };

    $scope.createCalendarizacion = function () {

        $scope.calendarzacion = {
        fechanInicio: "",
        fechaTermino: "",
        estado: 0,
        titulo: "",
        descripcion: "",
        id: ""
    };
        $scope.currentCalendarizacion = "";
        $scope.crearCalendarizacionModal = true;

    };

    $scope.modificarCalendarizacion = function () {
        //Llamada  a buscar Calendarizacion y setearlo en $scope.Calendarizacion
        /*
        api.one('Calendarizacions', $scope.currentCalendarizacion).get().then(function (data) {
            console.log(data, "EL Calendarizacion A MODIFICAR");
            $scope.calendarizacion = data;
            //$scope.Calendarizacion.roles = portalUtil.objectToIdArray(data.roles);
        });
        */
        $scope.crearCalendarizacionModal = true;

    };
    
    $scope.crear = function(){
        $scope.crearCalendarizacionModal  = true;
        
    }

    
    
    
    
    
    
    
    
    
    
    

    }]);