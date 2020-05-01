package ua.polina.controller.config;

import ua.polina.model.entity.Role;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SecurityConfig {
    private static final Map<Role, Set<String>> mapConfig = new HashMap<>();

    static {
        init();
    }
    private static void init(){
        Set<String> adminGetUrlPatterns = new HashSet<>();
        adminGetUrlPatterns.add("add-description");
        mapConfig.put(Role.ADMIN, adminGetUrlPatterns);
    }

    public static  boolean isSecured(String url){
        return mapConfig.values()
                .stream()
                .flatMap(Set::stream)
                .anyMatch(pattern -> pattern.equals(url));
    }

    public static boolean isAccessAllowed(String url,Set<Role> roles){
        return mapConfig.entrySet()
                .stream()
                .filter(entry -> entry.getValue().contains(url))
                .map(Map.Entry::getKey)
                .anyMatch(roles::contains);
    }

}
