<% for(var i=0; i < list.length; i++){%>
    <tr>
      <td><%=i+1%></td>
      <td><%=list[i].name%></td>
      <td><%=list[i].userid%></td>
      <td><%=list[i].orgname%></td>
      <td><%=list[i].rolename%></td>
      <td>
          <button class="btn btn-default btn-delete" data-id="<%=list[i].id%>">删除</button>
      </td>
    </tr>
<%}%>