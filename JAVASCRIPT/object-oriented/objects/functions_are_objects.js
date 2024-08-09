// in javascript functions are objects.
function Circle(radius) {
  this.radius = radius;
  this.diameter = this.radius * 2;
  this.display = function () {
    console.log("the radius is " + this.radius);
  };
}

const circle1 = new Circle(2);

// functions themselves are created using the Functions constructor, and the syntax is follows.
const Circlenew = new Function(
  "radius",
  `
    this.radius = radius;
    this.diameter = this.radius * 2;
    this.display = function () {
      console.log("the radius is " + this.radius);
    }`
);

const circle2 = new Circlenew(3);

// when we call function with/without using the new keyword

const circle3 = new Circle(5);

// internally this kindof thing happens

Circle.call({}, 3); // where 3 is the radius

// when without new

Circle.call(window, 3);

// this keyword refers to different scope when we take in consideration the new keyword is used or not.
