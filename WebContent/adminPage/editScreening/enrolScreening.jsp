<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.sql.*,gigabox.*"%>
<% request.setCharacterEncoding("euc-kr"); %>
    
  <!-- useBean �׼����� Banker Ŭ������  �ν��Ͻ� ��ü banker ����  -->
  <jsp:useBean class="gigabox.Screening" id="screening" scope="request"/> 
  <!-- setProperty �� �̿��� ����� �Է°��� �ڵ����� AutoLoan Ŭ������ ��ü Banker�� ��������� ������ -->
  <jsp:setProperty name="screening" property="*" />
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>������ �Է¹��� �� �����͸� DB�� �߰�</title>
</head>
  <BODY>
    <%  	  
       	// �� ������ ����Ǳ� ���� jsp���� �Էµ� parmeter���� cinema ��ü�� �ʵ忡 ����ȴ�.       	
          
		System.out.println("  << for debug >> in enrolCinema.jsp: cinema = ");
  		screening.output(); 

      	DB.insertScreening(screening);
     		
    %>
      
  </BODY>
</html>