// JS cookies

// Each cookie has a size limit (around 4KB per cookie) 
// and there's a limit on the number of cookies a browser can store for a single domain.

let getCookieMap = () => {
    let cookieMap = {}

    let cookies = document.cookie.split('; ');
    for (let cookie of cookies) {
      let [cookieName, cookieValue] = cookie.split('=');

      cookieMap[cookieName]=cookieValue
     
    }

    return cookieMap
}

let getCookie = (name) => {
    let cookies = document.cookie.split('; ');
    for (let cookie of cookies) {
      let [cookieName, cookieValue] = cookie.split('=');
      if (cookieName === name) {
        return cookieValue;
      }
    }
    return null;
}

let deleteCookie = (name) => {
    document.cookie = name + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC;";
}

/**
 * document.cookie = "username=john_doe; expires=" + new Date(Date.now() + 7 * 24 * 60 * 60 * 1000).toUTCString();
 */
let setCookie = (key, value, expirationInMiliseconds) => {
    let offset = expirationInMiliseconds
    let date = new Date(Date.now() + offset).toUTCString()
    document.cookie = `${key}=${value}; expires=${date}`;
}

setCookie('foo123', 'bar123', 7 * 24 * 60 * 60 * 1000)

getCookieMap()

getCookie('foo123');  // Output: "bar123"

deleteCookie('foo123');
