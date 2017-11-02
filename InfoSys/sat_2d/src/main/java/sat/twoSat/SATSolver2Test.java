package sat.twoSat;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import sat.CNFParser;

public class SATSolver2Test {
    public static void main(String[] args) {
        try {
            String readFile = "testcase.cnf";
            String writeFile = readFile.substring(0, readFile.length() - 4) + "Bool.txt";

            System.out.println("Reading " + readFile + "...\n");
            int[][] clauses = CNFParser.readCNF("testcase.cnf");

            System.out.println("SAT solver starts!!!");
            long started = System.nanoTime();
            SATSolver2 sat2 = new SATSolver2(clauses);
            Map<Integer, Integer> env = sat2.solve();
            long time = System.nanoTime();
            long timeTaken = time - started;
            System.out.println("Time: " + timeTaken/1000000.0 + "ms\n");

            if (env != null) {
                System.out.println("SATISFIABLE");
                System.out.println("Writing to " + writeFile + "...");
                BooleanAssignment.writeAssignments(env, writeFile);
            } else System.out.println("NOT SATISFIABLE");

            System.out.println("DONE");

        } catch (FileNotFoundException | IllegalArgumentException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println("IO Error!");
        }
    }
}
