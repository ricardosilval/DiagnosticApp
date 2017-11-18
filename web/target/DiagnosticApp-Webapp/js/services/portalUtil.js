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
                // header['X-Company'] = $rootScope.company;
                Restangular.setDefaultHeaders(header);
                // console.log(window.location);
                if (window.location.hostname === '127.0.0.1') {
                    Restangular.setBaseUrl('http://localhost:8080/');
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
                return Restangular.all('api');
            };
            this.getLoginApi = function () {
                var header = {};

                if (window.location.hostname === '127.0.0.1') {
                    Restangular.setBaseUrl('http://localhost:8080/');
                }

                Restangular.setDefaultHeaders(header);
                Restangular.setFullResponse(true);

                Restangular.setErrorInterceptor(function (response, deferred, responseHandler) {
                    return true;
                });
                return Restangular.all('api');
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


            this.getNotNullValue = function (value) {
                if (value == null || typeof value == 'undefined') {
                    return "";
                }
                return value;
            };

            this.getNotNullNumber = function (value) {
                if (value == null || typeof value == 'undfined' || value == '' || isNaN(value)) {
                    return 0;
                }
                return parseFloat(value + "");
            }

            this.fillTxtDteLine = function (val, maxLen, separador) {
                return this.str_pad(this.getNotNullValue(val), maxLen, separador, "STR_PAD_RIGHT").substring(0, maxLen);
            };

            this.str_pad = function (input, pad_length, pad_string, pad_type) {
                var half = '',
                    pad_to_go;
                var str_pad_repeater = function (s, len) {
                    var collect = '',
                        i;
                    while (collect.length < len) {
                        collect += s;
                    }
                    collect = collect.substr(0, len);
                    return collect;
                };

                input += '';
                pad_string = pad_string !== undefined ? pad_string : ' ';
                if (pad_type !== 'STR_PAD_LEFT' && pad_type !== 'STR_PAD_RIGHT' && pad_type !== 'STR_PAD_BOTH') {
                    pad_type = 'STR_PAD_RIGHT';
                }
                if ((pad_to_go = pad_length - input.length) > 0) {
                    if (pad_type === 'STR_PAD_LEFT') {
                        input = str_pad_repeater(pad_string, pad_to_go) + input;
                    } else if (pad_type === 'STR_PAD_RIGHT') {
                        input = input + str_pad_repeater(pad_string, pad_to_go);
                    } else if (pad_type === 'STR_PAD_BOTH') {
                        half = str_pad_repeater(pad_string, Math.ceil(pad_to_go / 2));
                        input = half + input + half;
                        input = input.substr(0, pad_length);
                    }
                }

                return input;
            };


            this.unidadesMedidasDesc = function (cod) {

                switch (parseInt(cod)) {
                case 1:
                    return "Kilo Neto";
                    break;

                case 2:
                    return "Kilo Bruto"
                    break;

                case 3:
                    return "Cartones"
                    break;

                case 4:
                    return "Toneladas"
                    break;

                case 5:
                    return "Litros"
                    break;
                default:
                    return "";
                }
            };
            this.tipoUnidadesMedidas = function (cod) {
                switch (cod) {
                case "D":
                case "d":
                    return "Documental";
                    break;
                case "v":
                case "V":
                    return "Verificado";
                    break;


                }
            }


            this.ubicacionMercanciaDesc = function (cod) {

                switch (cod) {
                case "E":
                    return "ESPECIAL";
                    break;
                case "A-01":
                    return "ADUANA";
                    break;
                case "A-02":
                    return "EMPRESA PORTUARIA DE VALPARAÍSO";
                    break;
                case "A-03":
                    return "PARTICULAR";
                    break;
                case "A-04":
                    return "CORREOS";
                    break;
                case "A-09":
                    return "DEPOCARGO S.A.(Santiago)";
                    break;
                case "A-10":
                    return "AEROSAN S.A.(Santiago)";
                    break;
                case "A-11":
                    return "FAST - AIR S.A.(Santiago)";
                    break;
                case "A-13":
                    return "DEPOSITOS PORTUARIOS LIRQUEN S.A.";
                    break;
                case "A-19":
                    return "S.A.A.M.EXTRAPORTUARIOS S.A.(Barón - Valparaíso)";
                    break;
                case "A-23":
                    return "COSAF COMERCIAL S.A.(EX MUELLES DE PENCO S.A.)";
                    break;
                case "A-24":
                    return "SEAPORT S.A.";
                    break;
                case "A-25":
                    return "EMPRESA PORTUARIA ARICA";
                    break;
                case "A-26":
                    return "EMPRESA PORTUARIA IQUIQUE";
                    break;
                case "A-27":
                    return "EMPRESA PORTUARIA ANTOFAGASTA";
                    break;
                case "A-28":
                    return "EMPRESA PORTUARIA COQUIMBO";
                    break;
                case "A-29":
                    return "PUERTO SAN ANTONIO";
                    break;
                case "A-30":
                    return "EMPRESA PORTUARIA TALCAHUANO";
                    break;
                case "A-31":
                    return "EMPRESA PORTUARIA PUERTO MONTT";
                    break;
                case "A-32":
                    return "EMPRESA PORTUARIA CHACABUCO";
                    break;
                case "A-33":
                    return "EMPRESA PORTUARIA AUSTRAL";
                    break;
                case "A-34":
                    return "TERMINAL PACIFICO SUR VALPARAISO S.A.";
                    break;
                case "A-35":
                    return "PUERTO PANUL S.A.  (San Antonio)";
                    break;
                case "A-36":
                    return "SAN ANTONIO TERMINAL INTERNACIONAL S.A.";
                    break;
                case "A-37":
                    return "SAN VICENTE TERMINAL INTERNACIONAL S.A.";
                    break;
                case "A-38":
                    return "DEPOSITO ADUANERO VENTANAS S.A.";
                    break;
                case "A-39":
                    return "IQUIQUE TERMINAL INTERNACIONAL S.A.";
                    break;
                case "A-40":
                    return "S.A.A.M.EXTRAPORTUARIOS S.A.  (San Antonio)";
                    break;
                case "A-42":
                    return "TRANS WARRANTS";
                    break;
                case "A-43":
                    return "ANTOFAGASTA TERMINAL INTERNACIONAL";
                    break;
                case "A-44":
                    return "SITRANS ALMACENES EXTRAPORTUARIOS LTDA.  (Curauma)";
                    break;
                case "A-45":
                    return "PUERTO COLUMBO S.A.(San Antonio)";
                    break;
                case "A-46":
                    return "ALMACEN EXTRAPORTUARIO EL SAUCE S.A.  (Los Andes)";
                    break;
                case "A-47":
                    return "CORONEL DEPOSITOS S.A.";
                    break;
                case "A-48":
                    return "TERQUIM S.A.   (San Antonio)";
                    break;
                case "A-56":
                    return "SITRANS ALMACENES EXTRAPORTUARIOS LTDA.  (San Antonio)";
                    break;
                case "A-60":
                    return "DEPOSITO CHACAYA LTDA (Puerto Mejillones)";
                    break;
                case "A-61":
                    return "FAST AIR ALMACENES DE CARGA S.A.   (Antofagasta)";
                    break;
                case "A-62":
                    return "FAST AIR ALMACENES DE CARGA S.A.   (Punta Arenas)";
                    break;
                case "A-63":
                    return "CONSORCIO PORTUARIO ARICA S.A.";
                    break;
                case "A-64":
                    return "ALMACEN EXTRAPORTUARIO HANSEN LTDA.";
                    break;
                case "A-65":
                    return "PUERTO TERRESTRE LOS ANDES, SOC.CONCESIONARIA S.A.";
                    break;
                case "A-66":
                    return "SOCIEDAD MARÍTIMA Y COMERCIAL SOMARCO LTYDA.";
                    break;
                case "A-67":
                    return "DEPOCARGO S.A. (Iquique)";
                    break;
                case "A-68":
                    return "SAAM EXTRAPORTUARIO S.A. (Placilla - Valparaíso)";
                    break;
                case "A-69":
                    return "TERMINAL EXTRAPORTUARIO VALPARAISO S.A.";
                    break;
                case "A-70":
                    return "EL SAUCE S.A.  (Valparaíso)";
                    break;
                case "A-71":
                    return "PUERTO CENTRAL S.A.  (San Antonio)";
                    break;
                case "A-72":
                    return "TERMINAL PUERTO COQUIMBO S.A. ";
                    break;
                case "A-73":
                    return "TERMINAL CERROS DE VALPARAISO S.A.";
                    break;
                case "A-74":
                    return "CONTOPSA INLAND TERMINALS SPA";
                    break;
                case "A-76":
                    return "PUERTO COLUMBO S.A.(Valparaíso)";
                    break;

                }
            };

            this.findAndRemove = function (array, property, value) {
                array.forEach(function (result, index) {
                    if (result[property] === value) {
                        //Remove from array
                        array.splice(index, 1);
                    }
                });
            };
            this.tasasImpuestosAdicionales = function (cod) {

                switch (parseInt(cod)) {
                case 23:
                case 25:
                case 26:
                case 44:
                    return 15;
                    break;
                case 24:
                    return 27;
                    break;
                case 27:
                    return 13;
                    break;
                case 28:
                case 35:
                    return 0;
                case 10:
                    return 0;
                default:
                    return 0;
                    break;
                }

            };

            this.tiposImpuestosAdicionales = function (cod) {

                switch (parseInt(cod)) {
                case 23:
                    return "Art. de oro, Joyas y Pieles finas 15%";
                    break;
                case 25:
                    return "Vinos, Chichas, Sidras 15%";
                    break;
                case 26:
                    return "Cervezas y Otras bebidas alcohólicas 15%";
                    break;
                case 44:
                    return "Tapices, Casas rod., Caviar y Arm.de aire 15%";
                    break;
                case 24:
                    return "Licores, Pisco, Destilados 27%";
                    break;
                case 27:
                    return "Aguas minerales y Beb. analcohólicas. 13%";
                    break;
                case 28:
                    return "Impuesto Específico Diesel";
                    break;
                case 35:
                    return "Impuesto Específico Gasolina";
                case 10:
                    return "No tiene";
                default:
                    return "No tiene";
                    break;
                }

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