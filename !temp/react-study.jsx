// Hooks

// React 18 - concurrent renders :)

/**

React version 18 provides 17 hooks 
1. useState
2. useEffect
3. useContext
4. useReducer
5. useRef
6. useMemo
7. useCallback
8. useLayoutEffect
9. useDebugValue
12. useImperativeHandle

10. useDeferredValue - React 18
11. useId - React 18
16. useTransition - React 18
13. useInsertionEffect --  - React 18
15. useSyncExternalStore --  - React 18

14. useOptimistic -- - React 19


17. use

useFormStatus - React 19
useFormState - React 19
 */

//=========================================================== // TODO

/**
 * react router
 * lazy loading
 * redux
 * redux-toolkit
 *
 * React.memo
 * pure components
 *
 * Synthetic events
 * debounce/throttle
 * error boundries
 * code-spliting
 * Jest
 * react-testing library
 * mocking
 * controlled vs uncontrolled components
 * HOC
 * render props
 * react concurrent mode (Suspense, React.lazy ?)
 * react devtools - (Performance profiling and detecting re-renders)
 * loadable-components
 * SSR - serverside rendering
 * SSG - static site generation
 * hydration
 * core web vitals (LCP, FID, CLS) - how to monitor
 * optimizing loading performance - prefetching, preloading resources <-------
 * typescript in react - props, state, event typing
 * container/presentational component pattern
 * virtualization - react-window, react-virtualized
 * build/deploy react apps - webpack,babel
 * deployment - AWS, Firebase
 * XSS propection
 * content security policy
 * Function as a Child Component (FACC) - the parent can define how the children is rendered - and child is responsible for the display logic
 *
 *
 *
 *
 *
 */

//=========================================================== useState

// add state to a component

import { useState } from "react";

const Foo = () => {
  const [foo, setFoo] = useState(0);

  const handleClick = () => setFoo((prev) => prev + 1);

  return (
    <>
      <p>{foo}</p>
      <button onClick={handleClick}>click</button>
    </>
  );
};

//=========================================================== useEffect

// performs side effects like:
// - DASET : Dom manipulation, API calls, subscriptions (web socket / RxJs), eventListeners, timers
// internal async
// - clean before unmount
// no loop: if(foo === false) setFoo(true)

// runs after render

import { useEffect } from "react";

const Foo2 = () => {
  useEffect(() => {
    return () => {
      /* cleanup code */
    };
  }, []);
  return (
    <>
      <p>foo2</p>
    </>
  );
};

//=========================================================== useLayoutEffect

// https://www.youtube.com/watch?v=9GAt97z8Jc4

// not good for performance !!!

// same as useEffect, but: it is SYNCHRONOUS, it blocks the rendering, so it might take longer to update, because it is waiting
// after all DOM mutations
// there might be delays between 2 state changes

import { useState, useLayoutEffect, useEffect } from "react";

export function App() {
  const [x, sx] = useState(0);
  const [y, sy] = useState(0);

  for (let i = 0; i < 100_000_000; i++) {} // simulate delay

  useLayoutEffect(() => {
    sy((prev) => prev + 1);
  }, [x]);

  const handleClick = () => {
    sx((prev) => prev + 1);
  };
  return (
    <div className="App">
      <h1>X {x}</h1>
      <h1>Y {y}</h1>
      <button onClick={handleClick}>click</button>
    </div>
  );
}

//======== another example: `width` is set to zero, then after useLayoutEffect it is updated to some number
// we use `useLayoutEffect` so there is no flickering

import { useState, useRef, useLayoutEffect, useEffect } from "react";

export function App() {
  const [width, setWidth] = useState(0);
  const divRef = useRef(null);

  for (let i = 0; i < 300_000_000; i++) {} // simulate delay

  // useLayoutEffect runs synchronously after all DOM mutations
  useLayoutEffect(() => {
    if (divRef.current) {
      setWidth(divRef.current.offsetWidth);
    }
  }, []); // The empty dependency array ensures it runs only once after the initial render

  return (
    <div>
      <div ref={divRef} style={{ width: "50%" }}>
        This div's width is {width}px
      </div>
    </div>
  );
}

