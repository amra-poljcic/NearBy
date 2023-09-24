import PropTypes from 'prop-types'
import { useEffect, useState } from 'react'
import { Auth0Client } from '@auth0/auth0-spa-js'

export const authClient = new Auth0Client({
    domain: import.meta.env.VITE_AUTH0_DOMAIN,
    clientId: import.meta.env.VITE_AUTH0_CLIENT_ID,
    authorizationParams: {
        redirect_uri: window.location.origin,
        audience: import.meta.env.VITE_AUTH0_AUDIENCE
    },
    cacheLocation: 'localstorage'
})

function Auth({ children }) {
    const [ isLoggedIn, setIsLoggedIn ] = useState(false)

    useEffect(() => {
        const authenticate = async () => {
            const isAuthenticated = await authClient.isAuthenticated()
            const query = window.location.search

            if (!isAuthenticated && query.includes('code=') && query.includes('state=')) {
                const redirectResult = await authClient.handleRedirectCallback()
                const appState = redirectResult.appState

                if (appState && appState.href) {
                    window.location.replace(appState.href)
                }
            }

            if (await authClient.isAuthenticated()) {
                const token = await authClient.getTokenSilently()
                localStorage.setItem('token', token)
                setIsLoggedIn(true)
            } else {
                authClient.loginWithRedirect({
                    redirect_uri: window.location.origin,
                    appState: {
                        href: window.location.href
                    }
                })
            }
        }

        authenticate()
    }, [])

    if (!isLoggedIn) {
        return <div>Logging in...</div>
    }

    return children
}

Auth.propTypes = {
    children: PropTypes.any
}

export default Auth
