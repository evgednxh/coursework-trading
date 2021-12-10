<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Obsidere | Business Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<header class="header">
    <div class="home-container flex-row flex--jc-sb">
        <div class="header__text text">${user.username}</div>

        <nav>
            <ul class="header__list flex-row">
                <li class="header__text">
                    <a class="header__link text" href="/profile">Profile</a>
                </li>
                <li class="header__text">
                    <a class="auth__btn text text--c-light" href="/signOut">Sign out</a>
                </li>
            </ul>
        </nav>
    </div>
</header>
<main>

</main>
</body>
</html>