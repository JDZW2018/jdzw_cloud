<!DOCTYPE html>
<html lang="zh">
<head></head>
<body>
<div>hello world!~</div>
<div>
    <form action="/upload"  method="post" enctype="multipart/form-data">
        用户名：<input type="text"  name="username"/><br/>
        文件1：<input type="file"  name="file1"/><br/>
        文件2：<input type="file"  name="file2"/><br/>
        <input type="submit"  value="提交"/>
    </form>
</div>
<div>
    <form name="serForm" action="/SpringMVC006/fileUpload" method="post" enctype="multipart/form-data">
        <h1>采用流的方式上传文件</h1>
        <input type="file" name="file">
        <input type="submit" value="upload"/>
    </form>
</div>


<div>
    <form name="Form2" action="/fileUpload2" method="post" enctype="multipart/form-data">
        <h1>采用multipart提供的file.transfer方法上传文件</h1>
        <input type="file" name="file">
        <input type="submit" value="upload"/>
    </form>

</div>
<div>
    <form name="Form2" action="/springUpload" method="post" enctype="multipart/form-data">
        <h1>使用spring mvc提供的类的方法上传文件</h1>
        <input type="file" name="file">
        <input type="submit" value="upload"/>
    </form>

</div>
</body>
</html>