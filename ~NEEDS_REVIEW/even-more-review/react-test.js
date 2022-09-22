Test


 https://stackoverflow.com/questions/41830165/how-to-mock-react-component-methods-with-jest-and-enzyme


window.alert = jest.fn();
 ———-

Create a __mock__ folder, inside add file names for what you want to mock, and mock the functions, example:

// react-dom.js
export default {
  render: jest.fn()
};
 
// in your test:
jest.mock(‘react-dom’);

expect(render).toBeCalled();


————


jest.fn().mockReturnValue(123);

jest.fn().mockImplementation(() => 456);

// mocking nested component
jest.mock('./Foo', () => ()=> <div id="foo">hello</div>)

// doMock prevents hoisting
jest.doMock('./ComponentToMock', () => {
  const ComponentToMock = () => <div />;
  return ComponentToMock;
});


jest.mock('react-router-dom/Link', () => 'Link')


/* global describe, it, expect */


const copy = { ...fooObj, bar: 123 };

————————

/* Place at the top of your file, after imports. Don't import the module you want to mock. */
jest.mock('path to components relative to test file');
/* Give your mocked component a name to render in snapshots like this */
jest.mock('path to components relative to test file', () => 'MyComponentName');

————‘——————-

// find() accepts a react class
wrapper.find(Child).prop('onChildClick')('foo');
 // expect parent method to be called
Test Child component callback calls parent method
———————-//-


it('calls componentDidMount', () => {
    sinon.spy(Foo.prototype, 'componentDidMount');
    const wrapper = mount(<Foo />);
    expect(Foo.prototype.componentDidMount).to.have.property('callCount', 1);
  });

—————————-/-//——

