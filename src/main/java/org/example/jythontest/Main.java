/*
 * Build with: mvn deploy
 * Run as: java -jar target/jythontest-1.0.0-SNAPSHOT-jar-with-dependencies.jar
 */

package org.example.jythontest;

import org.python.util.PythonInterpreter;
import org.python.core.*;


public class Main
{
    static String pyPath = "./";
    static String pyScript = "test"; // Filename sans ".py"

    public static void main( String[] args )
    {
        PySystemState pysys = new PySystemState();
        pysys.path.insert(0, Py.java2py(pyPath));
        PyObject importer = pysys.getBuiltins().__getitem__(Py.newString("__import__"));
        PyObject module = importer.__call__(Py.newString(pyScript));
        PyFunction pyFun = (PyFunction)module.__getattr__("fun");
        try
        {
            int rc = pyFun.__call__().asInt();
            System.out.println("Python returned: " + rc);
        }
        catch (Exception e)
        {
            System.out.println("argh");
        }
    }
}
