jQuery(function ($) {
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";
    jQuery(grid_selector).jqGrid({
        url: base + "sysUser/userBlacklist",
        subGrid: false,
        datatype: "json",
        height: 'auto',
        colNames: ['userId', '用户状态', '所属系统','登录名', '手机号',  '职称', '创建时间'],
        colModel: [
            {name: 'userId', index: 'userId', width: 200, editable: true, hidden: true, key: true},
            {
                name: 'status', index: 'status', width: 90, fixed: true, sortable: false, resize: false,
                formatter: function (cellvalue, options, rowObject) {
                    return "<input id='id-check-horizontal' type='checkbox' class='ace ace-switch ace-switch-3' onclick='updateStatus(\"" + rowObject.userId + "\",1,this)' /> <span class='lbl'></span>";
                }
            },
            {name: 'departmentName', index: 'departmentName', width: 90},
            {name: 'loginName', index: 'loginName', width: 90},
            {name: 'phone', index: 'phone', width: 90},
            {name: 'userName', index: 'userName', width: 90},
            {name: 'fcreateTime', index: 'createTime', width: 100}
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
            edit: false,
            edittext: '修改',
            edittitle: '修改管理员用户',
            add: false,
            addtext: '添加',
            addtitle: '添加管理员用户',
            addicon: 'ace-icon fa fa-plus-circle purple',
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
        var keyword = $("#user-keyword-1").val();
        var departmentId = $("#search-departmentId").val();
        $("#grid-table").jqGrid("setGridParam",{page:1});
        $("#grid-table").jqGrid('setGridParam', {
            postData: {"keyword": encodeURI(keyword),"departmentId":departmentId}
        }).trigger('reloadGrid');
    });

});

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


var updateStatus = function (userId, status, checkbox) {
    if (confirm("确认启用该用户,并移除黑名单吗？")) {
        debugger;
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
                    $(checkbox).prop("checked", false);
                }
            },
            error: function (data) {
                Modal.alert({msg: "系统异常，请稍后重试", title: "提示", btnok: "确定"});
                $(checkbox).prop("checked", false);
            }
        })
    }

    else {
        $(checkbox).prop("checked", false);
    }
};

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
