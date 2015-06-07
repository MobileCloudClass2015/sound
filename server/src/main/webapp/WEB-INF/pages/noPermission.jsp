<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jspf" %>

<script>
    $(function(){
        $('.error_wrap').on('click', function(){
            window.history.back();
        });
    });
</script>

<section class="error_wrap">
    <img src="${cp}/resources/image/error/access.png" />
</section>

<%@ include file="../layout/footer.jspf" %>