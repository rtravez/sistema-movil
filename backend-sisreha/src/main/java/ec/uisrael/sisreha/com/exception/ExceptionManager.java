
package ec.uisrael.sisreha.com.exception;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 * 
 * @author renetravez
 * @version $1.0$
 */

public class ExceptionManager extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public static final String ALL = "All ";
	public static final String ENTCHILD = "related tables(childs)";
	public static final String FOREIGNDATA = "foreign classes data: ";
	public static final String ENTITY_SUCCESFULLYSAVED = "Entity succesfully saved";
	public static final String ENTITY_SUCCESFULLYDELETED = "Entity succesfully deleted";
	public static final String ENTITY_SUCCESFULLYMODIFIED = "Entity succesfully modified";
	public static final String ENTITY_WITHSAMEKEY = "Another Entity with the same key was found";
	public static final String ENTITY_NOENTITYTOUPDATE = "No Entity was found, with the typed key ";

	public ExceptionManager() {
	}

	public ExceptionManager(String exception) {
		super(exception);
	}

	public class NotValidFieldException extends ExceptionManager {
		private static final long serialVersionUID = 1L;

		public NotValidFieldException(String info) {
			super(info);
		}
	}

	public class NullEntityExcepcion extends ExceptionManager {
		private static final long serialVersionUID = 1L;

		public NullEntityExcepcion(String info) {
			super(info);
		}
	}

	public class EmptyFieldException extends ExceptionManager {
		private static final long serialVersionUID = 1L;

		public EmptyFieldException(String info) {
			super(info);
		}
	}

	public class NotValidFormatException extends ExceptionManager {
		private static final long serialVersionUID = 1L;

		public NotValidFormatException(String info) {
			super(info);
		}
	}

	public class DeletingException extends ExceptionManager {
		private static final long serialVersionUID = 1L;

		public DeletingException(String info) {
			super(info);
		}
	}

	public class ForeignException extends ExceptionManager {
		private static final long serialVersionUID = 1L;

		public ForeignException(String info) {
			super(info);
		}
	}

	public class GettingException extends ExceptionManager {
		private static final long serialVersionUID = 1L;

		public GettingException(String info) {
			super(info);
		}
	}

	public class FindingException extends ExceptionManager {
		private static final long serialVersionUID = 1L;

		public FindingException(String info) {
			super(info);
		}
	}

}
