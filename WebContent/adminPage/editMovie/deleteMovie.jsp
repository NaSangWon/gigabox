<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.sql.*,gigabox.*"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>������ �Է¹��� �󿵰� �����͸� DB���� ����</title>
</head>
  <BODY>
    <%  	  
    	int mno = Integer.parseInt(request.getParameter("mno"));
  
  		DB.deleteMovie(mno);
    %>
      
  </BODY>
</html>