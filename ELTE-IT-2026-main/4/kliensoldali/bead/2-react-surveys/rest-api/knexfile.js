// Дополнительную информацию об этом файле см. на странице https://dove.feathersjs.com/guides/cli/databases.html.
import { app } from './src/app.js'

// Загрузите информацию о подключении к базе данных из конфигурации приложения.
const config = app.get('sqlite')

export default config
