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
            $scope.initDashboardDaterange();
            $scope.dataSource = [];
            $scope.lotesRegistrados = 0;
            $scope.documentosEmtidos = 0;
            $scope.comprobantesNoFacturados = 0;
            $scope.laData = [{"value": "", "label": ""}];
            $scope.inicio = [moment().format('YYYY-MM-DD')];
            $scope.hasta = [moment().subtract('days', 29).format('YYYY-MM-DD')];
            $scope.loadDashboard();
        });



        $scope.loadDashboard = function () {
//            App.blockUI("body");
//            var api = portalUtil.getApi();
//            var filterrier = {//Paginacion
//
//            };
//            filterrier = {//Paginacion
//                desde: $scope.inicio,
//                hasta: $scope.hasta
//            };
//            console.log(filterrier);
//            var dataSource = [];
//            api.one('dashboard').get(filterrier).then(function (data) {
//
//
//                $scope.lotesRegistrados = data.lotesRegistrados;
//                $scope.documentosEmtidos = data.documentosEmitidos;
//                $scope.comprobantesNoFacturados = data.comprobantesPendientes;
//
//                angular.forEach(data.mainChart, function (value, key, o) {
//                    dataSource.push({"label": value.aduanaLoteadora, "value": value.cantidadPreFacturas});
//                });
//                console.log(dataSource, "DS");
//                $scope.laData = dataSource;
//                App.unblockUI("body");
//
//            });


            // $scope.laData = '[{"label":"DRA Valparaiso","value":2},{"label":"DRA Iquique","value":0},{"label":"Arica","value":1},{"label":"Tocopilla","value":0},{"label":"Antofagasta","value":0},{"label":"Chañaral","value":0},{"label":"Coquimbo","value":2},{"label":"Los Andes","value":4},{"label":"San Antonio","value":0},{"label":"Talcahuano","value":1},{"label":"Osorno","value":3},{"label":"Puerto Montt","value":2},{"label":"Coyhaique","value":0},{"label":"Puerto Aysén","value":0},{"label":"Punta Arenas","value":2},{"label":"DRA Metropolitana (Santiago)","value":8}]';
        };

        $scope.initDashboardDaterange = function () {
            if (!jQuery().daterangepicker) {
                return;
            }

            $('#dashboard-report-range').daterangepicker({
                "ranges": {
                    'Hoy': [moment(), moment()],
                    'Ayer': [moment().subtract('days', 1), moment().subtract('days', 1)],
                    'Últimos 7 Dias': [moment().subtract('days', 6), moment()],
                    'Últimos 30 Dias': [moment().subtract('days', 29), moment()],
                    'Este mes': [moment().startOf('month'), moment().endOf('month')],
                    'Último mes': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
                },
                "locale": {
                    "format": "DD/MM/YYYY",
                    "separator": " - ",
                    "applyLabel": "Aplicar",
                    "cancelLabel": "Cancelar",
                    "fromLabel": "Desde",
                    "toLabel": "Al",
                    "customRangeLabel": "Personalizado",
                    "daysOfWeek": [
                        "Do",
                        "Lu",
                        "Ma",
                        "Mi",
                        "Ju",
                        "Vi",
                        "Sa"
                    ],
                    "monthNames": [
                        "Enero",
                        "Febrero",
                        "Marzo",
                        "Abril",
                        "Mayo",
                        "Junio",
                        "Julio",
                        "Agosto",
                        "Septiembre",
                        "Octubre",
                        "Noviembre",
                        "Diciembre"
                    ],
                    "firstDay": 1
                },
                //"startDate": "11/08/2015",
                //"endDate": "11/14/2015",
                opens: (App.isRTL() ? 'right' : 'left'),
            }, function (start, end, label) {
                if ($('#dashboard-report-range').attr('data-display-range') != '0') {
                    $scope.inicio = start.format('YYYY-MM-DD');
                    $scope.hasta = end.format('YYYY-MM-DD');

                    $('#dashboard-report-range span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                    $scope.loadDashboard();
                }
            });
            if ($('#dashboard-report-range').attr('data-display-range') != '0') {
                $scope.inicio = moment().subtract('days', 29).format('YYYY-MM-DD');
                $scope.hasta = moment().format('YYYY-MM-DD');

                $('#dashboard-report-range span').html(moment().subtract('days', 29).format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));
                $scope.loadDashboard();
            }

            $('#dashboard-report-range').show();

        };





    }]);
