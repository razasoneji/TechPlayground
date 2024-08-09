function Hackathon(name, location) {
  this.name = name;
  this.location = location;
  this.budget = 123;
  this.showbudget = function (value) {
    if (value !== 0.1) {
      console.log("false");
    } else {
      console.log(true);
    }
  };

  this.viewbudget = function () {
    this.showbudget(0.1);
  };
}

let synaps = new Hackathon("synaps", "california");

// synaps.showbudget is directly not accessible , such kindof logics can be created for abstractions.
// without any functionality.
synaps.viewbudget();
