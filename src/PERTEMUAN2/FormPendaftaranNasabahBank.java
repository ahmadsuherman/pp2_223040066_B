package PERTEMUAN2;

import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormPendaftaranNasabahBank extends JFrame {
	private JTextField textFieldNama;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextArea outputArea;
    private JList<String> listTabungan;
    private JSpinner spinnerFrekuensi, spinnerTanggalLahir;
    
    public FormPendaftaranNasabahBank() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Form Pendaftaran Nasabah Bank");
        this.setSize(500, 600);
        this.setLayout(null);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        
        JMenuItem menuReset = new JMenuItem("Reset");
        JMenuItem menuExit = new JMenuItem("Exit");
        
        menuReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetForm();
			}
		});
        
        menuExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
        
        menu.add(menuReset);
        menu.add(menuExit);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        
        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(20, 20, 100, 30);
        this.add(labelNama);
        
        textFieldNama = new JTextField();
        textFieldNama.setBounds(150, 20, 200, 30);
        this.add(textFieldNama);
        
        JLabel labelTabungan = new JLabel("Jenis Tabungan:");
        labelTabungan.setBounds(20, 70, 150, 30);
        this.add(labelTabungan);
        
        String[] jenisTabungan = {"Tabungan Harian", "Tabungan Berjangka", "Tabungan Anak", "Tabungan Haji"};
        listTabungan = new JList<>(jenisTabungan);
        listTabungan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listTabungan);
        scrollPane.setBounds(150, 70, 200, 60);
        this.add(scrollPane);
        
        JLabel labelFrekuensi = new JLabel("Frekuensi Transaksi:");
        labelFrekuensi.setBounds(20, 150, 150, 30);
        this.add(labelFrekuensi);
        
        SpinnerModel frekuensiModel = new SpinnerNumberModel(1, 1, 100, 1);
        spinnerFrekuensi = new JSpinner(frekuensiModel);
        spinnerFrekuensi.setBounds(150, 150, 100, 30);
        this.add(spinnerFrekuensi);
        
        JLabel labelTanggalLahir = new JLabel("Tanggal Lahir:");
        labelTanggalLahir.setBounds(20, 200, 150, 30);
        this.add(labelTanggalLahir);
        
        SpinnerDateModel dateModel = new SpinnerDateModel();
        spinnerTanggalLahir = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerTanggalLahir, "dd/MM/yyyy");
        spinnerTanggalLahir.setEditor(dateEditor);
        spinnerTanggalLahir.setBounds(150, 200, 150, 30);
        this.add(spinnerTanggalLahir);
        
        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setBounds(20, 250, 150, 30);
        this.add(labelPassword);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 250, 200, 30);
        this.add(passwordField);
        
        JLabel labelConfirmPassword = new JLabel("Confirm Password:");
        labelConfirmPassword.setBounds(20, 300, 150, 30);
        this.add(labelConfirmPassword);
        
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(150, 300, 200, 30);
        this.add(confirmPasswordField);
        
        JButton buttonSubmit = new JButton("Submit");
        buttonSubmit.setBounds(150, 350, 100, 40);
        this.add(buttonSubmit);
        
        buttonSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processForm();
            }
        });
        
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBounds(20, 400, 450, 100);
        this.add(outputArea);
    }
    
    private void processForm() {
        String nama = textFieldNama.getText();
        String jenisTabungan = listTabungan.getSelectedValue();
        int frekuensi = (Integer) spinnerFrekuensi.getValue();
        
        Date tanggalLahir = (Date) spinnerTanggalLahir.getValue();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedTanggalLahir = sdf.format(tanggalLahir);
        
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        
        String passwordMatch = password.equals(confirmPassword) ? "Password cocok" : "Password tidak cocok!";
        
        outputArea.setText(
            "Nama: " + nama + "\n" +
            "Jenis Tabungan: " + (jenisTabungan != null ? jenisTabungan : "Belum dipilih") + "\n" +
            "Frekuensi Transaksi: " + frekuensi + "\n" +
            "Tanggal Lahir: " + formattedTanggalLahir + "\n" +
            passwordMatch
        );
    }
    
    private void resetForm() {
        textFieldNama.setText("");
        listTabungan.clearSelection();
        spinnerFrekuensi.setValue(1);
        spinnerTanggalLahir.setValue(new Date());
        passwordField.setText("");
        confirmPasswordField.setText("");
        outputArea.setText("");
    }
    
    public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				FormPendaftaranNasabahBank b = new FormPendaftaranNasabahBank();
				b.setVisible(true);
			}
		});
	}
}
