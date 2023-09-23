import { Outlet } from 'react-router-dom'

export const Layout = () => {
    return (
        <div className="p-4">
            <div className="pb-3 px-5 bg-gray-400">
                <h2>NEAR BY</h2>
            </div>
            <Outlet />
        </div>
    )
}
