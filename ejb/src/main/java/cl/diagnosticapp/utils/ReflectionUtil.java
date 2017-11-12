/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diagnosticapp.utils;

import com.google.common.reflect.ClassPath;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtil {

    /**
     * Scans all classes accessible from the context class loader which belong
     * to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        System.out.println(classLoader);
//        assert classLoader != null;
//        String path = packageName.replace('.', '/');
//        System.out.println(path);
//        Enumeration<URL> resources = classLoader.getResources(path);
//        System.out.println(resources);
//        List<File> dirs = new ArrayList<>();
//        while (resources.hasMoreElements()) {
//            URL resource = resources.nextElement();
//            dirs.add(new File(resource.getFile()));
//        }
//        ArrayList<Class> classes = new ArrayList<>();
//        for (File directory : dirs) {
//            System.out.println(directory.getAbsolutePath());
//            classes.addAll(findClasses(directory, packageName));
//        }
//        return classes.toArray(new Class[classes.size()]);
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        ArrayList<Class> classes = new ArrayList<>();
        for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
            System.out.println(info.getName());
            if (info.getName().startsWith(packageName)) {
                final Class<?> clazz = info.load();
                classes.add(clazz);
            }
        }
        return classes.toArray(new Class[classes.size()]);
    }

    /**
     * Recursive method used to find all classes in a given directory and
     * subdirs.
     *
     * @param directory The base directory
     * @param packageName The package name for classes found inside the base
     * directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    public static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
