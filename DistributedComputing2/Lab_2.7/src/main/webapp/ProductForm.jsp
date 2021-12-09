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
            <a href="${pageContext.request.contextPath}/ProductNew">Add New Product</a>
            &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/ProductList">List All product</a>
             &nbsp;&nbsp;&nbsp;
            <a href="index.html">Back</a>
        </h2>
    </div>
    <div align="center">
        <c:if test="${product != null}">
            <form action="ProductUpdate" method="post">
        </c:if>
        <c:if test="${product == null}">
            <form action="ProductInsert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${product != null}">
                        Edit Product
                    </c:if>
                    <c:if test="${product == null}">
                        Add New Product
                    </c:if>
                </h2>
            </caption>
                <c:if test="${product != null}">
                    <input type="hidden" name="id" value="<c:out value='${producy.id}' />" />
                </c:if>           
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value='${product.name}' />"
                        />
                    <input type="text" name="sectionId" size="45"
                            value="<c:out value='${product.sectionId}' />"
                        />
                    <input type="text" name="population" size="45"
                            value="<c:out value='${product.price}' />"
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