let x = [1, 2, 3];

let y = {
  name: "raza",
  year: 3,
  college: "ddit",
  display: function () {
    console.log("inside the display fn");
  },
};

for (let key in x) {
  console.log(x[key]);
}

if (2 in x) {
  console.log("2 is present as an index");
}

for (let key in y) {
  console.log(y[key]);
}
