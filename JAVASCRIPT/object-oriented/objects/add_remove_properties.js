function Animal(name) {
  this.name = name;
  this.display = function () {
    console.log(this.name);
  };
}

let x = new Animal("donkey");
x.display();

x.ijjat = false;

console.log(x);

delete x.ijjat;
console.log(x);
