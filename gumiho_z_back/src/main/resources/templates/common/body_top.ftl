
	<body class="no-skin">
		<div id="navbar" class="navbar navbar-default ace-save-state navbar-fixed-top">
			<div class="navbar-container ace-save-state" id="navbar-container">
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>
				</button>

				<div class="navbar-header pull-left">
					<a href="" class="navbar-brand">
						<span id = "department"><small>
							<i class="fa fa-leaf"></i>
						</small>
							黑名单用户提示页
						</span>

					</a>
				</div>
				<div class="navbar-buttons navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">


						<li class="light-blue dropdown-modal">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="${ctx}/assets/images/avatars/avatar2.png" alt="" />
								<span class="user-info">
									<small>欢迎！</small>
									${userInfo.loginName}
						<#--		${(Session.SPRING_SECURITY_CONTEXT.authentication.principal.username)!}-->

								</span>

								<i class="ace-icon fa fa-caret-down"></i>
							</a>

							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<#--<li>-->
									<#--<a href="#">-->
										<#--<i class="ace-icon fa fa-cog"></i>-->
										<#--设置-->
									<#--</a>-->
								<#--</li>-->

								<#--<li>-->
									<#--<a href="profile.html">-->
										<#--<i class="ace-icon fa fa-user"></i>-->
										<#--个人中心-->
									<#--</a>-->
								<#--</li>-->

								<#--<li class="divider"></li>-->

								<li>
									<a href="/logout">
										<i class="ace-icon fa fa-power-off"></i>
										登出
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div><!-- /.navbar-container -->
		</div>

		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>
			<div class="main-content">
				<div class="main-content-inner">
					<#--<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">导航</a>
							</li>
							<li class="active" id="titleId"></li>
						</ul><!-- /.breadcrumb &ndash;&gt;
						
					</div>-->

					<div class="page-content">
						<div class="ace-settings-container" id="ace-settings-container">


							<div class="ace-settings-box clearfix" id="ace-settings-box"></div><!-- /.ace-settings-box -->
						</div><!-- /.ace-settings-container -->

						<#--<div class="page-header">-->

						<#--</div><!-- /.page-header &ndash;&gt;-->

						<div class="row">

