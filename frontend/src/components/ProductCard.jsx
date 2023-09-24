import PropTypes from 'prop-types'
import Card from 'react-bootstrap/Card'

export const ProductCard = ({ image, title, children }) => (
    <Card style={ { width: 200 } }>
        <Card.Img style={ { objectFit: 'cover' } } variant="top" src={ image } height={ 200 } />
        <Card.Body>
            <Card.Title>{ title }</Card.Title>
            <Card.Text>
                { children }
            </Card.Text>
        </Card.Body>
    </Card>
)

ProductCard.propTypes = {
    image: PropTypes.string,
    title: PropTypes.string,
    children: PropTypes.any
}
