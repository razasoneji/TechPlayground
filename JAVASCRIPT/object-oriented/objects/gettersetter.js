// a function can access its variables , which are the variables declared inside it.
// as well as it can access the variables of its parent scope.

function circle(radius) {
  this.radius = radius; // not accessible by the inner funtion

  let diameter = 2 * this.radius; // private variable , cannot be used outside the circle.

  let privatefun = function () {
    // this is a private function and cannot be accessed outside the circle
  };

  this.myfun = function () {
    //variables declared here get used here and then destroyed as methods ends
    //this is kindof internal and can access its variables as well as the variables of circle,
    //cannot access the radius ones.
  };

  Object.defineProperty(this, "diameter", {
    get: function () {
      return diameter;
    },
    set: function (value) {
      diameter = value;
    },
  });
}
