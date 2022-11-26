import React, { useEffect, useState } from "react";

/**
 * useEffect
 * - so components can have a LIFECYCLE
 * - useEffect fires after every completed render of the component
 *     - after Layout and Paint
 *     - guaranteed to run before every new re-render of the component
 *     - limited with dependencies
 * - cleanup: subscriptions, timer ID, event handlers
 * - [] : empty dependencies - useEffect will fire only once
 */
const Foo = () => {
  console.log("------------------ Foo Rendering...");

  const [bar, setBar] = useState(0);
  const [bar2, setBar2] = useState(0);

  useEffect(() => {
    console.log("Foo > useEffect()", `bar: ${bar}`);
  }, [bar]);

  return (
    <>
      <div>Foo</div>
      <button
        onClick={() => {
          setBar(bar + 1);
        }}
      >
        click
      </button>
      <p>bar: {bar}</p>

      <br />

      <button
        onClick={() => {
          setBar2(bar2 + 1);
        }}
      >
        click2
      </button>
      <p>bar2: {bar2}</p>
    </>
  );
};

export default Foo;
