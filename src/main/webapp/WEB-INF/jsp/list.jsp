<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    进入jsp 页面
    
    <h2>只能上传单张10M以下的 PNG、JPG、GIF 格式的图片</h2>
    <form action="/onfile" method="post" enctype="multipart/form-data">
            选择文件:<input type="file" name="file">
    <input type="submit" value="上传">
    </form>
   
</body>
</html>