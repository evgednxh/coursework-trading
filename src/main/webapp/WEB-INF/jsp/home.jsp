<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <header class="header">
        <div class="home-container flex-row flex--jc-sb">
            <div class="header__text text">${username}</div>
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
        <section class="stock-section home-container">
            <h1 class="stock-heading">Available stocks</h1>
            <ul class="stock-list">
                <c:forEach items="${stockList}" var="stock">
                    <li class="stock-list__item">
                        <div class="flex-row flex--jc-sb flex--ai-c">
                            <div>${stock.tradeName}</div>
                            <div>${stock.marketName}</div>
                            <div>${stock.tradePrice}</div>
                            <div>
                                <button
                                    onclick="showStockPopup({id: '${stock.id}', sellerID: '${stock.seller.id}', tradeName: '${stock.tradeName}', marketName: '${stock.marketName}', tradePrice: ${stock.tradePrice}})"
                                    class="auth__btn text text--c-light"
                                >
                                    Buy
                                </button>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </section>
    </main>
    <div id="stock-buy-popup" class="stock-buy-popup hidden flex-column flex--ai-c flex--jc-c">
        <div class="stock-buy-popup__card">
            <h2 class="stock-buy-popup__heading">Buy stocks</h2>
            <form action="/stock/buy" method="post" modelAttribute="buyStock" class="stock-buy-popup__form">
                <div class="flex-column">
                    <label class="stock-buy-popup__label">Trade name</label>
                    <input id="trade-name" class="stock-buy-popup__input" type="text" path="tradeName" disabled="true" />
                </div>
                <div class="flex-column">
                    <label class="stock-buy-popup__label">Market name</label>
                    <input id="market-name" class="stock-buy-popup__input" type="text" path="marketName" />
                </div>
                <div class="flex-column">
                    <label class="stock-buy-popup__label">Trade price</label>
                    <input id="trade-price" class="stock-buy-popup__input" type="number" path="tradePrice" disabled="true" />
                </div>
                <div class="flex-column">
                    <label class="stock-buy-popup__label">Amount</label>
                    <input id="stock-amount" class="stock-buy-popup__input" type="number" placeholder="Enter stock amount to buy" path="amount" />
                </div>
                <div>
                    <button id="submit-btn" type="submit" class="stock-buy-popup__btn auth__btn text text--c-light">Buy</button>
                </div>
            </form>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/home.js"></script>
</body>
</html>