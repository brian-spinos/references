// CoSE MRCaRLID - 28

// context, state, effect, memo, ref, reducer, callback, layoutEffect, ImperativeHandle, debugValue

// new hooks:

import React, {
  createContext,
  useCallback,
  useContext,
  useDebugValue,
  useEffect,
  useLayoutEffect,
  useMemo,
  useReducer,
  useRef,
  useState,
} from "react";

//==================================================================================================== useContext
// Context: parent, provider, child comps

interface IContext {
  name: string;
}

const MyContext = createContext<IContext>({ name: "brian" }); // default value

const Parent1 = () => {
  const value = { name: "ana" };

  return (
    <MyContext.Provider value={value}>
      <Child1 />
    </MyContext.Provider>
  );
};

const Child1 = () => {
  const ctx = useContext(MyContext);

  return (
    <>
      <p>ctx: {ctx.name}</p>
    </>
  );
};

//==================================================================================================== use State

interface IState {
  name: string;
}

const Parent2 = () => {
  const [foo, setFoo] = useState<IState>({ name: "brian" });
  return (
    <>
      <p>foo: {foo.name}</p>
      <button onClick={() => setFoo({ name: "ana" })}>setFoo</button>
    </>
  );
};

//==================================================================================================== useEffect

// Runs side effects after component renders

// change x based on y

const Parent3 = () => {
  const [x, sx] = useState<number>(0);
  const [y, sy] = useState<number>(0);

  // DOM is updated, Browser paints, then this function runs  - LPX
  useEffect(() => {
    sy((prev) => prev + 1);
  }, [x]);

  return (
    <>
      <p>x:{x}</p>
      <p>y:{y}</p>

      <button onClick={() => sx((prev) => prev + 1)}>sx</button>
    </>
  );
};

//==================================================================================================== useMemo

// calculate a value based of another value,
// if its the same, no need to re-calculate

interface IProps4 {
  num: number;
}

const Child4 = ({ num }: IProps4) => {
  const val = useMemo<number>(() => {
    let x = 0;
    for (let i = num; i < 1_000_000_000; i++) {
      x += i;
    }
    return x;
  }, [num]);

  return (
    <>
      <p>val: {val}</p>
    </>
  );
};

const Parent4 = () => {
  return <Child4 num={10} />;
};

//==================================================================================================== useRef

// reference to a HTML element, or to a variable that does not change on re-render

type ButtonEvent = React.MouseEvent<HTMLButtonElement>;
type ButtonElement = HTMLButtonElement;

const Parent5 = () => {
  const myRef = useRef<ButtonElement>(null);

  const handleA = (e: ButtonEvent) => {
    console.log("A");
  };

  const handleB = (e: ButtonEvent) => {
    console.log("B");
    myRef.current?.click();
  };

  return (
    <>
      <button onClick={handleA} ref={myRef}>
        A
      </button>
      <button onClick={handleB}>B</button>
    </>
  );
};

//==================================================================================================== useReducer

// more complex than useState

interface IState6 {
  count: number;
}

type IAction6 =
  | { type: "INC"; payload: number }
  | { type: "DEC"; payload: number };

const initialState: IState6 = {
  count: 0,
};

const red = (state: IState6, action: IAction6): IState6 => {
  switch (action.type) {
    case "INC": {
      return { ...state, count: state.count + action.payload };
    }
    case "DEC": {
      return { ...state, count: state.count - action.payload };
    }
    default: {
      // exhaustive check
      const _ec: never = action;
      throw new Error(`error: ${_ec}`);
    }
  }
};

const Parent6 = () => {
  const [state, dispatch] = useReducer(red, initialState);

  const handleClick = (e: React.MouseEvent<HTMLButtonElement>) => {
    dispatch({ type: "INC", payload: 10 });
  };
  return (
    <>
      <p>count: {state.count}</p>
      <button onClick={handleClick}>click</button>
    </>
  );
};

//==================================================================================================== useCallback

// memoize functions, when passed as props

type Func = () => void;

interface Iprops7 {
  callback: Func;
}

interface IpropsParent7 {
  foo: string;
}

const Parent7 = ({ foo }: IpropsParent7) => {
  const callback = useCallback<Func>(() => {
    console.log("callback");
  }, [foo]);

  return (
    <>
      <Child7 callback={callback} />
    </>
  );
};

const Child7 = React.memo(({ callback }: Iprops7) => {
  return (
    <>
      <p>aaa</p>
      <button onClick={callback}>click</button>
    </>
  );
});

//==================================================================================================== useLayoutEffect

// avoids multiple re-paints, if 2 states change

// browser rendering pipeline:

// change x based on y

/*
Explaination:
    1. user clicks x, and it changes x state
    2. delay happens on re-render
    3. useLayoutEffect will handle the change in x, and update y
        - browser layout will happen (calculation on sizes), but no rendering
        - will prevent browser paint (rendering) becuase it will wait for y to update
    4. browser will not paint (render)
*/

const Parent8 = () => {
  const [x, sx] = useState<number>(0);
  const [y, sy] = useState<number>(0);

  // runs synchronously after the DOM has been updated but before the browser paints

  // DOM is updated,  (then this function runs) , Browser paints   - LXP
  useLayoutEffect(() => {
    // with delay
    const tid = setTimeout(() => {
      sy((prev) => prev + 1);
    }, 50);

    return () => clearTimeout(tid);
  }, [x]);

  return (
    <>
      <p>x:{x}</p>
      <p>y:{y}</p>

      <button onClick={() => sx((prev) => prev + 1)}>sx</button>
    </>
  );
};

//==================================================================================================== useImperativeHandle

// TODO

//==================================================================================================== useDebugValue

// used inside custom hooks

// React DevTools > Components >

const useFoo = () => {
  let val = 10;

  // multiple values will be converted to an array in devtools
  useDebugValue(val, (v: number) => v + 10);
  useDebugValue(val, (v: number) => v + 10);
  useDebugValue(val, (v: number) => v + 10);
};

//====================================================================================================
