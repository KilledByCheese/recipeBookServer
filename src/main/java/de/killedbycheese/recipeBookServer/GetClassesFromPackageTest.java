package de.killedbycheese.recipeBookServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class GetClassesFromPackageTest {

	public static void main(String[] args) {

		try {
			List<String> classNameList = getClassList("de/killedbycheese/recipeBookServer/recipe/model/request");

			for (String string : classNameList) {
				System.out.println(string);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("resource")
	private static List<String> getClassList(String packageName) throws IOException {
		Set<String> classList = new HashSet<>();

		Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packageName);

		while (resources.hasMoreElements()) {
			URL url = resources.nextElement();
			String next = new Scanner((InputStream) url.getContent()).useDelimiter("\\A").next();
			classList.add(next.trim());
		}
		List<String> classNameList = null;
		for (String className : classList) {
			classNameList = Arrays.asList(className.split("\\n"));
		}

		for (int i = 0; i < classNameList.size(); i++) {
			classNameList.set(i, classNameList.get(i).substring(0, classNameList.get(i).indexOf(".")));
		}
		return classNameList;

	}
}
