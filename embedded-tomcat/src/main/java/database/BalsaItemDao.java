package database;

import java.util.List;

import model.BalsaItem;

public interface BalsaItemDao {

	public List<BalsaItem> getAllItems();

	public BalsaItem getItem(int id);

	public boolean addItem(BalsaItem balsalevy);

	public boolean removeItem(BalsaItem balsalevy);
}