//============================================ react hooks


https://reactjs.org/docs/hooks-overview.html

https://reactjs.org/docs/hooks-reference.html <---

//============================================

// CoSE MRCaRLID
useContext()
useState()
useEffect()
useMemo()
useReducer()
useCallback()
useRef()
useLayoutEffect()
useImperativeLayout()
useDebugValue()

//============================================


let CTX = createContext(defaultVal)
let x = useContext(CTX)
// <CTX.Provicer value={obj}>
// </CTX.Provicer>

//============================================

let [foo, setFoo] = useState(0)
// setFoo(foo + 1)
// setFoo(prev => !prev)

//============================================

// fires after layout and paint
useEffect(fn_ret, [dep]) // runs again if change
// empty array: fires only once

//============================================

let x = useMemo(fnRetVal, [deps])

//============================================

let [foo, dispatch] = useReducer(red, initS) // 3rd arg?
// red: (state, action) => newState


//============================================


let fn = useCallback(fn, [deps])

//============================================

// val persists
let x = useRef(123) // null for DOM
x.current.click()


//============================================

useLayoutEffect(fnRet, [deps])


//============================================


useDebugValue() // myHook: value



//============================================


useImperativeHandle(ref, fn, [deps])
// 1. forwardRef
// 2. (props, ref)
// 3. inner ref
// 4. hook uses param ref
// 5. fnRet obj
let FancyInput = (props, ref) => {
  const innerRef = useRef()

  useImperativeHandle(ref, () => ({
    focus: () => {
      innerRef.current.focus()
    }
  }))

  return <input ref={innerRef} ... />
}

FancyInput = React.forwardRef(FancyInput)



//============================================
