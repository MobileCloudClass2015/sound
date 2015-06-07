<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../layout/header.jspf" %>
<%@ include file="../../layout/nav.jspf" %>

<c:set var="pagination" value="${authSearchFilter.pagination }"/>
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

        var form = document.forms['authSearchFilter'];
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
            <h1>Auth Manager</h1>
        </header>

        <article class="common_article">
            <form:form commandName="authSearchFilter" action="${cp}/manage/auth" method="get" htmlEscape="true">
                <form:hidden path="page" value="${pagination.currentPage}"/>
            </form:form>
            
            <table class="authTable">
                <thead>
                    <tr>
                        <th>
                            <span>Primary Key</span>
                        </th>
                        <th>
                            <span>Name</span>
                        </th>
                        <th>
                            <span>Email Address</span>
                        </th>
                        <th>
                            <span>Register Date</span>
                        </th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <td colspan="4">
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
                    <c:forEach items="${authList}" var="auth">
                        <tr>
                            <td>
                                    ${auth.id}
                            </td>
                            <td>
                                ${auth.name}
                            </td>
                            <td>
                                ${auth.email}
                            </td>
                            <td>
                                ${auth.registerDate}
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>


        </article>

    </section>

</section>

<%@ include file="../../layout/footer.jspf" %>