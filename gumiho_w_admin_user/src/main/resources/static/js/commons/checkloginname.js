

$(function(){
    // 查看是否已经登录，如果已经登录查询登录信息
    $.ajax({
        async: true,
        url : baseMenu +'/menu/getLoginName',
        dataType : 'json',
        cache:true,
        type:'POST',
        success : function(data){

            if(data != null){
                $(".user-info").append(data.loginName);
            }
        },
        error: function(data){
            alert("用户名加载失败，请稍后重试");
        }
    });

});
