/**
 *
 * Mocking functions test
 *
 * DOCS: https://jestjs.io/docs/mock-function-api
 *
 * npm test ./src/test/components/c1001/Test1001.test.jsx
 *
 */
describe("mocking functions", () => {
  it("should work", async () => {
    //

    let obj = {
      foo: () => {
        return 123_000;
      },

      foo2: () => "ORIGINAL",

      asyncFoo: async () => {
        return 123_000;
      },

      asyncFooRej: async () => {
        return Promise.reject("ERROR: asyncFooRej ORIGINAL");
      },
    };

    //

    obj.foo = jest.fn(() => 1000);
    expect(obj.foo()).toBe(1000);

    //
    //
    //

    obj.foo = jest.fn();
    obj.foo.mockImplementation(() => 1001);
    expect(obj.foo()).toBe(1001);

    //

    obj.foo = jest.fn();
    obj.foo.mockImplementationOnce(() => 1002);
    expect(obj.foo()).toBe(1002);
    expect(obj.foo()).toBe(undefined);

    //

    obj.foo = jest.fn();
    obj.foo.mockReturnValueOnce(1003);
    expect(obj.foo()).toBe(1003);
    expect(obj.foo()).toBe(undefined);

    //

    obj.foo = jest.fn();
    obj.foo.mockReturnValue(1004);
    expect(obj.foo()).toBe(1004);
    expect(obj.foo()).toBe(1004);

    //

    obj.asyncFoo = jest.fn();
    obj.asyncFoo.mockResolvedValueOnce(1005);
    expect(await obj.asyncFoo()).toBe(1005);
    expect(await obj.asyncFoo()).toBe(undefined);

    //

    obj.asyncFoo = jest.fn();
    obj.asyncFoo.mockResolvedValue(1006);
    expect(await obj.asyncFoo()).toBe(1006);
    expect(await obj.asyncFoo()).toBe(1006);

    //

    obj.asyncFooRej = jest.fn();
    obj.asyncFooRej.mockRejectedValueOnce(new Error("Mocked error"));
    await expect(obj.asyncFooRej()).rejects.toThrow("Mocked error");
    await expect(obj.asyncFooRej()).toBe(undefined);

    //

    obj.asyncFooRej = jest.fn();
    obj.asyncFooRej.mockRejectedValue(new Error("Mocked error"));
    await expect(obj.asyncFooRej()).rejects.toThrow("Mocked error");
    await expect(obj.asyncFooRej()).rejects.toThrow("Mocked error");

    //

    obj.asyncFooRej = jest.fn();
    obj.asyncFooRej(1, 2);
    obj.asyncFooRej(3, 4);
    expect(obj.asyncFooRej).toHaveBeenCalled();
    expect(obj.asyncFooRej).toHaveBeenCalledWith(1, 2);
    expect(obj.asyncFooRej).toHaveBeenCalledTimes(2);

    //

    obj.asyncFooRej.mockReturnThis();
    expect(obj.asyncFooRej()).toBe(obj);

    //

    obj.foo = jest.fn();
    obj.foo();
    obj.foo();
    expect(obj.foo).toHaveBeenCalledTimes(2);
    obj.foo.mockClear(); // clear calls
    expect(obj.foo).toHaveBeenCalledTimes(0);

    //

    obj.foo = jest.fn();
    obj.foo();
    obj.foo();
    expect(obj.foo).toHaveBeenCalledTimes(2);
    jest.clearAllMocks(); // clear calls
    expect(obj.foo).toHaveBeenCalledTimes(0);

    //

    obj.foo = jest.fn(() => 9000);
    expect(obj.foo()).toBe(9000);
    obj.foo.mockReset(); // mockClear() + remove fn implementation
    expect(obj.foo()).toBe(undefined);

    //

    obj.foo = jest.fn(() => 9000);
    expect(obj.foo()).toBe(9000);
    jest.resetAllMocks(); // mockClear() + remove fn implementation
    expect(obj.foo()).toBe(undefined);

    //

    jest.spyOn(obj, "foo2").mockImplementation(() => 99000); // <------- GOTCHA: use spy
    // obj.foo2 = jest.fn(() => 99000); // <---------------------------- this would not work
    expect(obj.foo2()).toBe(99000);
    obj.foo2.mockRestore(); // mockReset() + original fn implementation
    expect(obj.foo2()).toBe("ORIGINAL");

    //

    jest.spyOn(obj, "foo2").mockImplementation(() => 99000); // <------- GOTCHA: use spy
    // obj.foo2 = jest.fn(() => 99000); // <---------------------------- this would not work
    expect(obj.foo2()).toBe(99000);
    jest.restoreAllMocks(); // mockReset() + original fn implementation
    expect(obj.foo2()).toBe("ORIGINAL");

    //
    // m.mock.calls AND m.mock.results
    //

    let obj2 = {
      foo: () => "ORIGINAL-2",
    };

    obj2.foo = jest.fn(() => "MOCK-2");

    obj2.foo("a", "b");
    obj2.foo("x", "y");

    // An array containing the call arguments of all calls that have been made to this mock function
    //      m.mock.calls[0][0].results[0].value; // first index is calls, second is arguments
    console.log("obj2.foo.mock.calls", obj2.foo.mock.calls);
    //      => obj2.foo.mock.calls [ [ 'a', 'b' ], [ 'x', 'y' ] ]

    console.log("obj2.foo.mock.results", obj2.foo.mock.results);
    // obj2.foo.mock.results [
    //     { type: 'return', value: 'MOCK-2' },
    //     { type: 'return', value: 'MOCK-2' }
    // ]
  });
});

//----------------------

// spy.mockRestore();
