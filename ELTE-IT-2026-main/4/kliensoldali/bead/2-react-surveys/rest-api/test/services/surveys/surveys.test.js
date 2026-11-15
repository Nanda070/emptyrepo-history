// Дополнительную информацию об этом файле см. на странице https://dove.feathersjs.com/guides/cli/service.test.html.
import assert from 'assert'
import { app } from '../../../src/app.js'

describe('surveys service', () => {
  it('registered the service', () => {
    const service = app.service('surveys')

    assert.ok(service, 'Registered the service')
  })
})
