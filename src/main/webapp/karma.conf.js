module.exports = function(config){
    config.set({

      preprocessors: {
        "app/**/*.js": "coverage",
        '**/*.html': ['ng-html2js'],
        'test-context.js': ['webpack']
      },

      reporters: ['progress', 'coverage'],

      basePath : './',

      files : [
        'dist/vendors~app.bundle.js',
        'dist/app.bundle.js',
        'node_modules/angular-mocks/angular-mocks.js',
        'test-context.js',
        'app/views/**/*.js',
        'tests/mocks/**/*.js'
      ],

      webpack: {
        devtool: 'eval-source-map',
        module: {
            rules: [
                {
                    test: /\.js/,
                    exclude: /node_modules/,
                    loader: 'babel-loader'
                }
            ]
        }
      },

      autoWatch : true,

      frameworks: ['jasmine'],

      browsers : ['Chrome', 'Firefox'],

      plugins : [
        'karma-chrome-launcher',
        'karma-coverage',
        'karma-firefox-launcher',
        'karma-jasmine',
        'karma-junit-reporter',
        'karma-ng-html2js-preprocessor',
        'karma-webpack'
      ],

      coverageReporter: {
        type: "lcov",
        dir: "coverage/"
      }

    });
};