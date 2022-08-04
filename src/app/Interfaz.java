package app;

import conexionpg.ConexionPG;
import conexionpg.Crud;
import conexionpg.Empleado;
import java.sql.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Interfaz extends JFrame {

    private JPanel panel;
    private JLabel ingreso;
    private JLabel user;
    private JLabel pass;
    private JLabel botonIngresar;
    private JTextField campoUsuario;
    private JPasswordField campoPass;
    private JLabel crearEmpleados;
    private JLabel listarEmpleados;
    private JLabel actualizarEmpleados;
    private JLabel eliminarEmpleados;
    private ConexionPG con = new conexionpg.ConexionPG("localhost", "5432", "empleados", "postgres", "admin");

    public Interfaz() {
        iniciarComponentes();
        setTitle("Empleados");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/app/iconoApp.png")).getImage());
        setVisible(true);
    }

    private void iniciarComponentes() {
        panelDeComponentes();
        interfazLogin();
        interfazApp();
        setVisibleInterfazApp(false);
    }
    private void panelDeComponentes() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(this.getSize());
        panel.setLocation(0, 0);
        panel.setBackground(Color.decode("#212121"));
        this.add(panel);
    }
    private void interfazLogin() {
        ingreso = new JLabel("INGRESO DE SESION");
        ingreso.setForeground(Color.decode("#efb810"));
        ingreso.setHorizontalAlignment(SwingConstants.CENTER);
        ingreso.setVerticalAlignment(SwingConstants.CENTER);
        ingreso.setFont(new Font("Broadway", 0, 30));
        ingreso.setBounds(50, 20, 400, 50);
        panel.add(ingreso);

        user = new JLabel("•Usuario•");
        user.setForeground(Color.decode("#51D1F6"));
        user.setHorizontalAlignment(SwingConstants.LEFT);
        user.setVerticalAlignment(SwingConstants.CENTER);
        user.setFont(new Font("Cambria Math", 1, 20));
        user.setBounds(50, 150, 130, 50);
        panel.add(user);

        campoUsuario = new JTextField();
        campoUsuario.setForeground(Color.decode("#6f6f6f"));
        campoUsuario.setBackground(Color.decode("#212121"));
        campoUsuario.setHorizontalAlignment(SwingConstants.LEFT);
        campoUsuario.setFont(new Font("Centuty Schoolbook", 1, 17));
        campoUsuario.setBounds(240, 158, 140, 32);
        panel.add(campoUsuario);

        pass = new JLabel("•Contraseña•");
        pass.setForeground(Color.decode("#51D1F6"));
        pass.setHorizontalAlignment(SwingConstants.LEFT);
        pass.setVerticalAlignment(SwingConstants.CENTER);
        pass.setFont(new Font("Cambria Math", 1, 20));
        pass.setBounds(50, 250, 130, 50);
        panel.add(pass);

        campoPass = new JPasswordField();
        campoPass.setForeground(Color.decode("#6f6f6f"));
        campoPass.setBackground(Color.decode("#212121"));
        campoPass.setHorizontalAlignment(SwingConstants.LEFT);
        campoPass.setFont(new Font("Centuty Schoolbook", 1, 17));
        campoPass.setBounds(240, 258, 140, 32);
        campoPass.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    validarLogin();
                }
            }
        });
        panel.add(campoPass);

        botonIngresar = new JLabel("INGRESAR");
        botonIngresar.setForeground(Color.red);
        botonIngresar.setBackground(Color.decode("#262626"));
        botonIngresar.setBorder(new LineBorder(Color.decode("#262626"), 1, true));
        botonIngresar.setFont(new Font("Century", 1, 15));
        botonIngresar.setHorizontalAlignment(SwingConstants.CENTER);
        botonIngresar.setBounds(200, 350, 100, 35);
        botonIngresar.setOpaque(true);
        botonIngresar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                validarLogin();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botonIngresar.setFont(new Font("Century", 1, 16));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonIngresar.setFont(new Font("Century", 1, 15));
            }

        });
        panel.add(botonIngresar);
    }
    private void interfazApp() {
        crearEmpleados = new JLabel("(1)  Crear empleados");
        crearEmpleados.setFont(new Font("Leelawdee", 1, 18));
        crearEmpleados.setForeground(Color.decode("#efb810"));
        crearEmpleados.setHorizontalAlignment(SwingConstants.LEFT);
        crearEmpleados.setVerticalAlignment(SwingConstants.CENTER);
        crearEmpleados.setBounds(50, 20, 400, 50);
        crearEmpleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                crearEmpleados.setFont(new Font("Leelawdee", 1, 20));
                crearEmpleados.setForeground(Color.decode("#ffeb00"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                crearEmpleados.setFont(new Font("Leelawdee", 1, 18));
                crearEmpleados.setForeground(Color.decode("#efb810"));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                crearEmpleado();
            }

        });
        panel.add(crearEmpleados);

        listarEmpleados = new JLabel("(2)  Listar Empleados");
        listarEmpleados.setFont(new Font("Leelawdee", 1, 18));
        listarEmpleados.setForeground(Color.decode("#efb810"));
        listarEmpleados.setHorizontalAlignment(SwingConstants.LEFT);
        listarEmpleados.setVerticalAlignment(SwingConstants.CENTER);
        listarEmpleados.setBounds(50, 90, 400, 50);
        listarEmpleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                listarEmpleados.setFont(new Font("Leelawdee", 1, 20));
                listarEmpleados.setForeground(Color.decode("#ffeb00"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                listarEmpleados.setFont(new Font("Leelawdee", 1, 18));
                listarEmpleados.setForeground(Color.decode("#efb810"));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                listarEmpleado();
            }

        });
        panel.add(listarEmpleados);

        actualizarEmpleados = new JLabel("(3)  Actualizar Empleados");
        actualizarEmpleados.setFont(new Font("Leelawdee", 1, 18));
        actualizarEmpleados.setForeground(Color.decode("#efb810"));
        actualizarEmpleados.setHorizontalAlignment(SwingConstants.LEFT);
        actualizarEmpleados.setVerticalAlignment(SwingConstants.CENTER);
        actualizarEmpleados.setBounds(50, 160, 400, 50);
        actualizarEmpleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                actualizarEmpleados.setFont(new Font("Leelawdee", 1, 20));
                actualizarEmpleados.setForeground(Color.decode("#ffeb00"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                actualizarEmpleados.setFont(new Font("Leelawdee", 1, 18));
                actualizarEmpleados.setForeground(Color.decode("#efb810"));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                actualizarEmpleado();
            }

        });
        panel.add(actualizarEmpleados);

        eliminarEmpleados = new JLabel("(4)  Eliminar Empleados");
        eliminarEmpleados.setFont(new Font("Leelawdee", 1, 18));
        eliminarEmpleados.setForeground(Color.decode("#efb810"));
        eliminarEmpleados.setHorizontalAlignment(SwingConstants.LEFT);
        eliminarEmpleados.setVerticalAlignment(SwingConstants.CENTER);
        eliminarEmpleados.setBounds(50, 230, 400, 50);
        eliminarEmpleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                eliminarEmpleados.setFont(new Font("Leelawdee", 1, 20));
                eliminarEmpleados.setForeground(Color.decode("#ffeb00"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                eliminarEmpleados.setFont(new Font("Leelawdee", 1, 18));
                eliminarEmpleados.setForeground(Color.decode("#efb810"));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                eliminarEmpleado();
            }

        });
        panel.add(eliminarEmpleados);

    }

    private void setVisibleInterfazLogin(boolean status) {
        ingreso.setVisible(status);
        user.setVisible(status);
        pass.setVisible(status);
        campoUsuario.setVisible(status);
        campoPass.setVisible(status);
        botonIngresar.setVisible(status);
    }
    private void setVisibleInterfazApp(boolean status) {
        crearEmpleados.setVisible(status);
        listarEmpleados.setVisible(status);
        actualizarEmpleados.setVisible(status);
        eliminarEmpleados.setVisible(status);
    }
    private static String[][] convertir(LinkedList<Empleado> Ll) {
        String[][] datosConvertidos;
        datosConvertidos = new String[Ll.size()][4];
        for (int i = 0; i < Ll.size(); i++) {
            datosConvertidos[i][0] = String.valueOf(Ll.get(i).getIDENTIFICACION());
            datosConvertidos[i][1] = String.valueOf(Ll.get(i).getNOMBRE());
            datosConvertidos[i][2] = Ll.get(i).getFECHA_NACIMIENTO();
            datosConvertidos[i][3] = Ll.get(i).getCARGO();
        }
        return datosConvertidos;
    }
    private void validarLogin() {
        con.abrirConexion();
        if (!campoUsuario.getText().equals("") && !campoPass.getText().equals("")) {
            try {
                boolean prueba = false;
                PreparedStatement ps = con.getCon().prepareStatement("select * from usuarios where \"user\"=? and \"password\"=?");
                ps.setString(1, campoUsuario.getText());
                ps.setString(2, campoPass.getText());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    prueba = true;
                    setVisibleInterfazLogin(false);
                    setVisibleInterfazApp(true);
                }
                if (prueba) {
                    Notificacion n = new Notificacion(1);
                    n.start();
                    setVisible(false);
                    Thread.sleep(1500);
                    setVisible(true);
                    con.cerrarConexion();
                } else {
                    Notificacion nn = new Notificacion(2);
                    nn.start();
                }
            } catch (Exception ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                con.cerrarConexion();
            }
        }
    }
    private void crearEmpleado() {
        String nombre = "", id = "", fecha_nacimiento = "", cargo = "";
        try {
            nombre = JOptionPane.showInputDialog("Escriba el nombre del empleado");;
            id = JOptionPane.showInputDialog("Digite la cedula del empleado");
            fecha_nacimiento = JOptionPane.showInputDialog("Escriba la fecha del nacimiento del empleado (AAAA-MM-DD)");;
            cargo = JOptionPane.showInputDialog("Escriba el cargo empleado");
        } catch (Exception ex) {
            Notificacion n = new Notificacion(5);
            n.start();
        }
        if (nombre == null || fecha_nacimiento == null || id == null || cargo == null || nombre.equals("") || fecha_nacimiento.equals("") || id.equals("") || cargo.equals("")) {
            Notificacion n = new Notificacion(7);
            n.start();
        } else {
            try {
                long identificacion = Long.parseLong(id);
                Crud.Create(identificacion, nombre, fecha_nacimiento, cargo);
                Notificacion n = new Notificacion(1);
                n.start();
            } catch (Exception ex) {
                System.out.println(ex);
                Notificacion n = new Notificacion(7);
                n.start();
            }
        }

    }
    private void listarEmpleado() {
        ResultSet rs = Crud.Read();
        LinkedList<Empleado> empleados = new LinkedList<>();
        try {
            rs.next();
            do {
                String nombre = rs.getString("nombre");
                long identificacion = rs.getLong("identificacion");
                String fecha = rs.getString("fecha_nacimiento");
                String cargo = rs.getString("cargo");
                Empleado em = new Empleado(nombre, identificacion, fecha, cargo);
                empleados.addLast(em);
            } while (rs.next());
        } catch (Exception ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] cabezera = {"identificacion", "nombre", "fecha_nacimiento", "cargo"};
        String[][] datos = convertir(empleados);
        DefaultTableModel mod = new DefaultTableModel(datos, cabezera);
        JTable tabla = new JTable(datos, cabezera);
        tabla.setColumnSelectionAllowed(false);
        tabla.setEnabled(false);
        tabla.setFont(new Font("Arial", 1, 13));
        tabla.setBackground(Color.decode("#212121"));
        tabla.setForeground(Color.decode("#efb810"));
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(0, 0, 745, 150);
        scroll.setWheelScrollingEnabled(false);

        JFrame marco = new JFrame();
        marco.setSize(750, 180);
        marco.setLayout(null);
        marco.setResizable(false);
        marco.setLocationRelativeTo(null);
        marco.add(scroll);
        marco.setVisible(true);

    }
    private void actualizarEmpleado() {
        String id = "", nombre = "", fecha_nacimiento = "", cargo = "";
        try {
            id = JOptionPane.showInputDialog("Digite la cedula del empleado");
        } catch (Exception ex) {
            Notificacion n = new Notificacion(5);
            n.start();
        }
        try {
            nombre = JOptionPane.showInputDialog("Escriba el nuevo nombre del empleado");;
            fecha_nacimiento = JOptionPane.showInputDialog("Escriba la nueva fecha del nacimiento del empleado (AAAA-MM-DD)");;
            cargo = JOptionPane.showInputDialog("Escriba el nuevo cargo empleado");
        } catch (Exception ex) {
            Notificacion n = new Notificacion(5);
            n.start();
        }
        if (nombre == null || fecha_nacimiento == null || id == null || cargo == null || nombre.equals("") || fecha_nacimiento.equals("") || id.equals("") || cargo.equals("")) {
            Notificacion n = new Notificacion(7);
            n.start();
        } else {
            try {
                long identificacion = Long.parseLong(id);
                Crud.Update(identificacion, nombre, fecha_nacimiento, cargo);
                Notificacion n = new Notificacion(1);
                n.start();
            } catch (Exception ex) {
                Notificacion n = new Notificacion(7);
                n.start();
            }
        }
    }
    private void eliminarEmpleado() {
        try {
            String identificacion = JOptionPane.showInputDialog("Digite el mumero de la cedula del empleado a eliminar");
            if (identificacion == null || identificacion.equals("")) {
                Notificacion n = new Notificacion(3);
                n.start();
            } else {
                long l = Long.parseLong(identificacion);
                int a = Crud.Delete(l);
                if (a > 0) {
                    Notificacion n = new Notificacion(1);
                    n.start();
                } else {
                    Notificacion n = new Notificacion(6);
                    n.start();
                }
            }
        } catch (Exception ex) {
            Notificacion n = new Notificacion(5);
            n.start();
        }
    }
}
