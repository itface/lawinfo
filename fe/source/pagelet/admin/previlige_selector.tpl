<% for(var i=0; i < list.length; i++){%>
<span >
    <label>
        <input type="checkbox" p-id="<%=list[i].id%>"></input><%=list[i].name%>
    </label>
</span>
<%}%>