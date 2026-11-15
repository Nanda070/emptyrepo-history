// Дополнительную информацию об этом файле см. на странице https://dove.feathersjs.com/guides/cli/application.html.
import { feathers } from '@feathersjs/feathers'
import configuration from '@feathersjs/configuration'
import { koa, rest, bodyParser, errorHandler, parseAuthentication, cors, serveStatic } from '@feathersjs/koa'

import { configurationValidator } from './configuration.js'
import { logError } from './hooks/log-error.js'
import { sqlite } from './sqlite.js'

import { authentication } from './authentication.js'

import { services } from './services/index.js'

const app = koa(feathers())

// Загрузите конфигурацию нашего приложения (см. папку config/)
app.configure(configuration(configurationValidator))

// Настройка промежуточного программного обеспечения Koa
app.use(cors())
app.use(serveStatic(app.get('public')))
app.use(errorHandler())
app.use(parseAuthentication())
app.use(bodyParser())

// Настройка сервисов и транспорта
app.configure(rest())

app.configure(sqlite)

app.configure(authentication)

app.configure(services)

// Зарегистрируйте перехватчики, которые работают во всех методах службы.
app.hooks({
  around: {
    all: [logError]
  },
  before: {},
  after: {},
  error: {}
})
// Зарегистрируйте хуки для настройки и демонтажа приложения здесь.
app.hooks({
  setup: [],
  teardown: []
})

export { app }
