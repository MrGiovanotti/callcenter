package ec.com.nashira.callcenter.utils;

public class ConstantsUtils {

  private ConstantsUtils() {

  }

  // PUNCTUATION MARKS
  public static final String DASH_SEPARATOR = " - ";
  public static final String COLON_SEPARATOR = " : ";
  public static final String LINE_BREAK = "\n";
  public static final String PERIOD = ".";
  public static final String UNDERSCORE_SEPARATOR = "_";

  // MESSAGES
  public static final String NOT_FOUND_RESOURCE_MESSAGE = "Recurso no encontrado";
  public static final String FOUND_RESOURCE_MESSAGE = "Recurso encontrado";
  public static final String DATABASE_ERROR_MESSAGE =
      "Error relacionado con la persistencia en la base de datos";
  public static final String VALIDATION_ERROR_MESSAGE = "Existen errores de validación";
  public static final String CREATED_MESSAGE = "Creado con éxito";
  public static final String UPDATED_MESSAGE = "Actualizado con éxito";
  public static final String SECRET_UPDATED_MESSAGE = "Contraseña cambiada con éxito";
  public static final String DELETED_MESSAGE = "Eliminación exitosa";
  public static final String INTERNAR_SERVER_ERROR_MESSAGE = "Error interno del sistema";
  public static final String UPLOAD_FILE_ERROR_MESSAGE = "Error en carga de archivo";
  public static final String UPLOADED_IMAGE_MESSAGE = "Imagen cargada exitosamente";
  public static final String SHOW_IMAGE_ERROR_MESSAGE = "Problemas al mostrar la imagen";
  public static final String DELETE_FILE_ERROR_MESSAGE = "No se pudo borrar un archivo";

  // CONFIGURATIONS
  public static final int NUMBER_ITEMS_PER_PAGE = 4;

  // SECURITY
  public static final String ADMIN_ROLE = "ROLE_ADMIN";

}
