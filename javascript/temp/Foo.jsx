import React from "react";
import { useRef, useEffect, useState } from "react";

const fetchData = () => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve("Hello, World!");
    }, 900); // just under 1000ms as it seems to be the default time to wait in the tests...
  });
};

const Foo = () => {
  const ref3001 = useRef(null);

  const [isFormSubmitted, setIsFormSubmitted] = useState(false);

  const [formData, setFormData] = useState({
    field1: "",
    field2: 0,
    field3: "",
    field4: 0,
    field5: 0,
    field6: null,
  });

  /**
   * Adding 'keydown' event
   */
  useEffect(() => {
    const keyDownFunc = (e) => {
      setFormData((prev) => ({ ...prev, field2: prev.field2 + 1 }));
    };

    const btn3001 = ref3001.current;

    btn3001.addEventListener("keydown", keyDownFunc);

    const cleanupFunc = () => {
      btn3001.removeEventListener("keydown", keyDownFunc);
    };

    return cleanupFunc;
  }, []);

  /**
   * Fetching mock API data
   */
  useEffect(() => {
    const fetchDataAsync = async () => {
      const result = await fetchData();
      setFormData((prev) => ({ ...prev, field6: result }));
    };

    fetchDataAsync();
  }, []);

  //----------------------------- Helpers

  const handleFormSubmit = (e) => {
    e.preventDefault();
    // call API...
    setIsFormSubmitted(true);
  };

  const handleFormChange = (e) => {
    let t = e.target;
    const k = t.name;
    const v = t.type === "checkbox" ? t.checked : t.value;

    setFormData((prev) => ({ ...prev, [k]: v }));
  };

  const handleButtonClick = () => {
    setFormData((prev) => ({ ...prev, field4: prev.field4 + 1 }));
  };

  const handleButtonClick2 = () => {
    setFormData((prev) => ({ ...prev, field5: prev.field5 + 1 }));
  };

  const style = {
    backgroundColor: "#eee",
    padding: "10px",
    margin: "10px",
    border: "1px solid black",
  };

  //-----------------------------

  return (
    <div>
      <div style={style}>
        <div data-testid="f1">foo: {JSON.stringify(formData.field4)}</div>
        <button data-testid="f2" onClick={handleButtonClick}>
          Increment Foo
        </button>
        <div data-testid="f4">D</div>
        <div data-testid="f3">A</div>
        <div data-testid="f3">B</div>
        <div data-testid="f3">C</div>
      </div>

      <div style={style}>
        <p>fireEvent</p>
        {/*  
          TODO:
          fireEvent.blur();
          fireEvent.focus();
      */}
        <button data-testid="f1001" onClick={handleButtonClick2}>
          Increment Foo2
        </button>
        <div data-testid="f1002">field5: {formData.field5}</div>
      </div>

      <div style={style}>
        <h1>Form</h1>
        <form onSubmit={handleFormSubmit} data-testid="f2000">
          {/* ------------------------ */}
          <hr />
          <div data-testid="f2002">field1: {formData.field1}</div>
          <input
            value={formData.field1}
            onChange={handleFormChange}
            name="field1"
            type="text"
            id="field1"
            data-testid="f2001"
          />

          {/* ------------------------ */}
          <hr />
          <p>Button keyDown</p>
          <input
            value={formData.field3}
            onChange={handleFormChange}
            onKeyDown={(event) => {
              if (event.key === "a") {
                setFormData((prev) => ({
                  ...prev,
                  field3: `${prev.field3}-AAA`,
                }));
              }

              if (event.key === "Enter") {
                setFormData((prev) => ({
                  ...prev,
                  field3: `${prev.field3}-BBB`,
                }));
              }
            }}
            name="field3"
            type="text"
            id="field3"
            ref={ref3001}
            data-testid="f3001"
          />
          {/* ------------------------ */}
          <hr />

          <button type="submit">submit</button>
          <div data-testid="f2003">{isFormSubmitted ? "Y" : "N"}</div>
        </form>
      </div>

      <div style={style}>
        <p>Async Message:</p>
        <div>
          {formData.field6 ? (
            <p>{formData.field6}</p>
          ) : (
            <p data-testid="f4001">Loading...</p>
          )}
        </div>
      </div>

      <div style={style}>
        <div
          data-testid="f5001"
          href={"https://example.com"}
          className="my-class"
          style={{ backgroundColor: "red" }}
        >
          MY DIV
        </div>
      </div>
    </div>
  );
};

export default Foo;
