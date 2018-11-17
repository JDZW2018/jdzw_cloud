<#include "../common/head_top.ftl"/>
<input type="hidden" id="menuId" value="5dd04e36c28d45dc875c05f045a2f44c"/><#--menu active-->

<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-select.css" />

<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>

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

<#include "../common/body_middle.ftl"/>
<#include "../common/bootstrap_modal.ftl">
<script src="${ctx}/assets/js/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/js/jquery.jqGrid.min.js"></script>
<script src="${ctx}/assets/js/grid.locale-en.js"></script>
<script src="${ctx}/assets/js/bootstrap-select.js"></script>

<!-- inline scripts related to this page -->
<script src="${ctx}/assets/js/jquery-ui.min.js"></script>
<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/js/jquery.form.js"></script>
<script src="${ctx}/js/sys/userRecycleBin.js"></script>
<#include "../common/body_bottom.ftl"/>
