import haversineDistance from 'haversine-distance'
import Form from 'react-bootstrap/Form'
import { Pagination } from 'react-bootstrap'
import { Link, useLoaderData, useSearchParams } from 'react-router-dom'
import { ProductCard } from './../../components/ProductCard.jsx'
import { roundDecimalNumber } from '../../utils/common.js'
import { listProducts } from '../../api/product.js'
import { listCategories } from '../../api/category.js'
import { FaArrowsLeftRight } from 'react-icons/fa6'

export const productsLoader = async (productParams) => {
    const categoriesPromise = listCategories()
    const productsPromise = listProducts(productParams)
    return await Promise.all([ categoriesPromise, productsPromise ])
}

export const Products = () => {
    const [ categories, products ] = useLoaderData()
    const [ searchParams, setSearchParams ] = useSearchParams()

    const onPageChange = (page) => {
        searchParams.set('page', page)
        setSearchParams(searchParams)
    }

    const onSortChange = (sort) => {
        searchParams.delete('sort')
        searchParams.delete('lat')
        searchParams.delete('lon')

        if (sort === 'default') {
            setSearchParams(searchParams)
        } else if (sort === 'distance') {
            navigator.geolocation.getCurrentPosition((position) => {
                searchParams.set('lat', position.coords.latitude.toString())
                searchParams.set('lon', position.coords.longitude.toString())
                setSearchParams(searchParams)
            }, (er) => console.error(er))
        } else {
            searchParams.set('sort', sort)
            setSearchParams(searchParams)
        }
    }

    const sortValue = () => {
        const sort = searchParams.get('sort')

        if (sort) {
            return sort
        }

        if (searchParams.get('lat') && searchParams.get('lon')) {
            return 'distance'
        }
    }

    return (
        <div className="d-flex gap-3">
            <ProductsFilter categories={ categories } searchParams={ searchParams } onPageChange={ onPageChange } />
            <div className="flex-grow-1">
                <div className="d-flex justify-content-end mb-3">
                    <Form.Select value={ sortValue() }
                                 onChange={ (e) => onSortChange(e.target.value) }
                                 className="w-25">
                        <option value="default">Sort by</option>
                        { navigator.geolocation && <option value="distance">Gps coordinates</option> }
                        <option value="name">Name (A to Z)</option>
                        <option value="name,desc">Name (Z to A)</option>
                        <option value="price">Price (low to high)</option>
                        <option value="price,desc">Price (high to low)</option>
                        <option value="views">Views (low to high)</option>
                        <option value="views,desc">Views (high to low)</option>
                    </Form.Select>
                </div>
                <ProductsGrid products={ products } searchParams={ searchParams } onPageChange={ onPageChange } />
            </div>
        </div>
    )
}

const ProductsFilter = ({ categories, searchParams, onPageChange }) => {
    const onInputChange = (name, value) => {
        if (!value) {
            searchParams.delete(name)
        } else {
            searchParams.set(name, value)
        }
        onPageChange(1)
    }

    const onCategoryChange = (id, checked) => {
        if (checked) {
            searchParams.append('categoryIds', id)
        } else {
            const filteredCategoryIds = searchParams.getAll('categoryIds').filter((categoryId) => id !== categoryId)
            searchParams.delete('categoryIds')
            for (const categoryId of filteredCategoryIds) {
                searchParams.append('categoryIds', categoryId)
            }
        }
        onPageChange(1)
    }

    return <div className="d-flex flex-column gap-3" style={ { minWidth: 300, maxWidth: 300 } }>
        <Form.Control value={ searchParams.get('name') }
                      onChange={ (e) => onInputChange('name', e.target.value) }
                      placeholder="Search" />

        <div className="border py-3">
            <h5 className="px-3 fw-light">PRODUCT CATEGORIES</h5>
            <div className="border-bottom w-100 mt-3 mb-3 " />
            { categories?.data.content.map(({ id, name }) => (
                <Form.Check key={ id }
                            className="text-start fs-5 mx-3"
                            reverse
                            type="checkbox"
                            label={ name }
                            checked={ searchParams.getAll('categoryIds').includes(id) }
                            onChange={ (e) => onCategoryChange(id, e.target.checked) } />
            )) }
        </div>

        <div className="d-flex align-items-center">
            <Form.Control value={ searchParams.get('minPrice') }
                          onChange={ (e) => onInputChange('minPrice', e.target.value) }
                          placeholder="Min price"
                          type="number" />
            <span className="mx-2 fs-4">-</span>
            <Form.Control value={ searchParams.get('maxPrice') }
                          onChange={ (e) => onInputChange('maxPrice', e.target.value) }
                          placeholder="Max price"
                          type="number" />
        </div>
    </div>
}

const ProductsGrid = ({ products, searchParams, onPageChange }) => {
    const page = Number(products?.data.number + 1)

    const renderProducts = () => {
        if (products?.data.content.length === 0) {
            return <h4 className="fw-lighter">No products that match filters</h4>
        }

        return products?.data.content.map(({ id, name, price, image, gpsCoordinates }) => (
            <Link to={ `/products/${ id }` } key={ id }>
                <ProductCard image={ image } title={ name }>
                    <div className="d-flex justify-content-between">
                        <div> $ { price } </div>
                        <div>
                            { searchParams.get('lat') && searchParams.get('lon') && (
                                <>
                                    <FaArrowsLeftRight />
                                    &nbsp;
                                    {
                                        roundDecimalNumber(haversineDistance({
                                                lat: searchParams.get('lat'),
                                                lon: searchParams.get('lon')
                                            }, {
                                                lon: gpsCoordinates.coordinates[0],
                                                lat: gpsCoordinates.coordinates[1]
                                            }
                                        ) / 1000, 1)
                                    }
                                    km
                                </>
                            ) }
                        </div>
                    </div>
                </ProductCard>
            </Link>
        ))
    }

    return (
        <>
            <div className="d-flex justify-content-start flex-wrap gap-3">
                { renderProducts() }
            </div>
            { products?.data.totalPages > 1 && (
                <Pagination className="justify-content-center pt-3">
                    <Pagination.First onClick={ () => onPageChange(1) } disabled={ products?.data.first } />
                    <Pagination.Prev onClick={ () => onPageChange(page - 1) } disabled={ products?.data.first } />
                    <Pagination.Item active>{ page }</Pagination.Item>
                    <Pagination.Next onClick={ () => onPageChange(page + 1) } disabled={ products?.data.last } />
                    <Pagination.Last onClick={ () => onPageChange(products?.data.totalPages) } disabled={ products?.data.last } />
                </Pagination>
            ) }
        </>
    )
}
