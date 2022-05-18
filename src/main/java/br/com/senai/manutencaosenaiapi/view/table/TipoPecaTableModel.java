package br.com.senai.manutencaosenaiapi.view.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.senai.manutencaosenaiapi.entity.TipoPeca;

public class TipoPecaTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private final int QTDE_COLUNAS = 2;

	private List<TipoPeca> tipoPeca;

	public TipoPecaTableModel(List<TipoPeca> tipoPeca) {
		this.tipoPeca = tipoPeca;
	}

	@Override
	public int getRowCount() {
		return tipoPeca.size();
	}

	@Override
	public int getColumnCount() {
		return QTDE_COLUNAS;
	}

	@Override
	public String getColumnName(int column) {
		if (column == 0) {
			return "ID";
		} else if (column == 1) {
			return "Descrição";
		}
		throw new IllegalArgumentException("Indice inválido");
	}

	public TipoPeca getPor(int rowIndex) {
		return tipoPeca.get(rowIndex);
	}

	public void removerPor(int rowIndex) {
		tipoPeca.remove(rowIndex);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		if (columnIndex == 0) {
			return tipoPeca.get(rowIndex).getId();
		} else if (columnIndex == 1) {
			return tipoPeca.get(rowIndex).getDescricao();
		}

		throw new IllegalArgumentException("Indice inválido");
	}

}
