const path = require('path');
const HtmlWebpackPlugin = require("html-webpack-plugin")

module.exports = {
  mode: "development",
  entry: './js/index.js',
  output: {
    filename: 'main.js',
    path: path.resolve(__dirname, './js/dist'),
  },
  module: {
    rules: [
      {
        test: /\.css$/,
        use: ["style-loader", "css-loader"],
      },
      {
        test: /\.png$/,
        use: ["file-loader"],
      },
    ],
  },
  plugins: [
      new HtmlWebpackPlugin({
        template: "./js/dist/index.html",
      }),
  ],
  devServer: {
      host: "0.0.0.0",
      disableHostCheck: true
  }
};