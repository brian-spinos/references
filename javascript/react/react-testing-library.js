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


//
//
//

import React from "react";
import {
  render,
  fireEvent,
  screen,
  waitFor,
  act, // seem you dont need it, I tried to find a usecase, but didnt find it
  cleanup,
} from "@testing-library/react";
import FooCounter from "../../main/components/FooCounter";

describe("FooCounter", () => {
  it("should increment foo when the button is clicked", async () => {
    const { getByText, getByTestId } = render(<FooCounter />);
    const fooValue = getByTestId("foo-value"); // same as `screen.getByTestId("foo-value")`
    const button = getByText("Increment foo"); // same as `screen.getByText("Increment foo")`

    expect(fooValue.textContent).toBe("Value of foo: 0");

    fireEvent.click(button);

    expect(fooValue.textContent).toBe("Value of foo: 1");

    // expect(await screen.findByText("User Name Required")).toBeVisible();
  });
});

// ================================================================================ render

// getting elements is scoped by the component, NOT the whole screen, FYI

// .container
// .baseElement
// .getBy*
// .findBy*
// .getAllBy*
// .queryBy*
// .queryAllBy*
// .debug

//  expect(await screen.findByText('User Name Required')).toBeVisible()

// ================================================================================ fireEvent

// .click
// .change
// .submit
// .blur
// .focus
// .keydown
// ...and many user actions

// fireEvent.click(button);
// fireEvent.click(checkbox);
// fireEvent.change(input, { target: { value: 'testuser' } });

// ================================================================================ waitFor

// We use waitFor in testing when we need to wait for some asynchronous behavior to
// complete before we can make an assertion about the state of the application.

// const { getByText } = render(<AsyncComponent />);
// const loadingText = getByText('Loading...');
// expect(loadingText).toBeInTheDocument();

// await waitFor(() => {
//   const asyncContent = getByText('Here is some async content!');
//   expect(asyncContent).toBeInTheDocument();
// });
// Docs: https://testing-library.com/docs/dom-testing-library/api-async/#waitfor

// ================================================================================ screen

// seems to be the same as the function in the `render` object... BUT, it searches all the screen, NOT just the rendered component

// ================================================================================ act (we might not need it)

// to ensure that all updates to the component are complete before we make any
// assertions about the component's state or behavior.

// act(() => {
//     fireEvent.click(incrementButton);
//   });

// ================================================================================ cleanup

// // to unmount a component and perform any necessary cleanup after a test is finished running.
// afterEach(() => {
//     cleanup();
//   });
//
//   test('renders a component', () => { ...

// ================================================================================ expect
// expect(2 + 2).toBe(4);
//  expect({ a: 1, b: 2 }).toEqual({ a: 1, b: 2 });
//  expect(helloWorldElement).toBeInTheDocument();
// expect(linkElement).toHaveAttribute('href', 'https://example.com');
// expect(element).toHaveClass('my-class');
// expect(element).toHaveStyle('background-color: red');
// expect(helloWorldElement).toHaveTextContent('Hello, world!');
// expect(element).not.toBeVisible()
// expect(element).not.toBe*
// expect(element).toHaveBeenCalled*

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
