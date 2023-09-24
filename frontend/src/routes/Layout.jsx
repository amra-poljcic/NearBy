import { Button } from 'react-bootstrap'
import { Link, Outlet } from 'react-router-dom'
import { authClient } from '../Auth.jsx'

export const Layout = () => {
    return (
        <div>
            <Header />
            <div className="p-4">
                <Outlet />
            </div>
        </div>
    )
}

export const Header = () => (
    <div className="d-flex justify-content-between py-3 px-5 bg-gray-400 bg-light">
        <h2 className="mb-0 fw-lighter">
            <Link to="/">
                NEAR BY
            </Link>
        </h2>
        <Button onClick={ () => authClient.logout() }>
            Log out
        </Button>
    </div>
)
