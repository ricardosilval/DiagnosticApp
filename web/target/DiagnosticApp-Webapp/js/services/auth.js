angular
        .module('DiagnosticApp')
        .service('auth', ['$http', 'session', 'portalUtil', function ($http, session, portalUtil) {
                /**
                 * Check whether the user is logged in
                 * @returns boolean
                 */
                this.isLoggedIn = function isLoggedIn() {
                    return session.getUser() !== null;
                };
                /**
                 * Log in
                 *
                 * @param credentials
                 * @returns {*|Promise}
                 */
            
           /* var user = {
                            "id": "admindiagnosicappid",
                            "nombre": "Admin",
                            "username": "AdminDummy",
                            "apellido": "Dummy",
                            "email": "admin@dummy.cl",
                            "token": "45677890765467890876546789",
                            "roles": {id:"roladmin", nombre:"administrador"},
                            "avatar": ""
                        };
                        session.setUser(user);*/
            
            
                this.logIn = function (credentials) {
                   
                    var api = portalUtil.getLoginApi();
                    console.log(credentials, "Les cred");
                    return api.all('auth').all('login').post(credentials).then(function (response) {
                        console.log(response, "RESPONSE LOGIN");
                        var data = response.data;
                        var user = {
                            "id": data.usuarioId,
                            "nombre": data.nombre,
                            "username": data.username,
                            "apellido": data.apellido,
                            "email": data.correoElectronico,
//                            "token": data.token,
                            "rol": data.rol
                        };
                        session.setUser(user);
                        console.log(user, "SESSION DATA");
                        return response;
                    }, function (error) {
                        alert("error");
                        return error;
                    });
                };
                
                /**
                 * Log out
                 *
                 * @returns {*|Promise}
                 */
                this.logOut = function () {
                    session.destroy();
                };
            }]);
