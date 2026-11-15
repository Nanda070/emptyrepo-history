// Дополнительную информацию об этом файле см. на странице https://dove.feathersjs.com/guides/cli/logging.html.
import { createLogger, format, transports } from 'winston'

// Настройте регистратор Winston. Полную документацию см. https://github.com/winstonjs/winston.
export const logger = createLogger({
  // Чтобы просмотреть более подробные ошибки, измените это значение на «отладка».
  level: 'info',
  format: format.combine(format.splat(), format.simple()),
  transports: [new transports.Console()]
})
