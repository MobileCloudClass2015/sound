<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jspf" %>

<sec:authorize access="isAnonymous()">
<section class="user_login_wrap">
    
    <header class="user_login_header">
        <h1>
            Sound
        </h1>
    </header>

    <article class="user_login_article">
        <form id="user" action="${cp}/j_spring_security_check" method="post">
            <ul class="user_login_form">
                <li>
                    <input id="auth_username" name="auth_username" type="text" placeholder="ID" class="none"/>
                </li>
                <li>
                    <input id="auth_password" name="auth_password" type="password" placeholder="PW" class="none">
                </li>
                <c:if test="${not empty param.login_error}">
                    <li class="error">
                        <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
                    </li>
                </c:if>
                <li>
                    <button type="submit" class="button_common button_blue">Sign In</button>
                </li>
            </ul>
        </form>
    </article>
    
</section>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
<script>
    location.href= contextPath + '/dashboard'
</script>
</sec:authorize>

<%@ include file="../layout/footer.jspf" %>