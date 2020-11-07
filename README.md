# AppMusic
A Java app which is a recreation of a Spotify-like application for the subject of TDS

### Cosas por hacer
Falta implementar los descuentos (y consecuentemente, hacerse premium)

### Metodos del controlador

```java
boolean registrarUsuario(String nombre, String apellidos, Date fechaNacim,
	String email, String username, String password)
```

Registra a un usuario en el sistema.
Si ya existe un usuario con el nombre de usuario indicado, devuelve false.
	
```java
boolean login(String usuario, String contraseña)
```
Devuelve un boolean indicando si se ha podido realizar el logeo del usuario.
El usuario se encuentra contenido en el controlador, y es accesible.

```java
void logout()
```
Cierra la sesion del usuario. 
Pone el usuario actual a null, y el MediaPlayer a null.

```java
Usuario getUsuarioActual()
```
Devuelve el usuario actual en la aplicacion.

```java
ListaReproduccion crearNuevaLista(String nombre)
```
Crea una nueva lista de reproduccion asociada al usuario actual con el nombre indicado.
Devuelve la nueva lista creada.
Si ya existe una lista creada con ese nombre, devuelve dicha lista.

```java
ListaReproduccion addCancionToLista(ListaReproduccion lr, Cancion cancion)
```
Añade una cancion existente en el catalogo de la aplicacion a la lista de reproduccion indicada.
Devuelve dicha lista de reproduccion.

```java
boolean removeListaReproduccion(ListaReproduccion lr)
```
Elimina la lista de reproduccion del usuario indicada
Devuelve un boolean indicando si se ha podido eliminar la lista o no.

```java
void loadCanciones()
```
Carga las canciones desde la carpeta de canciones correspondiente
Este método se hace uso de forma automática al cargar el controlador.
Es posible utilizarlo para "actualizar" las canciones del catalogo, en caso de que se añadiesen nuevas canciones.
Actualmente, el directorio indicado para las canciones es E:/AppMusic/canciones. Se encuentra en el string resourcePath (165).

```java
List<Cancion> getAllCanciones()
```
Devuelve todas las canciones existentes en el catalogo de canciones de la aplicacion.

```java
void reproducirCancion(Cancion c)
```
Método para reproducir la Cancion c.

```java
void pausarCancion()
```
Pausa la cancion actual (si hay alguna cancion reproduciendose).

```java
List<Cancion> buscarCanciones(String titulo, String interprete, String estilo)
```
Busca canciones en el catalogo de canciones las cuales se ajusten al filtro indicado.
Nota importante: el filtro es **CASE SENSITIVE o SENSIBLE A MAYUSCULAS**, por lo que es importante que se escriba de forma correcta

```java
becomePremium()
```
Método para convertir a un usuario en premium.
Este método **NO ESTA COMPLETADO**.
Actualmente solo cambia el atributo del usuario y actualiza la base de datos. No tiene en cuenta descuentos etc.
(Tampoco se si tendre que añadir algo mas).

```java
List<Cancion> getCancionesRecientes()
```
Devuelve la lista de las 10 ultimas canciones reproducidas por el usuario actual.

```java
void addCancionReciente(Cancion c)
```
Añade la Cancion c a la lista de canciones recientes del usuario.
Es posible que este metodo se vuelva private.

```java
List<ListaReproduccion> getAllListasReproduccion()
```
Devuelve todas las listas de reproduccion del usuario actual.

```java
void printPDF()
```
Imprime en un fichero PDF las listas de reproduccion del usuario actual.
Incluye el nombre de cada lista, asi como el titulo y los interpretes de cada cancion en las listas.
El fichero tiene el formato usuarioActual.getNombre()_playlists.pdf.
El fichero se genera actualmente en E:/AppMusic.

```java
List<Cancion> getCancionesMasReproducidas()
```
Devuelve la lista de las 10 canciones mas reproducidas en la aplicacion.
