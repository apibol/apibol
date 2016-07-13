'use strict';

const brfs = require('brfs');
const gulp = require('gulp');
const babelify = require('babelify');
const browserify = require('browserify');
const ngAnnotate = require('gulp-ng-annotate');
const source = require('vinyl-source-stream');
const minifyHtml = require('gulp-minify-html');
const inject = require('gulp-inject');

gulp.task('compile-js', ['optimize-views'], function () {
    return browserify({debug: true})
        .transform(babelify.configure({sourceMaps: true, presets: ['es2015']}))
        .transform(brfs)
        .require(require('./package.json').main, {entry: true})
        .bundle()
        .pipe(source('app.js'))
        .pipe(ngAnnotate({add: true}))
        .pipe(gulp.dest('./dist'));
});

gulp.task('optimize-views', function () {
    return gulp.src('./src/app/**/*.html', {base: './src'})
        .pipe(minifyHtml({empty: true}))
        .pipe(gulp.dest('./src/app/configurations'));
});

gulp.task('build', ['compile-js'], function () {
    const libs = [
        './dist/app.js'
    ];
    return gulp.src('./src/index.html', {base: './src'})
        .pipe(inject(gulp.src(libs, {read: false}), {ignorePath: 'dist/'}))
        .pipe(minifyHtml({empty: true}))
        .pipe(gulp.dest('./dist'));
});

gulp.task('default', function () {
});
