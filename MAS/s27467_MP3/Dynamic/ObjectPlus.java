package Dynamic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

class ObjectPlus implements Serializable {
    private static Map<Class<? extends ObjectPlus>, List<ObjectPlus>> allExtents = new HashMap<>();

    public ObjectPlus() {
        List<ObjectPlus> extent = null;
        Class theClass = this.getClass();
        if (allExtents.containsKey(theClass)) {
            extent = allExtents.get(theClass);
        } else {
            extent = new ArrayList();
            allExtents.put(theClass, extent);
        }
        extent.add(this);
    }

    public static void writeExtents(ObjectOutputStream stream) throws IOException {
        stream.writeObject(allExtents);
    }

    public static void readExtents(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        allExtents = (HashMap) stream.readObject();
    }

    public static <T extends ObjectPlus> List<T> getExtent(Class<T> type) throws ClassNotFoundException {
        if (allExtents.containsKey(type)) {
            return Collections.unmodifiableList((List<T>) allExtents.get(type));
        }
        throw new ClassNotFoundException(
                String.format("%s. Stored extents: %s",
                        type.toString(),
                        allExtents.keySet()));
    }

    protected static void removeFromExtent(ObjectPlus obj) {
        Class theClass = obj.getClass();
        if (allExtents.containsKey(theClass)) {
            List<ObjectPlus> extent = allExtents.get(theClass);
            if (extent.remove(obj)) {
                System.out.println("Object removed from: " + theClass.getSimpleName());
            }
        }
    }

    public static void showExtent(Class<? extends ObjectPlus> theClass) throws Exception {
        List<ObjectPlus> extent = null;
        if (allExtents.containsKey(theClass)) {
            extent = allExtents.get(theClass);
        } else {
            throw new Exception("Unknown class " + theClass);
        }
        System.out.println("Extent of the class: " + theClass.getSimpleName());
        for (ObjectPlus obj : extent) {
            System.out.println(obj);
        }
    }
}