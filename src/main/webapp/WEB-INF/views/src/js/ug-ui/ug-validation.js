/**
 * Created by Jeng on 2015/12/17.
 */
/*jshint globalstrict:true*/
/*global angular:false*/
(function(){
    // Create global xmg obj and its namespaces
    // build processes may have already created an xmg obj
    window.ug = window.ug || {};
    window.ug.version = '1.0.0';
(function(angular){
    'use strict';
/**
 * 校验规则表达式
 */
var Regex_Rules = {
    // 正数字
    positiveIntegerRegex: /^[0-9]+$/,
    // 整数
    integerRegex : /^\-?[0-9]+$/,
    // 正浮点数字
    positiveDecimalRegex : /^[0-9]*\.?[0-9]+$/,
    // 浮点数字
    decimalRegex : /^\-?[0-9]*\.?[0-9]+$/,
    // email
    emailRegex : /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
    // 大小写字母或数字
    notSpecialCharacterRegex : /^[A-Za-z0-9]+$/i,
    // 非汉字
    notChineseRegex : /^[\u4E00-\u9FA5]+$/i,
    // 中国身份证
    chinaIdRegex : /^[1-9]\d{5}[1-9]\d{3}(((0[13578]|1[02])(0[1-9]|[12]\d|3[0-1]))|((0[469]|11)(0[1-9]|[12]\d|30))|(02(0[1-9]|[12]\d)))(\d{4}|\d{3}[xX])$/,
    // 中国邮政编码
    chinaZipRegex : /^\d{6}$/,
    // 手机号码
    telephoneRegex : /^(1)[0-9]{10}$/,
    // IP
    ipRegex : /^((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})$/
};

function $ugValidateProvider() {
    return {
        defaultMessages: {
            required        : "选项必填",
            minlength       : "字符长度不能小于{minlength}",
            maxlength       : "字符长度不能大于{maxlength}",
            email           : "邮件格式不正确",
            repeat          : "两次输入值不一致",
            pattern         : "格式不正确",
            number          : "必须输入数字",
            remote          : "校验不通过",
            url             : "URL格式不正确",
            specialCharacter: "仅支持大小写字母及数字",
            positiveInteger : "仅支持正整数值",
            positiveDecimal : "仅支持正浮点数值",
            notEmpty        : "选项必填"
        }
    }
}


// 字段描述
var $fieldName = [function() {
    return {
        restrict: 'A',
        require:"ngModel",
        link: function(scope, element, attr) {
            scope.fieldName = attr.fieldName;
        }
    };
}];
var $ugNotEmpty = [function () {
    return {
        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl) {
            ctrl.$parsers.push(function(viewValue){
                if(viewValue){
                    if(angular.isArray(viewValue) && viewValue.length == 0){
                        ctrl.$setValidity('notEmpty', false);
                        return viewValue;
                    }else{
                        ctrl.$setValidity('notEmpty', true);
                        return viewValue;
                    }
                } else{
                    ctrl.$setValidity('notEmpty', false);
                    return viewValue;
                }
            });
        }
    }
}];
// 非特殊字符校验
var $ugNotSpecialCharacter = ["Regex_Rules", function (Regex_Rules) {
    return {
        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl) {
            ctrl.$parsers.push(function(viewValue){
                var ruleRegex = new RegExp(Regex_Rules.notSpecialCharacterRegex);
                if(ruleRegex.test(viewValue)){
                    ctrl.$setValidity('specialCharacter', true);
                    return viewValue;
                } else{
                    ctrl.$setValidity('specialCharacter', false);
                    return viewValue;
                }
            });
        }
    }
}];
// 正整数
var $ugPositiveInteger = ["Regex_Rules", function (Regex_Rules) {
    return {
        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl) {
            ctrl.$parsers.push(function(viewValue){
                var ruleRegex = new RegExp(Regex_Rules.positiveIntegerRegex);
                if(ruleRegex.test(viewValue)){
                    ctrl.$setValidity('positiveInteger', true);
                    return viewValue;
                } else{
                    ctrl.$setValidity('positiveInteger', false);
                    return viewValue;
                }
            });
        }
    }
}];
// 正浮点数
var $ugPositiveDecimal = ["Regex_Rules", function (Regex_Rules) {
    return {
        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl) {
            ctrl.$parsers.push(function(viewValue){
                var ruleRegex = new RegExp(Regex_Rules.positiveDecimalRegex);
                if(ruleRegex.test(viewValue)){
                    ctrl.$setValidity('positiveDecimal', true);
                    return viewValue;
                } else{
                    ctrl.$setValidity('positiveDecimal', false);
                    return viewValue;
                }
            });
        }
    }
}];
// 手机号码校验
var $ugTelephone = ["Regex_Rules", function (Regex_Rules) {
    return {
        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl) {
            ctrl.$parsers.push(function(viewValue){
                var ruleRegex = new RegExp(Regex_Rules.telephoneRegex);
                if(ruleRegex.test(viewValue)){
                    ctrl.$setValidity('pattern', true);
                    return viewValue;
                } else{
                    ctrl.$setValidity('pattern', false);
                    return viewValue;
                }
            });
        }
    }
}];
// 中国邮政编码校验
var $ugChinaZip = ["Regex_Rules", function (Regex_Rules) {
    return {
        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl) {
            ctrl.$parsers.push(function(viewValue){
                var ruleRegex = new RegExp(Regex_Rules.chinaZipRegex);
                if(ruleRegex.test(viewValue)){
                    ctrl.$setValidity('pattern', true);
                    return viewValue;
                } else{
                    ctrl.$setValidity('pattern', false);
                    return viewValue;
                }
            });
        }
    }
}];

