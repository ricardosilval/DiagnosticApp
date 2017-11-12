angular.module('DiagnosticApp')
    .service('session', ['localStorage', function (localStorage) {
        // Instantiate data when service
        // is loaded
        this._user = JSON.parse(localStorage.getItem('session.user'));
        //console.log(this._user, "USER IS ADMIN?");
        this.getUser = function () {
            return this._user;
        };

        this.setUser = function (user) {
            this._user = user;
            localStorage.setItem('session.user', JSON.stringify(user));
            return this;
        };

        /**
         * Destroy session
         */
        this.destroy = function destroy() {
            this.setUser(null);
        };

        this.isAdmin = function () {
            var user = this.getUser();
            if (user) {
                // console.log(user, "USER IS ADMIN?");
                for (var i = 0; i < user.roles.length; i++) {
                    if (user.roles[i].nombre.toLowerCase() === 'administrador' || user.roles[i].nombre.toLowerCase() === 'superadministrador')
                        return true;
                }
            }
            return false;
        };

        this.isConsultor = function () {
            var user = this.getUser();
            if (user) {
                // console.log(user, "USER IS ADMIN?");
                for (var i = 0; i < user.roles.length; i++) {
                    if (user.roles[i].nombre.toLowerCase() === 'consultor')
                        return true;
                }
            }
            return false;
        };
        this.isPreFacturador = function () {
            var user = this.getUser();
            if (user) {
                // console.log(user, "USER IS ADMIN?");
                for (var i = 0; i < user.roles.length; i++) {
                    if (user.roles[i].nombre.toLowerCase() === 'prefacturador')
                        return true;
                }
            }
            return false;
        };
        this.isFacturador = function () {
            var user = this.getUser();
            if (user) {
                // console.log(user, "USER IS ADMIN?");
                for (var i = 0; i < user.roles.length; i++) {
                    if (user.roles[i].nombre.toLowerCase() === 'facturador')
                        return true;
                }
            }
            return false;
        };
        this.isGestor = function () {
            var user = this.getUser();
            if (user) {
                // console.log(user, "USER IS ADMIN?");
                for (var i = 0; i < user.roles.length; i++) {
                    if (user.roles[i].nombre.toLowerCase() === 'gestor')
                        return true;
                }
            }
            return false;
        };
        this.isGestorNacional = function () {
            var user = this.getUser();
            if (user) {
                // console.log(user, "USER IS ADMIN?");
                for (var i = 0; i < user.roles.length; i++) {
                    if (user.roles[i].nombre.toLowerCase() === 'gestor_nacional')
                        return true;
                }
            }
            return false;
        };

        this.hasPermission = function (permissionName) {
            var user = this.getUser();
            for (var i = 0; i < user.roles.length; i++) {
                for (var j = 0; j < user.roles[i].permissions.length; j++) {
                    if (user.roles[i].permissions[j].name === permissionName)
                        return true;
                }
            }
            return false;
        };

            }]);