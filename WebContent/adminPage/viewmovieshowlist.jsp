<%@ page contentType="text/html;charset=euc-kr"
         import="java.sql.*,gigabox.*, java.util.Date"  %>
<html>
    <%
    	request.setCharacterEncoding("euc-kr");

    	ResultSet rs = DB.getAllMovieShowRS();

    	int cnt = DB.getNoTuplesRS(rs);

    	if (cnt == 0) {
    		out.print("<script>alert('상영 예정이 없습니다.')</script>");
    		out.print("<script>window.top.location='menuForLogin.jsp'</script>");
    	} else {
    		request.setAttribute("title", "상영시간표");
    		request.setAttribute("RS", rs);
    		request.getRequestDispatcher("listRS.jsp").forward(request, response);
    	}
    %>
</html>