<#assign title=" 黑名单用户提示页"/>
<#include "common/head_top.ftl"/>
<#include "common/head_bottom.ftl"/>
<#include "common/body_top.ftl"/>

<div class="profile-user-info profile-user-info-striped">

    <div class="profile-info-row">
        <div class="profile-info-name"> 登录名称 </div>

        <div class="profile-info-value">
            <span class="editable" id="username">${(userInfo.loginName)!}</span>
        </div>
    </div>

    <div class="profile-info-row">
        <div class="profile-info-name"> 所属系统 </div>

        <div class="profile-info-value">
            <span class="editable" id="departmentName">${(userInfo.departmentName)!}</span>
        </div>
    </div>

    <div class="profile-info-row">
        <div class="profile-info-name"> 职称 </div>

        <div class="profile-info-value">
            <span class="editable" id="user_name">${(userInfo.userName)!}</span>
        </div>
    </div>
    <div class="profile-info-row">
        <div class="profile-info-name"> 手机号 </div>

        <div class="profile-info-value">
            <span class="editable" id="login">${(userInfo.phone)!}</span>
        </div>
    </div>
    <div class="profile-info-row">
        <div class="profile-info-name"> 创建时间 </div>

        <div class="profile-info-value">
            <span class="editable" id="createTime">${(userInfo.FCreateTime)!}</span>
        </div>
    </div>

    <div class="profile-info-row">
        <div class="profile-info-name"> 用户状态 </div>

        <div class="profile-info-value">
            <span class="editable" id="about" style="color: red;">已禁用</span>
        </div>
    </div>
</div>

<#if shopInfo??>

<div class="page-header">
    <h1>
        关联的商户信息
    </h1>
</div>
   <div class="profile-user-info profile-user-info-striped">
       <div class="profile-info-row">
           <div class="profile-info-name"> 商户ID </div>

           <div class="profile-info-value">
               <span class="editable" id="username">${(shopInfo.shopId)!}</span>
           </div>
       </div>



       <div class="profile-info-row">
           <div class="profile-info-name"> 商户名称 </div>

           <div class="profile-info-value">
               <span class="editable" id="age">${(shopInfo.shopName)!}</span>
           </div>
       </div>
       <div class="profile-info-row">
           <div class="profile-info-name"> 所在位置 </div>

           <div class="profile-info-value">
               <i class="fa fa-map-marker light-orange bigger-110"></i>
               <span class="editable" id="address">${(shopInfo.address)!}</span>
           </div>
       </div>
       <div class="profile-info-row">
           <div class="profile-info-name"> 商户余额 </div>

           <div class="profile-info-value">
               <span class="editable" id="balanceAccount">￥${(shopInfo.balanceAccount)!}</span>
           </div>
       </div>

       <div class="profile-info-row">
           <div class="profile-info-name"> 商户积分 </div>

           <div class="profile-info-value">
               <span class="editable" id="login">${(shopInfo.integral)!}</span>
           </div>
       </div>

       <div class="profile-info-row">
           <div class="profile-info-name"> 商户状态 </div>

           <div class="profile-info-value">
               <span class="editable" id="about" style="color: red;">已禁用</span>
           </div>
       </div
   </div>
</#if>

<#include "common/body_middle.ftl"/>
<#include "common/bootstrap_modal.ftl">
<#include "common/body_bottom.ftl"/>

