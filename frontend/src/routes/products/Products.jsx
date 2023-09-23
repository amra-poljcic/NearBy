import Form from 'react-bootstrap/Form'
import Card from 'react-bootstrap/Card'
import { Button } from 'react-bootstrap'
import { useNavigate } from 'react-router-dom'

export const Products = () => {
    const navigate = useNavigate()

    return (
        <div className="d-flex gap-3">
            <div className="d-flex flex-column gap-3">
                <Button className="w-100" variant="primary">Apply filter</Button>
                <Form.Control placeholder="Search" />

                <div className="border p-3">
                    <Form.Check type="checkbox" label="Category" />
                </div>

                <div className="d-flex">
                    <Form.Control placeholder="Min price" />
                    <Form.Control placeholder="Max price" />
                </div>
            </div>
            <div className="flex-grow-1">
                <div className="d-flex justify-content-end">
                    <Form.Select className="w-25">
                        <option>Sort by</option>
                        <option value="1">Name</option>
                        <option value="2">Price</option>
                        <option value="3">Gps coordinates</option>
                        <option value="3">Views</option>
                    </Form.Select>
                </div>
                <div>
                    <Card style={ { width: '18rem' } }>
                        <Card.Img variant="top"
                                  src="https://imageio.forbes.com/specials-images/imageserve/5d35eacaf1176b0008974b54/2020-Chevrolet-Corvette-Stingray/0x0.jpg?format=jpg&crop=4560,2565,x790,y784,safe&width=1440" />
                        <Card.Body>
                            <Card.Title>Ime produkta</Card.Title>
                            <Card.Text>
                                100$
                            </Card.Text>
                            <Button onClick={ () => navigate('/products/132') } variant="primary">
                                See details
                            </Button>
                        </Card.Body>
                    </Card>
                </div>
            </div>
        </div>
    )
}
