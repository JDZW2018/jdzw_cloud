
function getBaseMenuPath() {
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    var localhostPaht = curWwwPath.substring(0, pos);
    return localhostPaht;
}
var baseMenu = getBaseMenuPath();


$(function(){
    function createMenu(data) {
        var v = "";

        $.each(data,function(i,n) {
            if(n.childs) {
                v += '<li class="'+n.active+' '+n.open+'"><a href="#" class="dropdown-toggle" ><i class="menu-icon fa ';
                v += n.icon;
                v += '"></i> <span class="menu-text">'+n.name+'</span> ';
                v += '<b class="arrow fa fa-angle-down"></b>';
                v += '</a> <b class="arrow"></b> ';
                v += '<ul class="submenu">';
                v += createMenu(n.childs);
                v += '</ul></li>';
            }else {
                v += '<li class="'+n.active+' '+n.open+'"> <a href="'+ baseMenu +n.url+'"><i class="menu-icon fa '+n.icon+'"></i> <span class="menu-text">'+n.name+'</span> ';
                v += '</a> <b class="arrow"></b></li>';
            }

        });
        return v;
    }

    $.ajax({
        url: baseMenu + "/menu/get",
        data: {'menuId':$("#menuId").val()},
        cache:true,
        success: function(data){
            if(data.result == 1) {
                $("#menu").html(createMenu(data.data));
                $("#department").append(data.data[0].name);
            }else {
                window.location.href = "/login";
            }

        },
        error: function(data){
            alert("获取菜单失败");
        }
    });
});
