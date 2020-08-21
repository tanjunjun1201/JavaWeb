<%@ page import="com.atguigu.pojo.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/6/21
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List<Student> studentList = (List<Student>) request.getAttribute("stuList");
%>
<table>
<%
    for(Student student:studentList){
        %>
        <tr>
            <td><%=student.getName()%></td>
            <td><%=student.getId()%></td>
            <td><%=student.getAge()%></td>
            <td><%=student.getPhone()%></td>


        </tr>

<%
    }
%>
</table>


</body>
</html>
