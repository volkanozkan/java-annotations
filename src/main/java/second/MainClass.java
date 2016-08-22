package second;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.util.stream.Collectors;

public class MainClass {

	private ArrayList<Bean> beanList;
	private List<Object> instanceList;

	public static void main(String[] args) {

		MainClass mainClass = new MainClass();

		Collection<File> allFiles = new ArrayList<File>();
		List<String> fileAdressList = new ArrayList<>();

		// Find All Files In Path
		try {
			allFiles = Files.walk(Paths.get("./target/classes/second")).filter(Files::isRegularFile).map(Path::toFile)
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Fix Class Names.
		for (File file : allFiles) {
			fileAdressList.add(file.toString().substring(file.toString().lastIndexOf("/") + 1).replace(".class", ""));
		}

		mainClass.classInstance(fileAdressList);
		mainClass.calculateTimeAndPrice();

		System.out.println(mainClass.beanList.toString());

	}

	// Create Class Instance List
	public void classInstance(List<String> fileList) {
		instanceList = new ArrayList<>();
		Object[] o = new Object[fileList.size()];

		for (int i = 0; i < fileList.size(); i++) {
			try {
				o[i] = Class.forName("second." + fileList.get(i).toString()).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// Example: Avoid Secret Files In MAC OSX Like DS.Store
			}
			instanceList.add(o[i]);
		}
	}

	public void calculateTimeAndPrice() {
		beanList = new ArrayList<>();
		// get price for each of class.
		for (Object object : instanceList) {
			if (object != null) {
				Food food = object.getClass().getAnnotation(Food.class);
				Method[] methodArray = object.getClass().getMethods();
				int time = 0;
				// Calculate Time.
				for (Method method : methodArray) {
					Time timeClass = method.getAnnotation(Time.class);
					if (timeClass != null) {
						time += timeClass.takes();
					}
				}
				if (food != null) {
					beanList.add(new Bean(object.getClass().getName(), food.price(), time));
				}
			}
		}
	}

}