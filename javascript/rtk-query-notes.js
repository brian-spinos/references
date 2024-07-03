

// RTK-query 

// npm install @reduxjs/toolkit react-redux


// src/services/api.js
import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react';

export const api = createApi({
  reducerPath: 'api',
  baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:3001/api' }),
  endpoints: (builder) => ({
    // GET
    getPosts: builder.query({
        query: () => '/posts',
    }),
    // GET
    getPostById: builder.query({
        query: (id) => `/posts/${id}`,
    }),
    // POST
    createPost: builder.mutation({
        query: (newPost) => ({
          url: '/posts',
          method: 'POST',
          body: newPost,
        }),
    }),
    // PATCH
    updatePost: builder.mutation({
      query: ({ id, ...patch }) => ({
        url: `/posts/${id}`,
        method: 'PATCH',
        body: patch,
      }),
    }),
    // PUT
    putPost: builder.mutation({
      query: ({ id, ...post }) => ({
        url: `posts/${id}`,
        method: 'PUT',
        body: post,
      }),
    }),
    // DELETE
    deletePost: builder.mutation({
      query: (id) => ({
        url: `/posts/${id}`,
        method: 'DELETE',
      }),
    }),
  }),
});



export const {
    useGetPostsQuery, // useLazyGetPostsQuery
    useLazyGetPostByIdQuery, // or useGetPostByIdQuery
    useGetPostByIdQuery, // or useLazyGetPostByIdQuery
    useCreatePostMutation,
    useUpdatePostMutation,
    usePutPostMutation,
    useDeletePostMutation,
  } = api;


//======================================================= component  (GET)


  const { data: posts, error, isLoading } = useGetPostsQuery();

  if (isLoading) return <div>Loading...</div>;
  if (error) return <div>Error occurred: {error.message}</div>;


  <p>{JSON.stringify(posts)}</p>


//======================================================= component  (GET) LAZY


  const [trigger, { data, error, isLoading, isSuccess, isError }] = useLazyGetPostByIdQuery();

  // on click
  trigger(postId);

// diplay data
  <p>{data && JSON.stringify(data)}</p>


  {isLoading && <p>Loading...</p>}
{isSuccess && <p>Post created successfully!</p>}
{isError && <p>Error creating post: {error.message}</p>}

//======================================================= component  (create)


const [createPost, { isLoading, isSuccess, isError, error }] = useCreatePostMutation();



// on click 
await createPost({ title });


{isLoading && <p>Loading...</p>}
{isSuccess && <p>Post created successfully!</p>}
{isError && <p>Error creating post: {error.message}</p>}


//======================================================= update

const [updatePost, { isLoading, isSuccess, isError, error }] = useUpdatePostMutation();
 

// on click
await updatePost({ id: post.id, title });


{isLoading && <p>Loading...</p>}
{isSuccess && <p>Post updated successfully!</p>}
{isError && <p>Error updating post: {error.message}</p>}

//======================================================= delete 

const [deletePost, { isLoading, isSuccess, isError, error }] = useDeletePostMutation();

// on click
await deletePost(id);

{isLoading && <p>Loading...</p>}
{isSuccess && <p>Post deleted successfully!</p>}
{isError && <p>Error deleting post: {error.message}</p>}

//======================================================= put

const [putPost, { isLoading, isSuccess, isError, error }] = usePutPostMutation();

// on click
  await putPost({ id: post.id, title });


  {isLoading && <p>Loading...</p>}
  {isSuccess && <p>Post updated successfully!</p>}
  {isError && <p>Error updating post: {error.message}</p>}


//=======================================================
