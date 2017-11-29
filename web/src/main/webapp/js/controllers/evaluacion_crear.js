angular.module('DiagnosticApp').controller('EvaluacionCrearCtrl', ['$rootScope', '$scope', 'settings', 'portalUtil', '$sce', function ($rootScope, $scope, settings, portalUtil, $sce) {
    $scope.$on('$viewContentLoaded', function () {
        // initialize core components
        App.initAjax();
        // set default layout mode
        $rootScope.settings.layout.pageContentWhite = true;
        $rootScope.settings.layout.pageBodySolid = false;
        $rootScope.settings.layout.pageSidebarClosed = false;
        $rootScope.settings.layout.isPublic = false;
        $rootScope.settings.layout.isPrivate = true;

        $scope.crearPreguntaModal = false;
        $scope.subCategorias = [];
        $scope.currentPregunta = {
            "imagen": "",
            "categoriaId": "",
            "cuerpo": "",
            "estado": "",
            respuestas: [{
                    "cuerpo": "",
                    "valor": 0,
                    "num": 1
                },
                {
                    "cuerpo": "",
                    "valor": 0,
                    "num": 2
                },
                {
                    "cuerpo": "",
                    "valor": 0,
                    "num": 3
                },
                {
                    "cuerpo": "",
                    "valor": 0,
                    "num": 4
                }, ]
        };

        $scope.evaluacion = {
            "evaluacionId": "",
            "categoriaId": "",
            "titulo": "",
            "preguntas": []
        };
    });

    // relleno categoria 
    $scope.categorias = [
        {
            "value": "1",
            "text": "Matemáticas"
        },
        {
            "value": "2",
            "text": "Inglés"
        },
        {
            "value": "3",
            "text": "Psicología"
        }
];

    $scope.cargaSubcategorias = function () {
        if ($scope.evaluacion.categoriaId == 1) {
            $scope.subCategorias = [
                {
                    "value": "4",
                    "text": "Álgebra"
                },
                {
                    "value": "5",
                    "text": "Potencias"
                },
                {
                    "value": "6",
                    "text": "Cálculo"
                }
        ];
        } else if ($scope.evaluacion.categoriaId == 2) {


            $scope.subCategorias = [

                {
                    "value": "7",
                    "text": "Verbos"
                },
                {
                    "value": "8",
                    "text": "Léxico"
                },
                {
                    "value": "9",
                    "text": "Comprensión"
                }
        ];
        }
    }

    $scope.modalCrearPregunta = function () {

        if ($scope.evaluacion.categoriaId == "") {

            $("[name='CategoriaEvaluacion']").closest(".input-group").addClass("has-error");
            $.bootstrapGrowl('<i class="fa fa-ban"></i>  Debe seleccionar categoría de evaluación', {
                type: 'danger',
                align: 'right',
                allow_dismiss: true

            });

            return false;
        }
        console.log("MODAL?")
        $scope.cargaSubcategorias();
        $scope.crearPreguntaModal = true;

    };

    $scope.guardaPregunta = function (form) {
        console.log("entra a guardaPregunta");
        $scope.evaluacion.preguntas.push($scope.currentPregunta);
        $scope.currentPregunta = {
            "imagen": "",
            "categoriaId": "",
            "cuerpo": "",
            "estado": "",
            respuestas: [{
                    "cuerpo": "",
                    "valor": 0,
                    "num": 1
                },
                {
                    "cuerpo": "",
                    "valor": 0,
                    "num": 2
                },
                {
                    "cuerpo": "",
                    "valor": 0,
                    "num": 3
                },
                {
                    "cuerpo": "",
                    "valor": 0,
                    "num": 4
                }, ]
        };
        $scope.crearPreguntaModal = false;


    }

    $scope.save = function (form) {
        console.log(form, "le form");

    }

    }]);