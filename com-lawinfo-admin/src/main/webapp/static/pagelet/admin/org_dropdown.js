function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='';
 for(var i=0; i < list.length; i++){
__p+='\n  <li label="'+
((__t=(list[i].name))==null?'':__t)+
'" data-id="'+
((__t=(list[i].id))==null?'':__t)+
'"><a href="#">'+
((__t=(list[i].name ))==null?'':__t)+
'</a></li>\n';
}
__p+='';
}
return __p;
}