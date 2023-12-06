<%@ page contentType="text/html;charset=euc-kr"
         import="java.sql.*, gigabox.*, java.util.Date"  %>

<HTML>
    <HEAD><TITLE>취소내역 조회</TITLE></HEAD>

    <BODY>
	<%
    	request.setCharacterEncoding("euc-kr");
		
		User userLogin = (User) session.getAttribute("userLogin");

    	ResultSet rs = DB.getCancelReservationRS(userLogin.getID());

    	int cnt = DB.getNoTuplesRS(rs);

    	if (cnt == 0) {
    		out.print("<script>alert('예매 취소 내역이 하나도 없습니다.')</script>");
    		out.print("<script>window.top.location=index.html</script>");
    	} else {
    		request.setAttribute("title", "예매 취소 리스트");
    		request.setAttribute("RS", rs);
    		request.getRequestDispatcher("listRS.jsp").forward(request, response);
    	}
    %> 
     </BODY>
</HTML>