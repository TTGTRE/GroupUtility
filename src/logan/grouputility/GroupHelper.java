package logan.grouputility;

import java.util.ArrayList;
import java.util.Collection;

public class GroupHelper
{
	private ArrayList<Group> groupList = new ArrayList<>();
	
	public void addGroup(Group group)
	{
		groupList.add(group);
	}
	
	public void addGroups(Collection<Group> groups)
	{
		for (Group g : groups)
			groupList.add(g);
	}
	
	public Group getGroup(String groupName)
	{
		for (Group g : groupList)
		{
			if (g.getGroupName().equalsIgnoreCase(groupName)) return g;
		}
		return null;
	}
	
	public Group getGroup(int index)
	{
		return groupList.get(index);
	}
	
	public void removeGroup(Group group)
	{
		groupList.remove(group);
	}
	
	public void removeGroup(String groupName)
	{
		for (Group g : groupList)
		{
			if (g.getGroupName().equalsIgnoreCase(groupName))
			{
				groupList.remove(groupName);
				break;
			}
		}
	}
	
	public Collection<Group> getGroupList()
	{
		return groupList;
	}
	
	public Collection<String> getGroupNames()
	{
		ArrayList<String> groupNames = new ArrayList<>();
		for (Group g : groupList)
		{
			groupNames.add(g.getGroupName());
		}
		return groupNames;
	}
}