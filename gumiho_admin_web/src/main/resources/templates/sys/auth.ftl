<#assign title="权限管理"/>
<#assign menuId="11"/>
<#include "../common/head_top.ftl"/>

<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/icon.css"/>

<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<div class="col-xs-12">

    <table id="grid-table"></table>

    <div id="grid-pager"></div>

    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->

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


<div id="tree-modal" class="modal fade in" tabindex="-1" style="display: none; padding-right: 17px;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="tree-modal-close-btn">×</button>
                <h3 class="smaller lighter blue no-margin">权限设置</h3>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <h1 class="smaller center grey" id="tree-modal-reload">
                    <i class="ace-icon fa fa-spinner fa-spin gray bigger-125"></i>
                    <small>加载中...</small>
                </h1>
                <div  id="tree-modal-body">
                    <div class="widget-box widget-color-blue2">
                        <div class="widget-header">
                            <h5 class="widget-title lighter smaller" id="tree-modal-title">
                            </h5>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main padding-8">
                                <ul id="tree" class="easyui-tree" role="tree"></ul>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="hidden" id="tree-modal-role-id">
                        <button class="btn btn-primary btn-sm" data-dismiss="modal" id="tree-modal-save"  onclick="treeSave()" >
                            <i class="ace-icon fa fa-check"></i>
                            保存
                        </button>
                        <button class="btn btn-sm" data-dismiss="modal" id="tree-modal-save-ing" style="display:none">
                            <i class="ace-icon fa fa-spinner fa-spin gray bigger-125"></i>
                            保存...
                        </button>
                    </div>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>
</div>
<#include "../common/body_middle.ftl"/>
<#include "../common/bootstrap_modal.ftl">
<!-- page specific plugin scripts -->
<script src="${ctx}/assets/js/jquery.jqGrid.min.js"></script>
<script src="${ctx}/assets/js/jquery.nestable.min.js"></script>
<script src="${ctx}/assets/js/grid.locale-en.js"></script>
<!-- inline scripts related to this page -->
<script src="${ctx}/assets/js/jquery-ui.min.js"></script>

<script src="${ctx}/assets/js/spin.js"></script>

<script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>
<script src="${ctx}/assets/js/bootstrap.modal.js"></script>
<script src="${ctx}/js/sys/auth.js?v=0.0.2"></script>

<#include "../common/body_bottom.ftl"/>