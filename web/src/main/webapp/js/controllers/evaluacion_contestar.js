angular.module('DiagnosticApp').controller('EvaluacionContestarCtrl', ['$rootScope', '$scope', 'settings', 'portalUtil', '$sce', function ($rootScope, $scope, settings, portalUtil, $sce) {
    $scope.$on('$viewContentLoaded', function () {
        // initialize core components
        App.initAjax();
        // set default layout mode
        $rootScope.settings.layout.pageContentWhite = true;
        $rootScope.settings.layout.pageBodySolid = false;
        $rootScope.settings.layout.pageSidebarClosed = false;
        $rootScope.settings.layout.isPublic = false;
        $rootScope.settings.layout.isPrivate = true;

        $scope.evaluacion = {
            "evaluacionId": "",
            "categoria": {
                "id": "",
                "nombre": ""
            },
            "titulo": "",
            "preguntas": []
        };
        $scope.cargarEvaluacion();


        $scope.respuestasAlumno = [];

    });



    $scope.seleccion = function (idPregunta, idRespuesta) {
        //$scope.respuestasAlumno.push()
        var setRespuesta = {
            "preguntaId": idPregunta,
            "respuestaId": idRespuesta
        };
        $scope.respuestasAlumno.push(setRespuesta);
    }


    $scope.cargarEvaluacion = function () {


        $scope.evaluacion = {
            "evaluacionId": "aa",
            "categoria": {
                "id": "bb",
                "nombre": "Matemáticas"
            },
            "titulo": "Matemáticas amigables",
            "preguntas": [
                {
                    "id": "1a",
                    "cuerpo": "<p>Cual es la respuesta correcta:</p>",
                    "respuestas": [
                        {
                            "id": "a0",
                            "valor": 0,
                            "cuerpo": "Alternativa A"
                        },
                        {
                            "id": "b0",
                            "valor": 0,
                            "cuerpo": "Alternativa B"
                        },
                        {
                            "id": "c0",
                            "valor": 1,
                            "cuerpo": "Alternativa C"
                        },
                        {
                            "id": "d0",
                            "valor": 0,
                            "cuerpo": "Alternativa D"
                        }
                    ]
                },
                {
                    "id": "1b",
                    "cuerpo": "Según la expresión dada: 3X^2+2X+234 = 0:",
                    "respuestas": [
                        {
                            "id": "a",
                            "valor": 0,
                            "cuerpo": "Alternativa A"
                        },
                        {
                            "id": "b",
                            "valor": 0,
                            "cuerpo": "Alternativa B"
                        },
                        {
                            "id": "c",
                            "valor": 1,
                            "cuerpo": "Alternativa C"
                        },
                        {
                            "id": "d",
                            "valor": 0,
                            "cuerpo": "Alternativa D"
                        }
                    ]
                }
            ]
        };

    };



    $scope.enviaRespuestas = function (form) {

        //validar que estén todas contestadas
        console.log(form, "le form");
        //Enviar respuestas
        console.log($scope.respuestasAlumno, "Respuestas");

    };

    }]);