//=========================================================== useContext
import { createContext, useContext, useState } from "react";

// access context without prop drilling

const MyContext = createContext(null); // default value

// my component
const Parent3 = () => {
  const [config, setConfig] = useState({ name: "foo" });
  return (
    <>
      <MyContext.Provider value={{ config, setConfig }}>
        <Child3 />
      </MyContext.Provider>
    </>
  );
};

const Child3 = () => {
  const { config, setConfig } = useContext(MyContext);
  return (
    <>
      <p>context</p>
      <p>{config.name}</p>
    </>
  );
};

//=========================================================== useReducer

// alternate to useState

const red = (state, action) => {
  // let payload = action.payload
  switch (action.type) {
    case "INC": {
      return { ...state, count: state.count + 1 };
      break;
    }
    case "DEC": {
      return { ...state, count: state.count - 1 };
      break;
    }
    default: {
      throw new Error("wrong action");
    }
  }
};

let is = { count: 0 };

const Foo3 = () => {
  const [data, dispatch] = useReducer(red, is);

  const handleInc = () => dispatch({ type: "INC", payload: 1 });
  const handleDec = () => dispatch({ type: "DEC", payload: 1 });

  return (
    <>
      <p>data: {data.count}</p>
      <button onClick={handleInc}>inc</button>
      <button onClick={handleDec}>dec</button>
    </>
  );
};

//=========================================================== useMemo

import { useState, useMemo } from "react";

const Foo4 = () => {
  const [foo, setFoo] = useState(0);

  //const memoizedValue = useMemo(() => computeExpensiveValue(a, b), [a, b]);

  const val = useMemo(() => {
    // sum indexes of 1M + foo
    return (
      Array.from({ length: 1_000_000 }, (_, i) => i).reduce(
        (a, b) => a + b,
        0
      ) + foo
    );
  }, [foo]);

  const handleClick = () => {
    setFoo((prev) => prev + 1);
  };

  return (
    <>
      <p>useMemo: {val}</p>
      <button onClick={handleClick}>click</button>
    </>
  );
};

//=========================================================== useCallback

// memoize a function reference - when passing callbacks to children, to avoid rerender

import { useState, useCallback } from "react";

const Foo5 = ({ callback }) => {
  const handleClick = () => {
    callback();
  };

  return (
    <>
      <p>Child</p>
      <button onClick={handleClick}>click</button>
    </>
  );
};

const Parent5 = () => {
  const [foo, setFoo] = useState(0);

  const cb = useCallback(() => {
    console.log(foo + 10);
  }, [foo]);

  const handleClick = () => {
    setFoo((prev) => prev + 1);
  };

  return (
    <>
      <p>Parent5</p>
      <Foo5 callback={cb} />
      <button onClick={handleClick}>click</button>
    </>
  );
};

//=========================================================== useRef

// https://www.youtube.com/watch?v=42BkpGe8oxg

// does not trigger rerender
// - updates are automatic, no async behaviour
// - access DOM elements
// - persist values across rerenders without triggering re-renders

import { useRef } from "react";

const Foo6 = () => {
  const myRef = useRef(null);

  // myRef.current.click();

  return (
    <>
      <p>useRef</p>
      <buttn ref={myRef} onClick={() => console.log(123)}>
        click
      </buttn>
    </>
  );
};

//=========================================================== useImperativeHandle

// make child's props accept a ref:  forwardRef(Child)

import { forwardRef, useImperativeHandle, useRef } from "react";

const Child7 = (props, ref) => {
  const internalRef = useRef(null);

  useImperativeHandle(ref, () => ({
    focus: () => internalRef.current?.focus(),
    click: () => internalRef.current?.click(),
  }));

  return (
    <>
      <p>Child7</p>
      <button
        ref={internalRef}
        onClick={() => {
          console.log(123);
        }}
      >
        click
      </button>
    </>
  );
};

// export default forwardRef(Child7);

// usage in the parent: <Child7 ref={childRef} />

//=========================================================== useDebugValue

// to be used inside a custom hook ! -- useMyCustomHook
let foo = "hello";
useDebugValue(foo, (foo) => foo.toUpperCase()); // formatter function - in case I have a slow function -> it is only run when I am in development mode and have the devtools open !!!

