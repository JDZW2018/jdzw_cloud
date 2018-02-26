function getRootPath() {
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPaht = curWwwPath.substring(0, pos);
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
}
var base = getRootPath() + '/';

Date.prototype.pattern=function(fmt) {
    var o = {
        "M+" : this.getMonth()+1, //月份
        "d+" : this.getDate(), //日
        "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
        "H+" : this.getHours(), //小时
        "m+" : this.getMinutes(), //分
        "s+" : this.getSeconds(), //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S" : this.getMilliseconds() //毫秒
    };
    var week = {
        "0" : "/u65e5",
        "1" : "/u4e00",
        "2" : "/u4e8c",
        "3" : "/u4e09",
        "4" : "/u56db",
        "5" : "/u4e94",
        "6" : "/u516d"
    };
    if(/(y+)/.test(fmt)){
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    if(/(E+)/.test(fmt)){
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);
    }
    for(var k in o){
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
};

/**
 * 清空form表单
 * @param formId 表单Id
 */
var emptyForm = function(formId){
    $(':input','#'+formId).not(':button, :submit, :reset, :hidden,:radio').val('').removeAttr('checked').removeAttr('selected');
}

// 空字符串
var isEmpty = function isEmpty(str) {
    if ((null == str) || (str.toString().replace(/\s+/g, "") == "")) {
        return true;
    } else {
        return false;
    }
}

var isNotEmpty = function isEmpty(str) {
    if ((null == str) || (str.toString().replace(/\s+/g, "") == "")) {
        return false;
    } else {
        return true;
    }
}

var isMoney = function isMoney(money){
    var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
    //var money = "520.100";
    //000 错
    //0 对
    //0. 错
    //0.0 对
    //050 错
    //00050.12错
    //70.1 对
    //70.11 对
    //70.111错
    //500 正确
    if (reg.test(money)) {
        return true;
    }else{
        return false;
    }
}


