/** Mivel az <a> elem funkcionalitását terjesztjük ki ezért az osztálynak abból kell öröklődnie. */
class ConfirmLink extends HTMLAnchorElement {
    constructor() {
        super();
    }

    // Вызывается при подключении к DOM
    connectedCallback() {
        this.addEventListener('click', this.onClick);
    }

    // Вызывается при удалении из DOM
    disconnectedCallback() {
        this.removeEventListener('click', this.onClick);
    }

    onClick = e => {
        if (!confirm('Biztos meg akarod nyitni a hivatkozást?')) {
            e.preventDefault();
        }
    };
}

// здесь также необходимо указать, что расширяет пользовательский элемент
customElements.define('confirm-link', ConfirmLink, {extends: 'a'});

const a = document.createElement('a', {is: 'confirm-link'});
a.innerHTML = 'elte';
a.href = 'https://www.elte.hu';
document.body.append(a);