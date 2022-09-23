# Accept JSON post

```php
<?php

    /**
     * HTTP response helper
     */
    function myHttpResponse($status_code, $response) {
        header('Content-Type: application/json');
        http_response_code($status_code);
        echo json_encode($response);
    }

    /**
     * Get request
     */
    $request = json_decode(file_get_contents('php://input'), true);

    /**
     * Process request
     */
    $data = [
        'foo' => $request['foo'],
        'bar' => 456
    ];

    /**
     * Build response
     */
    $response = [
        'success' => true,
        'errors' => [],
        'data' => $data
    ];

    /**
     * Send response
     */
    myHttpResponse(200, $response);

?>
```
