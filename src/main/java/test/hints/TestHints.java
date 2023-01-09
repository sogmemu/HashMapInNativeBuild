package test.hints;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aot.hint.ExecutableMode;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeReference;
import test.properties.TestProperties;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

@Slf4j
public class TestHints implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        addAll(hints, TestProperties.class);
        addAll(hints, TestProperties.PermissionsConfig.class);
        addAll(hints, TestProperties.Permission.class);

    }

    private static void addAll(RuntimeHints hints, Class<?> clazz) {
        addAll(hints, clazz, true);
    }

    private static void addAll(RuntimeHints hints, Class<?> clazz, boolean withInterfacesAndSuperClasses) {

        if (clazz == null) {
            return;
        }

        addAllMethods(hints, clazz);
        addAllFields(hints, clazz);
        addAllConstructors(hints, clazz);

        if (!withInterfacesAndSuperClasses) {
            return;
        }

        for (Class<?> interfaceClazz :clazz.getInterfaces()) {
            addAll(hints, interfaceClazz);
        }

        addAll(hints, clazz.getSuperclass());
    }

    private static void addAllMethods(RuntimeHints hints, Class<?> clazz) {
        var methods = clazz.getDeclaredMethods();
        for (Method method: methods) {
            hints.reflection().registerMethod(method, ExecutableMode.INVOKE);
        }
    }

    private static void addAllFields(RuntimeHints hints, Class<?> clazz) {
        var fields = clazz.getDeclaredFields();
        for(Field field: fields) {
            hints.reflection().registerField(field);
        }
    }

    private  static void addAllConstructors(RuntimeHints hints, Class<?> clazz) {
        var constructors = clazz.getConstructors();
        for (Constructor<?> constructor: constructors) {
            hints.reflection().registerConstructor(constructor, ExecutableMode.INVOKE);
        }
    }
}
