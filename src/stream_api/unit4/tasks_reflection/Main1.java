package stream_api.unit4.tasks_reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

public class Main1 {
    public static void main(String[] args) {
    }

    public static void printFields(Object o) throws Exception {
        Class<?> cls = o.getClass();
        for (var field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            System.out.println(field.getName() + " = " + field.get(o));
        }
    }

    public static Object create(String className) throws Exception {
        Class<?> cls = Class.forName(className);
        return cls.getDeclaredConstructor().newInstance();
    }

    public static void callSecret(Object o) throws Exception {
        var method = o.getClass().getDeclaredMethod("secret");
        method.setAccessible(true);
        method.invoke(o);
    }

    public static Object readPrivate(Object o, String fieldName) throws Exception {
        var field = o.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(o);
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface NotEmpty {
    }

    public static void validate(Object o) throws Exception {
        for (var field : o.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(NotEmpty.class)) {
                field.setAccessible(true);
                Object value = field.get(o);
                if (value == null || value.toString().isEmpty()) {
                    throw new RuntimeException("Field " + field.getName() + " is empty!");
                }
            }
        }
    }

    public static void fill(Object o, Map<String, Object> values) throws Exception {
        Class<?> cls = o.getClass();
        for (var entry : values.entrySet()) {
            try {
                var field = cls.getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(o, entry.getValue());
            } catch (NoSuchFieldException ignored) {
            }
        }
    }

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface RunMe {}

    public static void runTests (Object o) throws Exception {
        for (var method : o.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(RunMe.class)) {
                method.setAccessible(true);
                method.invoke(o);
            }
        }
    }

    public static void loadPlugin (String className) throws Exception {
        Class <?> cls = Class.forName(className);
        Object plugin = cls.getDeclaredConstructor().newInstance();
        cls.getMethod("execute").invoke(plugin);
    }

    public static boolean supports (Object o, String iface) throws Exception {
        Class <?> cls  = o.getClass();
        Class <?> target = Class.forName(iface);

        for (var i : cls.getInterfaces()) {
            if (i.equals(target)) return true;
        }
        return false;
    }

    public static String autoToString (Object o) throws Exception {
        StringBuilder sb = new StringBuilder(o.getClass().getSimpleName() + "{");
        for (var field : o.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            sb.append(field.getName())
                    .append("=")
                    .append(field.get(o))
                    .append(", ");
            if (sb.length() > 2) {sb.setLength((sb.length()) - 2);
            sb.append("}");
            return sb.toString();
            }
        }
        return "false";
    }
}