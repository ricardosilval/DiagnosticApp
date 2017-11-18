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
             return true;
            /*
             var user = this.getUser();
            if(user.rol.nombre.toLowerCase === 'administrador'){
                
                return true;
            }
            */
            
            //return false;
        };

        this.isExaminador = function () {
            var user = this.getUser();
            if(user.rol.nombre.toLowerCase === 'examinador'){
                
                return true;
            }
            
            return false;
        };
        
/*
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
        */

            }]);