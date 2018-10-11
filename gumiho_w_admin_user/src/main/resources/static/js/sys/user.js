var dbUserRole = new Object();
jQuery(function ($) {
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";
    jQuery(grid_selector).jqGrid({
        url: base + "sysUser/list",
        subGrid: false,
        datatype: "json",
        height: 'auto',
        colNames: ['userId', '所属系统', '登录名', '手机号',  '职称', '创建时间','用户状态', '权限设置', '修改密码'],
        colModel: [
            {name: 'userId', index: 'userId', width: 200, editable: true, hidden: true, key: true},
            {name: 'departmentName', index: 'departmentName', width: 90},
            {name: 'loginName', index: 'loginName', width: 90},
            {name: 'phone', index: 'phone', width: 90},
            {name: 'userName', index: 'userName', width: 90},
            {name: 'fcreateTime', index: 'createTime', width: 100},
            {
                name: 'status', index: 'status', width: 90, fixed: true, sortable: false, resize: false,
                formatter: function (cellvalue, options, rowObject) {
                    return "<input id='id-check-horizontal' type='checkbox' class='ace ace-switch ace-switch-3' checked='checked' onclick='updateStatus(\"" + rowObject.userId + "\",0,this)' /> <span class='lbl'></span>";
                }
            },
            {
                name: 'roleName', index: 'roleName', width: 100, fixed: true, sortable: false, resize: false,
                formatter: function (cellvalue, options, rowObject) {
                    return '<a type="button" class="btn btn-white btn-sm btn-primary" onclick="editRole(\'' + rowObject.userId + '\',\'' + rowObject.departmentId + '\')"><i class="ace-icon fa fa-pencil-square-o bigger-200"></i></a>';
                }
            },
            {
                name: 'userId', index: 'userId', width: 90, fixed: true, sortable: false, resize: false,
                formatter: function (cellvalue, options, rowObject) {
                    return '<a type="button" class="btn btn-white btn-sm btn-primary" onclick="editPwd(\'' + cellvalue + '\')">修改密码</a>';
                }
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
        rowNum: 20,
        rowList: [10, 20, 30],
        pager: pager_selector,
        altRows: true,
        multiselect: true,
        //multikey: "ctrlKey",
        //toppager: true,
        multiboxonly: true,
        loadComplete: function () {
            var table = this;
            setTimeout(function () {
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
            addicon: 'ace-icon fa fa-plus-circle purple',
            addfunc: add,
            del: true,
            deltext: '回收站',
            deltitle:'删除至回收站',
            delfunc: updateRecycleBinStatus,
            search: false,
            refresh: true,
            refreshtitle: '刷新',
            refreshicon: 'ace-icon fa fa-refresh green',
            alertcap: '提示',
            alerttext: '请选择一条记录'
        }
    )

    //更新分页按钮
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

    function enableTooltips(table) {
        $('.navtable .ui-pg-button').tooltip({container: 'body'});
        $(table).find('.ui-pg-div').tooltip({container: 'body'});
    }

    $(document).one('ajaxloadstart.page', function (e) {
        $.jgrid.gridDestroy(grid_selector);
        $('.ui-jqdialog').remove();
    });

    $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
        _title: function (title) {
            var $title = this.options.title || '&nbsp;'
            if (("title_html" in this.options) && this.options.title_html == true)
                title.html($title);
            else title.text($title);
        }
    }));
    var departmentList = getAllDepartments();
    var optionhtml =  "<option value= '' >-----所有子系统-----</option>";
    $("#search-departmentId").html(optionhtml+departmentList);

    //模糊查询
    $("#user-search").bind("click", function () {
        debugger
        var keyword = $("#user-keyword-1").val();
        var departmentId = $("#search-departmentId").val();
        $("#grid-table").jqGrid("setGridParam",{page:1});
        $("#grid-table").jqGrid('setGridParam', {
            postData: {"keyword": encodeURI(keyword),"departmentId":departmentId}
        }).trigger('reloadGrid');

    });

    //显示添加
    function add() {

        $("#addModal-type").modal("show");//打开模态窗口

        $("#role_checkbox_add").empty();

       var htmlVal = getAllDepartments();
        $("#departmentId").html(htmlVal);
    }

    function getAllDepartments() {
        var htmlVal = "";
        $.ajax({
            async: false,
            type: "GET",
            url: base + "sysDepartment/getAllForSysUser",
            dataType: "json",
            success: function (data) {
                if (data != null) {
                    if (data.sysDepartmentVOList != null && data.sysDepartmentVOList.length > 0) {

                        for (var i = 0; i < data.sysDepartmentVOList.length; i++) {
                            htmlVal += "<option value='" + data.sysDepartmentVOList[i].departmentId + "'>" + data.sysDepartmentVOList[i].departmentName + "</option>";
                        }
                    }
                }
            },
            error: function (data) {
                alert("系统异常，请稍后重试");
            }
        });
        return htmlVal;
    }

    //保存数据
    $("#adduser").on('click', function () {
        if (!verifyValue($("#username_add").val(), $("#loginname_add").val(), $("#phone_add").val())) {
            return;
        }
        $("#adduser").attr("disabled", true);

        $.ajax({
            async: true,
            type: "POST",
            url: base + "sysUser/addUsers",
            data: $("#add-form-user").serialize(),
            dataType: "json",
            success: function (data) {
                $("#adduser").attr("disabled", false);
                if ("success" == data.status) {
                    Modal.alert({msg: "保存成功！", title: "提示", btnok: "确定", btncl: "取消"});
                    emptyForm('add-form-user');
                    $("#addModal-type").modal("hide");
                    jQuery(grid_selector).trigger("reloadGrid");
                } else {
                    Modal.alert({msg: data.message, title: "提示", btnok: "确定", btncl: "取消"});
                }
            },
            error: function (data) {
                $("#adduser").attr("disabled", false);
                alert("系统异常，请稍后重试");
            }
        })
    })

    //删除操作
    function deluser(userId) {
        $.ajax({
            async: false,
            url: base + "sysUser/delUsers?userId" + userId,
            dataType: "json",
            success: function (data) {
                Modal.alert({
                    msg: "删除成功！",
                    title: "提示",
                    btnok: "确定",
                    btncl: "取消"
                })
                jQuery(grid_selector).trigger("reloadGrid");
            }/*,
         error:function(data){
         Modal.alert({
         msg:"删除失败，请重试！",
         title:"提示",
         btnok:"确定",
         btncl:"取消"
         });
         }*/
        })
        jQuery(grid_selector).trigger("reloadGrid");
    }

    //修改操作
    function edit(userId) {
        debugger;
        //清空表单
        var rowData = $("#grid-table").jqGrid('getRowData',userId);
        emptyForm('user-form-update');
        var data = editInt(userId);
       /* var department = selectByUserId(userId);*/
        if (data == null && data == undefined) {
            Modal.alert({msg: "数据请求失败！", title: "提示", btnok: "确定", btncl: "取消"});
            return;
        }

        $("#updateModal-type").modal("show");


        $("#departmentName").val(rowData.departmentName);
        for (var user in data) {
            $("#" + user + "_update").val(data[user]);
        }
    }

    /*修改用户权限*/
    $("#updateUserRole").click(function () {
        $("#updateUserRole").attr("disabled", true);
        //复选框数值
        var roleIds = "";
        $("#updateUserRoleModal-type input[name='form-checkbox-role']:checked").each(function () {
            roleIds += "," + $(this).val();
        });
        $("#roleIds_updateUserRole").val(roleIds);
        $.ajax({
            async: true,
            url: base + "sysUser/updateSysUserRole",
            type: "POST",
            data: $("#user-form-updateUserRole").serialize(),
            dataType: "json",
            success: function (data) {
                $("#updateUserRole").attr("disabled", false);
                if ("success" == data.status) {
                    Modal.alert({msg: "修改成功！", title: "提示", btnok: "确定", btncl: "取消"});
                    jQuery(grid_selector).trigger("reloadGrid");
                    $("#updateUserRoleModal-type").modal("hide");
                } else {
                    Modal.alert({msg: data.message, title: "提示", btnok: "确定", btncl: "取消"});
                }
            },
            error: function (data) {
                $("#updateUserRole").attr("disabled", false);
                alert("系统异常，请稍后重试");
            }

        });

    });

    $("#updateuser").click(function () {
        $("#updateuser").attr("disabled", true);
        $.ajax({
            async: true,
            url: base + "sysUser/updateUser",
            type: "POST",
            data: $("#user-form-update").serialize(),
            dataType: "json",
            success: function (data) {
                $("#updateuser").attr("disabled", false);
                if ("success" == data.status) {
                    Modal.alert({msg: "修改成功！", title: "提示", btnok: "确定", btncl: "取消"});
                    jQuery(grid_selector).trigger("reloadGrid");
                    $("#updateModal-type").modal("hide");
                } else {
                    Modal.alert({msg: data.message, title: "提示", btnok: "确定", btncl: "取消"});
                }
            },
            error: function (data) {
                $("#updateuser").attr("disabled", false);
                alert("系统异常，请稍后重试");
            }
        });
    });


    $("#updatePwdUser").click(function () {
        $("#updatePwdUser").attr("disabled", true);
        var pwd = $("#pwd").val();
        var rePwd = $("#repwd").val();
        if (isEmpty(pwd)) {
            Modal.alert({msg: "密码不能为空", title: "提示", btnok: "确定", btncl: "取消"});
            $("#updatePwdUser").attr("disabled", false);
            return false;
        }
        if (pwd != rePwd) {
            Modal.alert({msg: "两次输入不一致", title: "提示", btnok: "确定", btncl: "取消"});
            $("#updatePwdUser").attr("disabled", false);
            return false;
        }
        $.ajax({
            async: true,
            url: base + "sysUser/updateUserPwd",
            type: "POST",
            data: $("#user-form-updatePwd").serialize(),
            dataType: "json",
            success: function (data) {
                $("#updatePwdUser").attr("disabled", false);
                if ("success" == data.status) {
                    Modal.alert({msg: "修改密码成功！", title: "提示", btnok: "确定", btncl: "取消"});
                    jQuery(grid_selector).trigger("reloadGrid");
                    $("#updatePwdModal-type").modal("hide");
                } else {
                    Modal.alert({msg: data.message, title: "提示", btnok: "确定", btncl: "取消"});
                }
            },
            error: function (data) {
                $("#updatePwdUser").attr("disabled", false);
                alert("系统异常，请稍后重试");
            }
        });
    });


    //校验对应数据
    var verifyValue = function (userName, loginName, phone) {
        var result = false;
        if (isEmpty(userName)) {
            Modal.alert({msg: "职称不能为空！", title: "提示", btnok: "确定", btncl: "取消"});
            return result;
        }
        if (isEmpty(loginName)) {
            Modal.alert({msg: "登录名不能为空！", title: "提示", btnok: "确定", btncl: "取消"});
            return result;
        }
        if (isEmpty(phone)) {
            Modal.alert({msg: "手机号不能为空！", title: "提示", btnok: "确定", btncl: "取消"});
            return result;
        }
        if (!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(phone))) {
            Modal.alert({msg: "手机号格式错误！", title: "提示", btnok: "确定", btncl: "取消"});
            return result;
        }
        result = true;
        return result;
    }

});
var updateRecycleBinStatus = function (userId) {
    if (confirm("确认将用户删除,并放入回收站吗？")) {
        debugger;
        var recycleBinStatus = 0;//状态0为回收站用户
        $.ajax({
            async: false,
            type: "POST",
            url: base + "sysUser/updateRecycleBinStatus",
            data: {'userId': userId.join(','), 'recycleBinStatus':  recycleBinStatus},
            dataType: "json",
            success: function (data) {
                if (data.status == "success") {
                    Modal.alert({msg: data.message, title: "提示", btnok: "确定"});
                    $("#grid-table").trigger("reloadGrid");
                } else {
                    Modal.alert({msg: data.message, title: "提示", btnok: "确定"});
                }
            },
            error: function (data) {
                Modal.alert({msg: "系统异常，请稍后重试", title: "提示", btnok: "确定"});
            }
        });
    }
};

var getRoleByDepartmentId = function (userId, departmentId) {
    var str = '';
    $.ajax({
        url: base + "sysRole/getRolesByDepartmentId",
        dataType: "json",
        data: {'userId': userId, 'departmentId': departmentId},
        async: false,
        success: function (data) {
            if ("success" == data.status) {
                var roleList = data.roleList;
                dbUserRole = data.userRoleList;
                if (isNotEmpty(roleList)) {
                    var sign = false;
                    for (var index in roleList) {
                        sign = false;
                        if (dbUserRole != null) {
                            for (var y in dbUserRole) {
                                if (roleList[index].roleId == dbUserRole[y].roleId) {
                                    sign = true;
                                }
                            }
                        }
                        if (sign) {
                            str += '<div class="checkbox">'
                                + ' <label>'
                                + '     <input name="form-checkbox-role" checked  type="checkbox" class="ace" value="' + roleList[index].roleId + '">'
                                + '     <span class="lbl">' + roleList[index].roleName + '</span>'
                                + ' </label>'
                                + '</div>';
                        } else {
                            str += '<div class="checkbox">'
                                + ' <label>'
                                + '     <input name="form-checkbox-role" type="checkbox" class="ace" value="' + roleList[index].roleId + '">'
                                + '     <span class="lbl">' + roleList[index].roleName + '</span>'
                                + ' </label>'
                                + '</div>';
                        }
                    }
                }
            }
        },
        error: function (data) {
            alert("系统异常，请稍后重试");
        }
    });
    return str;
}


//获取role角色
var getRole = function () {
    var str = '';
    $.ajax({
        url: base + "sysRole/getRole",
        dataType: "json",
        async: false,
        success: function (data) {
            if ("success" == data.status) {
                var roleList = data.roleList;
                if (isNotEmpty(roleList)) {
                    var sign = false;
                    for (var index in roleList) {
                        sign = false;
                        if (dbUserRole != null) {
                            for (var y in dbUserRole) {
                                if (roleList[index].roleId == dbUserRole[y].roleId) {
                                    sign = true;
                                }
                            }
                        }
                        if (sign) {
                            str += '<div class="checkbox">'
                                + ' <label>'
                                + '     <input name="form-checkbox-role" checked  type="checkbox" class="ace" value="' + roleList[index].roleId + '">'
                                + '     <span class="lbl">' + roleList[index].roleName + '</span>'
                                + ' </label>'
                                + '</div>';
                        } else {
                            str += '<div class="checkbox">'
                                + ' <label>'
                                + '     <input name="form-checkbox-role" type="checkbox" class="ace" value="' + roleList[index].roleId + '">'
                                + '     <span class="lbl">' + roleList[index].roleName + '</span>'
                                + ' </label>'
                                + '</div>';
                        }
                    }
                }
            }
        },
        error: function (data) {
            alert("系统异常，请稍后重试");
        }
    });
    return str;
}

var updateStatus = function (userId, status, checkbox) {
    debugger

    if (confirm("确认禁用该用户,并拉入黑名单吗？")) {
        $.ajax({
            async: false,
            type: "POST",
            url: base + "sysUser/updateStatus",
            data: {'userId': userId, 'status': status},
            dataType: "json",
            success: function (data) {
                if (data.status == "success") {
                    Modal.alert({msg: data.message, title: "提示", btnok: "确定"});
                    $("#grid-table").trigger("reloadGrid");
                } else {
                    Modal.alert({msg: data.message, title: "提示", btnok: "确定"});
                    $(checkbox).prop("checked", true);
                }
            },
            error: function (data) {
                Modal.alert({msg: "系统异常，请稍后重试", title: "提示", btnok: "确定"});
                $(checkbox).prop("checked", true);
            }
        })
    }

    else {
        $(checkbox).prop("checked", true);
    }
}

var editRole = function (userId, departmentId) {
    $("#role_checkbox_update1").html("");// 清空
    $("#updateUserRoleModal-type").modal("show");
    dbUserRole = new Object();
    var roles = getRoleByDepartmentId(userId, departmentId);
    if (isNotEmpty(roles)) {
        $("#role_checkbox_update1").html(roles);
    }
    $("#userId_updateUserRole").val(userId);
}


var editPwd = function (userId) {

    //清空表单
    emptyForm('user-form-updatePwd');
    var data = editInt(userId);
    if (data == null && data == undefined) {
        Modal.alert({msg: "数据请求失败！", title: "提示", btnok: "确定", btncl: "取消"});
        return;
    }
    $("#updatePwdModal-type").modal("show");
    for (var user in data) {
        $("#" + user + "_updatePwd").val(data[user]);
    }
}

//查出对应的用户信息
function editInt(userId) {
    var user = "";
    $.ajax({
        async: false,
        type: "POST",
        url: base + "sysUser/selectByUserId",
        data: {userId: userId},
        dataType: "json",
        success: function (data) {
            if ("success" == data.status) {
                user = data.user;
                /*   dbUserRole = data.userRoleList;*/
            }
        },
        error: function (data) {
            alert("系统异常，请稍后重试");
        }
    });
    return user;
};


/*暂时弃用*/
/*
function selectByUserId(userId) {
    var department = "";
    $.ajax({
        async: false,
        type: "POST",
        url: base + "sysDepartment/selectByUserId",
        data: {userId: userId},
        dataType: "json",
        success: function (data) {
            if ("success" == data.status) {
                department = data.sysDepartmentVO;
            }
        },
        error: function (data) {
            alert("系统异常，请稍后重试");
        }
    });
    return department;
}
*/

