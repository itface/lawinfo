<% for(var i=0; i < list.length; i++){%>
  <li label="<%=list[i].name%>" data-id="<%=list[i].id%>" data-type="<%=list[i].orgtype%>"><a href="#"><%=list[i].name %></a></li>
<%}%>