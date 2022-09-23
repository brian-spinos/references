```java
 // https://www.baeldung.com/rest-template

    /**
     * {
     *     "userId": 1,
     *     "id": 1,
     *     "title": "delectus aut autem",
     *     "completed": false
     * }
     */
    // GET http://localhost:8080/api/v1/myapp/getForEntity-example
    @GetMapping("/getForEntity-example")
    public ResponseEntity<String> restTemplateForGET() {
        // import org.springframework.web.client.RestTemplate;

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/todos/1";

        ResponseEntity<String> res = restTemplate.getForEntity(
                url,
                String.class
        );

//        Map<String, Object> map = new HashMap<>();
//        map.put("headers", res.getHeaders());
//        map.put("status", res.getStatusCode());
//        map.put("body", res.getBody());
        return res;
    }

    /**
     * {
     *     "userId": "1",
     *     "id": 1,
     *     "title": "delectus aut autem",
     *     "completed": false
     * }
     */
    // GET http://localhost:8080/api/v1/myapp/get-with-exchange-example
    @GetMapping("/get-with-exchange-example")
    public FooBar restTemplateForGET_exchangeAPI() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/todos/1";

        HttpHeaders headers = new HttpHeaders();
        //headers.set("abc", "123");
        HttpEntity<FooBar> req = new HttpEntity<>(null, headers);

        ResponseEntity<FooBar> res = restTemplate.exchange(
                url,
                HttpMethod.GET,
                req,
                FooBar.class
        );

        return res.getBody();
    }

    /**
     * {
     *     "userId": "some-user-id",
     *     "id": 201,
     *     "title": "some-title",
     *     "completed": true
     * }
     */
    // GET http://localhost:8080/api/v1/myapp/post-with-exchange-example
    @GetMapping("/post-with-exchange-example")
    public FooBar restTemplateForGET_exchangeAPI2() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/todos/";

        FooBar body = new FooBar();
        body.setUserId("some-user-id");
        body.setId(1234);
        body.setTitle("some-title");
        body.setCompleted(true);

        HttpHeaders headers = new HttpHeaders();
        //headers.set("abc", "123");

        HttpEntity<FooBar> req = new HttpEntity<>(body, headers);

        ResponseEntity<FooBar> res = restTemplate.exchange(
                url,
                HttpMethod.POST,
                req,
                FooBar.class
        );

        return res.getBody();
    }
}

class FooBar {
    String userId;
    int id;
    String title;
    boolean completed;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
