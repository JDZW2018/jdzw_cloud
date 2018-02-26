</div><!-- /.row -->
</div><!-- /.page-content -->
</div>
</div><!-- /.main-content -->

<div class="footer">
    <div class="footer-inner">
        <div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">教头学院</span>
							 &copy; 2016-2018
						</span>

            &nbsp; &nbsp;
            <span class="action-buttons">
							<#--<a href="#">-->
								<#--<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>-->
							<#--</a>-->

							<#--<a href="#">-->
								<#--<i class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>-->
							<#--</a>-->

							<#--<a href="#">-->
								<#--<i class="ace-icon fa fa-rss-square orange bigger-150"></i>-->
							<#--</a>-->
						</span>
        </div>
    </div>
</div>

<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script src="${ctx}/assets/js/jquery-2.1.4.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="${ctx}/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='${ctx}/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="${ctx}/assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="${ctx}/assets/js/excanvas.min.js"></script>
<![endif]-->


<!-- ace scripts -->
<script src="${ctx}/assets/js/ace-elements.min.js"></script>
<script src="${ctx}/assets/js/ace.min.js"></script>

<script src="${ctx}/assets/js/bootstrap.modal.js"></script>

<script>
    function createMenu(data) {
        var v = "";

        $.each(data,function(i,n) {

            if(n.childs) {
                v += '<li class="'+n.active+' '+n.open+'"><a href="#" class="dropdown-toggle" ><i class="menu-icon fa ';
                v += n.icon;
                v += '"></i> <span class="menu-text">'+n.name+'</span> ';
                v += '<b class="arrow fa fa-angle-down"></b>';
                v += '</a> <b class="arrow"></b> ';
                v += '<ul class="submenu">';
                v += createMenu(n.childs);
                v += '</ul></li>';
            }else {
                v += '<li class="'+n.active+' '+n.open+'"> <a href="${ctx}'+n.url+'"><i class="menu-icon fa '+n.icon+'"></i> <span class="menu-text">'+n.name+'</span> ';
                v += '</a> <b class="arrow"></b></li>';
            }

        });
        return v;
    }

    $.ajax({
        url: "${ctx}/menu/get",
        data: {'menuId':'${menuId}'},
        cache:true,
        success: function(data){
            if(data.result == 1) {
                $("#menu").html(createMenu(data.data));
            }else {
                window.location.href = "${ctx}/login";
            }

        },
        error: function(data){
            alert("获取菜单失败");
        }
    });
</script>

