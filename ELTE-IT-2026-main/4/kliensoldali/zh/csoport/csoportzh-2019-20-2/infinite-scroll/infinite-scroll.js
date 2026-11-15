//1.а
document.querySelector('#btn-new-content').remove();

window.addEventListener('scroll', _.throttle(onScroll, 1000))

function onScroll() {
    if ((window.innerHeight + window.scrollY) >= document.body.scrollHeight) {
        // 1.б
        console.log('End of page');

        // 1.в, 1.д
        document.body.appendChild(document.querySelector('#newRow').content.cloneNode(true));
    }
}