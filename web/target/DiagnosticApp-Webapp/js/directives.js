/***
 GLobal Directives
 ***/

// Route State Load Spinner(used on page or content load)
DiagnosticApp.directive('ngSpinnerBar', ['$rootScope',
    function ($rootScope) {
        return {
            link: function (scope, element, attrs) {
                // by defult hide the spinner bar
                element.addClass('hide'); // hide spinner bar by default

                // display the spinner bar whenever the route changes(the content part started loading)
                $rootScope.$on('$stateChangeStart', function () {
                    element.removeClass('hide'); // show spinner bar
                });

                // hide the spinner bar on rounte change success(after the content loaded)
                $rootScope.$on('$stateChangeSuccess', function () {
                    element.addClass('hide'); // hide spinner bar
                    $('body').removeClass('page-on-load'); // remove page loading indicator
                    Layout.setSidebarMenuActiveLink('match'); // activate selected link in the sidebar menu

                    // auto scorll to page top
                    setTimeout(function () {
                        App.scrollTop(); // scroll to the top on content load
                    }, $rootScope.settings.layout.pageAutoScrollOnLoad);
                });

                // handle errors
                $rootScope.$on('$stateNotFound', function () {
                    element.addClass('hide'); // hide spinner bar
                });

                // handle errors
                $rootScope.$on('$stateChangeError', function () {
                    element.addClass('hide'); // hide spinner bar
                });
            }
        };
    }
])

// Handle global LINK click
DiagnosticApp.directive('a', function () {
    return {
        restrict: 'E',
        link: function (scope, elem, attrs) {
            if (attrs.ngClick || attrs.href === '' || attrs.href === '#') {
                elem.on('click', function (e) {
                    e.preventDefault(); // prevent link click for above criteria
                });
            }
        }
    };
});

// Handle Dropdown Hover Plugin Integration
DiagnosticApp.directive('dropdownMenuHover', function () {
    return {
        link: function (scope, elem) {
            elem.dropdownHover();
        }
    };
});

DiagnosticApp.directive('modalShow', function () {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            scope.$watch(attrs.modalShow, function (value) {
                if (value)
                    element.modal('show');
                else
                    element.modal('hide');
            });
        }
    };
});

DiagnosticApp.directive('regexValidate', function () {
    return {
        // restrict to an attribute type.
        restrict: 'A',
        // element must have ng-model attribute.
        require: 'ngModel',
        // scope = the parent scope
        // elem = the element the directive is on
        // attr = a dictionary of attributes on the element
        // ctrl = the controller for ngModel.
        link: function (scope, elem, attr, ctrl) {

            //get the regex flags from the regex-validate-flags="" attribute (optional)
            var flags = attr.regexValidateFlags || '';

            // create the regex obj.
            var regex = new RegExp(attr.regexValidate, flags);

            // add a parser that will process each time the value is 
            // parsed into the model when the user updates it.
            ctrl.$parsers.unshift(function (value) {
                // test and set the validity after update.
                var valid = regex.test(value);
                ctrl.$setValidity('regexValidate', valid);

                // if it's valid, return the value to the model, 
                // otherwise return undefined.
                return valid ? value : undefined;
            });

            // add a formatter that will process each time the value 
            // is updated on the DOM element.
            ctrl.$formatters.unshift(function (value) {
                // validate.
                ctrl.$setValidity('regexValidate', regex.test(value));

                // return the value or nothing will be written to the DOM.
                return value;
            });
        }
    };
});

DiagnosticApp.directive('onlyDigits', function () {
    return {
        require: 'ngModel',
        restrict: 'A',
        link: function (scope, element, attr, ctrl) {
            function inputValue(val) {
                if (val) {
                    var digits = val.replace(/[^0-9.]/g, '');

                    if (digits.split('.').length > 2) {
                        digits = digits.substring(0, digits.length - 1);
                    }

                    if (digits !== val) {
                        ctrl.$setViewValue(digits);
                        ctrl.$render();
                    }
                    return parseFloat(digits);
                }
                return undefined;
            }
            ctrl.$parsers.push(inputValue);
        }
    };
});

 DiagnosticApp.directive('icheck', ['$timeout', '$parse', function($timeout, $parse) {
    return {
      restrict: 'A',
      require: '?ngModel',
      link: function(scope, element, attr, ngModel) {
        $timeout(function() {
          var value = attr.value;
        
          function update(checked) {
            if(attr.type==='radio') { 
              ngModel.$setViewValue(value);
            } else {
              ngModel.$setViewValue(checked);
            }
          }
          
          $(element).iCheck({
           checkboxClass: attr.checkboxClass || 'icheckbox_square-blue',
            radioClass: attr.radioClass || 'iradio_square-blue'
          }).on('ifChanged', function(e) {
            scope.$apply(function() {
              update(e.target.checked);
            });
          });

          scope.$watch(attr.ngChecked, function(checked) {
            if(typeof checked === 'undefined') checked = !!ngModel.$viewValue;
            update(checked)
          }, true);

          scope.$watch(attr.ngModel, function(model) {
            $(element).iCheck('update');
          }, true);
          
        })
      }
    }
  }]);
  
  DiagnosticApp.directive('xcheck', ['$timeout', '$parse', function($timeout, $parse) {
    return {
      restrict: 'A',
      require: '?ngModel',
      link: function(scope, element, attr, ngModel) {
        $timeout(function() {
          var value = attr.value;
        
          function update(checked) {
            if(attr.type==='radio') { 
              ngModel.$setViewValue(value);
            } else {
              ngModel.$setViewValue(checked);
            }
          }
          
          $(element).iCheck({
            checkboxClass: attr.checkboxClass || 'icheckbox_xcheck-blue',
            radioClass: attr.radioClass || 'iradio_xcheck-blue'
          }).on('ifChanged', function(e) {
            scope.$apply(function() {
              update(e.target.checked);
            });
          });

          scope.$watch(attr.ngChecked, function(checked) {
            if(typeof checked === 'undefined') checked = !!ngModel.$viewValue;
            update(checked)
          }, true);

          scope.$watch(attr.ngModel, function(model) {
            $(element).iCheck('update');
          }, true);
          
        })
      }
    }
  }]);

DiagnosticApp.directive('stringToNumber', function() {
  return {
    require: 'ngModel',
    link: function(scope, element, attrs, ngModel) {
      ngModel.$parsers.push(function(value) {
        return '' + value;
      });
      ngModel.$formatters.push(function(value) {
        return parseFloat(value);
      });
    }
  };
});
DiagnosticApp.directive('validDate', function ($timeout) {
  return {
    scope: {
      ngModel: '='
    },
    bindToController: true,
    controllerAs: 'vm',
    link: function (scope, element, attrs, ctrl) {
      element.on('blur', function () {
        // using timeout instead of scope.$apply, notify angular of changes
        $timeout(function () {
          ctrl.ngModel = ctrl.ngModel || new Date();
        });
      });
    }, 
    controller: function () {}
  }
});