<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.sql.*, gigabox.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>티켓 구매 처리</title>
</head>
<body>
	<% request.setCharacterEncoding("utf-8"); 
	   
	   int ticketCount = Integer.parseInt(request.getParameter("ticketCount"));
	%>
	
	<!-- 	먼저 해당 상영 데이터를 불러와야 -->
	<% 
	String screeningNumber = request.getParameter("screeningNumber"); 
	Screening sc = DB.getScreeningBySno(Integer.parseInt(screeningNumber));
	
	sc.output();
	%>
	
	<%
	// 상영에서 자리가 남아있는지? 
	if (sc.getRemainingSitCount() > 0) {
		// 예매 투플 삽입
		User userLogin = (User) session.getAttribute("userLogin");
		
		Reservation res = new Reservation(sc);
		res.init(sc, ticketCount, userLogin.getID());
		
		// 가격 책정
		res.setPrice(DB.getPrice(Integer.parseInt(screeningNumber), ticketCount));
		
		res.output();
		DB.insertReservation(res);
		
		
		// 상영 투플의 잔여좌석수, 마지막좌석 업데이트
		DB.updateScreening(ticketCount, sc.getSno());
	} else {
		out.println("해당 상영의 모든 자리가 예매 되어있습니다. 예매 실패!!!");
	}
	
	
	%>
</body>
</html>