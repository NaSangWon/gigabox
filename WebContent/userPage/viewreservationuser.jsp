<%@ page contentType="text/html;charset=euc-kr"
         import="java.sql.*,gigabox.*, java.util.Date"  %>
<html>
	<% DB.loadConnectGigabox(); %>
    <%
    	request.setCharacterEncoding("euc-kr");

    	ResultSet rs = DB.getAllReservationUsersRS();

    	int cnt = DB.getNoTuplesRS(rs);

    	if (cnt == 0) {
    		out.print("<script>alert('���� ������ �����ϴ�.')</script>");
    		out.print("<script>window.top.location='menuForLogin.jsp'</script>");
    	} else {
    		request.setAttribute("title", "���� ���� ����Ʈ");
    		request.setAttribute("RS", rs);
    		request.getRequestDispatcher("listRS.jsp").forward(request, response);
    	}
    %>
</html>