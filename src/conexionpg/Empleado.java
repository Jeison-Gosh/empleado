package conexionpg;

public class Empleado {
    private final String NOMBRE;
    private final long IDENTIFICACION;
    private final String FECHA_NACIMIENTO;
    private final String CARGO;

    public Empleado( String NOMBRE, long IDENTIFICACION, String FECHA_NACIMIENTO, String CARGO) {
        this.NOMBRE = NOMBRE;
        this.IDENTIFICACION = IDENTIFICACION;
        this.FECHA_NACIMIENTO = FECHA_NACIMIENTO;
        this.CARGO = CARGO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public long getIDENTIFICACION() {
        return IDENTIFICACION;
    }

    public String getFECHA_NACIMIENTO() {
        return FECHA_NACIMIENTO;
    }

    public String getCARGO() {
        return CARGO;
    }

  
    
}
