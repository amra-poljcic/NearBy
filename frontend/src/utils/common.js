export function roundDecimalNumber(number, maximumFractionDigits = 2) {
    const divider = Math.pow(10, maximumFractionDigits)
    return Math.round(number * divider) / divider
}
