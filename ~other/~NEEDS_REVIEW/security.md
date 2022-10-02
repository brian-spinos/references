# XSS (cross-site-scripting)

- A hacker posts a comment to a blog with some embedded javascript.
- The user then logs in, opens the page that has the comment with embedded javascript
- the javascript code is executed! potentially stealling a cookie, or some other information 

- Solution: Sanitize user input, escape javascript


# CSRF

- While the user is signed in, a hacker can send an email with a malicious link
- If the user clicks that link, the request is sent to the server, and treated as if the user himself sent it.
- So the hacker bypasses the login page.

- Solution: Create an authenticity_token and place it in a hidden field in the form, so the server can know it came from the user, and not the hacker

# SQL Injection

- A Hacker types in SQL code to delete a tabel, or to make himself an admin...
- If the server does not sanitize the user input, the SQL typed by the hacker will run.

- Solution: Sanitize user input, escape SQL
