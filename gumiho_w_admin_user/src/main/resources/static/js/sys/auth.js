var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
jQuery(function ($) {
    jQuery(grid_selector).jqGrid({
        url: base + "sysRole/list",
        subGrid: false,
        datatype: "json",
        height: 'auto',
        colNames: ['id','所属系统','编号', '权限名称','创建时间','设置资源'],
        colModel: [
            {name: 'id', index: 'id', width: 200, editable: true, hidden: true, key: true},
            {name: 'departmentName', index: 'departmentName', width: 90},
            {name: 'id', index: 'id', width: 90},
            {name: 'roleName', index: 'roleName', width: 90},
            {name: 'fcreateTime', index: 'fcreateTime', width: 90},
            {name:'id',index:'id',width:90, formatter : function(cellvalue, options, rowObject) {return '' +
                '<a href="javascript:void(0);" onclick="editAuth(\'' + rowObject.roleId + '\',\'' + rowObject.roleName + '\')" > <i class="ace-icon fa fa-pencil-square-o bigger-200"></i></a>'}
            }
        ],
        jsonReader: {
            root: "list",
            page: "pageNum",
            total: "pages",
            records: "total"
        },
        pgtext: "第 {0} 页，共 {1}页",
        recordtext: "第 {0} 到 {1} 共 {2} 条",
        viewrecords: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: pager_selector,
        altRows: true,
        multiselect: true,
        multiboxonly: true,
        loadComplete: function () {
            var table = this;
            setTimeout(function () {
                updatePagerIcons(table);
            }, 0);
        },
        caption: "",
        autowidth: true
    });

    function updatePagerIcons(table) {
        var replacement =
            {
                'ui-icon-seek-first': 'ace-icon fa fa-angle-double-left bigger-140',
                'ui-icon-seek-prev': 'ace-icon fa fa-angle-left bigger-140',
                'ui-icon-seek-next': 'ace-icon fa fa-angle-right bigger-140',
                'ui-icon-seek-end': 'ace-icon fa fa-angle-double-right bigger-140'
            };
        $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function () {
            var icon = $(this);
            var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
            if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
        })
    }

    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {
            edit: true,
            edittitle:'修改',
            editfunc: edit,
            add: true,
            addtitle:'添加',
            addicon : 'ace-icon fa fa-plus-circle purple',
            addfunc : add,
            del: true,
            deltitle:'删除',
            delfunc: delrole,
            search:false,
            refresh: true,
            refreshtitle: '刷新',
            refreshicon: 'ace-icon fa fa-refresh green',
            alertcap:'提示',
            alerttext: '请选择一条记录'
        }
    )
});

var departmentList = getAllDepartments();
debugger
var optionhtml =  "<option value= '' >-----所有子系统-----</option>";
$("#search-departmentId").html(optionhtml+departmentList);

//模糊查询
$("#auth-search").bind("click", function () {
    debugger
    var keyword = $("#auth-keyword-1").val();
    var departmentId = $("#search-departmentId").val();
    $("#grid-table").jqGrid("setGridParam",{page:1});
    $("#grid-table").jqGrid('setGridParam', {
        postData: {"keyword": encodeURI(keyword),"departmentId":departmentId}
    }).trigger('reloadGrid');

});

var editAuth = function(roleId,roleName){
    if(!isEmpty(roleId)){
        $("#tree-modal").show();
        $("#tree-modal-reload").show();
        $("#tree-modal-body").hide();
        $("#tree-modal-title").text("角色名称："+roleName);
        $("#tree-modal-role-id").val(roleId);
        $("#tree").tree({
            cascadeCheck:'true',
            url : base + "auth/manage/getResourceJson?roleId="+roleId+"&"+new Date().getTime(),
            method:'get',
            animate:true,
            checkbox:true,
            onLoadSuccess:function(node, data) {
                $("#tree-modal-reload").hide();
                $("#tree-modal-body").show();
            },
            onLoadError:function(arguments){
                alert("加载失败！");
            }
        });
    }
}

$("#tree-modal-close-btn").bind("click",function(){
    $("#tree-modal").hide();
})

var treeSave = function(){
    $("#tree-modal-save-ing").show();
    $("#tree-modal-save").hide();
    var nodes = $('#tree').tree('getChecked', ['checked','indeterminate']);

    var resourceIds = "";
    for(var index in nodes){
        resourceIds += "," + nodes[index].id;
    }
    var roleId = $("#tree-modal-role-id").val();
    if(isNotEmpty(roleId)){
        $.ajax({
            url:base + "auth/manage/authSave",
            data:{resourceIds:resourceIds,roleId:roleId},
            async:false,
            dataType:"json",
            type:"POST",
            success:function(data){
                if("success" == data.status){
                    Modal.alert({
                        msg : "修改成功！",
                        title : "提示",
                        btnok : "确定"
                    })
                    $("#tree-modal").hide();
                }else{
                    Modal.alert({
                        msg : data.message,
                        title : "提示",
                        btnok : "确定"
                    })
                }
            },
            error:function(data){
                alert("系统异常，请稍后重试");
            }
        })
        $("#tree-modal-save-ing").hide();
        $("#tree-modal-save").show();
    }else{
        alert("参数缺失");
    }
}


