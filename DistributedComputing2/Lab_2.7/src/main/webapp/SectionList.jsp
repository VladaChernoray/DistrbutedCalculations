<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lab_7</title>
</head>
<body>
    <div style="text-align: center;">
        <h1>Sections</h1>
        <h2>
            <a href="${pageContext.request.contextPath}/SectionNew">Add New Section</a>
            &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/SectionList">List All Sections</a>
             &nbsp;&nbsp;&nbsp;
            <a href="index.html">Back</a>
        </h2>
    </div>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Sections</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="section" items="${listSections}">
                <tr>
                    <td><c:out value="${section.id}" /></td>
                    <td><c:out value="${section.name}" /></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/SectionEdit?id=<c:out value='${section.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/SectionDelete?id=<c:out value='${section.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>