export const formatDate = (date) => {
    return new Date(date).toLocaleString('en-GB', {
        year: 'numeric',
        month: 'numeric',
        day: 'numeric'
    })
}
