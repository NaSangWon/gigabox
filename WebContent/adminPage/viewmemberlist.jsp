<%@ page contentType="text/html;charset=euc-kr"
         import="java.sql.*,gigabox.*, java.util.Date"  %>
<html>
    <%
    	request.setCharacterEncoding("euc-kr");

    	ResultSet rs = DB.getAllUsersRS();

    	int cnt = DB.getNoTuplesRS(rs);

    	if (cnt == 0) {
    		out.print("<script>alert('생성된 회원이 하나도 없습니다.')</script>");
    		out.print("<script>window.top.location='index.html'</script>");
    	} else {
    		request.setAttribute("title", "모든 회원 리스트");
    		request.setAttribute("RS", rs);
    		request.getRequestDispatcher("listRS.jsp").forward(request, response);
    	}
    %>
</html>