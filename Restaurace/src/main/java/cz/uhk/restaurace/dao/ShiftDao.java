package cz.uhk.restaurace.dao;

import java.util.List;
import cz.uhk.restaurace.model.Shift;

public interface ShiftDao {
	public void addShift(Shift shift);
	public void updateShift(Shift shift);
	public List<Shift> listShift();
	public Shift getShiftById(int id);
	public void removeShift(int id);

}
