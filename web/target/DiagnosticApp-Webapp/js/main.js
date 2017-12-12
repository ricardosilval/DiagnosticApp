/***

 ***/


var DiagnosticApp = angular.module("DiagnosticApp", [
    "ui.router",
    "ui.bootstrap",
    "oc.lazyLoad",
    "ngSanitize",
    "restangular",
    "ui.select",
    "angular.morris",
    'summernote'

]).config(function ($httpProvider) {
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    });


/* Configure ocLazyLoader(refer: https://github.com/ocombe/ocLazyLoad) */
DiagnosticApp.config(['$ocLazyLoadProvider', function ($ocLazyLoadProvider) {
    $ocLazyLoadProvider.config({
        // global configs go here
    });
    }]);

//AngularJS v1.3.x workaround for old style controller declarition in HTML
DiagnosticApp.config(['$controllerProvider', function ($controllerProvider) {
    // this option might be handy for migrating old apps, but please don't use it
    // in new ones!
    $controllerProvider.allowGlobals();
    }]);

/********************************************
 END: BREAKING CHANGE in AngularJS v1.3.x:
 *********************************************/

/* Setup global settings */
DiagnosticApp.factory('settings', ['$rootScope', function ($rootScope) {
    // supported languages
    var settings = {
        layout: {
            pageSidebarClosed: false, // sidebar menu state
            pageContentWhite: true, // set page content layout
            pageBodySolid: false, // solid body color state
            pageAutoScrollOnLoad: 1000, // auto scroll to top on page load
            isPublic: false,
            isPrivate: false
        },
        assetsPath: '../assets',
        globalPath: '../assets/global',
        layoutPath: '../assets/layouts/layout2'
    };

    $rootScope.settings = settings;
    $rootScope.layoutTheme = "light2";
    //console.log(window.location.hostname, "WINDOWS:LOCATION");

    return settings;
    }]);

/* Setup App Main Controller */
DiagnosticApp.controller('AppController', ['$scope', '$rootScope', 'auth', '$state', '$window', function ($scope, $rootScope, auth, $state, $window) {
    $scope.$on('$viewContentLoaded', function () {

        $scope.logOut = function () {
            App.unblockUI("body");
            auth.logOut();
            $state.go('public.auth', {}, {
                notify: false
            });
            window.location.reload();
        };

        //App.initComponents(); // init core components
        //Layout.init(); //  Init entire layout(header, footer, sidebar, etc) on page load if the partials included in server side instead of loading with ng-include directive 
    });
    }]);

/***
 Layout Partials.
 By default the partials are loaded through AngularJS ng-include directive. In case they loaded in server side(e.g: PHP include function) then below partial 
 initialization can be disabled and Layout.init() should be called on page load complete as explained above.
 ***/

/* Setup Layout Part - Header */
DiagnosticApp.controller('HeaderController', ['$scope', function ($scope) {
    $scope.$on('$includeContentLoaded', function () {
        Layout.initHeader(); // init header
    });
    }]);

/* Setup Layout Part - Sidebar */
DiagnosticApp.controller('SidebarController', ['$scope', function ($scope) {
    $scope.$on('$includeContentLoaded', function () {
        Layout.initSidebar(); // init sidebar
    });
    }]);

///* Setup Layout Part - Quick Sidebar */
DiagnosticApp.controller('QuickSidebarController', ['$scope', function ($scope) {
    $scope.$on('$includeContentLoaded', function () {
        setTimeout(function () {
            QuickSidebar.init(); // init quick sidebar        
        }, 2000)
    });
    }]);

/* Setup Layout Part - Theme Panel */
//DiagnosticApp.controller('ThemePanelController', ['$scope', function ($scope) {
//        $scope.$on('$includeContentLoaded', function () {
//            Demo.init(); // init theme panel
//        });
//    }]);

/* Setup Layout Part - Footer */
DiagnosticApp.controller('FooterController', ['$scope', function ($scope) {
    $scope.$on('$includeContentLoaded', function () {
        Layout.initFooter(); // init footer
    });
    }]);

