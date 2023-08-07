- Mock Axios

```json
// ./pakage.json

{
  //...
  "dependencies": {
    "axios": "^1.4.0",
    //...
  },
//...
  "jest": {
    "transformIgnorePatterns": [
      "node_modules/(?!axios)/"
    ]
  }
}
```

```js
// src/main/components/brian/Brian.jsx

import React, { useEffect, useState } from "react";
import axios from "axios";

const Brian = () => {
  const [res, setRes] = useState({});

  useEffect(() => {
    // axios
    //   .get("https://jsonplaceholder.typicode.com/todos/2")
    //   .then((response) => {
    //     console.log("data", response.data);
    //     setRes(response.data);
    //   });
  }, []);

  return (
    <div>
      Brian
      <button
        data-testid="my-button"
        onClick={async () => {
          let response = await axios.get(
            "https://jsonplaceholder.typicode.com/todos/1"
          );
          console.log("data1", response.data);
          setRes(response.data);
          axios
            .get("https://jsonplaceholder.typicode.com/todos/1")
            .then((response) => {
              console.log("data2", response.data);
              setRes(response.data);
            });
        }}
      >
        click me
      </button>
      <p data-testid="my-result">{JSON.stringify(res)}</p>
    </div>
  );
};

export default Brian;


```


```js
// src/main/components/brian/Brian.test.jsx

import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import axios from "axios";
import Brian from "./Brian";

jest.mock("axios");
let BASE_URL = "https://jsonplaceholder.typicode.com";

describe("Brian", () => {
  it("working test", async () => {
    // given
    const users = [
      { id: 1, name: "John" },
      { id: 2, name: "Andrew" },
    ];
    axios.get = jest.fn(() => users);

    // when
    let result = await axios.get(
      "https://jsonplaceholder.typicode.com/todos/1"
    );

    // then
    expect(axios.get).toHaveBeenCalledWith(`${BASE_URL}/todos/1`);
    expect(result).toEqual(users);
  });

  it("fetches and displays data on button click", async () => {
    // mock get
    const mockData = {
      mockResult: 1000,
      brian: "spinos",
    };
    axios.get = jest.fn(() => Promise.resolve({ data: mockData }));

    let wrapper = render(<Brian />);
    let pTag = screen.getByTestId("my-result");

    const btn = screen.getByTestId("my-button");
    fireEvent.click(btn);

    // Wait for the API call and state update to complete
    await waitFor(() => {
      expect(axios.get).toHaveBeenCalledWith(`${BASE_URL}/todos/1`);
      expect(pTag).toHaveTextContent(JSON.stringify(mockData));
    });
  });
});

```

