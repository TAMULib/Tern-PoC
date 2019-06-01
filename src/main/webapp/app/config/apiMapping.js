// CONVENTION: must match model name, case sensitive
export const apiMapping = {
  Processor: {
    all: {
      'endpoint': '/private/queue',
      'controller': 'processor/custom',
      'httpMethod': 'GET'
    },
    listen: {
      'endpoint': '/channel',
      'controller': 'custom-processor'
    },
    create: {
      'endpoint': '/private/queue',
      'controller': 'processor/custom',
      'httpMethod': 'POST'
    },
    remove: {
      'endpoint': '/private/queue',
      'controller': 'processor/custom',
      'httpMethod': 'DELETE'
    },
    update: {
      'endpoint': '/private/queue',
      'controller': 'processor/custom',
      'httpMethod': 'PUT'
    },
    test: {
      'endpoint': '/private/queue',
      'controller': 'processor/custom/test-run'
    }
  },
  User: {
    lazy: true,
    instantiate: {
      'endpoint': '/private/queue', 
      'controller': 'user', 
      'method': 'credentials'
    },
    all: {
      'endpoint': '/private/queue',
      'controller': 'user',
      'method': 'all'
    },
    listen: {
      'endpoint': '/channel',
      'controller': 'user'
    },
    update: {
      'endpoint': '/private/queue',
      'controller': 'user',
      'method': 'update'
    }
  }
};