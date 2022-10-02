# SAML

- Security Assertion Markup Language

- Provides SSO (Single Sign On)

###### Scenario
```
group of users need access to multiple apps, how to manage that?
```

###### 3 components/entities/parts:

- `User`
    - Attempts to use the `Service provider`
- (IdP) `Identity Provider`
    - an organization, that manages the user accounts
    - It produces the SAML XML file
- `Service provider` - the app 
    - It consumes the SAML XML file
    - It creates a session for the user
