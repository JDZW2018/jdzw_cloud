<#assign title="角色管理"/>
<#assign menuId="9"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<div class="col-xs-12">

    <table id="grid-table"></table>

    <div id="grid-pager"></div>

    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->
<div id="dialog">

</div>
<!-- 角色修改Modal -->
<div class="modal fade" id="updateModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">修改角色</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="resources-form-typeup" class="form-horizontal">
                    <input id="reolid" name="id" type="hidden">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName">角色名称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="roleNames" name="roleName" placeholder="角色名称"
                                   class="col-xs-12 col-sm-5" value="" onblur="checkrole()"/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="updaterole">保 存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<#--角色addModal-->
<!-- 角色修改Modal -->
<div class="modal fade" id="addModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加角色</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="add-form-typeup" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName">角色名称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="roleName" name="roleName" placeholder="角色名称"
                                   class="col-xs-12 col-sm-5" value="" onblur="checkrole()"/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="addrole">保 存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<#--add-end-->
<#include "../common/body_middle.ftl"/>
<#include "../common/bootstrap_modal.ftl">
<!-- page specific plugin scripts -->
<script src="${ctx}/assets/js/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/js/jquery.jqGrid.min.js"></script>
<script src="${ctx}/assets/js/grid.locale-en.js"></script>
<!-- inline scripts related to this page -->
<script src="${ctx}/assets/js/jquery-ui.min.js"></script>
<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/js/jquery.form.js"></script>
<script src="${ctx}/assets/js/bootstrap.modal.js"></script>
<script type="text/javascript">
    jQuery(function ($) {
        var grid_selector = "#grid-table";
        var pager_selector = "#grid-pager";


        var parent_column = $(grid_selector).closest('[class*="col-"]');
        //resize to fit page size
        $(window).on('resize.jqGrid', function () {
            $(grid_selector).jqGrid('setGridWidth', parent_column.width());
        });


        //resize on sidebar collapse/expand
        $(document).on('settings.ace.jqGrid', function (ev, event_name, collapsed) {
            if (event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed') {
                //setTimeout is for webkit only to give time for DOM changes and then redraw!!!
                setTimeout(function () {
                    $(grid_selector).jqGrid('setGridWidth', parent_column.width());
                }, 20);
            }
        });

        jQuery(grid_selector).jqGrid({
            //direction: "rtl",

            url: "${ctx}/sysrole/list",
            subGrid: false,
            datatype: "json",
            height: 325,
            colNames: ['id', '角色名称'],
            colModel: [
                {name: 'id', index: 'id', width: 200, editable: true, hidden: true, key: true},
                {name: 'roleName', index: 'roleName', width: 90}
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
            //toppager: true,
            multiselect: true,
            //multikey: "ctrlKey",
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

        //navButtons
        jQuery(grid_selector).jqGrid('navGrid', pager_selector,
                {
                    edit: true,
                    edittext :'修改',
                    edittitle : '修改角色名称',
                    editfunc: edit,
                    add: true,
                    addtext : '添加',
                    addtitle : '添加角色名称',
                    addicon : 'ace-icon fa fa-plus-circle purple',
                    addfunc : add,
                    del: true,
                    deltext : '删除',
                    deltitle : '删除角色名称',
                    delfunc: delrole,
                    refresh: true,
                    refreshtitle : '刷新',
                    refreshicon: 'ace-icon fa fa-refresh green',
                    alertcap : '提示',
                    alerttext : '请选择一条记录'
                }
        )


        function checkrole(){
            var result = true;
            $.ajax({
                async:false,
                type: "POST",
                url: "${ctx}/sysrole/checkRoles",
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

    <#--修改操作-->
        function edit(id) {
            $("#updateModal-type").modal("show");
            $("#reolid").val(id);
            editInt(id);
        }

        function editInt(id) {
            $.ajax({
                async: false,
                type: "POST",
                url: "${ctx}/sysrole/selectRoles?id=" + id,
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
            if (!checkrole())return;
           /* if(!notnull())return;*/
            $.ajax({
                async: false,
                type: "POST",
                url: "${ctx}/sysrole/updateRoles",
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
    <#--删除操作-->
        function delrole(id) {
            $.ajax({
                async: false,
                url: "${ctx}/sysrole/delRoles?id=" + id,
                dataType: "json",
                success: function (data) {
                    Modal.alert({
                        msg : "删除成功！",
                        title : "提示",
                        btnok : "确定",
                        btncl : "取消"
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
    <#--添加操作-->
        function add(){
            $("#addModal-type").modal("show");//打开模态窗口
        }

       $("#addrole").on('click',function(){
           if (!checkrole())return;
           /*if(!notnull())return;*/
           $.ajax({
               async: false,
               type: "POST",
               url: "${ctx}/sysrole/addRoles",
               data: {'rolename': encodeURI($("#roleName").val())},
               dataType: "json",
               success: function (data) {
                  if("添加成功" == data){
                      Modal.alert({
                          msg : "添加成功！",
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

        //replace icons with FontAwesome icons like above
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

        //var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

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

          <#--
                  $("#add_role").on('click', function (e) {
            e.preventDefault();

            var dialog = $("#dialog").load("${ctx}/sysrole/add").dialog({
                modal: true,
                title: "<div class='widget-header widget-header-small'><h4 class='smaller'>添加角色</h4></div>",
                title_html: true,
                buttons: [

                    {
                        text: "保存",
                        "class": "btn btn-primary btn-minier",
                        click: function () {
                            checkrole()
                            if (!checkrole())return;
                            $.ajax({
                                async: false,
                                type: "POST",
                                url: "${ctx}/sysrole/addRoles",
                                data: {'rolename': encodeURI($("#roleName").val())},
                                dataType: "json",
                                success: function (data) {
                                    return false;
                                }
                            })
                            $(this).dialog("close");
                            jQuery(grid_selector).trigger("reloadGrid");
                        }
                    },
                    {
                        text: "取消",
                        "class": "btn btn-minier",
                        click: function () {
                            $(this).dialog("close");
                            $(this).dialog("destroy");
                            $("#dialog").html("");
                        }
                    }
                ]
            });

            /**
             dialog.data( "uiDialog" )._title = function(title) {
						title.html( this.options.title );
					};
             **/
        });
            -->
    });
</script>
<#include "../common/body_bottom.ftl"/>
