import { customAxios } from './axios.js'

const BASE_URL = `${ import.meta.env.VITE_BACKEND_URL }/api/v1/product`

export const listProducts = (params) => {
    return customAxios.get(BASE_URL, { params })
}

export const getProductById = (id) => {
    return customAxios.get(`${ BASE_URL }/${ id }`)
}

export const listProductPriceHistory = (id, params) => {
    return customAxios.get(`${ BASE_URL }/${ id }/price-history`, { params })
}
