var compression = require("compression");
const express = require("express");
const PORT = process.env.PORT || 5000;

express()
  .use(compression())
  .use(express.static(__dirname + '/dist', { maxAge: 8640000000000 }))
  .get('/*', (req, res) => res.sendFile('./dist/index.html', { root: __dirname }))
  .listen(PORT, () => console.log(`Listening on ${PORT}`));
