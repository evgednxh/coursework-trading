const popup = document.getElementById("stock-buy-popup")
const tradeName = document.getElementById("trade-name")
const marketName = document.getElementById("market-name")
const tradePrice = document.getElementById("trade-price")
const submitBtn = document.getElementById("submit-btn")
const amount = document.getElementById("stock-amount")

let stockID = ""
let sellerID = ""

submitBtn.addEventListener('click', (e) => {
    e.preventDefault()
    console.log('hello')
    fetch('/stock/buy', {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: "include",
        body: JSON.stringify({
            id: stockID,
            seller: {id: sellerID},
            tradeName: tradeName.value,
            marketName: marketName.value,
            tradePrice: tradePrice.value,
            amount: amount.value,
        })
    })
        // .then(resp => resp.json())
        // .then(resp => console.log(resp))
        // .catch(err => console.log(err))
})

popup.addEventListener('click', (e) => {
    if(e.target === popup) {
        popup.classList.add('hidden')
    }
})

const showStockPopup = (stock) => {
    stockID = stock.id
    sellerID = stock.sellerID
    tradeName.value = stock.tradeName
    marketName.value = stock.marketName
    tradePrice.value = stock.tradePrice

    popup.classList.remove('hidden')
}