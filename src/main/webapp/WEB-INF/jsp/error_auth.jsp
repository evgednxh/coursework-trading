<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up Error | Obsidere</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<main class="full-height flex-column flex--jc-c flex--ai-c text-center">
    <div>
        <div class="text text--semibold">Failed to sign up. User with such email already exists</div>
        <div><a href="/login">Log in</a> or <a href="/signUp">Sign up</a></div>
    </div>
</main>
</body>
</html>