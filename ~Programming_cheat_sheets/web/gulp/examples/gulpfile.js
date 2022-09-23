var gulp = require('gulp');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var cleanCSS = require('gulp-clean-css');

/**
 * sourcemaps makes the source files available to the browser!
 * No need to add the .map files in your index.html file, just add the minified files.
 */
var sourcemaps = require('gulp-sourcemaps');

var del = require('del');

/**
 *  Not working yet, maybe it is some issue with the livereload server,
 * Remember that you need the livereload chrome plugin.
 */
var livereload = require('gulp-livereload');
// var coffee = require('gulp-coffee');
// var imagemin = require('gulp-imagemin');
// livereload({ start: true });


var pluginRoot = './';


var paths = {
    scripts: [pluginRoot + 'js/**/*.js'],
    styles: [pluginRoot + 'css/**/*.css'],
    dist: pluginRoot + 'dist' // this folder can be deleted and it will still work! :)
};


/**
 * Not all tasks need to use streams
 * A gulpfile is just another node program and you can use any package available on npm
 */ 
gulp.task('clean', function ()
{
    console.log("\n\nTASK: clean\n\n");

    // You can use multiple globbing patterns as you would with `gulp.src`
    del([paths.dist])
        .then(function(paths){
            console.log('Deleted files and folders:\n', paths.join('\n'));
        });
});


gulp.task('scripts', function ()
{
    console.log("\n\nTASK: scripts\n\n");

    // Minify and copy all JavaScript (except vendor scripts)
    // with sourcemaps all the way down
    return gulp.src(paths.scripts)

        .pipe(sourcemaps.init())

        // .pipe(coffee())

        .pipe(concat('min.js'))

        .pipe(uglify())

        .pipe(sourcemaps.write('./'))

        .pipe(gulp.dest(paths.dist))

        // .pipe(livereload());
});


gulp.task('styles', function ()
{
    console.log("\n\nTASK: styles\n\n");

    return gulp

        // Which CSS files to minify:
        .src(paths.styles)

        // start sourcemaps
        .pipe(sourcemaps.init())

        // concat all css files into this file: 'min.css'
        .pipe(concat('min.css'))

        // if there is an error, log it.
        // .on('error', notify.onError("Error: <%= error.message %>"))

        // Some compatibility ?
        .pipe(cleanCSS({compatibility: 'ie8'}))

        // end sourcemaps
        .pipe(sourcemaps.write('./'))

        // Destination folder.
        .pipe(gulp.dest(paths.dist))

        // Tasks done!
        // .pipe(notify({
        //     title: "CSS Tasks done!",
        //     message: "at " + moment().format('h:mm:ss A'), // 'MMM Do h:mm:ss A'
        //     onLast: true // if you only want a single notification per-stream
        // }));

        // .pipe(livereload());
});


// Rerun the task when a file changes
gulp.task('watch', ['clean'], function () // The 'clean' task will run before the 'watch' task.
{
    console.log("\n\nTASK: watch\n\n");
    // livereload.listen();
    gulp.watch(paths.scripts, ['scripts']);
    gulp.watch(paths.styles, ['styles']);
});


// The default task (called when you run `gulp` from cli)
gulp.task('default', ['watch', 'scripts', 'styles']);
