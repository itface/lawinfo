function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='';
 for(var i=0; i < list.length; i++){
__p+='\n    <tr>\n      <td>'+
((__t=(i+1))==null?'':__t)+
'</td>\n      <td>'+
((__t=(list[i].name))==null?'':__t)+
'</td>\n      <td>'+
((__t=(list[i].userid))==null?'':__t)+
'</td>\n      <td>'+
((__t=(list[i].orgname))==null?'':__t)+
'</td>\n      <td>'+
((__t=(list[i].rolename))==null?'':__t)+
'</td>\n      <td>\n          <button class="btn btn-default btn-delete" data-id="'+
((__t=(list[i].id))==null?'':__t)+
'">删除</button>\n      </td>\n    </tr>\n';
}
__p+='';
}
return __p;
}