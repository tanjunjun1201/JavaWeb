<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/6/21
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <h1 align="center">九九乘法表</h1>
    <%
        for(int i =1;i<=9;i++){
            for(int j = 1;j<i;j++){
                %>
    <%=j+"x"+i+"="+j*i %>
    <%
            }
            %>
    <br/>
    <%
        }
    %>
</head>
<body>

</body>
</html>
