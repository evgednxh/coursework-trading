<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Obsidere</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<main>
    <div class="container">
        <div class="flex-column flex--ai-c flex--jc-c">
            <div>
                <h1 class="text-heading text--semibold">Sign in</h1>
                <form:form action="/signIn" method="post" modelAttribute="user" class="auth__form">
                    <div class="auth__form-row flex-column">
                        <label class="text-heading-small text--semibold">Username</label>
                        <form:input class="auth__input text" type="text" minlength="4" placeholder="Enter your username" path="username" required="true"/>
                    </div>
                    <div class="auth__form-row flex-column">
                        <label class="text-heading-small text--semibold">Email</label>
                        <form:input class="auth__input text" type="email" placeholder="Enter your email" path="email" required="true"/>
                    </div>
                    <div class="auth__form-row flex-column">
                        <label class="text-heading-small text--semibold">Password</label>
                        <form:input class="auth__input text" type="password" minlength="4" placeholder="Enter your password" path="password" required="true"/>
                    </div>
                    <div class="auth__form-row flex-column">
                        <label class="text-heading-small text--semibold">Account type</label>
                        <form:select class="auth__input text" path="userType">
                            <option selected value="Investor">Investor</option>
                            <option value="BusinessOwner">Business owner</option>
                        </form:select>
                    </div>
                    <button type="submit" class="auth__btn text text--c-light">Sign in</button>
                </form:form>
                <div>Already has an account? <a href="/login">Log in</a></div>
            </div>
        </div>
    </div>
</main>
</body>
</html>