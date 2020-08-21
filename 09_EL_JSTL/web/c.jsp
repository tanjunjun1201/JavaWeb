<%@ page import="pojo.Person" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/6/22
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        Person person = new Person();
        person.setName("tanjunjun");
        person.setPhones(new String[]{"18656942059","15056568756","18119613334"});
        List<String> cities = new ArrayList<String>();
       cities.add("北京");
       cities.add("上海");
       cities.add("深圳");
       cities.add("广州");
        person.setCities(cities);
        Map<String,Object> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        person.setMap(map);


        pageContext.setAttribute("p",person);
    %>
        输出person:${p}<br/>
</body>
</html>
