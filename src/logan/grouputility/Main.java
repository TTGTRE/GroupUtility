package logan.grouputility;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application
{
	// TODO Finish implementation of permissions browser.
	// TODO Add some sort of color demo to prefixes/suffixes.
	// TODO Add editing function.
	
	private final String title = "Group Utility 0.7a1";
	private final int width = 1000;
	private final int height = 600;
	private final GroupHelper groupHelper = new GroupHelper();
	private final FileHelper fileHelper = new FileHelper();
	
	/*
	 * Effects
	 */
	private final InnerShadow innerShadow = new InnerShadow(10, Color.BLACK);
	// private final DropShadow dropShadow = new DropShadow(10, 5, 5, Color.GRAY);
	// private final Bloom bloom = new Bloom(0.1);
	
	/*
	 * Main nodes
	 */
	private Stage stage;
	private BorderPane bPane;
	private Scene mainScene;
	private ListView<String> groupList;
	private ListView<String> permList;
	private SelectionModel<String> groupModel;
	private SelectionModel<String> permModel;
	private ContextMenu listMenu;
	private VBox leftVBox;
	private VBox rightVBox;
	
	/*
	 * Group creator
	 */
	
	private Label groupLbl;
	private TextField nameFld;
	private CheckBox defBox;
	private CheckBox buildBox;
	private Button doneBtn;
	
	// Separator
	private Separator separator;
	private Separator separator2;
	
	/*
	 * Root creator
	 */
	private Label rootLbl;
	private TextField prefixFld;
	private TextField suffixFld;
	private ComboBox<String> colorCombo;
	private SelectionModel<String> colorModel;
	
	/*
	 * Inheritance
	 */
	private ComboBox<String> inheritCombo;
	private SelectionModel<String> inheritModel;
	
	public void start(Stage stage)
	{
		this.stage = stage;
		
		bPane = new BorderPane();
		bPane.setPadding(new Insets(10, 10, 10, 10));
		
		mainScene = new Scene(bPane, width, height);
		
		groupList = new ListView<>();
		groupList.setEffect(innerShadow);
		groupList.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) ->
		{
			if (e.isShiftDown()) listMenu.show(groupList, e.getScreenX(), e.getScreenY());
		});
		groupModel = groupList.getSelectionModel();
		
		permList = new ListView<>();
		permList.setEffect(innerShadow);
		permList.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) ->
		{
			groupHelper.getGroup(groupModel.getSelectedItem()).addPermission(permModel.getSelectedItem());
		});
		
		MenuItem deleteItem = new MenuItem("Delete");
		deleteItem.addEventHandler(ActionEvent.ANY, (e) ->
		{
			groupHelper.removeGroup(groupHelper.getGroup(groupModel.getSelectedIndex()));
			groupList.getItems().remove(groupModel.getSelectedIndex());
		});
		
		MenuItem editItem = new MenuItem("Edit");
		editItem.addEventHandler(ActionEvent.ANY, (e) ->
		{
		});
		
		listMenu = new ContextMenu(deleteItem);
		
		leftVBox = new VBox();
		leftVBox.setSpacing(10);
		leftVBox.setPadding(new Insets(0, 10, 0, 0));
		
		rightVBox = new VBox();
		rightVBox.setPadding(new Insets(0, 0, 0, 10));
		rightVBox.setSpacing(10);
		
		/*
		 * Group creator instances
		 */
		groupLbl = new Label("Group Creator");
		
		nameFld = new TextField();
		nameFld.setPromptText("Group name...");
		
		defBox = new CheckBox("Default");
		
		buildBox = new CheckBox("Build");
		
		/*
		 * Root creator instances
		 */
		rootLbl = new Label("Root Creator");
		
		prefixFld = new TextField();
		prefixFld.setPromptText("Group prefix...");
		
		suffixFld = new TextField();
		suffixFld.setPromptText("Group suffix...");
		
		String[] colors =
		{
				"Black",
				"Dark Blue",
				"Dark Green",
				"Dark Aqua",
				"Dark Red",
				"Dark Purple",
				"Gold",
				"Gray",
				"Dark Gray",
				"Blue",
				"Green",
				"Aqua",
				"Red",
				"Light Purple",
				"Yellow",
				"White"
		};
		
		colorCombo = new ComboBox<>(FXCollections.observableArrayList(colors));
		colorCombo.getSelectionModel().selectLast();
		colorCombo.addEventHandler(ActionEvent.ANY, new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				StringBuilder text = new StringBuilder(prefixFld.getText());
				
				if (prefixFld.getText().startsWith("&"))
				{
					text.delete(0, 2);
					prefixFld.setText(text.toString());
				}
				
				int index = colorModel.getSelectedIndex();
				String i = null;
				
				switch (index)
				{
					case 10:
						i = "a";
						break;
					case 11:
						i = "b";
						break;
					case 12:
						i = "c";
						break;
					case 13:
						i = "d";
						break;
					case 14:
						i = "e";
						break;
					case 15:
						i = "f";
						break;
					default:
						i = Integer.toString(index);
				}
				
				prefixFld.setText("&" + i + prefixFld.getText());
			}
		});
		
		/*
		 * Inherit instances
		 */
		inheritCombo = new ComboBox<>();
		inheritModel = inheritCombo.getSelectionModel();
		
		/*
		 * Other instances
		 */
		separator = new Separator();
		separator2 = new Separator();
		
		doneBtn = new Button("Done");
		doneBtn.addEventHandler(ActionEvent.ANY, new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				Group group = new Group(nameFld.getText(), prefixFld.getText(), suffixFld.getText(), inheritModel.getSelectedItem(), defBox.isSelected(), buildBox.isSelected());
				groupHelper.addGroup(group);
				groupList.getItems().add(format(group));
				inheritCombo.getItems().add(group.getGroupName());
			}
		});
		
		leftVBox.setAlignment(Pos.TOP_CENTER);
		leftVBox.getChildren().addAll(groupLbl, nameFld, defBox, buildBox, separator, rootLbl, prefixFld, suffixFld, colorCombo, separator2, inheritCombo, doneBtn);
		
		rightVBox.setAlignment(Pos.TOP_CENTER);
		rightVBox.getChildren().add(permList);
		
		bPane.setCenter(groupList);
		bPane.setLeft(leftVBox);
		bPane.setRight(rightVBox);
		
		this.stage.setTitle(title);
		this.stage.setScene(mainScene);
		this.stage.show();
		
		this.stage.setOnCloseRequest((e) ->
		{
			fileHelper.saveGroups(groupHelper.getGroupList());
		});
		
		groupHelper.addGroups(fileHelper.loadGroupsFromFile());
		
		for (Group g : groupHelper.getGroupList())
			groupList.getItems().add(format(g));
		
		inheritCombo.getItems().setAll(groupHelper.getGroupNames());
	}
	
	private String format(Group group)
	{
		return "Name: " + group.getGroupName() + ", Prefix: " + group.getGroupPrefix() + ", Suffix: " + group.getGroupSuffix() + ", Inherits: " + group.getInheritGroup() + ", Default: " + group.isDefault() + ", Build: " + group.canBuild();
	}
	
	public static void main(String[] args)
	{
		launch();
	}
}
