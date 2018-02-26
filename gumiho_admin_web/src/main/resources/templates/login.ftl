<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>myproject-后台登录</title>
    <link rel="shortcut icon" href="${ctx}/favicon.ico">
    <style>
        html,body{width: 100%;height: 100%;padding: 0;margin: 0;background-color:#32acf6; }
        img{width: 100%;height: 100%;}
        .f_left{float: left}
        .f_right{float: right}
        input{border: 0;outline: none;}
        .bg{width:100%;background: url("img/top.png") 1920px 890px;height: 890px;background-position: center;}
        .con_1200{width:1200px;margin: 0 auto;}
        .con_1200 .img1{width:520px;height: 482px;padding:130px 0 0 80px;display: inline-block;vertical-align: middle}
        .con_1200 .right{width: 380px;height: 362px;box-sizing: border-box;padding:0 60px 0 45px;background-image: url("img/login_right_bg.png");display: inline-block;vertical-align: middle;margin: 100px 0 0 100px;}
        .con_1200 .line{width: 100%;height: 40px;background-color: white;box-sizing: border-box;}
        .con_1200 p{width: 100%;line-height: 60px;font-size: 20px;color: white;margin: 0;}
        .con_1200 .img2{width: 20px;height: 20px;display: inline-block;vertical-align: middle;padding: 10px;}
        .con_1200 .name{width: 220px;outline: none;border: 0;height: 30px;font-size: 16px;color: #989898;}
        .con_1200 .u{margin-bottom: 25px;}
        .con_1200 .p{}
        .con_1200 .j{line-height: 40px;background-color:transparent;font-size: 12px;vertical-align: middle;color: white;}
        .con_1200 .y{background-color:transparent;}
        .con_1200 .d{background-color:#ffa42f;border-radius: 5px;color:white;text-align: center;height: 40px;line-height: 40px;margin: 20px 0;font-size: 18px;padding-bottom: 20px;cursor: pointer}
    </style>
</head>
<body>
<form action="${ctx}/login"  method="POST" >
<div class="bg">
    <div class="con_1200">
        <div class="img1"><img  src="img/login_left.png" alt=""></div>

			<div class="right">
				<p>账号登录</p>
				<div class="line u">
					<span class="img2"><img src="img/login_name.png"></span><input class="name" type="text" name="username" placeholder="用户名"/>
				</div>
				<div class="line p" style="margin-bottom: 25px;">
					<span class="img2"><img src="img/login_pwd.png"></span><input class="name" name="password" type="password" placeholder="密码"/>
				</div>
				<#--<div class="line j">-->
					<#--<div class="f_left"><input type="checkbox" style="vertical-align: middle;"/><span >记住密码</span></div>-->
					<#--<div class="f_right">-->
						<#--&lt;#&ndash;<span>找回密码</span>&ndash;&gt;-->
					<#--</div>-->
				<#--</div>-->
				<div class="line y">
					<div class="f_left " style="height: 30px;padding: 5px 0;"><input type="text" name="vrify"  style="width: 165px;height: 30px;box-sizing: border-box;padding: 0 10px;" placeholder="验证码"/></div>
					<div class="f_right " style="height: 30px;padding: 5px 0;">
						<img alt="验证码" onclick = "this.src='${ctx}/defaultKaptcha?d='+new Date()*1" src="${ctx}/defaultKaptcha"  style=""/>
					</div>
				</div>
				<div style="padding-top: 10px;">
				<#if error?? >
					<label class="block clearfix">
						<span class="block input-icon input-icon-right">
							<#if  error == 1>
								用户名密码错误
							<#elseif  error==2>
								访问服务出错
                            <#elseif  error==3>
                                验证码错误
							</#if>
						</span>
					</label>
				</#if>
				</div>

				<button type="submit" id="login" class="line d" style="line-height: 31px;">
					<i class="ace-icon fa fa-key"></i>
					<span class="bigger-110">登录</span>
				</button>

			</div>

    </div>
</div>
</form>
</body>
<!--[if !IE]> -->
<script src="${ctx}/assets/js/jquery-2.1.4.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="${ctx}/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
</html>