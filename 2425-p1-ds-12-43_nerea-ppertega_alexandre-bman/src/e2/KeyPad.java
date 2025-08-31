package e2;

public class KeyPad {
    public static boolean isValidKeyPad (char[][] keyPad) {
        if (keyPad == null || keyPad.length == 0 || keyPad[0] == null) {
            return false;
        }

        int rows = keyPad.length;
        int cols = keyPad[0].length;

        // Comprobar que el primer elemento es 1
        if (keyPad[0][0] != '1') {
            return false;
        }

        // Comprobar si la matriz es rectangular
        for (char[] value : keyPad) {
            if (value != null) {
                if (value.length != cols) {
                    return false;
                }
            }
        }

        // Definir secuencia esperada
        String sequence = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Si la matriz tiene un solo elemento
        if (rows == 1 && cols == 1) {
            return true;
        }
        // Si la matriz está ordenada por filas
        else if (cols > 1) { // Comprobar si la matriz solo tiene una fila
            int index = 0;
            if (keyPad[0][1] == '2') {
                for (char[] chars : keyPad) {
                    for (int j = 0; j < cols; j++) {
                        // Comprobar si los string coinciden
                        if (chars[j] != sequence.charAt(index)) {
                            return false;
                        }
                        index++;
                    }
                }
            }
        }
        // Si la matriz está ordenada por columnas
        else{ // rows > 1
            int index = 0;
            for (int i = 0; i < cols; i++) {
                for (char[] chars : keyPad) {
                    if (chars[i] != sequence.charAt(index)) {
                        return false;
                    }
                    index++;
                }
            }
        }

        System.out.println("The keyboard is VALID");
        return true;
    }

    public static boolean areValidMovements (String[] wrongLower) {
         if (wrongLower == null || wrongLower.length == 0) {
             return false;
         }
        for (String s : wrongLower) {
            if (s != null) {
                for (int j = 0; j < s.length(); j++) {
                    if (s.charAt(j) != 'U' && s.charAt(j) != 'D' && s.charAt(j) != 'L' && s.charAt(j) != 'R') {
                        return false;
                    }
                }
            }
            else {
                return false;
            }
        }
        return true;
    }

    public static String obtainCode (char[][] keyPad, String[] movements) {

        if (!isValidKeyPad(keyPad) || !areValidMovements(movements)) {
            throw new IllegalArgumentException();
        }

        int rows = keyPad.length;
        int cols = keyPad[0].length;

        StringBuilder key = new StringBuilder();
        int currentRow = 0;
        int currentCol = 0;

        // Procesar cada secuencia de movimientos
        for (String movementSeq : movements) { // Obtener secuencia individual
            for (int j = 0; j < movementSeq.length(); j++) {
                char move = movementSeq.charAt(j); // Obtener movimiento de una secuencia

                switch (move) {
                    case 'U':
                        if (currentRow != 0) {
                            currentRow--;
                        }
                        break;
                    case 'D':
                        if (currentRow != rows - 1) {
                            currentRow++;
                        }
                        break;
                    case 'L':
                        if (currentCol != 0) {
                            currentCol--;
                        }
                        break;
                    case 'R':
                        if (currentCol != cols - 1) {
                            currentCol++;
                        }
                        break;
                }
            }
            // Introducir número en la clave
            key.append(keyPad[currentRow][currentCol]);
        }
        // Devolver la clave obtenida
        return key.toString();
    }
}
