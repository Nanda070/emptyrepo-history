// Дополнительную информацию об этом файле см. на странице https://dove.feathersjs.com/guides/cli/databases.html.
import knex from 'knex'

export const sqlite = (app) => {
  const config = app.get('sqlite')
  const db = knex(config)

  app.set('sqliteClient', db)
}
