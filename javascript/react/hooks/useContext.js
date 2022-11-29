import React, { createContext, useContext, useMemo, useState } from "react";

const UserContext = createContext({ name: "Brian" }); // default value

/**
 * InnerFoo
 *
 * to memoize context:
 *     - do React.memo() on child component
 *     - pass the ctx as props to the child component
 *     - use useMemo() to memoize the value passed to the Provider (in the parent)
 */
const InnerFoo = ({ ctx }) => {
  console.log("------------------ InnerFoo rendering...");

  /**
   * This hook will trigger a re-render, when the context value changes (if address in memory is different)
   * - you can use memoization to optimize
   */
  const user = useContext(ctx);

  return (
    <>
      <p>InnerFoo</p>
      <p>name: {user.name}</p>
      <p>age: {user.age}</p>
    </>
  );
};
const MemoizedInnerFoo = React.memo(InnerFoo);

/**
 * Foo
 */
const Foo = () => {
  console.log("------------------ Foo rendering...");

  const [user, setUser] = useState({ name: "Brian2", age: 30 });
  const memoizedUser = useMemo(() => user, [user.name, user.age]);

  return (
    <>
      <UserContext.Provider value={memoizedUser}>
        <MemoizedInnerFoo ctx={UserContext} />

        <button
          onClick={() => {
            setUser((prevUser) => ({ ...prevUser, age: prevUser.age + 1 })); // causes re-render
            // setUser((prevUser) => ({ ...prevUser })); // still causes rerender in InnerFoo, if not memoized
            // setUser((prevUser) => prevUser); // does NOT cause rerender in InnerFoo (because the memory address of the object is the same)
          }}
        >
          change context
        </button>
      </UserContext.Provider>
    </>
  );
};

export default Foo;
