// Дополнительную информацию об этом файле см. на странице https://dove.feathersjs.com/guides/cli/service.html.
import { authenticate } from '@feathersjs/authentication'

import { hooks as schemaHooks } from '@feathersjs/schema'
import {
  userDataValidator,
  userPatchValidator,
  userQueryValidator,
  userResolver,
  userExternalResolver,
  userDataResolver,
  userPatchResolver,
  userQueryResolver
} from './users.schema.js'
import { UserService, getOptions } from './users.class.js'

export const userPath = 'users'
export const userMethods = ['find', 'get', 'create', 'patch', 'remove']

export * from './users.class.js'
export * from './users.schema.js'

// Функция настройки, которая регистрирует службу и ее перехватчики через app.configure.
export const user = (app) => {
  // Зарегистрируйте наш сервис в приложении Feathers
  app.use(userPath, new UserService(getOptions(app)), {
    // Список всех методов, которые эта служба предоставляет извне.
    methods: userMethods,
    // Здесь вы можете добавить дополнительные пользовательские события для отправки клиентам.
    events: []
  })
  // Инициализировать перехватчики
  app.service(userPath).hooks({
    around: {
      all: [schemaHooks.resolveExternal(userExternalResolver), schemaHooks.resolveResult(userResolver)],
      find: [authenticate('jwt')],
      get: [authenticate('jwt')],
      create: [],
      update: [authenticate('jwt')],
      patch: [authenticate('jwt')],
      remove: [authenticate('jwt')]
    },
    before: {
      all: [schemaHooks.validateQuery(userQueryValidator), schemaHooks.resolveQuery(userQueryResolver)],
      find: [],
      get: [],
      create: [schemaHooks.validateData(userDataValidator), schemaHooks.resolveData(userDataResolver)],
      patch: [schemaHooks.validateData(userPatchValidator), schemaHooks.resolveData(userPatchResolver)],
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
