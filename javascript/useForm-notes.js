

// useForm

// https://react-hook-form.com/

// https://react-hook-form.com/get-started

// npm install react-hook-form


// YUP
// npm install @hookform/resolvers yup
// import { yupResolver } from "@hookform/resolvers/yup"
// import * as yup from "yup"

import { useForm, Controller, SubmitHandler } from "react-hook-form"



// component


interface IFormInput {
    firstName: string
    lastName: string
}

// YUP SCHEMA
const schema = yup
  .object({
    firstName: yup.string().required(),
    age: yup.number().positive().integer().required(),
  })
  .required()


const { 
    register,  // register form fields
    handleSubmit,  // submit the form
    watch,  // watch(), watch("email") - watches for changes on the form/field -- console.log(watch("email"))
    control, // to be user with `Controller`
    setValue, // setValue("firstName", "brian") // can be used in a button click handler to set values to form fields
    formState: { 
        errors  // errors object  : {[fieldName]: {type, message}}
    } 
} = useForm({ // you could log the value returned from useForm() and see the available functions there
    defaultValues: {
        name: "John Doe",
        email: "john.doe@example.com",
        age: 30
    },
    resolver: yupResolver(schema), // YUP
});

console.log(errors);
  
const onSubmit = data => console.log(data);



  <form onSubmit={handleSubmit(onSubmit)}>

        <label htmlFor="firstName">First Name</label>
        <input
          id="firstName"
          {...register('firstName', { 
            required: 'First name is required', // string | boolean
            min:10, // for numbers
            max:20, // for numbers
            maxLength: 80, 
            minLength: 6,  // or  {value: 6, message: 'should be six'}
            pattern: /^\S+@\S+$/i })}
        />
        {errors.firstName && <p>{errors.firstName.message}</p>}

        {/* --- */}

        <Controller
            name="firstName"
            control={control}
            render={({ field }) => <Input {...field} />}
        />

  </form>
