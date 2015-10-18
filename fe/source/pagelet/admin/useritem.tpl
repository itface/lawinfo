<% for(var i=0; i < list.length; i++){%>
    <tr>
      <td><%=i%></td>
      <td><%=list[i].name%></td>
      <td><%=list[i].tel%></td>
      <td><%=list[i].org%></td>å
      <td><%=list[i].role%></td>
      <td>
          <button class="btn btn-default btn-delete" data-id="<%=list[i].id%>">删除</button>
      </td>
    </tr>
<%}%>