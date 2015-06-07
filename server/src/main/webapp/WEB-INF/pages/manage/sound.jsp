<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../layout/header.jspf" %>
<%@ include file="../../layout/nav.jspf" %>

<c:set var="pagination" value="${soundFilter.pagination }"/>
<%-- Page 처리 Script --%>
<script type="text/javascript">
    /* <![CDATA[ */
    var numPagesPerScreen = <c:out value='${pagination.numPagesPerScreen}'/>;
    var page = <c:out value='${pagination.currentPage}'/>;
    var numPages = <c:out value='${pagination.numPages}'/>;
    function goToNextPages() {
        goToPage(Math.min(numPages, page + numPagesPerScreen));
    }
    function goToPage(page) {
        var input = document.getElementById('page');
        input.value = page;

        var form = document.forms['soundFilter'];
        form.submit();
    }
    function goToPreviousPages() {
        goToPage(Math.max(1, page - numPagesPerScreen));
    }
    /* ]]> */
</script>

<section class="common_section_wrap">

    <section class="common_section">

        <header class="common_header">
            <h1>Sound Manager</h1>
        </header>

        <article class="common_article">
            <form:form commandName="soundFilter" action="${cp}/manage/auth/${authId}" method="get" htmlEscape="true">
                <form:hidden path="page" value="${pagination.currentPage}"/>
            </form:form>
            
            <table class="sound_table">
                <thead>
                <tr>
                    <th>
                        <span>Title</span>
                    </th>
                    <th>
                        <span>Artist</span>
                    </th>
                    <th>
                        <span>Count</span>
                    </th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <td colspan="3">
                        <a href="javascript:void(goToPage(1))" onfocus="blur();">
                            처음
                        </a>
                        <a href="javascript:void(goToPreviousPages())" onfocus="blur();" class="page-beforeafter">
                            이전
                        </a>
                        <c:forEach var="i" begin="${pagination.pageBegin}" end="${pagination.pageEnd}">
                            <c:if test="${i == pagination.currentPage}">
                                <a class="page-link page-now">${i}</a>
                            </c:if>
                            <c:if test="${i != pagination.currentPage}">
                                <a class="page-link" href="javascript:void(goToPage(${i}))" onfocus="blur();">${i}</a>
                            </c:if>
                        </c:forEach>
                        <a href="javascript:void(goToNextPages())" onfocus="blur();" class="page-beforeafter">
                            다음
                        </a>
                        <a href="javascript:void(goToPage(${pagination.numPages}))" onfocus="blur();">
                            끝
                        </a>
                    </td>
                </tr>
                </tfoot>
                <tbody>
                <c:forEach items="${soundList}" var="sound">
                    <tr>
                        <td>
                                ${sound.title}
                        </td>
                        <td>
                                ${sound.artist}
                        </td>
                        <td>
                                ${sound.count}
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${fn:length(soundList) eq 0}">
                    <tr>
                        <td colspan="3" style="text-align: center;">
                            데이터가 존재하지 않습니다.
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </article>

    </section>

</section>

<%@ include file="../../layout/footer.jspf" %>