function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='';
 for(var i=0; i < list.length; i++){
__p+='\n    <tr>\n      <td>'+
((__t=(i))==null?'':__t)+
'</td>\n      <td>'+
((__t=(list[i].name))==null?'':__t)+
'</td>\n      <td>'+
((__t=(list[i].tel))==null?'':__t)+
'</td>\n      <td>'+
((__t=(list[i].org))==null?'':__t)+
'</td>å\n      <td>'+
((__t=(list[i].role))==null?'':__t)+
'</td>\n    </tr>\n';
}
__p+='';
}
return __p;
}