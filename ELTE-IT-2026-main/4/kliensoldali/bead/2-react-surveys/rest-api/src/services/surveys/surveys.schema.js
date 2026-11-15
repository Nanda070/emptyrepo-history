// // Дополнительную информацию об этом файле см. на https://dove.feathersjs.com/guides/cli/service.schemas.html.
import { resolve, virtual } from '@feathersjs/schema'
import { Type, getValidator, querySyntax } from '@feathersjs/typebox'
import { dataValidator, queryValidator } from '../../validators.js'
import { userSchema } from '../users/users.schema.js'
import { v4 as uuidv4 } from 'uuid'

// Схема модели основных данных
export const surveySchema = Type.Object(
  {
    id: Type.Number(),
    name: Type.String(),
    content: Type.String(),
    hash: Type.String(),
    createdAt: Type.Number(),
    userId: Type.Number(),
    user: Type.Ref(userSchema)
  },
  { $id: 'Survey', additionalProperties: false }
)
export const surveyValidator = getValidator(surveySchema, dataValidator)
export const surveyResolver = resolve({
  user: virtual(async (survey, context) => {
    // Свяжите пользователя, отправившего сообщение
    return context.app.service('users').get(survey.userId)
  })
})

export const surveyExternalResolver = resolve({})

// Схема создания новых записей
export const surveyDataSchema = Type.Pick(surveySchema, ['name', 'content'], {
  $id: 'SurveyData'
})
export const surveyDataValidator = getValidator(surveyDataSchema, dataValidator)
export const surveyDataResolver = resolve({
  hash: async () => {
    return uuidv4()
  },
  userId: async (_value, _message, context) => {
    // Свяжите запись с идентификатором аутентифицированного пользователя.
    return context.params.user.id
  },
  createdAt: async () => {
    return Date.now()
  }
})

// Схема обновления существующих записей
export const surveyPatchSchema = Type.Partial(surveySchema, {
  $id: 'SurveyPatch'
})
export const surveyPatchValidator = getValidator(surveyPatchSchema, dataValidator)
export const surveyPatchResolver = resolve({})

// Схема разрешенных свойств запроса
export const surveyQueryProperties = Type.Pick(surveySchema, [
  'id',
  'name',
  'content',
  'hash',
  'createdAt',
  'userId'
])
export const surveyQuerySchema = Type.Intersect(
  [
    querySyntax(surveyQueryProperties),
    // Добавьте сюда дополнительные свойства запроса
    Type.Object({}, { additionalProperties: false })
  ],
  { additionalProperties: false }
)
export const surveyQueryValidator = getValidator(surveyQuerySchema, queryValidator)
export const surveyQueryResolver = resolve({
  userId: async (value, user, context) => {
    // Мы хотим иметь возможность найти все опросы, но
    // в противном случае разрешайте пользователю изменять свои опросы только
    if (context.params.user && context.method !== 'find') {
      return context.params.user.id
    }

    return value
  }
})
