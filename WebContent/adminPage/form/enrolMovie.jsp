<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.sql.*,gigabox.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <jsp:useBean id="movie" class="gigabox.movie"/> 
  <jsp:setProperty name="movie" property="*" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>������ �Է¹��� ��ȭ �����͸� DB�� �߰�</title>
</head>
  <BODY>
    <%  	  
       	// �� ������ ����Ǳ� ���� jsp���� �Էµ� parmeter���� movie ��ü�� �ʵ忡 ����ȴ�.       	
          
		System.out.println("  << for debug >> in enrolMovie.jsp: movie = ");
		movie.output(); 
		
		DB.insertMovie(movie);
              		
    %>
      
  </BODY>
</html>