/* Setup Rounting For All Pages */
DiagnosticApp.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    // Redirect any unmatched url
    $urlRouterProvider.otherwise("/dashboard");

    $stateProvider
        .state('public', {
            abstract: true,
            template: "<ui-view/>"
        })
        /*
         * LOGIN
         */
        .state('public.auth', {
            url: "/auth",
            controllerAs: 'vm',
            templateUrl: "/tpl/auth.html",
            data: {
                pageTitle: 'Inicio de Sesión'
            },
            controller: "AuthCtrl",
            resolve: {
                deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'DiagnosticApp',
                        insertBefore: '#ng_load_plugins_before_login',
                        files: [
                                        '/js/controllers/auth.js',
                                        '/assets/pages/scripts/login.min.js',
                                        '/assets/global/scripts/app.min.js',
                                        '/assets/pages/css/login.min.css',
                                        '/assets/global/css/plugins.min.css',
                                        '/assets/global/css/components.min.css',
                                        '/assets/global/plugins/jquery-validation/js/jquery.validate.min.js',
                                        '/assets/global/plugins/jquery-validation/js/additional-methods.min.js',
                                        '/assets/global/plugins/select2/js/select2.full.min.js',
                                        '/js/services/auth.js',
                                        '/js/services/session.js',
                                        '/js/services/localStorage.js',
                                        '/js/services/portalUtil.js'
                                    ]
                    });
                            }]
            }
        });


    $stateProvider
        .state('private', {
            abstract: true,
            template: "<ui-view/>"
        })
        /*
         DASHBOARD
         */
        .state('private.dashboard', {
            url: "/dashboard",
            templateUrl: "views/dashboard/dashboard.html",
            data: {
                pageTitle: 'Inicio'
            },
            controller: "DashboardCtrl",
            resolve: {
                deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'DiagnosticApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
                        files: [
                                        '/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.css',
                                        '/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js',
                                        '/assets/global/plugins/moment.min.js',
                                        '/assets/global/plugins/jquery.sparkline.min.js',
                                        '/assets/pages/scripts/dashboard.js',
                                        '/assets/global/plugins/counterup/jquery.waypoints.min.js',
                                        '/assets/global/plugins/counterup/jquery.counterup.min.js',
                                        'js/controllers/dashboard.js'
                                    ]
                    });
                            }]
            }
        })

    /*
     MANTENEDOR USUARIOS
     */
    .state('private.usuarios', {
        url: "/admin/usuarios",
        templateUrl: "views/usuarios/listar.html",
        data: {
            pageTitle: 'Administrar Usuarios'
        },
        controller: "UsuariosCtrl",
        resolve: {
            deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load({
                    name: 'DiagnosticApp',
                    insertBefore: '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
                    files: [
                                        'js/controllers/usuarios.js',
                                        '../assets/global/plugins/selectize/selectize.css',
                                        '../assets/global/plugins/selectize/selectize.min.js',
                                        '../assets/global/plugins/selectize/angular-selectize.js',
                                        '../assets/global/plugins/icheck/icheck.min.js',
                                        '../assets/global/plugins/icheck/skins/xcheck/blue.css',
                                        '../assets/global/plugins/icheck/skins/all.css',
                                    ]
                });
                            }]
        }
    })

    /*
     MANTENEDOR ROLES
     */
    .state('private.roles', {
        url: "/admin/roles",
        templateUrl: "views/roles/listar.html",
        data: {
            pageTitle: 'Administrar Roles'
        },
        controller: "RolesCtrl",
        resolve: {
            deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load({
                    name: 'DiagnosticApp',
                    insertBefore: '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
                    files: [
                                        'js/controllers/roles.js',
                                        '../assets/global/plugins/selectize/selectize.css',
                                        '../assets/global/plugins/selectize/selectize.min.js',
                                        '../assets/global/plugins/selectize/angular-selectize.js',
                                        '../assets/global/plugins/icheck/icheck.min.js',
                                        '../assets/global/plugins/icheck/skins/xcheck/blue.css',
                                        '../assets/global/plugins/icheck/skins/all.css',
                                    ]
                });
                            }]
        }
    })

    // Evaluaciones 

    .state('private.evaluaciones', {
        url: "/evaluaciones",
        templateUrl: "views/evaluaciones/listar.html",
        data: {
            pageTitle: 'Gestor de Evaluaciones'
        },
        controller: "EvaluacionesCtrl",
        resolve: {
            deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load({
                    name: 'DiagnosticApp',
                    insertBefore: '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
                    files: [
                                        ///'/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.css',
                                        //'/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js',
                                        //'/assets/global/plugins/moment.min.js',
                                        //'/assets/global/plugins/jquery.sparkline.min.js',
                                        //'/assets/pages/scripts/dashboard.js',
                                        //'/assets/global/plugins/counterup/jquery.waypoints.min.js',
                                        //'/assets/global/plugins/counterup/jquery.counterup.min.js',
                                        'js/controllers/evaluaciones.js'
                                    ]
                });
                            }]
        }

    })

    // Crear Evaluaciones 
    .state('private.evaluacionesCrear', {
            url: "/evaluaciones/crear",
            templateUrl: "views/evaluaciones/crear.html",
            data: {
                pageTitle: 'Creacion de Evaluaciones'
            },
            controller: "EvaluacionCrearCtrl",
            resolve: {
                deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'DiagnosticApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
                        files: [
                            'js/controllers/evaluacion_crear.js',
                            '../assets/global/plugins/bootstrap-growl/jquery.bootstrap-growl.js',

                                    ]
                    });
                            }]
            }
        })
        .state('private.evaluacionEditar', {
            url: "/evaluaciones/modificar/:id",
            templateUrl: "views/evaluaciones/modificar.html",
            data: {
                pageTitle: 'Modificar de Evaluacion'
            },
            controller: "EvaluacionesEditarCtrl",
            resolve: {
                deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'DiagnosticApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
                        files: [
                                        ///'/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.css',
                                        //'/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js',
                                        //'/assets/global/plugins/moment.min.js',
                                        //'/assets/global/plugins/jquery.sparkline.min.js',
                                        //'/assets/pages/scripts/dashboard.js',
                                        //'/assets/global/plugins/counterup/jquery.waypoints.min.js',
                                        //'/assets/global/plugins/counterup/jquery.counterup.min.js',
                                        'js/controllers/evaluaciones_editar.js'
                                    ]
                    });
                            }]
            }
        })
        .state('private.calendarizaciones', {
            url: "/calendarizaciones",
            templateUrl: "views/calendarizaciones/listar.html",
            data: {
                pageTitle: 'Gestión de Calendarizaciones'
            },
            controller: "CalendarizacionesCtrl",
            resolve: {
                deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'DiagnosticApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
                        files: [
                                        ///'/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.css',
                                        //'/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js',
                                        //'/assets/global/plugins/moment.min.js',
                                        //'/assets/global/plugins/jquery.sparkline.min.js',
                                        //'/assets/pages/scripts/dashboard.js',
                                        //'/assets/global/plugins/counterup/jquery.waypoints.min.js',
                                        //'/assets/global/plugins/counterup/jquery.counterup.min.js',
                                        'js/controllers/calendarizaciones.js'

                                    ]
                    });
                            }]
            }
        }).state('private.reportes', {
            url: "/reportes",
            templateUrl: "views/reportes/main.html",
            data: {
                pageTitle: 'Gestor de Reportes'
            },
            controller: "ReportesCtrl",
            resolve: {
                deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'DiagnosticApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
                        files: [
                                        'js/controllers/reportes.js'
                                    ]
                    });
                            }]
            }

        }).state('private.alumnos', {
            url: "/alumnos",
            templateUrl: "views/alumnos/main.html",
            data: {
                pageTitle: 'Gestor de Alumnos'
            },
            controller: "AlumnosCtrl",
            resolve: {
                deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'DiagnosticApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
                        files: [
                                        'js/controllers/alumnos.js'
                                    ]
                    });
                            }]
            }

        }).state('private.evaluacionContestar', {
            url: "/evaluacion/contestar/{id}",
            templateUrl: "views/evaluaciones/contestar.html",
            data: {
                pageTitle: 'Contestar evaluación'
            },
            controller: "EvaluacionContestarCtrl",
            resolve: {
                deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'DiagnosticApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
                        files: [
                                        'js/controllers/evaluacion_contestar.js'
                                    ]
                    });
                            }]
            }

        });


    }]);

/* Init global settings and run the app */
DiagnosticApp.run(["$rootScope", "settings", "$state", function ($rootScope, settings, $state) {
    $rootScope.$state = $state; // state to be accessed from view
    $rootScope.$settings = settings; // state to be accessed from view
    }]);