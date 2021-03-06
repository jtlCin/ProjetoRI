package busca;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class GUI {
	private ArquivoInvertido a;
	private Ranking r;
	private JTextArea textArea;
	private JFormattedTextField searchTextField, marcaTextField_2, processadorTextField_1;
	private JCheckBox kendalchckbxS, tfidfchckbxTfidf;
	private JComboBox soBox, hdBox, polegadasBox;
	private JFrame frmNotebooksSearch;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmNotebooksSearch.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		try {
			a = new ArquivoInvertido();
			r = new Ranking(a);
			initialize();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Nao foi possivel encontrar o arquivo do indice invertido (./ai) e nem as paginas para gerar o indice.");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frmNotebooksSearch = new JFrame();
		frmNotebooksSearch.setTitle("Notebooks Search");
		frmNotebooksSearch.setBounds(100, 100, 607, 439);
		frmNotebooksSearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNotebooksSearch.getContentPane().setLayout(null);
		
		JLabel lblOu = new JLabel("OU");
		lblOu.setBounds(88, 267, 57, 15);
		frmNotebooksSearch.getContentPane().add(lblOu);
		
		JLabel lblNewLabel = new JLabel("search");
		lblNewLabel.setBounds(12, 296, 57, 15);
		frmNotebooksSearch.getContentPane().add(lblNewLabel);
		
		searchTextField = new JFormattedTextField();
		searchTextField.setBounds(79, 294, 145, 19);
		frmNotebooksSearch.getContentPane().add(searchTextField);
		
		soBox = new JComboBox();
		soBox.setBounds(116, 86, 93, 24);
		soBox.addItem("-");
		soBox.addItem("Windows");
		soBox.addItem("Ubuntu");
		frmNotebooksSearch.getContentPane().add(soBox);
		
		JComboBox polegadasBox = new JComboBox();
		polegadasBox.setBounds(116, 185, 93, 24);
		polegadasBox.addItem("-");
		polegadasBox.addItem("13.3\"");
		polegadasBox.addItem("14\"");
		polegadasBox.addItem("15.6\"");
		polegadasBox.addItem("17.3\"");
		frmNotebooksSearch.getContentPane().add(polegadasBox);
		
		JComboBox hdBox = new JComboBox();
		hdBox.setBounds(116, 133, 93, 24);
		hdBox.addItem("-");
		hdBox.addItem("500GB");
		hdBox.addItem("512GB");
		hdBox.addItem("1TB");
		hdBox.addItem("2TB");
		frmNotebooksSearch.getContentPane().add(hdBox);
		
		processadorTextField_1 = new JFormattedTextField();
		processadorTextField_1.setBounds(116, 39, 145, 19);
		frmNotebooksSearch.getContentPane().add(processadorTextField_1);
		
		JLabel lblProcessador = new JLabel("Processador");
		lblProcessador.setBounds(12, 41, 89, 15);
		frmNotebooksSearch.getContentPane().add(lblProcessador);
		
		JLabel lblSo = new JLabel("SO");
		lblSo.setBounds(12, 91, 89, 15);
		frmNotebooksSearch.getContentPane().add(lblSo);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(12, 12, 89, 15);
		frmNotebooksSearch.getContentPane().add(lblMarca);
		
		JLabel lblHd = new JLabel("HD");
		lblHd.setBounds(12, 138, 89, 15);
		frmNotebooksSearch.getContentPane().add(lblHd);
		
		marcaTextField_2 = new JFormattedTextField();
		marcaTextField_2.setBounds(116, 10, 145, 19);
		frmNotebooksSearch.getContentPane().add(marcaTextField_2);
		
		JLabel lblPolegadas = new JLabel("Display");
		lblPolegadas.setBounds(12, 190, 89, 15);
		frmNotebooksSearch.getContentPane().add(lblPolegadas);
		
		JButton btnGo = new JButton("Go!");
		btnGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//get campos e call
				String search = searchTextField.getText();
				String marca = marcaTextField_2.getText();
				String processador = processadorTextField_1.getText();
				String SO = (String) soBox.getSelectedItem();
				String display = (String) polegadasBox.getSelectedItem();
				String hd = (String) hdBox.getSelectedItem();
				if (!SO.equals("-")) { //escolheu algo
					search += " sistemaoperacional." + SO;
				}
				if (!display.equals("-")) {
					search += " polegadatela." + display;
				}
				if (!hd.equals("-")) {
					search += " hd." + hd;
				}
				if (marca.length() > 0) {
					search += " marca." + marca;
				}
				if (processador.length() > 0) {
					search += " processador." + processador;
				}
				String string = "";
				if (!kendalchckbxS.isSelected()) {
					System.out.println("Buscando por " + search);
					string = r.searchToString(search, tfidfchckbxTfidf.isSelected());
				} else { //kendall
					System.out.println("Kendal em " + search);
					string = r.kendallToString(search);
				}
				textArea.setText(string);
			}
		});
		btnGo.setBounds(170, 324, 84, 25);
		frmNotebooksSearch.getContentPane().add(btnGo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(264, 10, 323, 368);
		frmNotebooksSearch.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		kendalchckbxS = new JCheckBox("kendal");
		kendalchckbxS.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (kendalchckbxS.isSelected()) {
					tfidfchckbxTfidf.setSelected(false);
				}
			}
		});
		kendalchckbxS.setBounds(8, 263, 82, 23);
		frmNotebooksSearch.getContentPane().add(kendalchckbxS);
		
		tfidfchckbxTfidf = new JCheckBox("tfidf");
		tfidfchckbxTfidf.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (tfidfchckbxTfidf.isSelected()) {
					kendalchckbxS.setSelected(false);
				}
			}
		});
		tfidfchckbxTfidf.setBounds(116, 263, 82, 23);
		frmNotebooksSearch.getContentPane().add(tfidfchckbxTfidf);
	}
}
