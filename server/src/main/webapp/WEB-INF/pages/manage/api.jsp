<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../layout/header.jspf" %>
<%@ include file="../../layout/nav.jspf" %>

<section>

	<%-- Search --%>
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
					$("#track").text(JSON.stringify(result.track));
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
	<span id="track"></span>
	<br/>

	<%-- Recommend --%>
	<script>
		$(function(){
			$('#recommendBtn').on('click', function(){
				var url = contextPath + '/request/recommend'
				var json = {
					trackId : $('#trackId').val(),
					count : $('#recommendCount').val()
				};
				
				$.postJSON(url,json, function(result){
					$('#recommendResult').text(JSON.stringify(result));
					$("#tracks").text(JSON.stringify(result.tracks));
				});
			})
			
		});
	</script>

	<input type="text" id="trackId" value="msNbcnXS38fP4HYI"/>
	<input type="text" id="recommendCount" value="10"/>
	
	<button type="button" id="recommendBtn">Recommend</button>
	
	<span id="recommendResult"></span>
	<span id="tracks"></span>
	<br/>
	
	<%-- RecommednMap --%>
	<%-- Search --%>
	<script>
		$(function(){
			$('#recommendSearch').on('click', function(){
				var url = contextPath+'/recommendList';
				var json = {
					artist : $('#recommendArtist').val(),
					title : $('#recommendTitle').val()
				};

				$.postJSON(url, json, function(result){
					$('#recommendListResult').text(JSON.stringify(result));
				});

			});
		});
	</script>

	<input type="text" id="recommendTitle" value="거짓말"/>
	<input type="text" id="recommendArtist" value="GOD"/>

	<button type="button" id="recommendSearch">Recommend List</button>

	<span id="recommendListResult"></span>
	<br/>

</section>

<%@ include file="../../layout/footer.jspf" %>