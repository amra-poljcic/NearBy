import { createBrowserRouter, redirect, RouterProvider } from 'react-router-dom'
import ErrorPage from './routes/ErrorPage.jsx'
import { Products } from './routes/products/Products.jsx'
import { Layout } from './routes/Layout.jsx'
import { SingleProduct } from './routes/products/SingleProduct.jsx'

const router = createBrowserRouter([
    {
        element: <Layout />,
        errorElement: <ErrorPage />,
        children: [
            {
                path: '',
                loader: () => redirect('/products')
            },
            {
                path: 'products',
                element: <Products />
            },
            {
                path: 'products/:id',
                element: <SingleProduct />
            }
        ]
    }
])


function App() {
    return (
        <RouterProvider router={ router } />
    )
}

export default App
