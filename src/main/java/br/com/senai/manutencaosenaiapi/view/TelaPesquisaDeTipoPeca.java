package br.com.senai.manutencaosenaiapi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.senai.manutencaosenaiapi.entity.TipoPeca;
import br.com.senai.manutencaosenaiapi.service.TipoPecaService;
import br.com.senai.manutencaosenaiapi.view.table.TipoPecaTableModel;
import javax.swing.border.TitledBorder;

@Component
public class TelaPesquisaDeTipoPeca extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtParametro;
	private JTable table;

	@Autowired
	private TipoPecaService service;

	@Autowired
	private TelaCadastroDeTipoPeca telaCadastroDeTipoPeca;

	/**
	 * Launch the application.
	 */

	public TelaPesquisaDeTipoPeca() {
		setTitle("Pesquisa de Tipo de Peça");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblParametro = new JLabel("Parametro");

		edtParametro = new JTextField();
		edtParametro.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<TipoPeca> tipoPeca = service.listarPor(edtParametro.getText());
				TipoPecaTableModel model = new TipoPecaTableModel(tipoPeca);
				table.setModel(model);
				TableColumnModel cm = table.getColumnModel();
				cm.getColumn(0).setPreferredWidth(50);
				cm.getColumn(1).setPreferredWidth(500);
				table.updateUI();
			}
		});

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaCadastroDeTipoPeca.setVisible(true);
				setVisible(false);
			}
		});

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "A\u00E7\u00F5es para a linha selecionada", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(edtParametro, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
								.addComponent(btnPesquisar).addGap(18).addComponent(btnAdicionar))
						.addComponent(lblParametro).addComponent(panel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
								206, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblParametro)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnAdicionar)
								.addComponent(btnPesquisar).addComponent(edtParametro, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)));

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int linhaSelecionada = table.getSelectedRow();
				TipoPecaTableModel model = (TipoPecaTableModel) table.getModel();
				TipoPeca tipoPecaSelecionada = model.getPor(linhaSelecionada);
				telaCadastroDeTipoPeca.colocarEmEdicao(tipoPecaSelecionada);
				telaCadastroDeTipoPeca.setVisible(true);
				setVisible(false);
			}
		});

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int linhaSelecionada = table.getSelectedRow();
				TipoPecaTableModel model = (TipoPecaTableModel) table.getModel();
				TipoPeca tipoPecaSalva = model.getPor(linhaSelecionada);
				service.removerPor(tipoPecaSalva.getId());
				model.removerPor(linhaSelecionada);
				table.updateUI();
				JOptionPane.showMessageDialog(contentPane, "Tipo de peça removida com sucesso");
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel.createSequentialGroup().addContainerGap(43, Short.MAX_VALUE)
						.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE).addGap(29)
						.addComponent(btnExcluir).addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel
						.createSequentialGroup().addGap(21).addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnExcluir).addComponent(btnEditar))
						.addContainerGap(25, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
