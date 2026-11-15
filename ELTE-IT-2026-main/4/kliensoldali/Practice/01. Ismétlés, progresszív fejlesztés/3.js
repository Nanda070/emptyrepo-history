/** @see https://Developer.mozilla.org/en-US/docs/Web/API/Intersection_Observer_API */
const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            entry.target.classList.add('animate__animated');

            /** @see https://Developer.mozilla.org/en-US/docs/Learn/HTML/Howto/Use_data_attributes */
            /** @see https://Developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Template_literals */
            entry.target.classList.add(`animate__${entry.target.dataset.scrollAnimation}`);
        }
    })
}, {threshold: 0.1});
// порог 0,1 должен отображаться как 10% элемента

document.querySelectorAll('[data-scroll]').forEach(e => {
    observer.observe(e);
})