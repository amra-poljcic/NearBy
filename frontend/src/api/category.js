import { customAxios } from './axios.js'

const BASE_URL = `${ import.meta.env.VITE_BACKEND_URL }/api/v1/category`

export const listCategories = (params) => {
    return customAxios.get(BASE_URL, params)
}
