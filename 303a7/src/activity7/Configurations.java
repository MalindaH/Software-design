package activity7;

import java.util.ArrayList;
import java.util.List;

public class Configurations
{
	private final List<String> aConfigNames = new ArrayList<String>();
	private final List<Category> aLeft = new ArrayList<Category>();
	private final List<Category> aCenter = new ArrayList<Category>();
	private final List<Category> aRight = new ArrayList<Category>();

	public Configurations() {
		aConfigNames.add("Drink/Main/Snack");
		aLeft.add(FoodCategory.DRINK);
		aCenter.add(FoodCategory.MAIN);
		aRight.add(FoodCategory.SNACK);
	}

	/**
	 * @pre pConfigName!=null&&pLeft!=null&&pCenter!=null&&pRight!=null
	 * @param pConfigName
	 * @param pLeft
	 * @param pCenter
	 * @param pRight
	 */
	public void addConfig(String pConfigName, Category pLeft, Category pCenter, Category pRight) {
		assert pConfigName!=null&&pLeft!=null&&pCenter!=null&&pRight!=null;
		if(!aConfigNames.contains(pConfigName)) {
			aConfigNames.add(pConfigName);
			aLeft.add(pLeft);
			aCenter.add(pCenter);
			aRight.add(pRight);
		}
	}

	/**
	 * gets the position of the input config
	 * @param pConfigName
	 * @return index of pConfigName, -1 if pConfigName is not found
	 * @pre pConfigName!=null && !pConfigName.equals("")
	 */
	public int getConfigPosition(String pConfigName) {
		assert pConfigName!=null && !pConfigName.equals("");
		if(aConfigNames.contains(pConfigName)) {
			return aConfigNames.indexOf(pConfigName);
		}
		return -1;
	}


	/**
	 * removes the Configuration at the specified position
	 * @param position
	 * @pre position>=0
	 */
	public void removeConfig(int position) {
		assert position>=0;
		if(aConfigNames.size()>position) {
			aConfigNames.remove(position);
			aLeft.remove(position);
			aCenter.remove(position);
			aRight.remove(position);
		}
	}

	/**
	 * gets the name of the config at the specified position
	 * @param position
	 * @return String of name of the config
	 * @pre position >= 0 && position < aConfigNames.size()
	 */
	public String getConfigName(int position)
	{
		assert position >= 0 && position < aConfigNames.size(); 
		return aConfigNames.get(position);
	}

	public List<String> getAllConfigNames() {
		return new ArrayList<>(aConfigNames);
	}

	/**
	 * @param position
	 * @return
	 * @pre position >= 0 && position < aLeft.size()
	 */
	public Category getLeft(int position)
	{
		assert position >= 0 && position < aLeft.size(); 
		return aLeft.get(position);
	}

	/**
	 * @param position
	 * @return
	 * @pre position >= 0 && position < aCenter.size()
	 */
	public Category getCenter(int position)
	{
		assert position >= 0 && position < aCenter.size(); 
		return aCenter.get(position);
	}

	/**
	 * @param position
	 * @return
	 * @pre position >= 0 && position < aRight.size()
	 */
	public Category getRight(int position)
	{
		assert position >= 0 && position < aRight.size(); 
		return aRight.get(position);
	}

	public int getSize() {
		return aConfigNames.size();
	}


}

