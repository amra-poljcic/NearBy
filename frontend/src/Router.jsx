import { createBrowserRouter, redirect, RouterProvider } from 'react-router-dom'
import ErrorPage from './routes/ErrorPage.jsx'
import { Layout } from './routes/Layout.jsx'
import { Products, productsLoader } from './routes/products/Products.jsx'
import { SingleProduct, singleProductLoader } from './routes/products/SingleProduct.jsx'

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
                element: <Products />,
                loader: ({ request }) => {
                    const urlParams = new URL(request.url).searchParams
                    const params = {
                        page: urlParams.get('page'),
                        size: urlParams.get('size'),
                        name: urlParams.get('name'),
                        categoryIds: urlParams.getAll('categoryIds'),
                        minPrice: urlParams.get('minPrice'),
                        maxPrice: urlParams.get('maxPrice'),
                        sort: urlParams.get('sort'),
                        lat: urlParams.get('lat'),
                        lon: urlParams.get('lon')
                    }
                    return productsLoader(params)
                }
            },
            {
                path: 'products/:id',
                element: <SingleProduct />,
                loader: ({ params: pathParams }) => {
                    const params = {
                        size: 1,
                        sort: 'timestamp,desc'
                    }
                    return singleProductLoader(pathParams.id, params)
                }
            }
        ]
    }
])

function Router() {
    return <RouterProvider router={ router } />
}

export default Router
