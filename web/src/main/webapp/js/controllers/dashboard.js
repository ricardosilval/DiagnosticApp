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


        $scope.evaluaciones = [];
        $scope.listar();

    });


    var api = portalUtil.getApi();


    $scope.listar = function () {
        /*
        Llamada API de listar evaluaciones
        */
        var filterrier = {
            pagina: 1,
            filas: 15
        };

        api.one('evaluacion').get(filterrier).then(function (data) {
            $scope.evaluaciones = data.evaluaciones;


        });

    };


    }]);