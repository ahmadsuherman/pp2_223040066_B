package PERTEMUAN6;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProfileManager extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public ProfileManager() {
        // Set up frame
        setTitle("Pengelolaan Data Profil");
        setSize(900, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Menu");
        JMenuItem exitItem = new JMenuItem("Keluar");

        // exit functionality
        exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // form components panel
        JPanel panel = new JPanel(new GridLayout(10, 2, 10, 10));

        // Name input
        JLabel nameLabel = new JLabel("Nama:");
        final JTextField nameField = new JTextField();

        // Age input
        JLabel ageLabel = new JLabel("Umur:");
        final JSpinner ageSpinner = new JSpinner(new SpinnerNumberModel(20, 0, 100, 1));

        // Gender selection
        JLabel genderLabel = new JLabel("Jenis Kelamin:");
        final JRadioButton maleRadio = new JRadioButton("Laki Laki");
        final JRadioButton femaleRadio = new JRadioButton("Perempuan");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        
		// Hobbies selection
        JLabel hobbiesLabel = new JLabel("Hobi:");
        final JCheckBox readingCheckBox = new JCheckBox("Membaca");
        final JCheckBox sportsCheckBox = new JCheckBox("Olahraga");
        final JCheckBox musicCheckBox = new JCheckBox("Musik");

        String hobbies = (readingCheckBox.isSelected() ? "Membaca, " : "") +
                (sportsCheckBox.isSelected() ? "Olahraga, " : "") +
                (musicCheckBox.isSelected() ? "Musik, " : "");
                
		// Menghapus koma terakhir jika ada
		if (hobbies.endsWith(", ")) {
		   hobbies = hobbies.substring(0, hobbies.length() - 2);
		}
		
        // Country selection
        JLabel countryLabel = new JLabel("Kewarganegaraan:");
        final JComboBox<String> countryComboBox = new JComboBox<>(new String[]{"WNI", "WNA"});

        // Bio input
        JLabel bioLabel = new JLabel("Profil Singkat:");
        final JTextArea bioArea = new JTextArea(3, 20);
        bioArea.setLineWrap(true);
        bioArea.setWrapStyleWord(true);
        JScrollPane bioScrollPane = new JScrollPane(bioArea);

        // Skill Level
        JLabel skillLabel = new JLabel("Level Skil:");
        final JSlider skillSlider = new JSlider(0, 10, 5);
        skillSlider.setMajorTickSpacing(1);
        skillSlider.setPaintTicks(true);
        skillSlider.setPaintLabels(true);

        // Language selection
        JLabel languageLabel = new JLabel("Bahasa sehari-hari:");
        final JList<String> languageList = new JList<>(new String[]{"Indonesia", "Inggris", "Spanyol", "Francis"});
        languageList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Add button
        JButton addButton = new JButton("Simpan");

        // Table setup for displaying data
        tableModel = new DefaultTableModel(new String[]{"Nama", "Umur", "Jenis Kelamin", "Hobi", "Kewarganegaraan", "Profil Singkat", "Level Skil", "Bahasa Sehari-hari"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Add components to panel
        panel.add(nameLabel); panel.add(nameField);
        panel.add(ageLabel); panel.add(ageSpinner);
        panel.add(genderLabel);
        panel.add(new JPanel(new FlowLayout(FlowLayout.LEFT)) {{
            add(maleRadio); add(femaleRadio);
        }});
        panel.add(hobbiesLabel);
        panel.add(new JPanel(new FlowLayout(FlowLayout.LEFT)) {{
            add(readingCheckBox); add(sportsCheckBox); add(musicCheckBox);
        }});
        panel.add(countryLabel); panel.add(countryComboBox);
        panel.add(bioLabel); panel.add(bioScrollPane);
        panel.add(skillLabel); panel.add(skillSlider);
        panel.add(languageLabel); panel.add(new JScrollPane(languageList));
        panel.add(new JLabel()); panel.add(addButton);

        // Main Layout
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(tableScrollPane, BorderLayout.SOUTH);

        // Action for add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int age = (Integer) ageSpinner.getValue();
                String gender = maleRadio.isSelected() ? "Laki Laki" : femaleRadio.isSelected() ? "Perempuan" : "";
                
                // Gabungkan hobi dengan separator koma
                String hobbies = (readingCheckBox.isSelected() ? "Membaca, " : "") +
                                 (sportsCheckBox.isSelected() ? "Olahraga, " : "") +
                                 (musicCheckBox.isSelected() ? "Musik, " : "");
                
                // Menghapus koma terakhir jika ada
                if (hobbies.endsWith(", ")) {
                    hobbies = hobbies.substring(0, hobbies.length() - 2);
                }
                
                String country = (String) countryComboBox.getSelectedItem();
                String bio = bioArea.getText();
                int skillLevel = skillSlider.getValue();
                
                // Penggabungan bahasa dari JList
                List<String> selectedLanguages = languageList.getSelectedValuesList();
                StringBuilder languagesBuilder = new StringBuilder();
                for (String language : selectedLanguages) {
                    if (languagesBuilder.length() > 0) {
                        languagesBuilder.append(", ");
                    }
                    languagesBuilder.append(language);
                }
                String languages = languagesBuilder.toString();

                // Tambahkan baris ke tabel
                tableModel.addRow(new Object[]{name, age, gender, hobbies, country, bio, skillLevel, languages});
            }
        });

        // Show frame
        setVisible(true);
    }

    public static void main(String[] args) {
        new ProfileManager();
    }
}

