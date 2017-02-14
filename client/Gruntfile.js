'use strict';
module.exports = function(grunt) {
  require('load-grunt-tasks')(grunt);

  var globalConfig = {
    distPath: './home',
    cssSrcPath: 'source/less',
    jsSrcPath: 'source/coffee',
    jadeSrcPath: 'source/jade',
    cssDistPath: '<%= globalConfig.distPath %>/css/',
    jsDebugPath: '<%= globalConfig.distPath %>/js/debug',
    jsDistPath: '<%= globalConfig.distPath %>/js/',
    htmlDistPath: '<%= globalConfig.distPath %>',
    viewName: 'hhw'
  };

  grunt.initConfig({
    globalConfig: globalConfig,
    watch: {
      less: {
        files: ['<%= globalConfig.cssSrcPath %>/*.less'],
        tasks: ['less', 'autoprefixer', 'cssmin']
      },
      js: {
        files: ['<%= globalConfig.jsSrcPath %>/*.coffee'],
        tasks: ['coffee']
          // tasks: ['coffee', 'uglify']
      },
      jade: {
        files: ['<%= globalConfig.jadeSrcPath %>/*.jade'],
        tasks: ['jade4php']
      }
    },

    cssmin: {
      target: {
        files: {
          '<%= globalConfig.cssDistPath %>/<%= globalConfig.viewName %>.css': '<%= globalConfig.cssDistPath %>/<%= globalConfig.viewName %>.css'
        }
      }
    },

    less: {
      target: {
        files: {
          '<%= globalConfig.cssDistPath %>/<%= globalConfig.viewName %>.css': '<%= globalConfig.cssSrcPath %>/**/*.less'
        }
      }
    },

    autoprefixer: {
      dist: {
        files: {
          '<%= globalConfig.cssDistPath %>/<%= globalConfig.viewName %>.css': '<%= globalConfig.cssDistPath %>/<%= globalConfig.viewName %>.css'
        }
      }
    },

    coffee: {
      options: {
        sourceMap: false,
        bare: true
      },
      compile: {
        files: {
          '<%= globalConfig.jsDistPath %>/<%= globalConfig.viewName %>.js': [
            '<%= globalConfig.jsSrcPath %>/**/*.coffee'
          ]
        }
      }
    },

    uglify: {
      builds: {
        options: {
          preserveComments: false
        },
        files: {
          '<%= globalConfig.jsDistPath %>/<%= globalConfig.viewName %>.js': [
            '<%= globalConfig.jsDistPath %>/<%= globalConfig.viewName %>.js'
          ]
        }
      }

    },

    jade4php: {
      compile: {
        options: {
          pretty: true,
          basedir: __dirname
        },
        files: [{
          expand: true,
          cwd: '<%= globalConfig.jadeSrcPath %>/',
          src: ['*.jade'],
          dest: '<%= globalConfig.htmlDistPath %>/',
          ext: '.html'
        }]
      }
    }
  });

  // Register tasks
  grunt.registerTask('default', [
    'less',
    'autoprefixer',
    'cssmin',
    'coffee',
    'uglify',
    'jade4php'
  ]);
  grunt.registerTask('dev', [
    'watch'
  ]);
  grunt.registerTask('css', [
    'less',
    'autoprefixer',
    'cssmin'
  ]);
};