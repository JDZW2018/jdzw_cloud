<#assign title="资源管理"/>
<#--<#assign menuId="23eb2b5d0dc1492692b531fb1ccdb52f"/>-->
<#include "../common/head_top.ftl"/>

<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/icon.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<input type="hidden" id="menuId" value="23eb2b5d0dc1492692b531fb1ccdb52f"/><#--menu active-->
<div class="col-xs-12">

    <div class="col-sm-5">
        <div class="widget-box widget-color-blue2">
            <div class="widget-header">
                <h4 class="widget-title lighter smaller">资源菜单</h4>
                <a  id="openBtn" style="color: white;display: none;float: right;padding-top: 9px; padding-right: 5px; cursor:pointer;" >展开>></a> <a id="closeBtn" style="color: white;float: right;padding-top: 9px;padding-right: 5px;cursor:pointer;" >折叠>>  </a>
            </div>
            <div class="widget-body">
                <div class="widget-main padding-8">
                    <ul id="menu-tree" class="tree tree-unselectable" role="tree"></ul>
                </div>
            </div>
        </div>
    </div>

    <div class="col-sm-7" style="" id="rightResource">

        <div class="widget-box widget-color-blue2">
            <div class="widget-header">
                <h4 class="widget-title lighter smaller">资源管理</h4>
            </div>
            <form id="form-resource" class="form-horizontal">
                <div class="hr hr-18 dotted"></div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="recordName">资源名称:</label>
                    <div class="col-sm-9">
                        <input type="text" id="resourceName" name="resourceName" placeholder="资源名称" class="col-xs-12 col-sm-5" value="" onblur=""/>
                    </div>
                </div>
                <div class="hr hr-18 dotted"></div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="recordName">资源值:</label>
                    <div class="col-sm-9">
                        <input type="text" id="value" name="value" placeholder="资源值" class="col-xs-12 col-sm-5" value="" onblur=""/>
                    </div>
                </div>
                <div class="hr hr-18 dotted"></div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="recordName">资源属性:</label>
<#--
                    <div class="col-sm-9">

                        <input type="radio" id="menu" name="menu" value="0" checked  />资源
                        <input type="radio" id="menu" name="menu" value="1"  />菜单
                    </div>
-->


                    <div class="col-sm-2">
                        <label>
                            <input type="radio" id="menu0" name="menu" value="0" class="ace input-lg" />
                            <span class="lbl bigger-120"> 资源</span>
                        </label>
                    </div>
                    <div class="col-sm-7">
                        <label>
                            <input type="radio" id="menu1"  name="menu" value="1" checked class="ace input-lg" />
                            <span class="lbl bigger-120"> 菜单</span>
                        </label>
                    </div>
                </div>
                <div class="hr hr-18 dotted"></div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-2">资源类别: </label>
                    <div class="col-sm-9">
                        <select class="col-xs-12 col-sm-6" id="parentId" name="parentId" class="easyui-combotree" style="width: 300px;high:100px; padding-right:2px;padding-right:2px;">
                        </select>
                    </div>
                </div>
                <div class="hr hr-18 dotted"></div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-2">序号: </label>

                    <div class="col-sm-9">
                        <input type="text" id="seqno" name="seqno" placeholder="序号" class="col-xs-12 col-sm-5" />
                    </div>
                </div>
                <div class="hr hr-18 dotted"></div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-2">展示图标: </label>
                    <div class="col-sm-9">
                        <input type="text" id="icon" name="icon" placeholder="展示图标" class="col-xs-12 col-sm-5" />
                        <span class="help-inline col-xs-12 col-sm-7">
                    </div>
                </div>
                <div class="hr hr-18 dotted"></div>

                <input type="hidden" id="resourceId" name="resourceId" class="input:hidden">
                <input type="hidden" id="type" name="type" class="input:hidden" value="1">
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary btn-xs" id="new" onclick="append()">新 建</button>
                    <button type="button" class="btn btn-primary btn-xs" id="addResource">保 存</button>
                    <button type="button" class="btn btn-primary btn-xs" id="delResource" disabled >删 除</button>
                    <#--<button type="button" class="btn btn-default btn-xs" data-dismiss="modal" id="closeResource">关 闭</button>-->
                </div>
            </form>
        </div>
    </div>
</div><!-- /.col -->

<#include "../common/body_middle.ftl"/>
<#include "../common/bootstrap_modal.ftl">

<!-- page specific plugin scripts -->
<script src="${ctx}/assets/js/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/js/jquery.jqGrid.min.js"></script>
<script src="${ctx}/assets/js/grid.locale-en.js"></script>
<script src="${ctx}/assets/js/spinbox.min.js"></script>
<!-- inline scripts related to this page -->
<script src="${ctx}/assets/js/jquery-ui.min.js"></script>
<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/js/jquery.form.js"></script>
<script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>
<script src="${ctx}/js/sys/resource.js?v=1"></script>
<#include "../common/body_bottom.ftl"/>
