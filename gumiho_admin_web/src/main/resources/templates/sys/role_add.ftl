<form class="form-horizontal" role="form" id="addrole" method="post">
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="roleName">角色名称</label>

        <div class="col-sm-9">
            <input type="text" id="roleName" name="roleName" class="adds" placeholder="角色名称" class="col-xs-10 col-sm-5" onblur="checkrole()"/>
        </div>
    </div>

</form>

<script>
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
</script>