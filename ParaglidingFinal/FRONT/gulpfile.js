"use strict";

var gulp = require("gulp"),
    autoprefixer = require("gulp-autoprefixer"),
    cssbeautify = require("gulp-cssbeautify"),
    removeComments = require('gulp-strip-css-comments'),
    rename = require("gulp-rename"),
    sass = require("gulp-sass"),
    cssnano = require("gulp-cssnano"),
    include = require('gulp-include'),
    uglify = require("gulp-uglify"),
    watch = require("gulp-watch"),
    plumber = require("gulp-plumber"),
    imagemin = require("gulp-imagemin"),
    run = require("run-sequence"),
    rimraf = require("rimraf"),
    jsp = require("gulp-compile-jsp");



/* Paths to source/build/watch files
=========================*/

var path = {
    build: {
        tag: "../web/WEB-INF/tags/",
        html: "../web/WEB-INF/jsp/",
        js: "../web/js/",
        css: "../web/css/",
        img: "../web/img/",
        fonts: "../web/fonts/"
    },
    src: {
        tag: "src/tags/*.tag",
        html: "src/*.{htm,html,jsp}",
        js: "src/assets/js/*.js",
        css: "src/assets/sass/style.scss",
        img: "src/assets/i/**/*.*",
        fonts: "src/assets/fonts/**/*.*"
    },
    watch: {
        tag: "src/tags/*.tag",
        html: "src/**/*.{htm,html,jsp}",
        js: "src/assets/js/**/*.js",
        css: "src/assets/sass/**/*.scss",
        img: "src/assets/i/**/*.*",
        fonts: "src/assets/fonts/**/*.*"
    },
    clean: "./build"
};

/* Tasks
=========================*/

gulp.task("html:build", function () {
    return gulp.src(path.src.html)
        .pipe(plumber())
        .pipe(include())
        .on('error', console.log)
        .pipe(gulp.dest(path.build.html));
});

gulp.task("tag:build", function () {
    return gulp.src(path.src.tag)
        .pipe(plumber())
        .pipe(include())
        .on('error', console.log)
        .pipe(gulp.dest(path.build.tag));
});

gulp.task("css:build", function () {
    return gulp.src(path.src.css, { base: './src/assets/sass/' })
        .pipe(plumber())
        .pipe(sass().on('error', sass.logError))
        .pipe(autoprefixer({
            browsers: ["last 8 versions"],
            cascade: true
        }))
        .pipe(cssbeautify())
        .pipe(gulp.dest(path.build.css))
        .pipe(cssnano({
            zindex: false,
            discardComments: {
                removeAll: true
            }
        }))
        .pipe(removeComments())
        .pipe(rename({
            suffix: ".min",
            extname: ".css"
        }))
        .pipe(gulp.dest(path.build.css));
});


gulp.task("js:build", function () {
    gulp.src(path.src.js)
        .pipe(plumber())
        .pipe(include())
        .on('error', console.log)
        .pipe(gulp.dest(path.build.js))
        .pipe(uglify())
        .pipe(removeComments())
        .pipe(rename("main.min.js"))
        .pipe(gulp.dest(path.build.js));
});


gulp.task("fonts:build", function () {
    gulp.src(path.src.fonts)
        .pipe(gulp.dest(path.build.fonts));
});


gulp.task("image:build", function () {
    gulp.src(path.src.img)
        .pipe(imagemin({
            optimizationLevel: 3,
            progressive: true,
            svgoPlugins: [{ removeViewBox: false }],
            interlaced: true
        }))
        .pipe(gulp.dest(path.build.img));
});


gulp.task("clean", function (cb) {
    rimraf(path.clean, cb);
});


gulp.task('build', function (cb) {
    run(
        "clean",
        "css:build",
        "html:build",
        "tag:build",
        "js:build",
        "fonts:build",
        "image:build"
        , cb);
});


gulp.task("watch", function () {
    watch([path.watch.html], function (event, cb) {
        gulp.start("html:build");
    });
    watch([path.watch.tag], function (event, cb) {
        gulp.start("tag:build");
    });
    watch([path.watch.css], function (event, cb) {
        gulp.start("css:build");
    });
    watch([path.watch.js], function (event, cb) {
        gulp.start("js:build");
    });
    watch([path.watch.img], function (event, cb) {
        gulp.start("image:build");
    });
    watch([path.watch.fonts], function (event, cb) {
        gulp.start("fonts:build");
    });
});


gulp.task("default", function (cb) {
    run(
        "clean",
        "build",
        "watch"
        , cb);
});
