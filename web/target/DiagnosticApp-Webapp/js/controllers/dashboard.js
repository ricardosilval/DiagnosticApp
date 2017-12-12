angular.module('DiagnosticApp').controller('DashboardCtrl', ['$rootScope', '$scope', 'settings', 'portalUtil', '$sce', function ($rootScope, $scope, settings, portalUtil, $sce) {
    $scope.$on('$viewContentLoaded', function () {
        // initialize core components
        // App.initAjax();

        // set default layout mode
        $rootScope.settings.layout.pageContentWhite = true;
        $rootScope.settings.layout.pageBodySolid = false;
        $rootScope.settings.layout.pageSidebarClosed = false;
        $rootScope.settings.layout.isPublic = false;
        $rootScope.settings.layout.isPrivate = true;

    });



    $scope.evaluaciones = [{
        "id" : "aa",
        "titulo": "Matematicas amigables",
        categoria: {
            "id": "asad1",
            "nombre": "Matematicas"
        }
    }, {"id" : "bb",
        "titulo": "Inglés Básico",
        categoria: {
            "id": "asad1",
            "nombre": "Inglés"
        }
    }, {
        id: "cc",
        "titulo": "Tendencia de Estilo de Aprendizaje",
        categoria: {
            "id": "asad2",
            "nombre": "Psicología"
        }
    }];


    }]);