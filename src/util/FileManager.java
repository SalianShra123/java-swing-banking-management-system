package util;

import model.Account;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManager {
    private static final String DATA_DIR = "data";
    private static final String ACCOUNT_FILE = DATA_DIR + File.separator + "accounts.dat";

    public static ArrayList<Account> loadAccounts() {
        File file = new File(ACCOUNT_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
            Object object = input.readObject();
            if (object instanceof ArrayList<?>) {
                ArrayList<?> rawList = (ArrayList<?>) object;
                ArrayList<Account> accounts = new ArrayList<>();
                for (Object item : rawList) {
                    if (item instanceof Account) {
                        accounts.add((Account) item);
                    }
                }
                return accounts;
            }
        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Unable to load account data: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public static void saveAccounts(ArrayList<Account> accounts) {
        File directory = new File(DATA_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(ACCOUNT_FILE))) {
            output.writeObject(accounts);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to save account data.", e);
        }
    }
}
