import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

class Injector {
    private static String PROPERTIES_PATH = "src/config/inj.properties";

    public <T> T inject ( T obj ) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        Properties properties = new Properties ( );
        properties.load ( new FileInputStream ( new File ( PROPERTIES_PATH ) ) );

        Class dependency;
        Class cl = obj.getClass ( );

        Field[] fields = cl.getDeclaredFields ( );
        for (Field field : fields) {

            if ( field.getAnnotation ( AutoInjectable.class ) != null ) {

                String className = field.getType ( ).toString ( ).split ( " " )[ 1 ];
                String equalsClassName = properties.getProperty ( className, null );
                if ( equalsClassName != null ) {
                    dependency = Class.forName ( equalsClassName );
                    field.setAccessible ( true );

                    field.set ( obj, dependency.getConstructor ( ).newInstance ( ) );
                } else
                    throw new ClassNotFoundException ( "No propertiesx for class:"+ className );
            }
        }
        return obj;
    }
}
