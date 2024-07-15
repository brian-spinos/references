// $ npm test ./src/test/components/react-testing-library/example-2.test.jsx

// react-testing-library.js

// Docs:
// https://testing-library.com/docs/queries/bytestid/
// https://testing-library.com/docs/react-testing-library/intro/
// https://testing-library.com/docs/react-testing-library/api

// Files:
// FooCounter.jsx
// FooCounter.test.js

// Run tests:
// npm run test

// data-testid="custom-element"

//
//
//

/*

// Useful links

// Queries: 
https://testing-library.com/docs/queries/about

// Firing events
https://testing-library.com/docs/dom-testing-library/api-events

// mock uploading files ???
https://testing-library.com/docs/dom-testing-library/api-events/#fireeventeventname

// async 
https://testing-library.com/docs/dom-testing-library/api-async

// userEvent
https://testing-library.com/docs/user-event/intro // <-- as a user, for more robust tests

// router
https://testing-library.com/docs/example-react-router

// API mocks?
https://testing-library.com/docs/react-testing-library/example-intro#mock

// Test forms ???
https://testing-library.com/docs/react-testing-library/faq (How do I test input onChange handlers?)

// cofig (like timeouts)
https://testing-library.com/docs/dom-testing-library/api-configuration

// findBy* docs
https://testing-library.com/docs/dom-testing-library/api-async/#findby-queries

*/

// arrange
// act
// assert

import React from "react";
import {
  render,
  fireEvent,
  waitFor,
  // act, // seem you dont need it, I tried to find a usecase, but didnt find it -- seems all react-testing-library helpers are wrapped with `act` ???
  screen,
  cleanup,
  prettyDOM,
  waitForElementToBeRemoved,
} from "@testing-library/react";

import Foo from "../../../main/react-testing-library/Foo.jsx";

/**
 * To unmount a component and perform any necessary cleanup after a test is finished running.
 *
 * It ensures that any side effects from one test do not affect subsequent tests
 * Seems it runs automatically when using react-testing-library
 *
 * afterEach(() => {
 *   cleanup();
 * });
 *
 */

