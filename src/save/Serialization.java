package save;

import java.io.*;

/**
 * Basic interface which defines save method for serialization
 */
public interface Serialization {
    default void save() {
        try
        {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.getClass().getSimpleName()+".out"));

            out.writeObject(this);
            out.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
