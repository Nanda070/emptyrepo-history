// Дополнительную информацию об этом файле см. на странице https://dove.feathersjs.com/guides/cli/service.html.
import { authenticate } from '@feathersjs/authentication'

import { hooks as schemaHooks } from '@feathersjs/schema'
import {
  surveyDataValidator,
  surveyPatchValidator,
  surveyQueryValidator,
  surveyResolver,
  surveyExternalResolver,
  surveyDataResolver,
  surveyPatchResolver,
  surveyQueryResolver
} from './surveys.schema.js'
import { SurveyService, getOptions } from './surveys.class.js'
import { checkQuery } from '../../hooks/check-query.js'

export const surveyPath = 'surveys'
export const surveyMethods = ['find', 'get', 'create', 'patch', 'remove']

export * from './surveys.class.js'
export * from './surveys.schema.js'

// Функция настройки, которая регистрирует службу и ее перехватчики через app.configure.
export const survey = (app) => {
  // Зарегистрируйте наш сервис в приложении Feathers
  app.use(surveyPath, new SurveyService(getOptions(app)), {
    // Список всех методов, которые эта служба предоставляет извне.
    methods: surveyMethods,
    // Здесь вы можете добавить дополнительные пользовательские события для отправки клиентам.
    events: []
  })
  // Инициализировать перехватчики
  app.service(surveyPath).hooks({
    around: {
      all: [
        authenticate('jwt'),
        schemaHooks.resolveExternal(surveyExternalResolver),
        schemaHooks.resolveResult(surveyResolver)
      ]
    },
    before: {
      all: [schemaHooks.validateQuery(surveyQueryValidator), schemaHooks.resolveQuery(surveyQueryResolver)],
      find: [checkQuery],
      get: [checkQuery],
      create: [schemaHooks.validateData(surveyDataValidator), schemaHooks.resolveData(surveyDataResolver)],
      patch: [schemaHooks.validateData(surveyPatchValidator), schemaHooks.resolveData(surveyPatchResolver)],
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
