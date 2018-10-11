$(function () {
    $.ajax({
        async: false,
        url : baseMenu +'/title/getTitleName',
        data:{'resourceId':$("#menuId").val()},
        dataType : 'json',
        type:'POST',
        cache:true,
        success : function(data){

            if(data != null){
                /* alert(data.titlename)*/
                $("#titleId").html(data.titlename);
                $("#headTitleId").html(data.titlename);
            }
        },
        error: function(data){
            alert("title加载失败，请稍后重试");
        }
    });
});