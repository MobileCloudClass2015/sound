<%@ include file="../layout/header.jspf" %>

<script>
    $(function(){
        $('.error_wrap').on('click', function(){
            window.history.back();
        });
    });
</script>

<section class="error_wrap">
    <img src="${cp}/resources/image/error/500.png" />
</section>

<%@ include file="../layout/footer.jspf" %>