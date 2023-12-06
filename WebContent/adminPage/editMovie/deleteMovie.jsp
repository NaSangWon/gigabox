<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.sql.*,gigabox.*"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>폼에서 입력받은 상영관 데이터를 DB에서 삭제</title>
</head>
  <BODY>
    <%  	  
    	int mno = Integer.parseInt(request.getParameter("mno"));
  
  		DB.deleteMovie(mno);
    %>
      
  </BODY>
</html>