function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='';
 for(var i=0; i < list.length; i++){
__p+='\n<span >\n    <label>\n        <input type="checkbox" p-id="'+
((__t=(list[i].id))==null?'':__t)+
'"></input>'+
((__t=(list[i].name))==null?'':__t)+
'\n    </label>\n</span>\n';
}
__p+='';
}
return __p;
}