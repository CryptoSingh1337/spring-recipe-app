'use strict';

const categoryClassColor = ['bg-primary', 'bg-secondary', 'bg-success', 'bg-danger', 'bg-warning', 'bg-info',
    'bg-dark'];

let j = 0;
const size = categoryClassColor.length;
Array.from(document.getElementsByClassName('badge')).forEach(badge => {
    j %= size;
    badge.classList.add(categoryClassColor[j++]);
});