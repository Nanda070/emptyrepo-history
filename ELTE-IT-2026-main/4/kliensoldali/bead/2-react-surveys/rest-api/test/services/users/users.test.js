// Дополнительную информацию об этом файле см. на странице https://dove.feathersjs.com/guides/cli/service.test.html.
import assert from 'assert'
import { app } from '../../../src/app.js'

describe('users service', () => {
  it('registered the service', () => {
    const service = app.service('users')

    assert.ok(service, 'Registered the service')
  })
})
