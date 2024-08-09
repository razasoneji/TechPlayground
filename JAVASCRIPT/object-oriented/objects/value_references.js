// primitives are copied by value and objects are copied by refernce types.

let x = 3;
let y = x;
x++; // value of y is still 3

let obj = {
  val: 1,
};

let obj2 = obj;

obj.val++; // val of the obj2 is also changed.
