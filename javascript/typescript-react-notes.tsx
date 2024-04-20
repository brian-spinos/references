import React from "react";

/**
 *
 *
 *
 *
 *
 *
 */

// Union type alias
type Status = "active" | "inactive" | "pending";

type boo = "aaa" | "bbb" | number;
type boo2 = void | unknown | undefined;

/**
 *
 *
 * function interfaces and types
 *
 *
 *
 */

type MyFunctionType = (arg1: number, arg2: string) => boolean;

interface MyFunctionInterface {
  (arg1: number, arg2: string): boolean;
}

type MyFuncType = <T extends number>(arg: T) => T;

interface IMyFunc {
  <T extends number>(arg: T): T;
}

/**
 *
 *
 * object interfaces and types
 *
 *
 */

// uses semi-colon (=>)
interface Person {
  name: string;
  firstName: string;
  address: {
    street: string;
    city: string;
  };
  callback: <T extends number>(arg1: number, arg2: T) => boolean;
}

// uses semi-colon, and EQUALS (=>)
type User = {
  name: string;
  firstName: string;
  address: {
    street: string;
    city: string;
  };
  callback: <T extends number>(arg1: number, arg2: T) => boolean;
};

/**
 *
 *
 *
 *
 */

// Intersection type alias
type Person2 = {
  name: string;
  age: number;
} & {
  email: string;
};

type Coordinate = [number, number];

/**
 *
 *
 *
 *
 */

interface FooProps {
  prop1: string;
  prop2: string;
  prop3: string;
}

//
// Using React.FC may not enforce strict prop typing by default, as it allows additional props to be passed without type checking
//
// const Foo: React.FC<FooProps> = ({ prop1, prop2 }) => {
const Foo = ({ prop1, prop2, prop3 }: FooProps) => {
  //
  //
  console.log({ prop1, prop2, prop3 });

  function identity<T>(arg: T): T {
    return arg;
  }

  // Define a generic arrow function - needs 'extends'
  const func2: IMyFunc = <T extends number>(arg: T): T => {
    return arg;
  };

  // Usage examples
  const result1 = identity<string>("Hello, world!");
  const result2 = identity<number>(42);

  // TypeScript can often infer the type argument from the provided argument
  const result3 = identity(true); // result3 has type boolean

  const value: string = "Hello, world!";
  const result = identity(value); // TypeScript infers result to be of type string

  return (
    <div>
      <p>Result 1: {result1}</p>
      <p>Result 2: {result2}</p>
    </div>
  );
};

export default Foo;
