'use strict';

const categoryClassColor = ['bg-primary', 'bg-secondary', 'bg-success', 'bg-danger', 'bg-warning', 'bg-info',
    'bg-dark'];

const badges = Array.from(document.getElementsByClassName('badge'));

let k = 0;
generateRandomNumber(categoryClassColor.length, badges.length).forEach(num => {
    badges[k++].classList.add(categoryClassColor[num]);
});

/**
 * Generate an array of random numbers between 0 to n
 * with no same adjacent
 * @param bgColor - size of the categoryClassColor
 * @param arraySize - size of the badges array
 * @return arr - array of randomly generated number with
 * no same adjacent.
 */
function generateRandomNumber(bgColor, arraySize) {
    let arr = [];
    arr[0] = Math.floor(Math.random() * bgColor);
    for (let i = 1; i < arraySize; i++) {
        let j = Math.floor(Math.random() * bgColor);
        while (arr[i-1] === j)
            j = Math.floor(Math.random() * bgColor);
        arr[i] = j;
    }
    return arr;
}