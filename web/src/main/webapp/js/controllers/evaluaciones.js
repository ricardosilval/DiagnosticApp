angular.module('DiagnosticApp').controller('EvaluacionesCtrl', ['$rootScope', '$scope', 'settings', 'portalUtil', '$sce', function ($rootScope, $scope, settings, portalUtil, $sce) {
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

        
        $scope.evaluacion = {
            titulo: "",
            categoriaId: "",
            usuarioId: "",
            calendarizacionId: "",
            id: ""
        };

        $scope.evaluaciones = [];
        $scope.categorias = [];
        
        $scope.loadCategorias();


    });


    $scope.loadCategorias = function () {
        var filterrierCategorias = {
            pagina: 1,
            filas: 100
        };

        api.one('categoria').get(filterrierCategorias).then(function (data) {
            console.log(data, "RESPONSE cat")
            $scope.evaluaciones = data.categorias;
        });
    }

    var api = portalUtil.getApi();


    $scope.crearEvaluacionModal = false;


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
        $scope.evaluacion = cal;
    };

    $scope.listar = function () {
        /*
        Llamada API de listar evaluaciones
        */
        var filterrier = {
            pagina: $scope.paginaActual,
            filas: 15
        };

       /* if ($scope.filtroEstado !== "") {
            filterrier['estado'] = $scope.filtroEstado;
        }
        if ($scope.filtroFechaInicio !== "") {
            filterrier['fechaInicio'] = $scope.filtroFechaInicio;
        }*/
        api.one('evaluacion').get(filterrier).then(function (data) {
            console.log(data, "RESPONSE")
            $scope.evaluaciones = data.evaluaciones;
            $scope.paginaActual = data.paginaActual;
            $scope.totales = data.total;

        });

    };

    $scope.save = function (form) {

        console.log($scope.evaluacion, "LA CAL");

        $scope.userError = [];
        portalUtil.validateForm(form, $scope.userError);
        if ($scope.userError.length)
            return;
        if ($scope.evaluacion.id === "") {
            console.log("Entra a post");
            api.all('evaluacion').post($scope.evaluacion).then(function () {
                $scope.listar();
                $scope.crearEvaluacionModal = false;
            }, saveError);
        } else {
            console.log("Entra a put");
            $scope.evaluacion.put().then(function () {
                $scope.listar();
                $scope.crearEvaluacionModal = false;
            }, saveError);
        }


    };

    var saveError = function (error) {
        if (error.data && error.data.message)
            $scope.userError.push(error.data.message);
        else
            $scope.userError.push("Error guardando Evaluacion.");
        console.log("Error al guardar");
    };



    $scope.modificarEvaluacion = function () {
        //Llamada  a buscar Evaluacion y setearlo en $scope.Evaluacion
        api.one('evaluacion', $scope.evaluacion.id).get().then(function (data) {
            console.log(data, "EL Evaluacion A MODIFICAR");
            $scope.evaluacion = data;
        });

        $scope.crearEvaluacionModal = true;

    };




    $scope.asignar = function (form) {

        console.log(form, "les form");
    };

    $scope.crear = function () {


        $scope.evaluacion = {
            titulo: "",
            categoriaId: "",
            usuarioId: "",
            calendarizacionId: "",
            id: ""
        };
        $scope.crearEvaluacionModal = true;

    };
    
    $scope.visualizarEvaluacion = function(){
         
         api.one('evaluacion', $scope.evaluacion.id).get().then(function (data) {
            console.log(data, "EL Evaluacion A VER");
            $scope.evaluacion = data;
        });
        
        
        
    }

    }]);