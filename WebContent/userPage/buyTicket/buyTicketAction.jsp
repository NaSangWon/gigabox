<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.sql.*, gigabox.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Ƽ�� ���� ó��</title>
</head>
<body>
	<% request.setCharacterEncoding("utf-8"); 
	   
	   int ticketCount = Integer.parseInt(request.getParameter("ticketCount"));
	%>
	
	<!-- 	���� �ش� �� �����͸� �ҷ��;� -->
	<% 
	String screeningNumber = request.getParameter("screeningNumber"); 
	Screening sc = DB.getScreeningBySno(Integer.parseInt(screeningNumber));
	
	sc.output();
	%>
	
	<%
	// �󿵿��� �ڸ��� �����ִ���? 
	if (sc.getRemainingSitCount() > 0) {
		// ���� ���� ����
		User userLogin = (User) session.getAttribute("userLogin");
		
		Reservation res = new Reservation(sc);
		res.init(sc, ticketCount, userLogin.getID());
		
		// ���� å��
		res.setPrice(DB.getPrice(Integer.parseInt(screeningNumber), ticketCount));
		
		res.output();
		DB.insertReservation(res);
		
		
		// �� ������ �ܿ��¼���, �������¼� ������Ʈ
		DB.updateScreening(ticketCount, sc.getSno());
	} else {
		out.println("�ش� ���� ��� �ڸ��� ���� �Ǿ��ֽ��ϴ�. ���� ����!!!");
	}
	
	
	%>
</body>
</html>