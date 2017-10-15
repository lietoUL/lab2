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
       
 <center>  
 <h2>  请输入需要更新的图书信息      </h2>
      <s:form action="upd">
       <s:textfield name="old_isbn"  value = "%{old_isbn}" readonly="true"  label="Isbn(不可修改)"></s:textfield>
       <h3>  下列是可修改的信息      </h3>
       <s:textfield name="update_book.AuthorID" label="AuthorID(int)" ></s:textfield><br/>
       <s:textfield name="update_book.Publisher" label="Publisher" ></s:textfield><br/>
       <s:textfield name="update_book.PublishDate" label="PublishDate" ></s:textfield> <br/>
       <s:textfield name="update_book.price" label="price(float)" ></s:textfield> <br/>
       <s:submit value="update" class="button"/>
    </s:form>
    </center>
</body>
</html>