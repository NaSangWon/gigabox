<%@ page contentType="text/html;charset=euc-kr"
         import="java.sql.*,gigabox.*, java.util.Date"  %>
<html>
	<% DB.loadConnectGigabox(); %>
    <%
    	request.setCharacterEncoding("euc-kr");

    	ResultSet rs = DB.getAllReservationUsersRS();

    	int cnt = DB.getNoTuplesRS(rs);

    	if (cnt == 0) {
    		out.print("<script>alert('예매 내역이 없습니다.')</script>");
    		out.print("<script>window.top.location='menuForLogin.jsp'</script>");
    	} else {
    		request.setAttribute("title", "예매 내역 리스트");
    		request.setAttribute("RS", rs);
    		request.getRequestDispatcher("listRS.jsp").forward(request, response);
    	}
    %>
</html>