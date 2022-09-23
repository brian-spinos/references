# OAuth

- This is just an initial draft, URLs might not be a good example...

- https://www.youtube.com/watch?v=CPbvxxslDTU

```
Spotify (OAuth client)
facebook (OAuth SERVER)

IMPORTANT: passwords are NEVER passed around during this process

-> ROLES
    -> user
    -> application (CLIENT) example: 'Spotify'
    -> API
        -> AUTHENTICATION SERVER  example: 'facebook'
        -> RESOURCE SERVER



-> basically, the CLIENT gets a token, then an access token, then user data (3 round trips)
-> use OAuth when you want to delegate the sign-in to a 3rd party



1. CLIENT makes a request to SERVER
   and sends clientId
   and redirectEndpointURL
   example:  GET facebook/oauth?clientId=abc&redirect=mybank.com

2. SERVER asks USER if he wants to authorize CLIENT to access your data
   - If user agrees to give CLIENT access to data,
     SERVER redirects user to CLIENT
     and CLIENT receives a token from SERVER
     - example: token=aaaaaa394rwn

4. now CLIENT can make requests to SERVER to get the 'access-token'
   with:
   - clientId
   - clientSecret
   - token to get user data (the token the CLIENT received from the SERVER)
   example:  GET facebook/oauth/get-access-token?
                 clientId=abc&
                 clientSecret=bbbbbb9ds8fu&
                 user-token=aaaaaa394rwn

5. now the SERVER will respond with an 'access-token',
   so the CLIENT has a limited amount of time to get user data,
   like 30min
   - example: access-token=ccccccwfd9

6. now the CLIENT can access user data for a limited time
   using the 'access-token'
   - example: GET facebook/user-info?
                  access-token=ccccccwfd9



 ======================= CLIENT REGISTRATION:

 CLIENT provides the SERVER with:
     - name
     - website
     - redirectURL

SERVER gives client:
    - clientID 
    - clientSecret

```

```
OAuth Grant Types:
    - ?
    - ?
    - ?
    - ?
```
