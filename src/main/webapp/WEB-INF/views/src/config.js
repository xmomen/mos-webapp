/**
 * Created by M on 2014/10/29.
 */
'use strict';

require.config({
    paths:{
        ngApp:"views/app",
        ugValidation:"js/ug-ui/ug-validation"
    },
    shim:{
        ngApp:{
            deps:["ugValidation"]
        }
    }
});
