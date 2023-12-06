<%@ page contentType="text/html;charset=utf-8" import="java.sql.*, java.util.Date" %>
<HTML>

<%--
 [ ListRsAddMoveButton.jsp ]: ResultSet 객체를 출력하고 버튼을 투플마다 생성
 
    o ResultSet 객체를 출력하면서 투플마다 선택된 특정 애트리뷰트에 대해 버튼을 생성하한 후
          여러 버튼 중  하나를 선택하면 주어진 페이지로 이동하는 JSP 페이지
  
    o 이 페이지를 호출하기 전  반드시 request.setAttribute()를 통해 
          타이틀 정보, ResultSet 객체, 선택할 애트리뷰트 이름, 이동할 JSP 페이지 등이 주어져야 함
     
      1) 타이틀 정보: request.setAttribute("title", "이 페이지에서 출력할 타이틀 문자열")
         - 예: request.setAttribute("title", "컴퓨터학과 학생 리스트");
             
      2) ResultSet 객체: request.setAttribute("RS", << 이 페이지에서 출력할 ResultSet 객체>>)
         - 예: << ResultSet rs = con.executeQuery("select sno, sname, year from student where dept = '컴퓨터'"); >>의
                        결과인 rs 객체를 이 페이지를 통해 출력하려면 다음과 같이 하면 됨
                        
              request.setAttribute("RS", rs);
              
      3) 선택할 애트리뷰트 이름: Rrequest.setAttribute("attributeToChoose", "버튼 선택시 전달할 애트리뷰트 이름인 문자열")
         - esultSet 객체의 여러 애트리뷰트 중 버튼을 선택하면 값을 전달할 애트리뷰트를 지정
         - 예: 2)의 예에서 ResulutSet 객체에는 sno, sname, year 애트리뷰트가 있는데, 
                        이중 전달할 값을 sno 애트리뷰트로 선택하려면 다음과 같이 하면 됨
                       
             request.setAttribute("attributeToChoose", "sno");
             
      4) 이동할 JSP 페이지: Rrequest.setAttribute("pageToMove", "버튼 클릭하면 이동할 페이지인 문자열")
         - 버튼 클릭하면 이동할 페이지를 표시함
         - 예: 버튼 클릭했을 때  listStudent.jsp 페이지로 이동하려면  다음과 같이 하면 됨
                       
             request.setAttribute("pageToMove", "ListStudent.jsp");

      o 버튼 클릭하면 pageToMove 애트리뷰트의 애트리뷰트 값으로 주어진  페이지로 이동하며
               선택된 체크박스의 값들은 attributeToChoose의 애트리뷰트의 값이 파라미터 이름으로 전달됨
               
         - 이동한 페이지에서 선택된 버튼의 전달된 값 추출하기 위해서는 다음과 같이 request.getParamter()를 이용함         
         
           String sno = request.getParameter("sno");        
 --%>

<head>

<script>
  // parameterName과 parameterValue를 post 방식으로 주어진 페이지로 전달하면서 이동하기
  function moveTo(pageToMove, parameterName, parameterValue) {
	  
	  var form = document.createElement("form");

      // Move the submit function to another variable
      // so that it doesn't get overwritten.
      form._submit_function_ = form.submit;

      form.setAttribute("method","post");
      form.setAttribute("action", pageToMove);

    
      var hiddenField = document.createElement("input");
      hiddenField.setAttribute("type", "hidden");
      hiddenField.setAttribute("name", parameterName);
      hiddenField.setAttribute("value", parameterValue);

      form.appendChild(hiddenField);
          
      document.body.appendChild(form);
          
      form.submit();
  }
</script>    

</head>
  <BODY>	 
  <br>   
 	<%	
 	 	   request.setCharacterEncoding("utf-8");
 	
 	       ResultSet rs = (ResultSet) request.getAttribute("RS");
 	       if (rs == null) {
 	    	   out.println("<H3 align=center >ResultSet 객체가 전달되지 않았습니다.</H3>");  
 	    	   return;
 	       }
 	       
 	%>
 	   <H3 align=center > <% out.println(request.getAttribute("title"));   %> </H3>
 	       
 	<% 
 	       String attributeToChoose = (String) request.getAttribute("attributeToChoose");
 	       String pageToMove = (String) request.getAttribute("pageToMove");

System.out.println("   >> attributeToChoose = " + attributeToChoose + "\n");	
System.out.println("   >> pageToMove = " + pageToMove + "\n");	

			rs.last();  // rs 커서를 끝으로 이동하여 투플 개수 확인
			int cntTuples = rs.getRow();  // 투플 개수를 구함
			rs.beforeFirst();  // rs 커서를 처음으로 이동
