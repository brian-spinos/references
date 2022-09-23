import {render, fireEvent, waitFor, screen} from '@testing-library/react';
import MyContextAgain from './contexts/MyContextAgain';
import FooBar from './FooBar';

describe('FooBar', () => {
    it('Should work', async () => {
        let onUnmountFn = jest.fn((n) => {});
        let component = render(<FooBar onUnmount={onUnmountFn}/>);
        let pTag = await component.findByTestId('abc'); // await find
        expect(pTag).toHaveTextContent('Hello');
    });

    it('Context should work', async () => {
        let ctx = {
            name: 'Erich',
            age: 20
        };
        let onUnmountFn = jest.fn((n) => {});
        let component = render(
            <MyContextAgain.Provider value={ctx}>
                <FooBar onUnmount={onUnmountFn}/>
            </MyContextAgain.Provider>
        );
        let pTag = component.getByTestId('context');
        expect(pTag).toHaveTextContent('name: Erich, age: 20');
    });

    it('State should work', () => {
        let onUnmountFn = jest.fn((n) => {});
        let component = render(<FooBar onUnmount={onUnmountFn}/>);
        let pTag = component.getByTestId('state-val');
        expect(pTag).toHaveTextContent('stateVal: 0');

        let btn = component.getByTestId("state-val-btn");

        fireEvent.click(btn);

        expect(pTag).toHaveTextContent('stateVal: 1');
    });

    it('effect shoud work', () => {
        let onUnmountFn = jest.fn((n) => {});
        let component = render(<FooBar onUnmount={onUnmountFn}/>);
        let pTag = component.getByTestId('effect');
        expect(pTag).toHaveTextContent('greetings: Hello from useEffect');
        component.unmount();
        expect(onUnmountFn).toHaveBeenCalledWith(123);
    })

    it('memo should work', () => {
        let onUnmountFn = jest.fn();
        let component = render(<FooBar onUnmount={onUnmountFn} />);
        let pTag = component.getByTestId('exp');
        expect(pTag).toHaveTextContent('expVal: $$$');
    });
});






//===================================================================

import {render, waitFor, fireEvent, screen} from '@testing-library/react';
import { act } from 'react-dom/test-utils';
import Parent from './Parent';

describe('Parent', () => {
    afterEach(() => {
        jest.restoreAllMocks(); // cleanup
    });

    it('normal fetch should work', async () => {
        const component = render(<Parent />);
        const pTag = component.getByTestId("user-details");

        // await, waitFor, expect
        await waitFor(() => {
          expect(pTag).toHaveTextContent("Brian 101");
        });
        //expect(screen.queryByText(/Loading/)).toBeNull(); // we don't have to await this one because we awaited the proper condition in the previous line
    });

    it('mock fetch', async () => {
        const mockResponse = {name: 'Erich', id: 999};
        jest.spyOn(global, 'fetch').mockImplementation(() => {
            return Promise.resolve({
                json: () => Promise.resolve(mockResponse)
              })
        });

        const component = render(<Parent />);
        const pTag = await component.findByTestId("user-details");
        expect(pTag).toHaveTextContent("Erich 999");
    });

    it('normal fetch should work #2', async () => {
        const component = render(<Parent />);
        const pTag = component.getByTestId("user-details");

        // `waitFor` waits until the callback doesn't throw an error
        await waitFor(() => {
            expect(pTag).toHaveTextContent("Brian 101");
        });
        //expect(screen.queryByText(/Loading/)).toBeNull(); // we don't have to await this one because we awaited the proper condition in the previous line
    });

    it('usinf ACT to test fetch (needs fetch mock)', async () => {
        let component;

        const mockResponse = {name: 'AAA', id: 888};
        jest.spyOn(global, 'fetch').mockImplementation(() => {
            return Promise.resolve({
                json: () => Promise.resolve(mockResponse)
              })
        });

        // Use the asynchronous version of act to apply resolved promises
        // await, act, async
        await act(async () => {
            component = render(<Parent />);
        });
        
        // component.debug();
        const pTag = component.getByTestId("user-details");
        expect(pTag).toHaveTextContent("AAA 888");
    });

    it('mock fetch #2', async () => {
        const mockResponse = {name: 'Erich', id: 999};
        jest.spyOn(global, 'fetch').mockImplementation(() => {
            return Promise.resolve({
                json: () => Promise.resolve(mockResponse)
              })
        });

        const component = render(<Parent />);
        const pTag = await component.findByText(/Erich 999/i);
        // const pTag = await component.findByTestId("user-details");
        expect(pTag).toHaveTextContent("Erich 999");
    });

    it('normal fetch should work #3', async () => {
        const component = render(<Parent />);

        // wait until u see the result !!!
        const pTag = await component.findByText(/Brian 101/i);
        // const pTag = await component.findByTestId("user-details");
        expect(pTag).toHaveTextContent("Brian 101");
    });

    // it.only('ONLY TEST', async () => {
    //     //...
    // });

    it('snapshot test', async () => {
        const component = render(<Parent />);

        // wait until u see the result !!!
        const pTag = await component.findByText(/Brian 101/i);
        // const pTag = await component.findByTestId("user-details");
        expect(pTag).toHaveTextContent("Brian 101");

        //expect(pTag).toMatchSnapshot(); // creates a __snapshots__ folder the first time
    });
});
