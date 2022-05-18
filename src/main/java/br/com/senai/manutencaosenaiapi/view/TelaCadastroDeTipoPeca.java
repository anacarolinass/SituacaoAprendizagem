package br.com.senai.manutencaosenaiapi.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import br.com.senai.manutencaosenaiapi.entity.TipoPeca;
import br.com.senai.manutencaosenaiapi.service.TipoPecaService;

@Component
public class TelaCadastroDeTipoPeca extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtId;
	private JTextField edtDescricao;

	@Autowired
	private TipoPecaService service;

	@Autowired
	@Lazy
	private TelaPesquisaDeTipoPeca telaPesquisaDeTipoPeca;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroDeTipoPeca frame = new TelaCadastroDeTipoPeca();
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
	public TelaCadastroDeTipoPeca() {
		setFont(new Font("Dialog", Font.PLAIN, 15));
		setTitle("Cadastro de Tipos de Peça");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 161);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Dialog", Font.BOLD, 12));

		edtId = new JTextField();
		edtId.setEnabled(false);
		edtId.setColumns(10);

		JLabel lblDescricao = new JLabel("Descrição");
		lblDescricao.setFont(new Font("Dialog", Font.BOLD, 12));

		edtDescricao = new JTextField();
		edtDescricao.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					if (edtId.getText() != null && edtId.getText().length() > 0) {
						TipoPeca tipoPecaSalva = new TipoPeca();
						tipoPecaSalva.setDescricao(edtDescricao.getText());
						tipoPecaSalva.setId(Integer.parseInt(edtId.getText()));
						service.alterar(tipoPecaSalva);
						JOptionPane.showMessageDialog(contentPane, "Tipo de peça alterada com sucesso");
					} else {
						TipoPeca novaTipoPecaSalva = new TipoPeca();
						novaTipoPecaSalva.setDescricao(edtDescricao.getText());
						TipoPeca tipoPecaSalva = service.inserir(novaTipoPecaSalva);
						edtId.setText(tipoPecaSalva.getId().toString());
						JOptionPane.showMessageDialog(contentPane, "Tipo de peça inserida com sucesso");
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage());
				}

			}
		});

		btnSalvar.setForeground(new Color(0, 0, 0));
		btnSalvar.setFont(new Font("Dialog", Font.BOLD, 12));

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaPesquisaDeTipoPeca.setVisible(true);
				setVisible(false);
			}
		});
		btnSair.setForeground(Color.BLACK);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblId).addComponent(edtId, GroupLayout.PREFERRED_SIZE, 47,
														GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblDescricao).addComponent(edtDescricao,
														GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE))
										.addGap(115))
								.addGroup(Alignment.TRAILING,
										gl_contentPane.createSequentialGroup().addComponent(btnSalvar)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnSair,
														GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
												.addGap(98)))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane
								.createParallelGroup(Alignment.BASELINE).addComponent(lblDescricao).addComponent(lblId))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(edtDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(edtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnSair)
								.addComponent(btnSalvar))));
		contentPane.setLayout(gl_contentPane);
	}

	public void colocarEmEdicao(TipoPeca tipoPecaSalva) {
		edtId.setText(tipoPecaSalva.getId().toString());
		edtDescricao.setText(tipoPecaSalva.getDescricao());

	}
}
