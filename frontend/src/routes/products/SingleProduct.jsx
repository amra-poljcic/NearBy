import Image from 'react-bootstrap/Image'
import Table from 'react-bootstrap/Table'
import Breadcrumb from 'react-bootstrap/Breadcrumb'

export const SingleProduct = () => {
    return (
        <>
            <Breadcrumb className="d-flex justify-content-end align-items-center py-3 px-2 mb-3 bg-light">
                <Breadcrumb.Item href="/products">Products</Breadcrumb.Item>
                <Breadcrumb.Item active>Single product</Breadcrumb.Item>
            </Breadcrumb>
            <div className="d-flex flex-column gap-3">
                <div className="d-flex gap-3">
                    <Image style={ { width: '30%' } }
                           src="https://imageio.forbes.com/specials-images/imageserve/5d35eacaf1176b0008974b54/2020-Chevrolet-Corvette-Stingray/0x0.jpg?format=jpg&crop=4560,2565,x790,y784,safe&width=1440" />

                    <div className="d-flex flex-column justify-content-between">
                        <div>
                            <h1>Ime produkta</h1>
                            <h4>100$</h4>
                            <h4>Views: 50</h4>
                        </div>
                        <div>
                            <h5>Description</h5>

                            <p>Lorem ipsum Lorem ipsumLorem ipsumLorem ipsumLorem ipsumLorem ipsumLorem ipsum Lorem
                                ipsum
                                Lorem ipsumLorem ipsumLorem ipsumLorem ipsumLorem ipsumLorem ipsum</p>
                        </div>
                    </div>
                </div>
                <div>
                    <h2 className="mb-3">Price history</h2>
                    <div className="border w-100" />
                    <Table striped className="w-50">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Price</th>
                            <th>Date of change</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>20$</td>
                            <td>12.12.2000</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>10$</td>
                            <td>12.12.2001</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>5$</td>
                            <td>12.12.2003</td>
                        </tr>
                        </tbody>
                    </Table>
                </div>
            </div>
        </>
    )
}
