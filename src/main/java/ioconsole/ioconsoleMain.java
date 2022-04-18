package ioconsole;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ioconsoleMain {

    final static String chelp = "help";
    final static String clist = "list";
    final static String cdelete = "delete";
    final static String cadd = "add";
    final static String cauth = "auth";
    final static String helpmsg = "\n"
            + chelp + " -> LIST ALL POSSIBLE COMMANDS\n"
            + clist + " $table -> LIST ALL TABLE $table ENTRYS\n"
            + cadd + " -> ADD NEW ITEM\n"
            + cdelete + " -> DELETE ITEM\n"
            + cauth + " -> MAKE AUTHENTICATION\n";
    final static String wrongcmd = "WRONG COMMAND. SEE HELP";

    public static void printHelp() {
        System.out.println(helpmsg);
    }
    public static void wrongCommand() {
        System.out.println(wrongcmd);
    }

    public static void authParser(Scanner scanner) {
        if (!scanner.hasNext()) {
            wrongCommand();
            return;
        }
        String login = scanner.next();
        if (!scanner.hasNext()) {
            wrongCommand();
            return;
        }
        String password = scanner.next();
        if (scanner.hasNext()) {
            wrongCommand();
            return;
        }
        HttpRequester.makeAuth(login, password);
    }
    public static void deleteParser(Scanner scanner, String requestTable) {
        if (!scanner.hasNext()) {
            HttpRequester.makeRequest(requestTable, HttpRequester.requestType_deleteAll, HttpRequester.requestType_deleteAll, null);
        }

        switch (scanner.next()) {
            case HttpRequester.requestType_deleteById -> {
                if (!scanner.hasNextInt()) {
                    wrongCommand();
                    return;
                }
                Integer id = scanner.nextInt();
                if (scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                HttpRequester.makeRequest(requestTable, HttpRequester.requestType_deleteById, "deletei" + "-" + id, null);
            }
            case HttpRequester.requestType_deleteByName -> {
                if (!scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                String name = scanner.next();
                if (scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                HttpRequester.makeRequest(requestTable, HttpRequester.requestType_deleteByName, "deleten" + "-" + name, null);
            }
            default -> {
                wrongCommand();
                return;
            }
        }
    }
    public static void listParser(Scanner scanner, String requestTable) {
        if (!scanner.hasNext()) {
            HttpRequester.makeRequest(requestTable, HttpRequester.requestType_getAll, HttpRequester.requestType_getAll, null);
            return;
        }

        switch (scanner.next()) {
            case HttpRequester.requestType_getWhereAmountMore -> {
                if (!scanner.hasNextDouble()) {
                    wrongCommand();
                    return;
                }
                Double amount = scanner.nextDouble();
                if (scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                HttpRequester.makeRequest(requestTable, HttpRequester.requestType_getWhereAmountMore, "get/where_amount_more" + "-" + amount, null);
            }
            case HttpRequester.requestType_getWhereAmountLess -> {
                if (!scanner.hasNextDouble()) {
                    wrongCommand();
                    return;
                }
                Double amount = scanner.nextDouble();
                if (scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                HttpRequester.makeRequest(requestTable, HttpRequester.requestType_getWhereAmountLess, "get/where_amount_less" + "-" + amount, null);
            }
            case HttpRequester.requestType_getWhereNameStarted -> {
                if (!scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                String start = scanner.next();
                if (scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                HttpRequester.makeRequest(requestTable, HttpRequester.requestType_getWhereNameStarted, "get/where_name_started" + "-" + start, null);
            }
            case HttpRequester.requestType_getWhereChargeAmountLess -> {
                if (!scanner.hasNextDouble()) {
                    wrongCommand();
                    return;
                }
                Double amount = scanner.nextDouble();
                if (scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                HttpRequester.makeRequest(requestTable, HttpRequester.requestType_getWhereChargeAmountLess, "get/where_charge_amount_less" + "-" + amount, null);
            }
            case HttpRequester.requestType_getWhereChargeAmountMore -> {
                if (!scanner.hasNextDouble()) {
                    wrongCommand();
                    return;
                }
                Double amount = scanner.nextDouble();
                if (scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                HttpRequester.makeRequest(requestTable, HttpRequester.requestType_getWhereChargeAmountMore, "get/where_charge_amount_more" + "-" + amount, null);
            }
            case HttpRequester.requestType_getById -> {
                if (!scanner.hasNextInt()) {
                    wrongCommand();
                    return;
                }
                Integer id = scanner.nextInt();
                if(scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                HttpRequester.makeRequest(requestTable, HttpRequester.requestType_getById, HttpRequester.requestType_getById + "-" + id, null);
            }
            case HttpRequester.requestType_getByName -> {
                if (!scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                String name = scanner.next();
                if (scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                HttpRequester.makeRequest(requestTable, HttpRequester.requestType_getByName,HttpRequester.requestType_getByName + "-" + name, null);
            }
            case HttpRequester.requestType_getWhereQuantityMore -> {
                if (!scanner.hasNextInt()) {
                    wrongCommand();
                    return;
                }
                Integer quantity = scanner.nextInt();
                if (scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                HttpRequester.makeRequest(requestTable, HttpRequester.requestType_getWhereQuantityMore, "get/where_quantity_more" + "-" + quantity, null);
            }
            case HttpRequester.requestType_getWhereQuantityLess -> {
                if (!scanner.hasNextInt()) {
                    wrongCommand();
                    return;
                }
                Integer quantity = scanner.nextInt();
                if (scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                HttpRequester.makeRequest(requestTable, HttpRequester.requestType_getWhereQuantityLess, "get/where_quantity_less" + "-" + quantity, null);
            }
            case HttpRequester.requestType_getWhereSaleQuantityMore -> {
                if (!scanner.hasNextInt()) {
                    wrongCommand();
                    return;
                }
                Integer sale_quantity = scanner.nextInt();
                if (scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                HttpRequester.makeRequest(requestTable, HttpRequester.requestType_getWhereSaleQuantityMore, "get/where_sale_quantity_more" + "-" + sale_quantity, null);
            }
            case HttpRequester.requestType_getWhereSaleQuantityLess -> {
                if (!scanner.hasNextInt()) {
                    wrongCommand();
                    return;
                }
                Integer sale_quantity = scanner.nextInt();
                if (scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                HttpRequester.makeRequest(requestTable, HttpRequester.requestType_getWhereSaleQuantityLess, "get/where_sale_quantity_less" + "-" + sale_quantity, null);
            }
            default -> {
                wrongCommand();
                return;
            }
        }
    }
    public static void addParser(Scanner scanner) {
        switch (scanner.next()) {
            case HttpRequester.tsale -> {
                if (!scanner.hasNextDouble()) {
                    wrongCommand();
                    return;
                }
                Double amount = scanner.nextDouble();

                if (!scanner.hasNextInt()) {
                    wrongCommand();
                    return;
                }
                Integer quantity = scanner.nextInt();

                if (!scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                LocalDateTime time = LocalDateTime.parse(scanner.next());

                if (!scanner.hasNextInt()) {
                    wrongCommand();
                    return;
                }
                Integer warehouse_id = scanner.nextInt();

                try {
                    String jsonString = new JSONObject()
                            .put("amount", amount)
                            .put("quantity", quantity)
                            .put("sale_date", time)
                            .put("warehouse_id", warehouse_id)
                            .toString();

                    HttpRequester.makeRequest(HttpRequester.tsale, HttpRequester.requestType_add, "add/", jsonString);
                } catch (JSONException e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }
            case HttpRequester.twarehouse -> {
                if (!scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                String name = scanner.next();

                if (!scanner.hasNextInt()) {
                    wrongCommand();
                    return;
                }
                Integer quantity = scanner.nextInt();

                if (!scanner.hasNextDouble()) {
                    wrongCommand();
                    return;
                }
                Double amount = scanner.nextDouble();

                try {
                    String jsonString = new JSONObject()
                            .put("name", name)
                            .put("quantity", quantity)
                            .put("amount", amount)
                            .toString();

                    HttpRequester.makeRequest(HttpRequester.twarehouse, HttpRequester.requestType_add, "add/", jsonString);
                } catch (JSONException e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }
            case HttpRequester.texpenseItem -> {
                if (!scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                String name = scanner.next();
                try {
                    String jsonString = new JSONObject()
                            .put("name", name)
                            .toString();

                    HttpRequester.makeRequest(HttpRequester.texpenseItem, HttpRequester.requestType_add, "add/", jsonString);
                } catch (JSONException e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }
            case HttpRequester.tcharge -> {
                if (!scanner.hasNextDouble()) {
                    wrongCommand();
                    return;
                }
                Double amount = scanner.nextDouble();

                if (!scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                LocalDateTime time = LocalDateTime.parse(scanner.next());

                if (!scanner.hasNextInt()) {
                    wrongCommand();
                    return;
                }
                Integer expense_item_id = scanner.nextInt();

                if (scanner.hasNext()) {
                    wrongCommand();
                    return;
                }

                try {
                    String jsonString = new JSONObject()
                            .put("amount", amount)
                            .put("charge_date", time)
                            .put("expense_item_id", expense_item_id)
                            .toString();

                    HttpRequester.makeRequest(HttpRequester.tcharge, HttpRequester.requestType_add, "add/", jsonString);
                } catch (JSONException e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }
            default -> {
                wrongCommand();
                return;
            }
        }
    }
    public static void commandParser(String line) {
        Scanner scanner = new Scanner(line);

        if (!scanner.hasNext()) {
            wrongCommand();
            return;
        }

        switch (scanner.next()) {
            case cauth -> authParser(scanner);
            case chelp -> printHelp();
            case cdelete -> {
                if (!scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                switch (scanner.next()) {
                    case HttpRequester.twarehouse -> {
                        deleteParser(scanner, HttpRequester.twarehouse);
                    }
                    case HttpRequester.tsale -> {
                        deleteParser(scanner, HttpRequester.tsale);
                    }
                    case HttpRequester.texpenseItem -> {
                        deleteParser(scanner, HttpRequester.texpenseItem);
                    }
                    case HttpRequester.tcharge -> {
                        deleteParser(scanner, HttpRequester.tcharge);
                    }
                    default -> {
                        wrongCommand();
                        return;
                    }
                }
            }
            case clist -> {
                if (!scanner.hasNext()) {
                    wrongCommand();
                    return;
                }

                switch (scanner.next()) {
                    case HttpRequester.twarehouse -> {
                        listParser(scanner, HttpRequester.twarehouse);
                    }
                    case HttpRequester.tsale -> {
                        listParser(scanner, HttpRequester.tsale);
                    }
                    case HttpRequester.texpenseItem -> {
                        listParser(scanner, HttpRequester.texpenseItem);
                    }
                    case HttpRequester.tcharge -> {
                        listParser(scanner, HttpRequester.tcharge);
                    }
                    default -> {
                        wrongCommand();
                        return;
                    }
                }
            }
            case cadd -> {
                if (!scanner.hasNext()) {
                    wrongCommand();
                    return;
                }
                addParser(scanner);
            }
            default -> {
                wrongCommand();
                return;
            }
        }
    }

    public static void main(String[] args) {
        printHelp();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("-> ");
                String line = scanner.nextLine();
                commandParser(line);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
