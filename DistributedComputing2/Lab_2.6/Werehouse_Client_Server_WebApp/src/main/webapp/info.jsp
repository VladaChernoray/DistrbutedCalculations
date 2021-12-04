<%@ page import="java.util.ArrayList" %>
<%@ page import="Werehouse.Product" %>
<%@ page import="Werehouse.Section" %><%--
  Created by IntelliJ IDEA.
  User: vlada
  Date: 04/12/2021
  Time: 12:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ArrayList products = (ArrayList)request.getAttribute("products");%>
<%ArrayList sections = (ArrayList)request.getAttribute("sections");%>
<html>
<head>
    <title>Werehouse info</title>
</head>
<body>
<hr>
<h2>Products : </h2>
<table>
    <tr>
        <td>ID</td>
        <td>Country</td>
        <td>Name</td>
        <td>Price</td>
        <td>SectionID</td>
    </tr>
    <hr>
    <% for(int i = 0; i < products.size(); i++){%>
    <tr>
        <% Product w = (Product) products.get(i);%>
        <td><%= w.getProductID()%></td>
        <td><%= w.getCountry()%></td>
        <td><%= w.getName()%></td>
        <td><%= w.getPrice()%></td>
        <td><%= w.getSectionID()%></td>
    </tr>
    <%}%>
</table>

<hr>

<h2>Sections : </h2>
<table>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Number</td>
    </tr>
    <hr>
    <% for(int i = 0; i < sections.size(); i++){%>
    <tr>
        <% Section w = (Section) sections.get(i);%>
        <td><%= w.getSectionID()%></td>
        <td><%= w.getName()%></td>
        <td><%= w.getNumber()%></td>
    </tr>
    <%}%>
</table>
</body>
</html>
