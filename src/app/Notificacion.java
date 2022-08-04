package app;

import java.awt.Color;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Notificacion extends Thread { 
    
    private final JFrame ventana;
    private JPanel panel1,panel2,panel3;
    private JLabel texto;
    private Color color,txtcolor;
    private int r,g,b,a,option;
    
    public Notificacion(int option){
        this.option = option;
        this.setName("NOTIFICACION");
        ventana = new JFrame();
        ventana.setSize(300, 50);
        ventana.setAlwaysOnTop(true);
        ventana.setUndecorated(true);
        ventana.setResizable(false);
        ventana.setFocusable(false);
        ventana.setFocusableWindowState(false);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(null);
        initComponents();
        
    }
    private void initComponents(){
        
        
        String cadena;
        switch (option) {
            case 1 -> {
                cadena="EL PROCESO FUE EXITOSO";
                r=0;
                g=255;
                b=0;
                txtcolor=Color.BLACK;
            }
            case 2 -> {
                cadena="CREDENCIALES INCORRECTAS";
                r=255;
                g=255;
                b=0;
                txtcolor=Color.BLACK;
            }
            case 3 -> {
                cadena="HA OCURRIDO UN ERROR";;
                r=255;
                g=0;
                b=0;
                txtcolor=Color.BLACK;
            }
            case 4 -> {
                cadena="HA OCURRIDO UN ERROR DESCONOCIDO";
                r=0;
                g=0;
                b=0;
                txtcolor=Color.WHITE;
            }
            case 5 -> {
                cadena="OPERACION CANCELADA";
                r=0;
                g=0;
                b=0;
                txtcolor=Color.WHITE;
            }
            case 6 -> {
                cadena="EMPLEADO NO REGISTRADO";
                r=255;
                g=255;
                b=0;
                txtcolor=Color.BLACK;
            }
            case 7 -> {
                cadena="HA DILIGENCIADO MAL UN CAMPO";
                r=255;
                g=0;
                b=0;
                txtcolor=Color.BLACK;
            }
            default -> {
                cadena="EL PROCESO FUE EXITOSO";
                r=69;
                g=227;
                b=33;
                txtcolor=Color.BLACK;
            }
        }
        a=255;
        color=new Color(r, g, b, a);
        ventana.setBackground(new Color(r, g, b, a));
                
        panel1=new JPanel(null);
        panel1.setSize(300,50);
        panel1.setLocation(0,0);
        panel1.setBackground(decolorar(ventana.getBackground(), 20));
        panel1.setBorder(new LineBorder(decolorar(ventana.getBackground(), 20), 1, true));
        ventana.add(panel1);

        panel2=new JPanel(null);
        panel2.setSize(294,44);
        panel2.setLocation(3,3);
        panel2.setBackground(decolorar(ventana.getBackground(), 10));
        panel2.setBorder(new LineBorder(decolorar(ventana.getBackground(), 10), 1, true));
        panel1.add(panel2);
        
        panel3=new JPanel(null);
        panel3.setSize(288,38);
        panel3.setLocation(3,3);
        panel3.setBackground(ventana.getBackground());
        panel2.add(panel3);

        
        texto=new JLabel(cadena);
        texto.setForeground(txtcolor);
        texto.setSize(ventana.getSize().width-20, ventana.getSize().height);
        texto.setFont(new Font("Berlin Sans FB Demi", 1, 14));        
        texto.setAlignmentX(SwingConstants.CENTER);
        texto.setAlignmentY(SwingConstants.CENTER);
        texto.setLocation(0,0);
        texto.setVisible(true);
        
        panel3.add(texto);
    }
    @Override
    public void run() {
        ventana.setVisible(true);      
        ventana.setVisible(false);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Notificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        ventana.setVisible(true);     
        while (a!=0 && ventana.isVisible()) {
            a-=17;
            ventana.setBackground(new Color(r, g, b, a));
            texto.setForeground(new Color(texto.getForeground().getRed(), texto.getForeground().getGreen(), texto.getForeground().getBlue(), a));
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Notificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ventana.dispose();
    }
    
    private Color decolorar(Color color, int valor){
        int r=color.getRed(), g=color.getGreen(), b=color.getBlue();
        if(r>0 && r>=valor){
            r-=valor;
        }
        if(g>0 && g>=valor){
            g-=valor;
        }
        if(b>0 && b>=valor){
            b-=valor;
        }
        color=new Color(r, g, b, a);
        return color;
    }
    
}
