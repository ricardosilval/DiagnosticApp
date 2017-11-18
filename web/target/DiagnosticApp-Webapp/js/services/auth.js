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
                this.logIn = function (credentials) {
                    var api = portalUtil.getLoginApi();
                    return api.all('auth').all('login').post(credentials).then(function (response) {
                        console.log(response, "RESPONSE LOGIN");
                        var data = response.data;
                        var user = {
                            "id": data.usuarioId,
                            "nombre": data.nombre,
                            "username": data.username,
                            "apellido": data.apellido,
                            "email": data.correoElectronico,
                            "token": data.token,
                            "permisos": data.permisos, //??
                            "roles": data.roles,
                            "avatar": data.avatar
                        };
                        session.setUser(user);
                        console.log(user, "SESSION DATA");
                        return response;
                    }, function (error) {
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
