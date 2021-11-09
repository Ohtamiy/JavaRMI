package engine;

import compute.*;
import java.rmi.*;
import java.rmi.server.*;

public class ComputeEngine extends UnicastRemoteObject implements Compute {
    public ComputeEngine() throws RemoteException {
        super();
    }

    public Object executeTask(Task t) {
        return t.execute();
    }

    public static void main(String[] args){
        try {
            String name = "rmi://192.168.31.133:65501/Compute";
            ComputeEngine engine = new ComputeEngine();
            Naming.rebind(name, engine);
            System.out.println("ComputeEngine bound: " + name);
        } catch(Exception e) {
            System.err.println("ComputeEngine exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
