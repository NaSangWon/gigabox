<%@ page contentType="text/html;charset=euc-kr"
         import="java.sql.*,gigabox.*, java.util.Date"  %>
<html>
	<%
    	request.setCharacterEncoding("euc-kr");

    	ResultSet rs = DB.getAllReservationsRS();

    	int cnt = DB.getNoTuplesRS(rs);

    	if (cnt == 0) {
    		out.print("<script>alert('예매 내역이 하나도 없습니다.')</script>");
    		out.print("<script>window.top.location='index.html'</script>");
    	} else {
    		request.setAttribute("title", "예매 리스트");
    		request.setAttribute("RS", rs);
    		request.getRequestDispatcher("listRS.jsp").forward(request, response);
    	}
    %>
</html>