angular.module('DiagnosticApp').controller('EvaluacionContestarCtrl', ['$rootScope', '$scope', 'settings', 'portalUtil', '$sce', '$stateParams', function ($rootScope, $scope, settings, portalUtil, $sce, $stateParams) {
    $scope.$on('$viewContentLoaded', function () {
        // initialize core components
        App.initAjax();
        // set default layout mode
        $rootScope.settings.layout.pageContentWhite = true;
        $rootScope.settings.layout.pageBodySolid = false;
        $rootScope.settings.layout.pageSidebarClosed = false;
        $rootScope.settings.layout.isPublic = false;
        $rootScope.settings.layout.isPrivate = true;

        $scope.evaluacion = {};
        $scope.cargarEvaluacion();

        console.log($rootScope);

        console.log($rootScope.state.params.id)

        if ($stateParams.id !== null && $stateParams.id !== "") {
            $scope.cargarEvaluacion();
        } else {
            session.destroy();
        }




    });
    var respuestasAlumno = [];
    var api = portalUtil.getApi();


    $scope.seleccion = function (idPregunta, idRespuesta) {

        respuestasAlumno.push({
            "preguntaId": idPregunta,
            "respuestaId": idRespuesta
        });
        console.log(respuestasAlumno);
    }


    $scope.cargarEvaluacion = function () {

        api.one('evaluacion', $stateParams.id).get().then(function (data) {
            console.log(data, "EL Evaluacion A VER");
            $scope.evaluacion = data;
        });


    };






    $scope.enviaRespuestas = function (form) {
        console.log(respuestasAlumno, "FINALMENTE");
        //validar que estén todas contestadas
        console.log(form, "le form");
        //Enviar respuestas
        //        console.log($scope.respuestasAlumno, "Respuestas");

        api.all('respuesta').post(respuestasAlumno).then(function () {

            alert("GRABAA");
        }, saveError);


    };

    }]);