System.out.println("   >> cntTuples = " + cntTuples + "\n");	
			
			if (cntTuples == 0) { // 투플 개수가 0이면
				out.println("<center>(결과 없음)</center>");
				return;
			}
			
 	       
 System.out.println("   >> rs : " + rs + "\n");	       
 
		   ResultSetMetaData md = rs.getMetaData();
		   int count = md.getColumnCount();
		   String[] columns = new String[count];
		   String[] columnTypes = new String[count];
		   
		   boolean notFoundAttributeForButton = true;  // 버튼 위한 애트리뷰트가 검색 결과 애트리뷰트에 포함되는가를 검사하기 위한 변수
		   
		   for(int i=0; i<count; i++){
			   columns[i] = md.getColumnLabel(i+1);
			   columnTypes[i] = md.getColumnTypeName(i+1);
			   
			   if (columns[i].equals(attributeToChoose))  // 버튼 위한 애트리뷰트가 검색 결과 애트리뷰트에 포함되면 false로 만듬
					   notFoundAttributeForButton = false;
			   
System.out.println("   >> clms : " + columns[i] + " " + columnTypes[i]+ "\n");							
     	   }
		   
System.out.println("   >> notFoundAttributeForButton : " + notFoundAttributeForButton);		
	  
		   if (notFoundAttributeForButton) {  // 버튼 위한 애트리뷰트그가 검색 결과 애트리뷰트에 포함안되면 취소
	   	 		out.println("<script language=javascript> alert('버튼 생성 애트리뷰트 [ " + attributeToChoose 
	   	 				       + " ]: 검색 결과 애트리뷰트에 포함되지 않습니다'); window.history.go(-1); </script>");
		        return;
		   }
       
/*		
			if (cntTuples == 1) { // 투플 개수가 1이면 한 라인에 애트리뷰트명과 애트리뷰트 이름을 출력, 여기에서는 이 기능을 삭제함
			    out.println("<table align=center valign=top border=1 cellpadding=8 cellspacing=0 bordercolor=#999999>");
			
				rs.next(); // ResultSet의 커서 이동

				for(int i=0; i<columns.length; i++){
					out.println("<tr><td bgcolor=#DDDDDD>" + columns[i]  + "</td >" + "<td > &nbsp;" +  rs.getObject(columns[i])  + "&nbsp;");	

					if (columns[i].equals(attributeToChoose)) {				
						String attributeValue = (String) rs.getObject(columns[i]);
												
						out.println(" <input type='button' value='선택하기' onclick='document.location.href=\"" + 
								pageToMove + "?attributeToChoose=" + attributeValue  + "\"'>  </td> ");							
					}		
					
					out.println("</td>");
					out.println("</tr>");
				}
				
				out.println("</table>");

				return;
			}
			
*/
       
	    out.println("<table align=center valign=top border=1 cellpadding=8 cellspacing=0 bordercolor=#999999>");
			out.println("<tr bgcolor=#DDDDDD>" );
			for(int i=0; i<columns.length; i++){
				out.println("<th>" + columns[i]  + "</th>" );									
			}
					
			// 행의 마지막 열에 선택 버튼을 위한 선택 열 추가 	
			out.println("<td align=center >선택 </td>");							
	
			
			out.println("</tr>" );

			while(rs.next()) {
				out.println("<tr>" );

				for(int i=0; i<columns.length; i++){

					Object obj= rs.getObject(columns[i]);
 System.out.println("   >> col value : " + (obj) + "\n");
 
					if (obj == null)    // null 객체이면 null을 출력
						out.println("<td> null </td>");
					else if (columnTypes[i].equals("INTEGER") || columnTypes[i].equals("FLOAT")
			                             || columnTypes[i].equals("DOUBLE") || columnTypes[i].equals("BIGINT") )
						out.println("<td align=right>" + obj + "</td>");	
					else if (columnTypes[i].equals("VARCHAR") && ((String) obj).equals(""))
					     out.println("<td> &nbsp; </td>");	
					else if (columnTypes[i].equals("VARCHAR") ) {
						out.println("<td align=left>"+ obj + "</td>");	
					}
 			     	else
					     out.println("<td>" + obj + "</td>");	
				}
				
				
				// 버튼 추가할 애트리뷰트의 값을 구하여 저장
				Object attributeValue = rs.getObject(attributeToChoose);
					
				// 버튼 클릭시 선택된 버튼의 애트리뷰트 이름과 값을 파라미터로 전달하여 pageToMove 페이지로 이동하게 하는 Javascript 함수 호출
				out.println("<td align=center > <input type='button' value='선택하기' onclick='moveTo(\"" + 
					        pageToMove  + "\", \"" + attributeToChoose  + "\", \"" + attributeValue  + "\")' > ");	

				/* get 방식으로 파라미터 전달하면 한글 데이터 전달 안되므로 한글 데이터 전달시 사용할 수 없음
				out.println(" <input type='button' value='선택하기' onclick='document.location.href=\"" + 
							 pageToMove + "?" + attributeToChoose + "=" + attributeValue  + "\"'>  </td> ");	
				*/ 
				
				System.out.println("  >> 작성 태그:  <td align=center > <input type='button' value='선택하기' onclick='moveTo(\"" + 
					        pageToMove  + "\", \"" + attributeToChoose  + "\", \"" + attributeValue  + "\")' >");


				out.println("</tr>" );
			}
      %>
	  </table >
	 <br>
  </BODY>
</HTML>
