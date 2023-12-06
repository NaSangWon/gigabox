<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.sql.*,gigabox.*"%>
<% request.setCharacterEncoding("euc-kr"); %>
    
  <!-- useBean 액션으로 Banker 클래스의  인스턴스 객체 banker 생성  -->
  <jsp:useBean class="gigabox.Screening" id="screening" scope="request"/> 
  <!-- setProperty 를 이용해 사용자 입력값을 자동으로 AutoLoan 클래스의 객체 Banker의 멤버변수에 저장함 -->
  <jsp:setProperty name="screening" property="*" />
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>폼에서 입력받은 상영 데이터를 DB에 추가</title>
</head>
  <BODY>
    <%  	  
       	// 이 문장이 수행되기 전에 jsp에서 입력된 parmeter들이 cinema 객체의 필드에 저장된다.       	
          
		System.out.println("  << for debug >> in enrolCinema.jsp: cinema = ");
  		screening.output(); 

      	DB.insertScreening(screening);
     		
    %>
      
  </BODY>
</html>