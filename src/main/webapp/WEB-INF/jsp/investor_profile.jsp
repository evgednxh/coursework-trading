<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Obsidere | Investor Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<header class="header">
    <div class="home-container flex-row flex--jc-sb">
        <div class="header__text text">${user.username}</div>
        <nav>
            <ul class="header__list flex-row">
                <li class="header__text">
                    <a class="header__link text" href="/home">Home</a>
                </li>
                <li class="header__text">
                    <a class="auth__btn text text--c-light" href="/signOut">Sign out</a>
                </li>
            </ul>
        </nav>
    </div>
</header>
<main>
    <section class="stock-section home-container">
        <h1 class="stock-heading">Investor profile</h1>
        <div>
            <div class="flex-column">
                <div class="text text--semibold">Username</div>
                <div class="text">${user.username}</div>
            </div>
            <div class="flex-column spacing-top-l">
                <div class="text text--semibold">Email</div>
                <div class="text">${user.email}</div>
            </div>
        </div>
        <div id="payment-row">
            <c:choose>
                <c:when test="${user.payment != null}">
                    <div class="spacing-top">
                        <h1 class="stock-heading">Your investments</h1>
                        <c:forEach items="${stocksInfo}" var="stock">
                            <li class="stock-list__item">
                                <div class="flex-row flex--jc-sb flex--ai-c">
                                    <div>${stock.tradeName}</div>
                                    <div>${stock.marketName}</div>
                                    <div>${stock.tradePrice}</div>
                                    <div>${stock.boughtAmount}</div>
                                </div>
                            </li>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="spacing-top">
                        <h1 class="stock-heading">Add payment card</h1>
                        <form:form action="/payment/new" method="post" modelAttribute="paymentInfo" class="auth__form">
                            <div class="auth__form-row flex-column spacing-top-l">
                                <label class="text text--semibold">Username</label>
                                <form:input class="auth__input text" required="true"  type="text" maxlength="16" minlength="16" placeholder="Enter your card number" path="cardNumber"/>
                            </div>
                            <button type="submit" class="btn text text--c-light">Next</button>
                        </form:form>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </section>
</main>
</body>
</html>