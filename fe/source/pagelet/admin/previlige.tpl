<% for(var i=0; i < list.length; i++){%>
<tr>
  <td><%=i%></td>
  <td><%=list[i].name%></td>
  <td><%=list[i].privilegeid%></td>
  <td><%=list[i].description%></td>
</tr>
<%}%>