<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>图书管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
        <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
<style>
    .demo{width:200px; margin:20px auto 0 auto; height:50px;}
    .button {
    display: inline-block;
    outline: none;
    cursor: pointer;
    text-align: center;
    text-decoration: none;
    font: 16px/100% 'Microsoft yahei',Arial, Helvetica, sans-serif;
    padding: .5em 2em .55em;
    text-shadow: 0 1px 1px rgba(0,0,0,.3);
    -webkit-border-radius: .5em; 
    -moz-border-radius: .5em;
    border-radius: .5em;
    -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.2);
    -moz-box-shadow: 0 1px 2px rgba(0,0,0,.2);
    box-shadow: 0 1px 2px rgba(0,0,0,.2);
}
</style>
  </head>
<body>         
 <h3>  作者详细信息：      </h3> 
  <center><table border="1" >
  <tr>
  <td>AuthorID      </td>      
  <td>Name       </td>
  <td>Age      </td>
  <td>Country   </td>
  </tr>
  <s:iterator value="author">
  <tr>  
      <td><s:property value="AuthorID" />      
      <td><s:property value="Name" /> 
      <td><s:property value="Age" />
      <td><s:property value="Country" />
           </tr>
  </s:iterator>
  </table></center>
  <h3>  书籍详细信息      </h3>
  <center><table border="1">
  <tr>      
  <td>Isbn       </td>
  <td>Title      </td>
  <td>AuthorID   </td>
  <td>Publisher  </td>
  <td>PublishDate</td>
  <td>price      </td>
  <td>delete</td>
  <td>update</td>
  </tr>
  <s:iterator value="information">
  <tr>          
      <td><s:property value="Isbn" /> 
      <td><s:property value="Title" />
      <td><s:property value="AuthorID" />
      <td><s:property value="Publisher" />
      <td><s:property value="PublishDate" />
      <td><s:property value="price" />  
      <td> <a href="<s:url action="deletebook.action">  
                    <s:param name="deleteIsbn" value="Isbn"/>  
                 </s:url>">删除
              </a> 
       <td> <a href="<s:url action="update.action">  
                    <s:param name="old_isbn" value="Isbn"/>  
                 </s:url>">更新信息
              </a>  
    </tr>
  </s:iterator>
  </table>
  </center>
   <s:form action="return">             
 <s:submit class = "button" value="返回搜索主界面"/>
   </s:form> 
   
  <div align="right">
<input type ="button" value ="返回上一页"  class="button" onclick="javascript:history.back(1);">
</div>
</body>
</html>