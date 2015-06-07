<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../layout/header.jspf" %>
<%@ include file="../../layout/nav.jspf" %>

<section class="common_section_wrap">

	<section class="common_section">

		<header class="common_header">
			<h1>Bonacell API Test</h1>
		</header>

		<article class="common_article">
			
			<header>
				<h2>Bonacell Search API</h2>
				<span>"/request/search"</span>
			</header>

			<ul class="common_input_wrap">
				<li>
					<label for="search_title">TITLE</label>
					<input type="text" id="search_title" value=""/>
				</li>
				<li>
					<label for="search_artist">ARTIST</label>
					<input type="text" id="search_artist" value="GOD"/>
				</li>
				<li>
					<label for="search_start">START</label>
					<input type="text" id="search_start" value="0"/>
				</li>
				<li>
					<label for="search_count">COUNT</label>
					<input type="text" id="search_count" value="10"/>
				</li>
			</ul>

			<button type="button" id="bonacell_search_btn" class="orangeButton">Search</button>
			
			<ul class="common_result_wrap" id="bonacell_search_result">
			</ul>
			
		</article>

		<article class="common_article">
			<header>
				<h2>Bonacell Recommend API</h2>
				<span>"/request/recommend"</span>
			</header>
	
			<ul class="common_input_wrap">
				<li>
					<label for="recommend_trackId">TRACK_ID</label>
					<input type="text" id="recommend_trackId" value="msNbcnXS38fP4HYI"/>
				</li>
				<li>
					<label for="recommend_count">COUNT</label>
					<input type="text" id="recommend_count" value="10"/>
				</li>
			</ul>
	
			<button type="button" id="bonacell_recommend_btn" class="orangeButton">Recommend</button>

			<ul class="common_result_wrap" id="bonacell_recommend_result">
			</ul>
		
		</article>
		
		<article class="common_article">
			
			<header>
				<h2>Search And Recommend API</h2>
				<span>"/recommendList"</span>
			</header>
			
			<ul class="common_input_wrap">
				<li>
					<label for="search_recommend_title">TITLE</label>
					<input type="text" id="search_recommend_title" value="거짓말"/>
				</li>
				<li>
					<label for="search_recommend_artist">ARTIST</label>
					<input type="text" id="search_recommend_artist" value="GOD"/>
				</li>
			</ul>

			<button type="button" id="bonalcell_search_recommend_btn" class="orangeButton">Search Recommend</button>

			<span id="recommendListResult"></span>
			
			<ul class="common_result_wrap" id="bonacell_search_recommend_result">
			</ul>
			
		</article>
		
	</section>

</section>


<%@ include file="../../layout/footer.jspf" %>