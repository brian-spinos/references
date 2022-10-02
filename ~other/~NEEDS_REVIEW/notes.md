# Notes

###### Securing an API
- the app generates a random hash
- use HTTP headers to send that hash to the server
- use SSL !


###### Omniauth
- idea: have a link in your app so users can sign in through twitter!
- use omniauth Module and provide it with your twitter_key and twitter_secret
- omniauth uses /auth/twitter endpoint
    - this link sends the user to twitter, so they can sign in there!
    - twitter will ask: 'Authorize FooApp to use your account?'
        - the user then can click sign-in, and twitter will redirect the user to FooApp with some query string parameters
    - FooApp should handle the /auth/twitter/callback path
        - omniauth gives you `env["omniauth.auth"]` with details about the user, with the users token and secret.
    - FooApp can then create or find a user, based on the info twitter and omniauth gave.
- use twitter


###### Sessions

- creates a files in a temp directory in the server
    - data will be available
    - server generates a hash
    - server sends a cookie to the client
        - PHPSESSID=abdfe9abe7dfabe7d82babdfea8ebae8df28edab822


