package productos.crud.utils;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class HandleThrow {

    public static void checkAndThrow(Supplier<Boolean> work, String message) {
        if (work.get()) throw new RuntimeException(message);
    }


}
