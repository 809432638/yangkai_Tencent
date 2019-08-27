<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
 <div class="container">
 
      <div class="form-group">
         <button type="button" class="btn btn-warning" onclick="add()">增加栏位</button>
         <button type="button" class="btn btn-success" onclick="publish()">发布图片</button>
      </div>
      													    
      <form action="/pics/publishpic" method="post" enctype="multipart/form-data" id="form1">
          	<div class="form-group">
				<label>图片标题</label> <input type="text" name="title"
					class="form-control">
			</div>
		 
			<div id="mdiv" class="form-group">
			       <div class="card" style="float: left">
			         <input type="file" name="files">
			        <div class="card-body">
						<textarea rows="5" cols="40" name="picContents"></textarea>
					</div>
			       </div>
			</div>
      </form>
 </div>
	 <script type="text/javascript">
	 
	    function add(){
	    	$("#mdiv").append("<div class='card' style='float: left'> <input type='file' name='files'><div class='card-body'><textarea rows='5' cols='40' name='picContents'></textarea></div> </div>")
	    }
	 
	     function publish(){
	    	 $("#form1").submit();
	     }
	 
	 </script>
</body>
</html>