import { render, screen } from "@testing-library/react";
import Foo from "../../main/components/Foo";

//--------------------------------------------------------- File: Foo.test.js

// run tests:
// $ npm test
// $ npm run test
// jest object is available by default in create-react-app app

let foo = { a: 1 };
let bar = { a: 1 };
let doThrowErr = () => {
  throw new Error("err");
};

test("renders Foo", () => {
  render(<Foo />);
  const paragraph = screen.getByText(/Foo 123/i);
  expect(paragraph).toBeInTheDocument();

  //---------------------------------------------------------

  let myMockFunction = jest.fn(); // jest.fn(myFunc)

  myMockFunction("abc");
  //---------------------------------------------------------

  myMockFunction.mockImplementation(() => {});
  myMockFunction.mockImplementationOnce(() => {});
  myMockFunction.mockResolvedValue(123);
  myMockFunction.mockReturnValue(123);
  myMockFunction.mockReturnValueOnce(123);

  //---------------------------------------------------------
  expect(1).toBe(1);
  expect(foo).toEqual(bar);
  expect(100).toBeGreaterThan(1);
  expect(100).toBeGreaterThanOrEqual(100);
  expect(myMockFunction).toHaveBeenCalledWith("abc");
  expect(null).toBeNull();
  expect(foo).toBeDefined();
  expect(foo).toBeTruthy();
  expect(false).toBeFalsy();
  expect("aaabbbccc").toMatch(/aaa/);
  expect([1, 2, 3]).toContain(2);
  expect(doThrowErr).toThrow(new Error("err"));
  expect(doThrowErr).toThrow("err");
  expect(doThrowErr).toThrow(/err/);

  //---------------------------------------------------------

  jest.spyOn(global, "fetch").mockImplementation(() => {
    return Promise.resolve({
      json: () => {
        let data = { foo: 123 };
        return Promise.resolve(data);
      },
    });
  });

  const url = "https://jsonplaceholder.typicode.com/users/1";
  fetch(url, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((res) => res.json())
    .then((data) => {
      console.log(data); // "foo 123"
    })
    .catch((error) => {
      console.log(error);
    });

  //---------------------------------------------------------

  //   myMockFunction.mock.calls[0][0];
  //   myMockFunction.mock.results[0].value;

  //--------------------------------------------------------- AXIOS: seems it is more complex to mock it

  //   jest.mock("axios");
  //   axios.get.mockImplementation(() => {}); // ?

  //   const url2 = "https://jsonplaceholder.typicode.com/users/1";
  //   let data = {
  //     name: "aaa",
  //     lname: "bbb",
  //   };

  //   axios.get(url2)
  //     .then(function (response) {
  //       // handle success
  //       console.log(response);
  //     })
  //     .catch(function (error) {
  //       // handle error
  //       console.log(error);
  //     })
  //     .then(function () {
  //       // always executed
  //     });
});