// in `Components` tab in JS console:
/*
hooks
  MyCustomHook:
      > DebugValue: "hello"



*/

//=========================================================== useId - React 18

import { useId } from "react";

const Foo7 = () => {
  // in case we have multiple inputs with same HTML id="email"
  const id = useId(); // output example: ':r1:', ':r2:'
  // best practice is to use 1 ID and do: `${id}-foo`, `${id}-bar`, ... for performance reasons

  return (
    <>
      <p>useId</p>
      <label htmlFor={id}>Email</label>
      <input id={id} type="email" />
    </>
  );
};

//=========================================================== use -- EXPERIMENTAL - can be used inside IF statement too !!!
import { Suspense, use } from "react";

// use(myPromise)

const Child8 = ({ url }) => {
  const data = use(fetch(url).then((res) => res.json()));

  return (
    <>
      <p>data:{data}</p>
    </>
  );
};

const Foo8 = () => {
  let url = "jsonplaceholder.typicode.com/posts";

  return (
    <>
      <p>Foo8</p>
      {/* For Errors: you need an ErrorBoundry :( */}
      <Suspense fallback={<div>loading...</div>}>
        <Child8 url={url} />
      </Suspense>
    </>
  );
};

//=========================================================== useDeferredValue

// - schedules an update of the copy of the variable, it can be interrupted
// - it it might lag behind - in the end it will always catch-up

// Use case: It's useful when you have a large number of updates happening concurrently
// (for example, typing in a search input or updating data from a large list)
// and want to make sure the UI remains responsive.

// - If you're typing in an input field that filters a large list,
// useDeferredValue might defer updates to the list rendering until after the user has finished typing.

// - kind of like debounce ????

const deferedVal = useDefferedValue(myPrimitiveTypeState); // boolean, string, number, ... no array, or object...

// TODO: implement

//=========================================================== useTransition
// https://www.youtube.com/watch?v=1xjSQJWejZM&t=4s

// - useTransition - interrupts a render for another higher priority render
// - Example; if you have to render 2500 items, then need to interrupt that do to some higher priority state update, say, change tabs

// it makes the rendering of those items a lower priority, say if you want to move to another tab :)

// TODO: implement

const [isPending, startTransition] = useTransition();

const someHighPriorityFunc = () => {
  startTransition(() => {
    setTab("tab2"); // something that updates state
  });
};

if (isPending) {
  // loading the 2500 items...
  return <p>Loading...</p>;
}

//=========================================================== useInsertionEffect

// - It's specialized for libraries managing styles.

// - insert styles dynamically before rendering - to prevent FOUC (flash of unstyled content)

// - it is for css-in-js to inject styles before browser paints (before DOM updates)

// - runs before useLayoutEffect and useEffect

// - it executes before react makes any changes to the DOM - no access to DOM elements

import { useInsertionEffect } from "react";

const Foo9 = () => {
  useInsertionEffect(() => {
    const style = document.createElement("style");
    style.innerHTML = ".my-class {color: red;}";
    document.head.appendChild(style);

    return () => document.head.removeChild(style);
  }, []);

  return <div className="my-class">Hello</div>;
};

//=========================================================== useOptimistic - React 19 ???

// TODO: implement

//=========================================================== useSyncExternalStore - used by redux
//=========================================================== useActionState ???

//
//
//

//=========================================================== lazy()

// https://www.youtube.com/watch?v=IpuOnfjY82E

import { lazy, Suspense } from "react";

const MyComponent = lazy(() => import("./components/MyComponent"));

{
  true && (
    <Suspense fallback={<p>Loading...</p>}>
      {/* browser will fetch this component code in the Network tab !!! */}
      <MyComponent />
    </Suspense>
  );
}

//=========================================================== custom hooks

// TODO: implement
//=========================================================== xxxx
//=========================================================== xxxx
//=========================================================== xxxx
//=========================================================== xxxx
//=========================================================== xxxx
//=========================================================== xxxx
//=========================================================== xxxx
//=========================================================== xxxx
//=========================================================== xxxx
//=========================================================== xxxx
//=========================================================== xxxx
//=========================================================== xxxx
//=========================================================== xxxx
