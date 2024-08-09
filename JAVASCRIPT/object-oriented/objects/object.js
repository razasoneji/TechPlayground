const obj = {
  name: "raza",
  age: "18",
  display: function () {
    console.log(this.name + " " + this.age);
  },
};

obj.display();
