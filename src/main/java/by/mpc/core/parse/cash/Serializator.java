package by.mpc.core.parse.cash;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import by.mpc.core.model.ListRoute;

public class Serializator {

	public static boolean serialization(ListRoute listRoute, String filePath, String fileName) {
		boolean flag = false;
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}
		file = new File(filePath + "/" + fileName);
		try (ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(file))) {
			ostream.writeObject(listRoute);
			flag = true;
		} catch (IOException e) {
			System.err.println("Ошибка ввода/вывода: " + e);
		}
		return flag;
	}

	public static ListRoute deserialization(String filePath, String fileName) {
		ListRoute listRoute = null;
		File file = new File(filePath + "/" + fileName);
		if (file.exists()) {
			try (ObjectInputStream istream = new ObjectInputStream(new FileInputStream(filePath + "/" + fileName))) {
				listRoute = (ListRoute) istream.readObject();
			} catch (IOException e) {
				System.err.println("Ошибка ввода/вывода: " + e);
			} catch (ClassNotFoundException e) {
				System.err.println("Класс не найден: " + e);
			}
		}
		return listRoute;
	}

}
