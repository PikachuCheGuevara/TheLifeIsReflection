import reflection.SomeInterface;
import reflection.SomeOtherInterface;

class Bean {
  
   @AutoInjectable
   private SomeInterface someField;
   @AutoInjectable
   private SomeOtherInterface otherField;

    public Bean() {
    }


    public void go(){
       someField.doSome();
       otherField.doSome();
   }
    
    
}
