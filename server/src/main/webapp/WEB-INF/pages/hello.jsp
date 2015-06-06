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
	<script src="${cp}/resources/jquery-1.11.2.min.js"></script>
	<script src="${cp}/resources/jquery.json.js"></script>

	<script>
		var contextPath = '<c:out value="${cp}"/>';
		var realPath = '<c:out value="${rp}"/>';
	</script>
</head>
<body>

	<script>
		$(function(){
			$('#search').on('click', function(){
				var url = contextPath+'/request/search';
				var json = {
					artist : $('#artist').val(),
					title : $('#title').val(),
					start : $('#start').val(),
					count : $('#count').val()
				};
				
				$.postJSON(url, json, function(result){
					$('#result').text(JSON.stringify(result));
				});
				
			});
		});
	</script>

	<input type="text" id="title" value=""/>
	<input type="text" id="artist" value="GOD"/>
	<input type="text" id="start" value="0"/>
	<input type="text" id="count" value="10"/>
	
	<button type="button" id="search">Search</button>

	<span id="result"></span>

</body>
</html>
