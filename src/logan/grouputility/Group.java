package logan.grouputility;

import java.util.ArrayList;
import java.util.Collection;

public class Group
{
	private String groupName;
	private String groupPrefix;
	private String groupSuffix;
	private String inheritGroup;
	private boolean isDefault;
	private boolean canBuild;
	private ArrayList<String> permissions;
	
	public Group(String groupName)
	{
		this.groupName = groupName;
	}
	
	public Group(String groupName, String groupPrefix, String groupSuffix, String inheritGroup, boolean isDefault, boolean canBuild)
	{
		this.groupName = groupName;
		this.groupPrefix = groupPrefix;
		this.groupSuffix = groupSuffix;
		this.inheritGroup = inheritGroup;
		this.isDefault = isDefault;
		this.canBuild = canBuild;
	}
	
	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}
	
	public void setGroupPrefix(String groupPrefix)
	{
		this.groupPrefix = groupPrefix;
	}
	
	public void setGroupSuffix(String groupSuffix)
	{
		this.groupSuffix = groupSuffix;
	}
	
	public void setInheritance(String groupName)
	{
		this.inheritGroup = groupName;
	}
	
	public void setDefault(boolean isDefault)
	{
		this.isDefault = isDefault;
	}
	
	public void allowBuild(boolean canBuild)
	{
		this.canBuild = canBuild;
	}
	
	public void addPermission(String permission)
	{
		this.permissions.add(permission);
	}
	
	public void addPermissions(String... permissions)
	{
		for (String s : permissions)
		{
			this.permissions.add(s);
		}
	}
	
	public String getGroupName()
	{
		return groupName;
	}
	
	public String getGroupPrefix()
	{
		return groupPrefix;
	}
	
	public String getGroupSuffix()
	{
		return groupSuffix;
	}
	
	public String getInheritGroup()
	{
		return inheritGroup;
	}
	
	public boolean isDefault()
	{
		return isDefault;
	}
	
	public boolean canBuild()
	{
		return canBuild;
	}
	
	public Collection<String> getGroupPerms()
	{
		return permissions;
	}
}
