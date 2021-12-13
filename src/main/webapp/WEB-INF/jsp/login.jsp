<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login | Obsidere</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<main>
    <div class="container">
        <div class="flex-column flex--ai-c flex--jc-c">
            <div>
                <h1 class="text-heading text--semibold">Log in</h1>
                <form:form action="/login/user" method="post" modelAttribute="user" class="auth__form">
                    <div class="auth__form-row flex-column">
                        <label class="text-heading-small text--semibold">Email</label>
                        <form:input class="auth__input text" type="email" placeholder="Enter your email" path="email" required="true"/>
                    </div>
                    <div class="auth__form-row flex-column">
                        <label class="text-heading-small text--semibold">Password</label>
                        <form:input class="auth__input text" type="password" placeholder="Enter your password" minlength="4" path="password" required="true"/>
                    </div>
                    <button type="submit" class="auth__btn text text--c-light">Log in</button>
                </form:form>
                <div>Dont have an account? <a href="/">Sign in</a></div>
            </div>
        </div>
    </div>
</main>
</body>
</html>