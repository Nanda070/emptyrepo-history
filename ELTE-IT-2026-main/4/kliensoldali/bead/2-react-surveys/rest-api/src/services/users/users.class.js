import { KnexService } from '@feathersjs/knex'

// По умолчанию вызывает стандартные методы обслуживания адаптера Knex, но его можно настроить с помощью собственных функций.
export class UserService extends KnexService {}

export const getOptions = (app) => {
  return {
    paginate: app.get('paginate'),
    Model: app.get('sqliteClient'),
    name: 'users'
  }
}
