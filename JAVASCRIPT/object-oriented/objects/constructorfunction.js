function Circle(radius) {
  this.radius = radius;
  this.diameter = this.radius * 2;
  this.display = function () {
    console.log("the radius is " + this.radius);
  };
}

let circle = new Circle(2);
circle.display();
console.log(circle);
