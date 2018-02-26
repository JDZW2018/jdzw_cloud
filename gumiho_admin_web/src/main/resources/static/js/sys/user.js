var dbUserRole = new Object();
jQuery(function($) {
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";
    jQuery(grid_selector).jqGrid({
        url : base + "sysUser/list",
        subGrid : false,
        datatype: "json",
        height: 'auto',
        colNames:['id','登录名','用户名','昵称','手机号','创建时间','状态','角色名称','操作'],
        colModel:[
            {name: 'id', index: 'id', width: 200, editable: true, hidden:true, key: true},
            {name:'loginName',index:'loginName',width:90},
            {name:'userName',index:'userName', width:90},
            {name:'nickName',index:'nickName', width:90},
            {name:'phone',index:'phone', width:90},
            {name:'fcreateTime',index:'createTime', width:100},
            {name:'strStatus',index:'status', width:90},
            {name:'roleName',index:'roleName', width:100},
            {name:'id',index:'id', width:90, fixed:true, sortable:false, resize:false,
                formatter: function(cellvalue, options, rowObject) {
                    return '<a type="button" class="btn btn-white btn-sm btn-primary" onclick="editPwd(\''+cellvalue+'\')">修改密码</a>';
                }
            }
        ],
        jsonReader : {
            root:"list",
            page: "pageNum",
            total: "pages",
            records: "total"
        },
        pgtext : "第 {0} 页，共 {1}页",
        recordtext : "第 {0} 到 {1} 共 {2} 条",
        viewrecords : true,
        rowNum:20,
        rowList:[10,20,30],
        pager : pager_selector,
        altRows: true,
        multiselect: true,
        //multikey: "ctrlKey",
        //toppager: true,
        multiboxonly: true,
        loadComplete : function() {
            var table = this;
            setTimeout(function(){
                updatePagerIcons(table);
                enableTooltips(table);
            }, 0);
        },
        caption: "",
        autowidth: true
    });

    $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size

    //操作按钮，true为显示，false为隐藏，xxxfunc:调用函数,xxxicon:图标
    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {
            edit: true,
            edittext: '修改',
            edittitle: '修改管理员用户',
            editfunc: edit,
            add: true,
            addtext: '添加',
            addtitle: '添加管理员用户',
            addicon : 'ace-icon fa fa-plus-circle purple',
            addfunc : add,
            del: false,
            delicon : ' ace-icon fa glyphicon glyphicon-trash',
            delfunc: deluser,
            search:false,
            refresh: true,
            refreshtitle: '刷新',
            refreshicon: 'ace-icon fa fa-refresh green',
            alertcap:'提示',
            alerttext: '请选择一条记录'
        }
    )

    //更新分页按钮
    function updatePagerIcons(table) {
        var replacement =
            {
                'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
                'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
                'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
                'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
            };
        $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
            var icon = $(this);
            var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
            if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
        })
    }

    function enableTooltips(table) {
        $('.navtable .ui-pg-button').tooltip({container:'body'});
        $(table).find('.ui-pg-div').tooltip({container:'body'});
    }

    $(document).one('ajaxloadstart.page', function(e) {
        $.jgrid.gridDestroy(grid_selector);
        $('.ui-jqdialog').remove();
    });

    $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
        _title: function(title) {
            var $title = this.options.title || '&nbsp;'
            if( ("title_html" in this.options) && this.options.title_html == true )
                title.html($title);
            else title.text($title);
        }
    }));

    //模糊查询
    $("#user-search").bind("click",function(){
        var keyword = $("#user-keyword-1").val();
        $("#grid-table").jqGrid('setGridParam',{
            postData:{"keyword":encodeURI(keyword)}
        }).trigger('reloadGrid');
    });

    //显示添加
    function add(){

        $("#addModal-type").modal("show");//打开模态窗口

        $("#role_checkbox_add").empty();

        //初始化角色
        dbUserRole = new Object();

        var roles = getRole();
        if(isNotEmpty(roles)){
            $("#role_checkbox_add").prepend(roles);
        }
    }

    //保存数据
    $("#adduser").on('click',function(){
        //复选框数值
        var roleIds = "";
        $("#addModal-type input[name='form-checkbox-role']:checked").each(function(){
            roleIds +="," + $(this).val();
        });
        $("#roleIds_add").val(roleIds);
        if(!verifyValue($("#username_add").val(),$("#loginname_add").val(),$("#nickname_add").val(),$("#phone_add").val())){
            return;
        }
        $("#adduser").attr("disabled",true);

        $.ajax({
            async: true,
            type: "POST",
            url: base + "sysuser/addUsers",
            data: $("#add-form-user").serialize(),
            dataType: "json",
            success: function (data) {
                $("#adduser").attr("disabled",false);
                if("success" == data.status){
                    Modal.alert({msg : "保存成功！", title : "提示", btnok : "确定", btncl : "取消"});
                    emptyForm('add-form-user');
                    $("#addModal-type").modal("hide");
                    jQuery(grid_selector).trigger("reloadGrid");
                }else{
                    Modal.alert({msg : data.message, title : "提示", btnok : "确定", btncl : "取消"});
                }
            },
            error:function(data){
                $("#adduser").attr("disabled",false);
                alert("系统异常，请稍后重试");
            }
        })
    })

    //删除操作
    function deluser(id) {
        $.ajax({
            async: false,
            url: base + "sysuser/delusers?id=" + id,
            dataType: "json",
            success: function (data) {
                Modal.alert({msg: "删除成功！", title: "提示", btnok: "确定", btncl: "取消"});
                jQuery(grid_selector).trigger("reloadGrid");
            }
        })
        jQuery(grid_selector).trigger("reloadGrid");
    }

    //修改操作
    function edit(id) {

        //清空表单
        emptyForm('user-form-update');

        //初始化角色
        dbUserRole = new Object();

        $("#role_checkbox_update").empty();


        var data = editInt(id);
        if(data == null && data == undefined){
            Modal.alert({msg: "数据请求失败！", title: "提示", btnok: "确定", btncl: "取消"});
            return;
        }

        $("#updateModal-type").modal("show");

        for(var user in data){
            $("#" +user + "_update").val(data[user]);
        }

        var roles = getRole();
        if(isNotEmpty(roles)){
            $("#role_checkbox_update").prepend(roles);
        }
    }

    $("#updateuser").click(function () {
        $("#updateuser").attr("disabled",true);
        //复选框数值
        var roleIds = "";
        $("#updateModal-type input[name='form-checkbox-role']:checked").each(function(){
            roleIds +="," + $(this).val();
        });
        $("#roleIds_update").val(roleIds);
        $.ajax({
            async: true,
            url: base + "sysuser/updateUser",
            type: "POST",
            data: $("#user-form-update").serialize(),
            dataType: "json",
            success: function (data) {
                $("#updateuser").attr("disabled",false);
                if ("success" == data.status){
                    Modal.alert({msg: "修改成功！", title: "提示", btnok: "确定", btncl: "取消"});
                    jQuery(grid_selector).trigger("reloadGrid");
                    $("#updateModal-type").modal("hide");
                }else{
                    Modal.alert({msg: data.message, title: "提示", btnok: "确定", btncl: "取消"});
                }
            },
            error: function (data) {
                $("#updateuser").attr("disabled",false);
                alert("系统异常，请稍后重试");
            }
        });
    });

    $("#updatePwdUser").click(function () {
        $("#updatePwdUser").attr("disabled",true);
        var pwd = $("#pwd").val();
        var rePwd = $("#repwd").val();
        if(isEmpty(pwd)){
            Modal.alert({msg: "密码不能为空", title: "提示", btnok: "确定", btncl: "取消"});
            $("#updatePwdUser").attr("disabled",false);
            return false;
        }
        if(pwd != rePwd){
            Modal.alert({msg: "两次输入不一致", title: "提示", btnok: "确定", btncl: "取消"});
            $("#updatePwdUser").attr("disabled",false);
            return false;
        }
        $.ajax({
            async: true,
            url: base + "sysuser/updateUserPwd",
            type: "POST",
            data: $("#user-form-updatePwd").serialize(),
            dataType: "json",
            success: function (data) {
                $("#updatePwdUser").attr("disabled",false);
                if ("success" == data.status){
                    Modal.alert({msg: "修改密码成功！", title: "提示", btnok: "确定", btncl: "取消"});
                    jQuery(grid_selector).trigger("reloadGrid");
                    $("#updatePwdModal-type").modal("hide");
                }else{
                    Modal.alert({msg: data.message, title: "提示", btnok: "确定", btncl: "取消"});
                }
            },
            error: function (data) {
                $("#updatePwdUser").attr("disabled",false);
                alert("系统异常，请稍后重试");
            }
        });
    });


    //校验对应数据
    var verifyValue = function(userName,loginName,nickName,phone){
        var result = false;
        if(isEmpty(userName)){
            Modal.alert({msg: "用户名不能为空！",title: "提示", btnok: "确定", btncl: "取消"});
            return result;
        }
        if(isEmpty(loginName)){
            Modal.alert({msg: "登录名不能为空！",title: "提示", btnok: "确定", btncl: "取消"});
            return result;
        }
        if(isEmpty(nickName)){
            Modal.alert({msg: "昵称不能为空！",title: "提示", btnok: "确定", btncl: "取消"});
            return result;
        }
        if(isEmpty(phone)){
            Modal.alert({msg: "手机号不能为空！",title: "提示", btnok: "确定", btncl: "取消"});
            return result;
        }
        if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(phone))){
            Modal.alert({msg: "手机号格式错误！",title: "提示", btnok: "确定", btncl: "取消"});
            return result;
        }
        result = true;
        return result;
    }

    //获取role角色
    var getRole = function(){
        var str = '';
        $.ajax({
            url:base + "sysrole/getRole",
            dataType:"json",
            async:false,
            success:function(data){
                if("success" == data.status){
                    var roleList = data.roleList;
                    if (isNotEmpty(roleList)) {
                        var sign = false;
                        for (var index in roleList) {
                            sign = false;
                            if(dbUserRole.length>0){
                                for(var y in dbUserRole){
                                    if(roleList[index].roleId == dbUserRole[y].roleId){
                                        sign = true;
                                    }
                                }
                            }
                            if(sign){
                                str +='<div class="checkbox">'
                                    + ' <label>'
                                    + '     <input name="form-checkbox-role" checked  type="checkbox" class="ace" value="'+roleList[index].roleId+'">'
                                + '     <span class="lbl">'+roleList[index].roleName+'</span>'
                                + ' </label>'
                                + '</div>';
                            }else{
                                str +='<div class="checkbox">'
                                    + ' <label>'
                                    + '     <input name="form-checkbox-role" type="checkbox" class="ace" value="'+roleList[index].roleId+'">'
                                + '     <span class="lbl">'+roleList[index].roleName+'</span>'
                                + ' </label>'
                                + '</div>';
                            }
                        }
                    }
                }
            },
            error:function(data){
                alert("系统异常，请稍后重试");
            }
        })
        return str;
    }
});

var editPwd = function(id){

    //清空表单
    emptyForm('user-form-updatePwd');
    var data = editInt(id);
    if(data == null && data == undefined){
        Modal.alert({msg: "数据请求失败！", title: "提示", btnok: "确定", btncl: "取消"});
        return;
    }
    $("#updatePwdModal-type").modal("show");
    for(var user in data){
        $("#" +user + "_updatePwd").val(data[user]);
    }
}

//查出对应的用户信息
function editInt(id) {
    var user = "";
    $.ajax({
        async: false,
        type: "POST",
        url: base + "sysuser/selectUser",
        data:{id:id},
        dataType: "json",
        success: function (data) {
            if("success" == data.status){
                user = data.user;
                dbUserRole = data.userRoleList;
            }
        },
        error:function(data){
            alert("系统异常，请稍后重试");
        }
    });
    return user;
}

