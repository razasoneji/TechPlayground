//if the interface only contains one method than it is called functional interface.

@FunctionalInterface
interface Walk{
    public void walk(int steps);
}

// Normal interface implementation.

// old way of dealing with interfaces
// also the way to deal with it when we have multiple methods.
 class SlowWalker implements Walk{
    @Override
    public void walk(int steps){
        System.out.println("The no of steps in slow are : "+ steps);
    }
}


