<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.sql.*, gigabox.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>예매 취소 처리</title>
</head>
<body>
	<% request.setCharacterEncoding("utf-8"); 
	   
	   int rno = Integer.parseInt(request.getParameter("ReservationNumber"));
	   int rate = Integer.parseInt(request.getParameter("rate"));
	   
	   // System.out.println("입력받은 평점: " + rate);
	   
	   DB.rate(rno, rate);
	%>
	
</body>
</html>