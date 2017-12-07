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
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;

public class GUI {
//	private ArquivoInvertido a = new ArquivoInvertido();
//	private Ranking r = new Ranking(a);
	private JTextArea textArea;
	private JCheckBox tfidfMark;
	private JFormattedTextField searchTextField, marcaTextField_2, processadorTextField_1, kendalTextField;
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNotebooksSearch = new JFrame();
		frmNotebooksSearch.setTitle("Notebooks Search");
		frmNotebooksSearch.setBounds(100, 100, 450, 300);
		frmNotebooksSearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNotebooksSearch.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("search");
		lblNewLabel.setBounds(12, 226, 57, 15);
		frmNotebooksSearch.getContentPane().add(lblNewLabel);
		
		searchTextField = new JFormattedTextField();
		searchTextField.setBounds(79, 224, 145, 19);
		frmNotebooksSearch.getContentPane().add(searchTextField);
		
		soBox = new JComboBox();
		soBox.setBounds(116, 71, 93, 24);
		soBox.addItem("-");
		soBox.addItem("Windows");
		soBox.addItem("Ubuntu");
		frmNotebooksSearch.getContentPane().add(soBox);
		
		JComboBox polegadasBox = new JComboBox();
		polegadasBox.setBounds(116, 150, 93, 24);
		polegadasBox.addItem("-");
		polegadasBox.addItem("13.3\"");
		polegadasBox.addItem("14\"");
		polegadasBox.addItem("15.6\"");
		polegadasBox.addItem("17.3\"");
		frmNotebooksSearch.getContentPane().add(polegadasBox);
		
		JComboBox hdBox = new JComboBox();
		hdBox.setBounds(116, 107, 93, 24);
		hdBox.addItem("-");
		hdBox.addItem("500GB");
		hdBox.addItem("512GB");
		hdBox.addItem("1TB");
		hdBox.addItem("2TB");
		frmNotebooksSearch.getContentPane().add(hdBox);
		
		processadorTextField_1 = new JFormattedTextField();
		processadorTextField_1.setBounds(116, 34, 145, 19);
		frmNotebooksSearch.getContentPane().add(processadorTextField_1);
		
		JLabel lblProcessador = new JLabel("Processador");
		lblProcessador.setBounds(12, 36, 89, 15);
		frmNotebooksSearch.getContentPane().add(lblProcessador);
		
		JLabel lblSo = new JLabel("SO");
		lblSo.setBounds(12, 76, 89, 15);
		frmNotebooksSearch.getContentPane().add(lblSo);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(12, 12, 89, 15);
		frmNotebooksSearch.getContentPane().add(lblMarca);
		
		JLabel lblHd = new JLabel("HD");
		lblHd.setBounds(12, 112, 89, 15);
		frmNotebooksSearch.getContentPane().add(lblHd);
		
		marcaTextField_2 = new JFormattedTextField();
		marcaTextField_2.setBounds(116, 10, 145, 19);
		frmNotebooksSearch.getContentPane().add(marcaTextField_2);
		
		JLabel lblPolegadas = new JLabel("Display");
		lblPolegadas.setBounds(12, 155, 89, 15);
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
				if (kendalTextField.getText().length() > 0) {
					System.out.println("Buscando por " + search);
					string = r.searchToString(search, tfidfMark.isSelected());
				} else { //kendall 
					string = r.kendallToString(search, kendalTextField.getText());
				}
				textArea.setText(string);
			}
		});
		btnGo.setBounds(330, 221, 84, 25);
		frmNotebooksSearch.getContentPane().add(btnGo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(264, 95, 166, 59);
		frmNotebooksSearch.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		kendalTextField = new JFormattedTextField();
		kendalTextField.setBounds(269, 183, 145, 19);
		frmNotebooksSearch.getContentPane().add(kendalTextField);
		
		JLabel lblSearchkendal = new JLabel("search2 (kendal)");
		lblSearchkendal.setBounds(136, 185, 127, 15);
		frmNotebooksSearch.getContentPane().add(lblSearchkendal);
		
		tfidfMark = new JCheckBox("tfidf");
		tfidfMark.setBounds(12, 195, 129, 23);
		frmNotebooksSearch.getContentPane().add(tfidfMark);
	}
}
