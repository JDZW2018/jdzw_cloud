$('#seqno').ace_spinner({value:1,min:1,max:10000,step:1, touch_spinner: true, icon_up:'ace-icon fa fa-caret-up bigger-110', icon_down:'ace-icon fa fa-caret-down bigger-110'});
$('#menu-tree').tree({
    dnd: true,
    url: base + 'resource/getResourceJson',
    animate:true,
    onClick:function(node){
        viewResource(node.id);
    },
    onContextMenu: function(e, node){
        e.preventDefault();
        // 查找节点
        $('#tt').tree('select', node.target);
        // 显示快捷菜单
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY
        });
    }
});

$('#parentId').combotree ({
    dnd: true,
    url: base + 'resource/getMenuJson',
    animate:false,
    onClick:function(node){
    }
});

var viewResource = function(resourceId){

    $("#resourceId").val("");
    $(".combo-value").val("");
    $("#rightResource").show();
    $("#delResource").show();
    if(isNotEmpty(resourceId)){
        emptyForm('form-resource');
        $.ajax({
            url:base + 'resource/getResource',
            type:'POST',
            dateType:'json',
            data:{resourceId:resourceId},
            success:function(data){

                if(isNotEmpty(data) && "success" == data.status){
                    var resource = data.resource;
                    for(var str in resource){
                        $("#"+str).val(resource[str]);
                    }
                    $("#parentId").combotree('setValue',resource["parentId"]);
                }else{
                    Modal.alert({msg : data.message, title : "提示", btnok : "确定"})
                }
            },
            error:function(data){
                alert("系统异常，请稍后重试");
            }
        })
    }
}

$("#addResource").bind("click",function(){
    $.ajax({
        url:base + 'resource/operSysResource',
        type:'POST',
        dateType:'json',
        data:$("#form-resource").serialize(),
        success:function(data){
            if(isNotEmpty(data) && "success" == data.status){
                $("#menu-tree").tree('reload');
                emptyForm('form-resource');
            }
            Modal.alert({msg : data.message, title : "提示", btnok : "确定"});
        },
        error:function(data){
            alert("系统异常，请稍后重试");
        }
    })
})

$("#delResource").bind("click",function(){
    var resourceId = $("#resourceId").val();
    $.ajax({
        url:base + 'resource/deleteSysResource',
        type:'POST',
        dateType:'json',
        data:{"resourceId":resourceId},
        success:function(data){
            if("success" == data.status){
                $("#menu-tree").tree('reload');
                emptyForm('form-resource');
            }
            Modal.alert({msg : data.message, title : "提示", btnok : "确定"});
        },
        error:function(data){
            alert("系统异常，请稍后重试");
        }
    })
})

$("#resetResource").bind("click",function(){
    emptyForm('form-resource');
})

var append = function(){
    emptyForm('form-resource');
    $("#resourceId").val("");
    $(".combo-value").val("");
    $("#delResource").hide();
}


$("#closeResource").bind("click",function(){
    $("#rightResource").hide();
    emptyForm('form-resource');
})