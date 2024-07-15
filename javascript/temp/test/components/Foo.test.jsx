/**
 * expectations
 */
describe("aaa", () => {
  it("bbb", () => {
    let obj = {
      myNum: 10,
      myStr: "xxxfooxxx",
      myArr: [10, 20, 30],
      myFunc: () => {
        return "SUCCESS";
      },
      myErrFunc: () => {
        throw new Error("my-error");
      },
    };

    // BEN DUTT FM > CC.not

    // expect(x).toBe(10)
    expect(obj.myNum).toBe(10);
    expect(obj.myNum).toEqual(10);
    expect(obj.myNum).not.toBeNull();
    expect(obj.myNum).toBeDefined();
    expect(obj.myNum).not.toBeUndefined();
    expect(obj.myErrFunc).toThrow("my-error");
    expect(obj.myErrFunc).toThrow(new Error("my-error"));
    expect(obj.myErrFunc).toThrow(/my-error/);
    expect(obj.myNum).toBeTruthy();
    expect(obj.myNum).not.toBeFalsy();
    expect(obj.myStr).toMatch(/foo/);
    expect(obj.myNum).toBeGreaterThan(1);
    expect(obj.myNum).toBeGreaterThanOrEqual(10);
    expect(obj.myNum).toBeLessThan(11);
    expect(obj.myNum).toBeLessThanOrEqual(10);
    expect(obj.myNum).toBeCloseTo(10.0005);
    expect(obj.myArr).toContain(10);
  });
});
