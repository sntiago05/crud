# CRUD de productos (JavaFX)

Aplicación de escritorio en **JavaFX** para gestionar un catálogo de productos (crear, leer, actualizar y eliminar).

## Arquitectura
- `controller/` vistas y eventos (FXML)
- `service/` lógica de negocio
- `repository/` acceso a datos (interfaz + implementación)
- `db/Conn.java` conexión a la base de datos
- `utils/` alertas, validaciones y gestión de escenas

## Base de datos
Scripts en `src/main/resources/sql/`:
- `create.sql` crea las tablas
- `seeds.sql` inserta datos de prueba

`InitializeDB.java` ejecuta la inicialización.

## Cómo ejecutar
1. Crea la base de datos con `create.sql` y carga `seeds.sql`.
2. Revisa los datos de conexión en `db/Conn.java`.
3. Ejecuta `Launcher` desde el IDE.
