<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="cp" value="<%=request.getContextPath() %>"/>
<c:set var="rp" value='<%=request.getAttribute("javax.servlet.forward.request_uri")%>'/>

<!DOCTYPE html>
<html lang="kr">
<head>
    <title></title>
</head>
<body>
<c:choose>
  <c:when test="${line ne null}">
    <c:out value="${line}"/>
  </c:when>
  <c:otherwise>
    데이터 존재하지 않습니다.
  </c:otherwise>
</c:choose>

</body>
</html>