// 中国身份证校验
var $ugChinaIdentityCard = ["Regex_Rules", function (Regex_Rules) {
    return {
        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl) {
            ctrl.$parsers.push(function(viewValue){
                var ruleRegex = new RegExp(Regex_Rules.chinaIdRegex);
                if(ruleRegex.test(viewValue)){
                    ctrl.$setValidity('pattern', true);
                    return viewValue;
                } else{
                    ctrl.$setValidity('pattern', false);
                    return viewValue;
                }
            });
        }
    }
}];
// 相等值校验
var $ugEqualTo = [function () {
    return {
        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl) {
            var otherInput = elem.inheritedData("$formController")[attrs.ugEqualTo];
            ctrl.$parsers.push(function(viewValue){
                if(viewValue == otherInput.$viewValue){
                    ctrl.$setValidity('repeat', true);
                    return viewValue;
                } else{
                    ctrl.$setValidity('repeat', false);
                    return viewValue;
                }
            });
        }
    }
}];
// 远程异步校验
var $ugRemote = ['$http', function($http) {
    return {
        restrict:"A",
        require: 'ngModel',
        link: function(scope, ele, attrs, ctrl) {
            $(ele).blur(function(){
                var modelVal = scope.$eval(attrs.ngModel);
                if(!modelVal){
                    return;
                }
                var remoteConfig = scope.$eval(attrs.ugRemote);
                if(!remoteConfig || !angular.isFunction(remoteConfig.params)){
                    return;
                }
                var url = remoteConfig.url;
                var params = remoteConfig.params();
                var callback = remoteConfig.callback;
                $http({
                    method: 'get',
                    url: url, //根据换成自己的url
                    params: params
                }).then(function(data) {
                    if(angular.isFunction(callback)){
                        if(callback(data) == true){
                            ctrl.$setValidity('remote', true);
                        }else{
                            ctrl.$setValidity('remote', false);
                        }
                    }else{
                        if(data.data && data.data.result == true){
                            ctrl.$setValidity('remote', true);
                        }else{
                            ctrl.$setValidity('remote', false);
                        }
                    }
                }, function(data) {
                    ctrl.$setValidity('remote', false);
                });
            });
        }
    };
}];
// 校验框架
var $ugValidator = ["$ugValidateProvider", function($ugValidateProvider) {
    return {
        restrict: 'A',
        scope:{
            ugValidator:"="
        },
        require:"form",
        link: function(scope, element, attr) {
            scope.validator = scope.ugValidator;
            var formAttr = scope.$parent[attr.name];
            var getMessage = function(name, type){
                var validator = scope.validator;
                var messages = validator.messages;
                var currentMessage;
                if(messages && messages[name] && messages[name][type]){
                    return messages[name][type];
                }
                currentMessage = $ugValidateProvider.defaultMessages[type];
                var fieldElem = $("[name='"+name+"']");
                if(currentMessage && fieldElem){
                    switch (type){
                        case "required":
                            if(fieldElem[0]['tagName'] == "SELECT"){
                                currentMessage = "选项必选";
                            }
                            break;
                        case "minlength":
                            currentMessage = currentMessage.replace("{minlength}", fieldElem.attr("ng-minlength"));
                            break;
                        case "maxlength":
                            currentMessage = currentMessage.replace("{maxlength}", fieldElem.attr("ng-maxlength"));
                            break;
                        default :
                            break;
                    }
                    var fieldName = fieldElem.attr("field-name");
                    if(fieldName){
                        return fieldName +"：" + currentMessage;
                    }
                    return currentMessage;
                }
            };
            angular.extend(scope.validator, formAttr, {
                valid:function(){
                    if(!formAttr.$valid){
                        this.showError();
                    }
                    return formAttr.$valid;
                },
                validField:function(name){
                    var errors = name.$error;
                    if(errors){
                        angular.forEach(errors, function(val, key){
                            var errorMsg = getMessage(name.$name, key);
                            alert(errorMsg);
                        });
                    }
                    return name.$valid;
                },
                showError: function(){
                    var errors = formAttr.$error;
                    if(errors){
                        angular.forEach(errors, function(val, key){
                            var errorMsg = getMessage(val[0].$name, key);
                            alert(errorMsg);
                        });
                    }
                }
            });
        }
    };
}];
    /**
     * 校验框架
     */
angular.module('ug.validation', ["ng"])
    .constant("Regex_Rules", Regex_Rules)
    .factory('$ugValidateProvider', $ugValidateProvider)
    .directive('fieldName', $fieldName)
    .directive('ugNotSpecialCharacter', $ugNotSpecialCharacter)
    .directive('ugPositiveInteger', $ugPositiveInteger)
    .directive('ugPositiveDecimal', $ugPositiveDecimal)
    .directive('ugTelephone', $ugTelephone)
    .directive('ugChinaZip', $ugChinaZip)
    .directive('ugChinaIdentityCard', $ugChinaIdentityCard)
    .directive('ugEqualTo', $ugEqualTo)
    .directive('ugRemote', $ugRemote)
    .directive('ugNotEmpty', $ugNotEmpty)
    .directive('ugValidator', $ugValidator);


}(angular));
})();