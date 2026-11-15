// // Дополнительную информацию об этом файле см. на https://dove.feathersjs.com/guides/cli/service.schemas.html.
import { resolve } from '@feathersjs/schema'
import { Type, getValidator, querySyntax } from '@feathersjs/typebox'
import { passwordHash } from '@feathersjs/authentication-local'
import { dataValidator, queryValidator } from '../../validators.js'

// Схема модели основных данных
export const userSchema = Type.Object(
  {
    id: Type.Number(),
    email: Type.String(),
    password: Type.Optional(Type.String()),
    fullname: Type.String()
  },
  { $id: 'User', additionalProperties: false }
)
export const userValidator = getValidator(userSchema, dataValidator)
export const userResolver = resolve({})

export const userExternalResolver = resolve({
  // Пароль никогда не должен быть виден извне
  password: async () => undefined
})

// Схема создания новых записей
export const userDataSchema = Type.Pick(userSchema, ['email', 'password', 'fullname'], {
  $id: 'UserData'
})
export const userDataValidator = getValidator(userDataSchema, dataValidator)
export const userDataResolver = resolve({
  password: passwordHash({ strategy: 'local' })
})

// Схема обновления существующих записей
export const userPatchSchema = Type.Partial(userSchema, {
  $id: 'UserPatch'
})
export const userPatchValidator = getValidator(userPatchSchema, dataValidator)
export const userPatchResolver = resolve({
  password: passwordHash({ strategy: 'local' })
})

// Схема разрешенных свойств запроса
export const userQueryProperties = Type.Pick(userSchema, ['id', 'email', 'fullname'])
export const userQuerySchema = Type.Intersect(
  [
    querySyntax(userQueryProperties),
    // Добавьте сюда дополнительные свойства запроса
    Type.Object({}, { additionalProperties: false })
  ],
  { additionalProperties: false }
)
export const userQueryValidator = getValidator(userQuerySchema, queryValidator)
export const userQueryResolver = resolve({
  // Если есть пользователь (например, с аутентификацией), ему разрешено видеть только свои собственные данные.
  id: async (value, user, context) => {
    if (context.params.user) {
      return context.params.user.id
    }

    return value
  }
})
