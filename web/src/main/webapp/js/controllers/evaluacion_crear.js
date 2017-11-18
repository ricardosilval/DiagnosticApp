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


            
           

        });
  $scope.evaluacion = {
    "evaluacionId":"",
      "categoriaId": "",
      "titulo": "",
      "preguntas": [
          {
              "cuerpo": "",
              "imagen": "",
              "categoria":"",
              "respuestas":[
                  {"cuerpo":"", "valor":0,"imagen":""}
            
              ]
          }
      ]
  }
      
  
  
  
  
    // relleno categoria 
    $scope.categorias = [
        {"value": "1","text": "Matemáticas"},
        {"value": "2","text": "Inglés"},
        {"value": "3","text": "Psicología"},
        
];
    

    }]);
