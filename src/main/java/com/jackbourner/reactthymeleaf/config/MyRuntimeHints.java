package com.jackbourner.reactthymeleaf.config;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class MyRuntimeHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        // Register method for reflection
        /*Method method1 = ReflectionUtils.findMethod(EmailValidator.class, "isValid", String.class, ConstraintValidatorContext.class);
        hints.reflection().registerMethod(method1, ExecutableMode.INVOKE);
        Method method2 = ReflectionUtils.findMethod(PasswordConstraintValidator.class, "isValid", String.class, ConstraintValidatorContext.class);
        hints.reflection().registerMethod(method2, ExecutableMode.INVOKE);
        Method method3 = ReflectionUtils.findMethod(PasswordMatchesValidator.class, "isValid", Object.class, ConstraintValidatorContext.class);
        hints.reflection().registerMethod(method3, ExecutableMode.INVOKE);*/

       // hints.reflection().registerType(EmailValidator.class, MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS);
        //hints.reflection().registerType(PasswordConstraintValidator.class, MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS);
       // hints.reflection().registerType(PasswordMatchesValidator.class, MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS);
        // Register resources
       // hints.resources().registerPattern("my-resource.txt");

        // Register serialization
       // hints.serialization().registerType(MySerializableClass.class);

        // Register proxy
       // hints.proxies().registerJdkProxy(MyInterface.class);
    }

}
