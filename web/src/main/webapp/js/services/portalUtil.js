angular
    .module('DiagnosticApp')
    .service('portalUtil', ['session', 'Restangular', '$state', '$rootScope',
            function (session, Restangular, $state, $rootScope) {

            this.findById = function (array, id) {
                for (var i = 0; i < array.length; i++) {
                    if (array[i].id == id) {
                        return array[i];
                    }
                }
                return null;
            };
            this.removeById = function (array, id) {
                var remove = this.findById(array, id);
                var index = array.indexOf(remove);
                if (index != -1) {
                    array.splice(index, 1);
                }
            };
            this.getApi = function () {
                var header = {};
                header['X-Token'] = session.getUser().token;
                 
                 header['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
                // header['X-Company'] = $rootScope.company;
                Restangular.setDefaultHeaders(header);
                // console.log(window.location);
                
                /**
                CAMBIAR AMBIENTE CHARBEL
                */
                if (window.location.hostname === '127.0.0.1') {
                    Restangular.setBaseUrl('http://172.20.10.4:9090/ServiciosDiagnosticApp/rest/servicios/');
                    //Restangular.setBaseUrl('http://localhost:8080/ServiciosDiagnosticApp/rest/servicios');
                }
                Restangular.setFullResponse(false);
                
                Restangular.setErrorInterceptor(function (response, deferred, responseHandler) {
                    if (response.status === 401) {
                        session.destroy();
                        $state.go('public.auth');
                        return false; // error handled
                    }

                    return true; // error not handled
                });
                return Restangular.all('');
            };
            this.getLoginApi = function () {
                var header = {};

                /**
                CAMBIAR AMBIENTE CHARBEL
                */
                if (window.location.hostname === '127.0.0.1') {
                    Restangular.setBaseUrl('http://172.20.10.4:9090/ServiciosDiagnosticApp/rest/servicios/');
                    //Restangular.setBaseUrl('http://localhost:8080/ServiciosDiagnosticApp/rest/servicios');
                }

                Restangular.setDefaultHeaders(header);
                Restangular.setFullResponse(true);

                Restangular.setErrorInterceptor(function (response, deferred, responseHandler) {
                    return true;
                });
                return Restangular.all('');
            };
            this.validateForm = function (form, scopeError) {

                if (form.$error.required || form.$error.pattern) {
                    for (var i = 0; form.$error.required && i < form.$error.required.length; i++) {
                        $("[name='" + form.$error.required[i].$name + "']").closest(".input-group").addClass("has-error");
                        scopeError.push("<i class='fa fa-warning'></i> El campo " + form.$error.required[i].$name + " es obligatorio.");
                    }
                    for (var i = 0; form.$error.pattern && i < form.$error.pattern.length; i++) {
                        scopeError.push("El campo " + form.$error.pattern[i].$name + " no cumple con el formato adecuado.");
                    }
                }
            };
            /**
             * Métdo que convierte un arreglo de objetos a un arreglo de id
             * tomando el campo id, no funciona si el campo almacenador del id
             * se llama algo_id, ej: usuario_id
             * 
             * @param {JSON} objeto
             * @returns {Array}
             */
            this.objectToIdArray = function (objeto) {

                var aux = [];
                $.each(objeto, function (i, o) {
                    console.log(o, "objeto");
                    if (o.id) {
                        aux.push(o.id);
                    }
                });
                console.log(aux, "SOLO IDS");
                return aux;

            };
            /**
             * Función que convierte una fecha string formato dd/mm/YYYY a milisegundos
             * @param {String} dateString
             * @returns {Number}
             */
            this.stringDateToMilliseconds = function (dateString) {

                var date = dateString.split("/");

                var d = date[0];
                var m = (date[1] - 1);
                var y = date[2];

                return new Date(y, m, d).getTime();
            };

            this.millisecondsToStringDate = function (milliseconds) {
                var time = new Date(milliseconds).getTime();
                var date = new Date(time);

                var theyear = date.getFullYear();
                var themonth = ("0" + (date.getMonth() + 1)).slice(-2);
                var theday = ("0" + date.getDate()).slice(-2);

                return theday + "/" + themonth + "/" + theyear;
            };


   
           


            this.findAndRemove = function (array, property, value) {
                array.forEach(function (result, index) {
                    if (result[property] === value) {
                        //Remove from array
                        array.splice(index, 1);
                    }
                });
            };
           
            this.countDaysBetweenDates = function (desde, hasta, separador) {
                var oneDay = 24 * 60 * 60 * 1000; // hours*minutes*seconds*milliseconds

                if (typeof desde !== 'undefined' && typeof hasta !== 'undefined') {
                    // console.log("Dentra a contar dias");
                    desde = desde.split(separador);
                    hasta = hasta.split(separador);

                    var firstDate = new Date(desde[2], desde[1], desde[0]);
                    var secondDate = new Date(hasta[2], hasta[1], hasta[0]);

                    // console.log(Math.round((firstDate.getTime() - secondDate.getTime()) / (oneDay)), "SON!");

                    difference = Math.round((firstDate.getTime() - secondDate.getTime()) / (oneDay));


                    if (!isNaN(difference)) {
                        return Math.round((firstDate.getTime() - secondDate.getTime()) / (oneDay));
                    }
                }




            };

            
                }
                ]);