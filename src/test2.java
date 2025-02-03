import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import database.Entrada;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;

public class test2 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JDateChooser dateChooser; // 用于显示日期选择框
	private JTextField apellidoInput;
	private JTextField telefonoInput;
	private JLabel nombre;
	private JTextField nombreInput;
	private JLabel apellido;
	private JLabel telefono;
	private JLabel fecha;
	private JButton agregar;
	private JTable table;
	private DefaultTableModel tableModel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test2 frame = new test2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public test2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 863, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Crear calendario
		dateChooser = new JDateChooser();
		dateChooser.setBounds(576, 91, 213, 21);
		contentPane.add(dateChooser);
		
		nombre = new JLabel("Nombre:");
		nombre.setFont(new Font("SimSun", Font.BOLD, 12));
		nombre.setBounds(59, 10, 54, 15);
		contentPane.add(nombre);
		
		nombreInput = new JTextField();
		nombreInput.setBounds(576, 7, 213, 21);
		contentPane.add(nombreInput);
		nombreInput.setColumns(10);
		
		apellido = new JLabel("Apellido:");
		apellido.setFont(new Font("SimSun", Font.BOLD, 12));
		apellido.setBounds(59, 38, 78, 15);
		contentPane.add(apellido);
		
		apellidoInput = new JTextField();
		apellidoInput.setColumns(10);
		apellidoInput.setBounds(576, 35, 213, 21);
		contentPane.add(apellidoInput);
		
		telefono = new JLabel("Tel\u00E9fono:");
		telefono.setFont(new Font("SimSun", Font.BOLD, 12));
		telefono.setBounds(59, 63, 59, 15);
		contentPane.add(telefono);
		
		telefonoInput = new JTextField();
		telefonoInput.setColumns(10);
		telefonoInput.setBounds(576, 60, 213, 21);
		contentPane.add(telefonoInput);
		
		fecha = new JLabel("Fecha de la cita:");
		fecha.setFont(new Font("SimSun", Font.BOLD, 12));
		fecha.setBounds(59, 88, 141, 15);
		contentPane.add(fecha);
		
		agregar = new JButton("Agregar Cita");
		agregar.addActionListener(this);
		agregar.setBounds(348, 328, 141, 23);
		contentPane.add(agregar);
		
		
		tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setBounds(59, 150, 730, 160);
        contentPane.add(table);

        // Table
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Apellido");
        tableModel.addColumn("Teléfono");
        tableModel.addColumn("Fecha");
        
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(59, 140, 730, 150); 
        contentPane.add(scrollPane);
        
        
        loadData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 
        String nombre = nombreInput.getText();
        String apellido = apellidoInput.getText();
        String telefono = telefonoInput.getText();
        Date fecha = null;
        if (dateChooser.getDate() != null) {
            fecha = new java.sql.Date(dateChooser.getDate().getTime());  
        }

 
        if (nombre != null && apellido != null && telefono != null && fecha != null) {
            Entrada.agregarCita(nombre, apellido, telefono, fecha);  // 调用数据库操作类的插入方法
        
            JOptionPane.showMessageDialog(this, "Cita agregada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            nombreInput.setText("");
            apellidoInput.setText("");
            telefonoInput.setText("");
            dateChooser.setDate(null);
        } else {
           
        	 JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
     
    		
        loadData();    
	}
	
	private void loadData() {
        Vector<Vector<Object>> citas = Entrada.getCitas(); 
        tableModel.setRowCount(0); 

 
        for (Vector<Object> cita : citas) {
            tableModel.addRow(cita);
        }
    }
	
	
}
