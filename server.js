const express = require("express");
const app = express();

app.use(express.json());
app.use(express.static(".")); // pour servir index.html

let data = {
  ldr: 0,
  temp: 0,
  motion: 0,
  brightness: 0
};

// recevoir données (simulation ou ESP32)
app.post("/data", (req, res) => {
  data = req.body;
  console.log("DATA:", data);
  res.sendStatus(200);
});

// envoyer données au dashboard
app.get("/data", (req, res) => {
  res.json(data);
});

// contrôle LED depuis dashboard
app.post("/control", (req, res) => {
  const { brightness } = req.body;
  data.brightness = brightness;
  console.log("LED Control:", brightness);
  res.sendStatus(200);
});

app.listen(3000, () => {
  console.log("Serveur lancé sur http://localhost:3000");
});