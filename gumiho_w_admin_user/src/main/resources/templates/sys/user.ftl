<#--<#assign menuId="cda3cded523d456dad5ba282ea5de934"/>-->
<#include "../common/head_top.ftl"/>
<input type="hidden" id="menuId" value="5c3689d4b7d84e56abb0cc206ce5a4ab"/><#--menu active-->
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-select.css" />
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<style>
    .dropdown-menu.inner.selectpicker{
        height: 200px;
    }
    .btn-group.bootstrap-select.show-tick.col-xs-12.col-sm-5{
        padding: 0px;
    }
</style>
<div class="col-xs-12 col-sm-12">
    <form class="form-horizontal">
        <div class="form-group">
            <div class="col-sm-2 col-xs-2">
                <select class="form-control" id="search-departmentId" name ="departmentId"></select>
            </div>
            <div class="col-sm-8 col-xs-8">
                <input type="text" id="user-keyword-1" placeholder="登录名、手机号" class="col-xs-2 ">
                 &nbsp;&nbsp;<button type="button" class="btn btn-info btn-sm" id="user-search">查询</button>
            </div>
        </div>
    </form>
    <table id="grid-table"></table>
    <div id="grid-pager"></div>
    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->

<div id="dialog">
</div>

<!-- 用户修改Modal -->
<div class="modal fade" id="updateModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">修改用户</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="user-form-update" class="form-horizontal">
                 <#--   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                -->    <input id="userId_update" name="userId" type="hidden">
                    <input id="roleIds_update" name="roleIds" type="hidden">
                   <#-- <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-2">关联前台用户: </label>
                        <div class="col-sm-9">
                            <input type="text" id="linkUserId_update" name="linkUserId_update" placeholder="前台用户ID" class="col-xs-12 col-sm-5" value="" onblur=""/>
                        </div>
                    </div>-->
                     <div class="form-group">
                         <label class="col-sm-3 control-label no-padding-right" for="recordName">所属系统:</label>
                         <div class="col-sm-9">
                             <input type="text" id="departmentName"<#-- name="departmentName"--> class="col-xs-12 col-sm-5" disabled/>
                         </div>
                     </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="loginName">登录名:</label>
                        <div class="col-sm-9">
                            <input type="text" id="loginName_update" <#--name="loginName"--> placeholder="登录名" class="col-xs-12 col-sm-5" disabled/>
                        </div>
                    </div>
                     <div class="form-group">
                         <label class="col-sm-3 control-label no-padding-right" for="form-field-2">手机号: </label>
                         <div class="col-sm-9">
                             <input type="text" id="phone_update" <#--name="phone"--> placeholder="手机号" class="col-xs-12 col-sm-5" disabled/>
                         </div>
                     </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName">职称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="userName_update" name="userName" placeholder="职称" class="col-xs-12 col-sm-5" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <#--<div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName">昵称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="nickName_update" name="nickName" placeholder="昵称" class="col-xs-12 col-sm-5" value=""/>
                        </div>
                    </div>-->

                    <#--<div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-2">角色选择: </label>
                        <div class="col-sm-9">
                            <div class="col-xs-12 col-sm-5">
                                <div class="control-group" id="role_checkbox_update">
                                </div>
                            </div>
                        </div>
                    </div>-->
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="updateuser">保 存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!--修改用户权限modal-->
<div class="modal fade" id="updateUserRoleModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">修改权限</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="user-form-updateUserRole" class="form-horizontal">
                    <input id="userId_updateUserRole" name="userId" type="hidden">
                    <input id="roleIds_updateUserRole" name="roleIds" type="hidden">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-2">权限选择: </label>
                        <div class="col-sm-9">
                            <div class="col-xs-12 col-sm-12">
                                <div class="control-group" id="role_checkbox_update1">
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="updateUserRole">保 存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- 用户密码修改Modal -->
<div class="modal fade" id="updatePwdModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="user-form-updatePwd" class="form-horizontal">
                    <input id="userId_updatePwd" name="userId" type="hidden">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-2">密码: </label>
                        <div class="col-sm-9">
                            <input type="password" id="pwd" name="password" placeholder="密码" class="col-xs-12 col-sm-5" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-2">确认密码: </label>
                        <div class="col-sm-9">
                            <input type="password" id="repwd" name="repassword" placeholder="确认密码" class="col-xs-12 col-sm-5" />
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="updatePwdUser">保 存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 用户添加Modal -->
<div class="modal fade" id="addModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加用户</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="add-form-user" class="form-horizontal">
                    <div class="form-group">
                    <#--<div class="widget-main">-->
                        <label class="col-sm-3 control-label no-padding-right" for="recordName">所属系统:</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="departmentId" name ="departmentId"></select>
                        </div>
                    <#--</div>-->
                    </div>

                    <#--<div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-2">关联前台用户: </label>
                        <div class="col-sm-9">
                            <input type="text" id="linkUserId_add" name="linkUserId_add" placeholder="前台用户ID" class="col-xs-12 col-sm-5" value="" onblur=""/>
                            <span class="help-inline col-xs-12 col-sm-7">
                        </div>
                    </div>-->

                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small> 登录名称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="loginname_add" name="loginName" placeholder="登录名称" class="col-xs-12 col-sm-5" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-2"><small class="red">*</small> 手机号: </label>
                        <div class="col-sm-9">
                            <input type="text" id="phone_add" name="phone" placeholder="手机号" class="col-xs-12 col-sm-5" />
                            <span class="help-inline col-xs-12 col-sm-7">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small> 职称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="username_add" name="userName" placeholder="职称" class="col-xs-12 col-sm-5" value="" onblur=""/>
                        </div>
                    </div>
<#--                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small> 昵称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="nickname_add" name="nickName" placeholder="昵称" class="col-xs-12 col-sm-5" value=""/>
                        </div>
                    </div>-->

<#--                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-2">角色选择: </label>
                        <div class="col-sm-9">
                            <div class="col-xs-12 col-sm-5">
                                <div class="control-group" id="role_checkbox_add">
                                </div>
                            </div>
                        </div>
                    </div>-->
                    <input type="hidden" name="roleIds" id="roleIds_add">
                   <#-- <input type="hidden" name="departmentId" id="departmentId">-->
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="adduser">保 存</button>
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
<script src="${ctx}/assets/js/bootstrap-select.js"></script>

<!-- inline scripts related to this page -->
<script src="${ctx}/assets/js/jquery-ui.min.js"></script>
<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/js/jquery.form.js"></script>
<script src="${ctx}/js/sys/user.js?v=0.0.4"></script>
<#include "../common/body_bottom.ftl"/>
