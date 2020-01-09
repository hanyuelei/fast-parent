layui.define(function (exports) {
    var ctxPath = {
            main: function (param) {
            		var pathName = window.location.pathname.substring(1);  
            	    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));  
            	    var main=window.location.protocol + '//' + window.location.host;  
            	    if(webName!=null&&webName!=''){
            	    	main+='/' + webName;
            	    }
            	    return main;	
            },
    };


    exports('domain', ctxPath);
});
