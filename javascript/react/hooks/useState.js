import React, { useState } from "react";

const calcInitialState = (arg) => {
  let i;
  for (i = 0; i < arg; i++) {}
  return i;
};

/**
 * useState
 * - so function components can have state
 * - setting the state enqueues a re-render of the component
 * - will not re-render if state change is same as previous
 * - lazy loading - runs only on initial render, and cached after
 */
const Foo = () => {
  // const [bar, setBar] = useState(0);

  // lazy loading:
  const [bar, setBar] = useState(() => {
    return calcInitialState(1_000_000_000);
  });

  return (
    <>
      <div>Foo</div>
      <button
        onClick={() => {
          // setBar(bar + 1);
          setBar((prev) => prev + 1);
        }}
      >
        click
      </button>
      <p>bar: {bar}</p>
    </>
  );
};

export default Foo;
