import OverlayTrigger from 'react-bootstrap/OverlayTrigger'
import Tooltip from 'react-bootstrap/Tooltip'
import Image from 'react-bootstrap/Image'
import Breadcrumb from 'react-bootstrap/Breadcrumb'
import { MapContainer, Marker, TileLayer } from 'react-leaflet'
import { Link, useLoaderData } from 'react-router-dom'
import { ProductCard } from './../../components/ProductCard.jsx'
import { formatDate } from '../../utils/dateUtils.js'
import { getProductById, listProductPriceHistory, listProducts } from '../../api/product.js'
import { FaArrowTrendUp, FaArrowTrendDown, FaEye } from 'react-icons/fa6'
import 'leaflet/dist/leaflet.css'

export const singleProductLoader = async (productId, params) => {
    const product = await getProductById(productId)
    const similarProductsPromise = listProducts({
        categoryIds: product.data.category.id,
        size: 5,
        excludeIds: product.data.id
    })
    const priceHistoryPromise = listProductPriceHistory(productId, params)
    return await Promise.all([ product, similarProductsPromise, priceHistoryPromise ])
}

export const SingleProduct = () => {
    const [ productData, similarProductsData, priceHistoryData ] = useLoaderData()
    const product = productData?.data
    const similarProducts = similarProductsData?.data.content
    const previousPriceHistory = priceHistoryData ? priceHistoryData.data.content[0] : null

    const renderPriceTrend = () => {
        if (product && previousPriceHistory && product.price < previousPriceHistory.price) {
            return <FaArrowTrendDown />
        } else {
            return <FaArrowTrendUp />
        }
    }

    return (
        <>
            <Breadcrumb className="d-flex justify-content-end align-items-center px-2 mb-3">
                <Breadcrumb.Item href={ '/products' }>Products</Breadcrumb.Item>
                <Breadcrumb.Item active>{ product?.name }</Breadcrumb.Item>
            </Breadcrumb>
            <div className="d-flex flex-column gap-3">
                <div className="d-flex gap-3">
                    <Image style={ { minWidth: 300 } } src={ product?.image } />
                    <div style={ { maxWidth: 600 } } className="d-flex flex-column justify-content-between">
                        <div>
                            <h1>{ product?.name }</h1>
                            <h5>Description</h5>
                            <p>{ product?.description }</p>
                        </div>
                        <div className="d-flex gap-5">
                            <h2>${ product?.price }
                                { previousPriceHistory && (
                                    <OverlayTrigger
                                        overlay={
                                            <Tooltip>
                                                Previous price <br />
                                                { `${ formatDate(previousPriceHistory.timestamp) } - $ ${ previousPriceHistory.price }` }
                                            </Tooltip>
                                        }
                                        placement="top"
                                    >
                                    <span>
                                        { renderPriceTrend() }
                                    </span>
                                    </OverlayTrigger>
                                ) }
                            </h2>
                            <h2><FaEye /> { product?.views }</h2>
                        </div>
                    </div>
                    <MapContainer style={ { width: 800, minHeight: 300 } }
                                  center={ [ ...product.gpsCoordinates.coordinates ].reverse() }
                                  zoom={ 8 }
                                  scrollWheelZoom={ false }>
                        <TileLayer
                            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                        />
                        <Marker position={ [ ...product.gpsCoordinates.coordinates ].reverse() } />
                    </MapContainer>
                </div>
                <div className="pt-3">
                    <h2 className="mb-3">Similar products</h2>
                    <div className="border w-100 mb-3" />
                    <div className="d-flex gap-3">
                        { similarProducts.map(({ id, name, image, price }) => (
                            <Link to={ `/products/${ id }` } key={ id }>
                                <ProductCard image={ image } title={ name }>
                                    $ { price }
                                </ProductCard>
                            </Link>
                        )) }
                    </div>
                </div>
            </div>
        </>
    )
}
