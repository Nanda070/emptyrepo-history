const navBar = document.querySelector('nav#mainNav');

const navObserver = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
        const link = document.querySelector(`a[href="#${entry.target.id}"]`);

        // переключатель может быть указан как необязательный параметр с логическим значением
        // если true, переключатель может только давать, если false, то только отнимать
        // здесь это нехорошо, потому что может случиться так, что мы будем без необходимости выбирать элементы из документа
        link.classList.toggle('active', entry.isIntersecting);
    })
}, {threshold: 0.8})

document.querySelectorAll('nav#mainNav a').forEach(e => {
    const url = new URL(e.href);
    console.log(document.querySelector(url.hash));
    navObserver.observe(document.querySelector(url.hash))
})