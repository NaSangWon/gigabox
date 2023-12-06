<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.sql.*,gigabox.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <jsp:useBean id="theater" class="gigabox.Theater"/> 
  <jsp:setProperty name="theater" property="*" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>폼에서 입력받은 극장 데이터를 DB에 추가</title>
</head>
  <BODY>
    <%  	  
       	// 이 문장이 수행되기 전에 jsp에서 입력된 parmeter들이 theater 객체의 필드에 저장된다.       	
          
		System.out.println("  << for debug >> in enrolTheater.jsp: theater = ");
		theater.output(); 
		
		DB.insertTheater(theater);
              		
    %>
      
  </BODY>
</html>