function checkrole(){
    var result = true;
    $.ajax({
        async:false,
        type: "POST",
        url: base + "sysRole/checkRoles",
        data:{'roleName':encodeURI($("#roleName").val())},
        dataType: "json",
        success:function(data){
            if(data == 1){
                alert("该角色已存在!");
                result = false;
            }
        }
    })
    return result;
}
/* function notnull(){
 var result = true;
 if($("#roleNames").val() == null){
 result = false;
 }
 }*/


function edit(id) {
    var rowData = $("#grid-table").jqGrid('getRowData',id);
    $("#updateModal-type").modal("show");
    $("#departmentName").val(rowData.departmentName);
    editInt(id);
    $("#reolid").val(id);

}

function editInt(id) {

    $.ajax({
        async: false,
        type: "POST",
        url: base + "sysRole/selectRoles",
        data:{id:id},
        dataType: "json",
        success: function (data) {
            console.log(data);
            var type = data;
            $("#reolid").val(type.id);
            $("#roleNames").val(type.roleName);

        }
    });
}

$("#updaterole").on("click", function () {
    $.ajax({
        async: false,
        type: "POST",
        url: base + "sysRole/updateRoles",
        data: $("#resources-form-typeup").serialize(),
        dataType: "json",
        success: function (data) {
            alert(data);
            if (data == "修改成功!") {
                Modal.alert({
                    msg : "修改成功！",
                    title : "提示",
                    btnok : "确定",
                    btncl : "取消"
                })
                $("#updateModal-type").modal("hide");
                jQuery(grid_selector).trigger("reloadGrid");
            } else {
                Modal.alert({
                    msg : "修改失败！",
                    title : "提示",
                    btnok : "确定",
                    btncl : "取消"
                })
            }
        }
    })
    $("#updateModal-type").modal("hide");
    jQuery(grid_selector).trigger("reloadGrid");

});

function delrole(id) {
    $.ajax({
        async: false,
        url: base + "sysRole/delRoles?id=" + id,
        dataType: "json",
        success: function (data) {
            Modal.alert({
                msg : "删除成功！",
                title : "提示",
                btnok : "确定",
                btncl : "取消"
            })
            jQuery(grid_selector).trigger("reloadGrid");
        }
    })
    jQuery(grid_selector).trigger("reloadGrid");
}

function add(){
    $("#roleName").val("");// 手动清空
    $("#addModal-type").modal("show");//打开模态窗口
    var htmlVal = getAllDepartments();
    $("#departmentId").html(htmlVal);
}

function getAllDepartments() {
    var htmlVal = "";
    $.ajax({
        async: false,
        type: "POST",
        url: base + "sysDepartment/getAllForSysRole",
        dataType: "json",
        success: function (data) {
            if(data != null){
                if(data.sysDepartmentVOList != null && data.sysDepartmentVOList.length > 0){
                    for(var i = 0;i < data.sysDepartmentVOList.length;i++){
                        htmlVal += "<option value='"+data.sysDepartmentVOList[i].departmentId+"'>"+data.sysDepartmentVOList[i].departmentName+"</option>";
                    }
                }
            }
        },
        error: function (data) {
/*   $("#").attr("disabled",false);*/
            alert("系统异常，请稍后重试");
        }
    });
    return htmlVal;
}

$("#addrole").on('click',function(){
    if (!checkrole())return;
    /*if(!notnull())return;*/
    $.ajax({
        async: false,
        type: "POST",
        url: base+"sysRole/addRoles",
        data: {'rolename': encodeURI($("#roleName").val()),'departmentId':$("#departmentId").val()},
        dataType: "json",
        success: function (data) {
            if("success" == data.status){
                Modal.alert({
                    msg : "添加成功！",
                    title : "提示",
                    btnok : "确定",
                    btncl : "取消"
                })
            }else {
                Modal.alert({
                    msg : "添加失败,请稍后重试!",
                    title : "提示",
                    btnok : "确定",
                    btncl : "取消"
                })
            }

        }
    })
    $("#addModal-type").modal("hide");
    jQuery(grid_selector).trigger("reloadGrid");
})

