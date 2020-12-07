import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class Main {
    public static void main(String... args) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        Bean sb = (new Injector()).inject(new Bean());
        sb.go();
    }
}
