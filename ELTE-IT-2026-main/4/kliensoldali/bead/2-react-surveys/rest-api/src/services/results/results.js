// Дополнительную информацию об этом файле см. на странице https://dove.feathersjs.com/guides/cli/service.html.
import { authenticate } from '@feathersjs/authentication'

import { hooks as schemaHooks } from '@feathersjs/schema'
import {
  resultDataValidator,
  resultPatchValidator,
  resultQueryValidator,
  resultResolver,
  resultExternalResolver,
  resultDataResolver,
  resultPatchResolver,
  resultQueryResolver
} from './results.schema.js'
import { ResultService, getOptions } from './results.class.js'

export const resultPath = 'results'
export const resultMethods = ['find', 'get', 'create', 'patch', 'remove']

export * from './results.class.js'
export * from './results.schema.js'

// Функция настройки, которая регистрирует службу и ее перехватчики через app.configure.
export const result = (app) => {
  // Зарегистрируйте наш сервис в приложении Feathers
  app.use(resultPath, new ResultService(getOptions(app)), {
    // Список всех методов, которые эта служба предоставляет извне.
    methods: resultMethods,
    // Здесь вы можете добавить дополнительные пользовательские события для отправки клиентам.
    events: []
  })
  // Инициализировать перехватчики
  app.service(resultPath).hooks({
    around: {
      all: [schemaHooks.resolveExternal(resultExternalResolver), schemaHooks.resolveResult(resultResolver)]
    },
    before: {
      all: [schemaHooks.validateQuery(resultQueryValidator), schemaHooks.resolveQuery(resultQueryResolver)],
      find: [authenticate('jwt')],
      get: [authenticate('jwt')],
      create: [schemaHooks.validateData(resultDataValidator), schemaHooks.resolveData(resultDataResolver)],
      patch: [schemaHooks.validateData(resultPatchValidator), schemaHooks.resolveData(resultPatchResolver)],
      remove: []
    },
    after: {
      all: []
    },
    error: {
      all: []
    }
  })
}
