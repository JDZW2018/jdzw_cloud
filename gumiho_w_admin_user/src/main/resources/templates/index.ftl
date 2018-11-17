<#assign title="首页"/>
<#--<#assign menuId="e3220dd568c9497ba3e00fc6ed3bfdae"/>-->
<#include "./common/head_top.ftl"/>
<input type="hidden" id="menuId" value="e3220dd568c9497ba3e00fc6ed3bfdae"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css" xmlns="http://www.w3.org/1999/html"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css" />
<#include "./common/head_bottom.ftl"/>
<#include "./common/body_top.ftl"/>

<div class="col-xs-12" style="padding: 0px;">
    <img src="img/index3.png" style="width: 100%"></img>
    <#--<a href="javascript:;" onclick="testCookie()">测试cookie</a>-->
</div>

<#include "./common/body_middle.ftl"/>
<#include "./common/bootstrap_modal.ftl">

<!-- page specific plugin scripts -->
<script src="${ctx}/assets/js/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/js/jquery.jqGrid.min.js"></script>
<script src="${ctx}/assets/js/grid.locale-en.js"></script>

<!-- inline scripts related to this page -->
<script src="${ctx}/assets/js/jquery-ui.min.js"></script>
<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/js/jquery.form.js"></script>


<#--<script type="text/javascript">
    function testCookie(){
        console.log("页面上的cookie:"+document.cookie);
        $.ajax({
            url: base+'menu/get',
            async: false,
            success: function() {
                console.log(2);
            }

        });
    }
</script>-->
<#include "./common/body_bottom.ftl"/>