describe("Foo Component", () => {
  it("render example #1", async () => {
    const { getByText, getByTestId } = render(<Foo />);

    const fooValue = getByTestId("f1");
    const button = getByTestId("f2");

    // console.log(prettyDOM(button)); // prettyDOM

    expect(fooValue.textContent).toBe("foo: 0");
    fireEvent.click(button);
    expect(fooValue.textContent).toBe("foo: 1");
  });

  it("screen example", async () => {
    // seems to be the same as the function in the `render` object... BUT, it searches all the screen, NOT just the rendered component

    const { getByText, getByTestId } = render(<Foo />);

    const fooValue = screen.getByTestId("f1");
    const button = screen.getByTestId("f2");

    expect(fooValue.textContent).toBe("foo: 0");
    fireEvent.click(button);
    expect(fooValue.textContent).toBe("foo: 1");
  });

  it("render example #2", async () => {
    const {
      container,
      wrapper, // ???
      rerender,
      cleanup,
      // act,
      unmount,
      asFragment,
      baseElement,
      getByTestId,
      findByTestId,
      getAllByTestId,
      queryByTestId,
      queryAllByTestId,
      debug, // NOTE: It's recommended to use screen.debug instead.
    } = render(<Foo />);

    // debug(); // prints <body> element and it's contents
    // screen.debug(); // prints <body> element and it's contents

    let elem4 = getByTestId("f4");
    let elem4_find = await findByTestId("f4");
    let elem4_find2 = queryByTestId("f4");
    let elems = getAllByTestId("f3");
    // console.log({ elems: elems[0] });

    let elems2 = queryAllByTestId("f3");
    // console.log({ elems2: elems2[0] });

    // screen.findBy*
    // screen.getBy*
    // screen.queryBy*
    // screen.logTestingPlaygroundURL(); // opens a URL for you to play with component
  });

  it("fireEvent example", async () => {
    const { getByTestId } = render(<Foo />);

    let button1001 = getByTestId("f1001");
    const fooValue2 = getByTestId("f1002");
    expect(fooValue2.textContent).toBe("field5: 0");
    fireEvent.click(button1001); // TODO: checkbox example
    expect(fooValue2.textContent).toBe("field5: 1");

    //

    let field1 = getByTestId("f2001");
    const f2001_ = getByTestId("f2002");
    expect(f2001_.textContent).toBe("field1: ");
    fireEvent.change(field1, { target: { value: "abc" } });
    expect(f2001_.textContent).toBe("field1: abc");

    //

    let formElement = getByTestId("f2000");
    let formSubmitIndicator = getByTestId("f2003");
    expect(formSubmitIndicator.textContent).toBe("N");
    fireEvent.submit(formElement);
    expect(formSubmitIndicator.textContent).toBe("Y");

    //

    // TODO:  keyPress keyUp
    //      fireEvent.blur();
    //      fireEvent.focus();
    let inputElement3001 = getByTestId("f3001");
    // expect(inputElement3001.textContent).toBe("");
    expect(inputElement3001).toHaveValue("");
    // fireEvent.keyDown(inputElement3001, { key: "Enter", code: "Enter" });
    // fireEvent.keyDown(inputElement3001, { key: "a", code: "KeyA" });
    fireEvent.keyDown(inputElement3001, { key: "a" });
    // expect(inputElement3001.textContent).toBe("a"); // did not work
    expect(inputElement3001).toHaveValue("-AAA");

    fireEvent.keyDown(inputElement3001, { key: "Enter" });
    expect(inputElement3001).toHaveValue("-AAA-BBB");

    //
    // TODO: ...more...
    //
  });

  it("waitFor example", async () => {
    /**
     * We use waitFor in testing when we need to wait for some asynchronous behavior to
     * complete before we can make an assertion about the state of the application.
     *
     * Docs: https://testing-library.com/docs/dom-testing-library/api-async/#waitfor
     *
     */
    const { getByText } = render(<Foo />);
    const loadingText = getByText("Loading...");
    expect(loadingText).toBeInTheDocument();
    await waitFor(
      () => {
        const asyncContent = getByText("Hello, World!");
        expect(asyncContent).toBeInTheDocument();
      },
      { timeout: 5000 }
    );
  });

  it("waitForElementToBeRemoved example", async () => {
    const { getByText } = render(<Foo />);
    const loadingText = getByText("Loading...");
    expect(loadingText).toBeInTheDocument();

    await waitForElementToBeRemoved(() => getByText("Loading..."), {
      timeout: 5000,
    });

    const asyncContent = getByText("Hello, World!");
    expect(asyncContent).toBeInTheDocument();
  });

  it("expect example", async () => {
    const { getByTestId } = render(<Foo />);
    let element = getByTestId("f5001");

    expect(element).toBeInTheDocument();
    expect(element).toHaveAttribute("href", "https://example.com");
    expect(element).toHaveClass("my-class");
    expect(element).toHaveStyle("background-color: red");
    expect(element).toHaveTextContent("MY DIV");
    expect(element.textContent).toBe("MY DIV");
    expect(element).toBeVisible();
    // expect(element).not.toBeVisible()
    // expect(element).not.toBe*
    // expect(element).toHaveBeenCalled*
  });

  it("findBy* example", async () => {
    const { getByText, findByText } = render(<Foo />);
    const loadingText = getByText("Loading...");
    expect(loadingText).toBeInTheDocument();

    // await waitForElementToBeRemoved(() => getByText("Loading..."), {
    //   timeout: 5000,
    // });

    const asyncContent = await findByText("Hello, World!", {
      timeout: 5000,
    });
    expect(asyncContent).toBeInTheDocument();
  });

  xit("ACT example", async () => {
    // act (we might not need it) -- seems all react-testing-library's functions are wrapped in 'act' ???
    //
    //
    // to ensure that all updates to the component are complete before we make any
    // assertions about the component's state or behavior.
    // act(() => {
    //     fireEvent.click(incrementButton);
    //   });
  });

  // const items = await screen.findAllByText(/Item #[0-9]: /)
  // queryBy*
  // expect(submitButton).toBeNull() // it doesn't exist
  // within(messages).getByText('hello')

  // fireEvent dispatches DOM events, whereas
  //    user-event simulates full interactions,
  //       which may fire multiple events and do additional checks along the way.
});

// ================================================================================ fetch API mock

// // This is the test that waits for the API call to complete.
// test("displays the data after fetching it from the API", async () => {
//   // Here we mock the `fetch` call to return some sample data.
//   global.fetch = jest.fn(() =>
//     Promise.resolve({
//       json: () =>
//         Promise.resolve({ title: "My Title", description: "My Description" }),
//     })
//   );

//   // Render the component.
//   render(<MyComponent />);

//   // Wait for the API call to complete and the data to be displayed.
//   await waitFor(() => {
//     expect(screen.getByText("My Title")).toBeInTheDocument();
//     expect(screen.getByText("My Description")).toBeInTheDocument();
//   });
// });

// ================================================================================ axios API mock
// // Here we mock the `axios.get` call to return some sample data.
// jest.spyOn(axios, "get").mockResolvedValue({
//   data: { title: "My Title", description: "My Description" },
// });
