<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lab_7</title>
</head>
<body>
	<center>
        <h1>Sections</h1>
        <h2>
            <a href="${pageContext.request.contextPath}/SectionNew">Add New Section</a>
            &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/SectionList">List All Sections</a>
             &nbsp;&nbsp;&nbsp;
            <a href="index.html">Back</a>
        </h2>
    </center>
    <div align="center">
        <c:if test="${section != null}">
            <form action="SectionUpdate" method="post">
        </c:if>
        <c:if test="${section == null}">
            <form action="SectionInsert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${section != null}">
                        Edit Section
                    </c:if>
                    <c:if test="${section == null}">
                        Add New Section
                    </c:if>
                </h2>
            </caption>
                <c:if test="${section != null}">
                    <input type="hidden" name="id" value="<c:out value='${section.id}' />" />
                </c:if>           
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value='${section.name}' />"
                        />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>