<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.sql.*,gigabox.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <jsp:useBean id="theater" class="gigabox.Theater"/> 
  <jsp:setProperty name="theater" property="*" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>������ �Է¹��� ���� �����͸� DB�� �߰�</title>
</head>
  <BODY>
    <%  	  
       	// �� ������ ����Ǳ� ���� jsp���� �Էµ� parmeter���� theater ��ü�� �ʵ忡 ����ȴ�.       	
          
		System.out.println("  << for debug >> in enrolTheater.jsp: theater = ");
		theater.output(); 
		
		DB.insertTheater(theater);
              		
    %>
      
  </BODY>
</html>