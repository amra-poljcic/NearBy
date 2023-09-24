import axios from 'axios'
import Qs from 'qs'

export const customAxios = axios.create({
    paramsSerializer: params => Qs.stringify(params, { arrayFormat: 'repeat' }),
    headers: { Authorization: `Bearer ${ localStorage.getItem('token') }` }
})
