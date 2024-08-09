let circle = {
  radius: 1,
  diameter: 2,
  display: function () {
    console.log(
      "The radius is " + this.radius + " and the diameter is " + this.diameter
    );
  },
};

circle.display();

//creating a factory function

function createCircle(radius) {
  return {
    radius,
    diameter: radius * 2,
    display: function () {
      console.log(radius + "     " + this.diameter); // radius refers to the outer property ,but as both same it works
      //this refers to the object being returned and this.diameter is the diameter variable inside the project
    },
  };
}

let x = createCircle(4);
x.display();
