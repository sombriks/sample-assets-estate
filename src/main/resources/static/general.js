//- https://pugjs.org/language/inheritance.html
htmx.logAll()

// we should have a token
document.addEventListener('htmx:configRequest', function (evt) {
  evt.detail.headers["X-Auth-Token"] = sessionStorage.getItem("X-Auth-Token")
});

// generic error handler
document.addEventListener('htmx:responseError', function (evt) {
  notify(evt.detail?.error, "is-danger")
  console.log(evt.detail?.xhr?.response)
});

function notify(message, type) {
  const notification = document.createElement("div")
  notification.classList.add("notification", type)
  notification.innerText = message
  const close = document.createElement("button")
  close.classList.add("delete")
  close.onclick = e => notification.parentNode.removeChild(notification)
  notification.appendChild(close)
  document.querySelector(".notifications").appendChild(notification)
}
