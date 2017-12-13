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


        $scope.clearFilter();
        $scope.listar();

        $scope.calendarizacion = {
            fechaInicio: "",
            fechaTermino: "",
            estado: "",
            titulo: "",
            descripcion: "",
            id: ""
        };
        $scope.paginaActual = 1;

    });
    var api = portalUtil.getApi();


    $scope.crearCalendarizacionModal = false;


    $scope.optionsEstado = [{
        "value": 1,
        "text": "Activos"
    }, {
        "value": 2,
        "text": "Inactivos"
    }];

    $scope.clearFilter = function () {

        $scope.filtroFechaInicio = "";
        $scope.filtroEstado = "";
        $scope.listar();
    };

    $scope.habilitaAcciones = function (cal) {
        $scope.permiteAcciones = true;
        $scope.calendarizacion = cal;
    };

    $scope.listar = function () {
        /*
        Llamada API de listar calendarizaciones
        */
        var filterrier = {
            pagina: $scope.paginaActual,
            filas: 15
        };

        if ($scope.filtroEstado !== "") {
            filterrier['estado'] = $scope.filtroEstado;
        }
        if ($scope.filtroFechaInicio !== "") {
            filterrier['fechaInicio'] = $scope.filtroFechaInicio;
        }
        api.one('calendarizacion').get(filterrier).then(function (data) {
            console.log(data, "RESPONSE")
            $scope.calendarizaciones = data.calendarizaciones;
            $scope.paginaActual = data.paginaActual;
            $scope.totales = data.total;

        });

    };

    $scope.save = function (form) {

        console.log($scope.calendarizacion, "LA CAL");

        $scope.userError = [];
        portalUtil.validateForm(form, $scope.userError);
        if ($scope.userError.length)
            return;
        if ($scope.calendarizacion.id === "") {
            console.log("Entra a post");
            api.all('calendarizacion').post($scope.calendarizacion).then(function () {
                $scope.listar();
                $scope.crearCalendarizacionModal = false;
            }, saveError);
        } else {
            console.log("Entra a put");
            $scope.calendarizacion.put().then(function () {
                $scope.listar();
                $scope.crearCalendarizacionModal = false;
            }, saveError);
        }


    };

    var saveError = function (error) {
        if (error.data && error.data.message)
            $scope.userError.push(error.data.message);
        else
            $scope.userError.push("Error guardando Calendarizacion.");
        console.log("Error al guardar");
    };



    $scope.modificarCalendarizacion = function () {
        //Llamada  a buscar Calendarizacion y setearlo en $scope.Calendarizacion
        api.one('calendarizacion', $scope.calendarizacion.id).get().then(function (data) {
            console.log(data, "EL Calendarizacion A MODIFICAR");
            $scope.calendarizacion = data;
        });

        $scope.crearCalendarizacionModal = true;

    };


    $scope.loadEvaluaciones = function () {

        $scope.modalAsignacion = true;

    }

    $scope.asignar = function (form) {

        console.log(form, "les form");
    };

    $scope.crear = function () {


        $scope.calendarizacion = {
            fechaInicio: "",
            fechaTermino: "",
            estado: 0,
            titulo: "",
            descripcion: "",
            id: ""
        };
        $scope.crearCalendarizacionModal = true;

    }









    }]);