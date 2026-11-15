// Javascript-решение
document.querySelector('nav#mainNav').addEventListener('click', evt => {
    //альтернатива: evt.target.matches()
    if (evt.target instanceof HTMLAnchorElement) {
        evt.preventDefault();
        // Используя URL API, можно легко получить различные части ссылки.
        const url = new URL(evt.target.href);

        document.querySelector(url.hash).scrollIntoView({behavior: 'smooth'})
    }
})