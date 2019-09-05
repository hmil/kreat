const path = require('path');

module.exports = {
    entry: './build/js/packages/todomvc',
    output: {
        filename: 'main.js',
        path: path.resolve(__dirname, 'www')
    },
    devServer: {
        contentBase: './www'
    },
};
