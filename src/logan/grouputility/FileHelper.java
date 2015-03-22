package logan.grouputility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class FileHelper
{
	private final String groupFileName = "groups.yml";
	private final String dataFileName = "data.txt";
	private File dataFile;
	private File groupFile;
	
	public FileHelper()
	{
		dataFile = new File(dataFileName);
		groupFile = new File(groupFileName);
		if (!groupFile.exists() || !dataFile.exists()) try
		{
			dataFile.createNewFile();
			groupFile.createNewFile();
			
			try (FileWriter w = new FileWriter(groupFile, true))
			{
				w.write("groups:\n");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void saveGroups(Collection<Group> groups)
	{
		dataFile.delete();
		groupFile.delete();
		try
		{
			dataFile.createNewFile();
			groupFile.createNewFile();
			
			try (FileWriter f = new FileWriter(groupFile, true))
			{
				f.write("groups:\n");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		for (Group group : groups)
		{
			String prefix = group.getGroupPrefix() == null ? "" : group.getGroupPrefix();
			String suffix = group.getGroupSuffix() == null ? "" : group.getGroupSuffix();
			String inherit = group.getInheritGroup() == null ? "" : group.getInheritGroup();
			
			try (FileWriter writer = new FileWriter(groupFile, true))
			{
				writer.write("  " + group.getGroupName() + ":\n");
				writer.write("    default: " + group.isDefault() + "\n");
				writer.write("    permissions: \n");
				writer.write("    inheritance: \n");
				writer.write("    - " + inherit + "\n");
				writer.write("    info: \n");
				writer.write("      prefix: '" + prefix + "'\n");
				writer.write("      build: " + group.canBuild() + "\n");
				writer.write("      suffix: '" + suffix + "'\n");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			try (FileWriter writer2 = new FileWriter(dataFile, true))
			{
				writer2.write(group.getGroupName() + ";" + prefix + ";" + suffix + ";" + group.getInheritGroup() + ";" + group.isDefault() + ";" + group.canBuild() + "\n");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public Collection<Group> loadGroupsFromFile()
	{
		ArrayList<Group> groups = new ArrayList<>();
		
		try (Scanner scanner = new Scanner(dataFile))
		{
			while (scanner.hasNextLine())
			{
				String[] data = scanner.nextLine().split(";");
				groups.add(new Group(data[0], data[1], data[2], data[3], Boolean.valueOf(data[4]), Boolean.valueOf(data[5])));
			}
			
			return groups;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
